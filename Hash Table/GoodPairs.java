import java.util.HashMap;

public class GoodPairs {
    public static int numIdenticalPairs(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        int goodPairs = 0;

        for (int num : nums) {
            goodPairs += count.getOrDefault(num, 0);
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        return goodPairs;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1, 1, 3};
        int[] nums2 = {1, 1, 1, 1};
        int[] nums3 = {1, 2, 3};

        System.out.println(numIdenticalPairs(nums1)); // Output: 4
        System.out.println(numIdenticalPairs(nums2)); // Output: 6
        System.out.println(numIdenticalPairs(nums3)); // Output: 0
    }

}


/*
 * 
 * Function numIdenticalPairs(nums):
    Initialize countMap as an empty hashmap
    Initialize goodPairs to 0

    For each number in nums:
        - Add the current count of number from countMap to goodPairs
        - Increment count of the current number in countMap

    Return goodPairs

 */
