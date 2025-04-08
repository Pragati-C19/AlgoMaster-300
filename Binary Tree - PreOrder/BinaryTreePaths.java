import java.util.*;

public class BinaryTreePaths {
    
}


/*
 * 
 * Intuitions :
 * 
 * 1. root is given 
 * 2. need to return root->leaf path
 * 3. will use stack here
 * 
 * Pattern :
 * 
 * 1. Let's try using stack first
 * 2. we will push root in stack
 * 3. while stack is not empty will follow below steps
 * - will check stackSize
 * - current =  
 * - if(root.left != null) stack.push(root.left)
 * - if(root.right != null) stack.push(root.right)
 * 
 * 4. now I think of it I need to use backtracking here.. 
 * - if we see the flow 1->2->5, 1->3 see it just not taking that value out of stack it's backtracking it removing it's traces
 * - just I'm not able to think of how to use it yet.. 
 * - Base Case : if(root.left && root.right == null) result.add(current)
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 * function binaryTreePaths(root) {
 * 
 *      result = new arraylist
 *      current = new String
 *      
 *      backtrack(root, current, result)
 *  
 *      return result;
 * }
 * 
 * 
 * function backtrack(node, current, result) {
 * 
 *      // Base Case:
 *      if(node.left == null && node.right == null) {
 *          result.add(current)
 *      }
 *      
 *      if(node.left != null) {
 * 
 *      }
 * 
 *      if(node.right != null){
 *          
 *      }
 * }
 * 
 */