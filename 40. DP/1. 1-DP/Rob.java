public class Rob {
    
    public int rob(int[] nums) {
        
        // Declare variables
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

    public static void main(String[] args) {

        Rob solution = new Rob();

        int[] nums1 = {1,2,3,1};
        System.out.println("Result 1 -> " + solution.rob(nums1) + "\n");    // 4

        int[] nums2 = {2,7,9,3,1};
        System.out.println("Result 2 -> " + solution.rob(nums2) + "\n");    // 12
        
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
 
 
 * Pseudo Code :
 



 */
