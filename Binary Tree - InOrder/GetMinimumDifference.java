import java.util.*;

public class GetMinimumDifference {
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. we have given a root
 * 2. we need to return minimum abs difference between values of any two nodes
 * 3. this que is same as MinDiffInBST
 * 4. will use inorder to get sorted list
 * 5. and will use global declaration of prevNode and minDiff pattern 
 * 
 * pattern : 
 * 
 * 1. will declare prevNode and minDiff globally
 * 2. call recursion function
 *      - if(root == null) return
 *      - call recursion function to check left node
 *      - visit the node : check if prevNode != null then find minDiff
 *      - call recursion function to check right node
 * 3. return minDiff
 * 
 * 
 * Pseudo Code :
 * 
 * prevNode = null
 * int minDiff = Max.Value
 * 
 * function getMinimumDifference(root){
 *      inorder(root)
 *      return minDiff
 * }
 * 
 * function inorder(node){
 * 
 *      if(node == null) return
 * 
 *      inorder(node.left)
 * 
 *      if(prevNode != null) {
 *          minDiff = min(minDiff, Math.abs(node.val - prevNode))
 *      }
 *      prevNode = node.val
 *  
 *      inorder(node.right)
 * }
 * 
 */
