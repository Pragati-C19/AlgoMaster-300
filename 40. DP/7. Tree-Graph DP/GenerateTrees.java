import java.util.*;

import javax.swing.tree.TreeNode;

public class GenerateTrees {
    
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

    // Driver Function
    public List<TreeNode> generateTrees(int n) {
        
        // Declare variables 
        List<TreeNode> resultTreesList = new ArrayList<>();

        resultTreesList = buildBSTTree(1, n);

        return resultTreesList;
    }

    // Helper Function : to get all BST 
    private List<TreeNode> buildBSTTree(int start, int end) {

        // Declare variables 
        List<TreeNode> currTreesList = new ArrayList<>();

        // Base Case : if start > end means we are not able to generate any subtree now
        if (start > end) {
            
            currTreesList.add(null);
            // System.out.println("    we can't generate more subtrees not as (" + start + " > " + end + ") so will return currTreesList : " + currTreesList);
        }


        // will check all index from start to end
        for (int i = start; i <= end; i++) {
        
            // will check subtrees of both sides 
            List<TreeNode> leftSideSubTree = buildBSTTree(start, i-1);
            List<TreeNode> rightSideSubTree = buildBSTTree(i+1, end);


            // Debugger Sout
            System.out.print("    - LeftSideSubTree : [");
            for (TreeNode node : leftSideSubTree) {
                System.out.print(printTreeAsArrayFormat(node));
            }
            System.out.println("]");

            System.out.print("    - RightSideSubTree : [");
            for (TreeNode node : rightSideSubTree) {
                System.out.print(printTreeAsArrayFormat(node));
            }
            System.out.println("]");


            // will check all nodes one by one and create BST 
            for (TreeNode leftNode : leftSideSubTree) {
                for (TreeNode rightNode : rightSideSubTree) {
                    
                    // create new tree with root i 
                    TreeNode root = new TreeNode(i);

                    root.left = leftNode;
                    root.right = rightNode;

                    // add this root in list
                    currTreesList.add(root);
                }
            }
        }

        return currTreesList;
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

    public static void main(String[] args){
        
        GenerateTrees solution = new GenerateTrees();

        // First Example
        List<TreeNode> result1 = solution.generateTrees(3);
        System.out.print("Result1 -> ");
        for (TreeNode root : result1) {
            System.out.print(printTreeAsArrayFormat(root) + ", ");
        }
        System.out.println("\n");

        // Second Example
        List<TreeNode> result2 = solution.generateTrees(1);
        System.out.print("Result2 -> ");
        for (TreeNode root : result2) {
            System.out.print(printTreeAsArrayFormat(root) + " ");
        }
        System.out.println("\n");

    }

}

/*
 * Intuitions :
 
    1. We have given a integer n
    2. need to return all structurally unique BST's (binary search tree)
        which has exactly n nodes of unique values from 1 to n 
    3. return all answers in any order

 
 * Pattern :
 
    1. Binary Search Tree mean on 
        left side we should have small values than currNode 
        right side we should have larger values than currNode

    2. so we need to create a function called binary search
        where will check if start and end are greater than mid if yes will place them accordingly

    3. will check letter's one by one?
        1 che 2 type che BST bantil
            1             1
             \             \
              2             3
               \           /
                3         2

        2 che 1 type che BST bantil
                2
              /   \
             1     3

        3 che 2 type che BST bantil
                3          3
               /          /
              2          1
             /            \
            1              2

    4. Create a resultTrees List to store all roots of tree
            List<TreeNode> resultTree = new ArraysList<>()

    5. jr start > end asel means aplylakde subtree nahiye kontech
        - will return resultTree

    6. Will check start to end roots one by one
        - get leftSubtree = start, i-1
        - get rightSubstree = i+1, end

    7. then will check nodes from left and right subtrees
        for(leftNode : leftSubTree)
            for(rightNode : rightSubTree)

                - declare a new root 
                - root.left = left
                - root.right = right

                - last add that root in resultTree

                

    ^ Dry Run :

        n = 3 means we are going to check from 1 to 3
    
        - Try 1 as root:
            subTreeLeft: []
            subTreeRight: [2,3]
        
                - Root: 2 -> [null, 3]
                - Root: 3 -> [2, null]
            
                - 2 right child subtrees 

            2 trees with root 1

        - Try 2 as root:
            subTreeLeft: [1]
            subTreeRight: [3]
        
                - Root: 1 -> [null, null]
                - Root: 3 -> [null, null]
            
                - 0 child subtrees 

            1 tree with root 2

        - Try 3 as root:
            subTreeLeft: [1,2]
            subTreeRight: []
        
                - Root: 1 -> [null, 2]
                - Root: 2 -> [1, null]
            
                - 2 left subtrees 

            2 trees with root 3

        - Total: 5 trees!
 

 * Pseudo Code :
 


 */