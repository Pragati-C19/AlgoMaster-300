import java.util.*;

public class Merge {
    
    public int[][] merge(int[][] intervals) {
        
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

        int[][] result = new int[currResult.size()][2];
        for (int i = 0; i < currResult.size(); i++) {
            result[i] = currResult.get(i);
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
 * 4. let's use two pointer here to store intitial values of start and end
 * 5. and will compare those start and end int this if
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
