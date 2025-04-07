import java.util.*;

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

        System.out.println("Root At Start : " + root.val);

        while (!queue.isEmpty()) {
            
            int levelSize = queue.size();
            System.out.println("Level of Size " + levelSize + " Started ");

            for (int i = 0; i < levelSize ; i++) {
                
                Node curr = queue.poll();
                
                System.out.println("[FOR] Current Node : " + curr.val);

                if(i < (levelSize - 1)){
                    // queue.peek() means: "show me the next element in the queue, but don’t remove it."
                    curr.next = queue.peek();
                    System.out.println("[IF] Next Node of Current Node  : " + curr.next.val);
                }

                // Below thing will always be same for all level order problems : Remember left first then right 
                if (curr.left != null) {
                    queue.add(curr.left);
                }

                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }

            System.out.println("\n");
        }

        return root;

    }

    // Helper Function : to build a tree from an array (for testing)
    public static Node buildTree(Integer[] nodes) {
        
        // Node is empty
        if (nodes.length == 0 || nodes[0] == null) return null;
        
        Node root = new Node(nodes[0]);
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(root);
        
        int i = 1;  // Start from second element

        while (!queue.isEmpty() && i < nodes.length) {
            Node parent = queue.poll();
            
            // Assign left child
            if (nodes[i] != null) {
                parent.left = new Node(nodes[i]);
                queue.add(parent.left);
            }
            i++;
            
            // Assign right child (check if there's still an element)
            if (i < nodes.length && nodes[i] != null) {
                parent.right = new Node(nodes[i]);
                queue.add(parent.right);
            }
            i++;
        }

        return root;
    }

    // Helper Function : To Print List
    public static void printNextAsList(Node root) {
        List<String> output = new ArrayList<>();
        Node levelStart = root;
    
        while (levelStart != null) {
            Node curr = levelStart;
            levelStart = null;
    
            while (curr != null) {
                output.add(String.valueOf(curr.val));
    
                if (levelStart == null) {
                    if (curr.left != null) levelStart = curr.left;
                    else if (curr.right != null) levelStart = curr.right;
                }
    
                curr = curr.next;
            }
    
            output.add("#");
        }
    
        System.out.println(output);
    }
    

    public static void main(String[] args) {
        
        Connect solution = new Connect();

        // First Example
        Integer[] treeArray1 = {1, 2, 3, 4, 5, null, 7};
        Node root1 = buildTree(treeArray1);
        // System.out.println("Result1: ");
        printNextAsList(solution.connect(root1));
        System.out.println("\n");

        // Second Example
        Integer[] treeArray2 = {};
        Node root2 = buildTree(treeArray2);
        // System.out.println("Result2: ");
        printNextAsList(solution.connect(root2));

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
 * 7. will pop root from the queue and store it in curr 
 * 8. if(i < (levelsize - 1)) curr.next = next node of same level (which will take by using peek())
 * 9. As always do the checks of curr.left and curr.right
 * 10. return root
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
 *          levelSize = queue.size()
 *          
 *          for (i till levelSize){
 *              Node curr = queue.pop();
 *              
 *              if(i < (levelSize - 1)) curr.next = queue.peek()
 *              
 *              if(curr.left != null) queue.add(curr.left)
 *              if(curr.right != null) queue.add(curr.right)
 *             
 *          }
 *      }
 *  
 *      return root
 * }
 * 
 */
