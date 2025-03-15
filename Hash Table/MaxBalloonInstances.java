import java.util.*;

class MaxBalloonInstances {
    public static int maxNumberOfBalloons(String text) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : text.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        String balloon = "balloon";
        int minBalloons = Integer.MAX_VALUE;

        for (char c : balloon.toCharArray()) {
            int needed = (c == 'l' || c == 'o') ? count.getOrDefault(c, 0) / 2 : count.getOrDefault(c, 0);
            minBalloons = Math.min(minBalloons, needed);
        }

        return minBalloons;
    }

    public static void main(String[] args) {
        System.out.println(maxNumberOfBalloons("nlaebolko")); // Output: 1
        System.out.println(maxNumberOfBalloons("loonbalxballpoon")); // Output: 2
        System.out.println(maxNumberOfBalloons("leetcode")); // Output: 0
    }
}

/*
 * 
 * Function maxBalloonInstances(text):
    Count frequency of each character in text
    Define target word = "balloon"
    Count needed characters:
        b, a, l (needs 2), o (needs 2), n
    Compute how many times we can form "balloon":
        Minimum of (count of b, a, n, l//2, o//2)
    Return that minimum

 */