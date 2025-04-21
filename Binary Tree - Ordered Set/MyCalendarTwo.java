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
        
        // 1. Check for Triple Booking
        for(Map.Entry<Integer, Integer> event : doubleBookingEvents.entrySet()){
            
            int keyDBE = event.getKey(); 
            int valueDBE = event.getValue();
            System.out.println("    [DBE] current event : " + keyDBE + " , " + valueDBE);

            if (startTime < valueDBE && endTime > keyDBE) {
                System.out.println("    [DBE] startTime (" + startTime + ") < valueDBE (" + valueDBE + ") and endTime (" + endTime + ") > keyDBE (" + keyDBE + ")");
                System.out.println("        - Means there is Triple Booking");
                
                return false;
            }
        }

        // 2. Declare a overlapping int array to temporary store overlapping time till for loop get's completed 
        List<int[]> overlapingEvents = new ArrayList<>();

        // 3. Check for Double Booking -> if found add it in overlapingEvents array
        for(Map.Entry<Integer, Integer> event : singleBookingEvents.entrySet()){
            
            int keySBE = event.getKey(); 
            int valueSBE = event.getValue();
            System.out.println("    [SBE] current event : " + keySBE + " , " + valueSBE);

            if (startTime < valueSBE && endTime > keySBE) {
                System.out.println("    [SBE] startTime (" + startTime + ") < valueSBE (" + valueSBE + ") and endTime (" + endTime + ") > keySBE (" + keySBE + ")");
                System.out.println("        - Means there is Double Booking");
                
                // There's an overlap → get time of overlap only
                int overlapStart = Math.max(startTime, keySBE);
                int overlapEnd = Math.min(endTime, valueSBE);
                
                // Temporary store that time of overlap in overlapping map 
                overlapingEvents.add(new int[] {overlapStart, overlapEnd});
                System.out.println("    - Event Added in OE " + Arrays.deepToString(overlapingEvents.toArray()) + " : " + overlapStart + " -> " + overlapEnd);

            }
        }

        // 4. After getting all overlapping events add it in doubleMap now.. this is the last count
        for(int[] event : overlapingEvents){

            doubleBookingEvents.put(event[0], event[1]);
            System.out.println("    - Event Added in DBE " + doubleBookingEvents + " : " + event[0] + " -> " + event[1]);

        }

        // 5. Otherwise add it in singleBooking map
        singleBookingEvents.put(startTime, endTime);
        System.out.println("    - Event Added in SBE " + singleBookingEvents + " : " + startTime + " -> " + endTime);
        
        return true;
    }


    public static void main (String[] args){
        
        MyCalendarTwo solution = new MyCalendarTwo();

        // First Example
        System.out.println("Final Result : ");
        System.out.println("  1st Iteration : " + solution.book(10, 25));  // Output: true
        System.out.println("  2nd Iteration : " + solution.book(15, 27));  // Output: true
        System.out.println("  3rd Iteration : " + solution.book(24, 35));  // Output: true
        // System.out.println("  4th Iteration : " + solution.book(5, 15));  // Output: false
        // System.out.println("  5th Iteration : " + solution.book(5, 10));  // Output: true
        // System.out.println("  6th Iteration : " + solution.book(25, 55));  // Output: true

        // int[][] bookings = {
        //     {51, 58}, {77, 85}, {35, 44}, {53, 61}, {86, 93}, {55, 61}, {43, 50},
        //     {64, 69}, {76, 82}, {98, 100}, {35, 40}, {25, 32}, {8, 17}, {37, 43},
        //     {53, 60}, {86, 91}, {97, 100}, {37, 43}, {41, 50}, {83, 92}, {66, 75},
        //     {42, 48}, {55, 64}, {37, 46}, {92, 97}, {69, 76}, {85, 94}, {60, 66},
        //     {27, 34}, {36, 44}, {32, 38}, {56, 62}, {93, 99}, {11, 18}, {21, 30},
        //     {81, 89}, {18, 26}, {81, 90}, {91, 96}, {43, 49}, {3, 12}, {97, 100},
        //     {72, 80}, {15, 23}, {63, 70}, {8, 16}, {1, 6}, {16, 24}, {45, 54},
        //     {3, 9}, {30, 36}, {29, 35}, {41, 48}, {21, 26}, {79, 87}, {27, 32},
        //     {88, 96}, {47, 55}, {71, 76}, {32, 40}, {68, 74}, {51, 59}, {44, 50},
        //     {65, 71}, {83, 90}, {86, 94}, {48, 57}, {26, 32}, {27, 32}, {78, 83},
        //     {27, 35}, {19, 24}, {26, 31}, {67, 75}, {87, 92}, {6, 15}, {37, 44},
        //     {62, 68}, {13, 18}, {41, 46}
        // };

        // int i = 0;

        // for (int[] booking : bookings) {

        //     boolean result = solution.book(booking[0], booking[1]);
        //     System.out.print("  " + i + " Iteration : " + result + "\n");

        //     i++;
        // }
        
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
 * - Used Arrays.deepToString to get list of array 
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