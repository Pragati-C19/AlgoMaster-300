import java.util.*;

public class FindPeakElement {

}

/*
 * 
 * Intuitions :
 * 
 * 1. peak element should be greater than it's neighbors
 * 2. A peak isn’t necessarily the global maximum. It’s any local maximum — an
 * element bigger than its immediate neighbors.
 * 3. return that greatest num's index
 * 4. an element is always considered to be strictly greater than a neighbor
 * that is outside the array.
 * 
 * Pattern :
 * 
 * 1.
 * 
 * 
 * Pseudo Code :
 * 
 * 1. Brute Force:
 * 
 * function findPeakElement(nums){
 * 
 * max = 0;
 * 
 * for(int i = 0; i < nums.length; i++){
 * max = Math.max(nums[i], max);
 * }
 * 
 * for(int i = 0; i < nums.length; i++){
 * if(nums[i] == max){
 * return i;
 * }
 * }
 * }
 * 
 * 
 * 2. Binary search:
 * 
 * function findPeakElement(nums){
 * 
 * // 1. Start with two pointers
 * left = 0
 * right = nums.length - 1
 * 
 * // 2. while loop
 * while(left < right){
 * 
 * mid is changging for all iterations
 * calculate mid = (left + right) / 2;
 * 
 * if(nums[mid] > nums[mid+1]){
 * peak could be on the left side (or mid itself), so move right
 * right = mid
 * }
 * else {
 * peak could be on the right side, so move left
 * left = mid + 1;
 * }
 * }
 * 
 * return left (both left and right will converge to the peak)
 * }
 * 
 * 
 * - why we use return left (both left and right will converge to the peak)?
 * 
 * When left == right, the loop stops:
 * -> Both pointers collapse to the same index, which is guaranteed to be a peak!
 * 
 * - Why return left works?
 * Since both left and right point to the same peak index by the end, returning
 * either one is fine. left is more common because it’s more readable — but
 * right would work equally well.
 * 
 */
