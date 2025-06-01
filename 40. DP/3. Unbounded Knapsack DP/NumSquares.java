import java.util.*;

public class NumSquares {
    
    public int numSquares(int n) {
        
        // Declare a dp 
        int[] dp = new int[n];

        // we need to get minimum numsquares to add so will have n 
        // so adding maxValue at start
        Arrays.fill(dp, Integer.MAX_VALUE);

        // Will initiallize first values
        dp[0] = 0;
        dp[1] = 1;

        // check other values
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j*j <= n ; j++) {
                
                int square = j*j;
                int waysUsingThisSquare  = 1 + dp[i - square];

                dp[i] = Math.min(dp[i], waysUsingThisSquare);
            }

            System.out.println("    - Updated DP Array : " + Arrays.toString(dp));
        }

        return dp[n];
    }

    public static void main(String[] args) {

        NumSquares solution = new NumSquares();
       
        System.out.println("Result 1 -> " + solution.numSquares(12) + "\n");    // 3
    
        System.out.println("Result 2 -> " + solution.numSquares(13) + "\n");    // 2
   
        System.out.println("Result 3 -> " + solution.numSquares(6) + "\n");     // 6

    }

}

/*
 * Intuitions :
 
    1. we have given integer n
    2. we need to return least number of perfect square numbers to sum
    3. Perfect Square : 
        - It is an integer that is the square of an integer
        - it is the product of some integer with itself 
        - example : 1, 4, 9, 16 are perfect squares while 3 and 11 are not

 
 * Pattern :
 
    1. Declare a DP with length n+1
        - will store minimize numbers of perfect square that sum upto index
        - initially all values have Max_Value as we nned minimum so can't use 0 
    2. Wrote initial values for 0 and 1 
        dp[0] = 0
        dp[1] = 1
    3. start a for loop 
        for(i = 2 to n)
    4. We need a Sum of perfect square should be equal to n 
        - so will try every square number less than or equal to i
        - why j*j ?
        for(j = 1 to j * j <= i)
    5. get square = j * j 
    6. now as same we did for coinChange que will take minimum
        - from prev value of dp[i] or curr value of dp[i]
        - as in we take this index 1 + dp[i-square]
            or don't take this index dp[i]
        min(dp[i], 1 + dp[i-square])
    7. at the end return dp[n]


    ^ Trace Example :

        n = 6      output = 3
        
        - so perfect squares ≤ 6 are: [1, 4]
        - mark initial value as Max_Value and dp[0] = 0

            index:  0   1   2   3   4   5   6  
            value: [0,  ∞,  ∞,  ∞,  ∞,  ∞,  ∞]

        1. i = 1
            squareOf = 1 -> 1 + dp[1 - 1] = 1 
            will not check other nums bcoz there is no square less than 1

            dp[1] = 1
            
            dp = [0, 1, ∞, ∞, ∞, ∞, ∞]

        2. i = 2
            squareOf = 1 -> 1 + dp[2 - 1] = 2
            will not check other nums bcoz there is no square less than 2

            dp[2] = 2
            
            dp = [0, 1, 2, ∞, ∞, ∞, ∞]

        3. i = 3
            squareOf = 1 -> 1 + dp[3 - 1] = 3 
            will not check other nums bcoz there is no square less than 3

            dp[3] = 3
            
            dp = [0, 1, 2, 3, ∞, ∞, ∞]

        4. i = 4
            squareOf = 1 -> 1 + dp[4 - 1] = 4
            squareOf = 2 -> 1 + dp[4 - 4] = 1 

            Will need min numbers to of squares which sums up to index 
            and here it is 1

            dp[4] = 1
            
            dp = [0, 1, 2, 3, 1, ∞, ∞]

        5. i = 5
            squareOf = 1 -> 1 + dp[5 - 1] = 2
            squareOf = 2 -> 1 + dp[5 - 4] = 2

            Will need min numbers to of squares which sums up to index 
            and here it is 2

            dp[5] = 2
            
            dp = [0, 1, 2, 3, 1, 2, ∞]

        6. i = 6
            squareOf = 1 -> 1 + dp[6 - 1] = 3
            squareOf = 2 -> 1 + dp[6 - 4] = 3
            
            Will need min numbers to of squares which sums up to index 
            and here it is 3

            dp[6] = 3
            
            dp = [0, 1, 2, 3, 1, 2, 3]

        


 * Pseudo Code :



 */