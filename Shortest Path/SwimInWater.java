import java.util.*;

public class SwimInWater {
    
    public int swimInWater(int[][] grid) {
        
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