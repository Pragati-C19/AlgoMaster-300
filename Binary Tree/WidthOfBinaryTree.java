import java.util.*;

public class WidthOfBinaryTree {

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. We have giving a Binary Tree
 * 2. We have to find the maximum width of the tree
 * 3. The maximum width of a tree is the maximum width among all levels.
 * 4. We can use BFS to solve this problem
 *
 * 
 * Pattern :
 * 
 * 1. We'll use BFS
 * 2. We'll use a queue to store the nodes at each level
 * 3. will add root to the queue
 * 4. Also will declare a variable maxWidth = 0
 * 5. start while loop till queue.isEmpty()
 * 6. will check levelSize
 * 
 * 
 * Here I'm thinking of 2-3 approaches
 * 1. will do level++ count
 * 2. at the end of loop use max = Math.max(max, levelSize + 1)
 * 3. Even if the specific node is null still we want to count it so -
 * - we can say count levels of the three, and at end out side of while loop
 * will use 2 ^ levelCount eg. if levels are 2 then maxWidth will be 2 ^ 2 = 4
 * also one thing to rememeber levelCount will start at 0 
 * 4. we used to check node.left or node.right != null will not do it at all..
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 1. function connect(Node root){
 * 
 *      Base Case 
 *      if(root == null) return 0
 * 
 *      int maxWidth = 0;
 *      
 *      queue = new Linkedlist
 *      queue.add(root)
 * 
 *      while(!queue.isEmpty){
 *          levelSize = queue.size()
 *          
 *          maxWidth = Math.Max(maxWidth, levelSize)
 *          
 *          for (i till levelSize){
 *              TreeNode node = queue.pop();
 *              
 *              queue.add(node.left)
 *              queue.add(node.right)
 *             
 *          }
 *          
 *      }
 *  
 *      return maxWidth;
 * }
 * 
 * 
 * 2. function connect(Node root){
 * 
 *      Base Case 
 *      if(root == null) return 0
 *      
 *      int maxWidth = 0;
 *      int level = 0;
 *      
 *      queue = new Linkedlist
 *      queue.add(root)
 * 
 *      while(!queue.isEmpty){
 *          levelSize = queue.size()
 *          
 *          maxWidth = Math.Max(maxWidth, level)
 * 
 *          for (i till levelSize){
 *              TreeNode node = queue.pop();
 *              
 *              queue.add(node.left)
 *              queue.add(node.right)
 *             
 *          }
 *          
 *          level++;
 *      }
 *  
 *      return maxWidth;
 * }
 * 
 * 
 * 3. function connect(Node root){
 * 
 *      Base Case 
 *      if(root == null) return 0
 *      
 *      int maxWidth = 0;
 *      int level = 0;
 *      
 *      queue = new Linkedlist
 *      queue.add(root)
 * 
 *      while(!queue.isEmpty){
 *          levelSize = queue.size()
 *          
 *          maxWidth = Math.Max(maxWidth, 2 ^ level)
 * 
 *          for (i till levelSize){
 *              TreeNode node = queue.pop();
 *              
 *              queue.add(node.left)
 *              queue.add(node.right)
 *             
 *          }
 *          
 *          level++;
 *      }
 *  
 *      return maxWidth;
 * }
 * 
 * 
 * 
 * 
 */
