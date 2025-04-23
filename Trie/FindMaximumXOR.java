import java.util.*;

public class FindMaximumXOR {
    

    public int findMaximumXOR(int[] nums) {
        
        int maxXOR = Integer.MIN_VALUE;
        
        int currXOR = nums[0];

        int index = 1;

        while (index < nums.length) {
            
            currXOR ^= nums[index];
            System.out.println("        Current XOR at index " + index + " : " + currXOR);
            
            maxXOR = Math.max(maxXOR, currXOR);
            System.out.println("        Max XOR at index " + index + " : " + maxXOR);

            index++;
        }

        return maxXOR;
    }

    public static void main(String[] args){

        FindMaximumXOR solution = new FindMaximumXOR();

        int[] nums1 = {3,10,5,25,2,8};
        System.out.println("-> Result 1 : " + solution.findMaximumXOR(nums1) + "\n");

        int[] nums2 = {14,70,53,83,49,91,36,80,92,51,66,70};
        System.out.println("-> Result 2 : " + solution.findMaximumXOR(nums2));
    }

}


/*
 * 
 * Intuitions :
 * 
 * 1. Integer array is given
 * 2. Return the maximum result of nums[i] XOR nums[j] 
 * 3. there is inbuild bitwise operator ^ used for getting XOR result 
 * 
 * Pattern :
 * 
 * 1. asssign maxXOR to min value
 * 2. for(i = 0 to nums.length)
 *      for(j = 1 to nums.length)
 *          -> currXOR = nums[i] ^ nums[j]
 *          -> maxXOR = Math.max(maxXOR, currXOR)
 * 3. return maxXOR
 * 4. If I get weak time complexity then try using while loop and currXOR = nums[i] ^ nums[i+1]
 * 
 * Pseudo Code : 
 * 
 * function findMaximumXOR (nums){
 * 
 *      int maxXOR = 0;
 * 
 *      int index = 0;
 *      
 *      while(index < nums.length){
 *          
 *          currXOR = nums[i] ^ nums[i+1];
 * 
 *          maxXOR = math.max(maxXOR, currXOR)
 *      }
 * 
 *      return maxXOR
 * }
 * 
 */