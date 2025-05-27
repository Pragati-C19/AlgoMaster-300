public class LongestOnes {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int maxLen = 0;
        int zerosCount = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Expand the window by including the current element
            if (nums[right] == 0) {
                zerosCount++;
            }
            
            // If the number of zeros exceeds k, shrink the window
            while (zerosCount > k) {
                if (nums[left] == 0) {
                    zerosCount--;
                }
                left++;
            }
            
            // Calculate the window length and update the maximum length
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }

    public static void main(String[] args) {
        LongestOnes solver = new LongestOnes();
        System.out.println(solver.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));  // Output: 6
        System.out.println(solver.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));  // Output: 10
    }
}


/*
 * 
 * Sliding Window:
Use two pointers (left and right) to represent the current window.
Expand the window by moving the right pointer and keep track of how many zeros are in the window.
If the number of zeros exceeds k, shrink the window from the left by moving the left pointer until the number of zeros is less than or equal to k.
Track the maximum length of the window during this process.
 */