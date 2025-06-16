import java.util.*;

public class MinCut {
    
    // Driver Function 
    public int minCut(String s) {
        
        // Declare variables
        int n = s.length();
        int[] dp = new int[n];

        // Check all chars and see if we can cut it or not?
        for (int i = 0; i < n; i++) {
            
            // check if currSubString from 0 to i is palindrome or not 
            if (isPalindrome(s, 0, i)) {
                
                dp[i] = 0;
                System.out.println("    - s[0," + i + "] isPalindrome so dp[" + i + "] = 0");
            }
            else {

                // if we didn't find palindrome of 0 to i 
                // we need to check other starting points than 0 
                for (int j = 1; j <= i; j++) {
                    
                    if (isPalindrome(s, j, i)) {
                     
                        int minCutIfCutHere = dp[j - 1] + 1;  

                        // let's take minimum of all of cuts
                        dp[i] = Math.min(dp[i], minCutIfCutHere);
                        
                        System.out.println("    - i = " + i + ", j = " + j + " -> minCut if we cut here : " + minCutIfCutHere);
                    }  
                }
            }

            System.out.println(" Updated DP Array : " + Arrays.toString(dp));
        }

        return 0;
    }

    // Helper Function : to check if string is palindrome or not
    private boolean isPalindrome (String subString, int start, int end) {

        // will keep loop till end > start
        while (end > start) {
            
            // get char of start and end of the substring
            char startChar = subString.charAt(start);
            char endChar = subString.charAt(end);

            // if both chars are not equal then return false
            if (startChar != endChar) {
                
                System.out.println("    - startChar(" + startChar + ") and endChar(" + endChar + ") are not same...");
                return false;
            }

            // if both chars are same will increase start and decrease end
            start++;
            end--;

        }

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