import java.util.*;

public class CountSquares {
    
    public int countSquares(int[][] matrix) {
        
        // Declare variables 
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int sumOfSubmatrices = 0;


        // Check first Row i = 0 and j = varies
        System.out.println("Filling first row:");
        for (int j = 0; j < n; j++) {
            
            // for first row we don't have top values like [i-1,j] or [i-1,j-1], 
            // There's no row above         -> dp[i-1][j] is invalid
            // There's no diagonal top-left -> dp[i-1][j-1] is invalid
            // So we can’t form any square larger than 1x1 here.

            dp[0][j] = matrix[0][j];

        }
        System.out.println("  [1st Row] DP Array : " + Arrays.deepToString(dp));
        System.out.println("-----------------------------\n");
        

        // Check first Col i = varies and j = 0
        System.out.println("Filling first col:");
        for (int i = 0; i < m; i++) {
            
            // for first col we don't have left side value [i,j-1], 
            // There's no left side   -> dp[i][j-1] is invalid
            // So we can't form any square larger than 1x1 here.

            dp[i][0] = matrix[i][0];

        }
        System.out.println("  [1st Col] DP Array : " + Arrays.deepToString(dp));
        System.out.println("-----------------------------\n");
        

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                
                // There's row above         -> dp[i-1][j] is valid
                // There's diagonal top-left -> dp[i-1][j-1] is valid
                // There's col left side     -> dp[i][j-1] is valid
                // So we can form square but will take min between them 

                int top = dp[i-1][j];
                int topLeft = dp[i-1][j-1];
                int left = dp[i][j-1];

                int minOfTopValues = Math.min(top, topLeft);

                dp[i][j] = 1 + Math.min(minOfTopValues, left);
            }

            System.out.println("  [Remain Cells] DP Array : " + Arrays.deepToString(dp));
        }
        

        // Let's do sum of all nums in dp
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                sumOfSubmatrices += dp[i][j];
            }
        }

        return sumOfSubmatrices;
    }

    public static void main(String[] args) {

        CountSquares solution = new CountSquares();

        int[][] matrix1 = {
            {0,1,1,1},
            {1,1,1,1},
            {0,1,1,1}
        };
        System.out.println("Result 1 -> " + solution.countSquares(matrix1) + "\n");    // 7

        int[][] matrix2 = {
            {1,0,1},
            {1,1,0},
            {1,1,0}
        };
        System.out.println("Result 2 -> " + solution.countSquares(matrix2) + "\n");    // 12
        
        // int[][] matrix3 = {
        //     {1}
        // };
        // System.out.println("Result 3 -> " + solution.countSquares(matrix3) + "\n");    // 0

    }

}

/*
 * Intuitions :
 
    1. we have given matrix of m x n
    2. where we see 0 and 1
    3. we need to return how many square submatrices have all ones
        meaning? let's understand with example
    4. submatrices : (1x1, 2x2, 3x3...) 
        we need to find all submatrices exist that are filled with all 1s.
 
 
 * Pattern :
 
    ^ Trace Example :

               matrix 

            0   1   1   1
            1   1   1   1
            0   1   1   1

        - Count 1 x 1 submatrix filled with 1s
                
            0   1   1   1       -> 3 ones
            1   1   1   1       -> 4 ones
            0   1   1   1       -> 3 ones

            1x1Count = 3 + 4 + 3 = 10

        - Count 2 x 2 submatrix filled with 1s
                
               _______                 _______                                                                                                                                                                                                                                                              
            0 | 1   1 | 1       0   1 | 1   1 |       0   1   1   1        0   1   1   1                                                                                                                                                                                                                                                   
            1 | 1   1 | 1       1   1 | 1   1 |          _______                  _______                                                                                                                                                                                                                                
               -------                 -------        1 | 1   1 | 1        1   1 | 1   1 |                                                                                                                                                                                                                                  
            0   1   1   1       0   1   1   1         0 | 1   1 | 1        0   1 | 1   1 |                                           
                                                         -------                  -------                                                                                                                                                                                                                                                                    

            2x2Count = 1 + 1 + 1 + 1 = 4

        - Count 3 x 3 submatrix filled with 1s
               ___________             
            0 | 1   1   1 |   
            1 | 1   1   1 |
            0 | 1   1   1 |
               -----------  
            
            3x3Count = 1 

        - Total will be 10 + 4 + 1 = 15

    Appraoch : 

        1. Declare variables
            m, n
            dp      -> to store the size of largest square ending at currCell which use only 1s

        2. matrix madhe 0 asel tr apan to count nahi karnar ahe
            so jithe pn zero asel will keep it zero in dp
            if(matrix[i][j] == 0)
                dp[i][j] = 0

        3. if we see 1 then we need to start counting now

             dp (initially all 0s)
                |               
              0 |   0   0   0   0   
              1 |   0   0   0   0
              2 |   0   0   0   0
            ----|------------------
                |   0   1   2   3

        4. Fill first Col and Row

                        dp
                |               
              0 |   0   1   1   1   
              1 |   1   0   0   0
              2 |   0   0   0   0
            ----|------------------
                |   0   1   2   3
            
        5. Fill remaining cells one by one

                        dp
                |               
              0 |   0   1   1   1   
              1 |   1   1   2   2
              2 |   0   1   2   3
            ----|------------------
                |   0   1   2   3

            - i = 1, j = 1 matrix[1][1] = 1

                we need to take min of min(top, left, top-left) 
                and then add currCell in it

                1 + dp([i-1][j], [i][j-1], [i-1][j-1])

                dp[1][1] = 1 + min([0,1], [1,0], [0,0]) = 1 + min(1,1,0) = 1

            - i = 1, j = 2 matrix[1][2] = 1
                dp[1][2] = 1 + min([0,2], [1,1], [0,1]) = 1 + min(1,1,1) = 2

            - i = 1, j = 3 matrix[1][3] = 1
                dp[1][3] = 1 + min([0,3], [1,2], [0,2]) = 1 + min(1,2,1) = 2

            - i = 2, j = 1 matrix[2][1] = 1
                dp[2][1] = 1 + min([1,1], [2,0], [1,0]) = 1 + min(1,0,1) = 1

            - i = 2, j = 2 matrix[2][2] = 1
                dp[2][2] = 1 + min([1,2], [2,1], [1,1]) = 1 + min(2,1,1) = 2

            - i = 2, j = 3 matrix[2][3] = 1
                dp[2][3] = 1 + min([1,3], [2,2], [1,2]) = 1 + min(2,2,2) = 3


        6. At end wrote addition of all nums in dp 

            sum =     0 + 1 + 1 + 1     = 15
                    + 1 + 1 + 2 + 2
                    + 0 + 1 + 2 + 3

 
 * Pseudo Code :
 



 */