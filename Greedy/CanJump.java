import java.util.*;

public class CanJump {
    
    public boolean canJump(int[] nums) {
        
        int n = nums.length; 

        // Base Case :
        if (n == 1) {
            return true;
        }

        int maxFarJump = 0;

        for (int i = 0; i < n - 1; i++) {
            
            int currMaxFarJump = i + nums[i];
            System.out.println("    We can jump from " + i + " to " + currMaxFarJump);

            if (currMaxFarJump == maxFarJump) {
                
                System.out.println("        -> maxFar " +  + maxFarJump + " is same as currMax " + currMaxFarJump);
                return false;
            }

            maxFarJump = Math.max(maxFarJump, currMaxFarJump);
            System.out.println("       -> Max far till then is : " + maxFarJump);
            
            if (maxFarJump >= n - 1) {

                System.out.println("        -> maxFar is reaching to end " + maxFarJump);
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args){

        CanJump solution = new CanJump();

        int[] nums1 = {2,3,1,1,4};
        System.out.println("Result 1 : " + solution.canJump(nums1) + "\n");

        int[] nums2 = {3,2,1,0,4};
        System.out.println("Result 2 : " + solution.canJump(nums2) + "\n");

        int[] nums3 = {3,2,1,4,4};
        System.out.println("Result 3 : " + solution.canJump(nums3) + "\n");

        int[] nums4 = {0};
        System.out.println("Result 4 : " + solution.canJump(nums4) + "\n");

        int[] nums5 = {0, 2, 3};
        System.out.println("Result 5 : " + solution.canJump(nums5) + "\n");

    }

}


/*
 * 
 * 
 * Intuitions :
 * 
 * 1. learning greedy or DP is confusing.. 
 * 2. We have given an array 
 * 3. each num in that array represent the maximum jumps we can make
 * 4. goal is to jump till the end of array
 * 
 * 
 * Pattern :
 * 
 * 1. Trace the example
 * 
 *       index      |     0       1       2       3       4
 *  ----------------|---------------------------------------------
 *       Nums[]     |     2       3       1       1       4
 *                  |     |_______|_______|
 *                  |             |_______|_______|_______|
 *                  |                     |_______|
 *                  |                             |_______|
 *              
 *      - we are standing at index 0
 *      - we check how far we can jump -> 0 + nums[0] = 0 + 2 = 2 -> means till index 2 or less
 *      - if we jump at index 1 -> 1 + nums[1] = 1 + 3 = 4 -> means we can jump till index 4 or less  -> this is the end of array length
 *      - if we jump at index 2 -> 2 + nums[2] = 2 + 1 = 3 -> means we can jump till index 3 only
 *      - if we jump at index 3 -> 3 + nums[3] = 3 + 1 = 4 -> means we can go to index 4 only -> this is the end of array
 *      - that means we are reaching to the end with both method
 *
 *  
 * 
 *       index      |     0       1       2       3       4
 *  ----------------|---------------------------------------------
 *       Nums[]     |     3       2       1       0       4
 *                  |     |_______|_______|_______|
 *                  |             |_______|_______|
 *                  |                     |_______|
 *                  |                             
 *              
 *      - we are standing at index 0
 *      - we check how far we can jump -> 0 + nums[0] = 0 + 3 = 3 -> means till index 3 or less
 *      - if we jump at index 1 -> 1 + nums[1] = 1 + 2 = 3 -> means we can jump till index 3 or less
 *      - if we jump at index 2 -> 2 + nums[2] = 2 + 1 = 3 -> means we can jump till index 3 only
 *      - from 3rd index we can go to -> 3 + nums[3] = 3 + 0 = 3 -> nowhere.. we are stuck on index 3
 * 
 * 
 * 1st Approach : I think I'm not using greedy here 
 * 
 * 1. Declare a variable (maxFarJump) to check how far we can go
 * 2. for( i = 0 to arr.length)
 *      - calculate the max jump for current index by i + num[i]
 *      - get maxFarJump by max(maxFarJump, currIndexMaxFarJump)
 *      - if maxFarJump == arr.length -> return true
 * 3. otherwise return false
 * 
 * 
 * 2nd Approach : 
 * 
 * 1. let's do the opposite here instead of checking if maxFarJump >= arr.length 
 * 2. will check if  index > than maxFarJump 
 * 3. mhnje jr suppose index 4 ahe for loop madhe and maza maxFarJump 3 vrch ahe ajun 
 *      that means maybe 3 var 0 hota and ata to pudhe nahi yeu shakat
 * 4. so will return false in that case
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 */