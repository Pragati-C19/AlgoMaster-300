import java.util.*;;

public class Construct {

}

/*
 * 
 * Intuitions :
 * 
 * 1. Quad Tree
 * - a tree in which each node has 4 children (topLeft. topRight, bottomLeft,
 * bottomRight)
 * - each node represents a rectangle in the plane
 * 
 * Pattern :
 * 
 * 1. If the grid has the same value (all 0s or all 1s), return a leaf node
 * (isLeaf = true).
 * 2. Recursive Case :
 * - Split the grid into 4 equal sub grids - topLeft, topRight, bottomLeft,
 * bottomRight
 * - Recursively build a node for each sub grid
 * - if all 4 children turn out to be leaf nodes with the same value, merge them
 * into one leaf node
 * 3. Return the constructed node
 * 
 * Pseudo Code :
 * 
 * function construct(grid):
 * # Base Case: If all values in grid are the same, create a leaf node
 * if all_same(grid):
 * return Node(val=grid[0][0], isLeaf=True)
 * 
 * # Otherwise, split the grid into four parts
 * n = len(grid) / 2
 * topLeft = construct(top-left sub-grid)
 * topRight = construct(top-right sub-grid)
 * bottomLeft = construct(bottom-left sub-grid)
 * bottomRight = construct(bottom-right sub-grid)
 * 
 * # If all children are leaves with the same value, merge into one leaf
 * if (topLeft.isLeaf and topRight.isLeaf and bottomLeft.isLeaf and
 * bottomRight.isLeaf
 * and topLeft.val == topRight.val == bottomLeft.val == bottomRight.val):
 * return Node(val=topLeft.val, isLeaf=True)
 * 
 * # Otherwise, create a parent node
 * return Node(val=1, isLeaf=False, topLeft, topRight, bottomLeft, bottomRight)
 * 
 * 
 */
