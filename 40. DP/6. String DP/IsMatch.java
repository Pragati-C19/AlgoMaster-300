import java.util.*;

public class IsMatch {
    
    public boolean isMatch(String s, String p) {
        
        // Declare variables
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];

        // let's add initial value of dp
        dp[0][0] = true;

        // we need to fill first row as in where s is empty and p varies
        for (int j = 1; j <= n; j++) {
            
            // check if p has * or not if yes then add true
            if (p.charAt(j-1) == '*') {
                
                dp[0][j] = dp[0][j-1];
            }
        }
        System.out.println(" Initial DP Array : " + Arrays.deepToString(dp));


        // Check other remaining Chars in s and p strings 
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                
                // assinging value of strings
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);

                // check if curr char of p is equal or '?'
                if ((sChar == pChar) || (pChar == '?')) {
                    
                    dp[i][j] = dp[i-1][j-1];
                }
                else if (pChar == '*') {
                    
                    // check if curr char of p is equal to '*'
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
                else {

                    // if all above not happening add false in dp
                    dp[i][j] = false;
                }
            }

            System.out.println(" Updated DP Array : " + Arrays.deepToString(dp));
        }

        return dp[m][n];
    }

    public static void main (String[] args) {

        IsMatch solution = new IsMatch();

        String s1 = "aa";
        String p1 = "a";
        System.out.println("Result1 -> " + solution.isMatch(s1,p1) + "\n");  // false

        String s2 = "aa";
        String p2 = "*";
        System.out.println("Result2 -> " + solution.isMatch(s2,p2) + "\n");  // true

        String s3 = "cb";
        String p3 = "?a";
        System.out.println("Result3 -> " + solution.isMatch(s3,p3) + "\n");  // false
        
    }

}

/*
 * Intuitions :
 
    1. we have given input strings(s) and pattern(p)
    2. we need to implement wildcard pattern matching with support for 
        '?' : matches any single char
        '*' : matches any sequence of char (including the empty sequence)
    3. matching should cover entire input string  
    
 
 * Pattern :
 
    1. apan p chi value check karu 
        p cha index * asel tr will return true 
        p cha index vr ? asel tr will say s.charAt(index) = p.charAt(index) are matched

    2. if those both symbols are not there will check for each index of both strings    
    3. Base Cases :
        - Diiferent length of s and p
            - check if p has any '*' if yes then return true
            - otherwise return false

        - Same Length 
            - check each s.charAt(i) == p.charAt(i) if yes then add true in dp
            - if not then check 
                if s.charAt(i) == '?' if yes then add true in dp
                if s.charAt(i) == '*' if yes then directly return true
        
    4. ohk I cross checked my logic with gpt and got some clarity abt what p = "*" is
        - p = "*a" or p = "we*e" this are not valid matches with s = "" or any s even if s = "rpq"
            to understand this more 

            - asa samaj p ek censore word ahe s cha.. jasa tu pahil asel b*tch lihitat tasa
            - aplyala kay karaychy sagle alphabates match karaychet p che s sobt 
                jr * ala tr next matching alphabate parynt apan jitke pn alphabates yetil te match ahet mhnun 
                jr ? ala tr curr index match ahe asa mhanu
            - example bagh :
                s = "axbympcz"    p = "a*b*c*"

                'a' matches 'a'
                '*' matches 'x'
                'b' matches 'b'
                '*' matches 'y'
                    matches 'm'     -> as we don't get any c in s-string yet
                    matches 'p'     -> as we don't get any c in s-string yet
                'c' matches 'c'
                '*' matches 'z' 

                so this strings are matched, samajl? nahi tr gpt kr parat


        - will use boolean[][] dp 
            where row  = i = char of s
                  col = j = char of p
            dp[0][j] means s is empty and we are checking j'th index from p
        
 
 *  Pseudo Code :
 
    function isMatch (s, p) {
    
        -> Declare variables 
            m, n    -> length of strings
            dp      -> will store index of i = s and j = p

        -> Initial value of dp[0][0]
            if both strings are empty our ans should be true bcoz they are matched
            
            dp[0][0] = true

        -> Fill First row where s is empty and p can varies
            if s is empty will check if p = '*' or not 
            remmeber we need p = "*", "**", "***" and so on bcoz string is empty 

            for(j = 1 to n)
                if(p.charAt(j - 1) == "*")
                    dp[0][j] = dp[0][j-1]

        -> if string p is empty and s not 
            then will return false bcoz we don't have anything to match in p with s
            tyamul apan first col nahi check karnar

        -> check remaining chars 
            for(i = 0 to m)
                for(j = 0 to n)

                    - check if curr char is equal or '?'
                        if yes then add true or false according to prev char

                        dp[i][j] = dp[i-1][j-1]

                    - check if p's char is * 
                        if yes then '*' can be empty (dp[i][j-1]) or match one/more characters (dp[i-1][j])

                        dp[i][j] = dp[i][j-1] || dp[i-1][j]

                    - if none of the above conditions add false in dp


        - at the end return dp[m][n]

    }

 
 */