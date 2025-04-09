import java.util.*;

import javax.swing.tree.TreeNode;

public class MaxAncestorDiff {

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
    public int maxAncestorDiff(TreeNode root) {
        
        // here we have assigned root.val to both minSoFar and MaxSoFar bcoz we are going to compare with that value
        return findMinMax(root, root.val, root.val);

    }

    // Recursion Function : To Find MinMax
    private int findMinMax(TreeNode node, int minSoFar, int maxSoFar){

        // Base Case
        if (node == null){
            return 0;
        }

        System.out.println("Starting Values | Node : " + node.val + " Minimun Node : " + minSoFar + " Maximum Node : " + maxSoFar);

        // Update Min and Max values with current node value
        minSoFar = Math.min(minSoFar, node.val);
        maxSoFar = Math.max(maxSoFar, node.val);
        System.out.println("After and Update | minSoFar : " + minSoFar + " maxSoFar : " + maxSoFar);

        // Recursive call for left and right child
        int leftDiff = findMinMax(node.left, minSoFar, maxSoFar);
        System.out.println("    leftDiff : " + leftDiff);

        int rightDiff = findMinMax(node.right, minSoFar, maxSoFar);
        System.out.println("    rightDiff : " + rightDiff);

        int currentDiff = Math.max((node.val - minSoFar), (node.val - maxSoFar));
        System.out.println("    currentDiff : " + currentDiff);
        
        return currentDiff;
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
        
        MaxAncestorDiff solution = new MaxAncestorDiff();

        // First Example
        Integer[] treeArray1 = {8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13};
        TreeNode root1 = buildTree(treeArray1, 0);
        System.out.println("Result1: " + solution.maxAncestorDiff(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = { 1, null, 2, null, 0, 3 };
        TreeNode root2 = buildTree(treeArray2, 0);
        System.out.println("Result2: " + solution.maxAncestorDiff(root2) + "\n");

    }

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. Root of bianry tree is given
 * 2. need to find maximum value where v = [a.val - b.val]
 * 3. a is an ancestor of b
 * eg. root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences
 * some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 * 
 * Hint : For each subtree, find the minimum value and maximum value of its descendants.
 * 
 * Pattern :
 * 
 * First function
 * 1. Checking the node 
 * 2. find minVal
 * 3. find maxVal
 * 4. take a difference of (root - minVal) and (root - MaxVal)
 * 5. return max of above two differences
 * 
 * Second Function 
 * 1. Traverse the tree
 * 2. for each node, call the first function
 * 3. return max of all the values
 * 
 * Let's use a little bit logic of PathSum here 😉
 * 
 * Pseudo Code :
 * 
 * 1. First Approach
 * - Compare only left/right child
 * - Get min/max from children
 * 
 * function maxAncestorDiff(TreeNode root){
 * 
 *      // Let's start with root
 *      
 *      currentMaxDiff = findMinMax(root);
 *      
 *      v = Math.max(max, currentMaxDiff)
 * 
 *      return v;
 * }
 * 
 * 
 * function findMinMax(root){
 *      
 *      if(root.left.val <= root.val){
 *          minVal = (root.left.val);
 *      } 
 *      else {
 *          maxVal = root.left.val;
 *      }
 *      
 *      if(root.right.val <= root.val){
 *          minVal = root.right.val;
 *      } 
 *      else {
 *          maxVal = root.right.val;
 *      }
 *      
 *      currentMaxDiff = Math.max((root.val - minVal), (root.val - maxVal))
 *  
 *      return currentMaxDiff
 * }
 * 
 * 
 * 2. Second Approach
 * - Compare with deepest descendant
 * - Pass min/max down and update while going deeper
 * 
 * function maxAncestorDiff(TreeNode root){
 * 
 *      // Let's start with root
 *      
 *      currentMaxDiff = findMinMax(root);
 *      
 *      v = Math.max(max, currentMaxDiff)
 * 
 *      return v;
 * }
 * 
 * 
 * function findMinMax(TreeNode root, int minSoFar, int maxSoFar){
 *      
 *      if(root == null) return 0;
 * 
 *      currentMaxDiff = Math.max((root.val - minSoFar), (root.val - maxSoFar))
 * 
 *      if(root.val <= minSoFar){
 *          minSoFar = findMinMax(root.left, root.val, maxSoFar)
 *          minSoFar = findMinMax(root.right, root.val, maxSoFar)
 *      } 
 *      else {
 *          maxSoFar = findMinMax(root.left, minSoFar, root.val)
 *          maxSoFar = findMinMax(root.right, minSoFar, root.val)
 *      }
 *  
 *      return currentMaxDiff
 * }
 * 
 * 
 * 3. Third Approach
 * - use Min Max function to get min and max between minSoFar and root.val
 * 
 *  
 * function maxAncestorDiff(TreeNode root){
 *      
 *      return findMinMax(root, root.val, root.val)
 * 
 * }
 * 
 * 
 * function findMinMax(TreeNode root, int minSoFar, int maxSoFar){
 *      
 *      // Base Case
 *      if(root == null) return 0;
 *      
 *      // Update Min and Max values with current node value
 *      minSoFar = Math.min(minSoFar, root.val)
 *      maxSoFar = Math.max(maxSoFar, root.val)
 * 
 *      // Check left and right
 *      leftDiff = findMinMax(root.left, minSoFar, maxSoFar)
 *      rightDiff = findMinMax(root.right, minSoFar, maxSoFar)
 *      
 *      // Max difference at this level
 *      currentMaxDiff = Math.max((root - minSoFar), (root - maxSoFar))
 *      
 *      return currentMaxDiff
 * }
 * 
 * 
 * 
 */