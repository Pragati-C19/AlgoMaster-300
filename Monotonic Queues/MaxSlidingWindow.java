import java.util.*;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {

        // If input array is empty or window size is invalid
        if (nums == null || nums.length == 0 || k <= 0)
            return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1]; // Result array to store the max of each window
        Deque<Integer> deque = new ArrayDeque<>(); // Deque to store indices of useful elements

        // Traverse the array
        for (int i = 0; i < n; i++) {
            System.out.println("Processing index " + i + " with value " + nums[i]);

            // Remove Elements from the front of the deque if they are out of this window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                System.out.println("Removing index " + deque.peekFirst() + " (out of window)");
                deque.pollFirst();
            }

            // Remove elements from the back if they are smaller than the current element
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                System.out.println("Removing index " + deque.peekLast() + " (smaller value)");
                deque.pollLast();
            }

            // Add current index to deque
            deque.offerLast(i);
            System.out.println("Added index " + i + " to deque");

            // Record the maximum value for this window
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
                System.out.println("Window [" + (i - k + 1) + " to " + i + "] -> Max: " + nums[deque.peekFirst()]);
            }

            System.out.println("Current Deque: " + deque + "\n");
        }

        return result;
    }

    public static void main(String[] args) {
        MaxSlidingWindow solution = new MaxSlidingWindow();

        int[] nums1 = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k1 = 3;
        System.out.println("Result1: " + Arrays.toString(solution.maxSlidingWindow(nums1, k1)) + "\n");

        int[] nums2 = { 1 };
        int k2 = 1;
        System.out.println("Result2: " + Arrays.toString(solution.maxSlidingWindow(nums2, k2)) + "\n");

    }

}

/*
 * 
 * 
 * Intuition :
 * 
 * 1. We need to find the maximum element in every subarray (or window) of size
 * k.
 * 2. A naive solution would be to calculate the max value by checking each
 * window one by one — but that would be too slow for large inputs.
 * 3. Instead of recalculating the max for every window, we can use a more
 * efficient strategy using a Deque (Double-Ended Queue) to store useful
 * elements only:
 * - Store elements in decreasing order in the deque.
 * - Remove the elements that are out of the window or smaller than the current
 * element.
 * - The front of the deque will always have the maximum element of the current
 * window.
 * 
 * 
 * Pattern :
 * 
 * 1. Remove elements out of the current window (those that are out of the left
 * side).
 * 2. Remove elements from the back of the deque if they are smaller than the
 * current element (because they can't be the max in the future).
 * 3. Add the current element's index at the back of the deque.
 * 4. The front of the deque will always store the maximum element for the
 * current window.
 * 
 * 
 * Pusedo Code :
 * 
 * 
 * 1️. Create an empty deque to store indices.
 * 2️. Loop through the array:
 * a) Remove indices from the front if they’re out of the current window.
 * b) Remove smaller elements from the back (they’re useless now).
 * c) Add the current index to the deque.
 * d) If we’ve processed at least `k` elements, record the maximum (deque’s
 * front).
 * 3️. Return the result array.
 * 
 * function maxSlidingWindow(nums, k):
 * result = []
 * deque = empty deque
 * 
 * for i = 0 to n-1:
 * # Step 1: Remove elements out of the current window
 * if deque is not empty and deque.front <= i - k:
 * remove from front of deque
 * 
 * # Step 2: Remove smaller elements (they can't be maximum)
 * while deque is not empty and nums[deque.back] < nums[i]:
 * remove from back of deque
 * 
 * # Step 3: Add current element's index at the back
 * add i to back of deque
 * 
 * # Step 4: Add to result once window is of size k
 * if i >= k - 1:
 * add nums[deque.front] to result
 * 
 * return result
 * 
 * 
 * 
 * Input: nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
 * Output: [3,3,5,5,6,7]
 * 
 * 1️. First window:
 * [1, 3, -1] → Maximum = 3
 * 
 * 2️. Move window one step right:
 * [3, -1, -3] → Maximum = 3
 * 
 * 3️. Move window one step right:
 * [-1, -3, 5] → **Maximum = 5`
 * 
 * 4️. Move window one step right:
 * [-3, 5, 3] → **Maximum = 5`
 * 
 * 5️. Move window one step right:
 * [5, 3, 6] → **Maximum = 6`
 * 
 * 6️. Move window one step right:
 * [3, 6, 7] → **Maximum = 7`
 * 
 * 7. Final Output:
 * [3, 3, 5, 5, 6, 7]
 * 
 * 
 */
