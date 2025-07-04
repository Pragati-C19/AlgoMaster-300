import java.util.*;

public class ShortestPalindrome {
    
    // Driver Function
    public String shortestPalindrome(String s) {
        
        // Declare variables
        int n = s.length();
        String prefix = "";

        // Base Case : if s is already a palindrome return s
        if (isPalindrome(s)) {
            
            System.out.println(" String s is already palindrome no need to change anything.. ");
            return s;
        }

        // Try adding chars in front of s to get palindrome
        for (int i = n - 1; i >= 0; i--) {
            
            // char at i
            char currChar = s.charAt(i);

            // prefix we have added so far
            prefix = prefix + currChar;

            // Add that currChar in front of s
            String newS = prefix + s;
            
            System.out.println("    - i = " + i + "\n       currChar : " + currChar + " | prefix : " + prefix + " | new String : " + newS);

            // Now check if it's palindrome, if yes return the string
            if (isPalindrome(newS)) {
            
                System.out.println(" String '" + newS + "' is palindrome break here.. ");

                return newS;
            }

        }


        // if we didin't get any palindrome return empty string
        return "";
    }

    // Helper Function : check if string is palindrome or not
    private boolean isPalindrome(String currString) {

        int start = 0;
        int end = currString.length() - 1;

        // will keep loop till end > start
        while (end > start) {
            
            // get char of start and end of the currString
            char startChar = currString.charAt(start);
            char endChar = currString.charAt(end);

            // if both chars are not equal then return false
            if (startChar != endChar) {
                
                System.out.println("       currString : " + currString + " is palindrome \n");
                return false;
            }

            // if both chars are same will increase start and decrease end
            start++;
            end--;

        }

    
        System.out.println("       currString : " + currString + " is palindrome \n");
        return true;
    }


    public static void main(String[] args) {

        ShortestPalindrome solution = new ShortestPalindrome();

        String s1 = "aacecaaa";
        System.out.println("Result1 -> " + solution.shortestPalindrome(s1) + "\n");    // aaacecaaa

        String s2 = "abcd";
        System.out.println("Result2 -> " + solution.shortestPalindrome(s2) + "\n");    // dcbabcd

        String s3 = "abbacd";
        System.out.println("Result3 -> " + solution.shortestPalindrome(s3) + "\n");    // dcabbacd

    }

}

/*
 * Intuitions :
 
    1. We have given a string s
    2. we can convert s to palindrome by adding a char in front of it
        means if we has s = "abc" then will add
        - b infront of a  -> "babc"
        - c infront of b  -> "cbabc"
    3. we need to return shortest palindrome we can find 
 
 
 * Pattern :
 
    1. First think of how to add second word in front
        - I think will use nested loop
            for(i = 0 to n)
                for(j = 1 to n)
        - and then will push j'th char in front of String till we reahc to n
        - I think we don't need nested loop too we can just use 1 for loop 
            for(i = 1 to n)

    2. for getting shortest palindrome
        - I think once we add a char in front we need to check if it's palindrome
        - if yes then return it.. 
        - if not then go till the end
        
    3. Also Base Case : if out input is palindrome let's return it
        - bcoz me jevha pn add karel char tevha curr string pekshya jastch length banel
        - and we need shortest palindrome so apan toch return karu 
    

    ^ Improvement :

    1. one test case is wrong
        s = "abbacd" 
        expected output = "dcabbacd"
        my output = "dcabbabbacd"

        - ithe na first round madhe last char add kela in front
            i = 5
                - currChar  : d  
                - newString : dabbacd
                - not a palindrome

            i = 4 
                - currChar  : c
                - newString : dcabbacd
                - it's a palindrome
    
    2. me add kartanna fact i = 5 front madhe add kela
        jevha i = 4 add kela tevha to second place vr kelela
        next i = 3 karnar asel tr to mala third place vr karava lagel add
        
    3. asa kahitri logic bagh jyani he work hoil
        newS = prefix + currChar + s
        where 
        prefix = will be starting string before we add currChar
        currChar = will be char at index i
        s = is a given string

    4. Getting TLE why?
        - we're creating new strings repeatedly inside the loop:
            prefix + s                          ->  O(n)
            new StringBuilder(...).reverse()    ->  O(n)
            .equals(...)                        ->  O(n)
        - These string operations are inside a loop that runs O(n) times  
            O(n²) string creations and reversals

        - we're growing the prefix inefficiently:
            prefix = prefix + currChar makes a new string every time
            Java strings are immutable — this becomes costly in large inputs

    5. How to improve without changing the core idea
        - fix isPalindrome function 
            use left, right, while loop instead of creating new reverse string 
        - if it didn't fix the problem 
            change type of prefix to stringbuilder 
            and append currChar in it 
            will not use concatenation
        

        
 
 * Pseudo Code :
 
    1. Brute Force :

    function shortestPalindrome (String s) {
    
        -> Declare variables 
            n = length of s

        -> Base Case :
            if given string is palindrome return that string
            
            if ( isPalindrome(s) )
                return s

        -> Try adding chars in front of s to get palindrome
            for(i = 1 to n)

                - get charAt(i)
                    currChar = charAt(i)

                - add it in front of s
                    newS = currChar + s

                - check if newS is palindrome or not
                    if yes return newS
        
        -> If we didin't found anything return "" empty string

    }


 */