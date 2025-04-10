import java.util.*;

public class BuildTree {
    
}


/*
 * 
 * 
 * Intuitions :
 * 
 * 1. Given Two arrays preorder and inorder
 * 2. preorder is the preorder traversal of binary tree (root -> left - > right)
 * 3. inorder is the inorder traversal of binary tree (left - > root -> right)
 * 4. return Binary tree
 * 
 * Pattern :
 * 
 * 1. Array of inorder will get shrink and breaks in leftSubTree and rightSubTree
 * 2. Array of preorder only tell use abt root...  so traverse full array one by one
 * 3. after getting root check at what index that root is in inorder Array
 * 4. if root is at index i then leftSubTree will be from 0 to i
 * 5. rightSubTree will be from i+1 to end of inorder Array
 * 6. Shrink Trees
 * 
 * eg. preorder = [3,9,10,20,15,11,7], inorder = [10,9,3,11,15,20,7]
 * 1. preorder(3) 
 * In inorder, 3 is at index 2.
 * So: Left Inorder = [10, 9] | Right Inorder = [11, 15, 20, 7]
 * 
 * 2. preorder(9)
 * In Left Inorder(3), 9 is at index 1. and size of array is 2
 * So: Left Inorder = [10] | Right Inorder = []
 * 
 * 3. preorder(10)
 * In Left Inorder(9), 10 is at index 0. and and size of array is 1
 * So: Left Inorder = [] | Right Inorder = []
 * 
 * Now we go back to 3 and handle the right subtree, which was [11, 15, 20, 7] in inorder.
 * 
 * 4. preorder(20)
 * In Right Inorder(3), 20 is at index 2.
 * So : Left Inorder = [11, 15] | Right Inorder = [7]
 * 
 * 5. preorder(15)
 * In Left Inorder(20), 15 is at index 1. and size of array is 2 
 * So : Left Inorder = [11] | Right Inorder = []
 * 
 * 6. preorder(11)
 * In Left Inorder(15), 11 is at index 0. and size of array is 1
 * So : Left Inorder = [] | Right Inorder = []
 * 
 * 7. preorder(7)
 * In Right Inorder(20), 7 is at index 0. and size of array is 1
 * So : Left Inorder = [] | Right Inorder = []
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * function buildTree(preorder, inorder){
 *      
 *      Integer[] result = new ArrayList
 *      int start = 0
 *      int end = inorder.length - 1
 * 
 *      Integer[] treeArray = dfs(preorder, inorder, start, end, result)
 *      
 *      TreeNode rootOfBinaryTree = buildTreeWithArray(treeArray)
 *      
 *      return rootOfBinaryTree;
 * 
 * }
 * 
 * function TreeNode buildTreeWithArray(Integer[] nodes) {
 * 
 *      // Node is empty
 *      if (nodes.length == 0 || nodes[0] == null) return null;
 * 
 *      TreeNode root = new TreeNode(nodes[0]);
 *      Queue<TreeNode> queue = new LinkedList<>();
 *      queue.add(root);
 * 
 *      int i = 1;  // Start from second element
 * 
 *      while (!queue.isEmpty() && i < nodes.length) {
 * 
 *          TreeNode parent = queue.poll();
 * 
 *          // Assign left child
 *          if (nodes[i] != null) {
 *              parent.left = new TreeNode(nodes[i]);
 *              queue.add(parent.left);
 *          }
 *          i++;
 *          
 *          // Assign right child (check if there's still an element)
 *          if (i < nodes.length && nodes[i] != null) {
 *              parent.right = new TreeNode(nodes[i]);
 *              queue.add(parent.right);
 *          }
 *          i++;
 *      }
 * 
 *      return root;
 * }
 * 
 * 
 * function dfs(int[] preorder, int[] inorder, int start, int end, Integer[] result) {
 *      
 *      if(inorder.lenght == 0){
 *          result.add(null)
 *          return;
 *      }
 *      
 *      left = 0
 *      right = inorder.lenght - 1
 * 
 *      for(int i = left; i <= right; i++){
 *          
 *          if(preorder[start] == inorder[i]){
 *              leftInorder = dfs( preorder, inorder[left : i - 1], start++, result)
 *              rightInorder = dfs( preorder, inorder[i + 1 : right], start++, result)
 *          } 
 *       
 *      }
 *      
 *      return result.add(leftInorder).add(rightInorder);
 * }
 * 
 * 
 * 
 * 
 */