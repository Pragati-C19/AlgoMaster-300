import java.util.*;

public class MinCostClimbingStairs {
    
    public int minCostClimbingStairs(int[] cost) {
        
        // Declare variables
        int n = cost.length;
        int[] dp = new int[n];

        // assign initial values
        dp[0] = cost[0];
        dp[1] = cost[1];

        // check other index
        for (int i = 2; i < n; i++) {
            
            // get min cost between prev steps steps
            int minCostForPrevSteps = Math.min( dp[i - 1], dp[i - 2] );

            dp[i] = cost[i] + minCostForPrevSteps;

            System.out.println("    - After cheking " + i + " step DP array looks like : " + Arrays.toString(dp));
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {

        MinCostClimbingStairs solution = new MinCostClimbingStairs();

        int[] cost1 = {10,15,20};
        System.out.println("Result 1 -> " + solution.minCostClimbingStairs(cost1) + "\n");

        int[] cost2 = {1,100,1,1,1,100,1,1,100,1};
        System.out.println("Result 2 -> " + solution.minCostClimbingStairs(cost2) + "\n");
        
    }
}

/*
 * Intuitions :
 
    1. we have given an array cost
        where cost[i] is the cost of i'th step on staircase
    2. once we pay the cost, we can climb 1 or 2 steps
    3. we can start from index 0 or index 1
    4. need to return minimum cost to reach the top of the floor

 
 * Pattern :
 
    1. declare a dp to check minimum cost we spend to reach the specific step
    2. dp[0] which is ground so it's cost is same as 0'th index cost
    3. dp[1] which is first step and que says we can start from 0 ro 1 
        so I think it's cost will be same as 1st index cost 
    4. start to check from 2nd index
        - we need minimun cost right? 
        - aplyala [i-1] and [i-2] madhla min cost ghyava lagel
        - and tyat mala curr index chi cost add karavi lagel
        - yeah I'm shock too maz dok asa kasa challa te but it's the right approach
 
 
 * Pseudo Code :
 
    function minCostClimbingStairs (cost) {
    
        -> declare dp
    
        dp[0] = cost[0]
        dp[1] = cost[1]

        for(i = 2 to cost.length)

            - get minimum cost between it's prev step and prev of prev step
            - why? karan bagh mala haviye minimum cost and mala curr cost pn pay karaychiye
                tyamul mala 2 steps prev chya cost madhe add karavi lagel curr cost and mala tyatla min lagel mhnun me adhich min kadhun ghete

            minCostForPrevSteps = min(dp[i-1], dp[i-2])

            - add one of the prev step cost in currCost
            dp[i] = cost[i] + minCostForPrevSteps
        
        return dp[n]
    }


 */