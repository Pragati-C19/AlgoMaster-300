import java.util.*;

public class ConstructMaximumBinaryTree {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val){
            this.val = val;
        }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        
        return nums;
    }

    // Function to print the tree
    public void printTree(TreeNode root){
        if(root == null) return;
        System.out.println(root.val + " ");

        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String[] args){
        ConstructMaximumBinaryTree solution = new ConstructMaximumBinaryTree();

        int[] nums = {3,2,1,6,0,5};

        System.out.println("Input Array: " + Arrays.toString(nums));
        TreeNode root = solution.constructMaximumBinaryTree(nums);

        System.out.println("Output Tree:");
        solution.printTree(root);
    }
    }

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. build a binary tree where each node is the maximum number from a specific
 * part of the array.
 * 2. Every time we pick the largest number, it becomes the root of that part of
 * the tree.
 * 3. Numbers to the left of the maximum go into the left subtree.
 * 4. Numbers to the right of the maximum go into the right subtree.
 * 
 * Pattern :
 * 
 * 1. Find the maximum number in the current part of the array.
 * 2. Create a node with that maximum number.
 * 3. Split the array into left (numbers before the max) and right (numbers
 * after
 * the max).
 * Recursively repeat steps 1-3 for the left and right parts to build subtrees.
 * Stop when the array is empty
 * 
 * Pseudo Code :
 * 
 * function constructMaximumBinaryTree(nums):
 * if nums is empty:
 * return null
 * 
 * maxIndex = find index of the maximum number in nums
 * root = new TreeNode(nums[maxIndex])
 * 
 * root.left = constructMaximumBinaryTree(nums from start to maxIndex-1)
 * root.right = constructMaximumBinaryTree(nums from maxIndex+1 to end)
 * 
 * return root
 * 
 * 
 * 
 */
