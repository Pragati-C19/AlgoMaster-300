import java.util.*;

public class FurthestBuilding {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        
        return heights.length;
    }

    public static void main(String[] args){

        FurthestBuilding solutions = new FurthestBuilding();

        int[] heights1 = {4,2,7,6,9,14,12};
        int bricks1 = 5;
        int ladders1 = 1;
        System.out.println("-> Result 1 : " + solutions.furthestBuilding(heights1, bricks1, ladders1) + "\n");

        int[] heights2 = {4,12,2,7,3,18,20,3,19};
        int bricks2 = 10;
        int ladders2 = 2;
        System.out.println("-> Result 2 : " + solutions.furthestBuilding(heights2, bricks2, ladders2) + "\n");

        int[] heights3 = {14,3,19,3};
        int bricks3 = 17;
        int ladders3 = 0;
        System.out.println("-> Result 3 : " + solutions.furthestBuilding(heights3, bricks3, ladders3) + "\n");

        int[] heights4 = {1, 2, 100, 200, 201};
        int bricks4 = 99;
        int ladders4 = 1;
        System.out.println("-> Result 4 : " + solutions.furthestBuilding(heights4, bricks4, ladders4) + "\n");

    }

}


/*
 * 
 * Intuitions :
 * 
 * 1. You're climbing buildings. Every time you go up, you need bricks or ladders.
 * 2. Ladders can be used for any height — big or small.
 * 3. Bricks are limited and used based on the size of the jump.
 * 4. We want to maximize how far you can go.
 * 
 * 
 * Brute force Approach :
 * 
 * - check if arr[i] <= arr[i - 1] -> if yes then no ladder or bricks needed just shift to next building
 * - if arr[i] > arr[i-1] 
 *      -> then we need to check abt how many bricks need do we have that much bricks and if we have ladder use it
 *      -> sequence dosen't matter for using bricks or ladder
 * 
 * - check bricksNeeded = arr[i] - arr[i-1]
 * - if (bricksNeed < brickRemain) -> then add bricks.. bricksRemain = bricksRemain - bricksNeeded go to next building
 * - if (bricksNeed > bricksRemain) -> if ladder > 0 -> then add ladder.. ladder-- go to next building
 *      - this if will give me output if ladder == 0 then return index of array
 * 
 * 
 * With priority queue approach :
 * 
 * 1. Strategy: "Save ladders for the biggest jumps"
 *      - using bricks for long jumps can go till minimum number of bricks heights = [1, 2, 100, 200, 201], bricks = 99, ladders = 1
 * 2. Track all upward jumps in a min-heap.
 * 3. The heap will store the diff between height of each jump > 0
 * 4. If we’ve made more upward jumps than we have ladders, we say:
 *      - “Oops, I can’t cover all these with ladders. Let me use bricks for the smallest jump instead.”
 *      - so will use minHeap.poll() -> this will give smallest difference so far
 * 5. If bricks are less than 0 then return index
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * function furthestBuilding(heights, bricks, ladder){
 * 
 *      -> declare a minheap with priority queue
 * 
 *      -> for loop ( index = 0 to heights.length)
 *              diffInHeights = heights[i + 1] - height[i]   // 1st index - 0th index
 *                  
 *              if(diffInHeights > 0) 
 *                    add it in minHeap
 *                      
 *                    // we are adding ladder by default.. if we see minHeap size getting higher than ladder will assign bricks to the smallest element
 *                    if(minHeap.size > ladder)
 *                          bricks = bricks - minHeap.poll()
 * 
 *                    if(bricks < 0) 
 *                          return index 
 *                              
 *      -> return heights.length
 * 
 * }
 * 
 * 
 * 
 * 
 */