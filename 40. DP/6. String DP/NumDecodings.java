import java.util.*;

public class NumDecodings {
    
    public int numDecodings(String s) {
        
        // Declare variables
        int n = s.length();
        int[] dp = new int[n];      // Strore ways to decode any string till currindex

        // Base Case : if first index of string is 0 not valid msg, if n = 0 there is nothing to decode
        if (n == 0 || s.charAt(0) == '0') {
            
            System.out.println(" String length is 0 or first char of string is 0... ");
            return 0;
        }
        
        // add initial values in dp
        dp[0] = 1;      // string is empty 
        dp[1] = 1;      // first char is not zero

        System.out.println("  Initial DP Array : " + Arrays.toString(dp));




        return dp[n - 1];
    }

    public static void main (String[] args) {

        NumDecodings solution = new NumDecodings();

        String s1 = "12";
        System.out.println("Result1 -> " + solution.numDecodings(s1) + "\n");  // 4

        String s2 = "226";
        System.out.println("Result2 -> " + solution.numDecodings(s2) + "\n");  // 2

        String s3 = "06";
        System.out.println("Result3 -> " + solution.numDecodings(s3) + "\n");  // 0

    }

}

/*
 * Intuitions :
 
    1. We have given a string with secrete msg encoded with numbers
    2. to decode it u need to follow below mapping
        1 - A
        2 - B
        3 - C
        ...
        26 - Z
    3. But here is the problem when we start decoding any msg we are getting many different ways
        For example, "11106" can be decoded into:
            
            - "AAJF" with the grouping (1, 1, 10, 6)
            - "KJF" with the grouping (11, 10, 6)
            - The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
 
    4. so we need to return number of ways we can decode the msg
    5. rememebr there are msg which we can't decode like "06" 
        so that time return 0
 
 
 * Pattern :
 
    1. ohk there is few thing I need to consider
        - mala first num of string zero nasla pahije mhnje "0234", "04664" like this, asel tr return 0
        - string length jr 0 asel means we don't have anything to decode so return 0 
    
        - dp madhe kay store karnar ?
            dp cha index asel length of string or index of string
            dp madhe store karnar kiti ways madhe apan ya currlength parynt yeu shakto

            so dp[0] means string length 0 ahe tr there is only 1 way to decode this 
               dp[1] means first char of the string tr there is only 1 way to decode this 
                        (this first char should not be 0)
            
        - ata bakiche index check karavya lagtil string chya
            for(i = 2 to n)

        - check single digits if they are not 0 then add it in dp[i]
        - then check secondDigit and concatinate it and check if it's >=10 && <=26
            bcoz we are checking for two digit numbered Alphabetes
            if we find it add it in dp


    ^ Trace Example :

        s = "2  1  6"
        i =  0  1  2  3
       dp =  0, 0, 0, 0  <-  Initial

        - will check if first char is not 0 ? or n = 0 
            it's not so checking next 

        - add inital values in dp for 0 and 1 indexed chars
            dp[0] = 1   ->  base case: empty string  
            dp[1] = 1   ->  '2' = 'B'

        - check remaining index from string 
                
            i = 2 looking at '1':
                - unitPlaceChar = charAt(2-1) = '1'   ->  valid as it's not 0      ->  dp[2] += dp[1] = 0 + 1 = 1
                - tenthPlaceChar = charAt(2-2) = '2'  ->  we need this onlhy to check twodigits
                - twoDigit = "21"                 ->  valid as 10 <= 21 <= 26  ->  dp[2] += dp[0] = 1 + 1 = 2
                
                so after adding those two valid ways dp[2] = 2
                dp = 1, 1, 2, 0

            i = 3 looking at '6':
                - unitPlaceChar = charAt(3-1) = '6'   ->  valid as it's not 0      ->  dp[3] += dp[2] = 0 + 2 = 1
                - tenthPlaceChar = charAt(3-2) = '1'  ->  we need this only to check twodigits
                - twoDigit = "16"                 ->  valid as 10 <= 16 <= 26  ->  dp[3] += dp[1] = 2 + 1 = 3 
                
                so after adding those two valid ways dp[3] = 3
                dp = 1, 1, 2, 3

            Final dp = [1, 1, 2, 3]
                - 3 ways to decode: "BBF", "BZ", "VF"

 
 
 * Pseudo Code :
 




 */