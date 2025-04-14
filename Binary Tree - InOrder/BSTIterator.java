import java.util.*;

public class BSTIterator {
    
}


/*
 * 
 * Intuitions :
 * 
 * 1. we need to implement BSTIterator class which represent an iterator over the Inorder Traversal
 * 2. BSTIterator(TreeNode root) Initializes an object of the BSTIterator class
 * 3. boolean hasNext() Returns true -> if there exist number in the traversal to the right of the pointer
 * 4. int next() moves pointer to right and Returns the value of that element
 * 
 * 
 * Pattern :
 * 
 * 1st Approach  :
 * 1. in next() -> will use inorder here and and visit left-> root -> right
 * 2. in hasNext() -> we are checking if node.right == null or not
 * -> if it's null return false
 * -> if not return true
 * 3. I just don't know how will an object class
 * 
 * BSTIterator(TreeNode root) {
 *      if(root == null) return null
 *      BSTIterator(root.left)
 *      root.next()
 *      if(root.hasNext()){
 *          BSTIterator(root.right)
 *      }
 * }
 * 
 * function int next() {
 *      if(root.hasNext() == true) {
 *          root = root.right
 *      } 
 *      return root.val
 * }
 * 
 * function boleean hasNext(){
 *      if(root == null) return false
 *      else return true;
 * }
 * 
 * 
 * 
 * 2nd Approach :
 * 
 * 1. will use stack here not recursion 
 * 2. hasNext() -> it will check if stack is empty?.. bco if stack is not empty mean there is a next value or value at right side
 * 3. next() -> will pop the stack and return the value
 * 4. basic thing is only insert left not right just check if there is right then insert all left of that right
 * 
 * BSTIterator Class(root){
 *      stack = new Stack
 *      
 *      while(root != null){
 *          // add root in stack
 *          stack.append(root)
 *          
 *          // shift root to left.. we have to go to extremely left
 *          root = root.left
 *      }
 * }
 * 
 * function int next(){
 *      // we already know that it hasNext() bcoz que says it will soo let's work on pop and store
 *      node = stack.pop()
 *      
 *      while(node != null){
 *          // add node in stack
 *          stack.append(node)
 *          
 *          // shift root to left.. we have to go to extremely left
 *          node = node.left
 *      }
 * 
 *      return node.val
 * }
 * 
 * function boolean hasNext(){
 *      if(!stack.isEmpty) return true
 *      else return false
 * }
 * 
 */