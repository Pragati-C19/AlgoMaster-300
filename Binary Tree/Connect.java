import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

public class Connect {

    // Definition for a Node.
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    // Driver Function
    public Node connect(Node root) {
        
        // Base Case
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            
            int levelSize = queue.size();

            for (int i = 0; i < levelSize ; i++) {
                
                Node head = queue.poll();

                head.next = head;

                if(i == (levelSize - 1)){
                    head.next = null;
                }

                // Below thing will always be same for all level order problems : Remember left first then right 
                if (head.left != null) {
                    queue.add(head.left);
                }

                if (head.right != null) {
                    queue.add(head.right);
                }
            }

        }

        return head.next;

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
        
        Connect solution = new Connect();

        // First Example
        Integer[] treeArray1 = {1, 2, 3, 4, 5, null, 7};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + solution.connect(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = {};
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.connect(root2) + "\n");

    }


}


/*
 * 
 * Intuitions : 
 * 
 * 1. In short we need to convert a Binary Tree into linkedlist
 * 2. If level is ended then add # (representation for null) next to that levels last num
 *
 * 
 * Pattern :
 * 
 * 1. Will use BFS
 * 2. BFS always work with queue
 * 3. First will add root in queue
 * 4. then will start while loop till queue is not empty 
 * 5. get queueSize which is as same as levelSize bcoz level means the horizontal bradth of tree
 * 6. then will start for loop till i = levelSize
 * 7. will pop root from the queue and store it in node 
 * 8. if(i == (levelsize - 1)) add "#"
 * 9. else add node.next = next node of same level (which is node.val)
 * 10. As always do the checks of node.left and node.right
 * 11. return result
 * 
 * Psuedo Code :
 * 
 * 
 * function connect(Node root){
 * 
 *      result = new Linkelist
 *      queue = new Linkedlist
 *      queue.add(root)
 * 
 *      while(!queue.isEmpty){
 *          levelSize = queue.size() - 1
 *          
 *          for (i till levelSize){
 *              Node head = queue.pop();
 *              
 *              result.add(head.val)
 *              if(i == (levelSize - 1)) result.add("#")
 *              
 *              if(head.left != null) queue.add(head.left)
 *              if(head.right != null) queue.add(head.right)
 *             
 *          }
 *      }
 * 
 * }
 * 
 */
