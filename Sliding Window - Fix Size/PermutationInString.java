public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        // If s1 is longer than s2, it's impossible to have a permutation of s1 in s2
        if (s1.length() > s2.length()) return false;

        int[] s1Count = new int[26];  // Frequency array for characters in s1
        int[] s2Count = new int[26];  // Frequency array for the current window in s2

        // Fill the frequency array for s1
        for (char c : s1.toCharArray()) {
            s1Count[c - 'a']++;
        }

        // Initialize the sliding window in s2 for the first len(s1) characters
        for (int i = 0; i < s1.length(); i++) {
            s2Count[s2.charAt(i) - 'a']++;
        }

        // Check if the first window is a permutation of s1
        if (matches(s1Count, s2Count)) {
            return true;
        }

        // Slide the window across the rest of s2
        for (int i = s1.length(); i < s2.length(); i++) {
            // Add the next character to the window
            s2Count[s2.charAt(i) - 'a']++;
            // Remove the character that is no longer in the window
            s2Count[s2.charAt(i - s1.length()) - 'a']--;

            // Check if the current window is a permutation of s1
            if (matches(s1Count, s2Count)) {
                return true;
            }
        }

        return false;
    }

    // Helper function to check if two frequency arrays are equal
    private boolean matches(int[] s1Count, int[] s2Count) {
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] != s2Count[i]) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        PermutationInString solver = new PermutationInString();
        System.out.println(solver.checkInclusion("ab", "eidbaooo"));  // Output: true
        System.out.println(solver.checkInclusion("ab", "eidboaoo"));  // Output: false
    }
}

/**
 * 
 * def checkInclusion(s1, s2):
    # If s1 is longer than s2, it's impossible to have a permutation of s1 in s2
    if len(s1) > len(s2):
        return False

    # Frequency arrays for s1 and the sliding window in s2
    s1_count = [0] * 26  # Frequency array for characters in s1
    s2_count = [0] * 26  # Frequency array for the current window in s2

    # Fill the frequency array for s1
    for char in s1:
        s1_count[ord(char) - ord('a')] += 1

    # Initialize the sliding window in s2 for the first len(s1) characters
    for i in range(len(s1)):
        s2_count[ord(s2[i]) - ord('a')] += 1

    # Check if the first window is a permutation of s1
    if s1_count == s2_count:
        return True

    # Slide the window across the rest of s2
    for i in range(len(s1), len(s2)):
        # Add the next character to the window
        s2_count[ord(s2[i]) - ord('a')] += 1
        # Remove the character that is no longer in the window
        s2_count[ord(s2[i - len(s1)]) - ord('a')] -= 1

        # Check if the current window is a permutation of s1
        if s1_count == s2_count:
            return True

    return False

 */
