import java.util.*;

public class TopKFrequent {

    public List<String> topKFrequent(String[] words, int k) {

        return words;
        
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
