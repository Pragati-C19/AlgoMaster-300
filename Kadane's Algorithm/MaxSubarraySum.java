public class MaxSubarraySum {
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = 0;

        for (int n : nums) {
            currentSum = Math.max(n, currentSum + n);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Max Subarray Sum: " + maxSubArray(nums));
    }
}







/**
 * 
 * def max_subarray(nums):
    max_sum = nums[0]
    curr_sum = 0

    for n in nums:
        curr_sum = max(n, curr_sum + n)
        max_sum = max(max_sum, curr_sum)
    
    return max_sum

 */