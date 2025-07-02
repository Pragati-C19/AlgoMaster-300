import java.util.*;

public class MaxProfit {
    
    public int maxProfit(int[] prices) {
        
        // Declare variables
        int n = prices.length;      // number of days
        int[] holdDP = new int[n];  // it will give max profit if we hold stock of day i
        int[] soldDP = new int[n];  // it will give max profit if we sold stock of day i
        int[] restDP = new int[n];  // it will give max profit if we rest on day i


        // Initial values for DP
        holdDP[0] = -prices[0];     // if we buy stock on day 0, we need to spend prices[0] money
        soldDP[0] = 0;              // we cannot sell at day 0, bcoz as per que before selling we need to buy
        restDP[0] = 0;              // means we are not getting money nor lossing money

        System.out.println("  hold : " + Arrays.toString(holdDP));
        System.out.println("  sold : " + Arrays.toString(soldDP));
        System.out.println("  rest : " + Arrays.toString(restDP));


        // Check other days now
        for (int i = 1; i < n; i++) {
            
            // check if we hold today what will be the maxProfit
            holdDP[i] = Math.max( holdDP[i-1], restDP[i-1] - prices[i] );


        }

        System.out.println("    - updated hold : " + Arrays.toString(holdDP));
        System.out.println("    - updated sold : " + Arrays.toString(soldDP));
        System.out.println("    - updated rest : " + Arrays.toString(restDP));

        return 0;
    }

    public static void main(String[] args){

        MaxProfit solution = new MaxProfit();

        int[] prices1 = {1,2,3,0,2};
        System.out.println(" Result1 -> " + solution.maxProfit(prices1) + "\n");    // 3

        int[] prices2 = {1};
        System.out.println(" Result2 -> " + solution.maxProfit(prices2) + "\n");    // 0
    
    }
}

/* 
 * Intuitions :
      
    1. We have given an array prices 
        where prices[i] is the price of gien stock on the ith day
    2. we need to find max Profit
    3. condition :
        - we can do multiple transactions (1 buy and 1 sell)
        - after we sell our stock, we cannot buy stock on next day 
            it will be cooldown day
        - we must sell stock before we buy again                                            
 
 
 * Pattern :
 
    1. We need to use 3 DP
        - hold : max profit if we holding a stock on day i
        - sold : max profit if we just sold a stock on day i
        - rest : max profit if we are resting on day i 

    2. How this states will work
        - Buy a Stock   ->  we are now in hold
        - Sell a Stock  ->  we are now in sold
        - Do Nothing    ->  we are now in rest
 
    3. Let's think of how to fill this dps
        ->  what is the best Profit I can have if I end up holding a stock on day i ?
                We have two options :
                    - We already held it yesterday (hold[i-1]), and do nothing today 
                    - We brought it today, means we were in rest state yesterday, then paid prices[i] to buy

                    hold[i] = max( hold[i-1], rest[i-1] - prices[i] )

                why minus of price? 
                    - bco we are spending money to buy stock 

        ->  whats the profit I get if I sell the stock today i ?
                - if we are selling today means we have held task yesterday
                    why? 
                        bcoz in que they said before selling stock we need to buy it
                        so yesterday we buy a stock, then today selling

                - and after we sell stock we are gaining price 

                    sold[i] = hold[i-1] + prices[i]

        ->  whats the profit if I rest today i ?
                We can do it in two ways
                    - we were resting yesterday and continue resting today too
                    - we sold stock yesterday, si as per que next day will be cooldown day

                rest[i] = max( rest[i-1], sold[i-1] )
                
        
    
 * Pseudo Code :
 



 */
 