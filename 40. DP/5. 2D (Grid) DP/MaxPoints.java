import java.util.*;

public class MaxPoints {
    
    public long maxPoints(int[][] points) {
        
        // Declare Variables
        int m = points.length;
        int n = points[0].length;
       
        // dp array stores the maximum points that can be collected up to previous row
        long[] dp = new long[n];

        // Base case: First row has no movement cost, so just use points directly
        for (int j = 0; j < n; j++) {
            dp[j] = points[0][j];
        }
        System.out.println("[1st Row] DP Array : " + Arrays.toString(dp));



        // Traverse remaining rows starting from 1 (second row)
        for (int i = 1; i < m; i++) {
            long[] left = new long[n];  // Stores best score from left-to-right sweep
            long[] right = new long[n]; // Stores best score from right-to-left sweep
            long[] newDp = new long[n]; // Temporary dp array for current row

            // Fill left sweep: tracks max(dp[k] - (j - k)) from left side
            left[0] = dp[0];
            for (int j = 1; j < n; j++) {
                // We reduce 1 for each step rightward
                left[j] = Math.max(left[j - 1] - 1, dp[j]);
                System.out.printf("     Left[%d] = max(Left[%d]-1=%d, dp[%d]=%d) = %d\n", j, j - 1, left[j - 1] - 1, j, dp[j], left[j]);
            }
            System.out.println("\n  Row " + i + " Left Pass  : " + Arrays.toString(left));

            // Fill right sweep: tracks max(dp[k] - (k - j)) from right side
            right[n - 1] = dp[n - 1];
            for (int j = n - 2; j >= 0; j--) {
                // We reduce 1 for each step leftward
                right[j] = Math.max(right[j + 1] - 1, dp[j]);

                System.out.printf("     Right[%d] = max(Right[%d]-1=%d, dp[%d]=%d) = %d\n", j, j + 1, right[j + 1] - 1, j, dp[j], right[j]);
            }
            System.out.println("  Row " + i + " Right Pass : " + Arrays.toString(right));

            // Update new dp values for current row
            for (int j = 0; j < n; j++) {
                // Take max of left[j] and right[j] to avoid O(n^2)
                // Then add current cell value to it
                long bestFromPrevRow = Math.max(left[j], right[j]);
                newDp[j] = points[i][j] + bestFromPrevRow;

                System.out.printf("     dp[%d][%d] = max(left[%d]=%d, right[%d]=%d) + points[%d][%d]=%d -> %d\n",i, j, j, left[j], j, right[j], i, j, points[i][j], newDp[j]);
            }

            // Replace dp with newDp for next iteration
            dp = newDp;
            System.out.println("[Updated] DP Array : " + Arrays.toString(dp));

        }

        // Return the max value from the last dp row
        long result = 0;
        for (long val : dp) {
            result = Math.max(result, val);
        }

        System.out.println("\nFinal DP: " + Arrays.toString(dp));
        System.out.println("Result (max points): " + result);

        return result;
    }

    public static void main(String[] args) {

        MaxPoints solution = new MaxPoints();

        int[][] points1 = {
            {1,2,3},
            {1,5,1},
            {3,1,1}
        };
        System.out.println("Result 1 -> " + solution.maxPoints(points1) + "\n");    // 9

        int[][] points2 = {
            {1,5},
            {2,3},
            {4,2}
        };
        System.out.println("Result 2 -> " + solution.maxPoints(points2) + "\n");    // 11
        
        // int[][] points3 = {
        //     {1}
        // };
        // System.out.println("Result 3 -> " + solution.maxPoints(points3) + "\n");    // 0

    }

}

/*

 ^ Took Help of GPT and solved it.. 


 * Intuitions :
 
    1. we have given a mxn points called points (0-indexed)
        means it's row by row 
    2. will start from point 0 
        means row = 0
    3. we need to max the number of points u can get from the points
    4. to gain points, we must pick one cell in each row
        will add points[r][c] in our score
    5. However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. 
       For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
       - I don't understand this 
    6. as per examples it seems we need to pick max number from every row and add it
 
 
 * Pattern :
 
    1. ohk one sec till this we are going correct..
        main approach of que is pick co-ordinates and add it 
        like we did here take max and add it in result
        then tyatun mala (j0-j1) + (j1-j2) karaych je ans yeil te return karaychy
        
        example : 
                     points                                                                                                      
                |                       - will take max of every cell [0,2] = 3, [1,1] = 5, [2,0] = 3                                                               
              0 |   1   2   3           - Take Sum of it                                                                    
              1 |   1   5   1               result = 3 + 5 + 3 = 11                                                 
              2 |   3   1   1           - ata result madhun (j0-j1) + (j1-j2) substract kr as in                                                      
            ----|--------------                 [i0,j0] = [0,2]     [i1,j1] = [1,1]     [i2,j2] = [2,0]                                                                 
                |   0   1   2               colSubstraction = (j0 - j1) + (j1 - j2) = (2 - 1) + (1 - 0) = 1 + 1 = 2                                                                
 
    2. Let's say we're at dp[i][j] right now.
    
        We want to know:
            - From which column k(j-1) in previous row i-1
            - should I come, such that I get max score?
        
        So we're trying all:
            dp[i][j] = max over k ( dp[i-1][k] + points[i][j] - |j - k| )
            
            Wait... Why This Transition? Because:
                - dp[i-1][k] is what score I had if I was in col k in previous row
                - points[i][j] is the value I'm about to take
                - |j - k| is the cost of shifting from col k to col j


    2. Trace Example 

            dp (iniital value)     
                |               
              0 |   0   0   0   
              1 |   0   0   0   
              2 |   0   0   0   
            ----|-------------- 
                |   0   1   2   
        
        - Will go row by row.. remember tithe row madhun ek num ahe so row by row
            check first row (row = 0) set values of them
                - for first row there will be no top 
                - and apalyala tr top num sobt compare karaychy curr 
                - tyamul first of dp will be same as first row do points matrix

                    dp     
                |               
              0 |   1   2   3   
              1 |   0   0   0   
              2 |   0   0   0   
            ----|-------------- 
                |   0   1   2
            
        - ata row 2nd kade ja.. row = 1

            - i = 1, j = 0  points[1][0] = 1

                from [i-1] = 0, [j-1] = 0  -> dp[0][0] + points[1][0] - (currJ - prevJ) = 1 + 1 - (0-0) = 2
                from [i-1] = 0, [j-1] = 1  -> dp[0][1] + points[1][0] - (currJ - prevJ) = 2 + 1 - (0-1) = 2
                from [i-1] = 0, [j-1] = 2  -> dp[0][2] + points[1][0] - (currJ - prevJ) = 3 + 1 - (0-2) = 2

                will take max of above values 
                    max(2,2,2) = 2

                dp[1][0] = 2
            

            - i = 1, j = 1  points[1][1] = 5

                from [i-1] = 0, [j-1] = 0  -> dp[0][0] + points[1][1] - (currJ - prevJ) = 1 + 5 - (1-0) = 5
                from [i-1] = 0, [j-1] = 1  -> dp[0][1] + points[1][1] - (currJ - prevJ) = 2 + 5 - (1-1) = 7
                from [i-1] = 0, [j-1] = 2  -> dp[0][2] + points[1][1] - (currJ - prevJ) = 3 + 5 - (1-2) = 7

                will take max of above values 
                    max(5,7,7) = 7

                dp[1][1] = 7


            - i = 1, j = 2  points[1][2] = 1

                from [i-1] = 0, [j-1] = 0  -> dp[0][0] + points[1][2] - (currJ - prevJ) = 1 + 1 - (2-0) = 0
                from [i-1] = 0, [j-1] = 1  -> dp[0][1] + points[1][2] - (currJ - prevJ) = 2 + 1 - (2-1) = 2
                from [i-1] = 0, [j-1] = 2  -> dp[0][2] + points[1][2] - (currJ - prevJ) = 3 + 1 - (2-2) = 4

                will take max of above values 
                    max(0,2,4) = 4

                dp[1][2] = 4

            - Dp after first row looks like  :

                        dp     
                    |               
                  0 |   1   2   3   
                  1 |   2   7   4   
                  2 |   0   0   0   
                ----|-------------- 
                    |   0   1   2            


        - ata row 3rd kade ja.. row = 2

            - i = 2, j = 0  points[2][0] = 3

                from [i-1] = 1, [j-1] = 0  -> dp[1][0] + points[2][0] - (currJ - prevJ) = 2 + 3 - (0-0) = 5
                from [i-1] = 1, [j-1] = 1  -> dp[1][1] + points[2][0] - (currJ - prevJ) = 7 + 3 - (0-1) = 9
                from [i-1] = 1, [j-1] = 2  -> dp[1][2] + points[2][0] - (currJ - prevJ) = 4 + 3 - (0-2) = 5

                will take max of above values 
                    max(5,9,5) = 9

                dp[2][0] = 9
            

            - i = 2, j = 1  points[2][1] = 1

                from [i-1] = 1, [j-1] = 0  -> dp[1][0] + points[2][1] - (currJ - prevJ) = 2 + 1 - (1-0) = 2
                from [i-1] = 1, [j-1] = 1  -> dp[1][1] + points[2][1] - (currJ - prevJ) = 7 + 1 - (1-1) = 8
                from [i-1] = 1, [j-1] = 2  -> dp[1][2] + points[2][1] - (currJ - prevJ) = 4 + 1 - (1-2) = 4

                will take max of above values 
                    max(2,8,4) = 8

                dp[2][1] = 8


            - i = 2, j = 2  points[2][2] = 1

                from [i-1] = 1, [j-1] = 0  -> dp[1][0] + points[2][2] - (currJ - prevJ) = 2 + 1 - (2-0) = 1
                from [i-1] = 1, [j-1] = 1  -> dp[1][1] + points[2][2] - (currJ - prevJ) = 7 + 1 - (2-1) = 7
                from [i-1] = 1, [j-1] = 2  -> dp[1][2] + points[2][2] - (currJ - prevJ) = 4 + 1 - (2-2) = 5

                will take max of above values 
                    max(1,7,5) = 7

                dp[2][2] = 7

            - Dp after first row looks like  :

                        dp     
                    |               
                  0 |   1   2   3   
                  1 |   2   7   4   
                  2 |   9   8   7   
                ----|-------------- 
                    |   0   1   2            


        - Last la result madhe aplyala bas max between last row haay
            max(9, 8, 7) = 9

 * Pseudo Code :
 
    

 */