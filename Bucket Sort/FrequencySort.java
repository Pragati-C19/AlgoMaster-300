import java.util.*;

public class FrequencySort {

    public String frequencySort(String s){
        // Count frequency of each charecter
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0)+1);
        }

        // Bucket sort setup — create buckets (index = frequency)
        List<Character>[] buckets = new List[s.length()+1];
        for(int i=0; i<=s.length(); i++) {
            char c = s.charAt(i);
            int freq = frequencyMap.get(c);

            if(buckets[freq] == null){
                buckets[freq] = new ArrayList<>();
            }

            if(!buckets[freq].contains(c)){
                buckets[freq].add(c);
            }
        }

        // Build result string from buckets (highest frequency first)
        StringBuilder result = new StringBuilder();
        for(int i=buckets.length-1; i>=0; i--) {
            if(buckets[i] != null) {
                for(int j=0; j<buckets[i].size(); j++){
                    char c = buckets[i].get(j);
                    for(int k=0; k<i; k++){
                        result.append(c);
                    }
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args){
        FrequencySort solution = new FrequencySort();

        System.out.println("Output 1: " + solution.frequencySort("tree"));      // eetr or eer or other valid output
        System.out.println("Output 2: " + solution.frequencySort("cccaaa"));    // cccaaa or aaaccc
        System.out.println("Output 3: " + solution.frequencySort("Aabb"));      // bbAa or bbaA

    }
}

/*
 * 
 * Intuitions :
 * 
 * 1. Count frequencies of each character (using a HashMap).
 * Example: For "tree", count = {t:1, r:1, e:2}
 * 2️. Sort characters by frequency —
 * - One approach: Use a priority queue (max-heap)
 * - Another (faster) approach: Bucket sort
 * 3️. Rebuild the string
 * 
 * Pattern :
 * 
 * 1. Frequency counting is a must (HashMap or Counter).
 * 2. Sorting — we need characters to appear based on frequency, not
 * lexicographical order.
 * 3. Output string — characters must appear together (e.g., "ee" not "eetr").
 * 
 * Pseudo Code :
 * 
 * function frequencySort(s):
 * 1. Count frequencies of each character using a HashMap.
 * 2. Create a "bucket" list, where index = frequency, and value = list of
 * characters with that frequency.
 * 3. Build the result string from the bucket, starting from the highest
 * frequency.
 * 4. Return the result string.
 * 
 * Lexicographical order Means - 
 * "apple" comes before "banana" (because 'a' is before 'b').
 * "abc" comes before "abd" (same prefix, but 'c' is before 'd').
 * "Aardvark" comes before "apple" (because 'A' is before 'a').
 * 
 * 
 */