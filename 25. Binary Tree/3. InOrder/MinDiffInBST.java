import java.util.*;

import javax.swing.tree.TreeNode;

public class MinDiffInBST {

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

    // Globally Declaring variables with by default values
    Integer prevNode = null;
    int minDiff = Integer.MAX_VALUE;

    // Driver Function
    public int minDiffInBST(TreeNode root) {
        
        findMinDiff(root);

        return minDiff;
    }

    // Recursion Function : To find Minimum Difference
    private void findMinDiff(TreeNode node){

        // Base Case:
        if (node == null) {
            return;
        }

        System.out.println("Visiting | Node : " + node.val + " PrevNode : " + prevNode);

        // Check Left Side
        findMinDiff(node.left);
        System.out.println("    leftDiff : " + minDiff);

        // Visit node : check minDiff 
        if (prevNode != null) {
            minDiff = Math.min(minDiff, Math.abs(node.val - prevNode));
        }

        prevNode = node.val;

        // check right side
        findMinDiff(node.right);
        System.out.println("    rightDiff : " + minDiff);

    }

    // Helper Function : to build a tree from an array (for testing)
    public static TreeNode buildTree(Integer[] nodes) {
        
        // Node is empty
        if (nodes.length == 0 || nodes[0] == null) return null;
        
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.add(root);
        
        int i = 1;  // Start from second element

        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode parent = queue.poll();
            
            // Assign left child
            if (nodes[i] != null) {
                parent.left = new TreeNode(nodes[i]);
                queue.add(parent.left);
            }
            i++;
            
            // Assign right child (check if there's still an element)
            if (i < nodes.length && nodes[i] != null) {
                parent.right = new TreeNode(nodes[i]);
                queue.add(parent.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        
        MinDiffInBST solution = new MinDiffInBST();

        // First Example
        Integer[] treeArray1 = {4, 2, 6, 1, 3};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + solution.minDiffInBST(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = { 1, 0, 48, null, null, 12, 49 };
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.minDiffInBST(root2) + "\n");

    }
    
}

/*
 * 
 * 
 * Let's Start Fresh
 * 
 * Intuitions : 
 * 1. Root of Binary Tree is given
 * 2. Find the minimum of any two different nodes from the tree 
 * 3. As u know now Inorder is a Sorted version of BST
 * 4. means if we check BST's inorder Traversal it can give sorted array
 * 5. and to get minimun difference we only need to check difference of adjucent nodes from array
 * 
 *
 * Pattern : 
 * 
 * 1. first approaches : We need to create sorted array with the help of inorder
 * - Create a array where u can put sorted values
 * - call inorder function to sort BST
 *  	- check if root is null -> return
 *      - check leftSide
 *      - add value of root in array
 *      - check right value
 * - run for loop from 1 to array.length 
 * - find minDiff = min(minDiff, (array[i] - array[i - 1])
 * - return minDiff
 * 
 * function minDiffInBST(root){
 * 
 *      List<Integer> sortedArray = new Array
 *      
 *      inorder(root, sortedArray)
 * 
 *      minDiff = Integer.MAX_Value
 *      for(i from 1 to sortedArray.length){
 *          minDiff = min(minDiff, (sortedArray[i] - sortedArray[i - 1]))
 *      }
 *      
 *      return minDiff
 * }
 * 
 * function inorder(node, sortedArray){
 *      if(node == null) return 
 *  
 *      // check left node
 *      inorder(node.left, sortedArray)
 * 
 *      // Visit root
 *      sortedArray.add(node.val)
 * 
 *      // check right node
 *      inorder(node.right, sortedArray)
 *      
 * }
 * 
 * 2. second approach : We need to create global variables and then use inorder traversal to find minDiff
 * - Create two variables Globally -> prevNode = null, minDiff = Integer.Max_Value
 * - call inorder recursion function
 *		- check if node is null -> return
 *		- check leftSide
 *		- see if prevNode != null -> minDiff =  min(minDiff, (node - prevNode)
 *		- else prevNode = node.val
 *		- check rightSide
 * - return minDiff
 * 
 * Integer prevNode = null
 * int minDiff = Integer.MAX_VALUE
 * 
 * function minDiffInBST(root){
 *      inorder(root)
 *      return minDiff
 * }
 * 
 * function inorder(node){
 *      if(node == null) return 
 *  
 *      // check left node
 *      inorder(node.left)
 * 
 *      // Visit root
 *      if(prev != null) minDiff = min(minDiff, (node - prevNode))
 *      
 *      prevNode = node.val
 *  
 *      // Check right node
 *      inorder(node.right)
 * }
 * 
 * 
 * 
 * 
 * ------------------------------------------------------------------------------
 * 
 * 
 * 
 * Old Intuitions :
 * 
 * 1. Root of Binary Tree is given
 * 2. Find the minimum of any two different nodes from the tree 
 * 3. this problem is almost same as MaxAncestorDiff
 * 

 * OLd Pattern :
 * 
 * 1. We'll use recursion here
 * 2. We'll find the minimum of the left subtree and the right subtree
 * 3. We will find minSoFar and MaxSoFar
 * 4. if root == null -> difference of minSoFar maxSofar
 * 5. then will call for recursion first left then right
 * 6. will find min(leftSubtree, rightSubtree)
 * 
 * 
 * Pseudo Code :
 * 
 * 1. Thought will use same logic as maxAcenture but it's wrong
 * 
 * function minDiffInBST(root){
 * 
 *      return findMinDiff(root, root.val, root.val, root.val)
 * }
 * 
 * function findMinDiff(root, minSoFar, maxSoFar) {
 * 
 *      if(root == null) return (maxSoFar - minSoFar)     
 * 
 *      minSoFar = min(minSoFar, root.val)
 *      maxSoFar = max(maxSoFar, root.val)
 * 
 *      leftSubtree = findMinDiff(root.left, minSoFar, maxSoFar)
 *      rightSubtree = findMinDiff(root.right, minSoFar, maxSoFar)
 * 
 *      currentMinDiff = min(leftSubtree, rightSubtree) 
 * 
 *      return currentMinDiff;
 * 
 * }
 * 
 * 
 * 
 * 2. using inorder
 * 
 * 
 * 
 */