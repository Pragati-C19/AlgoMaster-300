import java.util.*;

public class Permute {
    
}

/**
 * 
 * Intuitions : 
 * 
 * - Find permutation of given array
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
