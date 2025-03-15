import java.util.*;

public class CountMatchingSubsequences {
    public int numMatchingSubseq(String s, String[] words) {
        // Step 1: Use a map to track where each word is in its progress
        Map<Character, List<StringBuilder>> waiting = new HashMap<>();

        // Step 2: Initialize the map with the first character of each word
        for (char c = 'a'; c <= 'z'; c++) {
            waiting.put(c, new ArrayList<>());
        }

        for (String word : words) {
            waiting.get(word.charAt(0)).add(new StringBuilder(word));
        }

        int count = 0;

        // Step 3: Go through each character in s
        for (char c : s.toCharArray()) {
            List<StringBuilder> advance = waiting.get(c);
            waiting.put(c, new ArrayList<>());  // Clear the list for this character

            for (StringBuilder sb : advance) {
                sb.deleteCharAt(0);  // Move to the next character

                if (sb.length() == 0) {
                    count++;  // Word completed successfully
                } else {
                    waiting.get(sb.charAt(0)).add(sb);  // Move to next character's bucket
                }
            }
        }

        return count;
    }


    public static void main(String[] args) {
        CountMatchingSubsequences obj = new CountMatchingSubsequences();

        String s1 = "abcde";
        String[] words1 = {"a", "bb", "acd", "ace"};
        System.out.println("Input: " + s1 + ", words = " + Arrays.toString(words1) +
                           " -> Output: " + obj.numMatchingSubseq(s1, words1));

        String s2 = "dsahjpjauf";
        String[] words2 = {"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"};
        System.out.println("Input: " + s2 + ", words = " + Arrays.toString(words2) +
                           " -> Output: " + obj.numMatchingSubseq(s2, words2));
    }
}


/*
 * 
 * function numMatchingSubseq(s, words):
    # Step 1: Track each word's progress using a map of character positions
    create a map `waiting` where each key is a character in s, and value is a list of iterators for words

    # Step 2: Populate the map with the first character of each word
    for word in words:
        add the word's iterator to waiting[word[0]]

    # Step 3: Traverse through string s
    for char in s:
        take all iterators waiting for this char
        clear waiting[char] because we're processing it now
        
        for each iterator:
            move to the next character in the word
            if the iterator is exhausted (word is finished):
                increment result count
            else:
                add the iterator to waiting[next_char]

    return result count

 */