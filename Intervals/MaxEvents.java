import java.util.*;

public class MaxEvents {
    
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
