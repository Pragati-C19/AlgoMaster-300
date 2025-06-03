
import java.util.*;

public class MaxEnvelopes {
    
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
