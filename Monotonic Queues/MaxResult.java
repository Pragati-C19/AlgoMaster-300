public class MaxResult {
    public int maxResult(int[] nums, int k){

        return k;
    }

    public static void main(String[] args){
        MaxResult solution = new MaxResult();

        int[] nums1 = {1, -1, -2, 4, -7, 3};
        int k1 = 2;
        System.out.println("Final Result for nums1: " + solution.maxResult(nums1, k1) + "\n");

        int[] nums2 = {10, -5, -2, 4, 0, 3};
        int k2 = 3;
        System.out.println("Final Result for nums2: " + solution.maxResult(nums2, k2) + "\n");

        int[] nums3 = {1, -5, -20, 4, -1, 3, -6, -3};
        int k3 = 2;
        System.out.println("Final Result for nums3: " + solution.maxResult(nums3, k3) + "\n");
    }
}

/**
 * 
 * 
 * Intuition :
 * 
 * 1. Each index is a stepping stone
 * 2. You can jump forward up to k steps from any stone
 * 3. You can only jump forward, not backward
 * 4. maximize the total score by choosing the optimal path
 * 5. Greedy approach won’t work (e.g., picking the largest next number doesn’t
 * guarantee the highest overall sum).
 * 6. DP with Sliding Window (Deque/MaxHeap) is efficient, ensuring we track the
 * best path dynamically without revisiting unnecessary paths.
 * 
 * 
 * Pattern :
 * 
 * 1. dp[i] -> Max score to reach index i
 * 2. dp[0] = nums[0]
 * 3. dp[i] = nums[i] + max(dp[i-1], dp[i-2], ..., dp[i-k])
 * 4. We only care about the max score in the last k elements, so we track that
 * using a Deque.
 * 
 * 
 * Pseudo Code :
 * 
 * 1. Initialize dp[0] = nums[0]
 * 2. Create a deque to store indices of the "useful" max scores
 * 
 * 3. For each index i from 1 to n-1:
 * a) Ensure deque’s front is within the last k steps (remove front if out of range)
 * b) dp[i] = nums[i] + dp[deque.peekFirst()]
 * c) Maintain deque in descending order (remove worse scores from back)
 * d) Add current index to deque
 * 
 * 4. Return dp[n-1] (final index’s max score)
 * 
 * 
 */