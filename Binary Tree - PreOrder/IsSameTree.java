import java.util.*;

public class IsSameTree {
    
}


/*
 * 
 * Intuitions :
 * 
 * 1. we have given 2 binary trees
 * 2. we need to check if they are same or not
 * 3. Considered the same if they are structurally identical, and the nodes have the same value.
 * 
 * Pattern :
 * 
 * 1. will use recursion as I have to travel 2 trees at a time
 * 2. if I had used stack I have to wrote stack1 stack2 and had to comapre nodes from both of them
 * 3. stack will be so lengthy process but using recursion I can directly compare nodes of both trees
 * 
 * 
 * Pseudo Code :
 * 
 * function isSameTree (p , q) {
 * 
 *      if(p == null || q == null){
 *          return false;
 *      }
 * 
 *      // Process root
 *      if(p.val != q.val){
 *          return false;
 *      }
 * 
 *      if(p.val == q.val){
 *          isSameTree(node.left);
 *          isSameTree(node.right);
 *          return true;
 *      }
 *      
 * }
 * 
 * 
 */