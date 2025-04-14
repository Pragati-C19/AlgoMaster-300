import java.util.*;

public class DiameterOfBinaryTree {
    
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
    public int diameterOfBinaryTree(TreeNode root) {
        
        List<Integer> currPath = new ArrayList<>();
        int maxDiameter = 0;
        
        System.out.println("Starting post-order traversal to find diameter...");

        maxDiameter = postOrder(root, currPath, maxDiameter);

        System.out.println("Final max diameter: " + maxDiameter);
        return maxDiameter;
    }

    // Recursion Function : to check all paths
    private int postOrder(TreeNode root, List<Integer> currPath, int maxDiameter){

        if (root == null) {
            System.out.println("Visited null node. Skipping...");
            return maxDiameter;
        }

        System.out.println("\nVisiting node: " + root.val);

        int leftSideLongestPath = backtrack(root.left, currPath);
        System.out.println("  Left longest path for node " + root.val + " = " + leftSideLongestPath);

        int rightSideLongestPath = backtrack(root.right, currPath);
        System.out.println("  Right longest path for node " + root.val + " = " + rightSideLongestPath);

        int currDiameter = leftSideLongestPath + rightSideLongestPath;
        System.out.println("  Current diameter at node " + root.val + " = " + currDiameter);

        maxDiameter = Math.max(maxDiameter, currDiameter);
        System.out.println("  Updated max diameter = " + maxDiameter);

        return maxDiameter;

    }

    // Backtrack Function : to get longest Path
    private int backtrack(TreeNode root, List<Integer> currPath){

        // Base Case:
        if(root == null){
            System.out.println("    Reached leaf. Current path: " + currPath + ", size: " + currPath.size());
            return currPath.size();
        }

        currPath.add(root.val);
        System.out.println("    [backtrack] Visiting node : " + root.val + ", current path: " + currPath);

        backtrack(root.left, currPath);

        backtrack(root.right, currPath);

        return currPath.size();

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
        
        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();

        // First Example
        // Integer[] treeArray1 = {1, 2, 3, 4, 5};
        // TreeNode root1 = buildTree(treeArray1);
        // System.out.println("Result1: " + solution.diameterOfBinaryTree(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = { 1, 2 };
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.diameterOfBinaryTree(root2) + "\n");

        // Third Example
        // Integer[] treeArray3 = { 4, -7, -3, null, null, -9, -3, 9, -7, -4, null, 6, null, -6, -6, null, null, 0, 6, 5, null, 9, null, null, -1, -4, null, null, null, -2};
        // TreeNode root3 = buildTree(treeArray3);
        // System.out.println("Result3: " + solution.diameterOfBinaryTree(root3) + "\n");
        
    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. root is given, return length of diameter of the tree
 * 2. Diameter of the binary Tree is length of the lonest path between any two nodes in tree
 * 3. path may or may not pass through the root
 * 4. length of a path between two nodes is represented by number of edges between them
 * 5. I worked on BinaryTreePaths problem and it gives current paths.. 
 * 6. will check first which path's it can show us
 * 7. then will modify that function
 * 
 * Pattern :
 * 
 * 1. I think I need to use backtrack to to check path
 * 2. currentPathCount = longestPathAtLeft + longestPathAtRight 
 * 3. diameter = Math.max(currentPath, diameter)
 * 5. backtrack function : to get longestPath 
 * - create a curr array
 * - start storing root.val one by one in that array till u get leaf node
 * - return array.length to longestPathAtLeft ot LongestPathAt Right
 * 
 * Pseudo Code :
 * 
 * function diameterOfBinaryTree(root){
 * 
 *      List<Integer> currPath = new Array
 *      postOrder(root, 0, currPath)
 *      
 *      return diameter
 * }
 * 
 * // Recursion function 
 * function postOrder(root, diameter, currPath){
 *      
 *      if(root == null) return diameter
 * 
 *      // Check left side
 *      postOrder(root.left, diameter)
 * 
 *      // Check right side
 *      postOrder(root.right, diameter)
 *      
 *      // Visit root
 *      leftSideLongestPath = backtrack(root.left, currPath)
 * 
 *      rightSideLongestPath = backtrack(root.right, currPath)
 * 
 *      currLongestPath = leftSideLongestPath + rightSideLongestPath
 * 
 *      diameter = Math.max(diameter, currLongestPath)
 *           
 * }
 * 
 * // Backtrack Function : to get longest Path
 * function backtrack(root, currPath) {
 * 
 *      // Base Case :
 *      if(root == null){
 *          return currPath.length
 *      }
 * 
 *      currPath.add(root.val)
 * 
 *      if(root.left != null){
 *          backtrack(root.left, currPath)
 *      }
 * 
 *      if(root.right != null){
 *          backtrack(root.right, currPath)
 *      }     
 *      
 * }
 * 
 */