import java.util.HashMap;

public class ContainsDuplicateII {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> seen = new HashMap<>();

        // Loop through the array
        for (int i = 0; i < nums.length; i++) {
            if (seen.containsKey(nums[i]) && i - seen.get(nums[i]) <= k) {
                return true;  // Found a valid pair
            }
            // Update the last seen index of the number
            seen.put(nums[i], i);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;

        System.out.println("Contains nearby duplicate? " + containsNearbyDuplicate(nums, k));
    }
}

/**
 * 
 * 
 * def containsNearbyDuplicate(nums, k):
    # Use a hashmap to store the last seen index of each number
    seen = {}

    # Loop through the array
    for i, num in enumerate(nums):
        # If number exists and the difference of indices <= k, return True
        if num in seen and i - seen[num] <= k:
            return True
        # Update the latest index of this number
        seen[num] = i

    return False

 */