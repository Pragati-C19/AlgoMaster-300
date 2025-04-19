import java.util.*;

public class MyCalendar {
    
}


/*
 * 
 * Intuitions :
 * 
 * 1. we can add new events if adding the event will not cause double booking
 * 2. double booking means :
 * - when some time is common in the given range
 * 3. Event is represented as a pair of times [startTime, EndTime]
 * - means this event includes all numbers x where startTime <= x < EndTime
 * 4. MyCalender() -> initializes the calender objects (idkh)
 * 5. book(int start, int end) 
 * - return true if event can be added
 * - return false if event can't add the event
 * 6. idu.. how it's a binary tree?.. if it's binary tree I have to use TreeMap other wise I'm thinking of using HashMap
 * 
 * 
 * Pattern :
 * 
 * 
 * 1. MayCalender()-
 * - Declare hashmap 
 * 2. book() -
 * - check if the event can be added 
 * - if yes add the event to the hashmap and return true
 * - if no return false
 * 
 * Pseudo Code :
 * 
 * // Globally Declare variable
 * HashMap<Integer, boolean> eventMap;
 * 
 * MyCalender(){
 *      eventMap = new hashmap
 * }
 * 
 * book(){
 *  
 *      if(eventMap.containsKey(StartTime)){
 *          // means that event time is already exists we can't add new event
 *          return false;
 *      }
 *      else {
 *          // event time is not exists we can add new event
 *          for(int i = startTime ; i < EndTime; i++){
 *              eventMap.put(i, true)
 *          }
 * 
 *          return true;
 *      }
 * }
 * 
 * 
 * 
 */