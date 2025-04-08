import java.util.*;

public class IsSymmetric {

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
    public boolean isSymmetric(TreeNode root) {
        
        return true;
    }

    // Helper Function : to build a tree from an array (for testing)
    public static TreeNode buildTree(Integer[] nodes, int index) {
        
        // Base Case: if index is out of bounds or node is null
        if (index >= nodes.length || nodes[index] == null) return null;

        TreeNode root = new TreeNode(nodes[index]);

        // Recursively build left and right children
        root.left = buildTree(nodes, 2 * index + 1);
        root.right = buildTree(nodes, 2 * index + 2);

        return root;
    }


    public static void main(String[] args) {
        
        IsSymmetric solution = new IsSymmetric();

       // First Example
       Integer[] treeArray1 = {1, 2, 2, 3, 4, 4, 3};
       TreeNode root1 = buildTree(treeArray1, 0);
       System.out.println("Result1: " + solution.isSymmetric(root1) + "\n");

       // Second Example
       Integer[] treeArray2 = { 1, 2, 2, null, 3, null, 3 };
       TreeNode root2 = buildTree(treeArray2, 0);
       System.out.println("Result2: " + solution.isSymmetric(root2) + "\n");

    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. The tree is symmetric if the left and right subtrees are mirror images of
 * each other.
 * 2. need to check if it's symmetric around it's center which is root node
 * 3. will use recursion to check if tree is recursive or not
 * 
 * Pattern :
 * 
 * 1. I need to check if root's left side == root's right side
 * - if yes then return true
 * - if no then return false
 * 2. I'm not thinking of mirror image yet let's focus on basic things
 * - Base Case : root.left and root.right == null -> return true
 * - Base Case : root.left == null || root.right == null -> return false
 * - if root.left == root.right -> then call isSameTree(root.left, root.right)
 * - return isSameTree(root.left, root.right)
 * 3. will right same code we wrote for chaking tree is same or not.
 * - if u see in depth u'll understand that root.left is a new tree and
 * root.right is a new tree too we need to comapre them if both root.left and
 * root.right are equal
 * 
 * 4. IF I think of mirror image I'll just try one thing first that 
 * - changing recursion values in isSame Tree 
 * - In normal we were chaking p.left == q.left 
 * - But as I see in mirror it's like p.left == q.right so let's check this
 * return isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
 * 
 * 
 * Pseudo Code:
 * 
 * function isSymmetric(root) {
 *      
 *      // base Case
 *      if(root.left == null && root.right == null) return true
 *      
 *      else if(root.left == null || root.right == null) return false
 * 
 *      else if(root.left != root.right) return false
 * 
 *      return isSameTree(root.left, root.right)
 * 
 * }
 * 
 * function isSameTree(p, q){ 
 *      if (p == null && q == null) {
 *          System.out.println("Both vals are null");
 *          return true;
 *      }
 *      else if (p == null || q == null) {
 *          System.out.println("One of us is null");
 *          return false;
 *      }
 *      else if (p.val != q.val) {
 *          System.out.println("Both vals are different ->  p : " + p.val + " q : " + q.val);
 *          return false;
 *      }
 * 
 *      // Return combined result of both recursive calls
 *      return isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
 * 
 * }
 * 
 * 
 */