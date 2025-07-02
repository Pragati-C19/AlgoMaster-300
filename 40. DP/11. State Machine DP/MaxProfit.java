import java.util.*;

public class MaxProfit {
    
    public int maxProfit(int[] prices) {
        
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
 