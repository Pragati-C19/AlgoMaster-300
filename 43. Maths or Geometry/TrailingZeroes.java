import java.util.*;

public class TrailingZeroes {
    
    // Driver Function
    public int trailingZeroes(int n) {
     
        // Declare variables
        int zeroesCount = 0;

        // call helper function to get n!
        long nFactorial = factorials(n);
        System.out.println(" Factorial(n!) = " + nFactorial);

        // Start while loop till nFactorial will be equals to 0
        while (nFactorial != 0) {
            
            // get last digit of the num
            long digit = nFactorial % 10;

            // update num
            nFactorial = nFactorial / 10;

            // Check if curr last digit is not equal to zero or not
            if (digit != 0) {
                
                System.out.println(" currDigit(" + digit + ") is not equal to zero...");
                return zeroesCount;
            }

            // else will increase the count 
            zeroesCount++;

        }

        return zeroesCount;
    }

    // Helper Function : to get factotials
    private long factorials(int n) {

        // Declare a DP
        long[] dp = new long[n + 1];

        // add initial values in dp
        dp[0] = 1;      // why? check comments

        // check other i's 
        for (int i = 1; i <= n; i++) {
            
            // Use standard formula to get factorial
            dp[i] = i * dp[i-1];

        }
        System.out.println("    - DP Array : " + Arrays.toString(dp));

        return dp[n];
    }

    public static void main(String[] args) {

        TrailingZeroes solution = new TrailingZeroes();

        System.out.println(" Result1 -> " + solution.trailingZeroes(3) + "\n");      // 0
        System.out.println(" Result2 -> " + solution.trailingZeroes(5) + "\n");      // 1
        System.out.println(" Result3 -> " + solution.trailingZeroes(0) + "\n");      // 0
        System.out.println(" Result4 -> " + solution.trailingZeroes(20) + "\n");     // 0
        System.out.println(" Result5 -> " + solution.trailingZeroes(00000) + "\n");      // 0
        
        System.out.println(" Result6 -> " + solution.trailingZeroes(13) + "\n");     // 2

    }

}

/*
 * Intuitions :

    1. we have given integer n
    2. need to return number of trailing zeroes in n!
    3. Trailing Zero means :
        A zero at the end of a number.

        example :
            1200    -> has 2 trailing zeros
            100000  -> has 5 trailing zeros
            90010   -> has 1 trailing zero only (not the middle one!)
            0123    -> has no trailing zeros (leading zero)
            1040    -> has 1 trailing zero
            1002    -> has no trailing zeros (0 is in the middle)
 
    4. n! means we know right?
        n! = n * (n-1) * (n-2).... 3 * 2 * 1

    5. so we need to get ans for n! and then check that ans's each digit and see if last digits are zero or not

 
 * Pattern :
 
    1. we need to create a factorial function, which gives factorials for n
    2. je ans yeil we need to check it's digit from right to left (backwards)
        ka? karan trailing means zero should be at end
    3. mhnje me magun check karel 
        jr n-1 digit 0 asel tr will count it and move to next
            n-2 digit 0 nasel tr will directly return count so far

    ^ Improvement :
        - I tried checking different nums
        - but after 16 jo pn n ahe tyachi value Max_Value ahe tyamul ans asa ahe
            [-288522240, -898433024, 109641728, -2102132736, -1195114496, -522715136, 862453760, -775946240, 2076180480]
        - from 0 to 16 factorials are
            [1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 1932053504, 1278945280, 2004310016, 2004189184]

        - now I'm confused how will I handle n > 16 

        - ohk the problem I mention it's from 13 onwards nums not 16 onwards
            13! = 6,227,020,800 
            my ans is 1932053504
        
        - to make it work firts thought in my mind is change int to long 
            and it worked till n = 16
            let me check other random n values  
            and it works only till n = 20

        - need to think more optimal solution


 * Pseudo Code :
 
    function trailingZeroes (int n) {

        -> Declare variable
            zeroesCount = 0 

        -> call function to get factorials for n
            nFactorial = factorials(n)
    
        -> start while loop till nFactorial == 0 and check digit by digit from right to left
        
            - get last digit from the nFactorial num
                digit = nFactorial % 10

            - update nFactorial num by removing last digit
                nFactorial = nFactorial / 10
                
            - check if that last digit is not equal to 0?
                if yes then we don't need to check further return count

                if(digit != 0)
                    reutrn zeroesCount

            - else will go for next digit
    
        -> at the end return zeroesCount so far

    }

    function factorials (int n) {
    
        -> Declare variable
            dp  - it will store factorial of i 

        -> initial value for dp
            dp[0] = 1   - as per the Combinatorics Reasoning (nCr) 0! = 1
            dp[1] = 1

        -> check other i's 
            for(i = 2 to n)

                - standard formula for factorial is n! = n * (n-1)!
                dp[i] = i * dp[i-1]


        -> at the end return dp[n]

    }

 
 */