import java.util.*;

public class MaxSumOfThreeSubarrays {
    
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

 
 
 * Pseudo Code :
 

    1. Brute Force :
        



 */