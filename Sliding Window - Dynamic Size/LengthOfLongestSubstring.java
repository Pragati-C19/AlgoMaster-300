import java.util.HashMap;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            if (charIndexMap.containsKey(c) && charIndexMap.get(c) >= left) {
                left = charIndexMap.get(c) + 1;
            }
            
            charIndexMap.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring solver = new LengthOfLongestSubstring();
        System.out.println(solver.lengthOfLongestSubstring("abcabcbb")); // Output: 3
        System.out.println(solver.lengthOfLongestSubstring("bbbbb")); // Output: 1
        System.out.println(solver.lengthOfLongestSubstring("pwwkew")); // Output: 3
    }
}

/**
 * 
 * Sliding Window:

We maintain a window of characters in the string s and adjust the window's size dynamically based on whether the current character is a duplicate of any character in the window.
HashMap:

We can use a hashmap (or dictionary) to store the last seen index of each character. This helps us quickly determine the starting point of the current window and avoid duplicate characters.
Iterate:

Traverse through the string with two pointers: one pointer (right) extends the window, and the other pointer (left) adjusts the window to ensure that it contains only unique characters.
If a duplicate character is encountered, move the left pointer past the last occurrence of that character to remove the duplicate.
Update Maximum Length:

Each time the window is updated, check the current window length and update the maximum length encountered so far.
 */
