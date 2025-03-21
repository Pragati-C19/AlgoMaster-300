import java.util.*;

public class SortColors {
    
}


/*
 * 
 * Intuitions :
 * 
 * 1. red = 0 , white = 1, blue = 2
 * 2. u can't use sort function so use swapping
 * 
 * Pattern  : 
 * 
 * 1. see u have 3 numbers so check middle number = 1 
 * 2. if it's 0 swap(red and white)
 * 3. if it's 2 swap(blue and white)
 * 4. I'm not using linkelist just giving name like that for my preference
 * 5. and I'm using white color or mid as curr.. 
 * 
 * Pseudo Code :
 * 
 * fn sortColor(nums) :
 * 
 *      int n = nums.length;
 *      int prev = 0;
 *      int curr = 0;
 *      int next = n - 1;
 * 
 *      for(int i = 0; i < n; i++ ){
 *          
 *          if(nums[curr] == 0){
 *              swap(curr, prev);
 *              temp = nums[prev];
 *              nums[prev] = nums[curr];
 *              nums[curr] = temp;
 *              prev++;
 *              curr++;
 *          }
 *          else if(nums[curr] == 1){
 *              curr++;
 *          }
 *          else {
 *              // nums[curr] == 2
 *              swap(curr, high)
 *              temp = nums[high];
 *              nums[high] = nums[curr];
 *              nums[curr] = temp;
 *              high++;
 *              curr++;
 *          }
 *      }
 * 
 *      return nums;
 */
