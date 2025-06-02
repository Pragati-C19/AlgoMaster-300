import java.util.*;

public class FindNumberOfLIS {
    
    public int findNumberOfLIS(int[] nums) {
        
        // Declare variables
        int n = nums.length;
        int maxLength = 1;          // initial length of any num will be 1
        int countOfMaxLength = 0;   // it will tell how many maxLength subsequence we got
        int[] dp = new int[n];


        // Initially fill sp with 1 
        Arrays.fill(dp, 1);
        System.out.println(" Initial DP : " + Arrays.toString(dp) + "  |  with length : " + n);


        // Compare if prevIndex num and currIndex num
        for (int currIndex = 1; currIndex < n; currIndex++) {
            for (int prevIndex = 0; prevIndex < currIndex; prevIndex++) {
                
                // check if prevIndex num is < than currIndex if yes then think abt taking this currIndex or not
                if (nums[prevIndex] < nums[currIndex]) {
                    
                    int takeThisNumInSubsequence = 1 + dp[prevIndex];

                    dp[currIndex] = Math.max(dp[currIndex], takeThisNumInSubsequence);
                }
            }

            maxLength = Math.max(maxLength, dp[currIndex]);

            System.out.println("    - Updated DP Array : " + Arrays.toString(dp) + "  |  with maxLength : " + maxLength);
        }
        

        return 0;
    }

    public static void main(String[] args) {

        FindNumberOfLIS solution = new FindNumberOfLIS();

        int[] nums1 = {1,3,5,4,7};
        System.out.println("Result 1 -> " + solution.findNumberOfLIS(nums1) + "\n");    // 2

        int[] nums2 = {2,2,2,2,2};
        System.out.println("Result 2 -> " + solution.findNumberOfLIS(nums2) + "\n");    // 5
        
        // int[] nums3 = {7,7,7,7,7,7,7};
        // System.out.println("Result 3 -> " + solution.findNumberOfLIS(nums3) + "\n");    // 1

        // int[] nums4 = {1,3,6,7,9,4,10,5,6};
        // System.out.println("Result 4 -> " + solution.findNumberOfLIS(nums4) + "\n");    // 6

        // int[] nums5 = {0};
        // System.out.println("Result 5 -> " + solution.findNumberOfLIS(nums5) + "\n");    // 1

        // int[] nums6 = {1,2,1,1};
        // System.out.println("Result 6 -> " + solution.findNumberOfLIS(nums6) + "\n");    // 3

    }

}

/*
 * Intuitions :
 
    1. This que is same as lengthOfLIS just here we need to tell how many subsequances are longest
    2. means as always will check the all currNums with prevNums 
    3. then will check if that prevNum < currNum 
        if yes then will think that adding currNum will give longest subsequence or not 
    4. then will find a maxLength
    5. Now he je zal te prev que madhe hot tasach ahe.. now we a count of maxLength
        as in maxLength kiti veles alay in dp?
        so apan end la ek for loop lau jyat check karu dp[i] == maxLength
            if yes then count++
 
 
 * Pattern :
 
    1. declare variables
        n                   -> nums length
        maxLength           -> it will tell what is the longest subsequence so far
        countOfMaxLength    -> this will tell ashya kiti subsequence ahet jyanchi length maxLength itki ahe
        dp                  -> it will store longest subsequence till currIndex
 
    2. will initially set value 1 for all index in dp
        why?
            karan saglya nums seperate thevlya tr tyacha subseqence banelach..
    
    3. Apan currIndex al prevIndex sobt compare karu 
        - will not start currIndex with 0 bcoz there is not prev of that.. and at start it will be 1 
        - just apan bakichya que madhe dp[0] = 1 nahi kelay bcoz second step madhech value set keliye
        - aplyala fact prevIndex havyat currIndex pekshya tyamul me fact prevIndex < currIndex ch check karel

    4. jr prevIndex vrcha num small asel currIndex chya num pekshya
        - me num mhnle dp madhla longest subsequence count nahi
        - num as in nums array madhla num
        - so if yes then apan check karu ki curr count jo ahe dp[currIndex] madhe already to motha ahe ki jr currIndex la add kel tr motha hoil?
        - tula athavat me coinChange chya problem madhe kelel ki jast values bhetlya tr me tyanchyatla jo min asel to ghet hote 
            ithe pn same ahe just max gehtlay
    
    5. he zal ki for loop chya baher maxLength la update karat ja..
    6. at the end will check ki maxLength chya itke kiti subsequence cha count ahe mhnun

    7. and yes ithe me jo adhi bruteForce lihila hota prev que madhe tasa work nahi karnar karan maybe.. 
        - maybe karel pn maxLength count maintain kelyavr will think of that too later


 */