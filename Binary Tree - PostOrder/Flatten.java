import java.util.*;

public class Flatten {
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. Converting binary tree to linkedList
 * 2. Traverse the tree and add nodes to the linked list
 * 3. Return the head of the linked list
 * 4. conditions are
 * - linked list will use same TreeNode class 
 * - where root.right points to -> next node in the list
 * - root.left points to -> null
 * - linked list should be in the same order as a pre-order traversal
 * 
 * 
 * Pattern :
 * 
 * 1. Globally Declare TreeNode Head
 * 
 * 2. Recursion function 
 *      - Base Case : if(root == null) -> return null
 *      
 *      - Recur To left side and get head
 *      - Recur to right side and get head 
 *  
 *      - head = node.val
 * 
 *      - if(leftSubtree != null && rightSubtree != null)
 *          change head.left to null
 *          change head.right to leftSubtree
 *          change head.right.right to rightSubtree
 *      
 *      - if(leftSubtree == null)
 *          change head.right to rightSubtree
 * 
 *      - if(rightSubtree == null)
 *          change head.left to null
 *          change head.right to leftSubtree
 * 
 *      - return head
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * TreeNode head = new TreeNode
 * 
 * function void flatten(TreeNode root){
 *      postOrder(root);
 *      return;
 * }
 * 
 * function TreeNode postOrder(TreeNode root){
 * 
 *      // Base Case :
 *      if(root == null) return null
 * 
 *      // Recur to left side
 *      leftSubtree = postOrder(root.left)
 * 
 *      // Recur to right side
 *      rightSubTree = postOrder(root.right)
 * 
 *      // Visit Node
 *      head = root
 * 
 *      if(leftSubtree != null && rightSubtree != null) {
 *          head.left = null
 *          head.right = leftSubtree
 *          head.right.right = rightSubtree
 *      }
 *      else if(leftSubtree == null) {
 *          head.right = leftSubtree 
 *      }
 *      else if(rightSubtree == null) {
 *          head.left = null
 *          head.right = leftSubtree 
 *      }
 *      
 *      return head
 * }
 * 
 * 
 */
