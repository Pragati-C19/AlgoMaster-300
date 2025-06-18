import java.util.*;

public class GenerateTrees {
    
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