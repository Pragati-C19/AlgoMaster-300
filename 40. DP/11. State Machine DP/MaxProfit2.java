import java.util.*;

public class MaxProfit2 {
    
    public int maxProfit2(int[] prices) {
        
        // Declare variables
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];


        // Check all days from last to first
        for (int day = n-1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int trans = 1; trans <= 2; trans++) {
                    
                    // Check if we can buy today?
                    if (canBuy == 1) {
                        
                        int buy = dp[day + 1][0][trans] - prices[day];
                        int skip = dp[day + 1][1][trans];

                        dp[day][canBuy][trans] = Math.max(buy, skip); 

                        System.out.println("  Buy Today dp[" + day + "," + canBuy + "," + trans + "] : " + dp[day][canBuy][trans]);

                    }
                    else {

                        int sell = dp[day + 1][1][trans - 1] + prices[day];
                        int skip = dp[day + 1][0][trans];

                        dp[day][canBuy][trans] = Math.max(sell, skip); 

                        System.out.println("    - Sell Today dp[" + day + "," + canBuy + "," + trans + "] : " + dp[day][canBuy][trans]);

                    }

                }
            }
        } 


        // return starting day 0, canBuy, and transactionLeft as 2
        return dp[0][1][2];
    }

    public static void main(String[] args){

        MaxProfit2 solution = new MaxProfit2();

        int[] prices1 = {3,3,5,0,0,3,1,4};
        System.out.println(" Result1 -> " + solution.maxProfit2(prices1) + "\n");    // 6

        int[] prices2 = {1,2,3,4,5};
        System.out.println(" Result2 -> " + solution.maxProfit2(prices2) + "\n");    // 4
    
        int[] prices3 = {7,6,4,3,1};
        System.out.println(" Result3 -> " + solution.maxProfit2(prices3) + "\n");    // 0
    
    }

}

/*
 * Intuitions :
 
    1. We have given prices array
        where prices[i] is the price of given stock on day i
    2. we need to find maxProfit
    3. Condtiotions :
        - we only have two transactions limit
        - We must sell stock before we buy again
 
 
 * Pattern :
 
    1. We need an DP with 3 key dimentions:
        - day : it will have curr day index
        - canBuy : it will tell if we can buy today or not 
                    1 for yes, and 0 for holding
        - transactionsLeft : it will tell how many transactions are remaining (as per que we have max 2)

    2. if we can Buy, we have two options :
        Buy   -> lose money  -> go to canBuy = 0, same Day + 1
        Skip  -> stay at canBuy = 1

    3. if we can Sell, we have two options :
        Sell  -> earn money  -> go to canBuy = 1, reduce transactionsLeft
        Skip  -> stay at canBuy = 0

    4. we need to take max from both options and store it in dp
        - apan buy tevhach karu shakto jevha canBuy = 1 asel 
        - apan sell tevhach karu shakto jevha canBuy = 0 asel

    5. why we are doing (day = n - 1 to 0) and dp[i+1] instead of checking prev day
        - Decisions today affect future days, not past ones
        - So we need to solve from future and backward
 
 
 * Pseudo Code :
 
    function maxProfit (prices) {
    
        -> Declare variables
            n       - length of prices and number of days
            dp      - it will store [dayIndex][canBuy][transactionLeft]

        -> loop through day, canBuy, trans
            for(day = n - 1 to 0)
                for(canBuy = 0 to 1)
                    for(trans = 1 to 2)

                        - check if we canBuy stock today? 
                        
                            if yes then
                                dp[day][1][trans] = max( buy, skip )

                            if no then
                                dp[day][0][trans] = max( sell, skip )

        -> return start day 0, canBuy, with 2 transactions
    
    }


 */