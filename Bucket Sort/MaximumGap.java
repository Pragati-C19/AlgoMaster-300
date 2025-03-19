import java.lang.reflect.Array;
import java.util.*;

public class MaximumGap {
    
    public int maximumGap(int[] nums) {

        int n = nums.length;

        // If nums has less than 2 elements, return 0.
        if (n < 2)
            return 0;

        // Find min and max values
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;

        for (int num : nums) {
            // min Values
            if (num < minVal) minVal = num;

            // Max Values
            if (num > maxVal) maxVal = num;
        }

        // Calculate Bucket size and count
        int bucketSize = (maxVal - minVal) / (n - 1);
        int bucketCount = (maxVal - minVal) / bucketSize ;

        // Create buckets to store min and max
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            bucketMin[i] = Integer.MAX_VALUE;
            bucketMax[i] = Integer.MIN_VALUE;
        }        

        // Place numbers into buckets
        for(int num : nums){
            int bucketIndex = (num - minVal) / bucketSize;

            bucketMin[bucketIndex] = Math.min(bucketMin[bucketIndex], num);
            bucketMax[bucketIndex] = Math.max(bucketMax[bucketIndex], num);
        }

        // Calculate maximum gap
        int maxGap = 0;
        int prevBucketMax = minVal;
        for (int i = 0; i < bucketCount; i++) {

            // Skip empthy buckets
            if (bucketMin[i] == Integer.MAX_VALUE) {
                continue;
            }

            // Gap is from prev bucket Max to current bucket's min
            maxGap = Math.max(maxGap, bucketMin[i] - prevBucketMax);
            prevBucketMax = bucketMax[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {
        MaximumGap solution = new MaximumGap();

        int[] nums1 = { 3, 6, 9, 1 };
        int[] nums2 = { 10 };

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
 * - n = length of nums
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
