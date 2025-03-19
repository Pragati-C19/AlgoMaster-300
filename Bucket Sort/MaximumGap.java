
public class MaximumGap {
    public static int maximumGap(int[] nums) {

        return nums;
    }

    public static void main(String[] args){
        MaximumGap solution = new MaximumGap();

        int[] nums1 = {3, 6, 9, 1};
        int[] nums2 = {10};

        System.out.println("Result1: " + solution.maximumGap(nums1) + "\n");
        System.out.println("Result2: " + solution.maximumGap(nums2) + "\n");

    }

}

/**
 * 
 * 
 * Intuitions :
 * 
 * 1. he problem asks for the maximum gap between two consecutive elements after
 * sorting — but sorting is 𝑂(𝑛log𝑛)O(nlogn), and we need linear time.
 * 2. Bucket sort helps here — instead of sorting directly, we group numbers
 * into buckets based on the range they fall into.
 * 
 * Pattern :
 * 
 * 1. Find the minimum and maximum elements in the array.
 * 2️. Calculate bucket size and number of buckets:
 * - Bucket size = [max - min] / (n - 1)
 * - Number of buckets = [max - min] / (bucket size)
 * 3. Place each number into its corresponding bucket
 * Bucket index = [num - min] / (bucket size)
 * 4. Track Min and max values of each buckets
 * 5. Calculate the maximum gap
 * 
 * 
 * Pseudo Code :
 * 
 * function maximumGap(nums):
 * 1. If nums has less than 2 elements, return 0.
 * 2. Find min and max of nums.
 * 3. Calculate bucket size and number of buckets.
 * 4. Create buckets to store min and max values.
 * 5. Place numbers into appropriate buckets.
 * 6. Compute the max gap by comparing max of one bucket to min of the next
 * non-empty bucket.
 * 7. Return the max gap.
 * 
 * 
 * 
 */
