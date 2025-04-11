import java.util.*;

import javax.swing.tree.TreeNode;

public class BuildTreePostIn {

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


    // Globally assign Postorder Index to track the index of postorder in all recursion
    int postIndex;

    // Driver Function 
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        
        // Assinging starting value to postorder index (We are traveleling from back)
        postIndex = postorder.length - 1;

        // Creating map for inorder to store value and index
        Map<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // call Recursion function to get root
        return build(inorder, postorder, 0, inorder.length - 1, inorderMap);
    }
    
    // Recursion Function : to build the binary tree
    private TreeNode build(int[] inorder, int[] postorder, int inorderStart, int inorderEnd, Map<Integer, Integer> inorderMap){

        // Base Case 
        if (inorderStart > inorderEnd) {
            return null;
        }

        // Get root value from postorder and decreament postorder index
        int rootVal = postorder[postIndex];
        postIndex--;

        // check that root value in inorder and get index of it
        int rootIndex = inorderMap.get(rootVal);

        // create a root against that root value
        TreeNode root = new TreeNode(rootVal);

        // Find root's left and right Values
        root.right = build(inorder, postorder, rootIndex + 1, inorderEnd, inorderMap);
        root.left = build(inorder, postorder, inorderStart, rootIndex - 1, inorderMap);

        return root;

    }

    // Helper Function : To Print Binary Tree 
    public static List<String> printTreeAsArrayFormat(TreeNode root) {
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
    
        queue.add(root);
    
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
    
            if (node == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(node.val));
                queue.add(node.left);
                queue.add(node.right);
            }
        }
    
        // Trim trailing "null"s (Leetcode does this)
        int i = result.size() - 1;
        while (i >= 0 && result.get(i).equals("null")) {
            result.remove(i--);
        }
    
        return result;
    }    

    public static void main (String[] args){

        BuildTreePostIn solution = new BuildTreePostIn();

        int[] postorder1 = {9, 15, 7, 20, 3};
        int[] inorder1 = {9, 3, 15, 20, 7};
        System.out.println("Output1 : " + printTreeAsArrayFormat(solution.buildTree(inorder1, postorder1)) + "\n");

        int[] postorder2 = { -1 };
        int[] inorder2 = { -1 };
        System.out.println("Output2 : " + printTreeAsArrayFormat(solution.buildTree(inorder2, postorder2)));

    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. Postorder and Inorder traversal is given we need to build tree from it
 * 2. We need to find the root node first from postorder
 * 3. we have to check that rootIndex from inorder
 * 4. We need to find the left and right child of the root node
 * 5. then use recursion
 * 6. return the root
 * 
 * Pattern :
 * 
 * 1. Globally assign postIndex -> which will travel globally for all recursion
 * and will not go in stack overflow
 * 2. Create a map to store Inorder value and index
 * 3. call recursion function which has
 * - if inorder's start > inorder's end then return null
 * - find the root node from postorder and store it in rootVal
 * - decrease postIndex as we travel from back now.. bcoz in post order the flow
 * is left -> right -> root
 * - find the root node in inorder and store it's index in rootIndex
 * - get value of right and left of root by recursion calls (Remember : we are
 * travelening from back in postorder so after root will hit to right so we need
 * to check right first then left)
 * - return the root node
 * 
 * 
 * Pseudo Code :
 * 
 * // Globally Assigning postIndex 
 * int postIndex;
 * 
 * function buildTree(postorder, inorder){
 *      
 *      // assigning value to postIndex
 *      postIndex = postorder.length - 1
 *      
 *      // Creating map for inorder
 *      inorderMap = new Hashmap
 *  
 *      // Storing value and index of inorder in map
 *      for(i from 0 to inorder.length){
 *          map.put(inorder[i], i)
 *      }
 *  
 *      // calling recursion function 
 *      return build(postorder, inorder, 0, (inorder.length - 1), inorderMap)
 *      
 * }
 * 
 * 
 * function build(postorder, inorder, inorderStart, inorderEnd, inorderMap){
 *  
 *      if(start > end) return null
 * 
 *      // Get rootVal from postorder 
 *      rootVal = postorder[postIndex]
 *      postIndex--
 * 
 *      // Check index of that root in inorder then will divide inorder accordingly left and right 
 *      rootIndex = inorderMap.get(rootVal)
 * 
 *      // create a root from that rootVal
 *      root = new TreeNode(rootVal)
 * 
 *      // Get right and left of the root (wrote right first then left let's see do I need to change it or not at time of coding)
 *      root.right = build(postorder, inorder, rootIndex + 1, inorderEnd, inorderMap)
 *      root.left = build(postorder, inorder, inorderStart, rootIndex - 1, inorderMap)
 *  
 *      return root
 * 
 * }
 * 
 * 
 * 
 */