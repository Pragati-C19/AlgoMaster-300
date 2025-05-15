import java.util.*;
import java.util.stream.Collectors;

public class UpdateMatrix {
    
    public int[][] updateMatrix(int[][] mat) {
        
        // Declare variables
        int m = mat.length;
        int n = mat[0].length;
        boolean[][] visitedCell = new boolean[m][n];

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