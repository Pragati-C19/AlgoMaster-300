import java.util.*;

public class Reverse {
    
    public int reverse(int x) {
        
        // Declare variable
        int reverseX = 0;

        while (x != 0) {
            
            System.out.println(" -> x (" + x + ") != 0 : ");

            // get last digit from x
            int digit = x % 10;

            // Update x by removing that last digit
            x = x / 10;

            // add digit in reverse 
            reverseX = reverseX * 10 + digit;

            System.out.println("      digit      = " + digit);
            System.out.println("      updated x  = " + x);
            System.out.println("      reverse    = " + reverseX);

        }

        return reverseX;
    }

    public static void main(String[] args) {

        Reverse solution = new Reverse();

        System.out.println(" Result1 -> " + solution.reverse(123) + "\n");     // 321
        System.out.println(" Result2 -> " + solution.reverse(-123) + "\n");    // -321
        System.out.println(" Result3 -> " + solution.reverse(120) + "\n");     // 21

    }

}

/*
 * Intuitions :
 
    1. we have given an integer x
    2. we need to reverse it's digit 
        means if x = 123,  then reverse = 321
    3. In que they said x is 32-bit Interger and if our reverse will be outside of that range [-2^31, 2^31 - 1]
        return 0
    4. need to think of bit thing let's think abt reversing first
 
 * Pattern :
 
    1. As shown in test case we can't reverse - sign
        means is x = -123,  then reverse = -321
    2. so will not create String here 
    3. will directly start while loop till x == 0
    4. then will check digit one by one 
        - get last digit from x 
    5. update x by removing that digit 
    6. then add that digit in reveserd int


    ^ Dry Run :

        x = 123
        reverse = 0

        - First call for while (x = 123 != 0)

            digit   = 123 % 10      = 3
            x       = 123 / 10      = 12
            reverse = 0 * 10 + 3    = 3

        - Second call for while (x = 12 != 0)

            digit   = 12 % 10      = 2
            x       = 12 / 10      = 1
            reverse = 3 * 10 + 2   = 32

        - Third call for while (x = 1 != 0)

            digit   = 1 % 10       = 1
            x       = 1 / 10       = 0
            reverse = 32 * 10 + 1  = 321


 
 * Psedo Code :
 
    function reverse (int x) {
    
        -> Declare variable
            reverseX    - it's reveser integer of x

        -> start while loop till x == 0

            - get last digit from x
                digit = x % 10

            - update x by removing that digit 
                x = x / 10

            - add digit to the reverse int
                reverseX = reverseX * 10 + digit
    
        -> At the end return reverseX

    }


 */