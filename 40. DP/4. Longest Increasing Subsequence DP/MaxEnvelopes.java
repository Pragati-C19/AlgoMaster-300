
\]vygtfrdeswimport java.util.*;

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
        

        - let's start a dp and set value for dp[0]
            will store number of envolopes we can fit till the currIndex
            dp = [0, 0, 0, 0]

            I think individually at start Russian Doll length 1 tr aselch 
            jasa jasa aapn add karat jau tasa tas ati vadhel or kami hoil depends on w and height of other envelops
            dp = [1, 1, 1, 1]

        - set dp[0] = 1     
            at start ya envolope chi length 1 asel  

        - currEnvolope 1 -> weight = 6, height = 4
            will check all index and then will see if we can fit them in curr

            index = 0 -> currW(6) > indexW(5), currH(4) = indexH(4)   -> we can't take this as currH = indexH
            index = 2 -> currW(6) = indexW(6), currH(4) < indexH(7)   -> we can't take this as currW = indexW
            index = 3 -> currW(6) > indexW(2), currH(4) > indexH(3)   -> both weight and size is small of this index so dp[currEnvolope]++

            dp = [1, 2, 1, 1]

        - currEnvolope 2 -> weight = 6, height = 7
            will check all index and then will see if we can fit them in curr

            index = 0 -> currW(6) > indexW(5), currH(7) > indexH(4)   -> dp[currEnvolope] = 1 + 2 = 2
            index = 1 -> currW(6) = indexW(6), currH(7) > indexH(4)   -> we can't take this as currW = indexW
            index = 3 -> currW(6) > indexW(2), currH(7) > indexH(3)   -> both weight and size is small of this index so dp[currEnvolope]++

            dp = [1, 2, 1, 1]



 
 * Pseudo Code :
 
 


 
 */-+
