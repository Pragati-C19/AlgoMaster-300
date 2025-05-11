import java.util.*;

import javax.swing.tree.TreeNode;

public class DistanceK {
    
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

        // 🔧 Add this method for readable printing
        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }


    // Declare Global Variables
    List<Integer> result;
    Map<TreeNode, TreeNode> parentNodeMap;

    // Driver Function
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
     
        result = new ArrayList<>();
        parentNodeMap = new HashMap<>();

        Set<TreeNode> visited = new HashSet<>();

        setParentNodes(root);
        System.out.println("    parentNodeMap : " + parentNodeMap);

        dfs(target, 0, visited, k);

        return result;
    }

    // Recursion Function : To set parent of node in map
    private void setParentNodes(TreeNode node) {

        // Base Case:
        if (node == null) {
            return;
        }

        // add parent to left node
        if (node.left != null) {
            
            parentNodeMap.put(node.left, node);
            setParentNodes(node.left);
        }

        // add parent to right node
        if (node.right != null) {
            
            parentNodeMap.put(node.right, node);
            setParentNodes(node.right);
        }

        return;
    }

    // Recursion Function : To get nodes of K distance from target
    private void dfs(TreeNode node, int distance, Set<TreeNode> visited, int k) {
        
        // Base Case:
        if (node == null || visited.contains(node)) {
            return;
        }

        // check left node
        dfs(node.left, distance + 1, visited, k);

        // check right node
        dfs(node.right, distance + 1, visited, k);

        // check parent node
        TreeNode parentNode = parentNodeMap.get(node);
        dfs(parentNode, distance + 1, visited, k);

        // Visit node
        visited.add(node);

        if (distance == k) {
            
            result.add(node.val);

            System.out.println("    -> result List : " + result);
        }

        return;
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

    // Helper Function : to find target node by value
    public static TreeNode findNode(TreeNode root, int value) {
        if (root == null) return null;
        if (root.val == value) return root;
    
        TreeNode left = findNode(root.left, value);
        if (left != null) return left;
    
        return findNode(root.right, value);
    }
    

    public static void main(String[] args) {
        
        DistanceK solution = new DistanceK();

        // First Example
        Integer[] treeArray1 = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root1 = buildTree(treeArray1);
        TreeNode target1 = findNode(root1, 5);  // 🔍 Find node with value 5
        System.out.println("Result1: " + solution.distanceK(root1, target1, 2) + "\n");

        // Second Example
        Integer[] treeArray2 = {1};
        TreeNode root2 = buildTree(treeArray2);
        TreeNode target2 = findNode(root2, 1);  // 🔍 Find node with value 1
        System.out.println("Result2: " + solution.distanceK(root2, target2, 3) + "\n");

    }

}

/*
 * Intuitions :
 
    1. root of Binary Tree, the value of target node, and integer k is given
    2. we need to return all nodes which are k distance away from target node
    3. What does "distance k" mean?
        - Distance 0 → the target itself.
        - Distance 1 → nodes directly connected to the target (left child, right child, OR parent).
        - Distance 2 → nodes two steps away from the target — like child's child, or parent's child, etc.


 * Pattern :
 
    1. In Binary Tree we can go down easily but not up
        - like we can check childs but will not able to check parent from child 
    2. So will store parent node of that child in an map 
        - Create a Map<TreeNode, TreeNode> to store node, it's parent
    3. Will use DFS starting from target
    4. will use inorder traversal
        - why?
            bcoz it will first check left and right nodes so gettign values after that will be easy
        - also we need to check parent node too
    5. Will need visited array to mark node as visited
        - we don't want duplicates like if we check parent node so we don't want to check it again
    6. if we hit distance == k
        - then add that node in result
    7. why distance + 1?
        - karan simple ahe target 0 distance vr asel tr tyacha left right distance 1 vr asel na so + 1


 * Pseudo Code :
 
    -> Globally Declare variables
        result
        Map<TreeNode, TreeNode> parentMap

    function distanceK(root, target, k){
    
        result = new Array

        parentMap = new HashMap

        set<TreeNode> visited = new hashset

        addValueInParentMap(root)

        dfs(target, 0, visited, k)

        return result
    
    }

    function addValueInParentMap(node){
    
        -> Base Case :
        if(node == null)    return

        -> will check if left is not null then set it's parent as curr Node
        
            if(node.left != null)       parentMap.put(node.left, node)
            addValueInParentMap(node.left)

        -> will check if right is not null then set it's parent as curr Node

            if(node.right != null)      parentMap.put(node.right, node)
            addValueInParentMap(node.right)

        return
    
    }

    dfs(node, distance, visited, k){

        -> Base Case :
        if(node == null || visited.contains(node))    return

        -> Check left node
            dfs(node.left, distance + 1, visited, k)

        -> Check right node
            dfs(node.right, distance + 1, visited, k)

 ~       -> Check parent node  -> This is new in Binary Tree
            parentNode = parentMap.get(node)
            dfs(parentNode, distance + 1, visited, k)

        -> visiting node now
            visited.add(node)

        -> check if distance == k or not?
            if(distance == k)   result.add(node.val)
            return
        
        return;
    
    }

 */