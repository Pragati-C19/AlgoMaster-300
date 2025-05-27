import java.util.*;

public class LongestWord {

    // Driver Function 
    public String longestWord(String[] words) {
        
        String result = "";
        Set<String> buildWordSet = new HashSet<>();

        Arrays.sort(words);
        System.out.println("    Sorted Products List : " + Arrays.toString(words));

        for (String word : words) {
            
            System.out.println("      -> Word we are checking : " + word);

            String prefix = word.substring(0, word.length() - 1);

            if ( word.length() == 1 || buildWordSet.contains(prefix) ) {

                buildWordSet.add(word);

                if (word.length() > result.length() ||
                (word.length() == result.length() && word.compareTo(result) < 0)
                ) {
                    // "apply".compareTo("apple") > 0  // false so it dosen't change result, if it's true then it has changes the result

                    result = word;
                    System.out.println("      -> Word ( " + word + " ) Added in Current Result : " + result);   
                }
            }

            
            System.out.println("      -> Set Contains : " + buildWordSet);
        }

        System.out.println("    Result : " + result);
        return result;
    }


    public static void main(String[] args){

        LongestWord solution = new LongestWord();

        String[] words1 = {"w","wo","wor","worl","world"};
        System.out.println("-> Result 1 : " + solution.longestWord(words1) + "\n");

        String[] words2 = {"a","banana","app","appl","ap","apply","apple"};
        System.out.println("-> Result 2 : " + solution.longestWord(words2) + "\n");
    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. Array of string words given
 * 2. return the longest word in words that can be built one charecter at a time
 * by other words
 * 3. use lexicographical order if there are more than 1 word
 * 4. use hashset 
 * 
 * 
 * Pattern :
 * 
 * Exampl : Flow Should Look like this
 * 
 * Input: ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 
 * After sorting:
 * ["a", "ap", "app", "appl", "apple", "apply", "banana"]
 * 
 * Set = {}
 * res = ""
 * 
 * - "a" → valid → add to set → res = "a"
 * - "ap" → prefix "a" in set → add → res = "ap"
 * - "app" → prefix "ap" in set → add → res = "app"
 * - "appl" → prefix "app" in set → add → res = "appl"
 * - "apple" → prefix "appl" in set → add → res = "apple"
 * - "apply" → prefix "appl" in set → add, but res stays "apple" (because it's
 * smaller than "apply")
 * 
 * 
 * 1. assign a result string
 * 2. Create a set to store valid buildable words
 * 3. Will sort the String[] in lexicographical order
 * 4. for loop (word : words)
 *      - if(word.length == 1) -> add it in set and result.add(word)
 *      - else :
 *          - prefix = word.substring(0, word.length - 1)
 *          - check if(hashset.containsKey(prefix)) -> add it in set and result.add(word)
 * 5. return result
 * 
 * Pseudo Code :
 * 
 * function LongestWord(String[] words){
 * 
 *      result = ''
 *      Set<String> buildWord = new hashSet
 *      
 *      words.sort()
 * 
 *      for(char : word.length){
 *          
 *          if(char.length == 1) 
 *              -> hashset.add(char)
 *              -> add it in result
 * 
 *          else 
 *              -> prefix = char[0 : (char.length - 1)]
 *              -> if (hashset.contains(prefix)) 
 *                      -> add it in result
 *      }   
 *      
 *      return result;
 * }
 * 
 */