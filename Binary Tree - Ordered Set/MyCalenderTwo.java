import java.util.*;

public class MyCalenderTwo {
    
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