public class MaxCircularSubarraySum {
    public static int maxCircularSubarray(int[] nums) {
        int totalSum = 0;
        int maxSum = nums[0], currMax = 0;
        int minSum = nums[0], currMin = 0;

        for (int n : nums) {
            totalSum += n;

            currMax = Math.max(n, currMax + n);
            maxSum = Math.max(maxSum, currMax);

            currMin = Math.min(n, currMin + n);
            minSum = Math.min(minSum, currMin);
        }

        // Handle all-negative case
        return (maxSum > 0) ? Math.max(maxSum, totalSum - minSum) : maxSum;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, -2, 3, -2};
        int[] nums2 = {5, -3, 5};
        int[] nums3 = {-3, -2, -3};

        System.out.println("Example 1 Output: " + maxCircularSubarray(nums1));
        System.out.println("Example 2 Output: " + maxCircularSubarray(nums2));
        System.out.println("Example 3 Output: " + maxCircularSubarray(nums3));
    }
}


/**
 * 
 * def max_circular_subarray(nums):
    # Standard max subarray (Kadane’s)
    max_sum = curr_max = nums[0]
    min_sum = curr_min = nums[0]
    total_sum = 0

    for n in nums:
        total_sum += n
        curr_max = max(n, curr_max + n)
        max_sum = max(max_sum, curr_max)

        curr_min = min(n, curr_min + n)
        min_sum = min(min_sum, curr_min)

    # Edge case: all numbers are negative
    return max(max_sum, total_sum - min_sum) if max_sum > 0 else max_sum

 */