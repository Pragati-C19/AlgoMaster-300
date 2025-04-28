import java.util.*;

public class Merge {
    
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
