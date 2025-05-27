import java.util.*;

public class TopKFrequent {

    public List<String> topKFrequent(String[] words, int k) {
        
        // Count frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (int i=0; i<words.length; i++){
            String word = words[i];
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        System.out.println("Frequency Map: " + frequencyMap); 

        // Sort words alphabetically
        List<String> lexicographicalList = new ArrayList<>(frequencyMap.keySet());
        Collections.sort(lexicographicalList);
        System.out.println("Lexicographical List: " + lexicographicalList);

        // Bucket sort setup 
        List<String>[] buckets = new List[words.length + 1];

        for(String word : lexicographicalList) {
            int freq = frequencyMap.get(word);

            System.out.println("Processing Word and frequency: " + word + " -> " + freq + " ");

            if(buckets[freq] == null){
                buckets[freq] = new ArrayList<>();
            }

            buckets[freq].add(word);
        }
        System.out.println("Bucket Created: " + Arrays.toString(buckets));

        // Get top k words from highest frequency 
        List<String> result = new ArrayList<>();
        for(int i = buckets.length - 1; i >= 0; i--) {
            if(buckets[i] != null){
                result.addAll(buckets[i]);
            }
        }
        System.out.println("Result List: " + result.toString());

        // Returning only first k elements
        return result.subList(0, k);
        
    }

    public static void main(String[] args){
        TopKFrequent solution = new TopKFrequent();

        String[] words1 = {"i", "love", "leetcode", "i", "love", "coding"};
        int k1 = 2;
        System.out.println("Result1: " + solution.topKFrequent(words1, k1) + "\n");

        String[] words2 = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        int k2 = 4;
        System.out.println("Result2: " + solution.topKFrequent(words2, k2) + "\n");

    }


}

/**
 * 
 * 
 * Intuition :
 * 
 * 1. Count frequencies of each character (using a HashMap).
 * Example: For "tree", count = {t:1, r:1, e:2}
 * 2️. Sort characters by frequency — approach: Bucket sort
 * 3️. Return the top K frequent characters
 * 
 * 
 * Pattern :
 * 
 * 1. Count Frequencies — Use HashMap to count word frequencies.
 * 2️. Bucket Setup — Create a bucket array buckets[]:
 * - Index = frequency
 * - Value = list of words with that frequency
 * 3️. Sort Words Lexicographically — Before adding words to buckets, sort words
 * alphabetically to handle ties.
 * 4️. Build the Result — Traverse buckets from highest frequency to lowest,
 * collecting words until we get k results.
 * 
 * 
 * Pseudo Code :
 * 
 * function topKFrequent(words, k):
    # Step 1: Count frequencies
    frequency_map = count frequency of each word

    # Step 2: Sort words alphabetically first (handles same frequency tie)
    words.sort()

    # Step 3: Create buckets (index = frequency)
    buckets = array of lists, size = max frequency + 1
    for word, freq in frequency_map:
        buckets[freq].add(word)

    # Step 4: Collect words from highest frequency bucket down
    result = []
    for i from buckets.length - 1 to 0:
        if buckets[i] is not empty:
            for word in buckets[i]:
                result.add(word)
                if result.size() == k:
                    return result

    return result
 * 
 * 
 * 
 */
