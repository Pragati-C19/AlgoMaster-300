import java.util.*;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        // Step 1: Convert array to HashSet for fast lookup
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longest = 0;

        // Step 2: Loop through each number
        for (int num : numSet) {
            // Start a sequence only if it's the start (num - 1 not in set)
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int length = 1;

                // Expand the sequence
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    length++;
                }

                // Step 3: Update the maximum length
                longest = Math.max(longest, length);
            }
        }

        return longest;
    }

    // Test the solution
    public static void main(String[] args) {
        LongestConsecutiveSequence obj = new LongestConsecutiveSequence();

        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println("Input: " + Arrays.toString(nums1) + " -> Output: " + obj.longestConsecutive(nums1));

        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println("Input: " + Arrays.toString(nums2) + " -> Output: " + obj.longestConsecutive(nums2));

        int[] nums3 = {1, 0, 1, 2};
        System.out.println("Input: " + Arrays.toString(nums3) + " -> Output: " + obj.longestConsecutive(nums3));
    }
}


/*
 *
 * def longest_consecutive(nums):
    # Step 1: Convert array to set for quick lookup
    num_set = set(nums)
    longest = 0

    # Step 2: Iterate through each number
    for num in num_set:
        # Only start counting if it's the start of a sequence
        if num - 1 not in num_set:
            length = 1
            while num + length in num_set:
                length += 1

            # Step 3: Track the maximum length
            longest = max(longest, length)

    return longest
 
 */