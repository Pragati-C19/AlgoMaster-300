import java.util.*;

public class DelNodes {
    
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

    // Globally Declare variable

    // Driver Function 
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        
    }

    // Recursion Function : postOrder
    private TreeNode postOrder(root){

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

    // Helper Function : to serialize trees to list format
    public static List<List<String>> serializeForest(List<TreeNode> forest) {
        List<List<String>> result = new ArrayList<>();
        for (TreeNode node : forest) {
            result.add(printTreeAsArrayFormat(node));
        }
        return result;
    }
    

    public static void main(String[] args) {
        
        DelNodes solution = new DelNodes();

        // First Example
        Integer[] treeArray1 = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root1 = buildTree(treeArray1);
        int[] to_delete1 = {3, 5};
        System.out.println("Result1: " + serializeForest(solution.delNodes(root1, to_delete1)) + "\n");

        // Second Example
        Integer[] treeArray1 = {1, 2, 4, null, 3};
        TreeNode root1 = buildTree(treeArray1);
        int[] to_delete2 = {3};
        System.out.println("Result1: " + serializeForest(solution.delNodes(root1, to_delete2)) + "\n");

    }
}

/*
 * 
 * Intuitions :
 * 
 * 1. root of binary tree and to_delete array is given
 * 2. we need to delete a node from binary tree
 * 3. In return we need a list of new TreeNodes
 * 4. I need to use hashmap here which stores 
 * 
 * Pattern :
 * 
 * 1. Globally Declare deleteMap
 * 2. Main Function 
 * - Traverse the tree and store the nodes in deleteMap
 * - recursion call
 * - return result;
 * 
 * 3. recursion function 
 * - if(root == null) null;
 * - check left side
 * - check right side
 * - if(deleteMap.contains(root.val)) 
 *      -> if(leftSubtree != null) result.add(new TreeNode(leftSubtree))
 *      -> if(rightSubtree != null) result.add(new TreeNode(rightSubtree))
 *      -> return null; // bcoz that specific node is deleted
 * - return root
 * 
 * Pseudo Code :
 * 
 * 
 * // Globally Declare Variable
 * Map<Integer, Boolean> deleteMap = new HashMap<>()
 * result = new ArrayList
 * 
 * // Main Function
 * function delNodes(root, to_delete){
 *      
 *      for(i from 0 to to_delete.length){
 *          deleteMap.put(to_delete[i], false)
 *      }
 * 
 *      postOrder(root)
 *      
 *      return result;
 *      
 * }
 * 
 * function postOrder(root){
 * 
 *      // Base Case :
 *      if(root == null) return root;
 * 
 *      // Recur to left Side
 *      leftSubtree = postOrder(root.left)
 *      
 *      // Recur to right Side
 *      rightSubTree = postOrder(root.right)
 * 
 *      // Visit node
 *      if(deleteMap.get(root.val)){
 *          if(leftSubTree != null) result.add(new TreeNode(leftSubTree))
 *          if(leftSubTree != null) result.add(new TreeNode(rightSubTree))
 *          return null
 *      }
 * 
 *      return root;
 * }
 * 
 */