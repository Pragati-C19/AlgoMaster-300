import java.util.*;

public class FindMin {
    
    public int findMin(int[] nums) {
        
        int first = 0;
        int last = nums.length - 1;

        while (first < last) {
            
            int mid = (first + last) / 2;

            if( nums[mid] >= nums[first] ){
                first = mid + 1;
            }
            else{
                last = mid;
            }
        }

        return nums[first];
    }

    public static void main(String[] args){
        FindMin solution = new FindMin();

        int[] nums1 = {3,4,5,1,2};
        int[] nums2 = {4,5,6,7,0,1,2};
        int[] nums3 = {11,13,15,17};

        System.out.println("Result 1: " + solution.findMin(nums1) + "\n");
        System.out.println("Result 2: " + solution.findMin(nums2) + "\n");
        System.out.println("Result 3: " + solution.findMin(nums3));
    }

}


/*
 * 
 * Intuitions : 
 * 
 * 1. Find Minumun from rotated array
 * 2. Use Binary search
 * 3. Find the mid element
 * 4. Compare the mid element with the first and last element
 * 5. if first <= mid then first = mid + 1
 * 6. else last = mid 
 *  
 *  
 * Pattern :
 * 
 * 1. declare first, last
 * 2. use while loop till first < last
 * 3. find mid
 * 4. compare mid with first and last
 * 5. update first and last
 * 6. return nums[first]
 * 
 * Pseudo Code :
 * 
 * 
 * function findMin(nums){
 *  
 *      first = 0
 *      last = nums.length - 1
 * 
 *      while (first < last){
 *      
 *          mid = (first + last) / 2
 *          
 *          if( mid >= first ){
 *              first = mid + 1    
 *          }
 *          else {
 *              last = mid
 *          }
 *      }
 *      
 *      retuh nums[first]
 * }
 * 
 */
