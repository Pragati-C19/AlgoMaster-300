import java.util.*;

import javax.swing.tree.TreeNode;

public class MinCameraCover {
    
    // Definition for a binary tree node.
    public static class TreeNode {
        
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
    int cameraCount;
    int notMonitored = 0;
    int hasCamera = 1;
    int monitored = 2;

    // Driver Function
    public int minCameraCover(TreeNode root) {
        
        cameraCount = 0;

        // Call dfs and check if root is monitored or not
        if (dfs(root) == notMonitored) {
            
            cameraCount++;
            System.out.println(" Root node is not monitored, placing camera at root(" + root + ")");
            
        }

        // return camera count
        return cameraCount;
    }

    // Helper Function
    private int dfs(TreeNode currNode) {

        // Base Case : if currNode is null
        if (currNode == null) {
            
            System.out.println("    - currNode(" + currNode.val + ") is null..");
            return monitored;
        }

        System.out.println("    -> Children monitored, but Node " + currNode.val + " is not monitored.");
        return notMonitored;
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

    public static void main(String[] args){
      
        MinCameraCover solution = new MinCameraCover();

        // First Example
        Integer[] treeArray1 = {0,0,null,0,0};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1 -> " + solution.minCameraCover(root1) + "\n");   // 1

        // Second Example
        Integer[] treeArray2 = {0,0,null,0,null,0,null,null,0};
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2 -> " + solution.minCameraCover(root2) + "\n");   // 2
        
    }

}

/*
 * Intuitions :
 
    1. We have given root of binary tree
    2. We install cameras on the tree nodes 
        where each camera at a node can monitor its parent, itself, and it's immediate child
    3. return minimum number of cameras needed to monitor all nodes

 
 * Pattern :

    1. Jevha node visit karnar ahe tevha aplyala kahi gosti check karavya lagtil:
        My children need a camera?
        Are my children already covered?
        Do my children already have cameras?
    2. We need 2 dp's 
        1. cameraStates :
            - To check if we store the camera or not, or we need to installed camera or not
            - We can label each node with one of three states:
                - NotMonitored (0) — node needs a camera
                - HasCamera (1) — node has a camera
                - Monitored (2) — node is covered, but doesn't have a camera
        2. cameraCount :
            - To store number of cameras we installed for currNode

    3. will use bottom up approach 
        - means will check childs first (left-right-root)
        - if child not monotored yet will place camera at currNode
        - if any child has camera means currNode is already monitored
        - If all children are MONITORED but don’t have a camera → current node is NOT_MONITORED
            Suppose:
                A node has children.
                Those children are already covered (they don't need cameras).
                BUT those children themselves don't have a camera — they are only covered because their own children had cameras.
            Then this parent node:
                Is not being watched by any camera (because no child has a camera).
                Is not a camera itself.
                So it’s NOT_MONITORED.

    4. at the end return camera counts


 
 * Pseudo Code :
 
    -> Globally Declare variables :
        notMonitored
        hasCamera
        monitored
        cameraCount 

    function minCameraCover(root) {
    
        -> call dfs and check if root is monitored or not?
            if (dfs(root) = notMonitoried)
                will add camera here

        -> return count

    }

    function dfs(root) {
    
        -> Base Case :
            if currNode is null will consider it as monitored

        -> Check left and right child of currNode and get states from both

        -> if any one of the child or both child are not monitored 
            we need to add camera on root that is currNode
            so camera++
            and return hasCamera

        -> if any one of the child hasCamera or any both has camera
            we don't need to add camera at root that is currNode
            bcoz currNode will be motitored too
            will return monitored

        -> if children are monitored but don't have cameras
            we need to return currNode is not monitoried
    
    }
    

 */