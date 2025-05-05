import java.util.*;

public class Jump {
    
    public int jump(int[] nums) {
        
        int n = nums.length; 
        int maxFarJump = 0;
        int jumpCount = 0;

        // Base Case :
        if (n == 1) {
            return jumpCount;
        }

        for (int i = 0; i < n ; i++) {
            
            int currMaxFarJump = i + nums[i];
            System.out.println("    We can jump from " + i + " to " + currMaxFarJump);

            maxFarJump = Math.max(maxFarJump, currMaxFarJump);
            System.out.println("       -> Max far till then is : " + maxFarJump);

            jumpCount++;
            System.out.println("        -> current Jump Count is : " + jumpCount);
            
            if (maxFarJump == n - 1) {

                System.out.println("        -> " + maxFarJump + " is reaching to end : " + jumpCount);
                return jumpCount;
            }
        }

        return jumpCount;
    }

    public static void main(String[] args){

        Jump solution = new Jump();

        int[] nums1 = {2,3,1,1,4};
        System.out.println("Result 1 : " + solution.jump(nums1) + "\n");     // 2

        int[] nums2 = {2,3,0,1,4};
        System.out.println("Result 2 : " + solution.jump(nums2) + "\n");     // 2

        int[] nums3 = {3,2,1,4,4};
        System.out.println("Result 3 : " + solution.jump(nums3) + "\n");     // 2

        int[] nums4 = {0};
        System.out.println("Result 4 : " + solution.jump(nums4) + "\n");     // true

        int[] nums5 = {0, 2, 3};
        System.out.println("Result 5 : " + solution.jump(nums5) + "\n");     // false

    }
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. We have given an array 
 * 2. each num in that array represent the maximum jumps we can make
 * 3. will jump till the end of array 
 * 4. goal is to find minimum number of jumps we need to reach to the end 
 * 5. there are all test cases who can reach to end just find the minimum one
 * 
 *  
 * Pattern :
 * 
 * 1. I think I used the same pattern in canJump que
 * 2. Trace the example
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
 *      - ithech return karun dyaych.. karan yachyanantr me jitkya pn jump gheil tya saglya max hotil right?
 *      - so maybe goal can be first time madhech maxFar == nums.length zala ki count return karaycha
 * 
 *      No need to check below index after that
 *      - if we jump at index 2 -> 2 + nums[2] = 2 + 1 = 3 -> means we can jump till index 3 only
 *      - if we jump at index 3 -> 3 + nums[3] = 3 + 1 = 4 -> means we can go to index 4 only -> this is the end of array
 *      - that means we are reaching to the end with both method
 * 
 * Approach 1
 * 
 * 1. Declare a maxFarJump -> to check how far we can go from the index
 * 2. jumpCount -> to count how many jumps we did
 * 2. use for(i = 0 to nums.length)
 *      - check currMaxJump = i + nums[i]
 *      - get maxFarJump 
 *      - jumpCount++;
 *      - check if (maxFarJump == arr.length)
 *          return count;
 * 
 * Approach 2 -> let's change a code little bit
 * 
 * 1. Declare a maxFarJump -> to check how far we can go from the index
 * 2. jumpCount -> to count how many jumps we did
 * 3. for loop la jr me start every time 0, 1, 2 kartey tr te maxFar count karel.. 
 *      - but for problems like [3,2,1,4,4] 
 *      - to 0 index pasun 3rd index vr janya peshya 1 index cha pn jump count gheil 2 cha pn gheil mg 3 cha gheil.. 
 *      - so mala asa vatat ki i = maxFarJump asayla hav.. 
 *      - jr ti jump length pekshya jast hot asel tr for loop chya baher count++ karu
 *      - or for loop nums.length - 1 parynt ch chalau ajun better
 *      - or we can say at if ( maxFarJump >= arr.length)
 * 2. use for(i = maxFarJump to nums.length)
 *      - check currMaxJump = i + nums[i]
 *      - get maxFarJump 
 *      - jumpCount++;
 *      - check if (maxFarJump >= arr.length)
 *          return count;
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 */