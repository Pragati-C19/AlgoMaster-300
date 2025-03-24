import java.util.*;

public class ReversePairs {
    
    public int reversePairs(int[] nums){
        int n = nums.length;
        
        // Base Case :
        if( n <= 1) return 0;

        return mergeSort(nums, 0, n-1);
    }

    private int mergeSort(int[] nums, int left, int right){
        int count = 0;

        
        return count ; 
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right){
        int count = 0;

        return count;
    }


    public static void main(String[] args){
        ReversePairs solution = new ReversePairs();

        int[] nums1 = {1,3,2,3,1};
        int[] nums2 = {2,4,3,5,1};

        System.out.println("Output1: " + solution.reversePairs(nums1) + "\n");
        System.out.println("Output2: " + solution.reversePairs(nums2) + "\n");
    }

}


/**
 * 
 * 
 * Understand the problem :
 * 
 * 1. Look for pair (i, j) where i < j and nums[i] > 2 * nums[j] i.e. nums[i] should be double of nums[j]
 * 
 * Brute force : 
 * count = 0
 * for i from 0 to nums.length -1 :
 *      for j from i + 1 to nums.length -1 :
 *          if nums[i] > 2 * nums[j]: 
 *              count += 1
 * return count
 * 
 * Time complexity will be O(n^2)
 * 
 * 
 * 
 * Intuitions :
 * 
 * 1. We need to use merge sort as it will sort array in halves
 * 2. left side will be focused on smaller numbers and right will be focuse on bigger nums
 * 3. We need to count the number of pairs where nums[i] > 2 * nums
 * 4. Sorting allows us to skip unnecessary checks
 * 5. time complexity O(n long n)
 * 
 * 
 * 
 * Pattern :
 * 
 * 1. We only compare numbers from the left half to the right half — never the other way around.
 * 2. if nums[i] in the left half, fails for some nums[j] in the right half in nums[i] ≤ 2 * nums[j] then 
        -> All smaller numbers in the right half will also fail
 * 3. count pairs in left part, right part, and mixed one 
 * 
 * 
 * Pseudo Code :
 * 
 * function reversePairs()
 *      if(nums.length <= 1) return 0;
 *      
 *      return mergeSort(nums, 0, nums.length - 1);
 * 
 * function mergeSort(nums, left, right)
 *      if(left >= right) return 0;
 * 
 *      mid = (left + right) / 2
 *      count = 0;
 *      
 *      # Count in left half, right half, and across the halves
 *      count+=mergeSort(nums. left, mid)
 *      count+= mergeSort(nums, mid+1, right)
 *      count+= mergeAndCount(nums, left, mid, right)
 * 
 *      return count
 * 
 * function mergeAndCount(nums, left, mid, right)
 *      coutn = 0;
 *      j = mid + 1
 *      
 *      for(i = j ; i < mid; i++){
 *          while(j <= right && nums[i] > 2*nums[j])
 *              j += 1
 *          count+= j - (mid+1)
 *      }
 *      
 *      merge the two sorted halves nums[left:mid] and nums[mid+1:right]
 *      
 *      return count;
 */
