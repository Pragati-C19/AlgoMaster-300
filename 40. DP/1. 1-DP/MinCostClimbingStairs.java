import java.util.*;

public class MinCostClimbingStairs {
    
    public int minCostClimbingStairs(int[] cost) {
        
        // Declare variables
        int n = cost.length;
        int[] dp = new int[n + 1];

        // assign initial values
        dp[0] = cost[0];      // No cost to start before step 0
        dp[1] = cost[1];      // we can also start at step 1 for free

        // check other index
        for (int i = 2; i < n; i++) {
            
            // Option 1: Reach step i from step (i - 1)
            // We'll pay the total cost to reach step (i - 1), which is dp[i - 1],
            // and then pay cost[i - 1] to step on it before jumping to step i.
            int costFromOneStepBefore = dp[i - 1] + cost[i - 1];

            // Option 2: Reach step i from step (i - 2)
            // We'll pay the total cost to reach step (i - 2), which is dp[i - 2],
            // and then pay cost[i - 2] to step on it before jumping to step i.
            int costFromTwoStepsBefore = dp[i - 2] + cost[i - 2];

            // Choose the minimum of both options — the cheapest way to reach step i
            dp[i] = Math.min(costFromOneStepBefore, costFromTwoStepsBefore);

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
 
    ^ Improvement :

        Trace Example:
            If cost length = 4, then:

                      cost[0], cost[1], cost[2], cost[3]  
                         ^        ^        ^        ^                 
                         |        |        |        |   
                       step0    step1    step2    step3     <-- top is here (after step3)

            
            You can reach the top by:
                - ending on step 3 (then jump)
                - ending on step 2 (then jump)

            So the best path is:
                min(dp[2], dp[3])

 
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