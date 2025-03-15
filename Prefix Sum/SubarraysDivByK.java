import java.util.HashMap;

public class SubarraysDivByK {
    public int subarraysDivByK(int[] nums, int k) {
        int count = 0, prefixSum = 0;
        HashMap<Integer, Integer> modCount = new HashMap<>();
        modCount.put(0, 1);  // Base case: sum % k == 0

        for (int num : nums) {
            prefixSum += num;
            int mod = prefixSum % k;

            // Handle negative mods to ensure positive remainder
            if (mod < 0) mod += k;

            // If this mod has been seen before, add to count
            count += modCount.getOrDefault(mod, 0);

            // Track the frequency of this mod
            modCount.put(mod, modCount.getOrDefault(mod, 0) + 1);
        }

        return count;
    }

  
    public static void main(String[] args) {
        SubarraysDivByK solver = new SubarraysDivByK();
        System.out.println(solver.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5)); // Output: 7
        System.out.println(solver.subarraysDivByK(new int[]{5}, 9));  // Output: 0
    }
}


/**
 * 
 * 
 * def subarraysDivByK(nums, k):
    count = 0
    prefix_sum = 0
    mod_count = {0: 1}  # Track frequency of mods

    for num in nums:
        prefix_sum += num
        mod = prefix_sum % k

        # Handle negative mods to keep them positive
        if mod < 0:
            mod += k  

        # If mod already seen, add to count
        if mod in mod_count:
            count += mod_count[mod]

        mod_count[mod] = mod_count.get(mod, 0) + 1

    return count

 */
