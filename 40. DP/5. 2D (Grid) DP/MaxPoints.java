import java.util.*;

public class MaxPoints {
    
    public long maxPoints(int[][] points) {
        
        return 0;
    }

    public static void main(String[] args) {

        MaxPoints solution = new MaxPoints();

        int[][] points1 = {
            {1,2,3},
            {1,5,1},
            {3,1,1}
        };
        System.out.println("Result 1 -> " + solution.maxPoints(points1) + "\n");    // 9

        int[][] points2 = {
            {1,5},
            {2,3},
            {4,2}
        };
        System.out.println("Result 2 -> " + solution.maxPoints(points2) + "\n");    // 11
        
        // int[][] points3 = {
        //     {1}
        // };
        // System.out.println("Result 3 -> " + solution.maxPoints(points3) + "\n");    // 0

    }

}

/*
 * Intuitions :
 
    1. we have given a mxn points called points (0-indexed)
        means it's row by row 
    2. will start from point 0 
        means row = 0
    3. we need to max the number of points u can get from the points
    4. to gain points, we must pick one cell in each row
        will add points[r][c] in our score
    5. However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. 
       For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
       - I don't understand this 
    6. as per examples it seems we need to pick max number from every row and add it
 
 
 * Pattern :
 
 
 
 
 * Pseudo Code :
 



 */