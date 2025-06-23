import java.util.*;

public class CountNumbersWithUniqueDigits {
    
    public int countNumbersWithUniqueDigits(int n) {
        
        return 0;
    }

    public static void main(String[] args) {

        CountNumbersWithUniqueDigits solution = new CountNumbersWithUniqueDigits();

        System.out.println(" Result 1 -> " + solution.countNumbersWithUniqueDigits(2) + "\n");    // 91
        System.out.println(" Result 2 -> " + solution.countNumbersWithUniqueDigits(0) + "\n");    // 1

    }

}

/*
 * Intuitions :
 
    1. we have given integer n 
    2. return the count of all numbers with unique digit x
            where 0 <= x < 10^n
 
 
 * Pattern :
 
    1. return the count of all numbers with unique digit x means what ?
        - ithe bolley aplyala count havay number of digits cha
        - te numbers 0 to 10^n chya range amdhe havet
        - and lastly te unique havet as in 
            11, 22, 33, 44, 55 ashe nums nahi chalnar mala
            nor 121, 232, 144 type of nums too 
            I don't want any repetation 
        - asa asel tr skipp it 


    2. Count of unique digit numbers
        - n = 0 :
            0 <= x < 10^0   ->  0 <= x < 1  
            digit is 0 only
            count = 1

        - n = 1 :
            0 <= x < 10^1   ->  0 <= x < 10
            digits are 0 to 9
            count = 10

        - n = 2 :
            0 <= x < 10^2   ->  0 <= x < 100
            digits are 0 to 99 
            count = 100
            but we need to except 11, 22, 33 like nums
            so count = 91

    3. let's break into parts 
        - 1 digit numbers (0 to 9)
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 → 10 total

        - 2 digit numbers 
            We now build numbers like: XY, where:
                - X is the first digit (tens)
                - Y is the second digit (units)

            Step 1: Pick first digit (X)
                It can’t be 0 (we don’t want numbers like 05)
                So we can pick from 1 to 9 → 9 options

            Step 2: Pick second digit (Y)
                It can be 0, but must not match the first digit
                So we pick from 0 to 9 excluding whatever we picked in step 1
                So -> 9 options again

            Final :
                9 (choices for X) × 9 (choices for Y) = 81 valid 2-digit unique numbers

    


    ^ Dry Run :

        n = 2
        means we can take x as 
            0 <= x < 10^2 
            0 <= x < 100
            and we need to skip 11, 22, 33, 44, 55, 66, 77, 88, 99

        so total count of all x numbers are 91

 
 * Pseudo Code :
 
    function countNumbersWithUniqueDigits(int n) {
    
        -> countOfUniqueNumbers will store our final ans
            where 0 to 9  -> all 1-digit numbers
            it will start with 10

        -> currentCombinationCount will stores how many numbers of current digit length are valid
            First digit choices for multi-digit numbers (can't be 0)
            where for 2-digit: first digit has 9 options
            it will start with 9

        -> remainingDigitsToChooseFrom is how many digits are left to choose from for next digit
            Digits available after choosing the first digit
            where second digit can be any of remaining 9 digits
            it will start with 9

        -> Now we build numbers from 2 digits to n digits
            for(i = 2 to n)

                - multiply with how many new digits we can use 
                    Multiply with number of remaining digits we can choose next (to keep them unique)
                    
                    currentCombinationCount *= remainingDigitsToChooseFrom

                - Add this count to out total uniqueNumbers
                    countOfUniqueNumbers += currentCombinationCount

                - After choosing one more digit, one fewer is available 
                    remainingDigitsToChooseFrom--

        -> return countOfUniqueNumbers

    }


 */