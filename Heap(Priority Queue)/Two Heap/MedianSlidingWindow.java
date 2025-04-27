import java.util.*;

public class MedianSlidingWindow {

}

/*
 * 
 * 
 * Intuitions :
 * 
 * 1. I need to find median
 * 2. need to use sliding window as it's already written in problem and also it
 * says we need to check subarray of size k
 * 3. I think the pattern is simple sliding window + two heap
 * 
 * Pattern :
 * 
 * As per my knowledge so far.. I saw the discussion and it's soo overwhelming
 * that they say heap dosent work treemap does but in que topic that's written 
 * 
 * 1. will create basi 2 function to find median
 *      - add array -> it will add num to maxHeap and min Heap
 *           - will use for ( i = 0 to updatedArray.length)
 *               - then add updatedArray[i] in max heap
 *               - after that add max's top in min heap
 *               - if (max < min) -> add in min's top in max
 *           - median = call helper function of findMeadian
 *           - return median
 *      - find median -> to get median from heaps
 *           - max > min -> top of maxHeap
 *           - else -> max.top + min.top / 2
 * 2. main function ->
 *      - I need a result to store result
 *      - need a starting Window variable which will tell window is strating from which length
 *      - need an updatedArray to store only k length of nums
 *              for( j = startingWindow to k) -> updatedArray.add(nums[j])
 *      - will find median by calling a helper function of add(updatedArray)
 *      - result[i] = median
 * 
 * 3. here is only one bug in my wholee solution which is me window kashi pudhe ghenar?.
 * - as in me i = startingWindow to k ni karte tr ahe create a window.. 
 * - but me kaskay to parat loop madhe annar ani pudhe karnar?.. 
 * - ajun ek mala he median adding that median to result he kontya tri for loop madhe lagel without that it will only give one ans
 * 
 * 
 * - let's use pointer to solve this issue..
 * start = 0 
 * end = k - 1
 * 
 *  Like, initially it is from [0 to k-1],
 *  next time from [1 to k],
 *  next time from [2 to k+1],
 *  it seems like moving both start and end by 1 at a same time
 *  or removing one element from start and adding one element at end
 * 
 *              
 * 
 * Pseudo Code :
 * 
 * 
 * double[] medianSlidingWindow(int[] nums){
 * 
 *      List<Integer> currMedian = new ArrayList
 *      
 *      start = 0;
 *      end = k - 1;
 * 
 *      for(i = 0 to nums.length){
 * 
 *          List<Integer> updatedArray = getSlot(start, end, nums);
 * 
 *          double median = addNums(updatedArray)
 *          currMedian.add(median)
 * 
 *          start++;
 *          end++;
 *    
 *      }
 *      
 *      double[] result = new double[currMedian.size()]
 *      for(i = 0 to currMedian.size()){
 *          result[i] = currMedian.get(i)
 *      } 
 * 
 *      return result;
 * }
 * 
 * 
 * List<Integer> getSlot(start, end, nums) {
 * 
 *      List<Integer> updatedArray = new Array
 *      
 *      for(i = start to end){
 *          updatedArray.add(nums[i]);
 *      }
 * 
 *      return updatedArray;
 * }
 * 
 * double addNums(updatedArray){
 * 
 *      for(i = 0 to updatedArray.length){
 * 
 *          maxHeap.add(updatedArray[i]);
 *          minHeap.add(maxHeap.poll());
 * 
 *          if(maxHeap < minHeap) -> maxHeap.add(minHeap.poll());
 *      }
 * 
 *      double median = findMedian()
 * 
 *      return median
 * }
 * 
 * double findMedian(){
 * 
 *      if(maxHeap > minHeap) -> return maxHeap.peek
 *      
 *      else -> 
 *          maxHeap.peek() + minHeap.peek() / 2
 * }
 * 
 * 
 */