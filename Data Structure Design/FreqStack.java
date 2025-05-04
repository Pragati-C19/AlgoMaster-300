import java.util.*;

public class FreqStack {
    
}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. we need to create a frequncy stack
 * 2. void push(int val) pushes an integer val onto the top of the stack.
 * 3. int pop() removes and returns the most frequent element in the stack.
 * 4. what is most freq element means?
 *      int[] nums = {1, 3, 2, 3, 4, 3, 5};
 *      Frequencies:
 *          1 → 1 time
 *          2 → 1 time
 *          3 → 3 times ✅
 *          4 → 1 time
 *          5 → 1 time
 *      Most frequent element = 3 (since it appears the most times)
 * 
 * 
 * 
 * Pattern :
 * 
 * 
 * 1st Approach with one Hashmap and stack
 * 
 * 1. it's abt frequncy so my first thought is to use HashMap to store freq of that val
 * 2. void Push(int val)
 *      - will push val in stack
 *      - then check karel if map.contains(val)
 *          - if yes then increase it's frequency
 *          - if no then add val in map with freq 1
 * 3. int pop()
 *      - get the high freq element from map
 *      - how can you get it?
 *      - will use for loop for map
 *          for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
 *              if (entry.getValue() > maxFreq) {
 *                  maxFreq = entry.getValue();
 *                  element = entry.getKey();
 *              }
 *	        }

 *      - I thought of using max directly but then getting key against that maxFreqVal will be hard
 *              maxFreqVal = max(maxFreqVal, entry.getValue())
 *      
 *      - After getting max will it's time to pop
 *          means will decrese it's freq count 
 *          if count hits to 0 will remove that val from hashmap
 * 
 * 
 * 2nd Approach with 2 Hashmaps
 * 
 * 1. Global Variables are
 *      freqMap -> It will store freq of every value here
 *      groupValByFreqMap -> It will stores freq as key and list of nums which has that freq as value
 *      maxFreqCount -> it will track maximum count of frequncy so far
 * 
 * 2. push(val)
 *      - Take freq from freqMap for that val
 *      - I'm finding freq here if exist or adding by default 1 if not exist
 *          freqOfVal = freqMap.getOrDefault(val, 0) + 1
 *      - adding that freq to freqMap
 *          freqMap.put(val, freqOfVal)
 *      - check if that group of freq exist in groupValByFreqMap
 *          if(!map.contains(freqOfVal))
 *              map.put(freqOfVal, new Stack)
 *          map.get(freqOfVal).push(val)
 *      - check if this freqOfVal is greater than maxFreqCount
 *          if yes -> then change maxFreqCount to freqOfVal
 * 
 * 
 * 3. pop()
 *      - pop will be easier now I just need to pop eleemnt from stack which has high Freq
 *      - How to find highest freqency?
 *          for (Map.Entry<Integer, Integer> entry : groupValByFreqMap.entrySet()) {
 *              if (entry.getKey() > maxFreqCount) {
 *                  maxFreqCount = entry.getKey();
 *              }
 *	        }
 *      - Will store stack for that maxFreq in a variable for better access later
 *          stack = groupValByFreqMap.get(maxFreqCount)
 *      - pop out top element from that stack 
 *          stack.pop()
 *      - decrese the freq from freqMap
 *          freqMap.put(val, freqMap.get(val) - 1);
 *      - if(freqMap.get(val) == 0)
 *          freqMap.remove(val)
 *      - jr group map madhe pn stack empty zala tr mala delete karav lagel ti key
 *      - If the stack for maxFreqCount becomes empty, remove stack from map and reduce maxFreqCount
 *          if (groupValByFreqMap.get(maxFreqCount).isEmpty()) 
 *              groupValByFreqMap.remove(maxFreqCount);   
 *              maxFreqCount--;
 *         
 *  
 * 
 * Pseudo Code :
 * 
 * 
 * 
 * 
 */