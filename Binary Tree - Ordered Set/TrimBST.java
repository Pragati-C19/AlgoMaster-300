import java.util.*;

public class TrimBST {

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. root of tree is given and it's lowest and highest boundries
 * 2. Trim the tree so that all elements lies in [low, high]
 * 3. i.e only take path in between low and high
 * 4. Tree's structure should not be changed
 * 5. Retrun root
 * 
 * 
 * Pattern :
 * 
 * 1. Base case: If the node is null, just return null.
 * 2. Left subtree trimming: 
 *      - if(root.value < low) : then all nodes in leftSubtree are smaller than low
 *      - so we skip the left child and only recurse on the right subtree.
 * 3. Right subtree trimming: 
 *      - if(root.value > high), then all nodes in rightSubtree are greater than high 
 *      - so we skip the right child and only recurse on the left subtree.
 * 4. Visit node: 
 *      - if root's value is within the bounds, trim both its left and right subtrees recursively. 
 *      - Then, return the current node after trimming.
 * 
 * 
 * Pseudo Code :
 * 
 * function trimBST(root, low, high){
 * 
 *      // Base Case :
 *      if root == null : return null
 * 
 *      // left subtree trimming 
 *      if root.val < low : trim the left subtree and recur on right side only 
 *          return trimBST(root.right, low, high)
 * 
 *      // rigth subtree trimming
 *      if root.val > high : trim the right subtree and recur on left side only 
 *          return trimBST(root.left, low, high)
 * 
 *      // Recur to left side and add elements in tree which are in range
 *      leftNode = trimBST(root.left, low, high)
 * 
 *      // Recur to right side and add elements in tree which are in range
 *      rightNode = trimBST(root.right, low, high)
 * 
 *      // return current node
 *      return root
 * 
 * }
 * 
 * 
 */