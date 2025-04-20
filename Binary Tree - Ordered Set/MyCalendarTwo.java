import java.util.*;

public class MyCalendarTwo {
    
    // Global variables
    TreeMap<Integer, Integer> singleBookingEvents;
    TreeMap<Integer, Integer> doubleBookingEvents;

    public MyCalendarTwo() {
            
        // Assigning value to treemap
        singleBookingEvents = new TreeMap<>();
        doubleBookingEvents = new TreeMap<>();

        System.out.println("Starting the Iteration... \n");

    }
    
    public boolean book(int startTime, int endTime) {
        
        Integer floorKeyDBE = doubleBookingEvents.floorKey(startTime);
        Integer ceilingKeyDBE = doubleBookingEvents.ceilingKey(startTime);
        
        System.out.println("    [DBE] Floor Key : Check if there is any event started Before " + startTime + " : " + floorKeyDBE);
        System.out.println("    [DBE] Ceiling Key : Check if there is any event started After " + startTime + " : " + ceilingKeyDBE);

        if (floorKeyDBE != null && doubleBookingEvents.get(floorKeyDBE) < startTime) {
            System.out.println("    - [DBE] " + doubleBookingEvents.get(floorKeyDBE) + " > " + startTime + " means the event I want to add is overlapping by : " + (doubleBookingEvents.get(floorKeyDBE) - startTime));
            return false;
        }
        if (ceilingKeyDBE != null && ceilingKeyDBE < endTime) {
            System.out.println("    - [DBE] " + ceilingKeyDBE + " < " + endTime + " means the event I want to add is overlapping by : " + (endTime - ceilingKeyDBE));
            return false;
        }

        Integer floorKeySBE = singleBookingEvents.floorKey(startTime);
        Integer ceilingKeySBE = singleBookingEvents.ceilingKey(startTime);
        
        System.out.println("    [SBE] Floor Key : Check if there is any event started Before " + startTime + " : " + floorKeySBE);
        System.out.println("    [SBE] Ceiling Key : Check if there is any event started After " + startTime + " : " + ceilingKeySBE);

        if (floorKeySBE != null && singleBookingEvents.get(floorKeySBE) > startTime) {
            System.out.println("    - [SBE] " + singleBookingEvents.get(floorKeySBE) + " > " + startTime + " means the event I want to add is overlapping by : " + (singleBookingEvents.get(floorKeySBE) - startTime));
            
            doubleBookingEvents.put(startTime, endTime);
            System.out.println("    - Event Added in DBE " + doubleBookingEvents + " : " + startTime + " -> " + endTime);

            return true;
        }
        if (ceilingKeySBE != null && ceilingKeySBE < endTime) {
            System.out.println("    - [SBE] " + ceilingKeySBE + " < " + endTime + " means the event I want to add is overlapping by : " + (endTime - ceilingKeySBE));
            
            doubleBookingEvents.put(startTime, endTime);
            System.out.println("    - Event Added in DBE " + doubleBookingEvents + " : " + startTime + " -> " + endTime);

            return true;
        }
        

        singleBookingEvents.put(startTime, endTime);
        System.out.println("    - Event Added in SBE " + singleBookingEvents + " : " + startTime + " -> " + endTime);
        
        return true;
    }


    public static void main (String[] args){
        
        MyCalendarTwo solution = new MyCalendarTwo();

        // First Example
        System.out.println("Final Result : ");
        System.out.println("  1st Iteration : " + solution.book(10, 20));  // Output: true
        System.out.println("  2nd Iteration : " + solution.book(50, 60));  // Output: true
        System.out.println("  3rd Iteration : " + solution.book(10, 40));  // Output: true
        System.out.println("  4th Iteration : " + solution.book(5, 15));  // Output: false
        System.out.println("  5th Iteration : " + solution.book(5, 10));  // Output: true
        System.out.println("  6th Iteration : " + solution.book(25, 55));  // Output: true
        
    }
}


/*
 * //? Use Map.Entry here
 * Map.Entry<Integer, Integer> entry : doubleBookingEvents.entrySet()
 * - This line is use for checking all entries from the map
 * - map.entrySet() is use for getting all the set of key and values in map
 * - entry.getKey() is use for getting the key of the entry
 * - entry.getValue() is use for getting the value of the entry
 * 
 * 
 * 
 * Intuitions :
 * 
 * 1. Que is same as MyCalender one.. just in that we were checking double overlapping 
 * 2. here we want to check triple overlapping
 * 3. will use Map to store startTime as key and endTime as a value
 * 
 * 
 * Pattern :
 * 
 * 1. Declare Map as global variable
 * - singleBookingEvents 
 * - doubleBookingEvents
 * 2. Boolean book function (we are doing slmost same we did in MyCalender)
 * - get floorKey from doubleBookingEvents map for currStartTime
 * - get ceilKey from doubleBookingEvents map for currStartTime
 * - check for conditions if it's triple booking or not -> if yes then return false
 *      -> value.floorKey > startTime ? return false
 *      -> ceilingKey < endTime ? return false
 * - get floorKey from singleBookingEvents map for currStartTime
 * - get ceilKey from singleBookingEvents map for currStartTime
 * - check for conditions if it's double booking or not -> if yes then add it in doubleBookingEvents map
 *      -> value.floorKey > startTime ? add in dBE
 *      -> ceilingKey < endTime ? add in dBE
 * - if above all condtions are not fullfilled then add it in singleBookingEvents map
 * - return true
 * 
 * 
 * Pseudo Code :
 * 
 * // Globally declare mapps
 * TreeMap singleBookingEvents 
 * TreeMap doubleBookingEvents 
 * 
 * MyCalender {
 *      ~ assign value to both maps
 * }
 * 
 * function book(startTime, endTime) {
 * 
 *      - Check triple booking
 *      -> let's get floorKey and ceilingKey 
 *          floorKey = doubleBookingEvents.floorKey(startTime)
 *          ceilingKey = doubleBookingEvents.ceilingKey(startTime)
 * 
 *      -> Check for condition if it's triple booking or not
 *          if(value.floorKey > startTime || ceilingKey < endTime) return false
 *      
 *      - Check double booking
 *      -> let's get floorKey and ceilingKey
 *          floorKey = singleBookingEvents.floorKey(startTime)
 *          ceilingKey = singleBookingEvents.ceilingKey(startTime)
 * 
 *      -> Check for condition if it's double boking or not
 *          if(value.floorKey > startTime || ceilingKey < endTime) doubleBookingEvents.add(startTime, EndTime) return true
 * 
 *  
 *      - otherwise
 *      -> add event in singleBookingEvents map
 *      -> return true
 * }
 * 
 * 
 * 
 * 2nd Approach - Using Map.entry 
 * 
 * 
 * 1. Loop through doubleBookingEvents:
 *      - If (start < endDouble && startDouble < end), return false (triple booking)
 * 2. Loop through singleBookingEvents:
 *      - If (start < endSingle && startSingle < end), then:
 *         - Calculate overlap range: max(start, startSingle), min(end, endSingle)
 *         - Add that range to doubleBookingEvents
 * 3. Finally, add the original [start, end) to singleBookingEvents
 * 
 * 4. in above approach we are doing same thing... just slight difference of for loop
 *      - in first approach I used value.floorkey > startTime
 *      - here I'm saying endTimeDBE > currstartTime, value.floorKey is a value at that startTime entry na
 *      - in first approch I used ceilingKey < endTime
 *      - here I'm saying startTimeDBE < currendTime, ceilingKey is the key of that startTime entry na
 * 
 * 5. he bagh khup simple ahe.. 
 * - floorKey sangte ki curr key pekshya immediate lahan konti key ahe ka mazyakde? 
 * - ceilingKey sangte ki curr key pekshya immediate Mothi konti key ahe ka mazyakde?
 * - mala range madhe yetay currKey and currValue he check karaychy
 * - tyasathi me baghtey ki floorKey chi value ahe ti mothi ahe ka currKey pekshya?
 * - or me baghtey ki ceilingKey ahe ti lahan ahe ka currValue pekshya?
 * 
 * 
 * 
 * function book(startTime, EndTime){
 * 
 *      for(Map.Entry<Integer, Integer> entry : doubleBookingEvents.entrySet()){
 *          
 *          -> get key and value
 *          startTimeDBE = entry.getKey
 *          endTimeDBE = entry.getValue
 * 
 *          -> check if the currStart time or currEnd Time is overlapping ?
 *          if(currstartTime < endTimeDBE &&  currEndTime > startTimeDBE ) 
 *              return false
 * 
 *      }
 *
 *      -> do same for singleBookingEvents
 *      for(Map.Entry<Integer, Integer> entry : singleBookingEvents.entrySet()){
 *          
 *          -> get key and value
 *          startTimeSBE = entry.getKey
 *          endTimeSBE = entry.getValue
 * 
 *          -> check if the currStart time or currEnd Time is overlapping ?
 *          if(currstartTime < endTimeSBE &&  currEndTime > startTimeSBE ) 
 *              doubleBoookingEvents.add(key, value)
 *              return true
 *      }
 * 
 *      -> add event in singleBookingEvents if above any condition is not valid
 *      -> return true
 * }
 * 
 * 
 * 
 */