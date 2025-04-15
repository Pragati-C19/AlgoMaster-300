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
        
        // Base Case 1: If root is null, then there's no LCA in this path
        if(root == null) return null;

        // Base Case 2: If the current node is either p or q, we return the current node
        // This means one of the targets is found
        if (root == p) {
            System.out.println("Found node p: " + p.val + " at node " + root.val);
            return root;
        }
        if (root == q) {
            System.out.println("Found node q: " + q.val + " at node " + root.val);
            return root;
        }

        // Recur on left subtrees
        System.out.println("Going left from node: " + root.val);
        TreeNode leftSubtree = lowestCommonAncestor(root.left, p, q);

        // Recur on right subtrees
        System.out.println("Going right from node: " + root.val);
        TreeNode rightSubtree = lowestCommonAncestor(root.right, p, q);

        // Visit Node 
        // If both sides return not null, it means p and q are found in different subtrees => current node is their LCA
        if(leftSubtree != null && rightSubtree != null){
            System.out.println("Node " + root.val + " is the Lowest Common Ancestor of " + p.val + " and " + q.val);
            return root;
        }
        
        // If one side is null, return the non-null side
        // This means both p and q are in one subtree, or not found yet
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

    // Helper: find reference of a node with a given value
    public static TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;

        TreeNode left = findNode(root.left, val);
        if (left != null) return left;

        return findNode(root.right, val);
    }

    public static void main(String[] args) {
        
        LowestCommonAncestor solution = new LowestCommonAncestor();

        // First Example
        Integer[] treeArray1 = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root1 = buildTree(treeArray1);
        TreeNode p1 = findNode(root1, 5); 
        TreeNode q1 = findNode(root1, 1); 
        System.out.println("Result1: " + solution.lowestCommonAncestor(root1, p1, q1).val + "\n");

        TreeNode q2 = findNode(root1, 4) ;
        System.out.println("Result1: " + solution.lowestCommonAncestor(root1, p1, q2).val + "\n");

        // Second Example
        Integer[] treeArray3 = { 1, 2 };
        TreeNode root3 = buildTree(treeArray3);
        TreeNode p3 = findNode(root3, 1);
        TreeNode q3 = findNode(root3, 2);
        System.out.println("Result2: " + solution.lowestCommonAncestor(root3 , p3, q3 ).val + "\n");

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