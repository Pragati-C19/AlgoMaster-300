import java.util.*;
import java.util.stream.Collectors;

public class UpdateMatrix {
    
    public int[][] updateMatrix(int[][] mat) {
        
        // Declare variables
        int m = mat.length;
        int n = mat[0].length;
        boolean[][] visitedCell = new boolean[m][n];
        int[][] matrixDirection = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
        };

        // declare a queue to store unvisited cells
        Queue<int[]> queue = new LinkedList<>();

        // Initially store all 0's in the queue 
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                if (mat[i][j] == 0) {
                    visitedCell[i][j] = true;
                    queue.add(new int[] {i, j});
                }
            }
        }
        // DEBUGGER : To print int[] queue with sout statement we can use for loop too but I just need to do it with one liner so
        System.out.println(" Initial Queue : " + queue.stream()
                                                    .map(Arrays::toString)
                                                    .collect(Collectors.joining(", ", "[", "]"))
                          );

                          
        // Let's replace matrix cell value with their distance from 0
        while (!queue.isEmpty()) {
            
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                
                int[] popOutTopCell = queue.poll();

                // here row (i) = cell[0] and col (j) = cell[1]
                int row = popOutTopCell[0];
                int col = popOutTopCell[1];

                for (int[] dir : matrixDirection) {
                    
                    int x = row + dir[0];
                    int y = col + dir[1];

                    // if I found any fresh orange(1) will replace it with rotten orange(2)
                    if (x >= 0 && x < m && y >= 0 && y < n && visitedCell[x][y] != true) {
                        
                        mat[x][y] = mat[row][col] + 1;
                        queue.add(new int[] {x, y});
                        
                        visitedCell[x][y] = true;

                        System.out.println("  - Updated matrix for (" + x + ", " + y + ") : " + mat[x][y]);
                    }
                }
            }
        }

        return mat;
    }

    public static void main(String[] args) {

        UpdateMatrix solution = new UpdateMatrix();

        int[][] grid1 = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        System.out.println("Result 1 : " + Arrays.deepToString(solution.updateMatrix(grid1)) + "\n");      

        int[][] grid2 = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 1, 1}
        };
        System.out.println("Result 2 : " + Arrays.deepToString(solution.updateMatrix(grid2)) + "\n");      

    }

}

/*
  
 * Intuitions :
 
    1. We have given a binary matrix with size m x n
    2. return the distance of nearest 0 for each cell
    3. 
 
 * Pattern :
 
    1. BFS is pattern like below
        - we need queue
        - add initial value in queue
        - will check if queue.isEmpty
        - then use for loop ( dir : matrixDirection)
        - use base case if 
        - do actual changes and add that cell to queue
        - return the result

    2. let's think what we need to store in queue?
        - will store all zeoros in the queue
        - Initially store all cells with value zero in queue
        - then start while loop till !queue.isEmpaty
        - pop out the top cell
        - check it's directions (BRUL)
            if u get any unvisited cell 
            visit it and add it in queue
            also replace it's num to topcell[i][j] + 1
        - at the end return matrix

    3. why replace it's num to topcell[i][j] + 1 ?
        
        - let's take this as an example :
                0   1   1   1   1
                1   1   1   0   1
                1   0   1   1   1
                1   1   1   1   0

        - so at first our queue will be : [[0,0], [1,3], [2,1], [3,4]]
        - now we pop out [0,0]
            check unvisited cells near that poppedCell 
            add that unvisited cell in queue
            also changed that cell [1,0] from 1 to mat[0,0] (0) + 1 = 1 
        - after checking BRUL of [0,0] queue is : [[1,3], [2,1], [3,4], [1,0], [0,1]]
        - now we pop out [1,3]
            check unvisited cells near that poppedCell
            add that unisted cell in queue
            also changed that cell (BRUL cells) from 1 to mat[1,3] (0) + 1 = 1
        
        - like that matrix will look like
                0   1   2   1   2
                1   1   1   0   1
                1   0   1   1   1
                2   1   2   1   0



 * Pseudo Code :
 

 
 */