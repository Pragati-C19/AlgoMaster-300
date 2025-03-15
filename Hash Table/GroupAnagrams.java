import java.util.*;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        // Use a hashmap to group words by their sorted "signature"
        HashMap<String, List<String>> map = new HashMap<>();

        // Loop through each string
        for (String s : strs) {
            // Convert string to char array, sort it, then back to string
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);

            // If this sorted pattern doesn't exist in map, create a new list
            map.putIfAbsent(sorted, new ArrayList<>());

            // Add the original string to the corresponding anagram group
            map.get(sorted).add(s);
        }

        // Return all grouped anagrams as a list of lists
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println("Grouped Anagrams: " + groupAnagrams(strs));
    }
}


/*
 * 
 * def groupAnagrams(strs):
    # Create a hashmap to group anagrams
    anagram_map = {}

    # For each word in strs
    for word in strs:
        # Sort the word to create a "signature" for anagrams
        sorted_word = ''.join(sorted(word))
        
        # Add the word to the corresponding anagram group
        if sorted_word not in anagram_map:
            anagram_map[sorted_word] = []
        
        anagram_map[sorted_word].append(word)

    # Return all the grouped anagrams
    return list(anagram_map.values())

 */