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
        
        List<Integer> result = new ArrayList<>();

        // Base Case :
        if (root == null) {
            return result;
        }

        buildInorderTraversal(root, result);

        return result;

    }

    // Recursion Function : it will help to build tree in inorder way and also store result
    private void buildInorderTraversal(TreeNode root, List<Integer> result){

        // Base Case :
        if (root == null) {
            return;
        }

        // We'll check Left side first
        buildInorderTraversal(root.left, result);

        // Add root in result
        result.add(root.val);

        // Checking right side
        buildInorderTraversal(root.right, result);

        return;

    }

    // Helper Function : to build a tree from an array (for testing)
    public static TreeNode buildTree(Integer[] nodes, int index) {
        
        // Base Case: if index is out of bounds or node is null
        if (index >= nodes.length || nodes[index] == null) return null;

        TreeNode root = new TreeNode(nodes[index]);

        // Recursively build left and right children
        root.left = buildTree(nodes, 2 * index + 1);
        root.right = buildTree(nodes, 2 * index + 2);

        return root;
    }

    public static void main (String[] args){

        InorderTraversal solution = new InorderTraversal();

        Integer[] treeArray1 = {1, null, 2, 3};
        TreeNode root1 = buildTree(treeArray1, 0);
        System.out.println("Output1 : " + solution.inorderTraversal(root1) + "\n");


        Integer[] treeArray2 = {1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9};
        TreeNode root2 = buildTree(treeArray2, 0);
        System.out.println("Output2 : " + solution.inorderTraversal(root2) + "\n");

  
        Integer[] treeArray3 = {};
        TreeNode root3 = buildTree(treeArray3, 0);
        System.out.println("Output3 : " + solution.inorderTraversal(root3) + "\n");


        Integer[] treeArray4 = {1};
        TreeNode root4 = buildTree(treeArray4, 0);
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
