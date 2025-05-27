public class MinSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;
        
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            
            // Shrink the window while the sum is >= target
            while (currentSum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }
        
        // Return the minimum length if found, otherwise 0
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        MinSubArrayLen solver = new MinSubArrayLen();
        System.out.println(solver.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));  // Output: 2
        System.out.println(solver.minSubArrayLen(4, new int[]{1, 4, 4}));  // Output: 1
        System.out.println(solver.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));  // Output: 0
    }
}


/*** 
 * 
 * 
 * Sliding Window:

We maintain a window (a subarray) and slide it across the array. The goal is to find the shortest subarray whose sum is greater than or equal to the target.
Window Expansion:

Start by expanding the window by moving the right pointer (right) to include more elements in the sum.
Window Contraction:

If the current sum of the window is greater than or equal to the target, attempt to shrink the window by moving the left pointer (left) to minimize the length while still maintaining the sum >= target.
Track the Minimum Length:

After each valid window (where the sum is greater than or equal to the target), calculate the window length and update the minimum length if necessary.
Edge Case:

If no such subarray is found (i.e., if we finish the loop without ever having a sum >= target), return 0.

*/