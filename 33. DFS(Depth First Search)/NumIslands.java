import java.util.*;

public class NumIslands {
    
    public int numIslands(char[][] grid) {
        
        // get row and col count
        int m = grid.length;
        int n = grid[0].length;

        // Variable to track distict islands 
        int islandsCount = 0;

        // To track if block is visited or not 
        boolean[][] visitedBlock = new boolean[m][n];

        // Traverse matrix col by col
        for (int i = 0; i < m; i++) {
            
            for (int j = 0; j < n; j++) {
                
                System.out.println("    Visiting block is (" + i + ", " + j + ") : " + grid[i][j] + " with IslandsCount : " + islandsCount);
                
                boolean exploredIsland = dfs(i, j, grid, visitedBlock, m, n);

                if (exploredIsland) {
                    
                    islandsCount++;
                    System.out.println("Island Found.. " + islandsCount + "\n");
                }
               
            }
        }

        return islandsCount;
    }

    // Helper Function : To check if we have any unvisited 1's around?
    private boolean dfs(int i, int j, char[][] grid, boolean[][] visitedBlock, int m, int n) {

        // Base Case :
        if (i < 0 || i >= m || j < 0 || j >= n || visitedBlock[i][j] || grid[i][j] == '0') {
            System.out.println("        It's an end of row or col or block already visited or block has char '0'...");
            return false;    
        }

        System.out.println("       -> Visiting block is (" + i + ", " + j + ") : " + grid[i][j]);
        
        // Now we already checked if '0' exist then return false then there are only '1' left
        // so mark this block as visited
        visitedBlock[i][j] = true;

        // now check for it's down, right, up, left
        dfs(i+1, j, grid, visitedBlock, m, n);
        dfs(i, j+1, grid, visitedBlock, m, n);
        dfs(i-1, j, grid, visitedBlock, m, n);
        dfs(i, j-1, grid, visitedBlock, m, n);
        
        // will not backtrack that visitBlock to false.. bcoz we are not checking same block again

        return true;
    }

    public static void main(String[] args){

        NumIslands solution = new NumIslands();

        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println("Result1 -> " + solution.numIslands(grid1) + "\n");     // 1

        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("Result2 -> " + solution.numIslands(grid2) + "\n");      // 3
        
    }

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. We have given a 2D matrix
 * 2. where 1's represent as land and 0's represent as water
 * 3. we need to return number of islands
 * 4. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * 5. You may assume all four edges of the grid are all surrounded by water.
 * 
 * 
 * What is island in this ques
 *              
 *                  1 Island                                    3 Islands
 *             __________________                         ________               
 *            | 1,  1,   1,   1, | 0                     | 1,  1, |  0,   0,  0                        
 *            |         ____     |                       |        |                               
 *            | 1,  1, | 0, | 1, | 0                     | 1,  1, |  0,   0,  0                          
 *            |             |____|                       |________|_____                                           
 *            | 1,  1, | 0,   0,   0                       0,  0, |  1, | 0,  0                              
 *            |________|                                          |_____|_________                     
 *              0,  0,   0,   0,   0                       0,  0,    0, |  1,  1  |                 
 *                                                                      |_________|
 * 
 * 
 * 
 * Pattern :
 * 
 * 1. me left right up down check karel..
 * 2. jr mala '1' bhetal tr me pudhe continue karel
 * 3. jr mala nahi bhetla tr me return back karel
 * 4. numsIsLands(grid)
 *      - Declare islandsCount to 0
 *      - Travers col by col
 *          for(i = 0 to m)
 *              for(j = 0 to n)
 *      - call dfs recursion function to check if there exist any 1 in it?
 *      - if yes then increase the island count
 *      - if not continue the loop
 * 
 * 5. dfs(i, j, grid, visitedBlock, m, n)
 *      - Base Case : jr visited astil or row cha end or col cha end asel tr apan te consider nahi karnar ahe so return false
 *          if (i < 0 || i >= m || j < 0 || j >= n || visitedBlock[i][j])
 *      - Ajun ek jr aplyala '0' bhetla tr we don't want to check that or visit that block too so tithe pn return false
 *      - then jr aplyala '1' bhettoy tr apan tyala visited mhnu 
 *          visited ka mhntoy?.. karan tyani aplyala track asel ki ohk ekach 1 chya starting pasun kiti visited lands bhetlet.. 
 *          and combining them will become a island
 *      - then check karaych left right up down from that block
 * 
 *  
 * Pseudo Code :
 * 
 * 
 * function numIslands(grid){
 *      
 *      islandsCount = 0;
 *      
 *      -> Traverse by col by col
 *      for(i = 0 to m)
 *          for(j = 0 to n)
 *              
 *              exploredIsland = dfs(i, j, grid, visitedBlock, m, n)
 *              
 *              -> jr dfs function returns true then that means we have explore adjucent 1's
 *              if(exploredIsland)
 *                  islandsCount++
 *  
 *      return islandsCount
 * }
 * 
 * dfs(i, j, grid, visitedBlock, m, n){
 * 
 *      -> BASE CASE : It's an end of row or col or block already visited we don't want to consider it
 *      if (i < 0 || i >= m || j < 0 || j >= n || visitedBlock[i][j] || grid[i][j] == '0')
 *            return false;    
 * 
 *      -> set this block as visited 
 *          visitedBlock[i][j] = true
 * 
 *      -> if block contains char '1' then will check for connections 
 *      if(grid[i][j] == '1') 
 *          
 *          -> will check left right up down sides  
 *          dfs(i+1, j)
 *          dfs(i-1, j)
 *          dfs(i, j+1)
 *          dfs(i, j-1)
 * 
 *      return true;
 * }
 * 
 * 
 */