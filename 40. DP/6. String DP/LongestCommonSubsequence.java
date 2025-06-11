import java.util.*;

public class LongestCommonSubsequence {
    
    public int longestCommonSubsequence(String text1, String text2) {
        
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];     // it stores length of longest common subsequnce till the currChar
        int count = 0;

        // Fill first row
        // think of it like i = index[text1] = 0 
        // means ekach text1 ch first letter we need to check with other letter of text2 
        // if it's not common just keep 0 
        for (int j = 0; j < n; j++) {
            
            if(text1.charAt(0) == text2.charAt(j)) {

                dp[0][j] = 1;
                System.out.println("    - ch1 : " + text1.charAt(0) + ", ch2 : " + text2.charAt(j) + " are common.. ");
            }
        
        }

        // Fill first column
        // think of it like j = index[word2] = 0 
        // means ekach text2 ch first letter we need to check with other letter of text1 
        // if it's not common just keep 0 
        for (int i = 0; i < m; i++) {
            
            if(text1.charAt(i) == text2.charAt(0)) {

                dp[i][0] = 1;
                System.out.println("    - ch1 : " + text1.charAt(i) + ", ch2 : " + text2.charAt(0) + " are common.. ");
            }
        
        }
        System.out.println(" Initial DP Array : " + Arrays.deepToString(dp));


        // Check remaining words 
        for(int currChar = 1; currChar <= m; currChar++) {
            for(int prevChar = 1; prevChar <= n; prevChar++) {
                
                char ch1 = text1.charAt(currChar - 1);
                char ch2 = text2.charAt(prevChar - 1);

                // System.out.println("   ch1 : " + ch1 + ", ch2 : " + ch2);

                // if both chars are common add it in subsequence list
                if(ch1 == ch2) {

                    dp[currChar][prevChar] = 1 + dp[currChar - 1][prevChar - 1];
                    System.out.println("    - ch1 : " + ch1 + ", ch2 : " + ch2 + " are Common " + dp[currChar][prevChar]);
                }
                else {

                    // if both char don't match will drop one char from text1 or text2
                    // to see which one gives longest subsequence will be drop 1 char from both and then jyacha max asel apan to gheu  

                    int charDropFromText1 = dp[currChar-1][prevChar];
                    int charDropFromText2 = dp[currChar][prevChar-1];

                    dp[currChar][prevChar] = Math.max(charDropFromText1, charDropFromText2);

                    System.out.println("    - ch1 : " + ch1 + ", ch2 : " + ch2 + " are Different " + dp[currChar][prevChar]);
                }
            }

            System.out.println("      Updated DP Array : " + Arrays.deepToString(dp));
        }

        return count;
    }

    public static void main (String[] args) {

        LongestCommonSubsequence solution = new LongestCommonSubsequence();

        String text11 = "abcde";
        String text21 = "ace";
        System.out.println("Result1 -> " + solution.longestCommonSubsequence(text11, text21) + "\n");    // 3

        String text12 = "abc";
        String text22 = "abc";
        System.out.println("Result2 -> " + solution.longestCommonSubsequence(text12, text22) + "\n");    // 3

        String text13 = "abc";
        String text23 = "def";
        System.out.println("Result3 -> " + solution.longestCommonSubsequence(text13, text23) + "\n");    // 0

        String text14 = "ezupkr";
        String text24 = "ubmrapg";
        System.out.println("Result4 -> " + solution.longestCommonSubsequence(text14, text24) + "\n");    // 2

    }

}

/*
 * Intuitions :
 
    1. We have given two strings text1 and text2
    2. return there length of longest common subsequence
        if there is none return 0
    3. A subsequence of a string is a new string generated from the original string 
        with some characters (can be none) deleted without changing the relative order of the remaining characters
        means ? 
            example "ace" is a subsequence of "abcde"
    4. A common subsequence of two strings is a subsequence that is common to both strings.

 
 * Pattern :
 
    1. Brute force 
        apan one by one check karu char same ahet ka?
        asel tr count increase karu
    
    2. let's think of dp now
        we are using bottom up approach in dp that means 2D array
        if we find common char will just store it in dp
        
    3. The test cases was wrong after submitting brute force
        text1 = "ubmrapg"    text2 = "ezupkr"

        - the problem here is I'm checking same char again 
            it should not check char backward 
            as in bagh me jevha pn text1 compare kartey text 2 sobt 
            tevha text 2 madhe jar to letter yeun gela asel adhich I should be skipping it

            we start with 
                i = 0 = u
                    j = 2 = u  -> so add dp[0][2]  
                i = 1 = b
                    we check all j but nothing is there to match so check next
                i = 2 = m
                    we check all j but nothing is there to match so check next
                i = 3 = r
                    j = 5 = r  -> so add dp[0][5]
                i = 4 = a
                    we check all j but nothing is there to match so check next
                i = 5 = p
                    j = 3 = p  -> so add dp[0][3]
                    but I should br skipping it.. karan me already r common kadhlay so 
                    from text1 and text2 we got ur so far
                    
                    jr me p check karayla gele tr text1 madhe possible ahe te
                    but text2 madhe me reverse tr nahi jau shakat na p sathi right?
        
                i = 6 = g
                    we check all j but nothing is there to match so check next
                    

                        e   z   u   p   k   r
                    u   0   0   1   0   0   0                                                      
                    b   0   0   0   0   0   0                                           
                    m   0   0   0   0   0   0                                           
                    r   0   0   0   0   0   1                                                                 
                    a   0   0   0   0   0   0                                           
                    p   0   0   0   1   0   0                                                         
                    g   0   0   0   0   0   0                                           

        - so I think I have to check j = i to n instead of checking all letters let me check
            I can't do it.. it gives error when text1's length is > than text2's length
            
        - let's try (ch1 == ch2 && i > j) will skip it
            it didin't work either 

        - think think how will u solve this issue
            counts every match, even if:
                The match is out of sequence
                It reuses characters in text2 that have already been used in an earlier match

            ohk let's think of how will we add value in dp karan dp madhe apan 0 and 1 tr nahi thevnar ahe
                dp means finding min or max path from multiple paths

            so if we saw same chars we can say that will take this in our subsequence
                currLength = currChar + prevLength
                dp[i][j] = 1 + dp[i-1][j-1]

            if char don't match 
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);

                What it means:
                    If current characters don't match, we have two options:
                        - Skip the current character in text1   ->  dp[i-1][j]
                        - Skip the current character in text2   ->  dp[i][j-1]

                Take the maximum of these two options because you want the best LCS possible.
                Think of it like:
                    If these characters don't match, I have to drop one of them. Let me try both options and pick the better LCS


 
 * Pseudo Code :
 
    1. Brute force : 13/47 test cases were right

        public int longestCommonSubsequence(String text1, String text2) {
        
            int count = 0;

            for(char ch1 : text1.toCharArray()) {
                for(char ch2 : text2.toCharArray()) {

                    if(ch1 == ch2) {
                        count++;
                    }
                }
            }

            return count;
        }


 */