import java.util.*;

public class CoinChange {
    
    // Driver Function 
    public int coinChange(int[] coins, int amount) {
        
        // Declare a dp with length equals to amount and add initial values as Max_Value
        int[] dp = new int[amount + 1];
        
        Arrays.fill(dp, Integer.MAX_VALUE);

        // amount 0 will be with zero coins so 
        dp[0] = 0;

        System.out.println("Initial DP Array : " + Arrays.toString(dp));

        // let's check if we can make other amounts with given coins
        for (int currAmt = 1; currAmt <= amount; currAmt++) {
            for (int coin : coins) {
                
                // check if coin is less than currAmt -> we can't reach that currAmt with coin bigger than it 
                if (coin <= currAmt && dp[currAmt - coin] != Integer.MAX_VALUE) {
                    
                    // if we use this coin then the coins count will be as below
                    int minCoinsIfUsedThisCoin = 1 + dp[currAmt - coin];
                    System.out.println("        coin = " + coin + "  -> 1 + dp[" + currAmt + " - " + coin + "] = " + minCoinsIfUsedThisCoin); 

                    // if more than 1 coin can able to make currAmt we need min number of coins 
                    dp[currAmt] = Math.min(dp[currAmt], minCoinsIfUsedThisCoin);
                }
            }

            System.out.println("    - Updated DP Array : " + Arrays.toString(dp));
        }

        // return dp[amount] if we have any count else return -1
        
        return dp[amount];
    }

    public static void main(String[] args) {

        CoinChange solution = new CoinChange();

        int[] coins1 = {1,2,5};
        System.out.println("Result 1 -> " + solution.coinChange(coins1, 11) + "\n");    // 3

        int[] coins2 = {2};
        System.out.println("Result 2 -> " + solution.coinChange(coins2, 3) + "\n");    // -1
        
        int[] coins3 = {1};
        System.out.println("Result 3 -> " + solution.coinChange(coins3, 0) + "\n");    // 0

        // s1 = {3,6,8,16,20} s2 = {3,16,16,18}
        // int[] coins4 = {3,3,6,8,16,16,16,18,20};
        // System.out.println("Result 4 -> " + solution.coinChange(coins4) + "\n");    // true

        // int[] coins5 = {0, 0};
        // System.out.println("Result 5 -> " + solution.coinChange(coins5) + "\n");    // 0

        // int[] coins6 = {1,2,1,1};
        // System.out.println("Result 6 -> " + solution.coinChange(coins6) + "\n");    // 3

    }

}

/*

 ^ Improvement :

    1. We are getting dp[3] for 2nd test case as coin = 2  -> 1 + dp[3 - 2] = -2147483648
    2. why ? bcoz we are adding 1 in maxValue and it as 2147483647 is the max value and nothing is greater than that it automatically converts it into -2147483648
    3. To fix it will I have two things in mind :
        - wrote in if not only abt maxValue but abt -2147483648          
            but jr jast coins asel and again -2147483648 madhe + 1zala tr?
            mala exact number nahi mahit like Max_Value asa mahitiye to 
        - to avoid above problem
            apan if add karu ki 
            dp[amt - coin] != Integer.Max_Value asel trch apan pudhe dp[amt] madhe add karnyavr focus karu
            
            
    
 * Intuitions :
    
    1. we have given integer array coins representing coins of diff denominations 
    2. we have amount array represents total amount of money 
    3. retrun the fewest number of coins that u need to make up that amount
        - example we have coins [1, 2, 5] and our amount is 11
        - then we need extra 3 coins right? to get 11 so will return 3
        - ata tu mhnshil what abt combination? 3 numbercha coin tr nahiye aplyakde mg?
        - in que they said we have infinite coins of number given in coins array
        - so will use 2 5's coins and 1 1's coin -> 1 + 5 + 5 = 11
        - will not use 2 this time
    4. if amount of money cannot be made by combinations of coins 
        - return -1
        - example we have coins [2] and we need amount as 3
        - but here we can't combine coins to make 3 if we say 2 + 2 it will be 4 not 3
        - I know tu mhnshil 2 + 1 karaych simple.. but naa aplyakde ek pn coin 1 nahiye onoy 2 ahet
 

 
 * Pattern :
 
    1. let's declare a dp of length amount + 1
        - why? karan mala titki amount haviye coins gheun
        - me totalSum kadhaycha vichar kelela.. but ti sum kami asel amount pekshya ani apan kadhich amount true nahi karu shaknar

    2. dp[0] = 0
        - why? karan amount 0 asnyasathi aplyala zero coins ch lagtil for each test case

    3. check for other smounts now and tell if that amount is possible by this nums or not
        for(amt = 1 to amount)
            for(coin : coins) 

    4. check if coin <= amt ? 
        - why? apan check kartoy jr curramt pekshya coin motha asel tr we can't achive to that curramt by that coin
            tyamul to motha coin skip kartey
        - if yes then add 1 + dp[amt - coin]
            why? let's take exmaple
                - We're at amount = 7    Coin = 3
                - You already know the minimum coins to form amount = 4 is dp[4] = 2
                - If we add this coin (3) to the way of forming amount = 4,
                    you'll reach 7 using 1 (this coin) + dp[4] = 3 coins total.
 
    5. let's visiualize it more
        - take example ->  coins: [1, 2, 5]    Amount: 7
        
        - let's build dp[amount+1]
        - dp[0] = 0
        
        - so our dp looks like
            index:  0  1  2  3  4  5  6  7
            value: [0, ∞, ∞, ∞, ∞, ∞, ∞, ∞]

        - let's loop through amount
            
            1. amt = 1
                coin = 1 -> 1 + dp[1-1] = 1
                coin 2 and 5 are big
                
                dp[1] = 1

            2. amt = 2
                coin = 1 -> 1 + dp[2-1] = 2
                coin = 2 -> 1 + dp[2-2] = 1
                coin 5 is big
                - now see we can use two different coins now how should I know which one to take?
                    coin 1 = 2  or coin 2 = 1?
                    it's simple in que they said fewest number of coins 
                - tyamul apan min gheu 
                    which is coin 2 = 1 now
                
                dp[2] = 1

            3. amt = 3
                coin = 1 -> 1 + dp[3-1] = 2
                coin = 2 -> 1 + dp[3-2] = 2
                coin 5 is big
                use same logic as above
                min between both coins are 2 only

                dp[3] = 2

            4. amt = 4
                coin = 1 -> 1 + dp[4-1] = 3
                coin = 2 -> 1 + dp[4-2] = 2
                coin 5 is big
                use same logic as above
                min between both coins are 2

                dp[4] = 2

            5. amt = 5
                coin = 1 -> 1 + dp[5-1] = 3
                coin = 2 -> 1 + dp[5-2] = 3
                coin = 5 -> 1 + dp[5-5] = 1
                use same logic as above
                min between all coins are 1

                dp[5] = 1

            6. amt = 6
                coin = 1 -> 1 + dp[6-1] = 2
                coin = 2 -> 1 + dp[6-2] = 3
                coin = 5 -> 1 + dp[6-5] = 2
                use same logic as above
                min between all coins are 2

                dp[6] = 2

            7. amt = 7
                coin = 1 -> 1 + dp[7-1] = 3
                coin = 2 -> 1 + dp[7-2] = 2
                coin = 5 -> 1 + dp[7-5] = 2
                use same logic as above
                min between all coins are 2

                dp[7] = 2

        - return amount = 7 so dp[7] = 2


 * Pseudo Code :
 



 */