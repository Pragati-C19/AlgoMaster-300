import java.util.*;

public class AtMostNGivenDigitSet {
    
    // Globally Declare Variables
    String nString;
    int nLength;
    int m;

    // Driver Function
    public int atMostNGivenDigitSet(String[] digits, int n) {
        
        // Declare variables
        nString = String.valueOf(n);
        nLength = nString.length();
        m = digits.length;
        int totalCount = 0;

        // Phase 1 : Count numbers with less digits than n
        for (int i = 1; i < nLength; i++) {
            
            // if we didin't use (int) before pow it gives error as lossy conversion from double to int 
            int countForCurrLength = (int) Math.pow(m, i);
            System.out.println("    - Length " + i + " -> " + countForCurrLength + " numbers");

            // add it in totalCount 
            totalCount += countForCurrLength;
        }
        System.out.println(" Total Count till (nLength - 1) : " + totalCount + "\n");


        // Let's call recursion and add it in totalCount
        totalCount += dfs(0, digits);
        

        return totalCount;
    }

    // Recursion Function : to count valid numbers matching n digit-by-digit
    private int dfs(int positionIndex, String[] digits) {

        // Base Case : if all digits are match return 1 
        if (positionIndex == nLength) {
            
            System.out.println(" Matched full number...");
            return 1;
        }

        // assign currdigit and currcount
        int currCount = 0;
        int digitOfN = nString.charAt(positionIndex);
        System.out.println(" Visiting Position " + positionIndex + ", n digit: " + digitOfN);


        // Let's check each digit from digits array
        for (String digit : digits) {
            
            // Get char from that string digit 
            // we have used 0 bcoz ithe ekach char ahe 0th index vr so
            char digitChar = digit.charAt(0);

            // Check conditions 
            if (digitChar < digitOfN) {
                
                // check if d < digitOfN ? if Yes updated count, if not then check next condition 
                // get remaining Places except positionIndex
                int remainingPlaces = nLength - positionIndex - 1;

                // get all combinations at those places
                int combinations = (int) Math.pow(m, remainingPlaces);

                // Add that combination in count
                currCount += combinations;

                System.out.println("    - '" + digitChar + "' < '" + digitOfN + "' -> add " + combinations + " in currCount");
            
            }  
            else if (digitChar == digitOfN) {
                
                // check if d == digitOfN ? if Yes then call recursion
                System.out.println("    - '" + digitChar + "' == '" + digitOfN + "' -> call recursion");

                // Call recursion for next positionIndex and add it in count
                currCount += dfs(positionIndex + 1, digits);
            } 
            else {

                // If none of the above condition valids break it
                System.out.println("    - '" + digitChar + "' > '" + digitOfN + "' -> break for loop");

                break;
            }

        } 

        return currCount;
    } 


    public static void main(String[] args) {

        AtMostNGivenDigitSet solution = new AtMostNGivenDigitSet();

        // First Example :
        String[] digits1 = {"1", "3", "5", "7"};
        int n1 = 100;
        System.out.println(" Result 1 -> " + solution.atMostNGivenDigitSet(digits1, n1) + "\n");    // 20

        // Second Example :
        String[] digits2 = {"1", "4", "9"};
        int n2 = 1000000000;
        System.out.println(" Result 2 -> " + solution.atMostNGivenDigitSet(digits2, n2) + "\n");    // 29523

        // Third Example :
        String[] digits3 = {"7"};
        int n3 = 1000000000;
        System.out.println(" Result 3 -> " + solution.atMostNGivenDigitSet(digits3, n3) + "\n");    // 1

    }

}

/*
 * Intuitions :
 
    1. We have given digits array 
        which is sorted already in increasing order
    2. we can write numbers using the digits in digit arrays
        we can you each digit as many time as possible
        example : digits = ['1','3','5']
        we can use numbers as : '13', '551', and '1351315'
    3. We need to return the number of positive integers that can be generated 
        that are less than or equal to given integer n

 
 * Pattern :

    1. so as per que basically mala 0 <= x < n itke numbers havet
    2. ata last que madhe 1 digit count karaycha 
        - ithe aplyala numbers madhe digits array madhlech nums havet
    3. example : digits = ["1","3","5","7"], n = 100
        - we can generate 20 numbers only with all given digits till 100
        - 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77
    4. jitka distay mala tyatun sangte
        m = digits.length karu apan 
        unitPlaceDigit = we can use all numbers in digit 
        tensPlaceDigit = we can use all numbers in digit 

    5. jr nit pahil tr samjel 
        unitPlaceDigit = m  (length of digit array)
        tensPlaceDigit = m * m (bcoz we can place m number of digit at unitPlace and m number of digit at tens Place)
                       = unitPlaceDigit * m  (apan asa pn lihu shakto)
        hundredsPlaceDigit = m * m * m
                           = tensPlaceDigit * m
        and so on for other positions we need to find pattern here

    6. after checking one postion je ans yetay apan tyat unitPlace varcha count multiple kartoy
    7. and te alyavr aplyala saglya values add karavya lagtil
        total = unitPlaceDigit + tensPlaceDigit + hundredsPlaceDigit

    8. ithe challenge ahe kiti places ahet aplyakde te check karan and combined pattern shodhan 
    9. So apan n.length - 1 parynt che numbers me bolle tya logic ni kadhu shakte
        - peoblem tevha hoil jevha n.length asel
        - example me 100 check karnar ahe tr yat tbh mala hundreds(n.length) place check karaychi garaj nahiye still to count houn jail
            or me jr 234 check karat asel tyat mala husdred place lagtey but mala sagle nums nakoy with hundreds place
        - tyamul mala n.length al ki pratek veles check karav lagel ki number less than ahe ki nahi 
        - ata kr vichar kaskay karnar ahe without using for(i = 0 to n)
    

    ^ Start Fresh :

    1. Data I understood so far :
        
        -> Phase 1 : Count numbers with less digits than n
            - if n = 100 (3 digits) then :
                count all 1-digit numbers -> m
                count all 2-digit numbers -> m^2 or m * m
                total = m + (m*m)
            - Use Power here it will be easier instead of m * m
                Math.pow(m, lengthOfN)

        -> Phase 2 : Count numbers with the same number of digits as n
            - Here I'm stuck 
            - let's take example n = 100
                nLength = 3
                ata aplyala number build karaychay ha with given digits but 100 pekshya extend nahi zal pahije
            
            - prate position la kay karaych ata ?
                1. let's say aplyala xyz number build karaychay where x,m y, z are choosen from digit array
                2. At each position (from left to right), we check:
                    - How many digits in digits[] are less than the current digit in n
                    - That tells us how many options we can use to create smaller numbers at this place
                3. If the current digit in n exists in our digit array, we can proceed to the next digit
                4. If not, we must stop there (since we can't match n anymore)

            - Dry Run :
                digits = ["1", "2", "5", "7"], n = 234

                - total till nLength = 2 is 
                    TotalCount = 4 + (4 * 4) = 20
                - For nLength = 3 let's check one by one digit and get smallest from digits
                     will check from left to right  2, 3, 4

                - check index = 0, digitOfN = 2
                    loop over digits
                        
                    - d = 1 : 
                        d < digitOfN ?  ->  1 < 2  -> Yes
                        means we can choose any 2-digit number for remianing Places 

                        currCount = m * m = 4 * 4 = 16
                        TotalCount = TotalCount + currCount = 20 + 16 = 36

                    - d = 2 : 
                        d == digitOfN  -> 2 == 2  -> Now check for next postion 
                        
                        check index = 1, digitOfN = 3
                            - d = 1
                                d < digitOfN ?  ->  1 < 3  -> Yes
                                means we can choose any 1-digit number for remianing Place

                                currCount = m = 4
                                TotalCount = TotalCount + currCount = 36 + 4 = 40

                            - d = 2
                                d < digitOfN ?  ->  2 < 3  -> Yes
                                means we can choose any 1-digit number for remianing Place

                                currCount = m = 4
                                TotalCount = TotalCount + currCount = 40 + 4 = 44

                            - d = 5 :
                                d < digitOfN ?  ->  5 > 3  -> No 
                                break will not count this 

                            - d = 7 
                                d < digitOfN ?  ->  7 > 3  -> No 
                                break will not count this 

                    - d = 5 :
                        d < digitOfN ?  ->  5 > 2  -> No 
                        break will not count this 

                    - d = 7 
                        d < digitOfN ?  ->  7 > 2  -> No 
                        break will not count this 

                - We didin't check index = 3 bcoz we didn't reach there 
                    jr mala digits array madhe 3 asta and second position la 3 == 3 zala asta
                    tr unitPlace (index = 3) pn check karavi lagli asti
                    
                    
 
 * Pseudo Code :
 
    1st Approach :
        - Problem with this approach I didn't check if the number is <= 100 or not7

        function atMostNGivenDIgitSet(digits, n) {
        
            -> Declare variables
                m = digits.length
                totalCountOfNumbers = 0
                unitPlaceDigits = m  
                positionPlace = 1   
                digitCountForCurrPlace = unitPlaceDigits    

            -> will check position one by one 
                while(n / positionPlace) 

                - get digitcount for this place
                    digitCountForCurrPlace = digitCountForCurrPlace * m

                - adding this digitCount in total
                    totalCountOfNumbers += digitCountForCurrPlace

            -> return totalCountOfNumbers
        
        }


    2nd Approach :

        function atMostNGivenDIgitSet(digits, n) {
        
            -> Declare variables
               nString      - convert int n to string bcoz digits are String[]
               nLength      - length of n to know how many digits or positions it has
               m            - length of digits Array
               totalCount   - initially it is 0


            -> Phase 1 : Count numbers with less digits than n
                for(i = 0 to nLength)
                    
                    - All d-digit numbers using m digits
                        totalCount += m^i 

            -> Call recursion
                totalCount += dfs(0)

            -> return totalCount

        }



        function dfs(positionIndex) {
        
            -> Base Case : if positionIndex == nLength
                if (positionIndex == nLength)
                    return 1        // matched all digits -> valid number

            -> declare currCount = 0

            -> check each digits of digits array
                for (d : digits)

                    - check if d < digitOfN ? if Yes updated count, if not then check next condition 
                        if (d < nString[positionIndex])
                            count += m^(nLength - positionIndex - 1)

                    - check if d == digitOfN ? if Yes then call recursion
                        else if(d == nString[positionIndex])
                            count += dfs(positionIndex + 1)

                    - if above both conditions are not valid then break for loop


            -> return count
                        
        }


 */