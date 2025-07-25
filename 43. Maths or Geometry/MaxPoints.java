import java.util.*;

public class MaxPoints {
    
    // Driver Function 
    public int maxPoints(int[][] points) {
        
        // Declare variables
        int n = points.length;
        int maxPointsOnLine = 2;    

        // Base Case : if points are less than 2 then we don't need to check full code
        if (n <= 2) {
            
            System.out.println("    points are <= 2 so returning n : " + n);
            return n;
        }

        // let's start checking points 
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                // declare variable for points 
                int[] p1 = points[i];
                int[] p2 = points[j];

                // Count how many points lie on the line defined by p1 and p2
                int count = 2;  // p1 and p2 are already on line

                // Check other points from array 
                for (int k = 0; k < n; k++) {
                    
                    // if we are on index i and j we don't need to check it we have already check it
                    if (k == i || k == j) {
                        continue;
                    }

                    // declare variable for point p3
                    int[] p3 = points[k];

                    // check if point p3 is on line 
                    if (isPointOnLine(p1, p2, p3)) {
                        
                        count++;
                    }
                }

                // Update maxPoints on line count 
                maxPointsOnLine = Math.max(maxPointsOnLine, count);
            }
        }


        return maxPointsOnLine;
    }

    // Helper Function : To check if Point P on line AB or not
    private boolean isPointOnLine(int[] a, int[] b, int[] p) {

        // Get x and y values for vectors 
        
        // AB vector = (x2 - x1, y2 - y1)
        int x1 = b[0] - a[0];
        int y1 = b[1] - a[1];

        // AP vector = (x3 - x1, y3 - y1)
        int x2 = p[0] - a[0];
        int y2 = p[1] - a[1];

        // Cross Product of AB x AP = (x1 * y2) - (x2 * y1)
        int crossProduct = (x1 * y2) - (x2 * y1);
        
        if (crossProduct == 0) {
            
            System.out.println("    - point A" + Arrays.toString(a) + ", B" + Arrays.toString(b) + " and P" + Arrays.toString(p) + " are on Same Line.. ");
            return true;
        }

        System.out.println("    - point A" + Arrays.toString(a) + ", B" + Arrays.toString(b) + " and P" + Arrays.toString(p) + " are on Different Line.. ");
        return false;
    }

    public static void main(String[] args) {

        MaxPoints solution = new MaxPoints();

        int[][] points1 = {
            {1,1},
            {2,2},
            {3,3}
        };
        System.out.println(" Result1 -> " + solution.maxPoints(points1) + "\n");   // 3

        int[][] points2 = {
            {1,1},
            {3,2},
            {5,3},
            {4,1},
            {2,3},
            {1,4}
        };
        System.out.println(" Result2 -> " + solution.maxPoints(points2) + "\n");    // 4

        int[][] points3 = {
            {0,0}
        };
        System.out.println(" Result3 -> " + solution.maxPoints(points3) + "\n");   // 1

    }

}

/*
 * Intuitions :
 
    1. We have given an array called points
        where points[i] = [x,y]
    2. we need to check all points lies on a straight line
    3. and return that count 

 
 * Pattern :
 
    1. How can I know if point is lie on the line?
        - As we learn it can be done by slope check
            If points A(x1, y1), B(x2, y2), and P(x, y) are collinear:
                Slope(AP) = Slope(AB)
            
            but in code I think slope method will not be a safe, bcoz it can cause division by 0
        
        - Area of traingle = 0 Method
            If given points are A(x1, y1), B(x2, y2), and P(x, y)

            We need to find Area of triangle :
                but we can't use standard formula here 
                    1/2 base * height
                bcoz we don't know the height and we can't find it easily

            so Will use Shoelace Formula (Area from 3 points)
                area of traingle ABP using shoelace formula
                    area = (1/2) * | x1*(y2 - y) + x2*(y - y1) + x*(y1 - y2) |
                
            If area = 0, P lies on line AB 
 
        - Using cross product 
            If given points are A(x1, y1), B(x2, y2), and P(x, y)
                In vector form:
                    AB = (x2 - x1, y2 - y1)
                    AP = (x - x1, y - y1)

            then check AB * AP = 0
            if yes means P lies on AB 

    2. isPointOnLine (A, B, P) 
        - use any one method will go with cross product
        -  get values for 
            AB vector = (x2 - x1, y2 - y1)
            AP vector = (x3 - x1, y3 - y1)
        - Then get cross product of them,  
            AB * AP = (x1 * y2) - (x2 * y1)
            
            why ?
                cross product of two vectors (a, b) and (c, d) is defined as:
                    Cross = (a ⋅ d) − (b ⋅ c)

        - check if that cross product is 0 or not..
            if yes then return true 
            else false

    3. maxPoints (points) 
        - declare a variable maxPointsOnLine = 2
            why? 
                karan line tr 2 points ni bannar ahe compulsory 
                without at least two point line kaskay asel na?
        - then check all pair of points
            for(i = 0 to n)
                for(j = i+1 to n)

        - Count how many points lie on the line defined by points[i] and points[j]
        - count will be 2 till this bcoz points[i] and points[j] are on same line na
        
        - check all points from 0 to n
            for(k = 0 to n)
            
            - if k = i or j, will continue bcoz we have already knows that num is on line
            - check if points[k] lies on line by calling helper function
                if yes increase the count

        - once we check all values for k 
            updated maxPointsOnLine 

        - at the end return maxPointsOnLine


 */