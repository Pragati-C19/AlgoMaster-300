import java.util.*;

import javax.swing.tree.TreeNode;

public class ZigzagLevelOrder {
    
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
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
        
        ZigzagLevelOrder solution = new ZigzagLevelOrder();

        // First Example
        Integer[] treeArray1 = {3, 9, 20, null, null, 15, 7};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + solution.zigzagLevelOrder(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = {1};
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.zigzagLevelOrder(root2) + "\n");

        // Third Example
        Integer[] treeArray3 = {};
        TreeNode root3 = buildTree(treeArray3);
        System.out.println("Result3: " + solution.zigzagLevelOrder(root3) + "\n");
        
    }
}


/*
 * 
 * Intuition : 
 * 
 * 1. We have given root of binary tree
 * 2. We need to print zigzag level order traversal of binary tree
 * - i.e., from left to right, then right to left for the next level and alternate between
 * 
 * 
 * Pattern : 
 * 
 * 1. Will use BFS
 * 2. BFS always work with queue
 * 3. First will add root in queue
 * 4. then will start while loop till queue is not empty 
 * 5. Will check size of queue
 * 6. start for loop till it's equal to queueSize
 * 5. will pop root from the queue and store it in node variable
 * 6. current.add(node.val)
 * 7. then will check if i == (queueSize - 1)
 *  - if yes -> in queue add right node first then left
 *  - if no -> in queue add left node first then right (Normally we do this)
 * 8. result.add(new array(current))
 * 9. return result
 * 
 * 
 * Pesudo Code :
 * 
 * function zigzagLevelOrder(root) {
 * 
 *      result = new array
 *      current = new array
 *      
 *      queue = new Linkelist
 *      queue.add(root)
 *      
 *      while(!queue.isEmpty){
 *          queueSize = queue.size()
 *          
 *          for(i in range 0 to queueSize){
 *              
 *              node = queue.poll()
 *              current.add(node.val)
 *              
 *              if(i == queueSize - 1){
 *                  if(node.right != null) queue.add(node.right)
 *                  if(node.left != null) queue.add(node.left)
 *              }
 * 
 *              if(node.left != null) queue.add(node.left)
 *              if(node.right != null) queue.add(node.right)
 *          }
 *          
 *          result.add(new array current)
 *      }
 * 
 *      return result
 * }
 * 
 */