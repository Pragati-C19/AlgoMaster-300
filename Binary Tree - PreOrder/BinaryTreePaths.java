import java.util.*;

import javax.swing.tree.TreeNode;

public class BinaryTreePaths {
    
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
    public List<String> binaryTreePaths(TreeNode root) {
        
    }

    // Recursion Backtracking Function
    private List<String> backtrack(TreeNode node, String currentPath, List<String> result){

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
        
        BinaryTreePaths solution = new BinaryTreePaths();

       // First Example
       Integer[] treeArray1 = {1, 2, 3, null, 5};
       TreeNode root1 = buildTree(treeArray1, 0);
       System.out.println("Result1: " + solution.binaryTreePaths(root1) + "\n");

       // Second Example
       Integer[] treeArray2 = { 1 };
       TreeNode root2 = buildTree(treeArray2, 0);
       System.out.println("Result2: " + solution.binaryTreePaths(root2) + "\n");

    }

}


/*
 * 
 * Intuitions :
 * 
 * 1. We are given the root of the tree.
 * 2. need to return root->leaf path
 * 3. will use recursion here
 * 4. Appraoch
 * - Start from root.
 * - Build a string like "1->2->5" while going down.
 * - When at a leaf (left and right are null), save the string.
 * - Then backtrack so the same string isn’t reused for the next branch.
 * 5. So this is more like building up a string as we go down.
 * 6. When we reach a leaf node (no left or right), we save that path.
 * 7. We can do this using:
 * - Normal string building (current + "->" + nextVal)
 * - Or using StringBuilder for efficiency and clear backtracking
 * 
 * 
 * Pattern :
 * 
 * 1. Let's try using stack first
 * 2. we will push root in stack
 * 3. while stack is not empty will follow below steps
 * - will check stackSize
 * - current =  
 * - if(root.left != null) stack.push(root.left)
 * - if(root.right != null) stack.push(root.right)
 * 
 * 4. Now I think of it I need to use backtracking here.. 
 * - if we see the flow 1->2->5, 1->3 see it just not taking that value out of stack it's backtracking it removing it's traces
 * - just I'm not able to think of how to use it yet.. 
 * - Base Case : if(root.left && root.right == null) result.add(current)
 * - Go left if it exists.
 * - Go right if it exists.
 * - But before doing that:
 *      - In normal string approach: just build the string.
 *      - In StringBuilder approach:
 *          - Save length before
 *          - Append "->" and value
 *          - Recurse
 *          - Then reset length to backtrack
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 1. Not using StringBuilder Normal approach
 * 
 * function binaryTreePaths(root) {
 * 
 *      result = new arraylist
 *      string currentPath = InttoString(root.val)
 *      
 *      // Start with root node and its value as the path
 *      backtrack(root, currentPath, result)
 *  
 *      return result;
 * }
 * 
 * 
 * function backtrack(TreeNode node, String currentPath, List<String> result) {
 * 
 *      // Base Case:
 *      if(node.left == null && node.right == null) {
 *          result.add(currentPath)
 *      }
 *      
 *      // If left child exists, go left
 *      if(node.left != null) {
 *          backtrack(node.left, currentPath + "->" + node.left.val, result)
 *          current.remove(current.size() - 3)
 *      }
 * 
 *      // If right child exists, go right
 *      if(node.right != null){
 *          backtrack(node.right, currentPath + "->" + node.right.val, result)
 *          current.remove(current.size() - 3)
 *      }
 * }
 * 
 * 
 * 2. Using StringBulder and Clear backtracking thing (Took help from Gpt)
 * 
 * List<String> binaryTreePaths(TreeNode root) {
 *      
 *      result = new arraylist;
 *      
 *      // Initialize StringBuilder with root value
 *      StringBuilder currentPath = new StringBuilder();
 *      currentPath.append(root.val);
 * 
 *      // Start recursive traversal
 *      backtrack(root, currentPath, result);   
 *      return result;
 * 
 * }
 * 
 * function backtrack(TreeNode node, StringBuilder currentPath, List<String> result) {
 * 
 *      // Base case: if it's a leaf node, store the full path
 *      if (node.left == null && node.right == null) {
 *          result.add(currentPath);
 *      }
 * 
 *      // Explore left child
 *      if (node.left != null) {
 * 
 *          int lenBefore = currentPath.length();  // Save current length
 *          
 *          currentPath.append("->").append(node.left.val);  // Append to path
 *          
 *          backtrack(node.left, currentPath, result);       // Recurse
 *              
 *          currentPath.setLength(lenBefore);  // Backtrack
 *      }
 * 
 *      // Explore right child
 *      if (node.right != null) {
 *      
 *          int lenBefore = currentPath.length();  // Save current length
 * 
 *          currentPath.append("->").append(node.right.val);
 * 
 *          backtrack(node.right, currentPath, result);
 *      
 *          currentPath.setLength(lenBefore);  // Backtrack
 *      }
 * 
 * }
 * 
 */