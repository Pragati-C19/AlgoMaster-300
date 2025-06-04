import java.util.*;

public class MinimumTotal {
    
    public int minimumTotal(List<List<Integer>> triangle) {
        
        // Declare variables
        int m = triangle.size();
        List<List<Integer>> dp = new ArrayList<>();


        // ohk me mhntey dp.get(0) but me tr index dilich nahi,
        // jasa int[][] madhe dp declare kela ki automatic 0 value set hote tasa mala ithe karav lagel
        for (int row = 0; row < m; row++) {
            
            // adding empty list in dp[row]
            dp.add(new ArrayList<>());

            for (int col = 0; col < triangle.get(row).size(); col++) {
                
                // apan ithech set karu initial value for cell (0,0), adhi zero then again change karaych nako itka 2 veles work 
                if (row == 0 && col == 0) {
                    
                    int gridCell00 = triangle.get(0).get(0);
                    dp.get(row).add(gridCell00);
                }
                else if (row > 0 && col == 0) {
                    
                    // Check first Col of each row i = varies and j = 0
                    int valueFromTop = dp.get(row - 1).get(0);
                    int currGridValue = triangle.get(row).get(0);

                    int sumOfAbove = valueFromTop + currGridValue;

                    dp.get(row).add(sumOfAbove);
                }
                else {

                    dp.get(row).add(0);
                }
            }
        }
        System.out.println(" [Initial && 1st col] DP Array : " + dp);


        // Let's Check remaining cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < triangle.get(i).size(); j++) {
                
                int valueFromTop = dp.get(i-1).get(j);
                int currGridValue = triangle.get(i).get(j);

                int sumFromTop = valueFromTop + currGridValue;
                int prevIndexDP = dp.get(i).get(j-1);

                int minBetweenAbove = Math.min(sumFromTop, prevIndexDP);

                dp.get(i).set(j, prevIndexDP);
            }
        }
        System.out.println(" [Remain Cells] DP Array : " + dp);


        return 0;
    }

    public static void main(String[] args) {

        MinimumTotal solution = new MinimumTotal();

        List<List<Integer>> triangle1 = Arrays.asList(
            Arrays.asList(2),
            Arrays.asList(3, 4),
            Arrays.asList(6, 5, 7),
            Arrays.asList(4, 1, 8, 3)
        );
        System.out.println("Result 1 -> " + solution.minimumTotal(triangle1) + "\n"); // 11


        List<List<Integer>> triangle2 = Arrays.asList(
            Arrays.asList(-10)
        );
        System.out.println("Result 2 -> " + solution.minimumTotal(triangle2) + "\n"); // -10

    }

}

/*
 * Intuitions :
 
    1. We have given a triangle array
    2. we need to return path sum from top to bottom
    3. for each step we may move to adj number
        as in if u are on index i on curr row
        then will move to either i or index i + 1 on the next row
 
 
 * Pattern :
 
    ^ Trace Example :

                    triangle                                         dp                                         dp (optimal logic)                                                                                                                                   

                        2                                             2                                                 2                                                                     
                    3       4                                2+3=5  /   \  2+4=6                               2+3=5  /   \  2+4=6                                                                                                                                  
                6       5       7                                  5     6                                           5     5                                                                  
            4       1       8       3                             /       \                                         /       \                                                                     
                                                         [5+6=11, [5+5=10, [5+7=12,                            5+6=11 5+5=10 5+7=12                                                                     
                                                          6+6=12]  6+5=11]  6+7=13]                               min(11,10,12)                                                                     
                    [i,j] value                               /       |        \                                /       |        \                                                                                                                    
                                                             11       10       12                              10       10       10                                                                   
                      [0,0]                                 /         |          \                            /         |          \                                                                  
                  [1,0]   [1,1]                     [11+4=15, [11+1=12, [11+8=19, [11+3=14,             10+4=14  10+1=11  10+8=18  10+3=13                                 
              [2,0]   [2,1]   [2,2]                  10+4=14,  10+1=11,  10+8=18,  10+3=13,                      min(14,11,18,13)                                                        
          [3,0]   [3,1]   [3,2]   [3,3]              12+4=16]  12+1=13]  12+8=20]  12+3=15]               /       |          |        \                                       
                                                        /        |          |        \                  14       11          18        13                                                                                                                                     
                                                      14         11         18        13                                                                                                                                                   

        - 11 is th ans bcoz it's a min one
        
        - First approach
            yat me check karat hote ki prevRow madhle nums one by one add karayche currRow chya nums madhe 
            then tyancha madhun min lihaycha dp madhe
            but ithe pratek veles last 3 prev nums check karan is not possible
        
        - Second approach 
            to avoid too much sum and all
            apan pratek dp row la min set karu
            for(i = 1 to m)
                for(j = 0 to triangle[i].length)

                    -> he prevIndexPath sathi why use i=1? check out [i-j] value above
                    prevIndexPath = dp[i-1][j]
                    
                    -> Check what will be the sum if we add it in currNum 
                    sumWeGet = grid[i][j] + prevIndexPath

                    dp[i][j] = min(dp[i][j], sumWeGet)

            after tracing this I think I made a mistake
                karan if 
                    i = 1 and j = 0 : grid[1][0] = 3  dp[0][0] = 2
                        then dp[1][0] = min(max, 3+2) = 5
                    i = 1 and j = 1 : grid[1][1] = 4  dp[0][0] = 2
                        then dp[1][1] = min(max, 4+2) = 6

                see me kadhich 5 both side set nahi akru shakle

        - Third Approach
            apan thod change karu code just a little bit 
            mala fact top(i-1) nahi check karaychay mala left(j-1) pn check karaychay
            like 
                i = 1 and j = 0 : grid[1][0] = 3  dp[0][0] = 2 we don't have any left num here
                    then dp[1][0] = 3+2 = 5
                i = 1 and j = 1 : grid[1][1] = 4  
                    sumFromTop  = grid[i][j] + dp[i-1][j] = grid[1][1] + dp[0][0] = 4 + 2 = 6   
                    sumLeftNum = dp[i][j-1] = dp[1][0] = 5

                    then dp[1][1] = min(6, 5) = 5



 * Pseudo Code :

    function minimumTotal (triangle) {
    
        -> Declare variables
            m = triangle.length
            apan n nahi find karan bcoz each row has diff num of col
            dp = to store min sum we get from a path (top or left)
            
        -> Initially set dp[0][0] as grid[0][0]
            bcoz it's astarting point

        -> let's set every first col of each row or u can say 0th index of each row
            where i = varies and j = 0
            for(i = 1 to m)

                - it dosn't have left num so we are not going to check ki prev num cha dp value small ahe ki nahi te
                    apan directly top + curr jo yeil to store karu

                    dp[i][0] = dp[i-1] + grid[i][0]

        
        -> Now check other cells 
            for(i = 1 to m)
                for(j = 1 to triangle[i].length)

                    sumFromTop = dp[i-1][j] + grid[i][j]
                    prevIndexDP = dp[i][j-1]

                    dp[i][j] = min(sumFromTop, prevIndexDP)
        
        -> At end return dp[m-1][n-1]
    
    }


 */