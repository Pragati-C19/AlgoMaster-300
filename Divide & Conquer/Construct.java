import java.util.*;;

public class Construct {
    private class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;
    
        public Node() {}
    
        public Node(boolean _val, boolean _isLeaf) {
            val = _val;
            isLeaf = _isLeaf;
        }
    
        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    } 

    public Node construct(int[][] grid) {
        
        return 1;
    }

    public static void main(String[] args){
        Construct solution = new Construct();

        int[][] grid1 = {{0, 1}, {1, 0}};
        int[][] grid2 = {
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0}
        };

        Node root1 = solution.construct(grid1);
        Node root2 = solution.construct(grid2);

        System.out.println("Constructed for grid1");
        System.out.println("Constructed for grid2");
    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. Quad Tree
 * - a tree in which each node has 4 children (topLeft. topRight, bottomLeft,
 * bottomRight)
 * - each node represents a rectangle in the plane
 * - Taking help from gpt
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
