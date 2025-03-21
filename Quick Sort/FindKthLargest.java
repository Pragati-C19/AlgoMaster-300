import java.util.*;

public class FindKthLargest {
    
}

/*
 * 
 * Intuitions :
 * 
 * 1. my first thought is to sort the array and then take last number as largest one
 * 2. but sorting is not allowed in this que
 * 3. I can use Math.max but it will give me largest num not kth largest
 * 4. Using QuickSelect seems soo lengthy and hard like for that we have to 
 *      - take any random pivot 
 *      - then based on that pivot create smallest and lasrgest partitions
 *      - as we need largest so take largest partion and check Kth element in it
 * 5. Let's use min-heap (smallest number stays on top) :
 *      - Add elements one by one to the heap.
 *      - If the heap size exceeds k, remove the smallest number (pop the top).
 *      - When done, the top of the heap will hold the Kth largest element (because only the largest K numbers remain).
 * 
 * Pattern :
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * function findKthLargest(nums, k):
    # Create a min-heap (smallest element on top)
    heap = empty priority queue
    
    # Add each number to the heap
    for number in nums:
        push number into heap
        
        # If heap size exceeds k, remove the smallest number
        if size of heap > k:
            pop smallest from heap

    # The top of the heap is now the Kth largest number
    return top of heap

 * 
 * 
 */
