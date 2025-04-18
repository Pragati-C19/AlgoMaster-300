import java.util.*;

import javax.swing.tree.TreeNode;

public class MaxPathSum {
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Globally Declare Variable


    // Driver Function 
    public int maxPathSum(TreeNode root) {
        
    }

    // Recursion Function : to check sum of paths
    private int postOrder(TreeNode node){

    }

    // Helper Function : to build a tree from an array (for testing)
    public static TreeNode buildTree(Integer[] nodes) {
        
        // Node is empty
        if (nodes.length == 0 || nodes[0] == null) return null;
        
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.add(root);
        
        int i = 1;  // Start from second element

        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode parent = queue.poll();
            
            // Assign left child
            if (nodes[i] != null) {
                parent.left = new TreeNode(nodes[i]);
                queue.add(parent.left);
            }
            i++;
            
            // Assign right child (check if there's still an element)
            if (i < nodes.length && nodes[i] != null) {
                parent.right = new TreeNode(nodes[i]);
                queue.add(parent.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        
        MaxPathSum solution = new MaxPathSum();

        // First Example
        Integer[] treeArray1 = {1, 2, 3};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + solution.maxPathSum(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = {-10, 9, 20, null, null, 15, 7};
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.maxPathSum(root2) + "\n");
    
    }
}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. root is given return maximum path sum of path
 * 
 * 
 * Pattern :
 * 
 * 1. Globally Declare variable pathSum
 * 2. Recursively call function
 * - Base Case : if(root == null) return 0
 * - Recursively call function on left subtree
 * - Recursively call function on right subtree
 * - Check currSum = left + right + node.val
 * - Update pathSum -> pathSum = max(pathSum, currSum)
 * - return currSum
 * 3. return pathSum
 * 
 * 
 * Pseudo Code :
 * 
 * // Globally Declare variable
 * pathSum;
 * 
 * function int maxPathSum(root){
 * 
 *      // I haven't use 0 here bcoz node.val can be -1000 too (mention in constraints)
 *      pathSum = Integer.Min_Value;
 * 
 *      postOrder(root)
 * 
 *      return pathSum;
 *      
 * }
 * 
 * 
 * function int postOrder(node){
 * 
 *      // Base Case :
 *      if(root == null) return 0;
 * 
 *      // Recur to left side
 *      leftPathSum = postOrder(node.left)
 * 
 *      // Recur to right Side
 *      rightPathSum = postOrder(node.right)
 * 
 *      // Check current sum
 *      currPathSum = node.val + leftPathSum + rightPathSum
 * 
 *      // Check max path Sum
 *      pathSum = max(currPathSum, pathSum)
 * 
 *      return currPathSum
 * 
 * }
 * 
 */
