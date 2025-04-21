import java.util.*;

public class StockPrice {

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. given a stream of records
 * 2. each record contains a timestamp and curresponsing price
 * 3. Records are not in order
 * 4. Some recordrs are wrong ->
 * don't understand this line -> Another record with the same timestamp may
 * appear later in the stream correcting the price of the previous wrong record.
 * 5. Conditions 
 * -> StockPrice() -> Initialize the object
 * -> void update(int timestamp, int price) -> update price of the stock
 * -> int current() -> return latest price of the stock
 * -> int maximum() -> return the maximum price of the stock
 * -> int minimum() -> return minimum price of stock
 * 
 * 
 * 
 * Pattern :
 * 
 * 1. Globally declare a map which has timestamp as a key and price as a value
 * 2. will use TreeMap here bcoz we need ordered list by key
 * 3. StockPrice() -
 *      - Assign value to Globally Declare map 
 * 4. void update(int timestamp, int price) -
 *      - if(map.containsKey) -> then update the value of that key
 *      - else add key and value in map
 * 5. int current() -
 *      - So far I know I have to return value of key 
 *      - but I need to think abt how to get latest key
 * 6. int maximum() -
 *      - will use for loop here till we check every value in map
 *      - check max = Math.max(max, map.get(key)) 
 *      - We need to check maximum between values.. not with key 
 * 7. int minimum() -
 *      - will use for loop here till we check every value in map
 *      - check min = Math.min(min, map.get(key))
 *      - We need to check minimum between values.. not with key
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * // Gloabally declare map
 * Map<Integer, Integer> stockPriceMap
 * 
 * StockPrice () {
 *      -> stockPriceMap = new TreeMap
 * }
 * 
 * void update(int timestamp, int price) {
 * 
 *      if(stockPriceMap.containsKey(timestamp))
 *          stockPriceMap.put(timestamp, price)
 * 
 *      -> I think below line is enough I don't need to check if bcoz Map uses unique key
 *      -> So if it detacts key already exist it will just update it's value automatically
 *      stockPriceMap.put(timestamp, price)
 * 
 * }
 * 
 * 
 * int current() {
 * 
 * 
 * }
 * 
 * 
 * int maximum() {
 * 
 *      int max = Integer.Min_Value
 * 
 *      for(all stock from StokPrice)
 *          currPrice = stockPriceMap.get(stock)
 *          max = Math.max(max, currPrice)
 *      
 *      return max
 * }
 * 
 * int minimum() {
 * 
 *      int min = Integer.Max_Value
 * 
 *      for(all stock from StokPrice)
 *          currPrice = stockPriceMap.get(stock)
 *          min = Math.min(min, currPrice)
 *      
 *      return min
 * }
 * 
 * 
 */