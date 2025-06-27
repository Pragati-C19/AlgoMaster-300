import java.util.*;

public class New21Game {
    
    public double new21Game(int n, int k, int maxPts) {
        
        // Declare variables
        double[] dp = new double[n + 1];
        double windowsSum = 0;      
        double result = 0;

        // Base case shortcut: if k is 0 (she never draws), or max possible score is always ≤ n
        if (k == 0 || n >= k + maxPts) {
            
            System.out.println("    Base case hit: Returning 1.0");
            return 1.0;
        }

        // intialize starting values for dp and windowsSum
        dp[0] = 1;
        windowsSum = dp[0];

        System.out.println(" DP Array : " + Arrays.toString(dp));
        System.out.println(" windowsSum : " + windowsSum);

        // Phase 1: Check all scores from 1 to n and add their probability in dp
        for (int i = 1; i <= n; i++) {
            
            // Probability to reach score i is avg of windowsSum and maxPts 
            dp[i] = windowsSum / maxPts;

            
            // Phase 2: if we draw (score < k), add dp[i] in windowsSum
            if (i < k) {
                
                System.out.println("   i < k so added dp[" + i + "] in windowsSum... ");
                windowsSum += dp[i];
            }


            // Phase 3: If we draw (score >= k), will add dp[i] in result
            if (i >= k) {
                
                System.out.println("   i >= k so added dp[" + i + "] in result... ");
                result += dp[i];
            }


            // Phase 4: Slide window, remove dp[i - maxPts] if it goes out of range
            if ((i - maxPts) >= 0) {
                
                System.out.println("  (i - maxPts) >= 0 so substract dp[" + (i - maxPts) + "] from windowsSum... ");
                windowsSum -= dp[i - maxPts];
            }

            // Debugger :
            System.out.println("  - i = " + i + " : ");
            System.out.println("      dp[" + i + "]     = " + dp[i]);
            System.out.println("      windowsSum = " + windowsSum);
            System.out.println("      result     = " + result + "\n");

        }

        System.out.println(" Updated DP Array : " + Arrays.toString(dp));

        
        // at the end return result value
        return result;
    }

    public static void main(String[] args) {

        New21Game solution = new New21Game();

        System.out.println(" Result 1 -> " + solution.new21Game(10, 1, 10) + "\n");     // 1.0
        System.out.println(" Result 2 -> " + solution.new21Game(6, 1, 10) + "\n" );     // 0.6
        System.out.println(" Result 3 -> " + solution.new21Game(21, 17, 10) + "\n" );   // 0.73278
        System.out.println(" Result 4 -> " + solution.new21Game(0, 0, 1) + "\n" );      // 1.0

    }

}

/*
 * Intuitions :
 
    1. Alice plays a card game "21"
    2. she starts with 0 points
    3. she will draw numbers till she has < k points
    4. during each draw gives random numbers 
        from range [1, maxPts]
    5. She will stop drawing once she reaches k or more points
    6. we need to return probability that Alice ends up with a score <= n
 
 
 * Pattern :
 
    1. Phase 1 is understanding the Game and Math behind it
        - Alice was randomly picking numbers from [1, maxPtr]
        - She stops when her score >= k
        - our ans = sum of probabilities of all scores from k to n
        - will use DP to store probability that alice reaches score i
    
    2. What does [k, k+1, ..., k + maxPts - 1] mean?
        - Alice stops drawing once her total points >= k
        - and each draw gives random numbers from 1 to maxPtr
        - so what are the possible score Alice can end up with?
        - Example :
            k = 5 and maxPts = 3
            Alice keep drawing till score <= 5
            let's say alice is at score 4 now so she draw next nums
                - She draws 1 -> total becomes 5 (stop)
                - She draws 2 -> total becomes 6 (stop)
                - She draws 3 -> total becomes 7 (stop)
            so from 4 she can stop with score 5, 6, 7 final scores 
        - like that from k we can go upto k + maxPts
        - so our maximum final score Alice can end up with if she draw any number at that point
            will be k, k+1, k+2,... k+maxPts

    3. What is windows sum ?
        - WindowSum is sum of probabilities of all prev values 
            windowSum = dp[i-1] + dp[i-2] + ... + dp[i-maxPts]

    2. Approach will Use 
        - Alice will always starts with score 0, so probability of getting score 0 is always 1
            dp[0] = 1

        - Will use sliding window 
            initially windowSum = dp[0]
            at each i :
                add dp[i] to windowsSum (if i < k)
                substract dp[i - maxPts] from windowsSum (when window slides past)

        - get all probabilities for remaining score and store it in dp
            for(currScore = 0 to n)

                if currScore < k  : means we can draw cards 
                    dp[i] = windowSum / maxPts

                if i >= k : means will not draw anymore
                    ans += dp[i]

    ^ Dry Run :

        n = 6       -  We want Alice to end with score ≤ 6
        k = 3       -  Alice stops drawing once her score is ≥ 3
        maxPts = 2  -  Each draw gives either 1 or 2 points, with equal probability

        - we need to return probability that final score <= 6 means
            probability that she stops at score 3,4,5,6

            dp[3] + dp[4] + dp[5] + dp[6]

        - Will declare a dp to store proabilities at i score
        - initially for dp[0] = 1
            bcoz she is starting at 0 means score zero we can get 100%

        - assign value to windows sum
            windows sum = sum of previous maxPts values used to calculate next dp[i]

            so initially we only check dp[0], hence
            windowsSum = dp[0]
        
        - let's fill dp from 1 to 6

            i = 1 
                dp[1]      =  windowsSum / maxPts  = 1 / 2    = 0.5
                windowsSum =   windowsSum + dp[1]  = 1 + 0.5  = 1.5

            i = 2
                dp[2]      =  windowsSum / maxPts  = 1.5 / 2     = 0.75
                windowsSum =   windowsSum + dp[2]  = 1.5 + 0.75  = 2.25

            i = 3
                i >= k  -> Yes 3 >= 3
                so will stop here after adding it's probability in dp

                dp[3]      =  windowsSum / maxPts  = 2.25 / 2      = 1.125
                windowsSum =   windowsSum + dp[3]  = 2.25 + 1.125  = 3.375

                now we need to substract dp[1] bcoz we are sliding this window of i to k 
                to i + 1 to k + 1

                windowsSum =   windowsSum - dp[1]  = 3.375 - 0.5  = 2.875

                result = 1.125


            i = 4
                Alice already reached k, so she is not srawing anything just collecting the probability 
                and storing it in dp

                dp[4]      =  windowsSum / maxPts  = 2.875 / 2       = 1.4375
                windowsSum =   windowsSum + dp[4]  = 2.875 + 1.4375  = 4.3125

                now we need to substract dp[2] bcoz we are sliding this window of i to k 
                to i + 1 to k + 1

                windowsSum =   windowsSum - dp[2]  = 4.3125 - 0.75  = 3.5625

                result = result + dp[4] = 1.125 + 1.4375 = 2.5625


            i = 5
                Alice already reached k, so she is not srawing anything just collecting the probability 
                and storing it in dp
            
                dp[5]      =  windowsSum / maxPts  = 3.5625 / 2        = 1.78125
                windowsSum =   windowsSum + dp[5]  = 3.5625 + 1.78125  = 5.34375

                now we need to substract dp[3] bcoz we are sliding this window of i to k 
                to i + 1 to k + 1

                windowsSum =   windowsSum - dp[3]  = 5.34375 - 1.125  = 4.21875

                result = result + dp[5] = 2.5625 + 1.78125 = 4.34375


            i = 6 
                Alice already reached k, so she is not srawing anything just collecting the probability 
                and storing it in dp

                dp[6]      =  windowsSum / maxPts  = 4.21875 / 2         = 2.109375
                windowsSum =   windowsSum + dp[3]  = 4.21875 + 2.109375  = 6.328125

                now we need to substract dp[1] bcoz we are sliding this window of i to k 
                to i + 1 to k + 1

                windowsSum =   windowsSum - dp[4]  = 6.328125 - 1.4375  = 4.890625

                result = result + dp[6] = 4.34375 + 2.109375 = 6.453125

            - as we don't have any more i now will take last result as end result
                so return 6.453125


 * Pseudo Code :
 
    function new21Game (n, k, maxPts) {
    
        -> Base Case :
            if k = 0 or n >= k + maxPts 
                return 1

        -> Phase 1 : create DP Array
            dp  = to store probability to get score i
            dp[0] = 1

        -> Phase 2 : maintain a sliding window sum of last maxPts dp values
            windowsSum = 1  - stating with dp[0]

        -> declare a variable result to get sum os all probabilities of score < k
            result = 0


        -> Phase 3 : check 1 to n values now and fill DP array
            for (i = 1 to n)
            
                - probability to reach score i is the avg of prev maxPts
                    dp[i] = windowsSum / maxPts

                - Phase 4 : if we draw (score < k), add dp[i] in windowsSum
                    if(i < k)
                        windowsSum += dp[i]

                - Phase 5: If we draw (score >= k), will add dp[i] in result
                    if(i >= k)
                        result += dp[i]
                        
                - Phase 6: Slide window, remove dp[i - maxPts] if it goes out of range
                    if(i - maxPts >= 0) 
                        windowsSum -= dp[i - maxPts]

        -> At the end return result

    }


 */