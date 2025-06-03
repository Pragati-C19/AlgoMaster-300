import java.util.*;

public class UniquePathsWithObstacles {
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
           
        return 0;
    }

    public static void main(String[] args) {

        UniquePathsWithObstacles solution = new UniquePathsWithObstacles();

        int[][] obstacleGrid1 = {
            {0,0,0},
            {0,1,0},
            {0,0,0}
        };
        System.out.println("Result 1 -> " + solution.uniquePathsWithObstacles(obstacleGrid1) + "\n");    // 2

        int[][] obstacleGrid2 = {
            {0,1},
            {0,0}
        };
        System.out.println("Result 2 -> " + solution.uniquePathsWithObstacles(obstacleGrid2) + "\n");    // 1
        
    }

}

/*
 * Intuitions :
 
    1. We have given an grid of m x n length
    2. Robot -> located at the grid[0][0]
    3. This robot wants to go to bottom-right corner grid[m-1][n-1]
    4. the robot can go only bottom or right 
    5. in the grid 
        0 -> empty space where we can move robot to
        1 -> there is an obstacle at the cell
    6. return the number of possible unique Paths that the robot can take to reach bottom-right corner
 
 
 * Pattern :
 
    ^ Trace Example 

                obstacleGrid 

                |
              0 |   0   0   0  
              1 |   0   1   0
              2 |   0   0   0
            ----|---------------
                |   0   1   2

        - Let's create a dp to store number of possible ways to reach to the botttom-right cell
            int[][] dp = new int[m][n]
            
        - dp[0][0] = 1
            bcoz there is only one way to reach at cell(0,0)

        - ata bagh apan cell madhe bottom or right jau shakto
            mhnjech apan ekdya cell vr pohochnyasathi top or left side ni yeu shakto

            tr chala apan tyavr focus karu ki cell vr pohochnyasathi kiti path ahet
            
                          dp 

                    |                                                                                       
                  0 |   1 -> 1 -> 1              - to reach [0,1] there is only one way from left[0,0]                                             
                    |   |         |              - to reach [1,0] there is only one way from top[0,0]                                                                   
                    |   v         v              - to reach [0,2] there is only one way from left[0,1]                                               
                  1 |   1    0    1              - to reach [2,0] there is only one way from top[1,0]                                               
                    |   |         |              - to reach [1,2] there is only one way from top[0,2]                                               
                    |   v         v              - to reach [2,1] there is only one way from left[2,0]                                               
                  2 |   1 -> 1 -> 2                                                             
                    |                            - to reach [2,2] there is two ways from top[1,2] left[2,1]                                  
                ----|--------------------                                                               
                    |   0    1    2                                                             

        - ata vichar kr ithe just pratek cell sathi 1 way ch hota jr multiple asel tr kaskay next cell la sangshil?
            like suppose aplyakde below value asti :
                to reach [1,2] there are 2 ways from top[0,2] and left[1,1]
                to reach [2,1] there is only one way from left[2,0]

            then [2,2] la yayla 
                2 ways from [1,2]
                1 way from [2,1]
                total 3 na
            
            so mala vatat will go to cell one by one
            mg me mhnte ki left : grid[i-1][j] and top : grid[i][j-1] chya values 0 ahet ka?
                asel tr dp[i-1][j] + dp[i][j-1]

            jr grid[i][j] ya donhi paiki ekachi value jr 1 ahe?
                asel tr dp[i][j] = 0
        
        - at the end will return dp[m-1][n-1]


 
 * Pseudo Code :
 
    function uniquePathsWithObstacles(grid) {
    
        -> Declare variables
            m, n 
            dp      -> it will store how many path's are there to reach currCell

        -> fill top-left cell [0,0] as 1 bcoz there are only 1 way to reach there
            dp[0][0] = 1

        -> Pseudo code lihitanna samjal ki first row or first col la ekach veli chek karan will be hard 

        -> now check other cells one by one
            for(i = 0 to m) 
                for(j = 0 to n)

                    -> ithe apan [0,0] cell hoil check parat so let's skip it
                        if(i = 0 && j = 0)
                            continue
                        
                    -> jr aplyala obstacle bhetla tr apan tya cell cha dp 0 karu
                        if(grid[i][j] == 1)
                            dp[i][j] = 0

                    -> jr cell vr space ahe tr apan check karu tya cell vr yayla kiti paths lagle
                        if(grid[i][j] == 0)
                            dp[i][j] = dp[i-1][j] + dp[i][j-1]


        -> at the end return 
            dp[m-1][n-1]
                            
    }


 */