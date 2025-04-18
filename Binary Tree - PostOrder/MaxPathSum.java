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
    int pathSum;

    // Driver Function 
    public int maxPathSum(TreeNode root) {
        
        // Here I haven't wrote 0 bcoz constraints says node.val can be -1000 too 
        pathSum = Integer.MIN_VALUE;

        System.out.println("Starting Recursion...");
        postOrder(root);

        System.out.println("Ending Recursion...");
        return pathSum;
    }

    // Recursion Function : to check sum of paths
    private int postOrder(TreeNode node){

        // Base Case:
        if (node == null) {
            return 0;
        }

        // Recur to left Side
        int leftPathSum = Math.max(0, postOrder(node.left));
        System.out.println("    - Left Side's Path Sum of " + node.val + " : " + leftPathSum);
        
        // Recur to right side
        int rightPathSum = Math.max(0, postOrder(node.right));
        System.out.println("    - Right Side's Path Sum of " + node.val + " : " + rightPathSum);


        // Check current sum 
        // int currPathSum;
        // if (leftPathSum < 0 && rightPathSum < 0) {
        //     currPathSum = node.val;
        // }
        // else if (leftPathSum < 0) {
        //     currPathSum = node.val + rightPathSum;
        // }
        // else if (rightPathSum < 0) {
        //     currPathSum = node.val + leftPathSum;
        // }
        // else {
        //     currPathSum = node.val + leftPathSum + rightPathSum;
        // }

        // More clean solid Optimal instead of this if else get max of left and right at the recur call
        int currPathSum = node.val + leftPathSum + rightPathSum;
        System.out.println("        -> Current Path Sum of " + node.val + " : " + currPathSum);

        // Get max path sum till now
        pathSum = Math.max(pathSum, currPathSum);
        System.out.println("        -> Path Sum till now : " + pathSum);

        // Return statement was wrong : we need to be stricly one sided for next paths
        // rejecting negative path sums by allowing 0 instead of including bad paths( negative path)
        int returnPathSum = node.val + Math.max(0, Math.max(leftPathSum, rightPathSum));
        System.out.println("        -> Return Path Sum : " + returnPathSum);

        return returnPathSum;
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

        // // First Example
        // Integer[] treeArray1 = {1, 2, 3};
        // TreeNode root1 = buildTree(treeArray1);
        // System.out.println("Result1: " + solution.maxPathSum(root1) + "\n");

        // // Second Example
        // Integer[] treeArray2 = {-10, 9, 20, null, null, 15, 7};
        // TreeNode root2 = buildTree(treeArray2);
        // System.out.println("Result2: " + solution.maxPathSum(root2) + "\n");

        // // Third Example
        // Integer[] treeArray3 = {2, -1};
        // TreeNode root3 = buildTree(treeArray3);
        // System.out.println("Result3: " + solution.maxPathSum(root3) + "\n");

        // // Forth Example
        // Integer[] treeArray4 = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};
        // TreeNode root4 = buildTree(treeArray4);
        // System.out.println("Result4: " + solution.maxPathSum(root4) + "\n");

        // Fifth Example
        Integer[] treeArray5 = {9, 6, -3, null, null, -6, 2, null, null, 2, null, -6, -6, -6};
        TreeNode root5 = buildTree(treeArray5);
        System.out.println("Result5: " + solution.maxPathSum(root5) + "\n");
    
    }
}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. root is given return maximum path sum of path
 * 2. The global max might come from a "fork" path (left + node + right) — even if you can't return that to a parent.
 * 3. But when returning to parent, you must be one-sided don't use both left and right, not forked — just a straight segment upward.
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
