import java.util.*;

public class AtMostNGivenDigitSet {
    
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
 
 
 
 * Pseudo Code :
 



 */