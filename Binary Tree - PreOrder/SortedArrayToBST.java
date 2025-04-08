import java.util.*;

import javax.swing.tree.TreeNode;

public class SortedArrayToBST {

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


    // Driver Function 
    public TreeNode sortedArrayToBST(int[] nums) {
        
        int start = 0;
        int end = nums.length - 1;
        
        // building Tree
        return buildTree(nums, start, end);
    }

    // Recursion Function : Used to build Tree
    private TreeNode buildTree(int[] nums, int left, int right){

        // Base Case : this is same as we do in binary search while(left < right) thing
        if (left > right) return null;

        // Getting mid element
        int mid = (left + right) / 2;

        // Making mid element as root
        TreeNode root = new TreeNode(nums[mid]);

        // Adding left side of root
        root.left = buildTree(nums, left, mid - 1); 

        // Adding right side of root
        root.right = buildTree(nums, mid + 1, right); 

        return root;
    }


    public static void main (String[] args){

        SortedArrayToBST solution = new SortedArrayToBST();

        int[] nums1 = {-10, -3, 0, 5, 9};
        System.out.println("Output1 : " + solution.sortedArrayToBST(nums1) + "\n");

        int[] nums2 = {1, 3};
        System.out.println("Output2 : " + solution.sortedArrayToBST(nums2));

    }
    
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
 * 7. we don't need to merge as we want a root so.. 
 * 8. lets try with one node then will think of other
 * - Pick the mid elemet
 * - make it root
 * - root.left = nums[mid - 1]
 * - root.right = nums[mid + 1]
 * 
 * 
 * Pseudo Code :
 * 
 * function SortedArrayToBST(int[] nums){
 *      
 *      left = 0
 *      right = nums.length - 1
 *      
 *      return buildTree(nums, left, right)
 * }
 * 
 * function buildTree(int[] nums, int left, int right) {        
 *          
 *      // I pick the mid element
 *      mid = (left + right) / 2
 *          
 *      // Make it root
 *      root = nums[mid]
 * 
 *      // Adding left side of root 
 *      root.left = buildTree(nums, left, mid - 1)
 *      
 *      adding right side of root
 *      root.right = buildTree(nums, mid + 1, right)
 *      
 *      return root;
 * }
 * 
 * 
 */
