import java.util.*;

public class InvertTree {
    
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
    public TreeNode invertTree(TreeNode root) {
        
        // Base Case :
        if(root == null) return null;

        // Check left side
        invertTree(root.left);

        // Check right side
        invertTree(root.right);

        // Visit Root
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

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
        
        InvertTree solution = new InvertTree();

        // First Example
        Integer[] treeArray1 = {4, 2, 7, 1, 3, 6, 9};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + printTreeAsArrayFormat(solution.invertTree(root1)) + "\n");

        // Second Example
        Integer[] treeArray2 = { 2, 1, 3 };
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + printTreeAsArrayFormat(solution.invertTree(root2)) + "\n");

        // Third Example
        Integer[] treeArray3 = {};
        TreeNode root3 = buildTree(treeArray3);
        System.out.println("Result3: " + printTreeAsArrayFormat(solution.invertTree(root3)) + "\n");

    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. root is given need to return inverted tree
 * 
 * Pattern :
 * 
 * 1. saw the que and it seems they are interchanging right and left node of the root
 * 2. let's try doing it
 * 
 * Pseudo Code :
 * 
 * function invertedTree(root){
 * 
 *      // base case :
 *      if(root == null) return null
 * 
 *      // check left node
 *      invertedTree(root.left)
 *      
 *      // check right node
 *      invertedTree(root.right)
 * 
 *      // visit node
 *      if(node.right != null || node.left != null){
 *          temp = node.left
 *          node.left = node.right
 *          node.right = temp
 * 
 *          or use swap function
 *      }
 * 
 *      return root
 * 
 * }
 * 
 * 
 */