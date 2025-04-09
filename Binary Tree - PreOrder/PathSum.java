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
        
        // Base Case
        if(root == null) return 0;
        
        System.out.println("Starting new path from Node: " + root.val);
        
        // Try path Starting from node
        int countFromRoot = countPathFromNode(root, targetSum);
        System.out.println("  Paths from Node " + root.val + ": " + countFromRoot);
        
        // Try path of left and right 
        int countLeftPath = pathSum(root.left, targetSum);
        int countRightPath = pathSum(root.right, targetSum);
        
        int total = countFromRoot + countLeftPath + countRightPath;
        System.out.println("  Total paths at Node " + root.val + ": " + total);

        return total;
    }

    // Recursion Function : Count Path from Node
    private int countPathFromNode(TreeNode node, int targetSum) {

        // Base Case
        if(node == null) return 0;

        System.out.println("    Visiting Node: " + node.val + ", Remaining Target: " + targetSum);

        int count = 0;

        if (node.val == targetSum) {
            count++;
            System.out.println("     Found path ending at Node " + node.val + " (Remaining target matched!)");
   
        }

        // Keep reducing target and go deeper
        count += countPathFromNode(node.left, targetSum - node.val);
        count += countPathFromNode(node.right, targetSum - node.val);

        return count;
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

        // Third Example
        Integer[] treeArray3 = { 1000000000, 1000000000, null, 294967296, null, 1000000000, null, 1000000000, null, 1000000000 };
        TreeNode root3 = buildTree(treeArray3, 0);
        int targetSum3 = 0;
        System.out.println("Result3: " + solution.pathSum(root3, targetSum3) + "\n");

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
 * 
 * What I was Doing Right:
 * - Recursion is the key
 * - tracking the path sum 
 * - Paths don’t have to start from root or end at leaf
 * - using currentSum to track a running sum 
 * - trying to restart when sum exceeds targetSum (like pruning)
 * 
!   Where the Thinking Breaks (Slightly): 
 * 1. Every node can be the start of a path
 * - I'm only tracking one path at a time, starting from root. But the trick is:
 * - need to try every node as the starting point of a path.
 * 
 * 2. Why resetting currentSum = 0 doesn’t work
 * - Suppose you're on some path that failed, restarting from current node's child won't help,
 * - Instead, you should start a new DFS from each node, not just continue with same recursion.


 * Pseudo Code :
 * 
 * 1. Approach is mine but it has some flaws 
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
 * 2. Correct Approach
 * 
 * funtion pathSum(root, target){
 *      
 *      // Base Case
 *      if(root == null) return 0;
 * 
 *      // Try path Starting from node
 *      countFromRoot = countPathFromNode(root, targetSum)
 * 
 *      // Try path of left and right 
 *      countLeftPath = pathSum(root.left, targetSum)
 *      countRightPath = pathSum(root.right, targetSum)
 * 
 *      return countFromRoot + countLeftPath + countRightPath
 * 
 * }
 * 
 * 
 * function countPathFromNode(node, targetSum){
 * 
 *      if(node == null) return 0
 * 
 *      if(node.val == targetSum) count++;
 * 
 *      // Keep reducing target and go deeper
 *      count += countPathFromNode(node.left, targetSum - node.val)
 *      count += countPathFromNode(node.right, targetSum - node.val)
 *      
 *      return count
 * }
 * 
 * 
 */
