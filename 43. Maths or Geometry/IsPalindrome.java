import java.util.*;

public class IsPalindrome {
    
    public boolean isPalindrome(int x) {
        
        return true;
    }

    public static void main(String[] args) {

        IsPalindrome solution = new IsPalindrome();

        System.out.println(" Result1 -> " + solution.isPalindrome(121) + "\n");     // true
        System.out.println(" Result2 -> " + solution.isPalindrome(-121) + "\n");    // false
        System.out.println(" Result3 -> " + solution.isPalindrome(10) + "\n");      // false

    }

}

/*
 * Intuitions :
 
    1. we have given an integer x
    2. need to check if it's palindrome or not 
    3. if yes return true 
    4. else false
 
 * Pattern :
 
    1. In test cases I see -121 is not palindrome 
        means from left to right it says -121 
              from right to left it says 121-
    2. so I understtod one thing we need to build String for that num 
    3. then will check first and last char of the string 
    4. if it's same will check next char 
    5. if it's not same will return false

    6. to convert int x to string there are two ways
        - use String.valueOf(x)
            s = String.valueOf(x)

        - use Integer.toString(x)
            s = Integer.toString(x)

        - used any one of above more safe and optimal is first one



 
 * Pseudo Code :
 
    function isPalindrome (int x) {
    
        -> declare variables
            numString   - convert x to string
            n           - length of string
            start = 0   - starting index of string
            end = n-1   - ending index of string 

        -> start while loop will end < start

            - check if first and last char are different 
                don't need to check further return false
                    if(charAt(start) != charAt(end))
                        return false

            - else increase start and decrease end

        -> at the end return true

    }


 */