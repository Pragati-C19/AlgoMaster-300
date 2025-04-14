import java.util.*;

public class InvertTree {
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. root is given need to return inverted tree
 * 
 * Pattern :
 * 
 * 1. saw the que and it seems they are interchanging right and left node of the root
 * 2. let's try doing it
 * 
 * Pseudo Code :
 * 
 * function invertedTree(root){
 * 
 *      // base case :
 *      if(root == null) return null
 * 
 *      // check left node
 *      invertedTree(root.left)
 *      
 *      // check right node
 *      invertedTree(root.right)
 * 
 *      // visit node
 *      if(node.right != null || node.left != null){
 *          temp = node.left
 *          node.left = node.right
 *          node.right = temp
 * 
 *          or use swap function
 *      }
 * 
 *      return root
 * 
 * }
 * 
 * 
 */