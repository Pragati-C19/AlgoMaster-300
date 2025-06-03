import java.lang.reflect.Array;
import java.util.*;

public class ClimbStairs {
    
    public int climbStairs(int n) {
     
        // Declare a DP array to store how many ways to reach to that step
        // 1 <= n <= 45
        int[] dp = new int[n + 1];

        // assign starting value to first 2 indexes
        // why 1? bcoz both will have only 1 way to reach
        dp[0] = 1;
        dp[1] = 1;

        // check other steps now
        for (int i = 2; i <= n; i++) {
            
            dp[i] = dp[i - 1] + dp[i - 2];

            System.out.println("    - After cheking " + i + " step DP array looks like : " + Arrays.toString(dp));
        }

        return dp[n];
    }

    public static void main(String[] args) {

        ClimbStairs solution = new ClimbStairs();

        int n1 = 2;
        System.out.println("Result 1 -> " + solution.climbStairs(n1) + "\n");

        int n2 = 3;
        System.out.println("Result 2 -> " + solution.climbStairs(n2) + "\n");
        
    }

}

/*
 * Intuitions :
 
    1. we have given n steps
    2. and each time u can either climb 1 or 2 steps
    3. we need to know how many distinct way we can able to climb to top
 
 
 * Pattern :
 
    1. This problem is kind of fibonacci series
    2. To reach step i:
        - we could have come from step i - 1 (1 step)
        - or from step i - 2 (2 steps)
    3. so addition of it means we have come that much ways combined
    4. try to solve this problem with DP appraoch 
 
 
 * Pseudo Code :
 
    function climbStairs(n) {
    
        -> Declare a DP array of size n
            will store ways to reach that step 

        - 0th index will be the ground so there is only 1 way to go there
        - 1st index means first step to go there we have only 1 way from oth index 

        dp[0] = 1
        dp[1] = 1

        for(i = 2 to n) 

            dp[i] = dp[i-1] + dp[i-2]

        -> why return dp[n] ? bcoz it's the highest count of ways possible to reach the top
        return dp[n]
    }



 */
