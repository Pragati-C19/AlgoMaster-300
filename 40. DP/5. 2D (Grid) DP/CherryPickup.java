import java.util.*;

public class CherryPickup {
    
    // Gloablly Declare Variables 
    int cherryCount;
    int[][] matrixDirection = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    // Driver Function
    public int cherryPickup(int[][] grid) {
     
        // Declare variables
        int n = grid.length;
        int[][] dp = new int[n][n];
        cherryCount = 0;
        

        // If there is an thorn at first cell [0,0] return 0
        if (grid[0][0] == -1) {
            
            System.out.println(" Thorn at first cell...");
            return 0;
        }


        // Both persons starting from cell[0,0]
        dfs(0, 0, 0, 0, grid, n);

        // // else set dp of first cell same as grid 
        // dp[0][0] = grid[0][0];
        // System.out.println("Initial DP Array : " + Arrays.deepToString(dp));


        // // // Check first Row i = 0 and j = varies
        // System.out.println("Filling first row:");
        // for (int j = 1; j < n; j++) {
            
        //     // if cell has -1 means it has obstacle we want to avoid it
        //     if (grid[0][j] == -1) {
                    
        //         System.out.println("    will skip grid[i][j] if they are -1 bcoz it's thorn...");
        //         continue;
        //     }
            
        //     // we don't have any top so will add dp value of left + curr grid in curr cells dp
        //     dp[0][j] = dp[0][j-1] + grid[0][j];

        // }
        // System.out.println("  [1st Row] DP Array : " + Arrays.deepToString(dp));
        

        // // Check first Col i = varies and j = 0
        // System.out.println("Filling first col:");
        // for (int i = 1; i < n; i++) {
    
        //     // if cell has -1 means it has obstacle we want to avoid it
        //     if (grid[i][0] == -1) {
                    
        //         System.out.println("    will skip grid[i][j] if they are -1 bcoz it's thorn...");
        //         continue;
        //     }

        //     // we don't have any top so will add dp value of left + curr grid in curr cells dp
        //     dp[i][0] = dp[i-1][0] + grid[i][0];

        // }
        // System.out.println("  [1st Col] DP Array : " + Arrays.deepToString(dp));


        // // check other cells
        // for (int i = 1; i < n; i++) {
        //     for (int j = 1; j < n; j++) {
             
        //         // if cell has -1 means it has obstacle we want to avoid it
        //         if (grid[i][j] == -1) {
                    
        //             System.out.println("    will skip grid[i][j] if they are -1 bcoz it's thorn...");
        //             break;
        //         }

        //         // Top left ch karu apan add karat karat 
        //         dp[i][j] = dp[i-1][j] + dp[i][j-1] + grid[i][j];

        //     }

        //     System.out.println(" - Updated DP Array : " + Arrays.deepToString(dp));
        // }



        return cherryCount;
    }

    // Recursion Function : to get cherry Count at each cell
    private int dfs (int row1, int col1, int row2, int col2, int[][] grid, int n) {

        // Base Case :
        if (row1 < 0 || row1 >= n || col1 < 0 || col1 >= n || row2 < 0 || row2 >= n || col2 < 0 || col2 >= n || grid[row1][col1] == -1 || grid[row2][col2] == -1) {

            // Why Integer.MinValue ? check in comments
            return Integer.MIN_VALUE;
        }

        // If we reach to the end return cell count
        if (row1 == n - 1 && col1 == n - 1) {
            
            System.out.println("    We have reach to the end of cell so return : " + grid[row1][col1]);
            return grid[row1][col1];
        }


        // Declare a currCherry Count : to track cherris of curr cells of both person
        int currCherryCount = 0;

        // If both persons are on same cell will count cherry 1 time only
        if (row1 == row2 && col1 == col2) {
            
            currCherryCount += grid[row1][col1];
            System.out.println("    - both person on same cell [" + row1 + "," + col1 + "] so cherryCount : " + cherryCount);
        }
        else {

            // If both persons are on diff cells we need to add both cherries
            currCherryCount += grid[row1][col1] + grid[row2][col2];
            System.out.println("    - both person on diff cells P1: [" + row1 + "," + col1 + "] and P2: [" + row2 + "," + col2 + "] so cherryCount : " + cherryCount);
        }


        // now check all 4 positions : see I said positions not directions 
        // here both person goes to right or bottom we need to check will do this with recursion

        int bothToRight = dfs(row1, col1 + 1, row2, col2 + 1, grid, n);
        int bothToBottom = dfs(row1 + 1, col1, row2 + 1, col2, grid, n);
        int firstRightSecondBottom = dfs(row1, col1 + 1, row2 + 1, col2, grid, n);
        int secondRightFirstBottom = dfs(row1 + 1, col1, row2, col2 + 1, grid, n);

        System.out.println("    - bothToRight : " + bothToRight + " | bothToBottom : " + bothToBottom + " | firstRightSecondBottom : " + firstRightSecondBottom + " | secondRightFirstBottom : " + secondRightFirstBottom);

        // let's take maximum of bothToBottom and bothToRight
        int max1 = Math.max(bothToRight, bothToBottom);

        // let's take maximum of firstRightSecondBottom and secondRightFirstBottom
        int max2 = Math.max(firstRightSecondBottom, secondRightFirstBottom);

        // Now add max between above two max in cherry Count
        currCherryCount += Math.max(max1, max2);
        System.out.println("  CurrCherry Count is : " + currCherryCount);

        return currCherryCount;
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

    ^ Let's think fresh :

    - Took help from below video 
        https://www.youtube.com/watch?v=ZV0sUzfA7Eg&ab_channel=Pepcoding
        
    - First Approach : DFS 
        1. We need two person.. who can go through two different routes 
        2. doghanche positions kashe astil ata?.. 
            - aplyala right and bottom jaychy tr 
            - both right, both bottom, 1st right 2nd bottom, 1st bottom 2nd right
            - ya type ni te firtil
        3. ata apan donhi persons kontya possitions vr ahet te baghu 
            - jr te dogh ekach cell vr asel means row1 = row2 and col1 = col2 asel
                so apan ekdach cherry pick karu tithe cherry asel tr 
                cherryCount += grid[r1][c1]
            - jr dogh vegveglya cells vr asel and tyanchya cell vr cherry astil tr
                doghanchi addition add karun de cherryCount madhe
                cherryCount += grid[r1][c1] + grid[r2][c2]
        4. ata he zal fact ekach [r1,c1] and [r2,c2] ch what abt other ?
            - tyamul apan ithe dfs or recursion lavnar ahe
            - will check all 4 positions we mention earlier in recursion 
            - and last la apan tya 4 positions madhun max kadhun gheu
            - jo max apan cherryCount madhe add karun tyala return karu
        5. ata dfs lihitoy tr base case baddal lihav lagel rigth?
            - so Base case same asel me adhi pn lihileli ahe ti
                if (r1 < 0 || r1 >= m || c1 < 0 || c1 >= n || r2 < 0 || r2 >= m || c2 < 0 || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1)
            - I know it's long but it's nothing 
                we have check if both persons are not out of bound ? and if they have -1 as in thorn?
                if yes then will return 0
        6. when we reach to end cell will need to return the value 
            if (r1 == m - 1 && c1 == n - 1)
                return grid[r1][c1]
        
        7. Why Return Integer.MIN_VALUE Matters
            - Let's break this part clearly:
                cherries += Math.max(Math.max(f1, f2), Math.max(f3, f4));

                Suppose 3 of your paths are invalid, and return 0 (instead of negative value).
                And only 1 path is valid and returns 3 cherries.
                    max(0, 0, 0, 3) → 3 ✔️ Good.

                Now suppose all 4 paths are invalid, and return 0:
                    max(0, 0, 0, 0) → 0 ❌
                Looks like a valid path with no cherries.
                So it thinks it's okay to go forward, 
                but it isn't — this path should be skipped altogether.
                
            - That’s why Integer.MIN_VALUE is used. So that:
                max(-∞, -∞, -∞, -∞) → -∞ → rejected

 
 * Pseudo Code :
 
    public int cherryPickup(int[][] grid) {
     
        -> Declare variables
        int n = grid.length;
        int[][] dp = new int[n][n];
        int cherryCount = 0;
        int[][] matrixDirection = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
        };


        -> If there is an thorn at first cell [0,0] return 0
        if (grid[0][0] == -1) {
            
            System.out.println(" Thorn at first cell...");
            return 0;
        }

        -> else set dp of first cell same as grid 
        dp[0][0] = grid[0][0];
        System.out.println("Initial DP Array : " + Arrays.deepToString(dp));


        -> Check first Row i = 0 and j = varies
        System.out.println("Filling first row:");
        for (int j = 1; j < n; j++) {
            
            -> if cell has -1 means it has obstacle we want to avoid it
            if (grid[0][j] == -1) {
                    
                System.out.println("    will skip grid[i][j] if they are -1 bcoz it's thorn...");
                continue;
            }
            
            -> we don't have any top so will add dp value of left + curr grid in curr cells dp
            dp[0][j] = dp[0][j-1] + grid[0][j];

        }
        System.out.println("  [1st Row] DP Array : " + Arrays.deepToString(dp));
        

        -> Check first Col i = varies and j = 0
        System.out.println("Filling first col:");
        for (int i = 1; i < n; i++) {
    
            -> if cell has -1 means it has obstacle we want to avoid it
            if (grid[i][0] == -1) {
                    
                System.out.println("    will skip grid[i][j] if they are -1 bcoz it's thorn...");
                continue;
            }

            -> we don't have any top so will add dp value of left + curr grid in curr cells dp
            dp[i][0] = dp[i-1][0] + grid[i][0];

        }
        System.out.println("  [1st Col] DP Array : " + Arrays.deepToString(dp));


        -> check other cells
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
             
                -> if cell has -1 means it has obstacle we want to avoid it
                if (grid[i][j] == -1) {
                    
                    System.out.println("    will skip grid[i][j] if they are -1 bcoz it's thorn...");
                    break;
                }

                -> Top left ch karu apan add karat karat 
                dp[i][j] = dp[i-1][j] + dp[i][j-1] + grid[i][j];

            }

            System.out.println(" - Updated DP Array : " + Arrays.deepToString(dp));
        }

        return 0;
    }


 */