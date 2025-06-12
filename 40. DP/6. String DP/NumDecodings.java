import java.util.*;

public class NumDecodings {
    
    public int numDecodings(String s) {
        
        // Declare variables
        int n = s.length();
        int[] dp = new int[n + 1];      // Strore ways to decode any string till currindex

        // Base Case : if first index of string is 0 not valid msg, if n = 0 there is nothing to decode
        if (n == 0 || s.charAt(0) == '0') {
            
            System.out.println(" String length is 0 or first char of string is 0... ");
            return 0;
        }
        
        // add initial values in dp
        dp[0] = 1;      // string is empty 
        dp[1] = 1;      // first char is not zero

        System.out.println("  Initial DP Array : " + Arrays.toString(dp));


        // Let's check remaining index from dp 
        for (int i = 2; i <= n; i++) {
            
            // get unit place digit and tenth place char
            // apan unit place and tenth place ghetoy fact mhnje kay ? aplyakde alphabates fact 1 to 26 ahet so 
            // we are using bottom up approach so out tenth place char is prev of unit placed
            char unitPlaceChar = s.charAt(i - 1);
            char tenthPlaceChar = s.charAt(i - 2);

            System.out.println("    - tenthPlaceChar : " + tenthPlaceChar + ", unitPlaceChar : " + unitPlaceChar);


            // Check single digit, it should be between 1 to 9, if it's 0 will skip this if
            if (unitPlaceChar != '0') {
                
                dp[i] += dp[i-1];
                System.out.println("    - unitPlacedChar(" + unitPlaceChar + ") is valid so adding this way in dp[" + i + "] : " + dp[i]); 
            }

            // Check two digit char
            // we are taking int bcoz apan 10 <= x <= 26 nahi karu shaknar string or char asel tr
            int twoDigitChar = Integer.parseInt(s.substring(i - 2, i));
            System.out.println("    [Testing] twoDigitChar : " + twoDigitChar);

            // now check condition for twoDigitChar
            if (twoDigitChar >= 10 && twoDigitChar <= 26) {
                
                dp[i] += dp[i-2];
                System.out.println("    - twoDigitChar(" + twoDigitChar + ") is valid so adding this way in dp[" + i + "] : " + dp[i]); 
            }

            System.out.println("  Updated DP Array : " + Arrays.toString(dp));

        }


        return dp[n];
    }

    public static void main (String[] args) {

        NumDecodings solution = new NumDecodings();

        String s1 = "12";
        System.out.println("Result1 -> " + solution.numDecodings(s1) + "\n");  // 2

        String s2 = "226";
        System.out.println("Result2 -> " + solution.numDecodings(s2) + "\n");  // 3

        String s3 = "06";
        System.out.println("Result3 -> " + solution.numDecodings(s3) + "\n");  // 0

        String s4 = "10";
        System.out.println("Result4 -> " + solution.numDecodings(s4) + "\n");  // 1

    }

}

/*

 ^ Imptovements :

    - int twoDigitChar = tenthPlaceChar + unitPlaceChar;  gives ans 100 for "226" test case 
        char '2' = 50 (in ASCII)
        So '2' + '2' = 50 + 50 = 100

    - to fix this we have two ways
        1. int twoDigitChar = (tenthPlaceChar - '0') * 10 + (unitPlaceChar - '0');
            - unitPlaceChar  = '2' = 50 (in ASCII)
            - tenthPlaceChar = '2' = 50 (in ASCII)
            - twoDigitChar = (50 - 48) * 10 + (50 - 48)
                                   (2 * 10) + 2 
                           = 22

        2. int twoDigitChar = Integer.parseInt(s.substring(i - 2, i));
            - it will directly gives u substring in int format 
            - which is 22 

    - Ans for s4 = "10" should be 1 but my code gives 2 let's check why ?
        - when I check my debugging statement I found this 
            unitPlacedChar(0) is valid so adding this way in dp[2] : 1
        - it should not check 0 na it's invalid then why checking now ?
            ohhhh I was checkign single digit not equal to 0 as int not char 
            so it should be '0' not only 0



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