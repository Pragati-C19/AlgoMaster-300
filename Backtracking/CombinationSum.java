import java.util.*;

public class CombinationSum {
    
    // Function to find all combinations whos sum is equal to target
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        int sum = 0;
        int start = 0;
        
        Arrays.sort(candidates);

        System.out.println("Starting Backtracking...");
        backtrack(candidates, target, sum, current, result, start);

        System.out.println("Final Result: " + result);
        return result;

    }

    // Helper Function : to backtrack recurssively 
    private void backtrack(int[] nums, int target, int sum, List<Integer> current, List<List<Integer>> result, int start){
        
        System.out.println("[backtrack] Sum: " + sum + " | Current: " + current + " | result: " + result);

        // Base Case
        if(sum == target){
            result.add(new ArrayList<>(current));
            System.out.println("[backtrack] Added to result: " + current + " | Result: " + result + "\n");
            return;
        }

        for(int i = start; i < nums.length; i++){
            sum = sum + nums[i];
            System.out.println(" num : " + nums[i] + " | Sum : " + sum);

            if(sum <= target){
                current.add(nums[i]);
                System.out.println(" [IF : BEFORE] Current : " + current + " | Sum : " + sum + " | Result : " + result + "\n");

                backtrack(nums, target, sum, current, result, i);

                sum = sum - nums[i];
                current.remove(current.size() - 1);
                
                System.out.println(" [IF : AFTER] Current : " + current + " | Sum : " + sum + " | Result : " + result);

            }
        }
    }

    public static void main(String[] args){
        CombinationSum solution = new CombinationSum();

        // int[] candidates1 = {2,3,5};
        // int target1 = 8;
        // System.out.println("Output1 : " + solution.combinationSum(candidates1, target1) + "\n");

        int[] candidates2 = {8,7,4,3};
        int target2 = 11;
        System.out.println("Output2 : " + solution.combinationSum(candidates2, target2) + "\n");

        // int[] candidates3 = {2};
        // int target3 = 1;
        // System.out.println("Output3 : " + solution.combinationSum(candidates3, target3) + "\n");
        
    }
    
}


/*
 * 
 * Intuitions : 
 * 
 * 1. We have given an distict array with target
 * 2. We need to find all combinations of integer from array should be equal to sum of nums
 * 3. let's use backtracking here
 * - result will be return at sum(current) = target
 * 
 * 
 * Pattern :
 * 
 * 1. Base case: If the sum(current) equals to the target, add it to the result.
 * 2. Recursive step: Try each number as many times as u want
 * - find it's sum first
 * - if sum(current) < target
 * - Add it to the current.
 * - Recursively explore further choices
 * - Backtrack (remove last choice to try the next possibility)
 * - like current.remove(current.lenght - 1), sum = sum - current[current.lenght - 1]
 * - Recursively explore further choices.
 * - Backtrack (remove last choice to try the next possibility).
 * - if sum(current) > target break or return (I'm not sure of it yet will check while coding)
 * 
 * 
 * Pseudo Code :
 * 
 * function combinationSum {
 *      
 *      result = new array
 *      current = new array
 *      sum = 0
 *      
 *      backtrack(nums, target, current, result, sum)
 *      
 *      return result
 * }
 * 
 * 
 * function backtrack(nums, target, current, result, sum){
 *      
 *      if(sum == target){
 *          result.add(new Array(current))
 *          return;
 *      } 
 * 
 *      for(num : nums){
 *          sum = sum + num;
 *          if(sum < target) {
 *              current.add(num)
 *              backtrack(nums, target, current, result, sum)
 *              sum = sum - current[current.lenght - 1] or sum = sum - num
 *              current.remove(current.lenght - 1)
 *          }
 *          else if(sum > target){
 *              break;
 *          }
 *      }
 * }
 * 
 */
