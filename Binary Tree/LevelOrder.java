import java.util.*;

public class LevelOrder {
    
    private class TreeNode {
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<>();

        // Base Case 
        if (root == null) {
            return result;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(root.val);

        while (!queue.isEmpty()) {
            
            List<Integer> level = new ArrayList<>();
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                
                // Remove Node and Declare it
                TreeNode node = queue.poll();

                // Add that Node to level array
                level.add(node);
                
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }

            }

            result.add(level);
        }

        return result;
    }

    // Helper function to build a tree from an array (for testing)
    public static TreeNode buildTree(Integer[] nodes) {
        
        // Node is empty
        if (nodes.length == 0 || nodes[0] == null) return null;
        
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.add(root);
        
        int i = 1;

        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode parent = queue.poll();
            
            if (nodes[i] != null) {
                parent.left = new TreeNode(nodes[i]);
                queue.add(parent.left);
            }
            i++;
            
            if (i < nodes.length && nodes[i] != null) {
                current.right = new TreeNode(nodes[i]);
                queue.add(parent.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        Integer[] treeArray = {3, 9, 20, null, null, 15, 7};
        TreeNode root = buildTree(treeArray);
        
        List<List<Integer>> result = levelOrder(root);
        System.out.println(result);
    }
}

/*
 * 
 * Intuitions :
 * 
 * 1. Root is given of a binary tree
 * 2. Print the level order traversal of it's nodes
 * 3. level order traversal means - put all nums in one array which at same level of binary tree 
 * 4. Like in Linkedlist we used head, next, prev same will use in Binary Tree too which is root, left, right
 * 
 * 
 * Pattern : 
 * 
 * 1. Will use queue here to store nodes - FIFO (First In First Out)
 * 3. here first will add root in queue
 * 4. then will start loop till queue get's empty
 * - first will pop root then 
 * - Push its left and right children (if they exist) into the queue.
 * - Repeat until the queue is empty.
 * - Return the result list.
 * 
 * 6. Basic Pattern is BST(Queue)
 * - Use Queue (FIFO)
 * - Process level-by-level
 * - Add left child then right child to the queue
 * 
 * 
 * Pseudo Code : 
 * 
 * function levelOrder(TreeNode root){
 *      
 *      result = new arrayList
 *  
 *      // Base Case :
 *      if(root == null) return;
 * 
 *      Queue<TreeNode> queue = new LinkList<>();
 *      queue.add(root)
 *      
 *      while(!queue.isEmpty){
 *          levelSize = queue.size()
 *          level = new arrayList   // it's same like current in Backtracking
 *          
 *          for(i = 0 to levelSize - 1){
 *              TreeNode node = queue.poll()
 *              
 *              level.add(node.val)
 *              
 *              if(node.left != Null){
 *                  queue.add(node.left)
 *              }
 *          
 *              if(node.right != null){
 *                  queue.add(node.right)
 *              }
 *          }
 *          
 *          result.add(level)
 *      }
 * 
 *      return result;
 * }
 * 
 * 
 */
