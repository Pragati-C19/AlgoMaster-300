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
