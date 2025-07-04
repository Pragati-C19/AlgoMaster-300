import java.util.*;

public class RepeatedStringMatch {
    
}

/*
 * Intuitions :
 
    1. we have given two strings a and b
    2. we need to return minimum number of times we should repeat string a
        so that string b is substring of it
    3. if it's not possible for b to be a substring of a after repeating it
        return -1
    4. Example :
        string 'abc' 
        0 repeatation - means "" (empty string)
        1 repeatation - means "abc"
        2 repeatation - means "abcabc"
 
 
 * Pattern :
 
    1. we need to create string which has repeatation of a 
        so let's say will use a stringBuilder and create a variable repeatedAString
    2. apla core logic ahe ki 
        - repatedAString madhe a add karat jaycha 
        - add kela ki check karaych if we can contains b?
            if yes then return count
        - otherwise count++
 
 * Pseudo Code :

    1. Brute Force :

    function repeatedStringMatch (String a, String b) {
    
        -> Declare variables
            repatedAString = a
            int count = 1

        -> start while loop till I'm not sure yet
            let's consider true only 

            while(true) 

                - check if repatedAString contains b?
                    if yes then return count

                - otherwise 
                    will add a in repatedAString
                    and count++



        -> if we didin't get matching strings 
            return -1 

    }


 */