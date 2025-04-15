import java.util.*;

public class LowestCommonAncestor {
    
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        // Base Case 
        if(root == null) return null;

        // If p or q is found on that root
        if(root == p || root == q) return root;

        // Check left Side
        TreeNode leftSubtree = lowestCommonAncestor(root.left, p, q);

        // Check right Side
        TreeNode rightSubtree = lowestCommonAncestor(root.right, p, q);

        // Visit Node
        if(leftSubtree != null && rightSubtree != null){
            return root;
        }
        
        if (leftSubtree == null) {
            return rightSubtree;
        }
        
        return leftSubtree;

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
        
        LowestCommonAncestor solution = new LowestCommonAncestor();

        // First Example
        Integer[] treeArray1 = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root1 = buildTree(treeArray1);
        TreeNode p1 = new TreeNode(5); 
        TreeNode q1 = new TreeNode(1); 
        System.out.println("Result1: " + solution.lowestCommonAncestor(root1, p1, q1) + "\n");

        TreeNode p2 = new TreeNode(5); 
        TreeNode q2 = new TreeNode(4); 
        System.out.println("Result1: " + solution.lowestCommonAncestor(root1, p2, q2) + "\n");

        // Second Example
        Integer[] treeArray3 = { 1, 2 };
        TreeNode root3 = buildTree(treeArray3);
        TreeNode p3 = new TreeNode(1); 
        TreeNode q3 = new TreeNode(2); 
        System.out.println("Result2: " + solution.lowestCommonAncestor(root3 , p3, q3 ) + "\n");

    }
}
/*
 * 
 * Intuitions :
 * 
 * 1. Root of Binary Tree is given return Lowest Common Ancestor (LCA) of given nodes in the tree
 * 2. root, p, q given
 * 3. For this que we are going to play with root.. not root.val only
 * 
 * Pattern :
 * 
 * 1. In Dirver function -> return recursion call
 * 2. recursion call
 * - Base Case -> if(root == null) return null
 * - if we found p or q at that reoot return that root -> if(root == p || root == q) return root
 * - call recursion to check left side
 * - call recursion to check right side
 * - Visit Node -> here we will have to write code for nodes that has no p or q
 *      - both side are not null i.e they had got the p or q in them if(left != null && right != null) return root
 *      - if(left == null) return right
 *      - if(right == null) return left
 * 
 * 
 * Pseudo Code :
 * 
 * function lowestCommonAncestor(root, p, q){
 * 
 *      if(root == null) return null;
 *      
 *      if(root == p || root == q) return root;
 * 
 *      // check leftSubTree
 *      leftSubtree = lowestCommomAncestor(root.left, p, q)
 * 
 *      // check rightSubtree
 *      rightSubtree = lowestCommonAncestor(root.right, p, q)
 * 
 *      // Visit node
 *      if(leftSubtree != null && rightSubtree != null) return root;
 *      
 *      if(leftSubtree == null) return rightSubtree
 *      if(rightSubtree == null) return leftSubtree
 *      
 * }
 * 
 */