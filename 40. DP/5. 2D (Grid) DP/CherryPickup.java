import java.util.*;

public class CherryPickup {
    
    public int cherryPickup(int[][] grid) {
     
        // Declare variables
        int n = grid.length;
        int[][] dp = new int[n][n];
        int cherryCount = 0;
        int[][] matrixDirection = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
        };


        // If there is an thorn at first cell [0,0] return 0
        if (grid[0][0] == -1) {
            
            System.out.println(" Thorn at first cell...");
            return 0;
        }

        // else set dp of first cell same as grid 
        dp[0][0] = grid[0][0];
        System.out.println(" Initial DP Array : " + Arrays.deepToString(dp));

        

        return 0;
    }

    public static void main(String[] args) {

        CherryPickup solution = new CherryPickup();

        int[][] grid1 = {
            {0,1,-1},
            {1,0,-1},
            {1,1,1}
        };
        System.out.println("Result 1 -> " + solution.cherryPickup(grid1) + "\n");    // 5

        int[][] grid2 = {
            {1,1,-1},
            {1,-1,1},
            {-1,1,1}
        };
        System.out.println("Result 2 -> " + solution.cherryPickup(grid2) + "\n");    // 0
        
        // int[][] grid3 = {
        //     {1}
        // };
        // System.out.println("Result 3 -> " + solution.cherryPickup(grid3) + "\n");    // 0

    }

}


/*
 * Intuitions :
 
    1. we have given n x n grid representing the feild of cherries
        0 -> cell is empty, we can pass through
        1 -> contains cherry, pick it and pass through
       -1 -> cell contains thorn, which blocks ur way it is an obstacle

    2. return the maximum number of cherries we can get
    3. Conditions :
        - starting position is [0,0]
        - we need to reach to [n-1, n-1]
        - u can move right or down
        - once we reach [n-1, n-1] then again we need to start from [0,0]
        - Now move left or up
        - when u pick any cherry that cell will be empty
        
 
 
 * Pattern :
 
    1. Declare variables
        n 
        dp  -> to store count of how much cherry we can pick till the cell

    2. it says we have to move from top to bottom and we can move BRUL
        yeah u heard right I think que is being tricky... 
        it want me to get confused over kaskay start la right or down karu and then again start karun left or up karu
        
        but I think we should check BRUL at once.. let me Trace example and check it

    3. dp[0][0] will be?
        - if it will be -1 will return 0 
            bcoz start point vrch obstacle ahe pudhe jatach nahi yenar
        - if it will be 1 will set dp[0][0] = 1
        - if it will be 0 will set dp[0][0] = 0
        - means else of first base case we can say dp[0][0] = grid[0][0]

    4. will check first row and col
        - here I have problem so let's think abt it in a bit

    5. As per que trace I think we need to add all 4 sides dp
        dp[i][j] = dp[i-1][j] + dp[i][j+1] + dp[i+1][j] + dp[i][j-1]
                       top    +    right   +    down    +    left

    6. will start for loop if grid[i][j] != -1 asel 
        will start a for loop where will check all directions
        for(dir : direction) 
            x = dir[0] + i
            y = dir[1] + j

            if(Base Case : x >= 0 && y >= 0 && x < n && y < n)
 
                dp[i][j] = dp[i-1][j] + dp[i][j+1] + dp[i+1][j] + dp[i][j-1]

 
 * Pseudo Code :
 



 */