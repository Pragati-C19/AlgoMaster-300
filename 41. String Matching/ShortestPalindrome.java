import java.util.*;

public class ShortestPalindrome {
    
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