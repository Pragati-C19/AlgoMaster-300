import java.util.*;

public class KSmallestPairs {
    
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        
    }

    public static void main(String[] args){
        
        KSmallestPairs solution = new KSmallestPairs();

        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        int k1 = 3;
        System.out.println("Result1 : " + solution.kSmallestPairs(nums1, nums2, k1) + "\n");

        int[] nums3 = {1,1,2};
        int[] nums4 = {1,2,3};
        int k2 = 2;
        System.out.println("Result2 : " + solution.kSmallestPairs(nums3, nums4, k2) + "\n");

    }

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
 * First Approach - Brute Force 
 *      Problems with this approach 
 *          - Time Complexity
 *          - Unnecessary big Heap Size -> if u think closely u are adding alll sums in that heap
 *              it can cause overflow.. try to add only k elements in heap
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
 * 
 * Second Appraoch :
 * 
 * 1. 
 * 
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