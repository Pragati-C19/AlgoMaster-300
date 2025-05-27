import java.util.*;

import javax.swing.tree.TreeNode;

public class PostorderTraversal {
    
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


    // Driver Function 
    public List<Integer> postorderTraversal(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();

        // Call recursion 
        postOrder(root, result);
 
        return result;

    }

    // Recursion Function : write actual code of post order
    private void postOrder(TreeNode root, List<Integer> result){

        // Base Case :
        if(root == null) return;

        // Check left subtree
        postOrder(root.left, result);

        // Check right subtree
        postOrder(root.right, result);

        // Visit root
        result.add(root.val);
    
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
        
        PostorderTraversal solution = new PostorderTraversal();

        // First Example
        Integer[] treeArray1 = {1, null, 2, 3};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + solution.postorderTraversal(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = { 1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9 };
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.postorderTraversal(root2) + "\n");

        // Third Example
        Integer[] treeArray3 = {1};
        TreeNode root3 = buildTree(treeArray3);
        System.out.println("Result3: " + solution.postorderTraversal(root3) + "\n");

        // Forth Example
        Integer[] treeArray4 = {};
        TreeNode root4 = buildTree(treeArray4);
        System.out.println("Result4: " + solution.postorderTraversal(root4) + "\n");

    }
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
