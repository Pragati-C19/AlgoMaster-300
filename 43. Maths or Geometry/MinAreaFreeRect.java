import java.util.*;

public class MinAreaFreeRect {
    
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