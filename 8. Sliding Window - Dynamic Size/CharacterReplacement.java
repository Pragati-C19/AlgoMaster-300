import java.util.HashMap;

public class CharacterReplacement {
    public int characterReplacement(String s, int k) {
        HashMap<Character, Integer> freq = new HashMap<>();
        int left = 0;
        int maxLen = 0;
        int maxCount = 0;
        
        for (int right = 0; right < s.length(); right++) {
            // Add the current character to the window
            freq.put(s.charAt(right), freq.getOrDefault(s.charAt(right), 0) + 1);
            
            // Update the maximum frequency of any character in the window
            maxCount = Math.max(maxCount, freq.get(s.charAt(right)));
            
            // If the number of characters to replace exceeds k, shrink the window
            if (right - left + 1 - maxCount > k) {
                freq.put(s.charAt(left), freq.get(s.charAt(left)) - 1);
                left++;
            }
            
            // Update the maximum length of the valid window
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }

    public static void main(String[] args) {
        CharacterReplacement solver = new CharacterReplacement();
        System.out.println(solver.characterReplacement("ABAB", 2)); // Output: 4
        System.out.println(solver.characterReplacement("AABABBA", 1)); // Output: 4
    }
}

/**
 * 
 * Sliding Window:

We maintain a window (a substring) of characters in the string.
The goal is to try to maximize the length of this window while ensuring that we can change at most k characters to match the most frequent character in the current window.
Character Frequency:

We keep track of the frequency of each character in the window using a frequency map.
The idea is to maintain a window where the number of characters that need to be changed is less than or equal to k.
Window Expansion and Contraction:

Expand the window by moving the right pointer (right).
If the window becomes invalid (i.e., the number of characters to be changed exceeds k), move the left pointer (left) to shrink the window until it's valid again.
Valid Window:

A valid window is one where the total number of characters that need to be changed (i.e., the difference between the window size and the frequency of the most common character in the window) is less than or equal to k.
Update the maximum length whenever a valid window is found.
 */