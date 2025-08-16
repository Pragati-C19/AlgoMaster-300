import java.util.*;

public class LongestDupSubstring {
    
    public String longestDupSubstring(String s) {
        
        // Declare variables
        int n = s.length();
        String duplicateString = "";
        int maxLength = 0;
        Map<String, Integer> freqMap = new HashMap<>();


        // Phase 1 : Try all substrings from j to i and add it's freq in map
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                
                // Get substring including i
                String currSubString = s.substring(j, i + 1);

                // add that substring and it's freq in map
                freqMap.put(currSubString, freqMap.getOrDefault(currSubString, 0) + 1);

                // Debugging sout
                System.out.println(" - i = " + i);
                System.out.println("\t j = " + j + "  -> substring[" + j + "," + i + "] = " + currSubString);
                System.out.println("\t Freq Map : " + freqMap);

            }
        }

        System.out.println(" Freq Map : " + freqMap + "\n");


        // Phase 2 : From freqMap, find all substrings with freq >= 2
        for (Map.Entry<String,Integer> entry : freqMap.entrySet()) {
            
            // Assign variable to key and value and get length of key
            String key = entry.getKey();
            int currLength = key.length();
            int freq = entry.getValue();

            // check if freq >= 2 and keyLength > maxLength
            if (freq >= 2 && currLength > maxLength) {
                
                System.out.println("    - currDupStr : " + key + " | currLength : " + currLength);

                duplicateString = key;
                maxLength = currLength;

                System.out.println("      freq >= 2 so, longestDupStr : " + duplicateString + " | maxLength : " + maxLength);
            }
        }

        return duplicateString;
    }

    public static void main(String[] args) {
    
        LongestDupSubstring solution = new LongestDupSubstring();

        System.out.println(" Result 1 -> " + solution.longestDupSubstring("banana") + "\n");    // ana
        System.out.println(" Result 2 -> " + solution.longestDupSubstring("abcd") + "\n");      // ""

    }

}

/*
 * Intuitions :
 
    1. we have given a String s
    2. all duplicate substrings of s can occur 2 or more times in s
    3. It can overlap no issue
    4. we need to return duplicate substring that has longest length
    5. if we can't find duplicate substring return ""
 
 
 * Pattern :
 
    1. Brute Force thinking
        - Check all leteers in String one by one 
            for (i = 0 to n)
                for(j = 0 to i)

        - get substring of [j, i]
        - check if we have already came across this substring?
            if yes then increase it's freqency in Map
            if no add it in map saying freq as 1
        - then sagle char's check zale ki 
            get all substrings from map whose freq is >= 2
        - check that key Strings's length 
            if it's max that prev update map if not keep looking for next
 
    ^ Dry Run :

            s = b a n a n a
        index = 0 1 2 3 4 5

        - check char of string one by one

            i = 1
                j = 0 -> substring[0,1] = ba ->  freq = 1
                j = 1 -> substring[1,1] = a  ->  freq = 1
                
            i = 2
                j = 0 -> substring[0,2] = ban ->  freq = 1
                j = 1 -> substring[1,2] = an  ->  freq = 1
                j = 2 -> substring[2,2] = n   ->  freq = 1

            i = 3
                j = 0 -> substring[0,3] = bana ->  freq = 1
                j = 1 -> substring[1,3] = ana  ->  freq = 1
                j = 2 -> substring[2,3] = na   ->  freq = 1
                j = 3 -> substring[3,3] = a    ->  freq = 2

            i = 4
                j = 0 -> substring[0,4] = banan ->  freq = 1
                j = 1 -> substring[1,4] = anan  ->  freq = 1
                j = 2 -> substring[2,4] = nan   ->  freq = 1
                j = 3 -> substring[3,4] = an    ->  freq = 2
                j = 4 -> substring[4,4] = n     ->  freq = 2

            i = 5
                j = 0 -> substring[0,5] = banana ->  freq = 1
                j = 1 -> substring[1,5] = anana  ->  freq = 1
                j = 2 -> substring[2,5] = nana   ->  freq = 1
                j = 3 -> substring[3,5] = ana    ->  freq = 2
                j = 4 -> substring[4,5] = na     ->  freq = 2
                j = 5 -> substring[5,5] = a      ->  freq = 3


        - So substring whose freq >= 2 in freq map are
          
            ana = 2
            an  = 2
            na  = 2
            a   = 3
            n   = 2

        - we don't care abt freq in result we just needed it to check if it's duplicate or not
        - Will check length of duplicate strings

            ana = 3
            an  = 2
            na  = 2
            a   = 1
            n   = 1

        - we need longest substring 
            so ans is "ana"
        

    ^ Improvement :

    1. we are getting TLE for 24th test case out of 68
    2. We can use 2 things suffix Array or rolling hash 
        - using suffix and understanding it will take time
        - so let's use rolling hash + binary search here

    3. Phase 1 : use binary search to narrow down maximum length of string in parts
        - get mid 
        - if we found duplicate substring of length (mid)
            then try for longer length (right side of mid) 
            means left = mid + 1
        - if not then 
            try shorter length (left side of mid)
            means right = mid - 1

    4. Phase 2 :

 * Pseudo Code :
 

    function longestDupSubString (s) {
    
        -> Declare variables 
            n               - String s length
            freqMap         - it will store substring as key and freq as value
            longestDup      - It will tell which is longest duplicate string so far
            maxLength       - It will tell length of longestDup


        -> Phase 1 : Try all substrings from j to i and add it's freq in map
            for(i = 0 to n)
                for(j = 0 to i)

                    - Get substring including i'th char too
                        substring = s.substring(j, i + 1)  

                    - add it's freq in freqMap
                        freqMap.put(substring, freqMap.getordefault(substring, 0) + 1)

                        
        -> Phase 2 : From freqMap, find all substrings with freq >= 2
            for(entry : freqMap.entrySet)

                - declare variable for key and value
                    also length of key

                    key = getKey
                    freq = getValue
                    keyLength = key.length

                - check if freq >= 2 and keyLength > maxLength
                    if yes then updated 
                        longestDup String to key
                        maxLength to keyLength


        -> at the end return longestDup string

    }



 */