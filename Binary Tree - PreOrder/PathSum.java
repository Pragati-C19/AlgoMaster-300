import java.util.*;

import javax.swing.tree.TreeNode;

public class PathSum {
    
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
    public int pathSum(TreeNode root, int targetSum) {
        
        if (root == null) return 0;

        int count = checkSum(root, targetSum, 0, 0);

        System.out.println("Final path count: " + count);
        return count;
    }

    // Recursion Function : to check sum
    private int checkSum(TreeNode root, int targetSum, int currentSum, int pathCount){

        if (root == null) return pathCount;

        System.out.println("Visiting Node: " + root.val + ", CurrentSum: " + currentSum);

        currentSum += root.val;

        System.out.println("CurrentSum after update : " + currentSum);

        // Base Case 
        if (currentSum == targetSum) {
            pathCount++;
            System.out.println("Path Found Count : " + pathCount);
        }
        
        if (currentSum > targetSum) {
            System.out.println("CurrentSum > targetSum, restarting from children...");
            
            pathCount = checkSum(root.left, targetSum, 0, pathCount);
            pathCount = checkSum(root.right, targetSum, 0, pathCount);
        }
        else if (currentSum < targetSum) {
            System.out.println("Continue path left and right...");
            
            pathCount = checkSum(root.left, targetSum, currentSum, pathCount);
            pathCount = checkSum(root.right, targetSum, currentSum, pathCount);
        }

        return pathCount;
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
        
        PathSum solution = new PathSum();

        // First Example
        Integer[] treeArray1 = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        TreeNode root1 = buildTree(treeArray1, 0);
        int targetSum1 = 8;
        System.out.println("Result1: " + solution.pathSum(root1, targetSum1) + "\n");

        // Second Example
        Integer[] treeArray2 = { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1 };
        TreeNode root2 = buildTree(treeArray2, 0);
        int targetSum2 = 22;
        System.out.println("Result2: " + solution.pathSum(root2, targetSum2) + "\n");

    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. root of binary Tree and targetSum is given
 * 2. return the number of paths, where sum of values = targetSum
 * 3. path does not need to start or end at the root or a leaf, just it should go downwards
 * 
 * 
 * Pattern :
 * 
 * 1. Will use recursion here
 * 2. if targetSum == pathSum -> return count++
 * 3. if pathSum < targetSum -> add root.left or root.right in path and call recursion
 * 4. if pathSum > targetSum -> reset pathSum to zero and check for root.left or root.right and call recursion
 * 
 * Pseudo Code :
 * 
 * 
 * function pathSum (root, targetSum) {
 *      
 *      int sumCount = checkSum(root, targetSum, 0, 0) 
 * 
 *      return sumCount
 * }
 * 
 * 
 * function checkSum(TreeNode root, int targetSum, int currentSum, int sumCount){
 *      
 *      // Base Case 
 *      if(currentSum == targetSum) return count++
 * 
 *      if(currentSum > targetSum) {
 *          currentSum = 0
 *          checkSum(root.left, targetSum, currentSum, sumCount)
 *          checkSum(root.right, targetSum, currentSum, sumCount)
 *      } 
 * 
 *      if(currentSum < targetSum){
 *          currentSum += root.val
 *          checkSum(root.left, targetSum, currentSum, sumCount)
 *          checkSum(root.right, targetSum, currentSum, sumCount)
 *      }
 * 
 *      return count;
 * }
 * 
 * 
 */
