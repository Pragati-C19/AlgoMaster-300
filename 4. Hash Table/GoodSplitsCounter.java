import java.util.*;

public class GoodSplitsCounter {
    public int numSplits(String s) {
        // Step 1: Count frequencies of all characters on the right side initially
        Map<Character, Integer> rightCount = new HashMap<>();
        Set<Character> leftCount = new HashSet<>();

        for (char c : s.toCharArray()) {
            rightCount.put(c, rightCount.getOrDefault(c, 0) + 1);
        }

        int goodSplits = 0;

        // Step 2: Traverse the string and update counts
        for (char c : s.toCharArray()) {
            leftCount.add(c);  // Track unique chars on the left
            rightCount.put(c, rightCount.get(c) - 1);

            if (rightCount.get(c) == 0) {
                rightCount.remove(c);  // Remove when count goes to 0
            }

            // Step 3: Compare distinct counts on both sides
            if (leftCount.size() == rightCount.size()) {
                goodSplits++;
            }
        }

        return goodSplits;
    }

    // Test cases
    public static void main(String[] args) {
        GoodSplitsCounter counter = new GoodSplitsCounter();

        String s1 = "aacaba";
        System.out.println("Input: " + s1 + " -> Output: " + counter.numSplits(s1));

        String s2 = "abcd";
        System.out.println("Input: " + s2 + " -> Output: " + counter.numSplits(s2));

        String s3 = "aaa";
        System.out.println("Input: " + s3 + " -> Output: " + counter.numSplits(s3));
    }
}

/*
 * 
 * function countGoodSplits(s):
    # Step 1: Count frequencies of all characters in the string
    rightCount = count frequency of each character in s
    leftCount = empty set to track distinct characters
    
    goodSplits = 0

    # Step 2: Traverse the string and update counts
    for each character c in s:
        add c to leftCount
        decrease rightCount[c] by 1
        
        if rightCount[c] becomes 0:
            remove c from rightCount
        
        # Step 3: Compare distinct counts on both sides
        if size of leftCount equals size of rightCount:
            increment goodSplits
    
    return goodSplits

 */