import java.util.*;

import javax.swing.tree.TreeNode;

public class Flatten {
    
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


    // Globally Declaring Variables
    TreeNode head;

    // Driver Function
    public void flatten(TreeNode root){

        // Assign value to head
        head = new TreeNode();

        // calling recursion function 
        dfs(root);

        return;
    }

    // Recursion Function : To get the the Linked List
    public TreeNode dfs(TreeNode root){

        // Base Case :
        if(root == null){
            return null;
        }

        // Recur to left side and add it in linkedlist
        TreeNode leftSubtree = dfs(root.left);

        // Recur to right side and add it in linkedlist
        TreeNode rightSubtree = dfs(root.right);

        // Visit Node
        head = root;
        head.left = null;

        if (leftSubtree != null && rightSubtree != null) {
            head.right = leftSubtree;
            head.right.right = rightSubtree;
        }
        else if (leftSubtree == null) {
            head.right = rightSubtree;
        }
        else if (rightSubtree == null) {
            head.right = leftSubtree;
        }

        return root;
        
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

    // Helper Function : To Print Binary Tree 
    public static List<String> printTreeAsArrayFormat(TreeNode root) {
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
    
        queue.add(root);
    
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
    
            if (node == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(node.val));
                queue.add(node.left);
                queue.add(node.right);
            }
        }
    
        // Trim trailing "null"s (Leetcode does this)
        int i = result.size() - 1;
        while (i >= 0 && result.get(i).equals("null")) {
            result.remove(i--);
        }
    
        return result;
    }    

    public static void main(String[] args) {
        
        Flatten solution = new Flatten();

        // First Example
        Integer[] treeArray1 = {1, 2, 5, 3, 4, null, 6};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + printTreeAsArrayFormat(solution.flatten(root1)) + "\n");

        // Second Example
        Integer[] treeArray2 = {};
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + printTreeAsArrayFormat(solution.flatten(root2)) + "\n");

        // Third Example
        Integer[] treeArray3 = {0};
        TreeNode root3 = buildTree(treeArray3);
        System.out.println("Result3: " + printTreeAsArrayFormat(solution.flatten(root3)) + "\n");

    }
}

/*
 * 
 * Intuitions :
 * 
 * 1. Converting binary tree to linkedList
 * 2. Traverse the tree and add nodes to the linked list
 * 3. Return the head of the linked list
 * 4. conditions are
 * - linked list will use same TreeNode class 
 * - where root.right points to -> next node in the list
 * - root.left points to -> null
 * - linked list should be in the same order as a pre-order traversal
 * 
 * 
 * Pattern :
 * 
 * 1. Globally Declare TreeNode Head
 * 
 * 2. Recursion function 
 *      - Base Case : if(root == null) -> return null
 *      
 *      - Recur To left side and get head
 *      - Recur to right side and get head 
 *  
 *      - head = node.val
 * 
 *      - if(leftSubtree != null && rightSubtree != null)
 *          change head.left to null
 *          change head.right to leftSubtree
 *          change head.right.right to rightSubtree
 *      
 *      - if(leftSubtree == null)
 *          change head.right to rightSubtree
 * 
 *      - if(rightSubtree == null)
 *          change head.left to null
 *          change head.right to leftSubtree
 * 
 *      - return head
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * TreeNode head = new TreeNode
 * 
 * function void flatten(TreeNode root){
 *      postOrder(root);
 *      return;
 * }
 * 
 * function TreeNode postOrder(TreeNode root){
 * 
 *      // Base Case :
 *      if(root == null) return null
 * 
 *      // Recur to left side
 *      leftSubtree = postOrder(root.left)
 * 
 *      // Recur to right side
 *      rightSubTree = postOrder(root.right)
 * 
 *      // Visit Node
 *      head = root
 * 
 *      if(leftSubtree != null && rightSubtree != null) {
 *          head.left = null
 *          head.right = leftSubtree
 *          head.right.right = rightSubtree
 *      }
 *      else if(leftSubtree == null) {
 *          head.right = leftSubtree 
 *      }
 *      else if(rightSubtree == null) {
 *          head.left = null
 *          head.right = leftSubtree 
 *      }
 *      
 *      return head
 * }
 * 
 * 
 */
