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

                    // will add maxValue bcoz I want to add min sum we get by adding all nums of prev 
                    dp.get(row).add(Integer.MAX_VALUE);
                }
            }
        }
        System.out.println(" [Initial && 1st col] DP Array : " + dp);


        // Let's Check remaining cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < triangle.get(i).size(); j++) {
                
                System.out.println("checking " + i + ", " + j);

                int valueFromTop;

                // do j-1 only for last num of row, why? check improvement comment below
                if (j == triangle.get(i).size() - 1) {
                    
                    valueFromTop = dp.get(i-1).get(j-1);
                }
                else {

                    valueFromTop = dp.get(i-1).get(j);
                }

                // value From top is correct
                System.out.println("    - valueFromTop : " + valueFromTop);

                // curr Grid ghen is correct 
                int currGridValue = triangle.get(i).get(j);
                System.out.println("    - currGridValue : " + currGridValue);

                // tyanchi sum karan is correct
                int sumFromTop = valueFromTop + currGridValue;
                System.out.println("    - sumFromTop : " + sumFromTop);
                
                //todo: Need to change this
                int prevIndexDP = dp.get(i).get(j-1);
                System.out.println("    - prevIndexDP : " + prevIndexDP);

                int minBetweenAbove = Math.min(sumFromTop, prevIndexDP);
                System.out.println("    - minSum : " + minBetweenAbove);

                dp.get(i).set(j, minBetweenAbove);
            }
        }
        System.out.println(" [Remain Cells] DP Array : " + dp);


        // I need the value of from last row's last num from dp 
        // so first getting it's size and then will get num by get(size - 1)
        int lastRowSize = dp.get(m-1).size();
        System.out.println("Testing how to return dp[m-1][n-1] : size - " + (lastRowSize - 1) + ", value : " + dp.get(m - 1).get(lastRowSize - 1));

        return dp.get(m-1).get(lastRowSize - 1);
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


        // test case wants to take path as -1 -> 3-1 = 2 -> last row by this [3,1,-1] so ans is -1
        // currently my code works as      -1 -> 2-1 = 1 -> last row by this [2,0,-2] 
        List<List<Integer>> triangle3 = Arrays.asList(
            Arrays.asList(-1),
            Arrays.asList(2, 3),
            Arrays.asList(1, -1, -3)
        );
        System.out.println("Result 3 -> " + solution.minimumTotal(triangle3) + "\n"); // -1

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

    ^ Improvements :

        currCode gives ans as

        |  i  |  j  |  valueFromTop (i-1, j-1) |  currGridValue  |  sumFromTop (V+C)  |  prevIndexDP (i, j-1) |  minSum = min(S, P) |
        | --- | --- | ------------------------ | --------------- | ------------------ | --------------------- | ------------------- |
        |  1  |  1  |         2 (0,0)          |        4        |      6 (2+4)       |        5 (1,0)        |   min(6,5) = 5      |
        |  2  |  1  |         5 (1,0)          |        5        |      10 (5+5)      |        11 (2,0)       |   min(10,11) = 10   |
        |  2  |  2  |         5 (1,1)          |        7        |      12 (5+7)      |        10 (2,1)       |   min(12,10) = 10   |
        |  3  |  1  |         11 (2,0)         |        1        |      12 (11+1)     |        15 (3,0)       |   min(12,15) = 12   | 
        |  3  |  2  |         10 (2,1)         |        8        |      18 (10+8)     |        12 (3,1)       |   min(18,12) = 12   |
        |  3  |  3  |         10 (2,2)         |        3        |      13 (10+3)     |        12 (3,2)       |   min(13,12) = 12   |

        -> cell [3,1] chya tithe issue ahe karan ithech 10 + 1 = 11 have hote but jevha check kartoy tevha j-1 mul V chi value (2,0) hotoy 

        -> Solve bug with 
            if (j == triangle.get(i).size() - 1) {      
                valueFromTop = dp.get(i-1).get(j-1);
            }
            else {
                valueFromTop = dp.get(i-1).get(j);
            }

        - what does it do exacly ?
            - ha problem fact [1,1] chya tithe nahi tr every last col of row la asel..
            - pratek row chi size prev pekshya ek ni jast ahe tyamul he nehmich hoil 
            - see ithe problem ahe.. i-1 = 0 and j = 1 but asa kontach dp cell nahiye [0,1] vali 
            - tyamul let's try to get prev row madhla last (i-1, j-1) ?
                means jr me row = 2 vr ahe ani maza last num of this row is at j = 2 
                tr prev row cha last num nehmi ek ni kami mhnjech j-1 = 2-1 = 1 : (1,1) vr asel 
                tyamul row chya last num la top value ghyaychi asel tr fact i-1 nahi tr j-1 pn karav lagel
            - why? karan prev row chi size ek ni kami ahe so apan prev row cha end as in (i-1,j-1) ghetoy

    

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


    1st Apprroach wrote :

    public int minimumTotal(List<List<Integer>> triangle) {
        
        -> Declare variables
        int m = triangle.size();
        List<List<Integer>> dp = new ArrayList<>();


        -> ohk me mhntey dp.get(0) but me tr index dilich nahi,
        -> jasa int[][] madhe dp declare kela ki automatic 0 value set hote tasa mala ithe karav lagel
        for (int row = 0; row < m; row++) {
            
            -> adding empty list in dp[row]
            dp.add(new ArrayList<>());

            for (int col = 0; col < triangle.get(row).size(); col++) {
                
                -> apan ithech set karu initial value for cell (0,0), adhi zero then again change karaych nako itka 2 veles work 
                if (row == 0 && col == 0) {
                    
                    int gridCell00 = triangle.get(0).get(0);
                    dp.get(row).add(gridCell00);
                }
                else if (row > 0 && col == 0) {
                    
                    -> Check first Col of each row i = varies and j = 0
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


        -> Let's Check remaining cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < triangle.get(i).size(); j++) {
                
                System.out.println("checking " + i + ", " + j);

                int valueFromTop;

                -> do j-1 only for last num of row, why? check improvement comment below
                if (j == triangle.get(i).size() - 1) {
                    
                    valueFromTop = dp.get(i-1).get(j-1);
                }
                else {

                    valueFromTop = dp.get(i-1).get(j);
                }

                System.out.println("    - valueFromTop : " + valueFromTop);

                int currGridValue = triangle.get(i).get(j);
                System.out.println("    - currGridValue : " + currGridValue);

                int sumFromTop = valueFromTop + currGridValue;
                System.out.println("    - sumFromTop : " + sumFromTop);
                int prevIndexDP = dp.get(i).get(j-1);
                System.out.println("    - prevIndexDP : " + prevIndexDP);

                int minBetweenAbove = Math.min(sumFromTop, prevIndexDP);
                System.out.println("    - minSum : " + minBetweenAbove);

                dp.get(i).set(j, minBetweenAbove);
            }
        }
        System.out.println(" [Remain Cells] DP Array : " + dp);


        -> I need the value of from last row's last num from dp 
        -> so first getting it's size and then will get num by get(size - 1)
        int lastRowSize = dp.get(m-1).size();
        System.out.println("Testing how to return dp[m-1][n-1] : size - " + (lastRowSize - 1) + ", value : " + dp.get(m - 1).get(lastRowSize - 1));

        return dp.get(m-1).get(lastRowSize - 1);
    }



 */