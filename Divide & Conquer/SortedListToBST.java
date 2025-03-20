import java.util.*;

public class SortedListToBST {
    
}

/*
 * 
 * Intuitions : 
 * 
 * 1. Need to convert a sorted linked list to a height-balanced BST
 * 2. Height Balanced Tree - for every node. the depth difference between left and right subtree is at most 1.
 * 3. Middle value should be the root, left half becomes the left subtree and right half will be right subtree
 * 4. Recursively will apply same logic for left right subtrees
 * 
 * 
 * Pattern :
 * 
 * 1. Find the middle node of the list - Use the slow-fast pointer technique (slow moves 1 step, fast moves 2 steps) to find the middle efficiently.
 * 2. Make the middle node as the root
 * 3. left subtree and right subtree
 * 4. Recursively build the tree from other halves
 * 5. if list is empty, return null.
 * 
 * 
 * Pseudo Code :
 * 
 * function sortedListToBST(head):
 *      # Base Case
 *      if head is null :
 *          return null
 *      
 *      # If only one node, make it a leaf node
 *      if head.next is null :
 *          return TreeNode(head.val)
 * 
 *      # Find the middle node using slow and fast pointers
 *      prev = null
 *      slow = head
 *      fast = head
 * 
 *      while fast and fast.next :
 *          prev = slow
 *          slow = slow.next 
 *          fast = fast.next.next
 *  
 *      # Disconnect left half from middle
 *      prev.next = null
 * 
 *      # Middle node becomes the root
 *      root = TreeNode(slow.val)
 *  
 *      # Recursively build left and right subtrees
 *      root.left = sortedListToBST(head)   # left half
 *      root.right = sortedListToBST(slow.next)     # right half
 * 
 */
