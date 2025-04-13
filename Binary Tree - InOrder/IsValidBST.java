import java.util.*;

public class IsValidBST {
    
}


/*
 * 
 * Intuitions :
 * 
 * 1. root of binary Tree is given
 * 2. need to check if it's valid bonary search or not
 * 3. conditions to check if BST is valid
 * - node.left.val < node.val
 * - node.val < node.right.val
 * - left and right subtrees are also valid BST
 * 4. to solve this will use inoder as it is good at sorting
 * 5. if u ask me why we think of sorting here.. actually those conditions are make me think of inorder sorting problem so
 * 
 * Pattern :
 * 
 * 1st Approach
 * 1. let's create an array to store inorder nums
 * 2. recursion call to sort the Array
 * 3. let's check if arr[i-1] < arr[i] < arr[i + 1] -> return true
 * 4. return false
 * 5. problem in this is.. it will return true for every triplates which I don't want
 * 
 * 2nd Approach :
 * 1. Let's take global variable or let's check if condition in inorder function
 * 
 * 
 * Pseudo Code :
 * 
 * 1st Approach :
 * 
 * function isValidBST(root){
 * 
 *      List<Integer> arr = new Array
 *      
 *      inorder(root)
 * 
 *      for( i from 1 to arr.length){
 *          if(arr[i-1] < arr[i] < arr[i + 1]){
 *              return true
 *          }
 *      }
 * 
 *      return false
 * }
 * 
 * 
 * 2nd Approach :
 * 
 * prevNode = null;
 * nextNode = null;
 * boolean isValid
 * 
 * function isValidBST(root){
 *      inorder(root)
 *      return isValid     
 * }
 * 
 * function inorder(node){
 *  
 *      if(node == null) return
 * 
 *      inorder(node.left)
 * 
 *      if(node.left.val < node.val && node.val < node.right.val ){
 *          isValid = true
 *      }
 *      isValid = false
 *      
 *      inorder(node.right)
 * 
 * }
 * 
 * 
 */