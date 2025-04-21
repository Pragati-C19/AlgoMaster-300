import java.util.*;

public class StockPrice {

    // Globally declare Variables
    TreeMap<Integer, Integer> stockPriceMap;
    // int latestTimeStamp;

    public StockPrice() {
        
        // Assigning value to global variables
        stockPriceMap = new TreeMap<>();
        // latestTimeStamp = 0;

        System.out.println("Starting the Iteration...");

    }
    
    public void update(int timestamp, int price) {
        
        // latestTimeStamp = timestamp;
        // System.out.println("    Latest Timestamp : " + latestTimeStamp);

        stockPriceMap.put(timestamp, price);
        System.out.println("    Stock Price Map after updated : " + stockPriceMap);
        
        return;
    }
    
    public int current() {
        
        // int latestPrice = stockPriceMap.get(latestTimeStamp);
        // System.out.println("    Latest Price : " + latestPrice);

        int latestKey = stockPriceMap.lastKey();
        System.out.println("    Latest Key : " + latestKey);

        int latestPrice = stockPriceMap.get(latestKey);
        System.out.println("    Latest Price : " + latestPrice);

        return latestPrice;
    }
    
    public int maximum() {
        
        int maxPrice = Integer.MIN_VALUE;

        for(Map.Entry<Integer, Integer> stock : stockPriceMap.entrySet()){
            
            int currPrice = stock.getValue();
            System.out.println("    [Max] Current Checking Price : " + currPrice);

            maxPrice =  Math.max(maxPrice, currPrice);
        }

        System.out.println("    Maximum Price : " + maxPrice);
        return maxPrice;
    }
    
    public int minimum() {
        
        int minPrice = Integer.MAX_VALUE;

        for(Map.Entry<Integer, Integer> stock : stockPriceMap.entrySet()){
            
            int currPrice = stock.getValue();
            System.out.println("    [Min] Current Checking Price : " + currPrice);

            minPrice =  Math.min(minPrice, currPrice);
        }

        System.out.println("    Minimum Price : " + minPrice);
        return minPrice;
    }

    public static void main (String[] args){
        
        StockPrice solution = new StockPrice();

        // First Example
        System.out.println("Final Result : ");

        solution.update(1, 10);
        System.out.println("  1st Iteration : ");  
        
        solution.update(2, 5);
        System.out.println("  2nd Iteration : ");
          
        System.out.println("  3rd Iteration : " + solution.current());      // output : 5
        System.out.println("  4th Iteration : " + solution.maximum());      // output : 10
        
        solution.update(1, 3);
        System.out.println("  5th Iteration : ");      
        System.out.println("  6th Iteration : " + solution.maximum());      // output : 5
        
        solution.update(4, 2);
        System.out.println("  5th Iteration : ");      
        System.out.println("  8th Iteration : " + solution.minimum());      // output : 2

    }
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
 *      - I think lets create a Global variable latestTimestamp 
 *      - this latestTimestamp will get value from update and store it
 *      - then in current will take value against that latestTimeStamp key
 *      -> Getting issue in this 
 *      -> I think Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
 *      -> I think above line means not latest value or timestamp added 
 *      -> It means Last record with biggest Timestamp..
 *      -> After checking the testcase that has wrong ans I think of this
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
 * int latestTimeStamp
 * 
 * StockPrice () {
 *      -> stockPriceMap = new TreeMap
 *      -> latestTimeStamp = 0
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
 *      latestTimeStamp = timestamp
 * }
 * 
 * 
 * int current() {
 *      return stockPriceMap.getKey(timestamp)
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