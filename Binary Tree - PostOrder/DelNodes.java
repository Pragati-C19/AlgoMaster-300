import java.util.*;

import javax.swing.tree.TreeNode;

public class DelNodes {
    
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

    // Globally Declare variable
    List<TreeNode> result;
    Set<Integer> deleteSet = new HashSet<>();

    // Driver Function 
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        
        // Assinging value to result
        result = new ArrayList<>();

        for (int i = 0; i < to_delete.length; i++) {
            deleteSet.add(to_delete[i]);
        }

        // Print entire deleteMap after filling
        System.out.println("deleteSet: " + deleteSet);

        postOrder(root);

        // Print entire deleteMap after filling
        System.out.println("Final Result: " + result);

        return result;
    }

    // Recursion Function : postOrder
    private TreeNode postOrder(TreeNode root){

        // Base Case :
        if (root == null){
            return null;
        }

        System.out.println("Visiting root : " + root.val);

        // Recur to left side
        TreeNode leftSubTree = postOrder(root.left);

        // Recur to right side
        TreeNode rightSubTree = postOrder(root.right);

        // Visit Node
        if (deleteSet.contains(root.val)){
            
            System.out.println("Node " + root.val + " is marked for deletion.");
            
            // If Node is to be deleted then add left and right subtrees to result
            if (leftSubTree != null) {
                result.add(leftSubTree);
                System.out.println("  Adding left subtree of node " + root.val + " (root: " + leftSubTree.val + ") to result.");
            }

            if (rightSubTree != null) {
                result.add(rightSubTree);
                System.out.println("  Adding right subtree of node " + root.val + " (root: " + rightSubTree.val + ") to result.");
            }

            // bcoz we have deleted the node
            return null;
        }

        System.out.println("Returning node " + root.val + " as part of current tree.");
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

    // Helper Function : to serialize trees to list format
    public static List<List<String>> serializeForest(List<TreeNode> forest) {
        List<List<String>> result = new ArrayList<>();
        for (TreeNode node : forest) {
            result.add(printTreeAsArrayFormat(node));
        }
        return result;
    }
    

    public static void main(String[] args) {
        
        DelNodes solution = new DelNodes();

        // First Example
        Integer[] treeArray1 = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root1 = buildTree(treeArray1);
        int[] to_delete1 = {3, 5};
        System.out.println("Result1: " + serializeForest(solution.delNodes(root1, to_delete1)) + "\n");

        // Second Example
        Integer[] treeArray2 = {1, 2, 4, null, 3};
        TreeNode root2 = buildTree(treeArray2);
        int[] to_delete2 = {3};
        System.out.println("Result1: " + serializeForest(solution.delNodes(root2, to_delete2)) + "\n");

    }
}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. root of binary tree and to_delete array is given
 * 2. we need to delete a node from binary tree
 * 3. In return we need a list of new TreeNodes
 * 4. I need to use hashmap here which stores
 * 
 * correction :
 * - will use hashSet not hashmap bcoz in hashSet we only store key or value and in hashMap we store key and value both  
 * 
 * Pattern :
 * 
 * 1. Globally Declare deleteMap
 * 2. Main Function 
 * - Traverse the tree and store the nodes in deleteMap
 * - recursion call
 * - return result;
 * 
 * 3. recursion function 
 * - if(root == null) null;
 * - check left side
 * - check right side
 * - if(deleteMap.contains(root.val)) 
 *      -> if(leftSubtree != null) result.add(new TreeNode(leftSubtree))
 *      -> if(rightSubtree != null) result.add(new TreeNode(rightSubtree))
 *      -> return null; // bcoz that specific node is deleted
 * - return root
 * 
 * Pseudo Code :
 * 
 * 
 * // Globally Declare Variable
 * Map<Integer, Boolean> deleteMap = new HashMap<>()
 * result = new ArrayList
 * 
 * // Main Function
 * function delNodes(root, to_delete){
 *      
 *      for(i from 0 to to_delete.length){
 *          deleteMap.put(to_delete[i], false)
 *      }
 * 
 *      postOrder(root)
 *      
 *      return result;
 *      
 * }
 * 
 * function postOrder(root){
 * 
 *      // Base Case :
 *      if(root == null) return root;
 * 
 *      // Recur to left Side
 *      leftSubtree = postOrder(root.left)
 *      
 *      // Recur to right Side
 *      rightSubTree = postOrder(root.right)
 * 
 *      // Visit node
 *      if(deleteMap.get(root.val)){
 *          if(leftSubTree != null) result.add(new TreeNode(leftSubTree))
 *          if(leftSubTree != null) result.add(new TreeNode(rightSubTree))
 *          return null
 *      }
 * 
 *      return root;
 * }
 * 
 */