import java.util.*;

import javax.swing.tree.TreeNode;

public class DistributeCoins {

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


    // Globally Declare Variables


    // Driver Function 
    public int distributeCoins(TreeNode root) {
        
    }

    // Recursion Function : to get extra coins and moves
    private int postOrder(TreeNode node){

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
        
        DistributeCoins solution = new DistributeCoins();

        // First Example
        Integer[] treeArray1 = {3, 0, 0};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + solution.distributeCoins(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = {0, 3, 0};
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.distributeCoins(root2) + "\n");

        // Third Example
        Integer[] treeArray3 = {1};
        TreeNode root3 = buildTree(treeArray3);
        System.out.println("Result3: " + solution.distributeCoins(root3) + "\n");

        // Forth Example
        Integer[] treeArray4 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,100};
        TreeNode root4 = buildTree(treeArray4);
        System.out.println("Result4: " + solution.distributeCoins(root4) + "\n");

        // Fifth Example
        Integer[] treeArray5 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        TreeNode root5 = buildTree(treeArray5);
        System.out.println("Result5: " + solution.distributeCoins(root5) + "\n");

        // Sixth Example
        Integer[] treeArray6 = {2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,0};
        TreeNode root6 = buildTree(treeArray6);
        System.out.println("Result6: " + solution.distributeCoins(root6) + "\n");

    }
}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. Binarry tree is given with N nodes
 * 2. node.val represent number of coins at that node
 * 3. u can only move 1 coins in one move to ur adjacent node
 * 4. u can move coins from parent to child or child to parent
 * 5. Task is to find number of moves required to make every node.val is 1 by
 * moving coins
 * 
 * Things I understand :
 * 1. when (node.val == 1) stop the code and go for next
 * 2. when (node.val > 1) move 1 coin to child node
 * 3. when (node.val < 1) move 1 coin to parent node
 * 4. keep doing this until all nodes have 1 coin
 * 5. return the total number of moves
 * 
 * 
 * Pattern :
 * 
 * 1. Base Case : if(node == null) return 0;
 * 2. If node.val == 1 return 0;
 * 3. Recure to leftSubtree
 * 4. Recure to rightSubtree
 * 5. Visit Node :
 * - Count total coins and then check extra coins
 *      -> left + right + node.val = total coins
 *      -> total coins - 1 = extra coins
 * - moves =+ left + right
 *      -> also here addition will be absolute
 *      -> karan jr left kade 0 ahe node.val then tikdun extra coins -1 yetil ani
 *          moves madhe aplyala addition karaychiye substract nahi extra coins and total
 *          madhe -1 will work but moves madhe nahi so use absolute
 *      -> extra coins kaskay ale asel node kade? left and right ni move kele mhnun na
 *          ani jitke extra coins titke moves zale astil te denyat)
 * - return extra coins = give extra coins to parent node (even if it's
 * positive or negative)
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * // Globally declare move variable
 * minMoves;
 * 
 * function int distributeCoins(root){
 * 
 *      minMoves = 0;
 * 
 *      postOrder(root);
 * 
 *      return minMoves;
 *      
 * }
 * 
 * 
 * function postOrder(node){
 * 
 *      // Base Case : if node == null return 0 extra coins return
 *      if(node == null) return 0
 * 
 *      // Recur to left side
 *      leftSubtree = postOrder(root.left)
 * 
 *      // Recur to right side
 *      rightSubtree = postOrder(root.right)
 * 
 *      // Check total Coins 
 *      totalCoins = node.val + leftSubtree + rightSubtree
 * 
 *      // Check how many extra coin it has (it can be positive or negative)
 *      extraCoins = totalCoins - 1
 * 
 *      // Moves it takes to move those coins from node to node (for here will only check child to parent, -1 will do it's job for parent to child)
 *      minMoves =+ leftSubtree + rightSubtree
 * 
 *      return extraCoins
 * 
 * }
 * 
 */