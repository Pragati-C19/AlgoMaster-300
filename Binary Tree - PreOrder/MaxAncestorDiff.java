import java.util.*;

public class MaxAncestorDiff {

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. Root of bianry tree is given
 * 2. need to find maximum value where v = [a.val - b.val]
 * 3. a is an ancestor of b
 * eg. root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences
 * some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 * 
 * Hint : For each subtree, find the minimum value and maximum value of its descendants.
 * 
 * Pattern :
 * 
 * First function
 * 1. Checking the node 
 * 2. find minVal
 * 3. find maxVal
 * 4. take a difference of (root - minVal) and (root - MaxVal)
 * 5. return max of above two differences
 * 
 * Second Function 
 * 1. Traverse the tree
 * 2. for each node, call the first function
 * 3. return max of all the values
 * 
 * Let's use a little bit logic of PathSum here 😉
 * 
 * Pseudo Code :
 * 
 * 1. First Approach
 * - Compare only left/right child
 * - Get min/max from children
 * 
 * function maxAncestorDiff(TreeNode root){
 * 
 *      // Let's start with root
 *      
 *      currentMaxDiff = findMinMax(root);
 *      
 *      v = Math.max(max, currentMaxDiff)
 * 
 *      return v;
 * }
 * 
 * 
 * function findMinMax(root){
 *      
 *      if(root.left.val <= root.val){
 *          minVal = (root.left.val);
 *      } 
 *      else {
 *          maxVal = root.left.val;
 *      }
 *      
 *      if(root.right.val <= root.val){
 *          minVal = root.right.val;
 *      } 
 *      else {
 *          maxVal = root.right.val;
 *      }
 *      
 *      currentMaxDiff = Math.max((root.val - minVal), (root.val - maxVal))
 *  
 *      return currentMaxDiff
 * }
 * 
 * 
 * 2. Second Approach
 * - Compare with deepest descendant
 * - Pass min/max down and update while going deeper
 * 
 * function maxAncestorDiff(TreeNode root){
 * 
 *      // Let's start with root
 *      
 *      currentMaxDiff = findMinMax(root);
 *      
 *      v = Math.max(max, currentMaxDiff)
 * 
 *      return v;
 * }
 * 
 * 
 * function findMinMax(TreeNode root, int minSoFar, int maxSoFar){
 *      
 *      if(root == null) return 0;
 * 
 *      currentMaxDiff = Math.max((root.val - minSoFar), (root.val - maxSoFar))
 * 
 *      if(root.val <= minSoFar){
 *          minSoFar = findMinMax(root.left, root.val, maxSoFar)
 *          minSoFar = findMinMax(root.right, root.val, maxSoFar)
 *      } 
 *      else {
 *          maxSoFar = findMinMax(root.left, minSoFar, root.val)
 *          maxSoFar = findMinMax(root.right, minSoFar, root.val)
 *      }
 *  
 *      return currentMaxDiff
 * }
 * 
 * 
 * 3. Third Approach
 * - 
 * 
 * 
 * 
 */