import java.util.*;

public class LargestIsland {
    
   // Globally declare Variables
   int[][] matrixDirection = {
      {1, 0},
      {0, 1},
      {-1, 0},
      {0, -1}
   };

   // Driver Function 
   public int largestIsland(int[][] grid) {
        
      // Declare Variables
      Map<Integer, Integer> idSizeMap = new HashMap<>();
      int n = grid.length;
      int uniqueId = 2;       // starting from 2 bcoz 0 and 1 is already been used as land and water
      int maxLargestIslandSize = 0;    // It tells us abt largestIsland after replacing 0 to 1
      
      // get sizes of all islands
      for (int i = 0; i < n; i++) {    
         for (int j = 0; j < n; j++) {
            
            if (grid[i][j] == 1) {
               int currIslandSize = dfs(i, j, uniqueId, grid, n);

               idSizeMap.put(uniqueId, currIslandSize);
               System.out.println(" Added island size in map with uniqueId with island size : " + uniqueId + " , " + currIslandSize + " : " + idSizeMap);
               
               uniqueId++;
            }
         }
      }


      // will replace one of the 0 with 1
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            
            if (grid[i][j] == 0) {
               
               // Declare a set to store uniqueId near the cell in immediate BRUL direction
               Set<Integer> neighboringUniqueIdSet = new HashSet<>();

               // Declare a variable to add neighboring islands with replaced 0 to 1
               // Assign initial value to 1 bcoz we are taking the cell whose 0 we are replacing
               int currLargestIslandAfterReplacingZero = 1;

               // let's take all unique id near the cell
               for (int[] dir : matrixDirection) {
                  
                  int x = i + dir[0];
                  int y = j + dir[1];

                  if (x > 0 || x < n - 1 || y > 0 || y < n - 1 || grid[x][y] > 1) {
                     
                     neighboringUniqueIdSet.add(grid[x][y]);
                     System.out.println("\n -> Set looks Like : " + neighboringUniqueIdSet);
                  }
               }
            }
         }
      }

      return maxLargestIslandSize;

   }

   // Recursion Function : To get all islands sizes
   private int dfs(int i, int j, int uniqueId, int[][] grid, int n) {

      System.out.println("Visiting (" + i + ", " + j + ") , " + uniqueId);
      
      // Base Case :
      if (i >= n || j >= n || i < 0 || j < 0 || grid[i][j] != 1) {
         return 0;
      }

      // replace values of 1 to uniqueId
      grid[i][j] = uniqueId;
      System.out.println(" -> grid (" + i + ", " + j + ") is changed to " + uniqueId);

      // current cell counts as 1
      int currIslandSize = 1;
      System.out.println(" -> curr island size is " + currIslandSize);

      // Check BRUL : let's wrote it with the help of direction array 
      for (int[] dir : matrixDirection) {
         
         int x = i + dir[0];
         int y = j + dir[1];

         currIslandSize += dfs(x, y, uniqueId, grid, n);
      }

      return currIslandSize;

   }


   public static void main(String[] args) {

      LargestIsland solution = new LargestIsland();

      int[][] grid1 = {
         {1, 0},
         {0, 1}
      };
      System.out.println("Result 1 -> " + solution.largestIsland(grid1) + "\n");

      int[][] grid2 = {
         {1, 1},
         {1, 0}
      };
      System.out.println("Result 2 -> " + solution.largestIsland(grid2) + "\n");

      int[][] grid3 = {
         {1, 1},
         {1, 1}
      };
      System.out.println("Result 3 -> " + solution.largestIsland(grid3) + "\n");

      // Ok problem is with my test case : n = grid.length = 4, But grid[0].length = 5 (i.e. 5 columns)
      // in que it says that n x n matrix I'm creating m x n matrix in this test case
      // int[][] grid4 = {
      //    {1, 0, 1, 1, 0},
      //    {1, 0, 0, 1, 0},
      //    {0, 1, 1, 0, 1},
      //    {1, 0, 1, 0, 1}
      // };
      // System.out.println("Result 4 -> " + solution.largestIsland(grid4) + "\n");

   }
    

}

/*
 * Intuitions :
 
    1. we have given n x n binary matrix 
    2. you are allowed to change at most one 0 to 1
    3. return size of largest island in grid after applying the operation
    4. an island is a 4-D connected group of 1s
 
 * Pattern :
 
    1. Mala jr mahit asel island size of all island in an matrix
    2. and ata suppose me 0 change kela tr tychya javal je 1 astil me tyanna vicharal suppose ki ohk tumchi size kay ahe?
    3. so tyanchi size + 0 chi size 1 = ji size asel to largestIsland ahe for that changed cell
    4. pn ata size store kashi karnar?
    
    
    Example :

                                          |           5 Islands
                                          |    ____      _________                      
                                       0  |   | 1, | 0, | 1,   1, | 0                                         
                                          |   |    |    |____     |                                      
                                       1  |   | 1, | 0,   0, | 1, | 0                                           
                                          |   |____|_________|____|___                                                          
                                       2  |     0, | 1,   1, | 0, | 1 |                                              
                                          |    ____|____     |    |   |                                         
                                       3  |   | 1, | 0, | 1, | 0, | 1 |                                   
                                          |   |____|    |____|    |___|                                      
                                          |
                                    ------|-------------------------------------
                                     cell |     0    1    2    3    4
        
      - There are 5 Islands with islandSize
            2, 1, 3, 3, 2

      - Id we store those size and cell in any map maybe it will be easy to get which land comes under which island
      - so suppose me [0, 1] vrcha 0 change kela to 1 
            - ata tyala connect aselel 1 left and right side la ahe
            - tr me mhnte ki ohk left tuzya island chi size kiti ahe? to mhnel 2 
            - me right la vicharal tuzya island chi size? to mhnel 3
            - tr me mhnel ohk jr me 1 zalo tr donhi island mix houn apla motha island banel jyachi size asel 3 + 1 + 2 = 6

      - Same me suppose [2, 3] vrcha 0 change kela to 1
            - ata tyachya ass pass kiti 1 ahet? left, right, up
            - me tya tighanna tyanchi size vicharli.. left = 3, right = 2, up = 3
            - tr me mhnle ohk me jr 1 zale tr apan sagle milun size kay hoil?.. 3 + 2 + 3 + 1 = 9
      
      - asach me sagle 0 change kelayvr size kay hote te pahil.. and tya saglyatla me max ghete.. 
            max(6, 9) = 9 

      
            0's Cell  |    His neighbor with val 1 && their size   |    Total island size after combined    |    MaxArea so far     
         -------------|--------------------------------------------|----------------------------------------|----------------------
            [0, 1]    |       right = 2, left = 3                  |      3 + 2 + 1 = 6                     |   max(0, 6)  ->  6
            [0, 4]    |       left = 3                             |          3 + 1 = 2                     |   max(6, 2)  ->  6
            [1, 1]    |       bottom = 3, left = 1                 |      3 + 1 + 1 = 5                     |   max(6, 5)  ->  6                 
            [1, 2]    |       bottom = 3, right = 3, up = 3        |      3 + 3 + 1 = 7  (R & U are same)   |   max(6, 7)  ->  7                                                   
            [1, 4]    |       bottom = 2, left = 3                 |      2 + 3 + 1 = 6                     |   max(7, 6)  ->  7                            
            [2, 0]    |       bottom = 1, right = 3, up = 2        |  1 + 3 + 2 + 1 = 7                     |   max(7, 7)  ->  7                                      
            [2, 3]    |       right = 2, up = 3, left = 3          |  2 + 3 + 3 + 1 = 9                     |   max(7, 9)  ->  9                        
            [3, 1]    |       right = 3, up = 3, left = 1          |      3 + 1 + 1 = 5  (R & U are same)   |   max(9, 5)  ->  9                  
            [3, 3]    |       right = 2, left = 3                  |      2 + 3 + 1 = 6                     |   max(9, 6)  ->  9                    


   Let's think about how to write it in code

      1. we need an Map maybe to store island size for specific cell
         - This is the inefficient solution bcoz map madhe paratek cell add karan is not easy
      2. so tyapekshya apan kay karu matrix madhe ek island bhetla ki tyatlya nums chi value change karun island size deu
         - like [0, 0] [1, 0] chya island chi size 2 ahe tr apan tya cells cha num(1) change karun 2 karun taku
         - Now the problem with this approach is aplyala 2 veles dfs lavava lagel.. ek island size ghayyala dusra ti size tya specifice 1 chya jagi replcae karayla

      So to get more optimal way
         1. apan ek uniqueId term use karu.. 
            - what does uniqueId do? -> to pratek island chya cells la ek uniqueId deil.. like 2, 3, 4, 5 or any number like -1, -2, -3 
               except 1 or 0 bcoz they are already used in matrix as land and water
         2. ata apan map lau jyat ata fact uniqueId as key and islandSize as value dyayvch
            - adhi map sathi nahi bolle bcoz apan cell and islandSize store karaych mhnt hoto jyachi map chi size khup vadhli asti
            - ata apan bas uniqueId store kartoy jyamadhe bunch off cells add kartoy
         3. apan ekhada 0 chi value 1 karnar asel tr as always apan check karu as pass except 0 kahi ahe ka?
            - asel tr apan tya map madhun tya uniqueId chi island size gheu and add karu
         4. suppose same uniqueId asel 2 size or 3 size la like vrchya problem madhe zal bagh [1, 2] [3, 1] la 
            - tyasathi kay karnar apan ? 
            - jithe mhnt hoto except zero kahi ahe ka tithech apan ajun ek condition lau ki uniqueId should not be repeated
            - add one uniqueId once
         
^ Improvements :

   1. removing any traces of dfsForGettingUniqueId :
         - if yes then check if it has any neighbors with unique id?
               dfsForGettingUniqueId(i, j, visitedCellForUniqueId, uniqueIdSet, grid, n)
         - You're performing a DFS from a zero to check neighbors and collect unique IDs.
         - But you only want to peek at the 4-directional neighbors (BRUL), not perform a full DFS.
         - Problem: You're going deep into neighbors instead of just checking adjacent cells.
         - We don't nee it boz aplyala recursively check nahi karaychy full matrix
         - aplyala bas tya zero chya as pass che BRUL havet bas

   2. currIslandSize in dfsForCheckingIslandSize 
         todo: for now let's try to code if I have any issue will change it it's not that big difference


 * Pseudo Code :
 

^  -> Globally Declare direction array 
      int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}}

   function largestIsland (grid) {
   
      -> Declare variables
         map<uniqueId, islandSize>
         uniqueId = 2            -> starting from 2 bcoz 0 and 1 is already been used as land and water
         maxLargestIslandSize = 0       -> it tells us what is maximun island size after replacing 0 to 1
         visitedCellForSize      -> it checks if cell is visited when we were trying to find island sizes
         visitedCellForUniqueId  -> it checks if cell is visited when we were trying to find 
         
      
      -> will get size of all islands and store it in map 
         also assign unique Id to that specific cells
         
         for(i = 0 to n)
            for(j = 0 to n)
               
               -> getting size of island from dfs 
                  currIslandSize = dfsForCheckingIslandSize(i, j, uniqueId, 0, visitedCellForSize, grid, n)

               -> adding that unique id and size in map
                  map.put(uniqueId, currIslandSize) 
               
               -> increase uniqueId bcoz we need to assign different id to next island

         
      -> Now we got the sizes of all lands and stored it in map
         now let's try changing zeros and check if they have any uniqueId surrounded?

         for(i = 0 to n)
            for(j = 0 to n)

               -> check if the cell has 0 
                  if(grid[i][j] == 0)

                     -> Declare Set here bcoz we want to get set of id's for cell near 0
                        UniqueId cha set ghetoy bcoz I don't want duplicate id's
                           uniqueIdSet<Integer>  
                           
                     -> Declare currLargestIslandAfterRepacing0

                     ^ Improved :
                        for(dir : dirs)
                           -> je apan i+1, j+1, i-1, j-1 karat hoto te ithe using array kartoy
                              x = i + dr[0]     -> for dir(1, 0) : x = i + 1
                              y = j + dr[1]     -> for dir(1, 0) : y = j + 0

                           -> Basic Checks kele to know edge cell ahe ka te and cell value 0 or 1 tr nahiye na our uniqueId starts from 2
                              if(x > 0 || x < n - 1 || y > 0 || y < n - 1 || grid[x][y] > 1)
                                 uniqueIdSet.add(grid[x][y])

                     -> Now the set I got let's find islandSize corrusponding to those uniqueId's and add them
                        for(Set<Integer> id : uniqueIdSet)
                           currLargestIslandAfterRepacing0 += map.get(id)
                  
               -> get maximun between those islands
                  maxLargestIslandSize = max(maxLargestIslandSize, currLargestIslandAfterRepacing0)
             
      -> return maxLargestIslandSize
   
   }

   function dfsForCheckingIslandSize (i, j, uniqueId, currIslandSize, visited, grid, n) {
   
      -> Base Case :
         if(i > n || j > n || i < 0 || j < 0 || visited[i][j] || grid[i][j] == 0)
            return currIslandSize
         
      -> let's mark cell as visited
         visited[i][j] = true

      -> replace it's value from 1 to uniqueId
         it's easy to just replace uniqueId here bcoz we know it 

            grid[i][j] = uniqueId

      -> Increase the size of island
         why? bcoz when we visit any cell which has 1 that means it's a part of that island
         so every time we visited cell with element 1 island size also increase by 1
         nothing rocket science just a common sense

            currIslandSize += 1

      -> let's check BRUL 
         I can use dirs array here too

            dfsForCheckingIslandSize(i+1, j)
            dfsForCheckingIslandSize(i-1, j)
            dfsForCheckingIslandSize(i, j+1)
            dfsForCheckingIslandSize(i, j-1)

      -> at the end return currIslandSize
      
   }



 */