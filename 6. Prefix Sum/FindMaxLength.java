import java.util.HashMap;

public class FindMaxLength {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1); // Base case to handle balance from start
        int count = 0, maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            count += nums[i] == 1 ? 1 : -1;

            if (seen.containsKey(count)) {
                maxLen = Math.max(maxLen, i - seen.get(count));
            } else {
                seen.put(count, i);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        FindMaxLength solver = new FindMaxLength();
        System.out.println(solver.findMaxLength(new int[]{0, 1}));           // Output: 2
        System.out.println(solver.findMaxLength(new int[]{0, 1, 0}));         // Output: 2
        System.out.println(solver.findMaxLength(new int[]{0, 1, 1, 1, 1, 1, 0, 0, 0})); // Output: 6
    }
}

/**
 * 
 * def findMaxLength(nums):
    count = 0  
    seen = {0: -1}  # Track count balance at indices
    max_len = 0

    for i, num in enumerate(nums):
        count += 1 if num == 1 else -1

        if count in seen:
            max_len = max(max_len, i - seen[count])
        else:
            seen[count] = i

    return max_len

 */
