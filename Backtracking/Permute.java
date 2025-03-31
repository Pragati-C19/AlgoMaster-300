import java.util.*;

public class Permute {
    
    // find all permutations  
    public List<List<Integer>> permute(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        Set<Integer> usedSet = new HashSet<>();
        int n = nums.length;

        System.out.println("Starting Backtracking...");
        backtrack(nums, n, result, current, usedSet);

        System.out.println("Final Result: " + result);
        return result;
    }

    // Helper function : to backtrack permutation num recursively
    private void backtrack(int[] nums, int n, List<List<Integer>> result, List<Integer> current, Set<Integer> usedSet) {

        System.out.println("[backtrack] Current: " + current + " | usedSet: " + usedSet + " | result: " + result);
        
        if(current.size() == n){
            result.add(new ArrayList<>(current));
            System.out.println("[backtrack] Added to result: " + current + " | Result: " + result + "\n");
            return;
        }

        for(int num : nums){
            if(!usedSet.contains(num)){
                usedSet.add(num);
                current.add(num);
                System.out.println("[backtrack : adding num] Current : " + current + " | UsedSet: " + usedSet );

                backtrack(nums, n, result, current, usedSet);

                current.remove(current.size() - 1);
                usedSet.remove(num);
                System.out.println("[backtrack : removing num] Current : " + current + " | UsedSet: " + usedSet);
            }
        }
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
