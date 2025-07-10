import java.util.*;

public class MaxPoints {
    
    // Driver Function 
    public int maxPoints(int[][] points) {
        
        return 0;
    }

    // Helper Function 
    private boolean isPointOnLine(int[] a, int[] b, int p) {

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