import java.util.*;

public class CountNodes {
    
}


/*
 * 
 * Intuition :
 * 
 * 1. we have given root
 * 2. we need to count nodes
 * 3. we should use recursion ig let's see
 * 
 * Pattern :
 * 
 * 1. We need a int currentCount which stores count of nodes
 * 2. will use stack approach first as it's my first brute force type thought so 
 * 
 * Pseudo Code :
 * 
 * function countNodes (TreeNode root){
 *      
 *      int currentCount
 * 
 *      stack = new stack
 *      stack.push(root)
 *      
 *      while(!stack.isEmpty){
 *          
 *          stacksize = stack.size()
 *          
 *          for(i from 0 to stacksize){
 *              node = stack.pop()
 *              
 *              currentCount++;
 *              
 *              left != null -> stack.push(node.left)
 *              right != null -> stack.push(nide.right)
 *          }
 *      }
 *      
 *      return currentCount;
 * }
 * 
 */