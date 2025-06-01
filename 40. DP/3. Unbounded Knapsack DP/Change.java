import java.util.*;

public class Change {
    
}

/*
 * Intuitions :
 
    1. we have given integer array coins representing coins of diff denominations 
    2. we have amount represents total amount of money 
    3. return the number of combinations that make up that amount
        - example we have coins [1, 2, 5] and our amount is 11
        - then we need extra 3 coins right? to get 11 so will return 3
        - ata tu mhnshil what abt combination? 3 numbercha coin tr nahiye aplyakde mg?
        - in que they said we have infinite coins of number given in coins array
        - so will use 2 5's coins and 1 1's coin -> 1 + 5 + 5 = 11
        - will not use 2 this time
    4. if amount of money cannot be made by combinations of coins 
        - return 0
        - example we have coins [2] and we need amount as 3
        - but here we can't combine coins to make 3 if we say 2 + 2 it will be 4 not 3
        - I know tu mhnshil 2 + 1 karaych simple.. but naa aplyakde ek pn coin 1 nahiye onoy 2 ahet
 
 
 
 * Pattern :
 
    1. let's declare a dp of length amount + 1
        - why? karan mala titki amount haviye coins gheun
        - will store count of ways we need to make that amount
    
    2. 2. dp[0] = 1
        - why? karan amount 0 asnyasathi aplyala 1 ways asel nehmi for each test case

    3. start a nested for loop
        for(coin : coins)
            for(amt = coin to amount)
        
        - why? bcoz we can use one oin multiple time as much we want as possible 
        - we don't want to check amt < coins so skiping it

    4. check if coin
        dp[amt] += dp[amt - coin]

    5. let's visiualize it more
         - take example ->  coins: [1, 2, 5]    Amount: 5
        
        - let's build dp[amount+1]
        - dp[0] = 0
        
        - so our dp looks like
            index:  0  1  2  3  4  5  
            value: [1, 0, 0, 0, 0, 0]

        - let's loop through amount
            
            1. coin = 1
                amt = 1 -> dp[1] + dp[1-1] = 0 + 1 = 1
                amt = 2 -> dp[2] + dp[2-1] = 0 + 1 = 1
                amt = 3 -> dp[3] + dp[3-1] = 0 + 1 = 1
                amt = 4 -> dp[4] + dp[4-1] = 0 + 1 = 1
                amt = 5 -> dp[5] + dp[5-1] = 0 + 1 = 1

                dp = [1, 1, 1, 1, 1, 1]

            2. coin = 2
                amt = 2 -> dp[2] + dp[2-2] = 1 + 1 = 2
                amt = 3 -> dp[3] + dp[3-2] = 1 + 1 = 2
                amt = 4 -> dp[4] + dp[4-2] = 1 + 2 = 3
                amt = 5 -> dp[5] + dp[5-2] = 1 + 2 = 3

                dp = [1, 1, 2, 2, 3, 3]

            3. coin = 5
                amt = 5 -> dp[5] + dp[5-5] = 3 + 1 = 4

                dp = [1, 1, 2, 2, 3, 4]


 
 * Pseudo Code :
 



 */