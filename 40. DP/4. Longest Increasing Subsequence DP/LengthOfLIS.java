import java.util.*;

public class LengthOfLIS {
    
    public int lengthOfLIS(int[] nums) {

        // Declare variables
        int n = nums.length;
        int maxLength = 1;
        int[] dp = new int[n];

        // Initially will store 1 for all index bcoz we can count that num it self
        Arrays.fill(dp, 1);
        System.out.println(" Initial DP : " + Arrays.toString(dp));

        // so let's check all nums and see the longest length so far till that num
        for (int currIndex = 1; currIndex < n; currIndex++) {
            
            // We want to check all previous index values (nums[prevIndex])
            // to see which ones are smaller than the current number (nums[currIndex])
            // If we find such a number, it means we can extend its increasing subsequence
            // by adding the current number after it.

            for (int prevIndex = 0; prevIndex < currIndex; prevIndex++) {
             
                if (nums[prevIndex] < nums[currIndex]) {
                    
                    int takeThisNumInSubsequence = 1 + dp[prevIndex];
                    
                    dp[currIndex] = Math.max(dp[currIndex], takeThisNumInSubsequence);
                }
            }

            // get maxLength from whole dp array
            maxLength = Math.max(maxLength, dp[currIndex]);

            System.out.println("    - Updated DP Array : " + Arrays.toString(dp));
        }
        
        return maxLength;
    }

    public static void main(String[] args) {

        LengthOfLIS solution = new LengthOfLIS();

        int[] nums1 = {10,9,2,5,3,7,101,18};
        System.out.println("Result 1 -> " + solution.lengthOfLIS(nums1) + "\n");    // 4

        int[] nums2 = {0,1,0,3,2,3};
        System.out.println("Result 2 -> " + solution.lengthOfLIS(nums2) + "\n");    // 4
        
        int[] nums3 = {7,7,7,7,7,7,7};
        System.out.println("Result 3 -> " + solution.lengthOfLIS(nums3) + "\n");    // 1

        int[] nums4 = {1,3,6,7,9,4,10,5,6};
        System.out.println("Result 4 -> " + solution.lengthOfLIS(nums4) + "\n");    // 6

        int[] nums5 = {0};
        System.out.println("Result 5 -> " + solution.lengthOfLIS(nums5) + "\n");    // 1

        // int[] nums6 = {1,2,1,1};
        // System.out.println("Result 6 -> " + solution.lengthOfLIS(nums6) + "\n");    // 3

    }

}

/*
 * Intuitions :
 
    1. we have given an array 
    2. we need to return longest increasing subsequence 
    3. what is exactly longest increasing subsequence means?
        Example: 10,9,2,5,8,3,7,101,18

            - first will check 10 and add it in array 
                input [10]. arr = [10]

            - then will check 9 and 9 < 10 so will remove 10 and add 9
                input [10, 9]. arr = [9]

            - then will check 2 and 2 < 9 so will remove 9 and add 2
                input [10, 9, 2]. arr = [2] 

            - then will check 5 and 5 > 2 so will not remove 2 just add 5
                input [10, 9, 2, 5]. arr = [2, 5]

            - then will check 8 and 8 > 2 and 8 > 5 so will not remove 2 or 5 will just add 8
                 input [10, 9, 2, 5, 8]. arr = [2, 5, 8]

            - then will check 3 and 3 < 5 and 3 < 8 but 5 comes first so will remove 5 and add 3
                input [10, 9, 2, 5, 8, 3]. arr = [2, 3, 8]
            
            - then will check 7 and 7 < 8 so will remove 8 and add 7
                input [10, 9, 2, 5, 8, 3, 7]. arr = [2, 3, 7]
            
            - then will check 101 and 101 > 2, 101 > 3, 101 > 7 so will not remove 2 or 3 or 7 will just add 5
                input [10, 9, 2, 5, 8, 3, 7, 101]. arr = [2, 3, 7, 101]
            
            - then will check 18 and 18 < 101 so will remove 101 and add 18
                input [10, 9, 2, 5, 8, 3, 7, 101, 18]. arr = [2, 3, 7, 18]
 
 
 * Pattern :
 
    1. I can wrote a brute force approach like I used in above example 
        - but it will have issues of TLE 
        - bcoz checking if currAddingNum > or < arrNums takes soo much time
    2. Think abt how will u use dp here?
        - what will be the length of dp?
            it will be nums.length
        - what will we store in dp?
            will store length of longest increasing subsequence till that index
        - at start will store 1 for each index because the minimum length ending at any element is 1 – the element itself
        - For each element at index i, we check all previous indices j < i and if nums[j] < nums[i], we can try to extend the LIS ending at j to i

            | Index | Num |  dp[] state after this index                    |
            | ----- | --- | ----------------------------------------------- |
            | 0     | 10  |  [1]                                            |
            | 1     | 9   |  [1, 1]                                         |
            | 2     | 2   |  [1, 1, 1]                                      |
            | 3     | 5   |  [1, 1, 1, 2]               ->   5 > 2          |
            | 4     | 3   |  [1, 1, 1, 2, 2]            ->   3 > 2          |
            | 5     | 7   |  [1, 1, 1, 2, 2, 3]         ->   7 > 5, 3, 2    |
            | 6     | 101 |  [1, 1, 1, 2, 2, 3, 4]      -> 101 > 7,5,3,2    |
            | 7     | 18  |  [1, 1, 1, 2, 2, 3, 4, 4]   ->  18 > 7,5,3,2    |


        - what does the nested for loop do?
            - Sagle magcha elements (0 to currIndex-1) check karto 
            - Baghto ki konta element nums[currIndex] peksha chhota aahe ka?
            - jar asel, tar tya element varun apan subsequence la extend karu shakto
                mhanje dp[prevIndex] madhe je subsequence aahe, tyat nums[currIndex] add karu
            - ani baghu ki max subsequence length miltey ka
    
        - ohk mala vatal hot ki dp[n] last value ch max asel but tasa nahiye.. 
            max value madhech kontya pn index la asu shakte look at the example I send

    3. Don't know how but brute Force worked
        - I didn't do much just code one by one using above logic

    4. if length of num is 1 we need to return 1 not zero
        - same logic we use for filling whole dp by 1 applyis here too..
        - subsequence of any num indivisually itself is 1 
        - so maxLength should be initially as 1 not 0 
 
 * Pseudo Code :
 

    1. Brute Force :

        public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        
        -> let's declare array List as we don't know exact size yet in which will say if we are taking this nums or not 
        List<Integer> result = new ArrayList<>();        

        for (int num : nums) {
            
            -> If result array is empty we can add the num directly
            if (result.isEmpty()) {
                
                result.add(num);
                System.out.println("    - Result array is empty to ditectly added " + num + " : " + result);
            }
            else {
                
                -> Declare a variable replaced to know if we replaced the num or not 
                boolean replaced = false;

                -> If result array is not empty 
                for (int i = 0; i < result.size(); i++) {
                    
                    -> Focus on Replace only 
                    if (result.get(i) >= num) {
                        
                        result.set(i, num);     // Replacing that i'th index with currNum using set 
                        System.out.println("    - Given num(" + num + ") is <= i'th index(" + i + ") so replacing the i'th index num : " + result);

                        -> To avoid getting same values will say we have replaced this index
                        replaced = true;

                        -> After replacing one I don't want to replace any other nums so 
                        break;
                    }
                }

                -> now will not always add num > result.get(i) in array will just add nums which are not replaced 
                if (!replaced) {
                    
                    result.add(num);
                    System.out.println("    - Given num(" + num + ") is > all index in result so we have added num : " + result);
                }

                System.out.println("Current Result Array: " + result);
            }

        }

        return result.size();
    }


 */