import java.util.*;

public class LongestPalindromeSubseq {
    
}

/*
 * Intuitions :
 
    1. We have given a string s
    2. need to find longest palindromic subsequence's length in s
    3. A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
    4. palindrom means if we read word backwards it should be same as forward
        example : eye
 
 * Pattern :
 
    1. I think aplyala jr baghaychy ki from front and from last word same ahe ka nahi
        apan i = 0 to n gheu and j = n to i gheu?

        example : bbbab    n = 5

        - i = 1 = b
            j = 5 = b  -> same will add this in subsequence       -> 1 + dp[1-1][5-1] = 1 + 0 = 1
            j = 4 = a  -> different so skip one char from string  -> max(dp[1-1][4], dp[1][4-1]) = 0
            j = 3 = b  -> same will add this in subsequence       -> 1 + dp[1-1][3-1] = 1 + 0 = 1  
            j = 2 = b  -> same will add this in subsequence       -> 1 + dp[1-1][5-1] = 1 + 0 = 1
            j = 1 = b  -> same will add this in subsequence       -> 1 + dp[1-1][5-1] = 1 + 0 = 1
            
            same asel tr me currChar ghetey mazya subsequence madhe by 1 + dp[i-1][j-1] 
            diff asel tr 
                me max ghetey:
                    Skipping one character from the first string    -> dp[i-1][j]
                    Skipping one character from the second string   -> dp[i][j-1]

        - asach me each i check karun baghel
 
 
 * Pseudo Code :
 

    function longestPalindrome (s) {
    
        -> declare variables
            n       -> length of string
            dp      -> to store subsequence till currIndex
    
        -> we need a reverse string of s 
            me vichar kela hota direct n-1 karaych but tyat problem yeil 
            karan jr me [4,5] check karat asel tr me [4,4] check kelel nasel tevha tr tithe 0 asel tyani problem hoil.. maybe wrong values on yetil
            still code lihitanna me n-1 karun baghel ekda just to check if it works or not

            String reverserS = s.reverse()

        -> check all index of both strings and add in subsequence
            for(indexS = 0 to n)
                for(indexR = n-1 to i)  or for(indexR = 0 to n)

                    - if both char's are same
                        dp[i][j] = 1 + dp[i-1][j-1]

                    - if both char's are different
                        dp[i][j] = max(dp[i-1][j], dp[i][j-1])


        -> at the end return dp[n][n]
        
    }


 */