import java.util.*;

import javax.swing.tree.TreeNode;

public class RightSideView {
    
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
    public List<Integer> rightSideView(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();

        // Base Case
        if (root == null) {
            return result;
        }

        // Declare Queue
        Queue<TreeNode> queue = new LinkedList<>();

        // Add root to the Queue
        queue.add(root);

        while (!queue.isEmpty()) {
            
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                result.add(node.val);
                System.out.println("Adding " + node.val + " into " + result);

                // We need only right side view so just check for right side                
                if (node.right != null) {
                    queue.add(node.right);
                    System.out.println("[IF] right node val: " + node.right.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                    System.out.println("[IF] left node val: " + node.left.val);
                }

            }
        }

        return result;
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
        
        RightSideView solution = new RightSideView();

        // First Example
        Integer[] treeArray1 = {1, 2, 3, null, 5, null, 4};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + solution.rightSideView(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = {1, 2, 3, 4, null, null, null, 5};
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.rightSideView(root2) + "\n");

        // Third Example
        Integer[] treeArray3 = {1, null, 3};
        TreeNode root3 = buildTree(treeArray3);
        System.out.println("Result3: " + solution.rightSideView(root3) + "\n");
        
        // Fourth Example
        Integer[] treeArray4 = {};
        TreeNode root4 = buildTree(treeArray4);
        System.out.println("Result4: " + solution.rightSideView(root4) + "\n");
        
    }
}


/* 
 * 
 * Intuition :
 * 
 * 1. we have given root of binary tree.
 * 2. Return only right side of the Tree
 * 
 * 
 * Pattern :
 * 
 * 1. Will use BFS
 * 2. BFS always work with queue
 * 3. First will add root in queue
 * 4 then will start while loop till queue is not empty 
 * 5. will pop root from the queue and store it in node 
 * 6. will add that node.val in level or result -> in this que it will be result as we don't need level so
 * 7. if(node.right != null) then add that node.right in the queue
 * 
 * 
 * Psuedo Code :
 * 
 * 
 * function rightSideView{
 * 
 *      result = new array
 *      queue = new linklist
 *      
 *      queue.add(root)
 *      
 *      while(!queue.isEmpty){
 *          
 *          TreeNode node = queue.pop()
 *          
 *          result.add(node.val)
 *          
 *          if(node.right != null) queue.add(node.right)
 *      }
 * 
 *      return result;
 * 
 * }
 * 
 * 
 */