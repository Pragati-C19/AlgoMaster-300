import java.util.*;

public class SearchInsert {
    

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
