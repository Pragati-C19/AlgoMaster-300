import java.util.*;

public class CanPartition {

    public boolean canPartition(int[] nums) {
        
        // Declare Variables
        int n = nums.length;
        int totalSum = 0;


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


        // nums in both subarray should have sum as halfOfTotal
        int halfOfTotal = totalSum / 2;
        System.out.println(" Half of Total Sum : " + halfOfTotal);

        // Declare DP with length halfOfTotal
        boolean[] dp = new boolean[halfOfTotal + 1];

        // assign default value for 0'th index as Sum 0 is always possible (empty subset)
        dp[0] = true;

        // check all nums in an array and see if we find sum equals dp's index 
        for (int num : nums) {
            for (int i = halfOfTotal; i >= num; i--) {
                
                dp[i] = dp[i] || dp[i - num];
    
                System.out.println("    - After cheking " + i + " step DP array looks like : " + Arrays.toString(dp));
            }
        }

        return dp[halfOfTotal];
    }


    public static void main(String[] args) {

        CanPartition solution = new CanPartition();

        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Result 1 -> " + solution.canPartition(nums1) + "\n");    // true

        int[] nums2 = {1,2,3,5};
        System.out.println("Result 2 -> " + solution.canPartition(nums2) + "\n");    // false
        
        int[] nums3 = {1,2,5};
        System.out.println("Result 3 -> " + solution.canPartition(nums3) + "\n");    // false

        // s1 = {3,6,8,16,20} s2 = {3,16,16,18}
        // int[] nums4 = {3,3,6,8,16,16,16,18,20};
        // System.out.println("Result 4 -> " + solution.canPartition(nums4) + "\n");    // true

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
 
    ^ Improvement :

        1. Let's Use Subset Sum DP 
        2. Before we were trying to use Brute Force and memozation but now change the pattern
        3. What was our goal ?
            - Find the total sum of all numbers.
            - If the sum is odd, we cannot split into two equal parts.
            - If the sum is even, check if there is a subset whose sum = totalSum / 2.
        4. so will calculate the totalSum as we did before
        5. Set Base case as if sum is odd return false : we can't divide sum / 2 and as per que we need two subArrays
        6. What is the targeted sum we want to have for both subArr? 
            it's halfOfTotal : why? we already discussed it
        7. Now Main part of the game 
            - declare a dp array with length [halfOfTotal + 1]
                why ? apan check dp madhe store kartoy ki subset chi sum equal to index ahe ka mhnun 
            - dp[i] : "Can we get a subset with sum = i?"
            - dp[0] = true      -> base case: sum of 0 is always possible (empty subset)
            - then let's start an nested for loop
                for(num : nums)
                    for(i = halfOfTotal to num)

                why? We go backward from halfOfTotal to num to prevent using the same element twice in this iteration.

            - We have Two Choices:
                Don't take num  -> dp[j] remains as is
                Take num        -> check if dp[j - num] was true before -> now dp[j] becomes true
        8. at the end return dp[halfOfTotal]

    ^ Dry Run :

        nums = [1, 5, 11, 5]    total = 22 -> target = 11

        - we want to check if subset adds upto 11 

        - initial value in dp after adding dp[0] = true
            
            dp = [  true, false, false, false, false, false, false, false, false, false, false, false   ]
                      0     1      2      3      4      5      6      7      8      9      10     11

        - Checking num = 1
            we can now make sum 1 using 1

            dp[1] = dp[1] || dp[1 - 1]      ->  false || true -> true

        - Checking num = 5
            we can now make sum 5 using 5

            dp[5] = dp[5] || dp[5 - 5]      ->  false || true   -> true
            dp[6] = dp[6] || dp[6 - 5]      ->  false || dp[1]  -> true   -> dp[6] = true


 
 * Pseudo Code :
 

    1. Brute Force :

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

            // nums in both subarray should have sum as halfOfTotal
            int halfOfTotal = totalSum / 2;
            System.out.println(" Half of Total Sum : " + halfOfTotal);

            // dividing nums in 2 arrays
            for (int num : nums) {
                
                if ((s1Sum + num) <= halfOfTotal) {
                    
                    s1Sum += num;
                    System.out.println("    - (s1Sum + num) < halfOfTotal so added that num in s1Sum : " + s1Sum);
                }
                else {

                    s2Sum += num;
                    System.out.println("    - (s1Sum + num) > halfOfTotal so added that num in s2Sum : " + s2Sum);
                }
            }

            // if both sum equal return true
            if (s1Sum == s2Sum) {
                
                System.out.println(" Both subset sum are equal... ");
                return true;
            }

            return false;
        }

    2. Memoization :

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

            // nums in both subarray should have sum as halfOfTotal
            int halfOfTotal = totalSum / 2;
            System.out.println(" Half of Total Sum : " + halfOfTotal);


            return decideWhichNumWant(nums, halfOfTotal, 0, n);
        }

        // Helper Function :
        private boolean decideWhichNumWant (int[] nums, int halfOfTotal, int index, int n) {

            if (halfOfTotal == 0) {
                
                System.out.println("    halfOfTotal comes to 0.. means we got our one set");
                return true;
            }

            if (index >= n ) {
                
                System.out.println("    Base Case : if startIndex has more than length of nums means we don't have value in array now..");
                return false;
            }

            boolean take = false;
            boolean notTaking;

            if (nums[index] <= halfOfTotal) {
                
                System.out.println("    - if " + nums[index] + " <= " + halfOfTotal + " that means we can add it in a s1 set..");

                take = decideWhichNumWant(nums, halfOfTotal - nums[index], index + 1, n);
            }

            notTaking = decideWhichNumWant(nums, halfOfTotal, index + 1, n);
            
            System.out.println("    - take and notTaking : " + take + ", " + notTaking);

            return take || notTaking;
        }

 */