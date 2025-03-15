public class MaxAverageSubarray {
    public double findMaxAverage(int[] nums, int k) {
        // Calculate the sum of the first k elements
        double windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        double maxSum = windowSum;

        // Use sliding window to calculate sum of each subsequent subarray of length k
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k];  // Slide the window
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum / k;  // Return the maximum average
    }


    public static void main(String[] args) {
        MaxAverageSubarray solver = new MaxAverageSubarray();
        System.out.println(solver.findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));  // Output: 12.75
        System.out.println(solver.findMaxAverage(new int[]{5}, 1));                    // Output: 5.0
    }
}


/*
 * 
 * def findMaxAverage(nums, k):
    window_sum = sum(nums[:k])  # Sum of first k elements
    max_sum = window_sum

    for i in range(k, len(nums)):
        window_sum += nums[i] - nums[i - k]  # Slide the window by 1
        max_sum = max(max_sum, window_sum)

    return max_sum / k  # Return the maximum average

 */