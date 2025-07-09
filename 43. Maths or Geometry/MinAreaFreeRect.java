import java.util.*;

public class MinAreaFreeRect {
    
    public double minAreaFreeRect(int[][] points) {
        
        return 0;
    }

    // Helper Function : to get distance square for points
    private int distanceSquare(int[] a, int[] b) {

        return 0;
    }

    public static void main(String[] args) {

        MinAreaFreeRect solution = new MinAreaFreeRect();

        int[][] points1 = {
            {1,2},
            {2,1},
            {1,0},
            {0,1}
        };
        System.out.println(" Result1 -> " + solution.minAreaFreeRect(points1) + "\n");   // 2.00000

        int[][] points2 = {
            {0,1},
            {2,1},
            {1,1},
            {1,0},
            {2,0}
        };
        System.out.println(" Result2 -> " + solution.minAreaFreeRect(points2) + "\n");    // 1.00000

        int[][] points3 = {
            {0,3},
            {1,2},
            {3,1},
            {1,3},
            {2,1}
        };
        System.out.println(" Result3 -> " + solution.minAreaFreeRect(points3) + "\n");    // 0

    }

}

/*
 * Intuitions :
 
    1. we have given an array points 
        where points[i] = [x, y]
    2. We need to return the minimum area of any rectangle formed from these points
 
 * Pattern :
    
    1. Brute force approach I'm thinking :
        - get distance between all nums 
        - then create a map and store it's freq 
        - take two distance with lower value
        - multiply them and return 
            why? bcoz area of rectangle is length * breath 

    2. I did lot of browsing and learn the optimal way
        it's same as mine but little different

        - For all pairs of points :
            treat the pair as the ends of diagonal

            Check midPoint = (x1 + x2, y1 + y2)
            check distSq = (x1 - x2)^2 + (y1 - y2)^2

            will store this info in map
                key : string of (midX, midY, distSq)
                value : list of points (diagonal end)
 
        - For each group of diagonals with the same midpoint and length :
            try all combinations of 2 diagonals ends: (p1, p2)

            use those 2 points (p1, p2) and midpoint to compute area:
                side1 = dist between p1 and p2
                side2 = dist from midpoint to either p1 or p2
                area = side1 * side2

        - Keep track of the minimum area found

        - Return 0 if no rectagle, 
            else return the minimum area
 
 
 * Pseudo Code :
 




 */