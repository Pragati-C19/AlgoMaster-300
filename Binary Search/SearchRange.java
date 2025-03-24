import java.util.*;

public class SearchRange {
    public int[] searchRange(int[] nums, int target) {

        return nums;
    }

    public static void main(String[] args){
        SearchRange solution = new SearchRange();
        
        int[] nums1 = {5,7,7,8,8,10};
        int[] nums2 = {};
        
        int target1 = 8;
        int[] result1 = solution.searchRange(nums1, target1);
        System.out.println("Result 1: " + Arrays.toString(result1) + "\n");

        int target2 = 6;
        int[] result2 = solution.searchRange(nums1, target2);
        System.out.println("Result 2: " + Arrays.toString(result2) + "\n");

        int target3 = 1;
        int[] result3 = solution.searchRange(nums2, target3);
        System.out.println("Result 3: " + Arrays.toString(result3));

    }
}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. Sorted array is given and target
 * 2. find the first and last occurance of that target
 * 3. return the range of the first and last occurance
 * 4. if that target is not in the array return [-1, -1]
 * 5. Base Case : if array is null then return [-1, -1]
 * 6. Find First and Last Occurrence — You can’t stop at the first match; you
 * need two searches — one for the first position and another for the last.
 * 
 * Pattern :
 * 
 * 1. First pass: Find the leftmost (first) occurrence.
 * 2. Second pass: Find the rightmost (last) occurrence.
 * 3. whenever you find first target, keep searching left (right = mid - 1).
 * 4. whenever you find last target, keep searching right (left = mid + 1).
 * 
 * Pseudo Code :
 * 
 * 1. Brute force
 * 
 * first = -1
 * last = -1
 * 
 * for(int i = 0; i < n; i++){
 *      if(nums[i] == target){
 *          if(first == -1) first = i
 *          last = i
 *      }
 * }
 * 
 * return first != -1 ? [first, last] : [-1, -1] ;
 * 
 * 
 * 2. Binary Search :
 * 
 * Function searchRange(nums, target):
    If nums is empty:
        Return [-1, -1]

    # Find the first occurrence
    left = 0, right = n - 1, first = -1
    While left <= right:
        mid = left + (right - left) / 2
        If nums[mid] == target:
            first = mid
            right = mid - 1  # Keep searching left
        Else if nums[mid] < target:
            left = mid + 1
        Else:
            right = mid - 1

    # Find the last occurrence
    left = 0, right = n - 1, last = -1
    While left <= right:
        mid = left + (right - left) / 2
        If nums[mid] == target:
            last = mid
            left = mid + 1  # Keep searching right
        Else if nums[mid] < target:
            left = mid + 1
        Else:
            right = mid - 1

    Return [first, last]

 * 
 * 
 */
