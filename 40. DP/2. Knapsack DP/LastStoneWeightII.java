import java.util.*;

public class LastStoneWeightII {
    
    public int lastStoneWeightII(int[] stones) {
        
        // Declare Variables
        int n = stones.length;
        int totalSum = 0;


        // Check Total sum 
        for(int stone : stones) {
            totalSum += stone;
        }
        System.out.println(" Total Sum of all stones in Array : " + totalSum);


        // stones in both subarray should have sum as halfOfTotal
        int halfOfTotal = totalSum / 2;
        System.out.println(" Half of Total Sum : " + halfOfTotal);

        // Declare DP with length halfOfTotal
        boolean[] dp = new boolean[halfOfTotal + 1];

        // assign default value for 0'th index as Sum 0 is always possible (empty subset)
        dp[0] = true;

        // check all stones in an array and see if we find sum equals dp's index 
        for (int stone : stones) {
            for (int i = halfOfTotal; i >= stone; i--) {
                
                dp[i] = dp[i] || dp[i - stone];
            }

            System.out.println("    - After cheking " + stone + " step DP array looks like : " + Arrays.toString(dp));
        }

        return 0;
    }

    public static void main(String[] args) {

        LastStoneWeightII solution = new LastStoneWeightII();

        int[] stones1 = {2,7,4,1,8,1};
        System.out.println("Result 1 -> " + solution.lastStoneWeightII(stones1) + "\n");    // true

        int[] stones2 = {31,26,33,21,40};
        System.out.println("Result 2 -> " + solution.lastStoneWeightII(stones2) + "\n");    // false
        
        // int[] stones3 = {1,2,5};
        // System.out.println("Result 3 -> " + solution.lastStoneWeightII(stones3) + "\n");    // false

        // s1 = {3,6,8,16,20} s2 = {3,16,16,18}
        // int[] stones4 = {3,3,6,8,16,16,16,18,20};
        // System.out.println("Result 4 -> " + solution.canPartition(stones4) + "\n");    // true

        // int[] stones5 = {0, 0};
        // System.out.println("Result 5 -> " + solution.canPartition(stones5) + "\n");    // 0

        // int[] stones6 = {1,2,1,1};
        // System.out.println("Result 6 -> " + solution.canPartition(stones6) + "\n");    // 3

    }
}

/*
 * Intuition :
 
    1. We have given a stones array 
        - where stones[i] is the weight of i'th stone
    2. we are playing game with the stones.
        - on each turn, we choose any two stones and smash them together
    3. suppose stone x <= y then result of smash is :
        - x == y, both stones are destroyed
        - x != y, the stone of weight x is destroyed, 
                  and stone of weight y has new weight now -> y - x
    4. at the end of game there is at most one stone left
    5. need to return the smallest possible weight of the left stone
    6. if no stones left return 0
 
 
 * Pattern :

    1. Let's start brute force approach
        - first will convert int[] stones into List<Integer>
        - start a loop stones.size() > 1 till we do below steps
        - will sort stones array in descending order every time bcoz we want to take max stones 
        - then we take first two stones 
            x = stones.remove(0)
            y = stones.remove(1)
        
        - If x and y are not equal, push the difference back
            if(x != y)
                stones.add(y-x)

        - else we are already doing stones remove so it's automatically removing those 2 stones

        - at the end return 0'th index stone or if list.size get's zero will return 0 

    2. Now let's use Subset Sum pattern
        - check out this below video
            https://www.youtube.com/watch?v=gdXkkmzvR3c&ab_channel=NeetCodeIO
        - Find totalSum of all stones in array
        - take half of it and divide all stones in two sets
        - declare a dp of length subset 
        - add valuse just like we did in canPartition que
        - at the last if dp[i] = true
            will return totalSum - 2 * i


 * Pseudo Code :
 
    1. Brute Force :

        public int lastStoneWeightII(int[] stones) {
        
            // Convert this int[] into List<Integer>
            List<Integer> stonesList = new ArrayList<>();

            for (int stone : stones) {
                stonesList.add(stone);
            }
            System.out.println("Starting List : " + stonesList);


            // let's start while loop until only one stone is left
            while (stonesList.size() > 1) {
                
                // sort the stonesList in descending order
                Collections.sort(stonesList, Collections.reverseOrder());
                System.out.println("    - Sorted List : " + stonesList);

                // Get first two elements
                int y = stonesList.remove(0);   // largest element
                System.out.println("    - value of y : " + y);

                int x = stonesList.remove(0);   // second largest element
                System.out.println("    - value of x : " + x);

                // If both x and y are not equal then add y - x
                if (x != y) {
                    
                    stonesList.add(y - x);
                }

                // else it will destroy both stones 
                System.out.println("    - updated stone List : " + stonesList);
                
            }

            if (stonesList.size() == 1) {
                
                return stonesList.get(0);
            }

            return 0;
        }

 */