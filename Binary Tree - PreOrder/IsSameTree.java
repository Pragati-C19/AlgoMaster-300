import java.util.*;

public class IsSameTree {
    
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        System.out.println( "At start ->  p : " + p.val + " q : " + q.val);
        if (p == null && q == null) return true;
        
        if (p.val != q.val) {
            return false;
        }

        if (p.val == q.val) {
            
            // check left side of it
            isSameTree(p.left, q.left);

            // check right side of it
            isSameTree(p.right, q.right);
        }

        return false;
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


    public static void main(String[] args) {
        
        IsSameTree solution = new IsSameTree();

        // First Example
        Integer[] p1 = {1, 2, 3};
        Integer[] q1 = {1, 2, 3};
        TreeNode root1 = buildTree(p1 , 0);
        TreeNode root2 = buildTree(q1 , 0);
        System.out.println("Result1: " + solution.isSameTree(root1, root2) + "\n");

        // Second Example
        // Integer[] p3 = {1, 2};
        // Integer[] q4 = {1, null, 2};
        // TreeNode root3 = buildTree(p3 , 0);
        // TreeNode root4 = buildTree(q4 , 0);
        // System.out.println("Result1: " + solution.isSameTree(root3, root4) + "\n");

        // Third Example
        Integer[] p5 = {1, 2, 1};
        Integer[] q6 = {1, 1, 2};
        TreeNode root5 = buildTree(p5 , 0);
        TreeNode root6 = buildTree(q6 , 0);
        System.out.println("Result1: " + solution.isSameTree(root5, root6) + "\n");

    }
}


/*
 * 
 * Intuitions :
 * 
 * 1. we have given 2 binary trees
 * 2. we need to check if they are same or not
 * 3. Considered the same if they are structurally identical, and the nodes have the same value.
 * 
 * Pattern :
 * 
 * 1. will use recursion as I have to travel 2 trees at a time
 * 2. if I had used stack I have to wrote stack1 stack2 and had to comapre nodes from both of them
 * 3. stack will be so lengthy process but using recursion I can directly compare nodes of both trees
 * 
 * 
 * Pseudo Code :
 * 
 * function isSameTree (p , q) {
 * 
 *      if(p == null || q == null){
 *          return false;
 *      }
 * 
 *      // Process root
 *      if(p.val != q.val){
 *          return false;
 *      }
 * 
 *      if(p.val == q.val){
 *          isSameTree(node.left);
 *          isSameTree(node.right);
 *          return true;
 *      }
 *      
 * }
 * 
 * 
 */