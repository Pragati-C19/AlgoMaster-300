import java.util.*;

import javax.swing.tree.TreeNode;

public class KthSmallest {
    
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


    // Globally declared variables
    int smallestElement;
    int index;

    // Driver Function
    public int kthSmallest(TreeNode root, int k) {
        index = 1;
        inorderTraversal(root, k);
        return smallestElement;
    }

    // Recursion Function : to find smallest Element
    private void inorderTraversal(TreeNode node, int k){
        
        // Base Case :
        if(node == null) return;

        System.out.println("Initial Values | Node : " + node.val + ", Index : " + index + " K : " + k);
        
        // Traverse Left Subtree
        inorderTraversal(node.left, k);

        // Visit Node : check if index is equal to k or not 
        if (index == k) {
            smallestElement = node.val;
            System.out.println("- Node : " + node.val + " is smallestElement (" + smallestElement + ") " ); 
            System.out.println("    where index is " + index + " and k is " + k);
        }
        index++;
        System.out.println("updated Index : " + index + "\n");

        // Traverse Right Subtree
        inorderTraversal(node.right, k);

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
        
        KthSmallest solution = new KthSmallest();

        // First Example
        Integer[] treeArray1 = {3, 1, 4, null, 2};
        TreeNode root1 = buildTree(treeArray1);
        int k1 = 1;
        System.out.println("Result1: " + solution.kthSmallest(root1, k1) + "\n");

        // Second Example
        Integer[] treeArray2 = { 5, 3, 6, 2, 4, null, null, 1 };
        TreeNode root2 = buildTree(treeArray2);
        int k2 = 3;
        System.out.println("Result2: " + solution.kthSmallest(root2, k2) + "\n");

    }

}


/*
 * 
 * Intuitions :
 * 
 * 1. root is given and integer k is given
 * 2. here k is index 
 * 3. We need to find node at that index 
 * 
 * 
 * Pattern :
 * 
 * 1st basic approach to understand why I'm doing it in recursion 
 * 1. will sortArray by inorder
 * 2. return value at index k -> return arr.get(k)
 * 
 * 2nd approach
 * 1. Will declare index and smallestElement Globally resp 1 and null
 * 2. will call recursion function inorder whcih will help us to find kth smallest value
 * - Base case : if(node == null) return 
 * - call recursion to check left side node
 * - visit root : check if(index == k) -> if yes return node.val
 * - index++;
 * - call recursion to check right side node
 * 
 * 
 * Pseudo Code :
 * 
 * Integer smallestElement = null // let's try saying Null then will check int 
 * int index = 1;
 * 
 * function kthSmallest(root, k){
 *      inorder(root, k)
 *      return smallestElement;
 * }
 * 
 * function inorder(node, k){
 * 
 *      // Base Case :
 *      if(node == null) return 
 *      
 *      // check left side node
 *      inorder(node.left, k)
 * 
 *      // Visit Node 
 *      if(index == k){
 *          smallestElement = node.val
 *      }
 *      index++;
 * 
 *      // check right side node
 *      inorder(node.right, k)
 * }
 * 
 */