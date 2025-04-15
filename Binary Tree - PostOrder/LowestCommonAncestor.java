import java.util.*;

public class LowestCommonAncestor {
    
}
/*
 * 
 * Intuitions :
 * 
 * 1. Root of Binary Tree is given return Lowest Common Ancestor (LCA) of given nodes in the tree
 * 2. root, p, q given
 * 3. For this que we are going to play with root.. not root.val only
 * 
 * Pattern :
 * 
 * 1. In Dirver function -> return recursion call
 * 2. recursion call
 * - Base Case -> if(root == null) return null
 * - if we found p or q at that reoot return that root -> if(root == p || root == q) return root
 * - call recursion to check left side
 * - call recursion to check right side
 * - Visit Node -> here we will have to write code for nodes that has no p or q
 *      - both side are not null i.e they had got the p or q in them if(left != null && right != null) return root
 *      - if(left == null) return right
 *      - if(right == null) return left
 * 
 * 
 * Pseudo Code :
 * 
 * function lowestCommonAncestor(root, p, q){
 * 
 *      if(root == null) return null;
 *      
 *      if(root == p || root == q) return root;
 * 
 *      // check leftSubTree
 *      leftSubtree = lowestCommomAncestor(root.left, p, q)
 * 
 *      // check rightSubtree
 *      rightSubtree = lowestCommonAncestor(root.right, p, q)
 * 
 *      // Visit node
 *      if(leftSubtree != null && rightSubtree != null) return root;
 *      
 *      if(leftSubtree == null) return rightSubtree
 *      if(rightSubtree == null) return leftSubtree
 *      
 * }
 * 
 */