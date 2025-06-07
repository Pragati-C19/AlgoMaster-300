import java.util.*;

public class MaxCoins {
    
    public int maxCoins(int[] nums) {
        
        // Declare variables 
        int n = nums.length;

        // Declare dp to store maxCoins of start & end dp[start][end] 
        int[][] dp = new int[n + 2][n + 2];     // added +2 for start and end of 1 


        // In que we have given there is 1 before nums[0] and after nums[n] so let's add it in array 
        // in java we can't do push front push back so let's create new List<Integer>
        List<Integer> extendedNums = new ArrayList<>();

        // Add 1 at the begininng
        extendedNums.add(1);

        for (int num : nums) {
            extendedNums.add(num);
        }

        // Add 1 at the end
        extendedNums.add(1);

        System.out.println("Extended List : " + extendedNums);


        // Call a recursion function for start = 1 and end = n
        // why start = 1 bcoz apan list madhe 0'th index vr 1 taklay jo aplyala burst nahi karaychay.. 
        // why end = n bcoz apan list madhe n+1'th index vr 1 taklay jo aplyala burst nahi karaychay.. 
        recursivelyBurstBalloons(1, n, extendedNums, dp);

        return 0;
    }

    // Helper Function : get max coins by start and end
    private int recursivelyBurstBalloons (int start, int end, List<Integer> extendedNums, int[][] dp) {

        // Base Case :
        if (start > end) {
            
            // System.out.println("  - Start (" + start + ") is greater than end (" + end + ").. so return 0");
            return 0;
        }

        if (dp[start][end] != 0) {
            
            // we don't need to check value everytime just store it in dp and return it if needed 
            return dp[start][end];
        }


        // Declare variables 
        int maxCoins = 0;    // it stores maximum coins we can get by bursting the balloon


        // let's check all index from start to end
        // and as per que find coins at that index by nums[i - 1] * nums[i] * nums[i + 1]
        for (int i = start; i <= end; i++) {
            
            // Coins earned by bursting the current balloon at index 'i'
            // assuming balloons at i-1 and i+1 are still unburst
            int coinsEarnedByBurstingIth = extendedNums.get(i-1) * extendedNums.get(i) * extendedNums.get(i+1);
            
            // Maximum coins from recursively bursting balloons to the left of index 'i'
            // This is the subproblem [start, i - 1]
            int maxCoinsFromLeftPartition  = recursivelyBurstBalloons(start, i - 1, extendedNums, dp);
            
            // Maximum coins from recursively bursting balloons to the right of index 'i'
            // This is the subproblem [i + 1, end]
            int maxCoinsFromRightPartition  = recursivelyBurstBalloons(i + 1, end, extendedNums, dp);


            int currCoins = coinsEarnedByBurstingIth + maxCoinsFromLeftPartition + maxCoinsFromRightPartition;

            // Debugger :
            System.out.printf(
                "       i=%d | Burst[%d]=%d ->",
                i,
                i, extendedNums.get(i)
            );

            System.out.printf(
                "\n           - CoinsEarnedByBurstingIth: [%d]*[%d]*[%d] = %d | LeftDP[%d,%d]=%d | RightDP[%d,%d]=%d | CurrTotal = %d\n",
                extendedNums.get(i - 1), extendedNums.get(i), extendedNums.get(i + 1), coinsEarnedByBurstingIth,
                start, i - 1, maxCoinsFromLeftPartition,
                i + 1, end, maxCoinsFromRightPartition,
                currCoins
            );


            // Get maximum of all i's
            maxCoins = Math.max(maxCoins, currCoins);
            System.out.println("   Max Coins so far : " + maxCoins);

        }

        // store maxCoin value in dp
        dp[start][end] = maxCoins;
        System.out.println("   DP Array for [" + start + ", " + end + "] : " + Arrays.deepToString(dp) + "\n");

        // Return max Coins at the end 
        return maxCoins;
    }


    public static void main (String[] args) {

        MaxCoins solution = new MaxCoins();

        int[] nums1 = {3,1,5,8};
        System.out.println("Result 1 -> " + solution.maxCoins(nums1) + "\n");  // 167
    
        int[] nums2 = {1,5};
        System.out.println("Result 2 -> " + solution.maxCoins(nums2) + "\n");  // 10
    
    }

}

/*
 * Intuitions :
 
    1. we have given n ballons (0 to n - 1)
    2. each balloon has a number on it which tells in nums array
    3. we need to burst all balloons but there is a catch
        if we bust the ith balloon 
            will get nums[i-1] * nums[i] * nums[i+1] coins
        if i-1 and i+1 goes out of bound 
            treat it as if number on those balloons are 1

    4. need to return maximum coins we can collect by bursting balloons
 
 
 * Pattern :
 
    1. Start with entire Array or Block
	2. will mark them i and j 
		where i = start
		          j = end 
	3. Try all partitions 
		- Run A loop to try those partitions
		- take example : [ A, B, C, D]
		partitions are in format of ( i ) ( j )
			(A) (B,C,D)
			(A,B) (C,D)
			(A,B,C) (D)
	4. Return the best possible two partitions
		- Two partitions means ( i ) ( j )


    -> It seems same as binary search 
        like find start and end 
        then check mid and do operations on both sides of mid
        while loop till i <= j
	
	-> Just ithe dp create karun tyat value takaychiye

    ^ Check out below link :
        https://youtu.be/Yz4LlDSlkns?si=FlpGioHQz7mhsI_1

 
 
 * Pseudo Code :
 


 */