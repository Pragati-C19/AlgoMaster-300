import java.util.*;

public class CanPartition {

    public boolean canPartition(int[] nums) {
        
        // Declare Variables
        int n = nums.length;
        int totalSum = 0;
        int s1Sum = 0;      // 1st Subarray 
        int s2Sum = 0;      // 2nd Subarray 

        // Check Total sum 
        for(int num : nums) {
            totalSum += num;
        }
        System.out.println(" Total Sum of all nums in Array : " + totalSum);

        // Base Case :
        if(totalSum % 2 != 0) {

            System.out.println("    Total sum (" + totalSum + ") of all nums in array is not divisible by 2...");
            return false;
        }

           

        return false;
    }

    public static void main(String[] args) {

        CanPartition solution = new CanPartition();

        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Result 1 -> " + solution.canPartition(nums1) + "\n");    // true

        int[] nums2 = {1,2,3,5};
        System.out.println("Result 2 -> " + solution.canPartition(nums2) + "\n");    // false
        
        int[] nums3 = {1,2,5};
        System.out.println("Result 3 -> " + solution.canPartition(nums3) + "\n");    // false

        // int[] nums4 = {0};
        // System.out.println("Result 4 -> " + solution.canPartition(nums4) + "\n");    // 0

        // int[] nums5 = {0, 0};
        // System.out.println("Result 5 -> " + solution.canPartition(nums5) + "\n");    // 0

        // int[] nums6 = {1,2,1,1};
        // System.out.println("Result 6 -> " + solution.canPartition(nums6) + "\n");    // 3

    }

}

/*
 * Intuitions :
 
    1. We have given an nums array 
    2. we need to divide it in two subset of different lengths 
    3. sum of nums in 1st subset will be equal to sum of nums in 2nd subset
    4. if above condition get's fullfill we'll return true otherwise false  
 
 
 * Pattern :
 
    1. Brute Force Thinking :
        - two subarr chi ji total sum asel ti equal to sum of nums array asel, right?
            sum(nums of 1st SubArr) + sum(nums of 2nd SubArr) = sum(nums of nums array)
        - and aplyala havet 2 subarrays.. mhnje we are dividing sum(nums of nums array) in half half parts right?
            que madhe mention ahe donhi subset chi sum equal asali pahije
            ani suppose nums Array madhlya nums chi sum 12 ahe tr maze donhi subset chi value kay pahije ? 6-6 tevhach te equal hotil na?
    2. Now let's think wit dividing total sum by 2
        - apli ek base case hou shakte.. 
            jr total sum divisible by 2 nasel tr apan 2 subset madhe array la divide karuch nahi shakat
            so apan tithech false mhnun
        - else true nahi karu shakat.. bcoz I tried to submit it.. it gives error for test case : [1, 2, 5]
        - so sum even asel tr nehmich guarantee nahiye ki true asel
    3. Will check one subset whose sum equals to totalSum / 2
        - apan ek kam karu.. ek for loop start karu 
            for(num : nums) 
        - and tyat apan sum madhe ek ek num add karu
            if(s1Sum < totalSum/2) 
                will add in s1Sum
            else if(s1Sum > totalSum/2)
                will add in s2Sum
        - at the end of for loop check if s1Sum == s2Sum ? if yes then return true else false
 
 
 
 * Pseudo Code :
 



 */