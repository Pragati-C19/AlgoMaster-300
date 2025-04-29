import java.util.*;

public class EraseOverlapIntervals {
    
    public int eraseOverlapIntervals(int[][] intervals) {
        
        return 0;
    }

    public static void main(String[] args){

        EraseOverlapIntervals solution = new EraseOverlapIntervals();

        int[][] intervals1 = {
            {1,2},
            {2,3},
            {3,4},
            {1,3}
        };
        System.out.println("Result1 -> " + solution.eraseOverlapIntervals(intervals1) + "\n");

        int[][] intervals2 = {
            {1,2},
            {1,2},
            {1,2}
        };
        System.out.println("Result2 -> " + solution.eraseOverlapIntervals(intervals2) + "\n");

        int[][] intervals3 = {
            {1,2},
            {2,3}
        };
        System.out.println("Result3 -> " + solution.eraseOverlapIntervals(intervals3) + "\n");

    }

}


/*
 * 
 * Intuitions :
 * 
 * 1. interval is given [start, end]
 * 2. tell minium number of intervals u need to remove to make that array no overlapping one
 * 3. we need to check if any interval is overlapping 
 * 4. if it overlapps we need to remove that interval from array
 * 5. I'm using same approach as we did in merging
 * 6. just instead of adding those merged star and end will increase remove's count 
 * 
 * Pattern :
 * 
 * 1. removeIntervalCount = 0
 * 2. Sort array by it's start point
 * 3. initialize start and end with 0th index of value
 * 3. for(i = 1 to intervals.length)
 *      - check if(startTime[curr] < endTime[prev])
 *          -> if yes then increase removeIntervalCount 
 *          -> else startTime[prev] = startTime[curr] and endTime[prev] = endTime[curr]
 * 4. return removeIntervalCount
 * 
 * //? Note : Don't change start and end time outside of else, karan change kela with curr start and end tr to next time sathi pn count karel
 * example : [1,2],[2,3],[3,4],[1,3]  output 1
 *      
 *          1          2          3          4
 *       ---|----------|----------|----------|-----
 *          |__________|          |__________|
 *          |_____________________|            
 *                     |__________|
 *  
 *      - sort an array : [1,2],[1,3],[2,3],[3,4]
 *      - bagh jr me 1->2 la prev madhe initialize kel
 *      - ani check kartey ki for curr 1->3 with for loop
 *      - tr bagh (startTime[curr] < endTime[prev]) -> (1 < 2) so removeCount ++
 *      - ata me else n karta mhnle ki chalo else chi kay garaj ahe direct start and end value change karu
 *      - tr te next dharel prev la 1->3 and curr la 2->3
 *      - jyat removecount again increase hoil.. to baghat nahi basnar ki remove zalay or nahi te
 *      - so use if else -> is madhe count increase kr else change start and end to curr
 *       
 * 
 * Pseudo Code :
 * 
 * function eraseOverlapIntervals(intervals){
 * 
 *      removeIntervalCount = 0
 *      
 *      Arrays.sort(intervals)
 * 
 *      prevStart = intervals[0][0]
 *      prevEnd = intevrals[0][1]
 * 
 *      for(i = 1 to intervals.length)
 *          if(intervals[i][0] < prevEnd) -> removeIntervalCount++
 *          else 
 *              prevStart = intervals[i][0]
 *              prevEnd = intervals[i][1]
 * 
 *      return removeIntervalCount
 * }
 * 
 */