import java.util.*;

public class TimeMap {

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
