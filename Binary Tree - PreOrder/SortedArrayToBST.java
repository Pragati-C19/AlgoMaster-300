import java.util.*;

public class SortedArrayToBST {

}

/*
 * 
 * Intuitions :
 * 
 * 1. integer array is given
 * 2. we need to convert it into a binary search tree
 * 3. Height balanced to be specific - A height-balanced binary tree is a binary
 * tree in which the depth of the two subtrees of every node never differs by
 * more than one.
 * 
 * 
 * Pattern :
 * 
 * 1. we need to find mid here
 * 2. will divide aray in two parts from that mid
 * 3. will make mid as root
 * 4. will recursively call for buildTree function which I'm using all the time
 * for both parts
 * 5. merge both sides with mid as at the start
 * 6. will not pas mid to any side.. at the end will just add it at the start as
 * root
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 * public static TreeNode buildTree(Integer[] nodes, int index) {
 * 
 *      // Base Case: if index is out of bounds or node is null
 *      if (index >= nodes.length || nodes[index] == null) return null;
 * 
 *      TreeNode root = new TreeNode(nodes[index]);
 * 
 *      // Recursively build left and right children
 *      root.left = buildTree(nodes, 2 * index + 1);
 *      root.right = buildTree(nodes, 2 * index + 2);
 * 
 *      return root;
 * }
 * 
 * 
 */
