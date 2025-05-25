import java.util.*;

public class SwimInWater {
    
    public int swimInWater(int[][] grid) {
        
        // Declare Variables
        int m = grid.length;
        int n = grid[0].length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        int[][] bestTimeTaken = new int[m][n];
        int[][] matrixDirection = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
        };
        boolean[][] visitedCell = new boolean[m][n];


        // Add Initial Value
        minHeap.add(new int[]{0, 0, 0});

        // let's start while loop
        while (!minHeap.isEmpty()) {
            
            int[] popMinHeap = minHeap.poll();

            int currRow = popMinHeap[0];
            int currCol = popMinHeap[1];
            int currTimeTaken = popMinHeap[2];

            int currCellElevation = grid[currRow][currCol];

            if (currRow == (m - 1) && currCol == (n - 1)) {
                
                System.out.println("We have reach to the end of Matrix... ");
                return currTimeTaken;
            }

            // Mark this cell as visited
            visitedCell[currRow][currCol] = true;

            // Check neighbors
            for (int[] dirs : matrixDirection) {
                
                int x = dirs[0] + currRow;
                int y = dirs[1] + currCol;

                if (x >= 0 && y >= 0 && x < m && y < n && !visitedCell[x][y]) {
                    
                    // check height of this neighbor cell
                    int neighborCellElevation = grid[x][y];

                    // get abs difference between currCell and BRUL Cell
                    int diff = currCellElevation - neighborCellElevation;
                    System.out.println("        - abs diff of elevations between (" + currRow + ", " + currCol + ") and (" + x + ", " + y + ") : " + Math.abs(diff));
                    
                    // now check maximum effort in this path and add it in minHeap
                    int timeTakenSoFar = currTimeTaken + Math.abs(diff);
                    System.out.println("        - TimeTakenSoFar at (" + x + ", " + y + ") : " + timeTakenSoFar);

                    minHeap.add(new int[]{x, y, timeTakenSoFar});
                }
            }

            System.out.println("    - Updated minHeap : " + Arrays.deepToString(minHeap.toArray()));
            System.out.println("    - Updated bestTimeTaken : " + Arrays.deepToString(bestTimeTaken));

        }

        return 0;
    }

    public static void main(String[] args) {
        
        SwimInWater solution = new SwimInWater();

        int[][] grid1 = {
            {0, 2},
            {1, 3}
        };
        System.out.println("Result 1 -> " + solution.swimInWater(grid1) + "\n");   // Output: 3

        int[][] grid2 = {
            {0, 1, 2, 3, 4},
            {24, 23, 22, 21, 5},
            {12, 13, 14, 15, 16},
            {11, 17, 18, 19, 20},
            {10, 9, 8, 7, 6}
        };
        System.out.println("Result 2 -> " + solution.swimInWater(grid2) + "\n");   // Output: 16

    }

}

/*
 * Intuitions :
 
    1. We have given an matrix grid with size n x n
        - where each value grid[i][j] represents the elevation at that point (i, j)
    2. Rain starts to fall at time t
    3. You can swim in BRUL directions
    4. return the least time until we can reach the bottom right square 

 
 * Pattern :
 
    1. We need an minHeap will store {row, col, timeTakenSoFar}
    2. we need bestTimeTaken array to store minimun time it takes to reach that cell
    3. we need matrixDirection and visitedCell array 
    4. add initial values in minHeap
        - {0, 0, 0}
    5. start while loop till minHeap get's empty
        - Pop out top
            currRow, currCol, currTimeTaken
        - if we have reach to the end of matrix
            return currTimeTaken
        - mark cell as visited

    6. Check neighbors
        - check time needed to jump on this neighbor cell
            timeNeededSoFar = grid[neighborRow][neighborCol] - grid[currRow][currCol]

        - if timeNeededSoFar > bestTimeTaken[neighborRow][neighborCol]
            bestTimeTaken[neighborRow][neighborCol] = timeNeededSoFar
            minHeap.add({neighborRow, neighborCol, timeNeededSoFar})

    7. This que is soo similar as minimumEffortPath one.. 
        - we want to check timeTaken jo diff ahe consecutive cells cha 
        - jo maxTime asel tya path vrcha will add it in minHeap
        - and to minHeap nehmi min timeTaken vali cell pop karat jail 
 
 * Psuedo Code :
 


 */