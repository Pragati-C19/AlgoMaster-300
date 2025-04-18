import java.util.*;

import javax.swing.tree.TreeNode;

public class CountNodes {
    
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
    public int countNodes(TreeNode root) {
        
        // Base Case :
        if (root == null) {
            return 0;
        }

        // will use shortcut here for more details check pseudo code
        // currentCount + leftNodeCount + rightNodeCount
        return 1 + countNodes(root.left) + countNodes(root.right);

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
        
        CountNodes solution = new CountNodes();

        // First Example
        Integer[] treeArray1 = {1, 2, 3, 4, 5, 6};
        TreeNode root1 = buildTree(treeArray1, 0);
        System.out.println("Result1: " + solution.countNodes(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = { 1 };
        TreeNode root2 = buildTree(treeArray2, 0);
        System.out.println("Result2: " + solution.countNodes(root2) + "\n");

        // Third Example
        Integer[] treeArray3 = {};
        TreeNode root3 = buildTree(treeArray3, 0);
        System.out.println("Result3: " + solution.countNodes(root3) + "\n");
        
    }
}


/*
 * 
 * Intuition :
 * 
 * 1. we have given root
 * 2. we need to count nodes
 * 3. we should use recursion ig let's see
 * 
 * 
 * Pattern :
 * 
 * 1. We need a int currentCount which stores count of nodes
 * 2. will use stack approach first as it's my first brute force type thought so 
 * 3. with recursion 
 * - Base case: if root is null, return 0
 * - Count this current node → 1
 * - Go left → get count of nodes from left side
 * - Go right → get count of nodes from right side
 * - Add all of them → done
 * 
 * 
 * Pseudo Code :
 * 
 * 1. With stack - just like brute force
 * 
 * function countNodes (TreeNode root){
 *      
 *      int currentCount
 * 
 *      stack = new stack
 *      stack.push(root)
 *      
 *      while(!stack.isEmpty){
 *          
 *          stacksize = stack.size()
 *          
 *          for(i from 0 to stacksize){
 *              node = stack.pop()
 *              
 *              currentCount++;
 *              
 *              left != null -> stack.push(node.left)
 *              right != null -> stack.push(nide.right)
 *          }
 *      }
 *      
 *      return currentCount;
 * }
 * 
 * 
 * 2. with recursion
 * 
 * function countNodes (TreeNode root){
 *      
 *      // count the current node
 *      int currentCount = 1
 * 
 *      // count left node
 *      int leftNodeCount = countNodes(root.left)
 * 
 *      // count right node
 *      int rightNodeCount = countNodes(root.right)
 * 
 *      int total = currentCount + leftCount + rightCount
 * 
 *      return total
 * 
 * 
 *      //todo: short version of all of this is 
 *      // currentNode + leftNodeCount + rightNodeCount
 *      return 1 + countNodes(root.left) + countNodes(root.right)
 * }
 * 
 */