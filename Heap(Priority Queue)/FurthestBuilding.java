import java.util.*;

public class FurthestBuilding {

}


/*
 * 
 * Intuitions :
 * 
 * 1. let's understand this problem with example
 * heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
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
 * Above is a brute force thing.. I don't know yet where we need priority queue
 * 
 * Pattern :
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 */