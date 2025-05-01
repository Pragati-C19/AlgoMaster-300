import java.util.*;

public class TimeMap {

    // Custom Struct to easily get value and timestamp from list
    private static class TimeValue{

        String value;
        int timestamp;

        TimeValue(String value, int timestamp){
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    // Globally Declare variable 
    Map<String, List<TimeValue>> timeMap;

    public TimeMap() {
        
        // Assign initial value
        timeMap = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        
        // If key is not in hashmap add one with empty value
        if (!timeMap.containsKey(key)) {
            timeMap.put(key, new ArrayList<>());
        }

        // add value in for that key now -> u can add multiple values to one key now
        timeMap.get(key).add(new TimeValue(value, timestamp));

        return;
    }
    
    public String get(String key, int timestamp) {
        
        return key;
    }

    public static void main(String[] args){

        TimeMap solution = new TimeMap();

        System.out.println("Final Result : ");
        System.out.println("  1st Iteration"); 
        solution.set("foo", "bar", 1);
        
        System.out.println("  2nd Iteration : " + solution.get("foo", 1));    // bar
        System.out.println("  3rd Iteration : " + solution.get("foo", 3));    // bar

        System.out.println("  4th Iteration"); 
        solution.set("foo", "bar2", 4);

        System.out.println("  6th Iteration : " + solution.get("foo", 4));    // bar2
        System.out.println("  7th Iteration : " + solution.get("foo", 5));    // bar2

    }
}

/*
 * 
 * Data structure used is Hashmap (to store key and list) + Binary Search (to get timestamp with less time complexity)
 * 
 * Intuitions :
 * 
 * 1. need to implement timeMap
 * 2. void set(key, value, timestamp) -> it stores key and value at the given timestamp
 * 3. String get(key, timestamp) -> 
 *      Returns a value such that set was called previously, with timestamp_prev <= timestamp. 
 *      If there are multiple such values, it returns the value associated with the largest timestamp_prev. 
 *      If there are no values, it returns "".
 * 4. above thing says.. 
 *      jr key chya against to timestamp bhetla tr tyachi value return karaychi
 *      jr key chya against to timestamp nahi bhetla tr immediate less timestamp chi value takaychi
 *      jr key ch nasel hashmap madhe tr retuun ""
 * 5. first thing in mind is use hashmap to store key and value 
 * 6. confusions 
 *      where should I store timestamp for that key?
 *          - store it in hashmap just need to create custom map for storing 3 things in map
 *     
 * Pattern :
 * 
 * 1. create a custom TimeValue class to make this as one object to store in hashmap
 *      - String value, int timestamp
 *      - this.value = value, this.timestamp = timestamp
 * 
 * 2. Globally declare a hashmap<String, List<TimeValue>>
 * 3. TimeMap()
 *      - hashmap = new hashmap
 * 
 * 4. set(key, value, timestamp)
 *      //? Learn this method to write multiple things in hashmap
 *      - if ur hashmap don't have that key then add that key in hashmap with value as empty arrayList, initially 
 *          if(!hashmap.containsKey(key)) -> then hashmap.put(key, new ArraysList<>())
 *      - will fetch that value arrayList from the key : getting array list
 *          List<TimeValue> timestampedValue = hashmap.get(key)
 *      - and add new value and timestamp in it like we add in ArrayList
 *          timestampedValue.add(new TimeValue(timestamp, value))
 *      - varchi line ekdam simple ahe samjun ghe nit bcoz que is sooo simple... ani tula kalal hot ki hashmap madhe key sobt 2 value add karaychay don't just leave it 
 *          bagh mala hash map madhe key and value store karaychi aste right?..
 *          nehmi asa hot I used to store only Integer, String like that but kadhich int[] or ArraysList nahi add keliy me
 *          tich aj kartoy apan..
 *          jr key nasel tya hashmap madhe tr apan ti key with empty array add kartoy
 *          ata empty array kelay create tr aplyala value dyavi lagel.. ani tula mahitiye que madhe ekach key against khup sare values hotya
 *          tyamul apan kay kartoy adhi key chi value ghetoy... with hashmap.get(key)
 *          and then tya value madhe add kartoy new timestamp and key... hashmap.get(key).add(new TimeValue(timestamp, value))
 *          samjal?.. nasel samjal tr check kr ha video https://youtu.be/OWKIm52pUC4?si=uMNr3UtHt5Z0YIdh examples ni samjel ajun nit
 * 
 * 5. get(key, timestamp)
 *      - if(!hashmap.containsKey(key))  -> return ""
 *      - Take value list from hashmap.get(key)
 *      - use normal binary search on that list as we do normally
 *            left, right, mid 
 *            if list.get(mid).timestamp <= target
 *                  result = list.get(mid).value
 *                  left = mid + 1
 *            else 
 *                  right = mid - 1 
 *      - return result
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 */
