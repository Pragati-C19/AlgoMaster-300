import java.util.*;

public class RepeatedStringMatch {
    
    public int repeatedStringMatch(String a, String b) {
        
        // Declare variables 
        String repatedAString = a;
        int repatingCount = 1;      // as we have added a in repatedString 

        while (true) {
            
            // check if b is a substring of repeatedAString
            if (repatedAString.contains(b)) {
                
                System.out.println("  " + b + " is a substring of " + repatedAString + " so count : " + repatingCount);
                return repatingCount;
            }

            repatedAString += a;
            repatingCount++;

        }

        // it's not possible for b to be a substring of a after repating it, so
        return -1;
    }

    public static void main(String[] args) {

        RepeatedStringMatch solution = new RepeatedStringMatch();

        String a1 = "abcd";
        String b1 = "cdabcdab";
        System.out.println("Result1 -> " + solution.repeatedStringMatch(a1, b1) + "\n");    // 3

        String a2 = "a";
        String b2 = "aa";
        System.out.println("Result2 -> " + solution.repeatedStringMatch(a2, b2) + "\n");    // 3

    }

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
    3. while loop madhe ka rahun jatoy apan? 
        - a fact add hot jatoy infinite times 
        - jr b nahi bhetla tr he asach while loop chalat rahil
        - to reak it aplyala length chi limit lavavi lagel
        - ki ya length paryntch apan check karu nantr directly return -1 karu
        - why? 
            karan bagh me "abc" 2 veles repeat kela or 3 veles kela it will look like this
                "abcabc" and "abcabcabc"
            ata jo pn b asel to yapaikich kahitri asel na?.. 
            pratek thoda time nantr a string repeat hotey mhnje jr 1-2 repeatations nantr pn b nahi bhetat ahe
            mhnje it's not a substring of a..
            mg kitihi veles repeate kel tri
        - still will check till 
            repeatedA.length() < b.length() + 2 * a.length() 

 
 * Pseudo Code :

    1. Brute Force :

        -  It gives me error : unreachable statement return -1; 
            means we are stuck in while loop
        - think of how to improve while loop where should I break it

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



    2. Brute Force 2 :

    function repeatedStringMatch (String a, String b) {
    
        -> Declare variables
            repatedAString = a
            int count = 1

        -> start while loop till repeatedAString length is less than b + 2 * a

            while(repeatedA.length() < b.length() + 2 * a.length()) 

                - check if repatedAString contains b?
                    if yes then return count

                - otherwise 
                    will add a in repatedAString
                    and count++



        -> if we didin't get matching strings 
            return -1 

    }

 */