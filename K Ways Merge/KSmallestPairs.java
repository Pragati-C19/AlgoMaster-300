import java.util.*;

public class KSmallestPairs {
    
}


/*
 * 
 * 
 * Intuitions :
 * 
 * 1. Two arrays are given and we need to find k pairs with smalllest sum
 * 2. we can use Heap and forLoop
 * 3. will store all sum with pair in that heap
 * 4. and then pops out the smallest sum from that heap
 * 
 * Pattern :
 * 
 * 1. create a heap to store pair and sum of intergers
 * 2. for(i = 0 to nums1.length)
 *      for(j = 0 to nums2.length)
 *          sum = nums1[i] + nums2[j]
 *          heap.add(new int[]{nums1[i], nums2[j], sum}) 
 * 3. for(i = 0 to k)
 *      result[i][0] = heap.peek()[0]
 *      result[i][1] = heap.peek()[1]
 *      heap.poll()
 * 4. return result
 * 
 * Pseudo Code :
 * 
 * function kSmallestPairs(intervals){
 * 
 *      // Create a heap to store [nums1[i], nums2[j], theirSum]
 *      heap = new pq<>()
 * 
 *      for(i = 0 to nums1.length)
 *          for(j = 0 to nums2.length)
 *              sum = nums1[i] + nums2[j]
 *              heap.add(new int[]{nums1[i], nums2[j], sum}) 
 * 
 *      for(i = 0 to k)
 *          result[i][0] = heap.peek()[0]
 *          result[i][1] = heap.peek()[1]
 *          heap.poll()
 * 
 *      return result
 *     
 * }
 * 
 */