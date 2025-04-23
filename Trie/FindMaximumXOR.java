import java.util.*;

public class FindMaximumXOR {
    

    public int findMaximumXOR(int[] nums) {
        
        int maxXOR = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            
            for (int j = i; j < nums.length; j++) {
                
                int currXOR = nums[i] ^ nums[j];
                System.out.println("        Current XOR for nums " + nums[i] + " ^ " + nums[j] + " = " + currXOR);
                
                maxXOR = Math.max(maxXOR, currXOR);
            }

            System.out.println("    -> Max XOR for num " + nums[i] + " : " + maxXOR);
    
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
 * 
 * //?  We use hashset and xor or bitwise operations to solve this problem.. check out below link for more details
 *      -> https://chatgpt.com/share/68091e91-fdcc-8002-b67e-2385c7d789dc
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
 * 
 * 
 * 
 * 2. Let's do with Trie 
 * 
 * class Solution {
 *     public int findMaximumXOR(int[] nums) {
 *         int maxXOR = 0; // This will store our final max XOR
 *         int mask = 0;   // Used to extract left bits (prefixes) of the numbers
 * 
 *         // Step 1: Loop from bit 31 to bit 0
 *         for (int i = 31; i >= 0; i--) {
 *             mask = mask | (1 << i); // Set the ith bit to 1 in the mask
 * 
 *             // e.g., if i=31 → mask = 100000000...0 (MSB on)
 *             // if i=30 → mask = 110000000...0 (next MSB also on)
 * 
 *             Set<Integer> prefixes = new HashSet<>();
 * 
 *             // Step 2: Collect all prefixes of nums with 'i' bits
 *             for (int num : nums) {
 *                 // `num & mask` keeps only the left i bits
 *                 prefixes.add(num & mask);
 *             }
 * 
 *             // Step 3: Try to "greedily" set the ith bit in result to 1
 *             int candidate = maxXOR | (1 << i);
 * 
 *             // Example: 
 *             // If maxXOR = 00010000 (say bit 4 is on)
 *             // candidate = 00011000 (trying to turn bit i=3 on too)
 * 
 *             for (int prefix : prefixes) {
 *                 // Why this line?
 *                 // If a ^ b = c  →  then a ^ c = b
 *                 // So, if we have two prefixes `a` and `b` such that:
 *                 // a ^ b = candidate, then b = a ^ candidate
 * 
 *                 if (prefixes.contains(prefix ^ candidate)) {
 *                     maxXOR = candidate;
 *                     break;
 *                 }
 *             }
 *         }
 * 
 *         return maxXOR;
 *     }
 * }
 * 
 * 
 * 
 * 
 * 
 * 
 */