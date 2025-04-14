import java.util.*;

public class DiameterOfBinaryTree {
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. root is given, return length of diameter of the tree
 * 2. Diameter of the binary Tree is length of the lonest path between any two nodes in tree
 * 3. path may or may not pass through the root
 * 4. length of a path between two nodes is represented by number of edges between them
 * 5. I worked on BinaryTreePaths problem and it gives current paths.. 
 * 6. will check first which path's it can show us
 * 7. then will modify that function
 * 
 * Pattern :
 * 
 * 1. I think I need to use backtrack to to check path
 * 2. currentPathCount = longestPathAtLeft + longestPathAtRight 
 * 3. diameter = Math.max(currentPath, diameter)
 * 5. backtrack function : to get longestPath 
 * - create a curr array
 * - start storing root.val one by one in that array till u get leaf node
 * - return array.length to longestPathAtLeft ot LongestPathAt Right
 * 
 * Pseudo Code :
 * 
 * function diameterOfBinaryTree(root){
 * 
 *      List<Integer> currPath = new Array
 *      postOrder(root, 0, currPath)
 *      
 *      return diameter
 * }
 * 
 * // Recursion function 
 * function postOrder(root, diameter, currPath){
 *      
 *      if(root == null) return diameter
 * 
 *      // Check left side
 *      postOrder(root.left, diameter)
 * 
 *      // Check right side
 *      postOrder(root.right, diameter)
 *      
 *      // Visit root
 *      leftSideLongestPath = backtrack(root.left, currPath)
 * 
 *      rightSideLongestPath = backtrack(root.right, currPath)
 * 
 *      currLongestPath = leftSideLongestPath + rightSideLongestPath
 * 
 *      diameter = Math.max(diameter, currLongestPath)
 *           
 * }
 * 
 * // Backtrack Function : to get longest Path
 * function backtrack(root, currPath) {
 * 
 *      // Base Case :
 *      if(root == null){
 *          return currPath.length
 *      }
 * 
 *      currPath.add(root.val)
 * 
 *      if(root.left != null){
 *          backtrack(root.left, currPath)
 *      }
 * 
 *      if(root.right != null){
 *          backtrack(root.right, currPath)
 *      }     
 *      
 * }
 * 
 */