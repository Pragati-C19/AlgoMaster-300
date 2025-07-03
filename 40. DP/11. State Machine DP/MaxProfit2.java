import java.util.*;

public class MaxProfit2 {
    
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