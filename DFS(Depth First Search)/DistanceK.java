import java.util.*;

public class DistanceK {
   
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