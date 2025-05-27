import java.util.*;

public class ReorganizeString {
    public String reorganizeString(String s) {
        // Step 1: Count frequency of each character
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Use a max heap to sort by frequency
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = 
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        maxHeap.addAll(countMap.entrySet());

        StringBuilder result = new StringBuilder();
        Map.Entry<Character, Integer> prevEntry = null;

        // Step 3: Build the string by alternating characters
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> current = maxHeap.poll();
            result.append(current.getKey());

            // Push previous entry back if it still has remaining count
            if (prevEntry != null && prevEntry.getValue() > 0) {
                maxHeap.offer(prevEntry);
            }

            // Update the count of the current character
            current.setValue(current.getValue() - 1);
            prevEntry = current;
        }

        // Step 4: Check if result is valid
        return result.length() == s.length() ? result.toString() : "";
    }

    public static void main(String[] args) {
        ReorganizeString obj = new ReorganizeString();

        String s1 = "aab";
        System.out.println("Input: " + s1 + " -> Output: " + obj.reorganizeString(s1));

        String s2 = "aaab";
        System.out.println("Input: " + s2 + " -> Output: " + obj.reorganizeString(s2));
    }
}


/*
 * 
 * from collections import Counter
import heapq

def reorganizeString(s):
    # Step 1: Count frequency of each character
    char_count = Counter(s)
    
    # Step 2: Add all characters to a max heap (negative count for max behavior)
    max_heap = [(-count, char) for char, count in char_count.items()]
    heapq.heapify(max_heap)
    
    # Step 3: Build the result string
    prev_count, prev_char = 0, ""
    result = []

    while max_heap:
        # Pop the most frequent character
        count, char = heapq.heappop(max_heap)
        result.append(char)

        # If previous character still has remaining count, push it back
        if prev_count < 0:
            heapq.heappush(max_heap, (prev_count, prev_char))

        # Update previous character
        prev_count, prev_char = count + 1, char

    # Step 4: Return result if valid, else return ""
    result_str = "".join(result)
    return result_str if len(result_str) == len(s) else ""

 */
