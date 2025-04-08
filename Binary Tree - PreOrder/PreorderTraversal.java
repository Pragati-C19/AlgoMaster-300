import java.util.*;

public class PreorderTraversal {

    public static 
}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. To solve preorder we use stack or recurssion 
 * 2. We first visit the root node 
 * 3. then will add right to the stack 
 * 4. then will add left to the stack 
 * - Remember why we are doing by this order bcoz stack is FILO approach.. not like queue which is FIFO
 * 5. then we will pop the stack and print the value of the node which is process(root)
 * 
 * 
 * Pattern :
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * function preorderTraversal (root) {
 * 
 *      result = new Array
 *      
 *      Stack<TreeNode> stack = new Stack
 *      stack.push(root)
 * 
 *      (!stack.isEmpty){
 *          
 *          stackSize = stack.size()
 *          
 *          for(i from 0 to stackSize){
 *              
 *              TreeNode node = stack.pop()
 *              result.add(node)
 *              
 *              if(node.right != null) stack.push(node.right)
 *              if(node.left != null) stack.push(node.left)
 *               
 *          }
 *      }
 *      
 *      return result;
 * }
 * 
 */
