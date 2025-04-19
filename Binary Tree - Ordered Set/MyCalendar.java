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
 * Few Issues in my thinking
 * 1. I'm checking only contains StartTime what if I stored [10, 20] first then I tried to add [0, 22] 
 * - so I need to check if the new event is overlapping with the existing events
 * 2. I'm using HashMap with for loop 
 * - it will create issue with space complexity and take too muh time to store that much number
 * - for next event check all over number again
 * 3. More optimal way is to use TreeMap or TreeSet here
 * - I was confused do I need to use TreeMap or HashMap bcoz I had learn we use TreeMap only to get sorted things
 * - but now I know it should be used when 
 * 
 * 
 * Pattern :
 * 
 * 1st Approach : It's not fully wrong but it will take soo much space
 * 1. MayCalender()-
 * - Declare hashmap 
 * 2. book() -
 * - check if the event can be added 
 * - if yes add the event to the hashmap and return true
 * - if no return false
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
 * 2nd Approach : it's the most optimal way
 * 
 * 1. Declare TreeMap<Integer, Interger> where key is startTime, value is EndTime
 * 2. Numbers are in interval [startTime, EndTime) when
 * - StartTime <= currTime
 * - EndTime > currTime
 * 3. We need to check overlapping 
 * - floorKey = Who’s already partying before you?
 * - ceilingKey = Who’s about to party after you?
 * - Do their times bump into mine? -> If yes → Sorry bro, no party for you.
 * - floorKey(start) → check if previous party is still going on
 * - ceilingKey(start) → check if next party starts while you’re still partying
 * - floorKey and CeilingKey are the keys not the values
 * - to get value u have to say treeMap.get(key)
 * 4. Logic behind checking eventMap.get(floorKey) > currStart
 * - Was that party still going on when I arrived at 18?
 * 
 * In short : 
 *
 * - floorKey(currStart) : Checking previous party, To see if you're starting while they're still partying
 * - eventMap.get(floorKey) : Gives you their end time, So you can check if they’re still going on
 * - eventMap.get(floorKey) > currStart	: Means your start time overlaps, Not allowed to book ❌
 * 
 * 
 * Example :
 * Intervals[key -> value] : [10 -> 20], [30 -> 40]
 * Check for [15 -> 25]
 * 
 * 
 * // Globally Declare Variable 
 * TreeMap<Integer, Integer> eventMap;
 * 
 * MayCalender() {
 *      - eventMap = new TreeMap
 * }
 *      
 * function book(currStart, currEnd) {
 * 
 *      - will take floorKey first : he currStart key chya < startTime asnari key ahe ka check karel
 *      int floorKey = eventMap.floorKey(currStart)   // floorKey = 10
 *      
 *      - ata check kr ki konti pary chalu ahe ka mazya currStart chya adhi ani ti overlap hotey ka?
 *      - eventMap.get(10) = 20 (got value of key)
 *      if(eventMap.get(floorKey) > currStart)    // 20 > 18 mhnje adhichi party 2 min ni overlap hoil jr 18 la me event thevla
 *          return false;
 * 
 *      - will take ceilingKey next : he currStart > startTime asnari key shodhel
 *      int ceilingKey = eventMap.cielingKey(currStart)    // ceilingKey = 30
 *      
 *      - ata check kr ki maza end jo ahe to tya party la overlap tr nahi hotye?
 *      if(ceilingKey < currEnd)            // 30 < 35 mhnje next party che 5 min overlap hotil mazya current sobt
 *          return false
 *       
 * 
 *      - otherwise add key and value in map
 *      eventMap.put(currStart, currEnd)
 * 
 *      return true
 * 
 * }
 * 
 */