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
 */