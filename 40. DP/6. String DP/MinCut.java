import java.util.*;

public class MinCut {
    
    // Driver Function 
    public int minCut(String s) {
        
        return 0;
    }

    // Helper Function : to check if string is palindrome or not
    private boolean isPalindrome (String subString, int start, int end) {

        return true;
    }
    

    public static void main (String[] args) {

        MinCut solution = new MinCut();

        String s1 = "aab";
        System.out.println("Result1 -> " + solution.minCut(s1) + "\n");  // 1

        String s2 = "a";
        System.out.println("Result2 -> " + solution.minCut(s2) + "\n");  // 0

        String s3 = "ab";
        System.out.println("Result3 -> " + solution.minCut(s3) + "\n");  // 1
        
    }

}

/*
 * Intuitions :
 
    1. we have given a string s
    2. we need to do partition of s such that every substring of the partition is a palindrome.
    3. Return the minimum cuts needed for a palindrome partitioning of s.
        cuts as in "aab" will have two partitions "aa", "b" so there is only 1 cut
    4. we need min Number of cuts
    
 
 
 * Pattern :
 
    1. will declare a dp which stores min cuts need to cut substring[0..i]
    2. if string is already palindrome we don't need to cut string at all
        if(isPalindrome(s))
            dp[i] = 0
    3. Will create a function isPalindrome to check if the curr substring is palindrome or not
    4. if substring is not palindrome will try all chars from substring 
        for(j = 0 to i)

            - if this string[j...i] is palindrome 
                we can say it's out potential substring we can cut here 
                dp[j-1] + 1

            - then take min of all those cutss
                dp[i] = min(dp[i], dp[j-1]+1)

    5. isPalindrome function :
        will check start and end
        will keep loop till end > start
            
            if both chars are not same then return false
            else
                do start++ and end--

        at the end return true
 
 
 
 * Pseudo Code :
 
 


 
 */