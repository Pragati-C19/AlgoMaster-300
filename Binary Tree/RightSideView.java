import java.util.*;

public class RightSideView {
    
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