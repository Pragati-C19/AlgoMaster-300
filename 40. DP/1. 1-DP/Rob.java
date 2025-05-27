import java.util.Arrays;

public class Rob {
    
    public int rob(int[] nums) {
        
        // Declare variables
        int n = nums.length;
        int[] dp = new int[n + 1];

        if (n == 1) {
            
            return nums[0];
        }
        
        if (n == 2) {
            
            return Math.max(nums[0], nums[1]);
        }

        // assign starting values
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = nums[2] + nums[0];

        for (int i = 3; i < n; i++) {
            
            dp[i] = nums[i] + Math.max(dp[i - 2], dp[i - 3]);
            
            System.out.println("    - After cheking " + i + " step DP array looks like : " + Arrays.toString(dp));

        }

        // let's take max from last two amount in dp
        int maxMoneyRobbed = Math.max(dp[n - 1], dp[n - 2]);

        return maxMoneyRobbed;
    }

    public static void main(String[] args) {

        Rob solution = new Rob();

        int[] nums1 = {1,2,3,1};
        System.out.println("Result 1 -> " + solution.rob(nums1) + "\n");    // 4

        int[] nums2 = {2,7,9,3,1};
        System.out.println("Result 2 -> " + solution.rob(nums2) + "\n");    // 12
        
        int[] nums3 = {2,1,1,2};
        System.out.println("Result 3 -> " + solution.rob(nums3) + "\n");    // 4

        int[] nums4 = {0};
        System.out.println("Result 4 -> " + solution.rob(nums4) + "\n");    // 0

        int[] nums5 = {0, 0};
        System.out.println("Result 5 -> " + solution.rob(nums5) + "\n");    // 0

    }

}

/*
 * Intuition :
 
    1. We are a professional robber and planning to rob houses 
    2. each house has certain amount of money
    3. adj houses have security systems connected
    4. security systems will automatically contact the police if two adj houses broken into on same night
        - means will have to do odd indexes on one night even on one night\
    5. nums array represent amount of money the house has
    6. return maximum amount of money we can rob

 
 * Pattern :
 
    1. let's try brute force first as I think it will work
        - start for loop 
            if (i % 2 == 1) means index is odd and will add it's num in oddSum
            if (i % 2 == 0) means index is even and will add it's num in evenSum
        - at the end will take max between them and return it
    2. Let's try DP now
        - declare dp to store total amount of max money we get if we rob that house
        - I think we have to take 
            nums[i] + max( dp[i - 2], dp[i - 3] )  
 
 * Pseudo Code :
 
    1. Brute Force :

        public int rob(int[] nums) {
        
            -> Declare variables
                int n = nums.length;
                int evenSum = 0;
                int oddSum = 0;

            for (int i = 0; i < n; i++) {
                
                if (i % 2 == 1) {
                    
                    oddSum += nums[i];
                    System.out.println("    - Odd Index " + i + " so Odd sum : " + oddSum);
                }
                else {
                    
                    evenSum += nums[i];
                    System.out.println("    - Even Index " + i + " so Even sum : " + evenSum);
                }
            }

            int maxMoneyRobbed = Math.max(evenSum, oddSum);

            return maxMoneyRobbed;
        }


 */
