import java.util.*;

public class WordBreak {
    
    public boolean wordBreak(String s, List<String> wordDict) {
        
        // Declare variables
        int n = s.length();
        boolean[] dp = new boolean[n + 1];

        // Initially add true for dp[0] bcoz it's empty string
        dp[0] = true;
        System.out.println(" Initial DP Arrays : " + Arrays.toString(dp));

        // Check if we can split string
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                
                // get substring from (j, i)
                String substring = s.substring(j, i);
                System.out.println("    - substring[" + j + "," + i + "] = " + substring);

                // Check if we can split the string from 0 to j into dict word
                // and check if that substring is in wordDict or not
                if (dp[j] && wordDict.contains(substring)) {
                    
                    dp[i] = true;
                    System.out.println("        dp["+ j + "] = " + dp[j] + " and the substring is in wordDict so storing true for dp[" + i + "]");
                }
            }

            System.out.println(" Updated DP Arrays : " + Arrays.toString(dp) + "\n");
        }

        return true;
    }

    public static void main (String[] args) {

        WordBreak solution = new WordBreak();

        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet","code");
        System.out.println("Result1 -> " + solution.wordBreak(s1, wordDict1) + "\n");  // 2

        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple","pen");
        System.out.println("Result2 -> " + solution.wordBreak(s2, wordDict2) + "\n");  // 3

        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println("Result3 -> " + solution.wordBreak(s3, wordDict3) + "\n");  // 0

        // String s4 = "10";
        // System.out.println("Result4 -> " + solution.wordBreak(s4) + "\n");  // 1

    }

}

/*
 * Intuitions :
 
    1. we have given a string(s) and a dictornary of strings(wordDict)
    2. return true if s can be segmented into a space-seperated sequence of one or more dictionary words
        what does it means?
            example : s = "leetcode", wordDict = ["leet","code"]
                Return true because "leetcode" can be segmented as "leet code".
    3. we can use same word in dictionary multiple times
 
 * Pattern :
    
    1. In dp will store can we break the substring s[0.. i-1]
    2. will check substrings one by one
        example leetcode 
            i = 1 j = 4 -> leet check this substring in wordDict -> if found store true in dp
        will run nested loop as 
            for(i = 1 to n)
                for(j = 1 to j < i)
    3. we need to check dp[j] and if substring is there in dict or not
        if (dp[j] && wordDict.contains(s.substring(j, i)))

        - why dp[j] = true ?
            This means: can we split the string from 0 to j successfully into dictionary words?
            
        - why s.substring(j, i) ?
            This means: is the current piece (from j to i) a valid dictionary word?
        
        - What does it mean overall?
            We can split the full string s[0:i] into valid words if we can split it at some index j where:
            The first part s[0:j] is already solvable (i.e., dp[j] == true)
            The second part s[j:i] is in the dictionary
    

    3. Dry Run Example 

        1. First Example
               i =     0      1      2      3      4      5      6      7
               s = "   l      e      e      t      c      o      d      e   "
           dp[i] = [ false, false, false, false, false, false, false, false ]
        wordDict = ["leet","code"]
 
        - for dp[0] = true  -> empty string is always valid
        - check other index one by one

            i = 1
                j = 0 -> substring[0,1] = le -> not in dict
                j = 1 -> substring[1,1] = e  -> not in dict
                
            i = 2
                j = 0 -> substring[0,2] = lee -> not in dict
                j = 1 -> substring[1,2] = ee  -> not in dict
                j = 2 -> substring[2,2] = e   -> not in dict

            i = 3
                j = 0 -> substring[0,3] = leet -> Found in dict -> dp[3] = true
                j = 1 -> substring[1,3] = eet  -> not in dict
                j = 2 -> substring[2,3] = et   -> not in dict
                j = 3 -> substring[3,3] = t    -> not in dict

            dp[i] = [ true, false, false, true, false, false, false, false ]

            i = 4
                j = 0 -> substring[0,4] = leetc -> not in dict
                j = 1 -> substring[1,4] = eetc  -> not in dict
                j = 2 -> substring[2,4] = etc   -> not in dict
                j = 3 -> substring[3,4] = tc    -> not in dict
                j = 4 -> substring[4,4] = c     -> not in dict

            i = 5
                j = 0 -> substring[0,5] = leetco -> not in dict
                j = 1 -> substring[1,5] = eetco  -> not in dict
                j = 2 -> substring[2,5] = etco   -> not in dict
                j = 3 -> substring[3,5] = tco    -> not in dict
                j = 4 -> substring[4,5] = co     -> not in dict
                j = 5 -> substring[5,5] = o      -> not in dict

            i = 6
                j = 0 -> substring[0,6] = leetcod -> not in dict
                j = 1 -> substring[1,6] = eetcod  -> not in dict
                j = 2 -> substring[2,6] = etcod   -> not in dict
                j = 3 -> substring[3,6] = tcod    -> not in dict
                j = 4 -> substring[4,6] = cod     -> not in dict
                j = 5 -> substring[5,6] = od      -> not in dict
                j = 6 -> substring[6,6] = d       -> not in dict

            i = 7
                j = 0 -> substring[0,7] = leetcode -> not in dict
                j = 1 -> substring[1,7] = eetcode  -> not in dict
                j = 2 -> substring[2,7] = etcode   -> not in dict
                j = 3 -> substring[3,7] = tcode    -> not in dict
                j = 4 -> substring[4,7] = code     -> Found in dict -> dp[7] = true
                j = 5 -> substring[5,7] = ode      -> not in dict
                j = 6 -> substring[6,7] = de       -> not in dict
                j = 7 -> substring[7,7] = e        -> not in dict

            dp[i] = [ true, false, false, true, false, false, false, true ]

        - soo dp[n] last element is true so ans is true


        2. Check one more whose ans is false 
               i =     0      1      2      3      4      5      6      7      8
               s = "   c      a      t      s      a      n      d      o      g   "
           dp[i] = [ false, false, false, false, false, false, false, false, false ]
        wordDict = ["cats","dog","sand","and","cat"]
 
        - for dp[0] = true  -> empty string is always valid
        - check other index one by one

            i = 1

                j = 0 -> substring[0,1] = c  -> not in dict

            i = 2
                j = 0 -> substring[0,2] = ca -> not in dict
                j = 1 -> substring[1,2] = a  -> not in dict

            i = 3
                j = 0 -> substring[0,3] = cat -> Found in dict -> dp[3] = true
                j = 1 -> substring[1,3] = at  -> not in dict
                j = 2 -> substring[2,3] = t   -> not in dict

            dp[i] = [ true, false, false, true, false, false, false, false, false ]

            i = 4
                j = 0 -> substring[0,4] = cats -> Found in dict -> dp[4] = true
                j = 1 -> substring[1,4] = ats  -> not in dict
                j = 2 -> substring[2,4] = ts   -> not in dict
                j = 3 -> substring[3,4] = s    -> not in dict

            i = 5
                j = 0 -> substring[0,5] = catsa -> not in dict
                j = 1 -> substring[1,5] = atsa  -> not in dict
                j = 2 -> substring[2,5] = tsa   -> not in dict
                j = 3 -> substring[3,5] = sa    -> not in dict
                j = 4 -> substring[4,5] = a     -> not in dict

            i = 6
                j = 0 -> substring[0,6] = catsan -> not in dict
                j = 1 -> substring[1,6] = atsan  -> not in dict
                j = 2 -> substring[2,6] = tsan   -> not in dict
                j = 3 -> substring[3,6] = san    -> not in dict
                j = 4 -> substring[4,6] = an     -> not in dict
                j = 5 -> substring[5,6] = n      -> not in dict

            i = 7
                j = 0 -> substring[0,7] = catsand -> not in dict
                j = 1 -> substring[1,7] = atsand  -> not in dict
                j = 2 -> substring[2,7] = tsand   -> not in dict
                j = 3 -> substring[3,7] = sand    -> Found in dict -> dp[7] = true
                j = 4 -> substring[4,7] = and     -> Found in dict -> dp[7] = true
                j = 5 -> substring[5,7] = nd      -> not in dict
                j = 6 -> substring[6,7] = d       -> not in dict

            i = 8
                j = 0 -> substring[0,8] = catsando -> not in dict
                j = 1 -> substring[1,8] = atsando  -> not in dict
                j = 2 -> substring[2,8] = tsando   -> not in dict
                j = 3 -> substring[3,8] = sando    -> not in dict
                j = 4 -> substring[4,8] = ando     -> not in dict
                j = 5 -> substring[5,8] = ndo      -> not in dict
                j = 6 -> substring[6,8] = do       -> not in dict
                j = 7 -> substring[7,8] = o        -> not in dict

            i = 9
                j = 0 -> substring[0,9] = catsandog -> not in dict
                j = 1 -> substring[1,9] = atsandog  -> not in dict
                j = 2 -> substring[2,9] = tsandog   -> not in dict
                j = 3 -> substring[3,9] = sandog    -> not in dict
                j = 4 -> substring[4,9] = andog     -> not in dict
                j = 5 -> substring[5,9] = ndog      -> not in dict
                j = 6 -> substring[6,9] = dog       -> Found in dict -> dp[9] = true
                j = 7 -> substring[7,9] = og        -> not in dict
                j = 8 -> substring[8,9] = g         -> not in dict

            dp[i] = [ true, false, false, true, true, false, false, true, false, true ]

        - Since dp[9] == true, the string "catsandog" can be broken into valid words...
            But hold on, this contradicts the problem

        - Actually, here’s where we must check dp[j] too.
            The condition is:
                if (dp[j] && dict.contains(s.substring(j, i)))

                So even if "dog" is in the dict at s.substring(6, 9) 
                    — we only accept it if dp[6] == true

        - Check that:
            dp[6] == false       ->  So "dog" can't be used.
            Thus, dp[9] = false  ->  final result = false 



 
 * Pseudo Code :
 
 
 
 
 */