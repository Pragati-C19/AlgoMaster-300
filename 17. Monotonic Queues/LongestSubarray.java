import java.util.*;
public record LongestSubarray() {
    public int longestSubarray(int[] nums, int limit){
        
        // Empty Array 
        if(nums.length == 0) return 0;

        // Initialize pointers and max length
        int left = 0;
        int maxLength = 0;

        //Deques for max and min tracking
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        // Iterate over the array with right pointer
        for(int right = 0; right < nums.length; right++){

            int current = nums[right];
            System.out.println("Right pointer at index " + right + " with value: " + current);

            // MmaxDeque in descending order 
            while(!maxDeque.isEmpty() && maxDeque.peekLast() < current){
                maxDeque.pollLast();
            }
            maxDeque.addLast(current);
            System.out.println("MinDeque : " + minDeque);

            // minDeque in ascending order
            while(!minDeque.isEmpty() && minDeque.peekLast() > current){
                minDeque.pollLast();
            }
            minDeque.addLast(current);
            System.out.println("MaxDeque : " + maxDeque);

            // Check if the current window is valid
            while(maxDeque.peek() - minDeque.peek() > limit){
                System.out.println("Max-Min exceeded limit. Shrinking window...");

                // Move left pointer to shrink the window
                if(nums[left] == maxDeque.peek()) maxDeque.poll();
                if(nums[left] == minDeque.peek()) minDeque.poll();
                left++;

                System.out.println("Left pointer moved to index : " + left);
            }

            // Calculate current window size
            int currentLength = right - left + 1;
            maxLength = Math.max(maxLength, currentLength);

            System.out.println("Current valid window size: " + currentLength);
            System.out.println("Max Length so far: " + maxLength);
        } 


        return maxLength;
    }

    public static void main(String[] args){
        LongestSubarray solution = new LongestSubarray();

        int[] nums1 = {8, 2, 4, 7};
        int limit1 = 4;
        System.out.println("Final Result1: " + solution.longestSubarray(nums1, limit1) + "\n");

        int[] nums2 = {10, 1, 2, 4, 7, 2};
        int limit2 = 5;
        System.out.println("Final Result2: " + solution.longestSubarray(nums2, limit2) + "\n");

        int[] nums3 = {4,2,2,2,4,4,2,2};
        int limit3 = 0;
        System.out.println("Final Result3: " + solution.longestSubarray(nums3, limit3) + "\n");
    }
}

/*
 * 
 * Intuition :
 * 
 * 1. one deque for the maximum values
 * 2. One deque for the minimum values
 * 
 * Pattern :
 * 
 * 1. Expand the window by moving right pointer
 * 2. Track max and min in the window :
 * - Use maxDeque (descending order) -> front has the max
 * - Use minDeque (ascending order) -> front has the min
 * 3. If max - min > limit - shrink the window by moving left pointer
 * 4. Track the longest valid window
 * 
 * 
 * Psuedo Code :
 * 
 * 1. Initialize left pointer (l = 0), and result length (maxLen = 0)
 * 2. Create two deques:
 * a) maxDeque to track max elements (descending order)
 * b) minDeque to track min elements (ascending order)
 * 
 * 3. For each right pointer (r) from 0 to n-1:
 * a) Insert nums[r] into maxDeque (maintain descending order)
 * b) Insert nums[r] into minDeque (maintain ascending order)
 * 
 * 4. While (maxDeque.peek() - minDeque.peek() > limit):
 * a) Move left pointer (l++) to shrink the window
 * b) Remove nums[l] from the deques if it’s at the front
 * 
 * 5. Calculate window length: (r - l + 1)
 * 6. Track the maximum window length.
 * 
 * 7. Return maxLen
 * 
 * 
 */
