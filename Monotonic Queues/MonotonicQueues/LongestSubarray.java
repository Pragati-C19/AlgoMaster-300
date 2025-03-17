
public record LongestSubarray() {

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
