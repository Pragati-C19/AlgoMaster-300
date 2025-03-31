import java.util.*;

public class Permute {
    
    // find all permutations  
    public List<List<Integer>> permute(int[] nums) {
        
    }

    // Helper function : to backtrack permutation num recursively
    private void backtrack(int[] nums, int n, List<List<Integer>> result, List<Integer> current, List<Set> usedSet) {

    }

    public static void main(String[] args) {
        Permute solution = new Permute();

        int[] nums1 = {1, 2, 3};
        System.out.println("Output1: " + solution.permute(nums1) + "\n");

        int[] nums2 = {0, 1};
        System.out.println("Output2: " + solution.permute(nums2) + "\n");

        int[] nums3 = {1};
        System.out.println("Output3: " + solution.permute(nums3));
        
    }
}

/**
 * 
 * Intuitions : 
 * 
 * - Find permutation of given array
 * - will use hashset here bcoz it will only track unique num and it can check quickly if the num is present or not
 *
 *  
 * Pattern :
 * 
 * 1. Base case: If the current permutation reaches the size of nums, add it to the result.
 * 2. Recursive step: Try each number if it's not already used:
 * - Add it to the current permutation.
 * - Mark it as used.
 * - Recursively explore further choices.
 * - Backtrack (remove last choice to try the next possibility).
 * 
 * Pseudo Code :
 * 
 * function Permute (int[] nums){
 * 
 *      result = new array
 *      n = nums.length
 *      current = new array
 *      usedSet = new hashset
 * 
 *      backtrack( nums, n, result, current, usedSet )
 *      
 *      return result
 * }
 * 
 * 
 * function backtrack( nums, n, result, current, usedSet,  ){
 *      
 *      if(current.length == n) {
 *          result.append(current);
 *          result;
 *      }
 * 
 *      for(num : nums){
 *          if(!usedSet.contains(num)){
 *              current.add(num);
 *              usedSet.add(num);
 *              
 *              backtrack(nums, n, result, current, usedSet )
 * 
 *              Remove last choices
 * 
 *              current.remove(num);
 *              usedSet.remove(num);
 *              
 *          }
 *      }
 * 
 * }
 * 
 *  
 */
