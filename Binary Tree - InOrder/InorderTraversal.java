import java.util.*;

public class InorderTraversal {
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. Root of Binary Tree is Given
 * 2. We need to find the Inorder Traversal of the Binary Tree
 * 3. will use recursion here
 * 4. Inorder Traversal : Left -> Root -> Right
 * 
 * Pattern :
 * 
 * 1. we'll declare result as List<Integer>
 * 2. call recursion function (root, result)
 * - check if root is null -> return result
 * - check left side first -> root.left = (root.left, result)
 * - push the root in result
 * - check right side Last -> root.right = (root.right, result)
 * 3. return result
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * function inorderTraversal(root) {
 *      
 *      result = new ArrayList
 *      
 *      // Base Case:
 *      if(root == null) return result
 * 
 *      result = buildInorderTraversal(root, result)
 * 
 *      return result;
 * 
 * }
 * 
 * function buildInorderTraversal(root, result){
 * 
 *      if(root == null) return result;
 * 
 *      // will check left first as we want inorder traversal so
 *      root.left = buildInorderTraversal(root.left, result)
 *      
 *      // add root to result
 *      result.add(root)
 * 
 *      // will check right now
 *      root.right = buildInorderTraversal(root.right, result)
 * 
 *      return result;
 * 
 * }
 * 
 * 
 */
