import java.util.*;

public class KnightProbability {
    
    // Global Variables
    double[][][] dp;
    boolean[][][] visitedCell;
    int[][] matrixDirection = {
        {-1, -2},
        {-2, -1},
        {-1, 2},
        {-2, 1},
        {1, -2},
        {2, -1},
        {1, 2},
        {2, 1}
    };  

    // Driver Function 
    public double knightProbability(int n, int k, int row, int column) {
        
        // Declare variables
        dp = new double[n][n][k + 1];   // matrix is 0-indexed so wrote n, n only
        visitedCell = new boolean[n][n][k + 1] ;
        double finalProbability = 0;
        
        // Call dfs Function
        finalProbability = dfs(row, column, k, n);

        // Return final result
        return finalProbability;
    }

    // Recursion Function : to get probability for knights moves
    private double dfs(int i, int j, int k, int n) {

        // Base Case : if cell is out of board return 0
        if (i < 0 || i >= n || j < 0 || j >= n) {
            
            System.out.println("  cell [" + i + "," + j + "] is out of board and " + k + " moves remain");
            return 0;
        }


        // if that cell is not out of board check if k == 0 or not
        if (k == 0) {
            
            System.out.println("    - reached cell [" + i + "," + j + "] and k == 0");
            return 1;
        }


        // Check if we have visited (i, j, k) ?
        if (visitedCell[i][j][k]) {
            
            System.out.println("    - cell [" + i + "," + j + "] is visited");
            return dp[i][j][k];
        }


        // Declare a variable curr probability
        double currProbability = 0;


        // Check all 8 directions 
        for (int[] dir : matrixDirection) {
            
            int x = i + dir[0];
            int y = j + dir[1];

            // Call recursion and get probability for directions
            // did k - 1 bcoz our moves are decreasing with every direction
            double neighborProbability = dfs(x, y, k - 1, n);

            // Each move has 1/8 chance
            double moveChances = neighborProbability / 8.0;

            // add that move chances to currProbability
            currProbability += moveChances;
        }

        // Add that currProbability in DP 
        dp[i][j][k] = currProbability;

        // mark this cell as visited
        visitedCell[i][j][k] = true;

        System.out.println("    - Probability for cell [" + i + "," + j + "] : " + currProbability);
        return currProbability;
    }


    public static void main(String[] args) {

        KnightProbability solution = new KnightProbability();

        System.out.println(" Result 1 -> " + solution.knightProbability(3, 2, 0, 0) + "\n");      // 0.06250
        System.out.println(" Result 2 -> " + solution.knightProbability(1, 0, 0, 0) + "\n" );     // 1.0
        
    }

}

/*
 * Intuitions :
 
    1. We have a chessboard of n x n, a knight starts at cell (row, col)
    2. Knight can make exactly k moves
    3. chess Knight has 8 posible moves 
        - he same chess madhe knight jasa chalto tasa ahe

                |               
              0 |   0   J   0   J   0      
              1 |   J   0   0   0   J      
              2 |   0   0   K   0   0               J - Knight can Jump on that cell
              3 |   J   0   0   0   J               K - Knight is on this cell
              4 |   0   J   0   J   0     
            ----|------------------------- 
                |   0   1   2   3   4
                |
                
        - if Knight is at cell (2, 2), we can go to
            [1,0], [0,1], [0,3], [1,4]
            [3,0], [4,1], [4,3], [3,4]
            
        - So jr me i, j chya values madhe ghetal tr 
            if Knight is at (i, j), we can go to below 8 directions
            
            [i-1, j-2]  [i-2, j-1]
            [i-1, j+2]  [i-2, j+1]
            [i+1, j-2]  [i+2, j-1]
            [i+1, j+2]  [i+2, j+1]

    4. Knight can choose one of eight possible moves even if it ges out of chessBoard
    5. Knight will keep moving until it has made exactly k moves 
        or it moves out of chessBoard
    6. return probability that knight remains on board after it has stopped moving

 
 * Pattern :
 
    1. Declare a directionMatrix Array with those 8 directions
    2. Conditions when we need to stop 
        - if knight goes out of board
            kevha jail to?.. when i and j are 
            i < 0 or i >= n or j < 0 or j >= n
            
            will return 0

        - jr above condition nahi hotye tr apan check karu k = 0 zalay ka te?
            karan zala asel tr means our knight is on board so far
            me asa ka mhntey ? bcoz apan adhich check kartoy ki knight ahe board vr ki nahi te

            k == 0  -> return 1

    3. then apan same cell for same moves nako parat parat check karayla 
        like me atta [1,1] check keli for 3 moves left when my knight is at [2,3]
        mg me kay parat Probability kadhat basu tyachyasathi?

        tyamul ithe DP use hoil jo probability store karel for (i, j, k)
        and if those are visited apan direct store keleli probability return karu

    4. Check all directions from directionMatrix for each cell
        and will add those probability in our currProbability 
        
    5. I think it's simple logic now.. will use dfs to check all directions


    ^ Improvement :

    1. currProbabilty that value is wrong 
        instead of 
            currProbability += dfs(x, y, k - 1, n);
        we need to change to    
            currProbability += dfs(x, y, k - 1, n) / 8.0;

    2. why? 
        - que stated that 
            Each time the knight is to move, it chooses ONE of EIGHT possible moves uniformly at random
        - We are returning count of path not probability 
        - each 8 directions has equal probability 1/8
        - so to get probability we need to multiply pathCount with 1/8 or divide pathCount by 8
        - otherwise will only get count of all valid paths  

 
 * Pseudo Code :
 
    function knightProbability (n, k, row, column) {
    
        -> Declare Variables
            dp[i][j][k]     - to store probabilty for[i,j] cell in k moves
            visited         - to check if (i, j, k) is visited or not
            directionMatrix - it stores all 8 directions we can move to

        -> Call DFS function 
            and as per the que will start from given row, column
            dfs(row, column, k, n) 
    
        -> return probability
    
    }

    function dfs (i, j, k, n) {
    
        -> Base Case : if cell is out of chess board means knight is out of board too
            if(i < 0 or i >= n or j < 0 or j >= n)
                return 0

        -> if cells are not out of chess board and k == 0 we can return probability as 1 
            bcoz we are successefully completed the event
            if(k == 0)
                return 1

        -> if we have already visited this cell with k moves?
            if(visited[i][j][k])
                return dp[i][j][k]

        -> Check all 8 directions
            for(dir : directionMatrix)

                x = i + dir
                y = j + dir

                currProbability += dfs(x, y, k-1, n)

        -> Add that currProbability in dp

        -> return currProbability
    
    }


 */