import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // Sort the array first
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for the first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int total = nums[i] + nums[left] + nums[right];

                if (total == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for the second and third numbers
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    // Move pointers inward after finding a valid triplet
                    left++;
                    right--;
                } else if (total < 0) {
                    left++; // We need a larger number
                } else {
                    right--; // We need a smaller number
                }
            }
        }

        return result;
    }

 
    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();

        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(solution.threeSum(nums1)); // Output: [[-1, -1, 2], [-1, 0, 1]]

        int[] nums2 = {0, 1, 1};
        System.out.println(solution.threeSum(nums2)); // Output: []

        int[] nums3 = {0, 0, 0};
        System.out.println(solution.threeSum(nums3)); // Output: [[0, 0, 0]]
    }
}


/*
 * 
 * function threeSum(nums):
    sort(nums)
    result = []

    for i from 0 to nums.length - 2:
        # Skip duplicates for the first number
        if i > 0 and nums[i] == nums[i - 1]:
            continue

        # Two pointers: left and right
        left = i + 1
        right = nums.length - 1

        while left < right:
            total = nums[i] + nums[left] + nums[right]

            if total == 0:
                result.add([nums[i], nums[left], nums[right]])

                # Skip duplicates for the second and third numbers
                while left < right and nums[left] == nums[left + 1]:
                    left += 1
                while left < right and nums[right] == nums[right - 1]:
                    right -= 1

                # Move pointers inward after finding a valid triplet
                left += 1
                right -= 1

            elif total < 0:
                left += 1  # Need a larger number
            else:
                right -= 1  # Need a smaller number

    return result

 */