import java.util.*;

public class Search {
    
    public int search(int[] nums, int target) {
        
        return -1;
    }

    public static void main(String[] args){
        Search solution = new Search();

        int[] nums1 = {4,5,6,7,0,1,2};
        int[] nums2 = { };
        
        int target1 = 0;
        System.out.println("Result1: " + solution.search(nums1, target1) + "\n");

        int target2 = 3;
        System.out.println("Result2: " + solution.search(nums1, target2) + "\n");

        int target3 = 0;
        System.out.println("Result3: " + solution.search(nums2, target3));

    }

}

/*
 * 
 * Intuitions : 
 * 
 * 1. I think it's just checking the nums[i] == target and return i
 * 2. otherwise return -1
 * 
 * 
 * Pattern :
 * 
 * 
 * 
 * Pseudo Code : 
 * 
 * 1. Brute force:
 * 
 * function search(nums, target){
 *     
 * if(nums.length == 0) return -1;
 * 
 * for( int i = 0; i < nums.length - 1; i++){
 *      if(nums[i] == target){
 *          return i;
 *      }
 * }
 * 
 * return -1;
 * 
 * }
 * 
 * 
 */
