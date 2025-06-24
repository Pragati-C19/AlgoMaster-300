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
        System.out.println(" Result 3 -> " + solution.countDigitOne(824883294) + "\n" );    // Getting TLE for this

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
    4. let's use DP 
        - instead of checking all numbers will just count how many 1s appear at each digit place (units, tens..)
        - example n = 234 then we want count of 1s from 1 to 234
            
            will analize each digit seperately
                hundreds    tens    units
                    2         3       4

            will calculate how many 1s appear in each position from 1 to 234

    5. Break Down of DP approach 
        - Let's loop each digit from left to right (units, tens, hundreds)
            for(i = 0 to digit.length)
        - If we are at positionPlace = 10^i
            it can be 10^0 means units, 10^1 means tens, 10^2 means hundreds
        - then will slips n into 3 parts
            leftPart -> digits to the left of currDigit
            currDigit  -> the digit at currPosition which is positionPlace
            rightPart  -> digits to the right of currDigit
        - For example : n = 234
            positionPlace = 10
                 leftPart = 2 (hundreds place digit) 
                currDigit = 3 (tens place digit)
                rightPart = 4 (units place digit)

        - Now we apply logic 
            if currDigit = 0
                1s can only come from full cycles of left part 
                count = leftPart * positionPlace 
            
            if currDigit = 1
                count = (leftPart * positionPlace) + (rightPart + 1)

            if currDigit >= 2
                count = (leftPart * positionPlace) + positionPlace

        

    ^ Dry Run :

    -> Example 1 :
            n = 13

            TotalNumbers  = 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
            digitOneCount =     1    +    10    +    11    +    12    +    13     
                          = (1 time) + (1 time) + (2 time) + (1 time) + (1 time)  
                          = 6
 
    -> Example 2 :

            n = 140

                - Position = 1 (unit Place)
                    positionPlace = 1
                         leftPart = 14 (took all numbers before currDigit)   
                        currDigit = 0 
                        rightPart = 0 (nothing after unit Place)

                    currDigit == 0 so
                        count = leftPart * positionPlace 
                              = 14 * 1
                              = 14

                    so 14 numbers from 0 to 140 have 1 in the units place 

                - Position = 10 (tens Place)
                    positionPlace = 10
                         leftPart = 1 (took all numbers before currDigit)   
                        currDigit = 4 
                        rightPart = 0 (took all numbers after currDigit)

                    currDigit >= 2 so 
                        count = (leftPart * positionPlace) + positionPlace
                              = (1 * 10) + 10
                              = 20

                    so 20 numbers from 0 to 140 have 1 in the tens place

                - Position = 100 (hundreds Place) 
                    positionPlace = 100
                         leftPart = 0 (nothing before hundreds Place) 
                        currDigit = 1 
                        rightPart = 40 (took all numbers after currDigit)

                    currDigit == 1 so 
                        count = (leftPart * positionPlace) + (rightPart + 1)
                              = (0 * 100) + (40 + 1)
                              = 41

                    so 41 numbers from 0 to 140 have 1 in the hundreds place

                - Total count is 
                    totalCount = count[position1] + count[position10] + count[position100]
                               = 14 + 20 + 41
                               = 75

 
 
 * Pseudo Code :
 



 */