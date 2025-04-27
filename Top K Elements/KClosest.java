import java.util.*;

public class KClosest {
    
    public int[][] kClosest(int[][] points, int k) {
        
        int[][] result = new int[k][2];

        // will store distance with array
        int[][] updatedArray = new int[points.length][3];
        
        for (int i = 0; i < points.length; i++) {
            updatedArray[i][0] = points[i][0];      // Store x
            updatedArray[i][1] = points[i][1];      // Store y
            updatedArray[i][2] = 0;                 // distance
        }

        // Declare a heap
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < updatedArray.length; i++) {
            updatedArray[i][2] = (int) Math.sqrt(Math.pow(updatedArray[i][0], 2) + Math.pow(updatedArray[i][1], 2));
            minHeap.add(updatedArray[i]);
        }

        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll()[0][1];
        }

        return result;
    }

    public static void main(String[] args){

        KClosest solution = new KClosest();

        int[][] points1 = {
            {1, 3},
            {-2, 2}
        };
        int k1 = 1;
        System.out.println("Result 1 : " + Arrays.deepToString(solution.kClosest(points1, k1)) + "\n");

        int[][] points2 = {
            {3, 3},
            {5, -1},
            {-2, 4}
        };
        int k2 = 2;
        System.out.println("Result 2 : " + Arrays.deepToString(solution.kClosest(points2, k2)) + "\n");
        
    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. we have give a points arrray which contains (x, y)
 * 2. need to find closest point from the origin
 * 
 * First basic thing of math come to my mind is
 * - to find point where is point is we need to use distance formula
 *      √(x^1 - x^2)^2 + (y^1 - y^2)^2
 * - Now see it says closest from origin so our one point will always be (0, 0)
 *      √x^2 + y^2  -> we know this simple math right?
 * - to find closest means distance of that point should be small
 * 
 * Pattern :
 * 
 * 
 * Brute force will be :
 * 
 * 1. update the points 2D array to 
 *     [x, y, distance]
 * 2. let's create a heap maybe it's bcoz I did solve heap problems today but yeah will use minheap
 *      - minHeap -> it will store distance of the point with (x, y)
 *          [x, y, distance]
 *      - remeber to store it by min distance at top
 * 3. for(i = 0 to updatedArray.length) -> 
 *      updatedArray[i][2] = sqrt((updatedArray[i][0])^2 + (updatedArray[i][1])^2)
 *      minHeap.add(updatedArray[i]) 
 * 4. for(i = 0 to k ){
 *      result = minHeap.poll()[0][1]
 *  
 * 
 * Pseudo Code :
 * 
 * 
 * function kclosest(points, k){
 * 
 *      int[] result = new int[k][2]
 * 
 *      int[] updatedArray = new int[points.length][3]
 * 
 *      for(i = 0 to points.length)
 *           updatedArray[i][0] = points[i][0]    // Store x
 *           updatedArray[i][1] = points[i][1]    // Store y 
 *           updatedArray[i][2] = 0;              // Distance
 * 
 *      PQ<int[]> minHeap = new pq<>((a,b) -> a[2] - b[2])
 * 
 *      for(i = 0 < updatedArray.length)
 *          updatedArray[i][2] = sqrt((updatedArray[i][0])^2 + (updatedArray[i][1])^2)
 *          minHeap.add(updatedArray[i])
 * 
 *      for(int i = 0 to k)
 *          result[i] = minHeap.poll()[0][1]
 * 
 *      return result;
 * 
 * }
 * 
 * 
 */
