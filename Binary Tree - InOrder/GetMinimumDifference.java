import java.util.*;

import javax.swing.tree.TreeNode;

public class GetMinimumDifference {
    
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

    // Global Declaration of variables 
    Integer prevNode = null;
    int minDiff = Integer.MAX_VALUE;

    // Driver function 
    public int getMinimumDifference(TreeNode root) {
        
        inorderTraversal(root);
        return minDiff;

    }

    // Recursion Function : To sort BST and get MinDiff
    private void inorderTraversal(TreeNode node){

        // Base Case :
        if (node == null) {
            return;
        }

        // check left node
        inorderTraversal(node.left);

        // visit node : find the minDiff here
        if (prevNode != null) {
            minDiff = Math.min(minDiff, Math.abs(node.val - prevNode));
        }
        prevNode = node.val;

        // check right node
        inorderTraversal(node.right);
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
        
        GetMinimumDifference solution = new GetMinimumDifference();

        // First Example
        Integer[] treeArray1 = {4, 2, 6, 1, 3};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + solution.getMinimumDifference(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = { 1, 0, 48, null, null, 12, 49 };
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.getMinimumDifference(root2) + "\n");

    }
}

/*
 * 
 * Intuitions :
 * 
 * 1. we have given a root
 * 2. we need to return minimum abs difference between values of any two nodes
 * 3. this que is same as MinDiffInBST
 * 4. will use inorder to get sorted list
 * 5. and will use global declaration of prevNode and minDiff pattern 
 * 
 * pattern : 
 * 
 * 1. will declare prevNode and minDiff globally
 * 2. call recursion function
 *      - if(root == null) return
 *      - call recursion function to check left node
 *      - visit the node : check if prevNode != null then find minDiff
 *      - call recursion function to check right node
 * 3. return minDiff
 * 
 * 
 * Pseudo Code :
 * 
 * prevNode = null
 * int minDiff = Max.Value
 * 
 * function getMinimumDifference(root){
 *      inorder(root)
 *      return minDiff
 * }
 * 
 * function inorder(node){
 * 
 *      if(node == null) return
 * 
 *      inorder(node.left)
 * 
 *      if(prevNode != null) {
 *          minDiff = min(minDiff, Math.abs(node.val - prevNode))
 *      }
 *      prevNode = node.val
 *  
 *      inorder(node.right)
 * }
 * 
 */
