import java.util.*;

public class BuildTreePostIn {

}

/*
 * 
 * Intuitions :
 * 
 * 1. Postorder and Inorder traversal is given we need to build tree from it
 * 2. We need to find the root node first from postorder
 * 3. we have to check that rootIndex from inorder
 * 4. We need to find the left and right child of the root node
 * 5. then use recursion
 * 6. return the root
 * 
 * Pattern :
 * 
 * 1. Globally assign postIndex -> which will travel globally for all recursion
 * and will not go in stack overflow
 * 2. Create a map to store Inorder value and index
 * 3. call recursion function which has
 * - if inorder's start > inorder's end then return null
 * - find the root node from postorder and store it in rootVal
 * - decrease postIndex as we travel from back now.. bcoz in post order the flow
 * is left -> right -> root
 * - find the root node in inorder and store it's index in rootIndex
 * - get value of right and left of root by recursion calls (Remember : we are
 * travelening from back in postorder so after root will hit to right so we need
 * to check right first then left)
 * - return the root node
 * 
 * 
 * Pseudo Code :
 * 
 * // Globally Assigning postIndex 
 * int postIndex;
 * 
 * function buildTree(postorder, inorder){
 *      
 *      // assigning value to postIndex
 *      postIndex = postorder.length - 1
 *      
 *      // Creating map for inorder
 *      inorderMap = new Hashmap
 *  
 *      // Storing value and index of inorder in map
 *      for(i from 0 to inorder.length){
 *          map.put(inorder[i], i)
 *      }
 *  
 *      // calling recursion function 
 *      return build(postorder, inorder, 0, (inorder.length - 1), inorderMap)
 *      
 * }
 * 
 * 
 * function build(postorder, inorder, inorderStart, inorderEnd, inorderMap){
 *  
 *      if(start > end) return null
 * 
 *      // Get rootVal from postorder 
 *      rootVal = postorder[postIndex]
 *      postIndex--
 * 
 *      // Check index of that root in inorder then will divide inorder accordingly left and right 
 *      rootIndex = inorderMap.get(rootVal)
 * 
 *      // create a root from that rootVal
 *      root = new TreeNode(rootVal)
 * 
 *      // Get right and left of the root (wrote right first then left let's see do I need to change it or not at time of coding)
 *      root.right = build(postorder, inorder, rootIndex + 1, inorderEnd, inorderMap)
 *      root.left = build(postorder, inorder, inorderStart, rootIndex - 1, inorderMap)
 *  
 *      return root
 * 
 * }
 * 
 * 
 * 
 */