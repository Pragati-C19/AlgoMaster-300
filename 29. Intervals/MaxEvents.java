import java.util.*;

public class MaxEvents {
    
    public int maxEvents(int[][] events) {
        
        int countEvents = 0;

        Set<Integer> isOccupied = new HashSet<>();

        Arrays.sort(events, (a, b) -> {
            if(a[1] == b[1]) return Integer.compare(a[0], b[0]);    // startDay
            return Integer.compare(a[1], b[1]);                     // endDay
        });
        System.out.println("Sorted Array is " + Arrays.deepToString(events));

        for (int i = 0; i < events.length; i++) {
            
            int startDay = events[i][0];
            int endDay = events[i][1];
            
            System.out.println("  Start and end Day of event " + Arrays.toString(events[i]) + " : " + startDay + " -> " + endDay);

            for (int d = startDay; d <= endDay; d++) {
                
                System.out.println("    -> checking day is " + d);

                if(!isOccupied.contains(d)){

                    isOccupied.add(d);
                    countEvents++;

                    System.out.println("        ~ Hashset : " + isOccupied);
                    System.out.println("        Current Event Count is " + countEvents);
                    
                    break;
                }
            }
        }

        return countEvents;
    }

    public static void main(String[] args){

        MaxEvents solution = new MaxEvents();

        int[][] events1 = {
            {1,2},
            {2,3},
            {3,4}
        };
        System.out.println("Result1 -> " + solution.maxEvents(events1) + "\n");

        int[][] events2 = {
            {1,2},
            {2,3},
            {3,4},
            {1,2}
        };
        System.out.println("Result2 -> " + solution.maxEvents(events2) + "\n");

        int[][] events3 = {
            {1,2},
            {1,2},
            {3,3},
            {1,5},
            {1,5}
        };
        System.out.println("Result3 -> " + solution.maxEvents(events3) + "\n");

    }

}


/*
 * 
 * 
 * Intuitions :
 * 
 * 1. we have given array of event [startDay, EndDay]
 * 2. need to find the events we can attend without overlapping
 * 3. I think of something so far..
 *  - will check if startDay of event is occupied 
 *      -> if yes then check for next day
 *      -> if no then mark that day as Occupied
 * 
 * Pattern :
 * 
 * 1. countEvents = 0
 * 2. sort events array by startDay then LastDay 
 * 3. Declare a Hashset to check event is occupied or not
 * 4. for (i = 0 to events.length)
 *      for(d = startDay to endDay) -> we need to check all days in between that time
 *         d is days from start to end (all of them)
 *          - if(hashset.occupied(d))
 *              check next day..
 *          - if(!hashset.occupied(d)) 
 *              add that d - day in Hashset
 *              countEvents ++
 * 5. return count Events
 *        
 * 
 * 
 * Pseudo Code :
 * 
 * function maxEvents(events){
 * 
 *      countEvents = 0
 *      isOccupied = new Hashset
 *      
 *      Arrays.sort(events, by startDay then endDay)
 * 
 *      -> check event's specific day for free space
 *      for(i = 0 to events.length)
 *          for(d = startDay to endDay)
 *              
 *              if(!hashSet.contains(d))
 *                  hashset.add(d)
 *                  countEvents++
 *          
 *              else 
 *                  continue
 *              
 *      return countEvents
 * 
 * }
 * 
 */
