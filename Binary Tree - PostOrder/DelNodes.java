import java.util.*;

public class DelNodes {
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. root of binary tree and to_delete array is given
 * 2. we need to delete a node from binary tree
 * 3. In return we need a list of new TreeNodes
 * 4. I need to use hashmap here which stores 
 * 
 * Pattern :
 * 
 * 1. Globally Declare deleteMap
 * 2. Main Function 
 * - Traverse the tree and store the nodes in deleteMap
 * - recursion call
 * - return result;
 * 
 * 3. recursion function 
 * - if(root == null) null;
 * - check left side
 * - check right side
 * - if(deleteMap.contains(root.val)) 
 *      -> if(leftSubtree != null) result.add(new TreeNode(leftSubtree))
 *      -> if(rightSubtree != null) result.add(new TreeNode(rightSubtree))
 *      -> return null; // bcoz that specific node is deleted
 * - return root
 * 
 * Pseudo Code :
 * 
 * 
 * // Globally Declare Variable
 * Map<Integer, Boolean> deleteMap = new HashMap<>()
 * result = new ArrayList
 * 
 * // Main Function
 * function delNodes(root, to_delete){
 *      
 *      for(i from 0 to to_delete.length){
 *          deleteMap.put(to_delete[i], false)
 *      }
 * 
 *      postOrder(root)
 *      
 *      return result;
 *      
 * }
 * 
 * function postOrder(root){
 * 
 *      // Base Case :
 *      if(root == null) return root;
 * 
 *      // Recur to left Side
 *      leftSubtree = postOrder(root.left)
 *      
 *      // Recur to right Side
 *      rightSubTree = postOrder(root.right)
 * 
 *      // Visit node
 *      if(deleteMap.get(root.val)){
 *          if(leftSubTree != null) result.add(new TreeNode(leftSubTree))
 *          if(leftSubTree != null) result.add(new TreeNode(rightSubTree))
 *          return null
 *      }
 * 
 *      return root;
 * }
 * 
 */