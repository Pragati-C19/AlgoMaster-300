import java.util.*;

public class PathSum {
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. root of binary Tree and targetSum is given
 * 2. return the number of paths, where sum of values = targetSum
 * 3. path does not need to start or end at the root or a leaf, just it should go downwards
 * 
 * 
 * Pattern :
 * 
 * 1. Will use recursion here
 * 2. if targetSum == pathSum -> return count++
 * 3. if pathSum < targetSum -> add root.left or root.right in path and call recursion
 * 4. if pathSum > targetSum -> reset pathSum to zero and check for root.left or root.right and call recursion
 * 
 * Pseudo Code :
 * 
 * 
 * function pathSum (root, targetSum) {
 *      
 *      int sumCount = checkSum(root, targetSum, 0, 0) 
 * 
 *      return sumCount
 * }
 * 
 * 
 * function checkSum(TreeNode root, int targetSum, int currentSum, int sumCount){
 *      
 *      // Base Case 
 *      if(currentSum == targetSum) return count++
 * 
 *      if(currentSum > targetSum) {
 *          currentSum = 0
 *          checkSum(root.left, targetSum, currentSum, sumCount)
 *          checkSum(root.right, targetSum, currentSum, sumCount)
 *      } 
 * 
 *      if(currentSum < targetSum){
 *          currentSum += root.val
 *          checkSum(root.left, targetSum, currentSum, sumCount)
 *          checkSum(root.right, targetSum, currentSum, sumCount)
 *      }
 * 
 *      return count;
 * }
 * 
 * 
 */
