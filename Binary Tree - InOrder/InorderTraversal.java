import java.util.*;

public class InorderTraversal {
    
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
    public List<Integer> inorderTraversal(TreeNode root) {
        
    }

    // Recursion Function : it will help to build tree in inorder way and also store result
    private void buildInorderTraversal(TreeNode root, List<Integer> result){

    }
    

    public static void main (String[] args){

        InorderTraversal solution = new InorderTraversal();

        TreeNode root1 = {1, null, 2, 3};
        System.out.println("Output1 : " + solution.inorderTraversal(root1) + "\n");

        TreeNode root2 = {1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9};
        System.out.println("Output2 : " + solution.inorderTraversal(root2) + "\n");

        TreeNode root3 = {};
        System.out.println("Output3 : " + solution.inorderTraversal(root3) + "\n");

        TreeNode root4 = {1};
        System.out.println("Output4 : " + solution.inorderTraversal(root4) + "\n");

    }

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
