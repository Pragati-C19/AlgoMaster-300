import java.util.*;

public class LevelOrder {
    
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
