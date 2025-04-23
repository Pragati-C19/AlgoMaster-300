import java.util.*;

public class FindMaximumXOR {
    
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