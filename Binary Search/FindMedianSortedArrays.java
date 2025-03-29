import java.util.*;

public class FindMedianSortedArrays {
    
}


/*
 * 
 * Intuitions : 
 * 
 * 1. We need to merge those two array
 * 2. Create a whole array then find the middle of it
 * 3. to merging both array we can use divide and conquer
 * 4. to get mid from that merge array we can use below thing
 * - sum of all nums from array / n 
 * - left = 0, right = n-1, mid = (left + right) / 2
 * 
 * 
 * How To merge both array and create a new array?
 * Where to use Binary search in it?
 * 
 * 
 * Pattern : 
 * 
 * 1. let's use normal method first brute force to merge both arrays
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 1. merge two array with brute force
 * 
 * function int[] mergeArr(nums1, nums2, n){
 *      
 *      int[] mergeArr = new int[n];
 *      int i = 0, j = 0, k = 0
 * 
 *      while(i < n1 || j < n2) {
 *          if(nums1[i] < nums2[j]){
 *              mergeArr[k] = nums1[i]
 *              k++;
 *              i++; 
 *          }
 *          else {
 *              mergeArr[k] = nums2[j]
 *              k++;
 *              j++;
 *          }
 *      }
 *      
 *      return mergeArr[]
 * }
 * 
 * function findMedianSoertedArrays(nums1, nums2) {
 *      
 *      int n1 = nums1.length
 *      int n2 = nums2.length
 *      int sum = 0
 *      int n = n1 + n2
 *      
 *      int[] mergedArr = mergeArr(nums1, nums2, n)
 *      
 *      while(int i < n){
 *          sum = sum + mergedArr[i]
 *      }
 *      
 *      double median = sum / n
 * 
 *      return median;
 * }
 * 
 */
