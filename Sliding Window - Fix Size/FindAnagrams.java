import java.util.ArrayList;
import java.util.List;

public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        // If p is longer than s, no anagrams are possible
        if (s.length() < p.length()) return result;

        int[] pCount = new int[26];  // Frequency array for characters in p
        int[] sCount = new int[26];  // Frequency array for characters in the current window of s

        // Fill the frequency array for p
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        // Initialize the sliding window for the first k characters of s
        for (int i = 0; i < p.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
        }

        // Check if the first window is an anagram
        if (matches(pCount, sCount)) {
            result.add(0);
        }

        // Slide the window across the rest of s
        for (int i = p.length(); i < s.length(); i++) {
            // Include the next character in the window
            sCount[s.charAt(i) - 'a']++;
            // Remove the character that is no longer in the window
            sCount[s.charAt(i - p.length()) - 'a']--;

            // Check if the current window is an anagram
            if (matches(pCount, sCount)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }

    // Helper function to check if two frequency arrays are equal
    private boolean matches(int[] pCount, int[] sCount) {
        for (int i = 0; i < 26; i++) {
            if (pCount[i] != sCount[i]) return false;
        }
        return true;
    }

  
    public static void main(String[] args) {
        FindAnagrams solver = new FindAnagrams();
        System.out.println(solver.findAnagrams("cbaebabacd", "abc"));  // Output: [0, 6]
        System.out.println(solver.findAnagrams("abab", "ab"));         // Output: [0, 1, 2]
    }
}



/**
 * 
 * def findAnagrams(s, p):
    # If p is longer than s, no anagrams are possible
    if len(p) > len(s):
        return []

    # Count frequencies of characters in p
    p_count = [0] * 26  # Frequency array for characters in p
    s_count = [0] * 26  # Frequency array for characters in the current window of s
    result = []

    # Fill the frequency array for p
    for char in p:
        p_count[ord(char) - ord('a')] += 1

    # Initialize the sliding window for the first k characters of s
    for i in range(len(p)):
        s_count[ord(s[i]) - ord('a')] += 1

    # Check if the first window is an anagram
    if s_count == p_count:
        result.append(0)

    # Slide the window across the rest of s
    for i in range(len(p), len(s)):
        # Include the next character in the window
        s_count[ord(s[i]) - ord('a')] += 1
        # Remove the character that is no longer in the window
        s_count[ord(s[i - len(p)]) - ord('a')] -= 1

        # Check if the current window is an anagram
        if s_count == p_count:
            result.append(i - len(p) + 1)

    return result

 */