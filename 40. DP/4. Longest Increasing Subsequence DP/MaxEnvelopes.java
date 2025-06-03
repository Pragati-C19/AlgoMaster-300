
import java.util.*;

public class MaxEnvelopes {
    
    public int maxEnvelopes(int[][] envelopes) {
        
        // Declare Variables
        int n = envelopes.length;
        int maxLength = 1;
        int[] dp = new int[n];

        // Sort envelopes array
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        System.out.println(" Sorted Envelopes : " + Arrays.deepToString(envelopes));

        // Initally fill dp with value 1 bcoz every envelope itself is a Russian Doll length equals 1
        Arrays.fill(dp, 1);
        System.out.println(" Initial DP : " + Arrays.toString(dp));


        // Check all index one by one and see if we can fit any envolope in it
        for (int currIndex = 0; currIndex < n; currIndex++) {
            
            int currWeight = envelopes[currIndex][0];
            int currHeight = envelopes[currIndex][1];

            for (int prevIndex = 0; prevIndex < currIndex; prevIndex++) {
                
                int prevIndexWeight = envelopes[prevIndex][0];
                int prevIndexHeight = envelopes[prevIndex][1];

                if (prevIndexWeight < currWeight && prevIndexHeight < currHeight) {
                    
                    int ifWeTakeThisEnvelope = 1 + dp[prevIndex];

                    dp[currIndex] = Math.max(dp[currIndex], ifWeTakeThisEnvelope);
                    System.out.println("        - prevIndexWeight(" + prevIndexWeight + ") < currWeight(" + currWeight + ") && prevIndexHeight(" + prevIndexHeight + ") < currHeight(" + currHeight + ") so updated dp of " + currIndex);
                }
            } 

            // get maxLength from whole dp array
            maxLength = Math.max(maxLength, dp[currIndex]);

            System.out.println("    - Updated DP Array : " + Arrays.toString(dp) + "  |  with maxLength : " + maxLength);
        }

        return maxLength;
    }

    public static void main(String[] args) {

        MaxEnvelopes solution = new MaxEnvelopes();

        int[][] envelopes1 = {
            {5,4},
            {6,4},
            {6,7},
            {2,3}
        };
        System.out.println("Result 1 -> " + solution.maxEnvelopes(envelopes1) + "\n");    // 4

        int[][] envelopes2 = {
            {1,1},
            {1,1},
            {1,1}
        };
        System.out.println("Result 2 -> " + solution.maxEnvelopes(envelopes2) + "\n");    // 4
        
        // int[][] envelopes3 = {7,7,7,7,7,7,7};
        // System.out.println("Result 3 -> " + solution.maxEnvelopes(envelopes3) + "\n");    // 1

        // int[][] envelopes4 = {1,3,6,7,9,4,10,5,6};
        // System.out.println("Result 4 -> " + solution.maxEnvelopes(envelopes4) + "\n");    // 6

        // int[][] envelopes5 = {0};
        // System.out.println("Result 5 -> " + solution.maxEnvelopes(envelopes5) + "\n");    // 1

        // int[][] envelopes6 = {1,2,1,1};
        // System.out.println("Result 6 -> " + solution.maxEnvelopes(envelopes6) + "\n");    // 3

    }

}

/*
 * Intuitions :
 
    1. We have given 2D array of integers envelops 
        where envolope[i] = [width, height]
    2. one envolope can fit into another if and only if both width and height of one envolope are greater than other
    3. we need to return maximum number of envelopes you can Russian Doll
        - put one inside the other
    4. apla goal ahe sangan ki kiti number of envolopes me ekat ek ghalu shakte 
 
 * Pattern :
 
    ^ Trace Example :

        index       :     0       1       2       3
        envelopes   :   [5,4],  [6,4],  [6,7],  [2,3]
        
        - let's sort envelope array by weight and height
            index       :     0       1       2       3
            envelopes   :   [2,3],  [5,4],  [6,4],  [6,7]

        - let's start a dp and set value for dp[0]
            will store number of envolopes we can fit till the currIndex
            dp = [0, 0, 0, 0]

            I think individually at start Russian Doll length 1 tr aselch 
            jasa jasa aapn add karat jau tasa tas ati vadhel or kami hoil depends on w and height of other envelops
            dp = [1, 1, 1, 1]

        - set dp[0] = 1     
            at start ya envolope chi length 1 asel  

        - currEnvolope 1 -> weight = 5, height = 4
            will check all preIndex and then will see if we can fit them in curr

            preIndex = 0 -> currW(5) > preIndexW(2), currH(4) > preIndexH(3)   -> dp[currIndex] = 1 + dp[0] = 2
            
            dp = [1, 2, 1, 1]


        - currEnvolope 2 -> weight = 6, height = 4
            will check all preIndex and then will see if we can fit them in curr

            preIndex = 0 -> currW(6) > preIndexW(2), currH(4) > preIndexH(3)   -> dp[currIndex] = 1 + dp[0] = 2
            preIndex = 1 -> currW(6) > preIndexW(5), currH(4) = preIndexH(4)   -> we can't take this as currH = preIndexH
            
            dp = [1, 2, 2, 1]


        - currEnvolope 3 -> weight = 6, height = 7
            will check all preIndex and then will see if we can fit them in curr

            preIndex = 0 -> currW(6) > preIndexW(2), currH(7) > preIndexH(3)   -> dp[currIndex] = 1 + dp[0] = 2
            preIndex = 1 -> currW(6) > preIndexW(5), currH(7) > preIndexH(4)   -> dp[currIndex] = 1 + dp[1] = 3
            preIndex = 2 -> currW(6) = preIndexW(6), currH(7) > preIndexH(4)   -> we can't take this as currW = preIndexW
            
            dp = [1, 2, 2, 3]

        - dp[n] = 3

 
 * Pseudo Code :
 
    1. declare variables :
        - n     -> length of envelope array
        - maxLength     -> It will tell how many envelopes are added in curr evelope
        - dp            -> it will store length of Russian doll

    2. Sort envelope array 
    3. fill dp initially with 1 
    4. start nested for lopps
        for(currIndex = 1 to n)
            for(prevIndex = 0 to currIndex)
    5. will check if we can take currIndex's w and h are max than prevIndex's 
        if yes then will updated the dp[currIndex]
        
 
 */
