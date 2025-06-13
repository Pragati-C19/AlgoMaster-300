import java.util.*;

public class IsInterleave {
    
    public boolean isInterleave(String s1, String s2, String s3) {
        
        // Declare variables
        int m = s1.length();
        int n = s2.length();
        int o = s3.length();
        boolean[][] dp = new boolean[m + 1][n + 1];


        // Base Case :
        if (m + n != o) {
            
            System.out.println(" sum of lengths of s1(" + m + ") and s2(" + n + ") are not equal to length of s3(" + o + ")");
            return false;
        }


        // Assign initial value in dp : Empty strings s1 and s2 can form empty s3
        dp[0][0] = true;
        // System.out.println(" Initial DP Arrays : " + Arrays.deepToString(dp));


        // fill first Row 
        // only chars from s2
        for (int j = 1; j < n; j++) {
            
            dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
        }
        System.out.println("    - first row | DP Arrays : " + Arrays.deepToString(dp));

        // fill first Col 
        // only chars from s1
        for (int i = 1; i < m; i++) {
            
            dp[i][0] = dp[i-1][0] && s2.charAt(i-1) == s3.charAt(i-1);
        }
        System.out.println("    - first col | DP Arrays : " + Arrays.deepToString(dp));


        // check remaning chars
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                
                // will take either char from s1 or char from s2
                boolean charFromS1 = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1);
                boolean charFromS2 = dp[i][j-1] && s2.charAt(i-1) == s3.charAt(i+j-1);

                dp[i][j] = charFromS1 || charFromS2;

            }
        }

        return dp[m][n];
    }

    public static void main (String[] args) {

        IsInterleave solution = new IsInterleave();

        String s11 = "aabcc";
        String s21 = "dbbca";
        String s31 = "aadbbcbcac";
        System.out.println("Result1 -> " + solution.isInterleave(s11, s21, s31) + "\n");  // true

        String s12 = "aabcc";
        String s22 = "dbbca";
        String s32 = "aadbbbaccc";
        System.out.println("Result2 -> " + solution.isInterleave(s12, s22, s32) + "\n");  // false

        String s13 = "";
        String s23 = "";
        String s33 = "";
        System.out.println("Result3 -> " + solution.isInterleave(s13, s23, s33) + "\n");  // true
        
    }

}

/*
 * Intuitions :
 
    1. We have given 3 strings s1, s2, s3
    2. we need to find whether s3 is formed by an interleaving of s1 and s2
    3. interleaving of string means s and t is a configuration 
        where s and t are divided into n and m substrings respectively
        example : s = s1 + s2 + s3 + ... + sn
                  t = t1 + t2 + t3 + ... + tm
                  |n - m| <= 1
                  so the interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
    4. a + b is the cncatenation of strings a and b
    5. I don't understand what is this que want exactly
 
 
 * Pattern :
 
    1. mala itka tr samjtay ki s3 madhun aplyala one by one check karaych ahe
        apan ji substring gheu ti s3 madhli asel like
            s3.substring(j, i)

    2. then apan tya substring la check karu s1 madhe ahe ka or s2 madhe ahe ka te
    3. took help from gpt and wrote this code

 
 * Pseudo Code :
 
    function isInterleave(s1, s2, s3) {
    
        -> Declare variables
            m, n                -> length of s1 and s2 respectively
            boolean[][] dp      -> it will store all possible ways to form s3[0.. i+j-1] using s1[0..i-1] and s2[0..j-1]
    

        -> aplyala s1 and s2 che sagle chars use karaychet so
            s1.length + s2.length == s3.length asel nehmi 
            jr nasel te tr adhich return false karun de


        -> Define initial value in dp
            empty strings s1 and s2 can form empty s3
            dp[0][0] = true


        -> Will initialize first row 
            only char from s2 are used
            for(j = 1 to n)
                dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1)


        -> Will initialize first col 
            only char from s1 are used
            for(i = 1 to m)
                dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1)


        -> Check Remaining chars
            for(i = 1 to m)
                for(j = 1 to n)

                    - will take either char from s1 or char from s2
                        charFromS1 = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)
                        charFromS2 = dp[i][j-1] && s2.charAt(i-1) == s3.charAt(i+j-1)

                    - dp[i][j] = charFromS1 || charFromS2


        -> Final answer: can s3 be built from all of s1 and s2?
            dp[m][n]

    }


 */