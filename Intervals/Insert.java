import java.util.*;

public class Insert {
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        int n = intervals.length;

        int[][] afterAddNewIntervals = new int[n+1][2];
        afterAddNewIntervals[0][0] = newInterval[0];
        afterAddNewIntervals[0][1] = newInterval[1];

        for (int i = 0; i < n; i++) {
            afterAddNewIntervals[i+1][0] = intervals[i][0];
            afterAddNewIntervals[i+1][1] = intervals[i][1];
        }

        Arrays.sort(afterAddNewIntervals, (a, b) -> a[0] - b[0]);
        System.out.println("sorted array of afterAddNewIntervals : " + Arrays.deepToString(afterAddNewIntervals));

        return intervals;
    }

    public List<int[]> merge(int[][] intervals) {
        
        int n = intervals.length;

        List<int[]> currResult = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        System.out.println("Sorted Array : " + Arrays.deepToString(intervals));

        // assigned initial interval at 0th index to start and end
        int start = intervals[0][0];        // startTime[prev]
        int end = intervals[0][1];          // endTime[prev]
        
        for (int i = 1; i < n ; i++) {

            // here [i][0] means startTime endTime of next

            if (intervals[i][0] <= end) {
                 // Overlapping
                System.out.println(" start[curr] " + intervals[i][0] + " <= end[prev] " + end);

                // will only change end of interval here.. 
                end = Math.max(end, intervals[i][1]);
                System.out.println("    [IF] updated start and end to : (" + start + " , " + end + ")");
            
            }
            else {

                currResult.add(new int[]{start, end});

                start = intervals[i][0];
                end = intervals[i][1];
                
                System.out.println("    [ELSE] updated start and end to : (" + start + " , " + end + ")");

            }
        
        }

        // add last remaining intervals 
        currResult.add(new int[] {start, end});

        return currResult;
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