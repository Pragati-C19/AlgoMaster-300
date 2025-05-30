import java.util.*;

import javax.swing.tree.TreeNode;

public class WidthOfBinaryTree {

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

    private static class NodeIndexPair {
        TreeNode node;
        int index;
    
        NodeIndexPair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    // Driver Function 
    public int widthOfBinaryTree(TreeNode root) {
        
        // Base Case 
        if (root == null) {
            return 0;
        }
    
        int maxWidth = 0;

        // Will store not only root but it's index too in queue
        Queue<NodeIndexPair> queue = new LinkedList<>();
        queue.add(new NodeIndexPair(root, 0));  // root has index 0

        while (!queue.isEmpty()) {
            
            int levelSize = queue.size();
            System.out.println(" LevelSize : " + levelSize);

            int minIndex = queue.peek().index; // first index at this level
            System.out.println(" Minimum Index : " + minIndex);

            int firstIndexOfLevel = 0;
            int lastIndexOfLevel = 0;
            
            for (int i = 0; i < levelSize; i++) {
                
                NodeIndexPair pair = queue.poll();

                TreeNode node = pair.node;      // Here TreeNode is Key so 
                int index = pair.index - minIndex;     // normalize to prevent overflow

                if(i == 0) firstIndexOfLevel = index;
                if(i == (levelSize - 1)) lastIndexOfLevel = index;


                if (node.left != null) {
                    queue.add(new NodeIndexPair(node.left, 2 * index));
                }

                if (node.right != null) {
                    queue.add(new NodeIndexPair(node.right, 2 * index + 1));
                }
            }

            int width = lastIndexOfLevel - firstIndexOfLevel + 1;
            maxWidth = Math.max(maxWidth, width);
        }

        return maxWidth;
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
        
        WidthOfBinaryTree solution = new WidthOfBinaryTree();

        // First Example
        Integer[] treeArray1 = {1, 3, 2, 5, 3, null, 9};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + solution.widthOfBinaryTree(root1) + "\n");

        // Second Example
        Integer[] treeArray2 = {1, 3, 2, 5, null, null, 9, 6, null, 7};
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + solution.widthOfBinaryTree(root2) + "\n");

        // Third Example
        Integer[] treeArray3 = {1, 3, 2, 5};
        TreeNode root3 = buildTree(treeArray3);
        System.out.println("Result3: " + solution.widthOfBinaryTree(root3) + "\n");
        
    }
}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. We have giving a Binary Tree
 * 2. We have to find the maximum width of the tree
 * 3. The maximum width of a tree is the maximum width among all levels.
 * 4. We can use BFS to solve this problem
 *
 * 
 * Pattern :
 * 
 * 1. We'll use BFS
 * 2. We'll use a queue to store the nodes at each level
 * 3. will add root to the queue
 * 4. Also will declare a variable maxWidth = 0
 * 5. start while loop till queue.isEmpty()
 * 6. will check levelSize
 * 
 * 
 * Here I'm thinking of 2-3 approaches
 * 1. will do level++ count
 * 2. at the end of loop use max = Math.max(max, levelSize + 1)
 * 3. Even if the specific node is null still we want to count it so -
 * - we can say count levels of the three, and at end out side of while loop
 * will use 2 ^ levelCount eg. if levels are 2 then maxWidth will be 2 ^ 2 = 4
 * also one thing to rememeber levelCount will start at 0 
 * 4. we used to check node.left or node.right != null will not do it at all..
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 1. function connect(Node root){
 * 
 *      Base Case 
 *      if(root == null) return 0
 * 
 *      int maxWidth = 0;
 *      
 *      queue = new Linkedlist
 *      queue.add(root)
 * 
 *      while(!queue.isEmpty){
 *          levelSize = queue.size()
 *          
 *          maxWidth = Math.Max(maxWidth, levelSize)
 *          
 *          for (i till levelSize){
 *              TreeNode node = queue.pop();
 *              
 *              queue.add(node.left)
 *              queue.add(node.right)
 *             
 *          }
 *          
 *      }
 *  
 *      return maxWidth;
 * }
 * 
 * 
 * 2. function connect(Node root){
 * 
 *      Base Case 
 *      if(root == null) return 0
 *      
 *      int maxWidth = 0;
 *      int level = 0;
 *      
 *      queue = new Linkedlist
 *      queue.add(root)
 * 
 *      while(!queue.isEmpty){
 *          levelSize = queue.size()
 *          
 *          maxWidth = Math.Max(maxWidth, level)
 * 
 *          for (i till levelSize){
 *              TreeNode node = queue.pop();
 *              
 *              queue.add(node.left)
 *              queue.add(node.right)
 *             
 *          }
 *          
 *          level++;
 *      }
 *  
 *      return maxWidth;
 * }
 * 
 * 
 * 3. function connect(Node root){
 * 
 *      Base Case 
 *      if(root == null) return 0
 *      
 *      int maxWidth = 0;
 *      int level = 0;
 *      
 *      queue = new Linkedlist
 *      queue.add(root)
 * 
 *      while(!queue.isEmpty){
 *          levelSize = queue.size()
 *          
 *          maxWidth = Math.Max(maxWidth, 2 ^ level)
 * 
 *          for (i till levelSize){
 *              TreeNode node = queue.pop();
 *              
 *              queue.add(node.left)
 *              queue.add(node.right)
 *             
 *          }
 *          
 *          level++;
 *      }
 *  
 *      return maxWidth;
 * }
 * 
 * 
 * 4. Will Assign index to all nodes 
 * 
 * function connect(Node root){
 * 
 *      Base Case 
 *      if(root == null) return 0
 *      
 *      int maxWidth = 0;
 *      
 *      // Will store not only root but it's index too in queue
 *      queue = new Queue<Pair<TreeNode, index>>
 *      queue.add(new Pair(root, 0))    
 * 
 *      while(!queue.isEmpty){
 *          levelSize = queue.size()
 *          
 *          minIndex = queue.peek().index
 *          
 *          int firstIndexOfLevel = 0;
 *          int lastIndexOfLevel = 0;
 *          
 * 
 *          for (i till levelSize){
 *              Pair<TreeNode,Integer> node = queue.pop();
 * 
 *              int index = node.index - minIndex;  // normalize to prevent overflow
 * 
 *              if(i == 0) firstIndexOfLevel = index
 *              if(i == levelSize - 1) lastIndexOfLevel = index;
 *              
 *              if(node.left != null) queue.add(Pair (node.left, 2 * index))
 *              if(node.right != null) queue.add(Pair (node.right, 2 * index + 1)
 *             
 *          }
 *          
 *          width = lastIndexOfLevel - firstIndexOfLevel
 *          maxWidth = Math.max(maxWidth, width)
 *      }
 *  
 *      return maxWidth;
 * }
 * 
 * 
 */
