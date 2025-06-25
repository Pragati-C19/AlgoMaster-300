import java.util.*;

public class MaxSumOfThreeSubarrays {
    
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        
        // Declare variables
        int n = nums.length;
        int maxTotalSum = 0;
        int[] resultArray = new int[3];

        // Phase 1 : let's precompute prefix sum for quick subarray sum lookup
        // prefixSum[i] = sum of nums[0] to nums[i - 1]
        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
            System.out.println("    - prefixSum[" + (i + 1) + "] = prefixSum[" + i + "] (" + prefixSum[i] + ") + nums[" + i + "] (" + nums[i] + ") = " + prefixSum[i + 1] );
        }
        System.out.println(" Prefix Sum Array : " + Arrays.toString(prefixSum) + "\n");


        // Phase 2 : Try all valid combinations of three non-overlapping subarrays
        for (int i = 0; i <= n - 3*k; i++) {
           
            // Get First SubArray Starting at i
            int sum1 = prefixSum[i + k] - prefixSum[i];
            System.out.println("    - index i = " + i + " ->  sum1 = prefixSum[" + (i + k) + "] (" + prefixSum[i + k] + ") - prefixSum[" + i + "] (" + prefixSum[i] + ") = " + sum1);
 
            for (int j = i + k; j <= n - 2*k; j++) {

                // Get second SubArray Starting at j
                int sum2 = prefixSum[j + k] - prefixSum[j];
                System.out.println("\t    - index j = " + j + " ->  sum2 = prefixSum[" + (j + k) + "] (" + prefixSum[j + k] + ") - prefixSum[" + j + "] (" + prefixSum[j] + ") = " + sum2);

                for (int h = j + k; h <= n - k; h++) {
                    
                    // Get second SubArray Starting at h
                    int sum3 = prefixSum[h + k] - prefixSum[h];
                    System.out.println("\t\t    - index h = " + h + " ->  sum3 = prefixSum[" + (h + k) + "] (" + prefixSum[h + k] + ") - prefixSum[" + h + "] (" + prefixSum[h] + ") = " + sum3);


                    // Let's get curr Total
                    int currTotal = sum1 + sum2 + sum3;
                    System.out.println("    - Total sum of three subarrays = " + currTotal);

                    // Check if currTotal is greater than maxTotal so far if yes then update 
                    if (currTotal > maxTotalSum) {
                        
                        maxTotalSum = currTotal;

                        resultArray[0] = i;
                        resultArray[1] = j;
                        resultArray[2] = h;

                        System.out.println("\tNew max found! maxTotalSum = " + maxTotalSum);
                        System.out.println("\tUpdated resultArray: " + Arrays.toString(resultArray));
                    }
                }
            }
        }


        return resultArray;
    }

    public static void main(String[] args){

        MaxSumOfThreeSubarrays solution = new MaxSumOfThreeSubarrays();

        int[] nums1 = {1,2,1,2,6,7,5,1};
        System.out.println(" Result 1 -> " + Arrays.toString(solution.maxSumOfThreeSubarrays(nums1, 2)) + "\n");    // [0,3,5]

        int[] nums2 = {1,2,1,2,1,2,1,2,1};
        System.out.println(" Result 2 -> " + Arrays.toString(solution.maxSumOfThreeSubarrays(nums2, 2)) + "\n");    // [0,2,4]

    }

}

/*
 * Intuitions :
 
    1. we have given a nums array and integer k
    2. we need to find 3 overlapping subArrays of length k with max sum and return it
    3. results should be in lexicographically smallest one

 
 * Pattern :
 
    1. Brute Force :

        - Will try all possible combinations 
            first subarray starting at i
            second subArray starting at j (will take i + k)
            third subarray stating at h (will tak j + k)
        - each valid (i, j, h)
            check if all are non-overlapping?
            get totalSum = sum(i) + sum(j) + sum(h)
            and track this maximum sum and it's starting numIndex 
                if [7, 5] is there then take numIndex of 7..
        - with brute force time complexity is too much but let's wrong this for more clarity
        - Will use Prefix Sum for pre
            where prefix[i] = sum of first i elements in nums[]
            means :
                prefix[0] = 0
                prefix[1] = nums[0]
                prefix[2] = nums[0] + nums[1]
                prefix[3] = nums[0] + nums[1] + nums[2]
        - Then will check (i, j, h)
            will get sums1, sum2, sum3 
            then add them and check if currTotal is greater than maxSum will update maxSum

    2. We need more optimal solution as I'm getting TLE
        - Compute windowSum[i] = sum of nums[i..i+k-1]
        - Fill bestLeft[] where bestLeft[i] is index with max window sum in [0..i]
        - Fill bestRight[] where bestRight[i] is index with max window sum in [i..end]
        - For every j in range k to n-2k:
                i = bestLeft[j - k]
                h = bestRight[j + k]
                total = windowSum[i] + windowSum[j] + windowSum[h]
                Track max total and update result


 
 * Pseudo Code :
 

    1. Brute Force :

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        
        -> Declare variables
            int n = nums.length;
            int maxTotalSum = 0;
            int[] resultArray = new int[3];

        -> Phase 1 : let's precompute prefix sum for quick subarray sum lookup
        -> prefixSum[i] = sum of nums[0] to nums[i - 1]
            int[] prefixSum = new int[n + 1];

            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
                System.out.println("    - prefixSum[" + (i + 1) + "] = prefixSum[" + i + "] (" + prefixSum[i] + ") + nums[" + i + "] (" + nums[i] + ") = " + prefixSum[i + 1] );
            }
            System.out.println(" Prefix Sum Array : " + Arrays.toString(prefixSum) + "\n");


        -> Phase 2 : Try all valid combinations of three non-overlapping subarrays
            for (int i = 0; i <= n - 3*k; i++) {
            
            -> Get First SubArray Starting at i
                int sum1 = prefixSum[i + k] - prefixSum[i];
                System.out.println("    - index i = " + i + " ->  sum1 = prefixSum[" + (i + k) + "] (" + prefixSum[i + k] + ") - prefixSum[" + i + "] (" + prefixSum[i] + ") = " + sum1);
    
                for (int j = i + k; j <= n - 2*k; j++) {

                -> Get second SubArray Starting at j
                    int sum2 = prefixSum[j + k] - prefixSum[j];
                    System.out.println("\t    - index j = " + j + " ->  sum2 = prefixSum[" + (j + k) + "] (" + prefixSum[j + k] + ") - prefixSum[" + j + "] (" + prefixSum[j] + ") = " + sum2);

                    for (int h = j + k; h <= n - k; h++) {
                        
                    -> Get second SubArray Starting at h
                        int sum3 = prefixSum[h + k] - prefixSum[h];
                        System.out.println("\t\t    - index h = " + h + " ->  sum3 = prefixSum[" + (h + k) + "] (" + prefixSum[h + k] + ") - prefixSum[" + h + "] (" + prefixSum[h] + ") = " + sum3);


                    -> Let's get curr Total
                        int currTotal = sum1 + sum2 + sum3;
                        System.out.println("    - Total sum of three subarrays = " + currTotal);

                    -> Check if currTotal is greater than maxTotal so far if yes then update 
                        if (currTotal > maxTotalSum) {
                            
                            maxTotalSum = currTotal;

                            resultArray[0] = i;
                            resultArray[1] = j;
                            resultArray[2] = h;

                            System.out.println("\tNew max found! maxTotalSum = " + maxTotalSum);
                            System.out.println("\tUpdated resultArray: " + Arrays.toString(resultArray));
                        }
                    }
                }
            }


            return resultArray;
    }


 */