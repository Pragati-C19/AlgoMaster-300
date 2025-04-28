import java.util.*;

public class Merge {
    
    public int[][] merge(int[][] intervals) {
        
        int n = intervals.length;

        int[][] result = new int[n][2];

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        System.out.println("  Sorted Array : " + Arrays.deepToString(intervals));

        for (int i = 0; i < n - 1; i++) {

            if (intervals[i+1][0] < intervals[i][1]) {
                
                System.out.println(" Intervals are start[next] " + intervals[i+1][0] + " < end[curr] " + intervals[i][1]);

                result[i][0] = intervals[i][0];
                result[i][1] = intervals[i+1][1];

                if(intervals[i+1][1] < intervals[i][1]) {
                    
                    System.out.println(" Intervals are end[next] " + intervals[i+1][1] + " < end[curr] " + intervals[i][1]);
                    
                    result[i][1] = intervals[i][1];
                }
            }
        
        }

        return result;
    }

    public static void main(String[] args){

        Merge solution = new Merge();

        int[][] intervals1 = {
            {1, 3},
            {2, 6},
            {8, 10},
            {15, 18}
        };
        System.out.println("Result1 -> " + Arrays.deepToString(solution.merge(intervals1)) + "\n");

        int[][] intervals2 = {
            {1, 4},
            {4, 5}
        };
        System.out.println("Result2 -> " + Arrays.deepToString(solution.merge(intervals2)) + "\n");

    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. array of interval is given [start, end]
 * 2. merge all overlapping intervals
 * 3. as per my knowleged any interval overlap when
 * - startTime of next is < than endTime of curr
 *      add interval in result as [startTime[curr], endTime[next]]
 * - startTime of next < startTime of curr && endTime of next is < than endTime of curr
 *      add interval in result as [startTime[curr], endTime[curr]]
 * 
 * Pattern :
 * 
 * 1. declare a result
 * 2. sort an array by it's startTime
 * 3. for (i = 0 to intervals.length)
 *      
 *      if(intervals[i+1][0] < intervals[i][1])  // startTime[next] < endTime[curr]
 *  
 *          if(intervals[i+1][1] < intervals[i][1])         // endTime[next] < endTime[curr]
 *              result.add(intervals[i][0], intervals[i][1])       // add interval
 * 
 *          result.add(intervals[i][0], interval[i+1][1])     // add interval
 * 
 * 4. return result  
 * 
 *      
 * 
 * Pseudo Code :
 * 
 * 
 * 
 * 
 */
