import java.util.*;

public class CountDigitOne {
    
    public int countDigitOne(int n) {
        
        // Declare variables 
        int countOfDigitOnes = 0;
        
        
        // Let's check all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            
            // Assign a temp variable num store i in it
            int num = i;

            // Will keep our while loop till we don't have any digit to check
            while (num > 0) {
                
                // Get it's digit 
                int digit = num % 10;

                // If digit has 1 then increase the count
                if (digit == 1) {
                    
                    countOfDigitOnes++;

                    System.out.println("    - We Found 1 at digit of num : " + i + " | now count : " + countOfDigitOnes);
                }

                // Move to next digit 
                // reduce value of num in another words
                num = num / 10;

            }

        }


        return countOfDigitOnes;
    }

    public static void main(String[] args) {

        CountDigitOne solution = new CountDigitOne();

        System.out.println(" Result 1 -> " + solution.countDigitOne(13) + "\n");    // 6
        System.out.println(" Result 2 -> " + solution.countDigitOne(0) + "\n" );    // 0

    }

}

/*
 * Intuitions :
 
    1. we have given an integer n
    2. count the total number of digit 1 appearing in all on-negative integers
        <= n

 
 * Pattern :
 
    1. Means mala n dilay and
        first sagle integers count karayve lagtil je 0 <= x < n astil
        then me tyat digits check karel ki
            at least eka tri digit vr 1 ahe ka? asel tr count++

    2. Brute Force  :
        - will use for loop to check all numbers till n
            will start from 1 bcoz 0 dosen't have 1 
            for(number = 1 to n)

        - will check everyDigit one by one
            while(number > 0)

                - get digit with the use of mod 
                    this reminder gives us single digit 
                    13 % 10 = 3 
                        
                        digit = number % 10

                - check if that digit is 1 
                    if yes then increase count

                - then move to next digit of the number by using div
                    13 / 10 = 1

                        number = number / 10

        - and at teh end return count

    3. There is a problem with brute force of getting TLE


    ^ Dry Run :

        n = 13

        TotalNumbers  = 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
        digitOneCount =     1    +    10    +    11    +    12    +    13     
                      = (1 time) + (1 time) + (2 time) + (1 time) + (1 time)  
                      = 6
 
 
 
 * Pseudo Code :
 



 */