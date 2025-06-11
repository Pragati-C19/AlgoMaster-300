import java.util.*;

public class LongestCommonSubsequence {
    
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