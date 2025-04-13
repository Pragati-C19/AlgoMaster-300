import java.util.*;

import javax.swing.tree.TreeNode;

public class MinDiffInBST {

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
    public int minDiffInBST(TreeNode root) {
        
        return findMinDiff(root, root.val, root.val);

    }

    // Recursion Function : To find Minimum Difference
    private int findMinDiff( TreeNode root, int minSoFar, int maxSoFar){

        // Base Case:
        if (root == null) {
            return (maxSoFar - minSoFar);
        }

        System.out.println("Starting Values | Node : " + root.val + " Minimun Node : " + minSoFar + " Maximum Node : " + maxSoFar);

        // Get Value of minSoFar and maxSoFar
        minSoFar = Math.min(root.val, minSoFar);
        maxSoFar = Math.max(root.val, maxSoFar);
        System.out.println("After and Update | minSoFar : " + minSoFar + " maxSoFar : " + maxSoFar);

        // Check leftSubtree and rightSubtree
        int leftMinDiff = findMinDiff(root.left, minSoFar, maxSoFar);
        System.out.println("    leftDiff : " + leftMinDiff);

        int rightMinDiff = findMinDiff(root.right, minSoFar, maxSoFar);
        System.out.println("    rightDiff : " + rightMinDiff);

        // check minimum difference between left side and right side
        int currentMinDiff = Math.min(leftMinDiff, rightMinDiff);
        System.out.println("    currentDiff : " + currentMinDiff);

        return currentMinDiff;
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
        
        MinDiffInBST solution = new MinDiffInBST();

        // First Example
        Integer[] treeArray1 = {4, 2, 6, 1, 3};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + solution.minDiffInBST(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = { 1, 0, 48, null, null, 12, 49 };
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.minDiffInBST(root2) + "\n");

    }
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. Root of Binary Tree is given
 * 2. Find the minimum of any two different nodes from the tree 
 * 3. this problem is almost same as MaxAncestorDiff
 * 
 * 
 * Pattern :
 * 
 * 1. We'll use recursion here
 * 2. We'll find the minimum of the left subtree and the right subtree
 * 3. We will find minSoFar and MaxSoFar
 * 4. if root == null -> difference of minSoFar maxSofar
 * 5. then will call for recursion first left then right
 * 6. will find min(leftSubtree, rightSubtree)
 * 
 * Pseudo Code :
 * 
 * 
 * function minDiffInBST(root){
 * 
 *      return findMinDiff(root, root.val, root.val, root.val)
 * }
 * 
 * function findMinDiff(root, minSoFar, maxSoFar) {
 * 
 *      if(root == null) return (maxSoFar - minSoFar)     
 * 
 *      minSoFar = min(minSoFar, root.val)
 *      maxSoFar = max(maxSoFar, root.val)
 * 
 *      leftSubtree = findMinDiff(root.left, minSoFar, maxSoFar)
 *      rightSubtree = findMinDiff(root.right, minSoFar, maxSoFar)
 * 
 *      currentMinDiff = min(leftSubtree, rightSubtree) 
 * 
 *      return currentMinDiff;
 * 
 * }
 * 
 * 
 */