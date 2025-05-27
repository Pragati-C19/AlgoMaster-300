import java.util.HashMap;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0, prefixSum = 0;
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, 1); // Base case for sum exactly k

        for (int num : nums) {
            prefixSum += num;
            if (seen.containsKey(prefixSum - k)) {
                count += seen.get(prefixSum - k);
            }
            seen.put(prefixSum, seen.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    // Test Example
    public static void main(String[] args) {
        SubarraySumEqualsK solver = new SubarraySumEqualsK();
        System.out.println(solver.subarraySum(new int[]{1, 1, 1}, 2)); // Output: 2
        System.out.println(solver.subarraySum(new int[]{1, 2, 3}, 3));  // Output: 2
    }
}


/**
 * 
 * def subarraySum(nums, k):
    count = 0
    prefix_sum = 0
    seen = {0: 1}  # Track prefix sums

    for num in nums:
        prefix_sum += num
        if prefix_sum - k in seen:
            count += seen[prefix_sum - k]
        seen[prefix_sum] = seen.get(prefix_sum, 0) + 1

    return count

 */