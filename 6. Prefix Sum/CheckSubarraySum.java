import java.util.HashMap;

public class CheckSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1); // Base case to handle exact multiples early
        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int mod = prefixSum % k;

            // Handle negative remainders
            if (mod < 0) mod += k;

            if (seen.containsKey(mod)) {
                // Ensure subarray length is at least 2
                if (i - seen.get(mod) > 1) return true;
            } else {
                // Store the first time this remainder appears
                seen.put(mod, i);
            }
        }

        return false;
    }


    public static void main(String[] args) {
        CheckSubarraySum solver = new CheckSubarraySum();
        System.out.println(solver.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6));  // Output: true
        System.out.println(solver.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6));  // Output: true
        System.out.println(solver.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13)); // Output: false
    }
}


/**
 * 
 * def checkSubarraySum(nums, k):
    seen = {0: -1}  # Remainder -> Index map
    prefix_sum = 0

    for i, num in enumerate(nums):
        prefix_sum += num
        mod = prefix_sum % k

        # Handle negative remainders
        if mod < 0:
            mod += k

        # Check if this remainder has been seen before
        if mod in seen:
            # Ensure subarray length is at least 2
            if i - seen[mod] > 1:
                return True
        else:
            # Store first occurrence of this remainder
            seen[mod] = i

    return False

 */