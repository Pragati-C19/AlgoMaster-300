import java.util.*;

public class Subsets {
    
    // Function to get subsets
    public List<List<Integer>> subsets(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        System.out.println("Starting Backtracking...");
        backtrack(nums, 0, result, current);

        System.out.println("Final Result: " + result);
        return result;
    }

    // Helper Function: helping with backtracking recursive action
    private void backtrack(int[] nums, int start, List<List<Integer>> result, List<Integer> current){

        result.add(new ArrayList<>(current));
        System.out.println("[backtrack] Current: " + current + " | Start: " + start + " | result: " + result + "\n");

        for (int i = start; i < nums.length; i++) {
            System.out.println(" i : " + i + " | Start : " + start);
            
            current.add(nums[i]);
            System.out.println("[backtrack] Adding " + nums[i] + " to current");

            backtrack(nums, i + 1, result, current);
            
            current.remove(current.size() - 1);
            System.out.println("[backtrack] Removing last element from current | Current: " + current);
        
        }

        return;

    }

    public static void main(String[] args){
        Subsets solution = new Subsets();

        int[] nums1 = {1, 2, 3};
        System.out.println("Output1 : " + solution.subsets(nums1) + "\n");

        int[] nums2 = {0};
        System.out.println("Output2 : " + solution.subsets(nums2) + "\n");
    }

}


/**
 * 
 * ? Rememeber : will use index instead of set now.
 * 
 * Intuition :
 * 
 * 1. a integer array is given with unique elements
 * 2. we need to find all possible subsets of the array
 * 3. will use almost same logic as we used for permutate
 * 
 * 
 * 
 * Pattern :
 * 
 * 1. Will use backtracking
 * 2. will use a helper function to generate all subsets recursively
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * function subset(int[] nums){
 * 
 *      result = new array
 *      n = nums.length
 *      current = new array
 *      usedSet = new hashset
 *      
 *      result.add(new ArrayList(current))
 *      backtrack( nums, n, result, usedSet )
 *      
 *      return result
 * 
 * }
 * 
 * 
 * function backtrack(nums, n, result, current, usedSet){
 *      
 *      if(usedSet.size() == n){
 *          result.add(new Array(current) 
 *          return;   
 *      }
 *  
 *      for(num : nums){
 *          current.add(num)
 *          usedSet.add(num)
 *          backtrack(nums, n, result, current, usedSet)
 *          current.remove(current.length - 1)
 *      }
 * }
 * 
 */
