import java.util.*;

public class MinimumEffortPath {
    
    public int minimumEffortPath(int[][] heights) {
        
        // Declare Variables
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        int[][] bestEfforts = new int[m][n];
        int[][] matrixDirection = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
        };
        boolean[][] visitedCell = new boolean[m][n];


        // Initially add values in minHeap {row, col, effortsSoFar}
        minHeap.add(new int[]{0, 0, 0});
        // bestEfforts[0][0] = heights[0][0];

        System.out.println("Initially minHeap : " + Arrays.deepToString(minHeap.toArray()));
        System.out.println("Initially bestEfforts : " + Arrays.deepToString(bestEfforts));


        // Start while loop till minHeap get's empty
        while (!minHeap.isEmpty()) {
            
            int[] popMinHeap = minHeap.poll();

            int currRow = popMinHeap[0];
            int currCol = popMinHeap[1];
            int currEffort = popMinHeap[2];

            int currCellHeight = heights[currRow][currCol];

            System.out.println("    Visiting cell : " + Arrays.toString(popMinHeap) + " with height " + currCellHeight);

            if (currRow == (m - 1) && currCol == (n - 1)) {
                
                System.out.println("We have reach to the end of Matrix... ");
                return currEffort;
            }

            // Mark this cell as visited 
            visitedCell[currRow][currCol] = true;

            // check BRUL directions
            for (int[] dirs : matrixDirection) {
                
                int x = dirs[0] + currRow;
                int y = dirs[1] + currCol;

                // Wrote a base case 
                if (x >= 0 && y >= 0 && x < m && y < n && !visitedCell[x][y]) {
                    
                    // check height of this neighbor cell
                    int neighborCellHeight = heights[x][y];

                    // get abs difference between currCell and BRUL Cell
                    int diff = currCellHeight - neighborCellHeight;

                    System.out.println("        - abs diff of heights between (" + currRow + ", " + currCol + ") and (" + x + ", " + y + ") : " + Math.abs(diff));
                    
                    // now check maximum effort in this path and add it in minHeap
                    int effortSoFar = Math.max(currEffort, Math.abs(diff));

                    System.out.println("        - abs diff of heights between (" + currRow + ", " + currCol + ") and (" + x + ", " + y + ") : " + Math.abs(diff));

                    minHeap.add(new int[]{x, y, effortSoFar});
                }
            }

            System.out.println("    - Updated minHeap : " + Arrays.deepToString(minHeap.toArray()));
            System.out.println("    - Updated bestEfforts : " + Arrays.deepToString(bestEfforts));

        }

        return 0;
    }

    public static void main(String[] args) {
        
        MinimumEffortPath solution = new MinimumEffortPath();

        int[][] heights1 = {
            {1, 2, 2},
            {3, 8, 2},
            {5, 3, 5}
        };
        System.out.println("Result 1 -> " + solution.minimumEffortPath(heights1) + "\n");   // Output: 2

        int[][] heights2 = {
            {1, 2, 3},
            {3, 8, 4},
            {5, 3, 5}
        };
        System.out.println("Result 2 -> " + solution.minimumEffortPath(heights2) + "\n");   // Output: 1

        int[][] heights3 = {
            {1, 2, 1, 1, 1},
            {1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1},
            {1, 1, 1, 2, 1}
        };
        System.out.println("Result 3 -> " + solution.minimumEffortPath(heights3) + "\n");   // Output: 0
    
    }

}

/*
 * Intuitions :
 
    1. we are a hiker and preparing for upcomming hike
    2. we have given heights (row, col)
    3. we are standing at [0, 0] and our target is to go to [row - 1, col - 1]
    4. we can move BRUL
    5. we need to find route that requires minimum effort
    6. A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
        what is this ?
    7. return minEffortRequired
 
 
 * Pattern :
 
    1. We need a minHeap to store {row, col, effortsSoFar}
        - effortsSoFar : maximum height difference so far along the path to reach this cell
        - minHeap will help us to explore list Effort path
        - I'm a bit confused that shoul I use minHeap or maxHeap..
        - I think minHeap will be better here as we want minimum efforts
    2. we need a directionMatrix array
    3. will initially add {0, 0, heights[0][0]} in minHeap
    4. like maxProbability que here also we need a array which tells bestEfforts
        - which tracks the minimum effort needed to reach each cell 
        - initially add 1 at [0, 0]
    5. start while loop till minHeap get's empty
        pop the cell with the least effortSoFar from minHeap
        currRow = pop[0]
        currCol = pop[1]
        currEffort = pop[2]

        - if row and col hit to an end then 
            return the currEffort

        - check BRUL directions
            - get abs diff between currCell and neighbor
                diff = currCellHeight - neighborCellHeight
                
            - get maximum effort one
                neighborEffort = max(currEffort, diff)

            add neighbor row and col with neighborEffort in minHeap

    6. return 0
 
 

 */