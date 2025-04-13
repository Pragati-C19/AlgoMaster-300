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
 * 1. Let's take global variable or let's check if condition in inorder function
 * 2. ohk in my second approach there is problem over what if node.left and node.right is null?
 * 3. it will automatically give error there.. 
 * 
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
 * 3rd Approach :
 * 
 * 1. will go with global variable approach where will add variables as
 * - prevNode = null
 * - nextNode = null
 * - isValid = true
 * 2. will call recursion function inorderTraversal
 * - if(node == null) return 
 * - call recursion to check left side
 * - visit node : as in check if prevNode < node < nextNode also prevNode != null and nextNode != null
 * -> if yes then return isValid = true
 * -> if no return false
 * - prevNode = node.val
 * - nextNode = node.right.val 
 * - call recursion to check right side
 * 3. return isValid
 * 
 * 
 * prevNode = null;
 * nextNode = null;
 * boolean isValid = true;
 * 
 * function isValidBST(root){
 *      inorder(root)
 *      return isValid
 * }
 * 
 * function inorder(node){
 * 
 *      // Base Case
 *      if(node == null) return
 * 
 *      // check left node
 *      inorder(node.left)
 * 
 *      // Visit Node : check if condition is valid or not
 *      if(prevNode != null && nextNode != null) {
 *          if prevNode < node.val && node.val < nextNode){
 *              isValid = true
 *          }
 *          else {
 *              isValid = false
 *          }
 *      } 
 *      prevNode = node.val
 *      nextNode = node.right.val
 * 
 *      inorder(node.right)
 * 
 * }
 * 
 * Pseudo Code :
 * 
 * 1st Approach :
 * 
 * 
 * 
 * 2nd Approach :
 * 
 * prevNode = null;
 * nextNode = null;
 * 
 * 
 * 
 */