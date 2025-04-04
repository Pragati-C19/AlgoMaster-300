import java.util.*;

public class ZigzagLevelOrder {
    
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