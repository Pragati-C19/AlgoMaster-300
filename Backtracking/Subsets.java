import java.lang.Character.Subset;
import java.util.*;

public class Subsets {
    
    // Function to get subsets
    public List<List<Integer>> subsets(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        Set<Integer> usedSet = new HashSet<>();
        int n = nums.length;

        result.add(current);

        System.out.println("Starting Backtracking...");
        backtrack(nums, n, result, current, usedSet);

        System.out.println("Final Result: " + result);
        return result;
    }

    // Helper Function: helping with backtracking recursive action
    private void backtrack(int[] nums, int n, List<List<Integer>> result, List<Integer> current, Set<Integer> usedSet){

        System.out.println("[backtrack] Current: " + current + " | usedSet: " + usedSet + " | result: " + result);

        if(usedSet.size() == n ){
            result.add(new ArrayList<>(current));
            System.out.println("[backtrack : IF] Current: " + current + " | usedSet: " + usedSet + " | result: " + result);
            return;
        }

        for(int num : nums){
            current.add(num);
            usedSet.add(num);
            System.out.println("[backtrack : BEFORE : FOR] Current: " + current + " | usedSet: " + usedSet + " | result: " + result);

            backtrack(nums, n, result, current, usedSet);

            current.remove(current.size() - 1);
            System.out.println("[backtrack : AFTER : FOR] Current: " + current + " | usedSet: " + usedSet + " | result: " + result);

        }

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
 * Intuition :
 * 
 * 1. a integer array is given with unique elements
 * 2. we need to find all possible subsets of the array
 * 3. will use almost same logic as we used for permutate
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
