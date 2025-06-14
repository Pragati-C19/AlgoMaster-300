import java.util.*;

public class LongestIncreasingPath {
    
    // Gloabally Declare variables 
    int[][] dp;
    int[][] matrixDirection = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };

    // Driver Function
    public int longestIncreasingPath(int[][] matrix) {
        
        // Declare variables
        int m = matrix.length;
        int n = matrix[0].length;
        int maxPath = 0;
        dp = new int[m][n];


        // Start Checking all cells one by one
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                // call recursion and see prev values or curr one is max
                maxPath = Math.max(maxPath, dfs(i, j, matrix, m, n));
            }
        }
        System.out.println(" Finally DP : " + Arrays.deepToString(dp));
        System.out.println(" Finally MaxPath : " + maxPath);
        

        // Return maxPath
        return maxPath;
    }

    // Recursion Function : to check maxPath till currCell
    private int dfs(int i, int j, int[][] matrix, int m, int n) {

        // If we already have value store in dp for currCell return it
        if (dp[i][j] != 0) {
            
            System.out.println("    Value is already store in dp : " + dp[i][j]);
            return dp[i][j];
        }

        // Declare a variable to track currPaths
        int currCellPath = 1;   // including currCell

        // Now check this curr Cell's neighbors BRUL
        for (int[] dirs : matrixDirection) {
            
            int x = dirs[0] + i;
            int y = dirs[1] + j;

            // add conditions 
            if (x >= 0 && y >= 0 && x < m && y < n && matrix[i][j] > matrix[x][y]) {
                
                // Call recursion on neighbor
                int pathLenFromNeighbor = 1 + dfs(x, y, matrix, m, n);
                System.out.println("         - Path Count for [" + x + "," + y + "] : " + pathLenFromNeighbor);

                currCellPath = Math.max(currCellPath, pathLenFromNeighbor);
                System.out.println("         - currMaxPath Count for [" + i + "," + j + "] : " + currCellPath);

            }
        }

        // Store currCellPath in dp 
        dp[i][j] = currCellPath;
        System.out.println("    Updated DP : " + Arrays.deepToString(dp));

        return currCellPath;
    }


    public static void main(String[] args) {

        LongestIncreasingPath solution = new LongestIncreasingPath();

        int[][] matrix1 = {
            {9,9,4},
            {6,6,8},
            {2,1,1}
        };
        System.out.println("Result 1 -> " + solution.longestIncreasingPath(matrix1) + "\n");    // 4

        int[][] matrix2 = {
            {3,4,5},
            {3,2,6},
            {2,2,1}
        };
        System.out.println("Result 2 -> " + solution.longestIncreasingPath(matrix2) + "\n");    // 4
        
        int[][] matrix3 = {
            {1}
        };
        System.out.println("Result 3 -> " + solution.longestIncreasingPath(matrix3) + "\n");    // 1

    }

}

/*
 * Intuitions :
 
    1. We have given m x n matrix
    2. we need to return length of longest increasing path
    3. from each cell we can move in 4 directions : BRUL 
    
 
 * Pattern :
 
    1. Longest increasing path means
        1 -> 2 -> 3 -> 4 -> 5
        not 1 -> 1 -> 2 -> 3 -> 3 -> 4 -> 5
        there should not be same nums in one path
        if we get same num we have to start again
        we must only move to stricly increasing order (next > curr)

    2. from every cell we need to check 
        if neighbors have curr+1 value?
            if yes then go to that cell and explore that path
        
    3. After tracking example I think we need to use DFS here
        - start from every cell in matrix 
        - for every cell will che it's BRUL 
        - if neighbor has smallest value recur on it..
        - will store count of each cell in dp

    4. we don't want to consider equal or larger values
    

    ^ Trace Example :

              matrix
            9   9   4
            6   6   8
            2   1   1

        - cell [0,0] = 9
            check BRUL of it to see any maxNum is there?
            
            there is no left and up
            right -> [0,1] = 9 -> nope it's equal
            bottom -> [1,0] = 6 -> it's small
                
                - let's explore cell [1,0] = 6
                    check BRUL of it to see any maxNum is there?

                    there is no left 
                    up -> [0,0] = 9 -> it's large so -> dp[1,0] + 1 = 1
                    right -> [1,1] = 6 -> nope it's equal
                    bottom -> [2,0] = 2 -> it's small

                        - let's explore cell [2,0] = 2
                            check BRUL of it to see any maxNum is there?

                            there is no left and bottom 
                            up -> [1,0] = 6 -> it's large so -> dp[2,0] + 1 = 1
                            right -> [2,1] = 1 -> it's small

                                - let's explore cell [2,1] = 1
                                    check BRUL of it to see any maxNum is there?

                                    there is no bottom 
                                    up -> [1,1] = 6 -> it's large so -> dp[2,1] + 1 = 1
                                    right -> [2,2] = 1 -> nope it's equal
                                    left -> [2,0] = 2 -> it's large so -> dp[2,1] + 1 = 2

                                    return 2
                            
                            return 2 + 1

                    return 3 + 1

            return 4
                    

 
 
 * Pseudo Code :
 
    Globally Declare variable 
        - matrixDirection : to check all BRUL directions
        - dp

    function longestIncreasingPath (matrix) {
    
        -> Declare variables
            - m, n
            - dp        -> To store maxPath Count till currCell
            - maxPath   -> it will tell max of currCellPath or prev one 

        -> start checking cell one by one
            for(i = 0 to m)
                for(j = 0 to n)

                    maxPath = max(maxPath, dfs(i, j, matrix))

        -> return maxPath
    }

    dfs (i, j, matrix) {
    
        -> If we already have value for this cell in dp return it
            if dp[i][j] != 0
                return dp[i][j]

        -> declare currPath = 1 
            why? bcoz we are including curr cell in it 
            it will give path of currCell

        -> check neighbors
            for(dirs : matrixDirection) 

                x = dirs[0] + i
                y = dirs[1] + j

                -> check if neighbor is in bound and smaller that curr value?
                    if( Base Case && matrix[i][j] > matrix[x][y]) 

                        -> call recursion and added curr cell in it 
                            pathLenFromNeighbor = 1 + dfs(x, y, matrix)

                        currPath = max(currPath, pathLenFromNeighbor)

        -> store value in dp 
            dp[i][j] = currPath

        -> return currPath

    
    }


 */