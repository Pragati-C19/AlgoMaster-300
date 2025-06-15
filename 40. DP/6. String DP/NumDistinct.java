import java.util.*;

public class NumDistinct {
    
    public int numDistinct(String s, String t) {
        
        // Declare variables
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];


        // Add initial value in dp
        dp[0][0] = 1;    // both string are empty so there is 1 way to match them
        
        // Check t is empty we can create t from s with only 1 way
        for (int i = 1; i <= m; i++) {
            
            dp[i][0] = 1;
        } 
        System.out.println(" Initial DP Array : " + Arrays.deepToString(dp));



        return 0;
    }

    public static void main (String[] args) {

        NumDistinct solution = new NumDistinct();

        String s1 = "rabbbit";
        String t1 = "rabbit";
        System.out.println("Result1 -> " + solution.numDistinct(s1,t1) + "\n");  // 3

        String s2 = "babgbag";
        String t2 = "bag";
        System.out.println("Result2 -> " + solution.numDistinct(s2,t2) + "\n");  // 5

        // String s3 = "cb";
        // String t3 = "?a";
        // System.out.println("Result3 -> " + solution.numDistinct(s3,t3) + "\n");  // false
        
    }

}

/*
 * Intuitions :
 
    1. we have given string s and t 
    2. we need to return distinct subsequence of s which equal to t
    3. A subsequence of a string is a new string generated from the original string 
        with some characters (can be none) deleted without changing the relative order of the remaining characters
        means ? 
            example "ace" is a subsequence of "abcde"
        
    4. mala how many ways to create t sangaychay but in same order

   
 * Pattern :
 
    1. Example :

        s = "rabbbit"   t = "rabbit"

        We can form t = "rabbit" 3 different ways from s.
            - ra[b]b[b]it
            - ra[b]bb[i]t
            - rabb[b]i[t]
        Each valid way = 1 distinct subsequence.

    2. We'll use Dynamic Programming
        - dp[i][j] = number of ways to form first j chars of t using first i chars of s

        - Base Cases:
            dp[0][0] = 1 -> 1 way to match empty t with empty s
            dp[i][0] = 1 for all i -> there's always 1 way to form empty t: by deleting all chars in s
            dp[0][j] = 0 for j > 0 -> can't form non-empty t from empty s

    3. For remaining chars
        
        At dp[i][j]:
            If s[i-1] == t[j-1] -> we have 2 choices:
                - include this char: dp[i-1][j-1]
                - skip this char: dp[i-1][j]
                    so, dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
            Else (chars don’t match):
                we can only skip the char from s
                    so, dp[i][j] = dp[i-1][j]

    5. took help from gpt and videos   
        I understood the que but codding it was hard for me.. speacially thiss al of confusions abt i-1 j-1 and all kinda things
    
 
 *  Pseudo Code :
 
    
 
 */