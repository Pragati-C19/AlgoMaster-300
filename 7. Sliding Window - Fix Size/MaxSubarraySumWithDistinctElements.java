import java.util.HashSet;
import java.util.Set;

public class MaxSubarraySumWithDistinctElements {
    public int maxSubarraySum(int[] nums, int k) {
        int windowSum = 0;
        int maxSum = 0;
        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            // Add the current element to the window sum and the set
            windowSum += nums[i];
            seen.add(nums[i]);

            // If the window size exceeds k, slide the window
            if (i >= k) {
                // Remove the element that is sliding out of the window
                windowSum -= nums[i - k];
                seen.remove(nums[i - k]);
            }

            // If the window size is exactly k, check if all elements are distinct
            if (i >= k - 1) {
                if (seen.size() == k) { // All elements are distinct
                    maxSum = Math.max(maxSum, windowSum);
                }
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaxSubarraySumWithDistinctElements solver = new MaxSubarraySumWithDistinctElements();
        System.out.println(solver.maxSubarraySum(new int[]{1,5,4,2,9,9,9}, 3)); // Output: 15
        System.out.println(solver.maxSubarraySum(new int[]{4,4,4}, 3)); // Output: 0
    }
}


/*
 * 
 * def maxSubarraySum(nums, k):
    # Initialize the sliding window and the maximum sum
    window_sum = 0
    max_sum = 0
    seen = set()  # To store distinct elements in the current window

    # Iterate through the array to check subarrays of length k
    for i in range(len(nums)):
        # Add the current element to the window sum and the set
        window_sum += nums[i]
        seen.add(nums[i])

        # If the window size exceeds k, slide the window
        if i >= k:
            # Remove the element at the start of the window
            window_sum -= nums[i - k]
            seen.remove(nums[i - k])

        # If the window size is exactly k, check if all elements are distinct
        if i >= k - 1:
            if len(seen) == k:  # All elements are distinct
                max_sum = max(max_sum, window_sum)

    return max_sum

 */