import java.util.*;

public class CombinationSum2 {
    
    // Function to find all combinations whos sum is equal to target
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        Arrays.sort(candidates);

        System.out.println("Starting Backtracking...");
        backtrack(candidates, target, current, result, 0);

        System.out.println("Final Result: " + result);
        return result;

    }

    // Helper Function : to backtrack recurssively 
    private void backtrack(int[] nums, int target, List<Integer> current, List<List<Integer>> result, int start){
        
        System.out.println("[backtrack] Target: " + target + " | Current: " + current + " | result: " + result);

        // Base Case
        if(target == 0){
            result.add(new ArrayList<>(current));
            System.out.println("[backtrack] Added to result: " + current + " | Result: " + result + "\n");
            return;
        }

        //? another method to write this for loop without sorting array 
        for (int i = start; i < nums.length; i++) {
            
            System.out.println(" num : " + nums[i]);
            
            // Skip duplicate numbers at the same level
            if (i > start && nums[i] == nums[i - 1]) continue;

            //Break the loop for unnecessary calculations
            if(nums[i] > target) break;
             
            current.add(nums[i]);
            System.out.println(" [IF : BEFORE] Current : " + current + " | Target : " + target + " | Result : " + result + "\n");

            backtrack(nums, target - nums[i], current, result, i + 1);
            
            current.remove(current.size() - 1); // Undo choice
            System.out.println(" [IF : AFTER] Current : " + current + " | Target : " + target + " | Result : " + result);

        }
    }

    public static void main(String[] args){
        CombinationSum2 solution = new CombinationSum2();

        int[] candidates1 = {10,1,2,7,6,1,5};
        int target1 = 8;
        System.out.println("Output1 : " + solution.combinationSum2(candidates1, target1) + "\n");

        int[] candidates2 = {2,5,2,1,2};
        int target2 = 5;
        System.out.println("Output2 : " + solution.combinationSum2(candidates2, target2) + "\n");

    }
    
}


/*
 * //? It's almost same as Combination sum so let's not waste time in writing from scratch 
 * 
 * //? Let's think of a bit different approach without sum variable
 *  
 * Intuitions : 
 * 
 * 1. We have given an array with target
 * 2. We need to find all combinations of integer from array should be equal to sum of nums
 * 3. let's use backtracking here
 * - result will be return at  target = 0
 *  
 * 
 * Pattern :
 * 
 * 1. Base case: If the target equal to 0, add it to the result.
 * 2. Recursive step: Try each number as many times as u want
 * 3. In for loop :
 * - To reduce too much looping : If the nums[i] is greater than the target, skip it
 * - To handle duplicates : i > start and num[i] == num[i - 1] continue;
 * - Add nums[i] to the current.
 * - Recursively explore further choices
 * - Backtrack (remove last choice to try the next possibility)
 * - like current.remove(current.lenght - 1)
 * 
 * 
 * Pseudo Code :
 * 
 * function combinationSum {
 *      
 *      result = new array
 *      current = new array
 *      
 *      Arrays.sort(candidetes)
 * 
 *      backtrack(nums, target, current, result, 0)
 *      
 *      return result
 * }
 * 
 * 
 * function backtrack(nums, target, current, result, start){
 *      
 *      if(target == 0){
 *          result.add(new Array(current))
 *          return;
 *      } 
 * 
 *      for(int i = 0; i < nums.length; i++){
 *          
 *          if(i > start && nums[i] == nums[i - 1]) continue;
 *          
 *          if(nums[i] > target) break;
 * 
 *          current.add(nums[i])
 *          
 *          backtrack(nums, tartget - nums[i], current, result, i + 1)
 * 
 *          current.remove(current.length - 1)
 *          
 *      }
 * }
 * 
 */
