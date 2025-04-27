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
 * - 
 *              
 * 
 * Pseudo Code :
 * 
 * 
 * medianSlidingWindow(int[] nums){
 * 
 *      double[] result = new int[]
 *      
 *      startingWindow = 0
 * 
 *      for(i = 0 to nums.length){
 *          
 * 
 *          if(i >= k) {
 *              median = addNums(updatedArray)
 *              result[i] = median
 *          }
 *          
 *      }
 *           
 * 
 *      
 *      
 * 
 * }
 * 
 * 
 * getSlot(startingWindow, k) {
 * 
 *      List<Integer> updatedArray = new Array
 *      
 *      for(i = startingWindow to k ){
 *          updatedArray.add(nums[startingWindow]);
 *      }
 * 
 *      return updatedArray;
 * }
 * 
 */