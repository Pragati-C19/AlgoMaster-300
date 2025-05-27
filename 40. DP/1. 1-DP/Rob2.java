import java.util.*;

public class Rob2 {
    
    public int rob(int[] nums) {
        
        // Declare variables
        int n = nums.length;
        int[] dp = new int[n + 1];

        // Assign initial values
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // let's check other houses
        for (int i = 2; i < n; i++) {
            
            int moneyRobToday = nums[i] + dp[i - 2];
            System.out.println("    - How much money we get by robbing " + i + " today : " + moneyRobToday);
            
            dp[i] = Math.max(dp[i - 1], moneyRobToday);
            System.out.println("    - After cheking " + i + " step DP array looks like : " + Arrays.toString(dp));

        }

        if (dp[0] > dp[1]) {

            return dp[n-2];
        }

        return dp[n-1];
    }

    public static void main(String[] args) {

        Rob2 solution = new Rob2();

        int[] nums1 = {2,3,2};
        System.out.println("Result 1 -> " + solution.rob(nums1) + "\n");    // 3

        int[] nums2 = {1,2,3,1};
        System.out.println("Result 2 -> " + solution.rob(nums2) + "\n");    // 4
        
        int[] nums3 = {1,2,3};
        System.out.println("Result 3 -> " + solution.rob(nums3) + "\n");    // 3

    }

}

/*
 * Intuitions :
 
    1. We are a professional robber and planning to rob houses 
    2. each house has certain amount of money
    3. all houses are arranged in a circle
        - means first house is a neighbor of last house
    4. adj houses have security systems connected
    5. security systems will automatically contact the police if two adj houses broken into on same night
    6. nums array represent amount of money the house has
    7. return maximum amount of money we can rob   
 

 
 * Pattern :
 
    1. Will use basic pattern we wrote in Rob que
    2. take dp to store maximum money we robbed upto that day
    3. we need to choose if we are going to rob today or rest 
        - with help of did our yesterday's money was maximum or if we rob today then it will be max
    4. just addition in that pattern is.. 0th and nth index are adj
    5. ohk as per hint given
        since we can't rob house[0] and house[n-1] in one night     - 0-th indexed so n - 1
        then this problem becomes house[0] to house[n-2] and house[1] to house[n-1]
        now use rob pattern
    6. to check if we have robbed zeroth or first house will use a if
        if(dp[0] > dp[1]) -> then we have robbed 0th index
            - we need start from 0 to n-1 only
        if(dp[1] > dp[0]) -> then we have robbed 1st index
            - we need to start from 1 to n
    7. ohk I have an idea
        - apan jasa rob madhe kel same tasach karu and dp cha array purn karun gheu ohk?
        - then he check karu
            if(dp[0] > dp[1]) -> then will return dp[n - 2]
            if(dp[1] > dp[0]) -> then will return dp[n - 1]
 
 
 * Pseudo Code :
 



 */
