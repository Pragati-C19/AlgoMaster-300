import java.util.*;

public class TwoSumSorted {

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        // Two-pointer approach
        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1}; // 1-based index
            } else if (sum < target) {
                left++; // Move left pointer to increase sum
            } else {
                right--; // Move right pointer to decrease sum
            }
        }

        return new int[]{}; // Should never hit this line
    }

  
    public static void main(String[] args) {
        TwoSumSorted solution = new TwoSumSorted();

        int[] numbers1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println(Arrays.toString(solution.twoSum(numbers1, target1))); // Output: [1, 2]

        int[] numbers2 = {2, 3, 4};
        int target2 = 6;
        System.out.println(Arrays.toString(solution.twoSum(numbers2, target2))); // Output: [1, 3]

        int[] numbers3 = {-1, 0};
        int target3 = -1;
        System.out.println(Arrays.toString(solution.twoSum(numbers3, target3))); // Output: [1, 2]
    }
}

/*
 * 
 * function twoSum(numbers, target):
    left = 0
    right = numbers.length - 1

    while left < right:
        currentSum = numbers[left] + numbers[right]
        
        if currentSum == target:
            return [left + 1, right + 1]  # Return 1-based index
        elif currentSum < target:
            left += 1
        else:
            right -= 1

    return []  # Should never hit this because the problem guarantees a solution

 */

