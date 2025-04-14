import java.util.*;

public class PostorderTraversal {
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. root is give need to return postOrder traversal of it's node value 
 * 2. post order is left -> right -> root
 * 
 * Pattern :
 * 
 * 1. Need to declare a List<Integer> as result to store output
 * 2. call recursion function postOrder(root, result)
 * - Base Case : if(root == null) return
 * - call recursion for leftSubtree
 * - call recursion for rightSubtree
 * - Visit root : result.add(root.val)
 * 
 * Pseudo Code :
 * 
 * function postorderTraversal(root){
 * 
 *      result = new array
 *      
 *      postorder(root, result)
 *      
 *      return result
 * }
 * 
 * function postorder(root, result){
 *      
 *      // Base Case :
 *      if(root == null) return
 * 
 *      // Check leftSubtree
 *      postorder(root.left, result)
 * 
 *      // Check rightSubtree
 *      postorder(root.right, result)
 * 
 *      // Visit root
 *      result.add(root.val)
 * 
 * }
 * 
 * 
 */
