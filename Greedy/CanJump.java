import java.util.*;

public class CanJump {
    
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
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 */