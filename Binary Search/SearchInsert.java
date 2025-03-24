import java.util.*;

public class SearchInsert {
    
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;

        for(int i = 0 ; i < n - 1 ; i++){
            // if u found the target or u can place that target on the specific index 
            if(nums[i] >= target) return i;

            // If ur target is going to be placed at end of the string
            if(nums[i] < target) return i+1;
            
        }

        return target;
    }

    public static void main(String[] args){
        SearchInsert solution = new SearchInsert();

        int[] nums = {1,3,5,6};
        
        int target1 = 5;
        int result1 = solution.searchInsert(nums, target1);
        System.out.println("Result1 : " + result1 + "\n");

        int target2 = 2;
        int result2 = solution.searchInsert(nums, target2);
        System.out.println("Result2 : " + result2 + "\n");

        int target3 = 7;
        int result3 = solution.searchInsert(nums, target3);
        System.out.println("Result3 : " + result3 + "\n");

        int target4 = 0;
        int result4 = solution.searchInsert(nums, target4);
        System.out.println("Result4 : " + result4);

    }

}


/**
 * 
 * Understand the problem 
 * 
 * 1. Array is already sorted and target is given
 * 2. Try to find that target in array
 * 3. if u found the target then return it's position 
 * 4. if u didn't found the target then return it's position where it will have be stored
 * 
 * Intuitions : 
 * 
 * 1. he array is sorted —> Binary Search is the ideal approach for O(log n) complexity.
 * 2. If the target exists, return the index.
 *      - If the target doesn’t exist, return the first index where nums[i] >= target.
 * 3. This problem uses Lower Bound Search
 * 
 * Pattern : 
 * 
 * 1. we need the first position where nums[i] >= target
 * 2. In [1, 3, 5, 6], target 5 —> Found at index 2.
 * 3. In [1, 3, 5, 6], target 2 —> Should be inserted at index 1 (between 1 and 3).
 * 
 * Pseudo Code :
 * 
 * 1. Brute force : 
 * 
 * for( int i = 0; i < nums.length - 1; i++){
 *      if(nums[i] == target){
 *          return i;
 *      }
 *      else if(nums[i] > target){
 *          return i;
 *      }
 * }
 * 
 * return nums.length
 * 
 * 
 * 2. Binary Search :
 * - If nums[mid] == target, return mid.
 * - If nums[mid] < target, search the right half (left = mid + 1).
 * - If nums[mid] > target, search the left half (right = mid - 1).
 * 
 * left = 0
 * right = nums.length - 1
 * 
 * while(left <= right){
 *      mid = (left + right) / 2
 * 
 *      if(nums[mid] == target){
 *          return mid;
 *      } else if(nums[mid] < target){
 *          left = mid + 1    // move right
 *      } else {
 *          right = mid - 1   // move left
 *      }
 * }
 * 
 * # Left will point to the insert position after the loop
 * return left
 * 
 */
