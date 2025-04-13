import java.util.*;

public class KthSmallest {
    
}


/*
 * 
 * Intuitions :
 * 
 * 1. root is given and integer k is given
 * 2. here k is index 
 * 3. We need to find node at that index 
 * 
 * 
 * Pattern :
 * 
 * 1st basic approach to understand why I'm doing it in recursion 
 * 1. will sortArray by inorder
 * 2. return value at index k -> return arr.get(k)
 * 
 * 2nd approach
 * 1. Will declare index and smallestElement Globally resp 1 and null
 * 2. will call recursion function inorder whcih will help us to find kth smallest value
 * - Base case : if(node == null) return 
 * - call recursion to check left side node
 * - visit root : check if(index == k) -> if yes return node.val
 * - index++;
 * - call recursion to check right side node
 * 
 * 
 * Pseudo Code :
 * 
 * Integer smallestElement = null // let's try saying Null then will check int 
 * int index = 1;
 * 
 * function kthSmallest(root, k){
 *      inorder(root, k)
 *      return smallestElement;
 * }
 * 
 * function inorder(node, k){
 * 
 *      // Base Case :
 *      if(node == null) return 
 *      
 *      // check left side node
 *      inorder(node.left, k)
 * 
 *      // Visit Node 
 *      if(index == k){
 *          smallestElement = node.val
 *      }
 *      index++;
 * 
 *      // check right side node
 *      inorder(node.right, k)
 * }
 * 
 */