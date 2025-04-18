import java.util.*;

public class MaxPathSum {
    
}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. root is given return maximum path sum of path
 * 
 * 
 * Pattern :
 * 
 * 1. Globally Declare variable pathSum
 * 2. Recursively call function
 * - Base Case : if(root == null) return 0
 * - Recursively call function on left subtree
 * - Recursively call function on right subtree
 * - Check currSum = left + right + node.val
 * - Update pathSum -> pathSum = max(pathSum, currSum)
 * - return currSum
 * 3. return pathSum
 * 
 * 
 * Pseudo Code :
 * 
 * // Globally Declare variable
 * pathSum;
 * 
 * function int maxPathSum(root){
 * 
 *      // I haven't use 0 here bcoz node.val can be -1000 too (mention in constraints)
 *      pathSum = Integer.Min_Value;
 * 
 *      postOrder(root)
 * 
 *      return pathSum;
 *      
 * }
 * 
 * 
 * function int postOrder(node){
 * 
 *      // Base Case :
 *      if(root == null) return 0;
 * 
 *      // Recur to left side
 *      leftPathSum = postOrder(node.left)
 * 
 *      // Recur to right Side
 *      rightPathSum = postOrder(node.right)
 * 
 *      // Check current sum
 *      currPathSum = node.val + leftPathSum + rightPathSum
 * 
 *      // Check max path Sum
 *      pathSum = max(currPathSum, pathSum)
 * 
 *      return currPathSum
 * 
 * }
 * 
 */
