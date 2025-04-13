import java.util.*;

public class MinDiffInBST {

    
}

/*
 * 
 * Intuitions :
 * 
 * 1. Root of Binary Tree is given
 * 2. Find the minimum of any two different nodes from the tree 
 * 3. this problem is almost same as MaxAncestorDiff
 * 
 * 
 * Pattern :
 * 
 * 1. We'll use recursion here
 * 2. We'll find the minimum of the left subtree and the right subtree
 * 3. We will find minSoFar and MaxSoFar
 * 4. if root == null -> difference of minSoFar maxSofar
 * 5. then will call for recursion first left then right
 * 6. will find min(leftSubtree, rightSubtree)
 * 
 * Pseudo Code :
 * 
 * 
 * function minDiffInBST(root){
 * 
 *      return findMinDiff(root, root.val, root.val, root.val)
 * }
 * 
 * function findMinDiff(root, minSoFar, maxSoFar) {
 * 
 *      if(root == null) return (maxSoFar - minSoFar)     
 * 
 *      minSoFar = min(minSoFar, root.val)
 *      maxSoFar = max(maxSoFar, root.val)
 * 
 *      leftSubtree = findMinDiff(root.left, minSoFar, maxSoFar)
 *      rightSubtree = findMinDiff(root.right, minSoFar, maxSoFar)
 * 
 *      currentMinDiff = min(leftSubtree, rightSubtree) 
 * 
 *      return currentMinDiff;
 * 
 * }
 * 
 * 
 */