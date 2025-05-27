import java.util.*;

public class MinWindow {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";

        Map<Character, Integer> targetCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> currentCount = new HashMap<>();
        int left = 0, minStart = 0, minLen = Integer.MAX_VALUE;
        int requiredChars = targetCount.size();
        int formedChars = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            currentCount.put(c, currentCount.getOrDefault(c, 0) + 1);

            if (targetCount.containsKey(c) && currentCount.get(c).intValue() == targetCount.get(c).intValue()) {
                formedChars++;
            }

            // Shrink the window from the left while it's valid
            while (formedChars == requiredChars) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                currentCount.put(leftChar, currentCount.get(leftChar) - 1);
                if (targetCount.containsKey(leftChar) && currentCount.get(leftChar) < targetCount.get(leftChar)) {
                    formedChars--;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        MinWindow solver = new MinWindow();
        System.out.println(solver.minWindow("ADOBECODEBANC", "ABC")); // Output: "BANC"
        System.out.println(solver.minWindow("a", "a")); // Output: "a"
        System.out.println(solver.minWindow("a", "aa")); // Output: ""
    }
}


/**
 * 
 * Count characters in t — We create a frequency map (target_count) for characters in t.
Sliding Window — Use two pointers (left and right) to expand and contract a window over s:
Expand right until the window contains all characters in t (with the right frequency).
Once valid, shrink from left to find the smallest possible valid window.
Track the smallest window — If a valid window is found, keep track of the minimum size and starting index.
Return the result — Extract the substring using the smallest window information.
 */