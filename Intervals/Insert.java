import java.util.*;

public class Insert {
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        return intervals;
    }

    public static void main(String[] args){

        Insert solution = new Insert();

        int[][] intervals1 = {
            {1, 3},
            {6, 9}
        };
        int[] newInterval1 = {2, 5};
        System.out.println("Result 1 -> " + Arrays.deepToString(solution.insert(intervals1, newInterval1)) + "\n");

        int[][] intervals2 = {
            {1, 2},
            {3, 5},
            {6, 7},
            {8, 10},
            {12, 16}
        };
        int[] newInterval2 = {4, 8};
        System.out.println("Result 2 -> " + Arrays.deepToString(solution.insert(intervals2, newInterval2)) + "\n");

    }

}


/*
 * 
 * Intuitions :
 * 
 * 1. given an intervals [[start, end]]
 * 2. already sorted by start
 * 3. given one more interval newInterval [start, end]
 * 4. insert that newInterval in then given intrvals array
 *      - such that intervals array should be sorted as always
 *      - and intervals should not have merge overlapping intervals if any
 *      - if interval is overlapping merge them
 *      
 * 
 * Pattern :
 * 
 * u can say it's brute force approach and it may take soo much space and time complexity
 * 
 * 1. create a newArray to get full array of  intervals + newInterval
 * 2. store neInterval in it at start
 * 3. use for loop and store all intervals from array in it
 * 4. sort that newArray
 * 5. mergeOverlapIntervals(newArray) -> now use same logic as we did use for merge on that newArray
 *      - declare a list<int[]> mergeArray
 *      - initialized start and end points to store prev values
 *      - use for loop (i = 1 to n)
 *          check if(start[curr] <= end[prev])
 *              if yes then change end[prev] to max between end[curr] or end[prev]
 *          else 
 *              add that start and end to mergeArray
 *              change start[prev] to start[curr]
 *              change end[prev] to end[curr]
 *      - add remaining all intervals in mergeArray
 *      - return mergeArray
 * 6. result = new int[mergeArray.length][2]
 *      for(i = 0 to mergeArray.length)
 *          result[i] = mergeArray.get(i)
 * 7. return result
 *  
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 * 
 */