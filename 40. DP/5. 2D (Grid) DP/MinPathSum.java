import java.util.*;

public class MinPathSum {
    
    public int minPathSum(int[][] grid) {
        
        // Declare Variables
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];


        // Initially set dp for cell[0,0] as grid[0][0] why? check comments
        dp[0][0] = grid[0][0];


        // Check first Row i = 0 and j = varies
        System.out.println("Filling first row:");
        for (int j = 1; j < n; j++) {
            
            // Debugger :
            System.out.println(" currcol : " + j);
            System.out.println("  - dp[0][" + (j - 1) + "]         : " + dp[0][j - 1]);
            System.out.println("  - grid[0][" + j + "]       : " + grid[0][j]);

            dp[0][j] = dp[0][j-1] + grid[0][j];
           
            System.out.println("  - dp[0][" + j + "]         : " + dp[0][j]);
        }
        System.out.println("  [1st Row] DP Array : " + Arrays.deepToString(dp));
        System.out.println("-----------------------------\n");
        

        // Check first Col i = varies and j = 0
        System.out.println("Filling first col:");
        for (int i = 1; i < m; i++) {
            
            // Debugger :
            System.out.println(" currrow : " + i);
            System.out.println("  - dp[" + (i - 1) + "][0]         : " + dp[i-1][0]);
            System.out.println("  - grid[" + i + "][0]       : " + grid[i][0]);

            dp[i][0] = dp[i-1][0] + grid[i][0];
           
            System.out.println("  - dp[" + i + "][0]         : " + dp[i][0]);
        }
        System.out.println("  [1st Col] DP Array : " + Arrays.deepToString(dp));
        System.out.println("-----------------------------\n");
        

        // Check other remaining cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                
                // System.out.println(" checking grid[" + i + "," + j + "] : " + grid[i][j]);

                int sumFromTop = dp[i-1][j] + grid[i][j];
                int sumFromLeft = dp[i][j-1] + grid[i][j];

                dp[i][j] = Math.min(sumFromTop, sumFromLeft);

                // System.out.println("    We are taking min for cell [" + i + "," + j + "] between " + sumFromTop + ", " + sumFromLeft + " : " + dp[i][j]);
            }

            System.out.println("  [Remain Cells] DP Array : " + Arrays.deepToString(dp));
        }
        

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {

        MinPathSum solution = new MinPathSum();

        int[][] grid1 = {
            {1,3,1},
            {1,5,1},
            {4,2,1}
        };
        System.out.println("Result 1 -> " + solution.minPathSum(grid1) + "\n");    // 7

        int[][] grid2 = {
            {1,2,3},
            {4,5,6}
        };
        System.out.println("Result 2 -> " + solution.minPathSum(grid2) + "\n");    // 12
        
        // int[][] grid3 = {
        //     {1}
        // };
        // System.out.println("Result 3 -> " + solution.minPathSum(grid3) + "\n");    // 0

    }

}

/*
 * Intuitions :
 
    1. We have given a grid (mxn) filled with positive numbers
    2. We need to find path from top-left(0,0) to bottom-right(m-1, n-1)
    3. but there is a catch.. aplyala tya path madhlya saglya nums chi sum min haviye
    4. we can only move down or right

 * Pattern :

    1. Declare variables
        - m, n
        - dp    -> to store min sum we get from a path (top or left)
        
    2. apan nehmi (0,0) pasun start karnar ahe so let's set it's dp first
        - (0,0) parynt pohochaycha ekach way and till the sum grid[0][0] ch asel 
        - dp[0][0] = grid[0][0]

    3. let's check example an see what's happening

                      Grid                                        dp                                                                                            
                                                                               
                |                                   |        1+3      4+1                                                                                                                                  
              0 |   1   3   1                     0 |     1 -----> 4 -----> 5                                                                                                                               
              1 |   1   5   1                       |     |        |        |                                                                                                                         
              2 |   4   2   1                       | 1+1 |    4+5 |    5+1 |                                                                                                                                          
            ----|---------------                    |     |        |        |                                                                                                                                                  
                |   0   1   2                       |     v  2+5   v  7+1   v                                                                                                                                          
                                                  1 |     2 -----> 7 -----> 6                                           
                                                    |     |        |        |                                           
                                                    | 2+4 |    7+2 |    6+1 |                                        
                                                    |     |        |        |                                    
                                                    |     v  6+2   v  8+1   v                                    
                                                  2 |     6 -----> 8 -----> 7                                           
                                                    |                                                                   
                                                ----|-------------------------------                                    
                                                    |     0        1        2        
                                              
    
    -> First Row : i = 0
        - to reach [0,1] there is only one way from left[0,0]  
            so update [0,1] cell as dp[0][0] + grid[0][1] = 1 + 3 = 4

        - to reach [0,2] there is only one way from left[0,1] 
            so update [0,2] cell as dp[0][1] + grid[0][2] = 4 + 1 = 5

    -> Second Row : i = 1
        - to reach [1,0] there is only one way from top[0,0]     
            so update [1,0] cell as dp[0][0] + grid[1][0] = 1 + 1 = 2
            
        - to reach [1,1] there is Two ways from top[0,1] from left[1,0]
            so we need to take min between both paths
            dp[0][1] + grid[1][1] = 4 + 5 = 9
            dp[1][0] + grid[1][1] = 2 + 5 = 7
            so update [1,1] cell as 7
        
        - to reach [1,2] there is Two ways from top[0,2] from left[1,1]
            so we need to take min between both paths
            dp[0][2] + grid[1][2] = 5 + 1 = 6
            dp[1][1] + grid[1][2] = 7 + 1 = 8
            so update [1,2] cell as 6
    
    -> Third Row : i = 2
        - to reach [2,0] there is only one way from top[1,0]          
            so update [2,0] cell as dp[1][0] + grid[2][0] = 2 + 4 = 6

        - to reach [2,1] there is Two ways from top[1,1] from left[2,0]
            so we need to take min between both paths
            dp[1][1] + grid[2][1] = 7 + 2 = 9
            dp[2][0] + grid[2][1] = 6 + 2 = 8
            so update [2,1] cell as 8

        - to reach [2,2] there is Two ways from top[1,2] from left[2,1]
            so we need to take min between both paths
            dp[1][2] + grid[2][2] = 6 + 1 = 7
            dp[2][1] + grid[2][2] = 8 + 1 = 9
            so update [2,2] cell as 7  

    -> so at the end will return the um whch is 7


 * Pseudo Code :

    function minPathSum (grid) {
    
        -> Declare variables
            m, n
            dp

        -> Initially set dp[0][0] = grid[0][0]
            karan (0,0) is a starting point so tyachi sum grid[0][0] itkich asel


        -> Check first row : i = 0 and j = varies
            me directly nested for loop lavaycha vichar karat hote but I have learned ki i-1 or j-1 la issue yeto first row or col madhe
            apan j = 1 kartoy karan jevha j-1 karu tevha index bound out cha error nahi yenar

            for(j = 1 to n)
                - set dp's values by adding prev dp sum in currCell
                    also first row madhe top (i-1) kadun kahich yet nahi just left(j-1) kadun yet

                dp[0][j] = dp[0][j-1] + grid[0][j]


        -> Check first col : i = varies and j = 0
            me directly nested for loop lavaycha vichar karat hote but I have learned ki i-1 or j-1 la issue yeto first row or col madhe
            apan i = 1 kartoy karan jevha i-1 karu tevha index bound out cha error nahi yenar

            for(i = 1 to m)
                - set dp's values by adding prev dp sum in currCell
                    also first col madhe left (j-1) kadun kahich yet nahi just top(i-1) kadun yet

                dp[i][0] = dp[i-1][0] + grid[i][0]

        
        -> let's check remaining cells
            for(i = 1 to m)
                for(j = 1 to n)

                    sumFromTop = dp[i-1][j] + grid[i][j]
                    sumFromLeft = dp[i][j-1] + grid[i][j]

                    dp[i][j] = min(sumFromTop, sumFromLeft)

                
        -> At end return dp[m-1][n-1]
    
    }

 */