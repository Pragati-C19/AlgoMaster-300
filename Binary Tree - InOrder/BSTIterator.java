import java.util.*;

import javax.swing.tree.TreeNode;

public class BSTIterator {
    
    private static class TreeNode {
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

    
    // To initialize an object of the BSTIterator Class
    public BSTIterator(TreeNode root) {
        
    }
    
    // Moves pointer to next and return the value of node
    public int next() {
        
    }
    
    // Checks if any next value exist or not
    public boolean hasNext() {
        
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
        
        // First Example
        Integer[] treeArray1 = {7, 3, 15, null, null, 9, 20};
        TreeNode root1 = buildTree(treeArray1);

        BSTIterator solution = new BSTIterator(root1);

        System.out.println("Final Result : ");
        System.out.println("    1st Iteration : " + solution.next());  // Output: 3
        System.out.println("    2nd Iteration : " + solution.next());  // Output: 7
        System.out.println("    3rd Iteration : " + solution.hasNext());  // Output: true
        System.out.println("    4th Iteration : " + solution.next());  // Output: 9
        System.out.println("    5th Iteration : " + solution.hasNext());  // Output: true
        System.out.println("    6th Iteration : " + solution.next());  // Output: 15
        System.out.println("    7th Iteration : " + solution.hasNext());  // Output: true
        System.out.println("    8th Iteration : " + solution.next());  // Output: 20
        System.out.println("    9th Iteration : " + solution.hasNext());  // Output: false

    }

}


/*
 * 
 * Intuitions :
 * 
 * 1. we need to implement BSTIterator class which represent an iterator over the Inorder Traversal
 * 2. BSTIterator(TreeNode root) Initializes an object of the BSTIterator class
 * 3. boolean hasNext() Returns true -> if there exist number in the traversal to the right of the pointer
 * 4. int next() moves pointer to right and Returns the value of that element
 * 5. Example : 
 * - Inorder Traversal → [3, 7, 9, 15, 20]
 * - Stack will help you go:
 * - Push 7 → push 3 → pop 3 (no right) → pop 7 → push 15 → push 9 → pop 9 → pop 15 → push 20 → pop 20 
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
 * 3. next() -> will pop the stack and return the value then move to its right subtree, and again push all lefts from there
 * 4. basic thing is only insert left not right just check if there is right then insert all left of that right
 * 5. Class:
 * - Use a Stack<TreeNode> stack
 * - In constructor → push all the left children starting from root (until null)
 * 
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