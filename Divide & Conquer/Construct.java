import java.util.*;;

public class Construct {

    // Node class representing each node in the Quad Tree
    private class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

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

    // Main function to start the Quad Tree construction
    public Node construct(int[][] grid) {
        int n = grid.length;
        return build(grid, 0, 0, n);
    }

    // Recursive function to build the Quad Tree
    private Node build(int[][] grid, int row, int col, int size) {
        System.out.println("Building node from row: " + row + ", col: " + col + ", size: " + size);

        // If grid has same value, return a leaf node
        if (isUniform(grid, row, col, size)) {
            System.out.println("Leaf node created with value: " + grid[row][col]);
            return new Node(grid[row][col] == 1, true);
        }

        // Divide into 4 quadrants (top-left, top-right, bottom-left, bottom-right)
        int half = size / 2;
        System.out.println("Splitting into quadrants...");

        Node topLeft = build(grid, row, col, half);
        Node topRight = build(grid, row, col + half, half);
        Node bottomLeft = build(grid, row + half, col, half);
        Node bottomRight = build(grid, row + half, col + half, half);

        // If all children are leaf nodes with same value, merge them into one leaf node
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf &&
                topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val) {

            System.out.println("Merging leaf nodes into one leaf node with value: " + topLeft.val);
            return new Node(topLeft.val, true);
        }

        // If they are different, create a parent node with these children
        System.out.println("Creating a parent node with 4 children.");
        return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    // Check if the current grid section has all the same values
    private boolean isUniform(int[][] grid, int row, int col, int size) {
        int value = grid[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (grid[i][j] != value)
                    return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        Construct solution = new Construct();

        int[][] grid1 = { { 0, 1 }, { 1, 0 } };
        int[][] grid2 = {
                { 1, 1, 1, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 0, 0, 0, 0 }
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
