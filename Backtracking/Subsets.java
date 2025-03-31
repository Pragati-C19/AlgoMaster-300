import java.util.*;

public class Subsets {
    
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
