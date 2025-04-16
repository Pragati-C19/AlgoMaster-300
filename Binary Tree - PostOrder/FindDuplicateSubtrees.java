import java.util.*;

import javax.swing.tree.TreeNode;

public class FindDuplicateSubtrees {
 
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


    // Globally Declaring Variables


    // Driver Function 
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        
    }

    // Recursion Function : Check if u found duplicates or not (bottom-up pattern)
    private String postOrder(TreeNode root){

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

    // Helper Function : to serialize trees to list format
    public static List<List<String>> serializeForest(List<TreeNode> forest) {
        List<List<String>> result = new ArrayList<>();
        for (TreeNode node : forest) {
            result.add(printTreeAsArrayFormat(node));
        }
        return result;
    }

    public static void main(String[] args) {
        
        FindDuplicateSubtrees solution = new FindDuplicateSubtrees();

        // First Example
        Integer[] treeArray1 = {2, 1, 1};
        TreeNode root1 = buildTree(treeArray1);
        System.out.println("Result1: " + serializeForest(solution.findDuplicateSubtrees(root1)) + "\n");

        // Second Example
        Integer[] treeArray2 = {2, 2, 2, 3, null, 3, null};
        TreeNode root2 = buildTree(treeArray2);
        System.out.println("Result2: " + serializeForest(solution.findDuplicateSubtrees(root2)) + "\n");

        // Third Example
        Integer[] treeArray3 = {1, 2, 3, 4, null, 2, 4, null, null, 4};
        TreeNode root3 = buildTree(treeArray3);
        System.out.println("Result3: " + serializeForest(solution.findDuplicateSubtrees(root3)) + "\n");

    }
}

/*
 * 
 *  * Few Important things to understand here
 * 
 * 1. will use String + Subtree Serialization + DFS (Bottom-up | postorder) + Hashing format
 * 
 * 2. Why we use string instead of array to store currSubtree?
 * - You can’t use an array as a Map or Set key directly unless you override equality and hashcode.
 * - String can easily represent tree structure as a serialized format.
 * - Example: a subtree with root 1, left child 2, and right child 3 → "1,2,#,#,3,#,#"
 * - That string captures both structure and value in a unique, serializable way.
 * - Arrays can't capture structure unless you also include nulls and positions carefully.
 * 
 * 3. What is subtree Serialization?
 * - A subtree serialization is a string that represents the structure and values of a tree.
 * - It is a way to represent a tree as a string, which can be used to compare trees
 * - If two subtrees are structurally and value-wise identical, then their serialized form should be exactly the same.
 * 
 * 4. With a HashMap<String, Integer>, you can track the frequency of each subtree.
 * - This lets you add it to the result list only the first time you see a duplicate
 * - Avoid adding duplicates more than once.
 * 
 * Intuitions :
 * 
 * 1. Root is give we need to fing duplicate subtree
 * 2. Condition :
 * - Tree are duplicate if they have same stucture with same node values
 * 
 * Pattern :
 * 
 * 1. We need to use map to check if we found duplicate or not
 * 2. result to store all subtrees
 * 3. Recursion call
 * - Base Case : if(root == null) -> add # to the string to note root is null
 * - Traverse the tree to left side and store the subtree in map
 * - Traverse the tree to right side and store the subtree in map
 * - need to get value for currString 
 *      -> here we have to append root.val then left then right of that root which we have checked by above lines
 *      -> or u can declare new function called serialization and return this append root.val + left + right
 * - for every string in map our default value will be 0 as we haven't store it yet 
 *      -> after storing will increase it to 1 time and then we have added it in map
 *      -> so will check for duplicateMap.count == 1 ? if yes means same string already exist in map so this will be duplicate now then add currString in result
 * - will increase the count after of duplicate strings or add default value for non existing string
 *      -> duplicateMap.put(currString, value + 1)
 *      -> here by default we think as 0 for every string as it's not store will do +1 means we have added it now
 *      -> so if we have duplicate string then value will be 2 and we will add it in the map
 * 4. return result
 * 
 * Approach :
 * 
 * 1. Use a map to check if we’ve found a duplicate subtree or not
 * 2. Use a result list to store all duplicate subtrees
 *
 * 3. Recursively traverse the tree
 *    - Base Case: if (root == null) → return "#"
 *
 *    - Recur on the left subtree and get its serialized string
 *    - Recur on the right subtree and get its serialized string
 *
 *    - Now build the current subtree string
 *        → Append root.val + left + right
 *        → Or use a helper function like serialize(root.val, left, right)
 *
 *    - Check in map: for every string, default value is 0 (means we haven’t seen it yet)
 *        → If map.get(currString) == 1 → this is the 2nd time, so it’s a duplicate now → add currString to result
 *    
 *    - Update the map with new count
 *        → map.put(currString, value + 1)
 *        → If it was unseen before, treat value as 0 → now it becomes 1 after this step
 *
 * 4. After recursion completes, return the result list
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * // Globally Declare variables
 * map<String, Integer> duplicateMap
 * result;
 * 
 * function findDuplicateSubtrees(root) {
 * 
 *      result = new ArrayList
 *      duplicateMap = new HashMap
 * 
 *      postOrder(root)
 * 
 *      return result
 * }
 * 
 * function String postOrder(root){
 * 
 *      // Base Case :
 *      if(root == null) return currString.append("#")
 * 
 *      // Recur left side
 *      left = postOrder(root.left)
 * 
 *      // Recur right side
 *      right = postOrder(root.right)
 * 
 *      currString = root.val + "," + left + "," + right
 * 
 *      // Visit node
 *      if(duplicateMap.get(currString) == 1){
 *          result.add(new Array(currString))
 *      }
 * 
 *      duplicateMap.put(currString, value + 1)
 *      
 *      return currString
 * }
 * 
 */