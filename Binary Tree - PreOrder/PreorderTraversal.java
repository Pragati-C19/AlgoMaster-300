import java.util.*;

public class PreorderTraversal {
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
    public List<Integer> preorderTraversal(TreeNode root) {
        
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
        
        PreorderTraversal solution = new PreorderTraversal();

        // First Example
        Integer[] treeArray1 = {1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + solution.preorderTraversal(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = { 1 };
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.preorderTraversal(root2) + "\n");

        // Third Example
        Integer[] treeArray3 = {};
        TreeNode root3 = buildTree(treeArray3);
        System.out.println("Result3: " + solution.preorderTraversal(root3) + "\n");
        
    }

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. To solve preorder we use stack or recurssion 
 * 2. We first visit the root node 
 * 3. then will add right to the stack 
 * 4. then will add left to the stack 
 * - Remember why we are doing by this order bcoz stack is FILO approach.. not like queue which is FIFO
 * 5. then we will pop the stack and print the value of the node which is process(root)
 * 
 * 
 * Pattern :
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * function preorderTraversal (root) {
 * 
 *      result = new Array
 *      
 *      Stack<TreeNode> stack = new Stack
 *      stack.push(root)
 * 
 *      (!stack.isEmpty){
 *          
 *          stackSize = stack.size()
 *          
 *          for(i from 0 to stackSize){
 *              
 *              TreeNode node = stack.pop()
 *              result.add(node)
 *              
 *              if(node.right != null) stack.push(node.right)
 *              if(node.left != null) stack.push(node.left)
 *               
 *          }
 *      }
 *      
 *      return result;
 * }
 * 
 */
