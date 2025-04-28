import java.util.*;

public class FindMinArrowShots {
    
    public int findMinArrowShots(int[][] points) {
        
        return 0;
    }

    public static void main(String[] args){

        FindMinArrowShots solution = new FindMinArrowShots();

        int[][] points1 = {
            {10,16},
            {2,8},
            {1,6},
            {7,12}
        };
        System.out.println("Result1 -> " + solution.findMinArrowShots(points1) + "\n");

        int[][] points2 = {
            {1,2},
            {3,4},
            {5,6},
            {7,8}
        };
        System.out.println("Result2 -> " + solution.findMinArrowShots(points2) + "\n");

        int[][] points3 = {
            {1,2},
            {2,3},
            {3,4},
            {4,5}
        };
        System.out.println("Result3 -> " + solution.findMinArrowShots(points3) + "\n");

    }

}


/*
 * 
 * 
 * Intuitions :
 * 
 * 1. Ballons Diameters start and end point is given [start, end]
 * 2. this point only tells x-axis position we don't know anything abt y axis 
 *      - as is that ballong up or down and all nothing at all..
 * 3. Arrows can be shoot vertically in +ve y-direction
 * 4. the ballooon [start, end] will burst by an arrow at x 
 *      where start <= x <= end
 * 5. one more Most IMP thing here
 *      the arrow we shot at x will travel full path.. and burst all booloons on that path
 *      mhnje jr me arrow marla x = 6 vr tr tya line vr jitke pn balloon yet asel te sagle burst hotil
 * 6. that means I have to finde overlap points and burst them at there common num
 * 7. return miminum numbers of arrows that must be shot to burst all ballooons  
 * 
 * Pattern :
 * 
 * 1. declare arrowCount to 0 -> to count arrows 
 * 2. Sort an array by it's start point
 * 3. for( i = 0 to n)
 *      - need to check is there any x that is in the consecutive intersection diameneter
 *      - mhnje asa x havay jo intersect hot asel consecutive paths sobt..
 *      
 * example : [10,13],[2,8],[1,6],[7,11]  ans : x = 6, x = 11
 * 
 *         0    1    2    3    4    5    6    7    8    9   10   11   12   13 
 *     ----|----|----|----|----|----|----|----|----|----|----|----|----|----|----
 *         |    |    |_____________________________|         |______________|
 *         |    |________________________|    |                   |
 *         |                                  |___________________|
 *         |
 * 
 *      - after sorting array [1,6],[2,8],[7,11],[10,13]
 *      - If u see closely.. look it's almost like end of any array as x at start 
 *      - and then changing it
 *      - like let's take initial val of x = 6 -> end of first point
 *      - will check if any next point's starting point is less than current x or not
 *      - if it's less that means that two points are intersecting so it can burst at that x point
 *      - if it's greater that means we need to check another x now..
 *      - so increase the arrow count
 *      - change x to that starting point's end
 * 4. retrun the arrowCount
 * 
 * Pseudo Code :
 * 
 * function findMinArrowShots(points){
 * 
 *      arrowCount = 0
 * 
 *      Arrays.sort(points)
 * 
 *      -> let's assign to value to x as end of 0th index point
 *          x = points[0][1]
 * 
 *      for(i = 0 to n)
 *          
 *          if( points[i][0] > x ) 
 *              arrowCount++
 *              x = points[i][0]
 *          
 *      return arrowCount
 * 
 * }
 * 
 */