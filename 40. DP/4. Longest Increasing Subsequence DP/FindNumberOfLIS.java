import java.util.*;

public class FindNumberOfLIS {
    
    public int findNumberOfLIS(int[] nums) {
        
        // Declare variables
        int n = nums.length;
        int maxLength = 1;
        int countOfMaxLength = 0;
        int[] dpLength = new int[n];
        int[] dpCount = new int[n];


        // Initially fill sp with 1 
        Arrays.fill(dpLength, 1);
        Arrays.fill(dpCount, 1);

        System.out.println(" Initial dpLength : " + Arrays.toString(dpLength));
        System.out.println(" Initial dpCount  : " + Arrays.toString(dpCount));


        // Compare if prevIndex num and currIndex num
        for (int currIndex = 1; currIndex < n; currIndex++) {
            for (int prevIndex = 0; prevIndex < currIndex; prevIndex++) {
                
                // check if prevIndex num is < than currIndex if yes then think abt taking this currIndex or not
                if (nums[prevIndex] < nums[currIndex]) {
                    
                    int lengthIfTakenCurrNum = 1 + dpLength[prevIndex];
                
                    // need this before updating dpLenth of currIndex bcoz I need prev value of dpLength of currIndex
                    if (lengthIfTakenCurrNum == dpLength[currIndex]) {
                        
                        // is both length are same we need to add updated count[curr] + count[prev]
                        dpCount[currIndex] += dpCount[prevIndex];
                    }
                    else if (lengthIfTakenCurrNum > dpLength[currIndex]) {
                        
                        // we need to reset dpCount[currIndex] when we find a new longer subsequence
                        dpCount[currIndex] = dpCount[prevIndex];
                    }
            
                    dpLength[currIndex] = Math.max(dpLength[currIndex], lengthIfTakenCurrNum);

                }
            }

            // get maxLength from whole dp array
            maxLength = Math.max(maxLength, dpLength[currIndex]);

            System.out.println("    - Updated dpLength Array : " + Arrays.toString(dpLength) + "  |  with maxLength : " + maxLength);
            System.out.println("      Updated dpCount Array  : " + Arrays.toString(dpCount));
        }
        

        // Ohk I forgot the last part : we need count of all index with maxLength
        // Adhi me fact last index cha lihit hote 
        for (int i = 0; i < n; i++) {
            
            if (dpLength[i] == maxLength) {
                
                countOfMaxLength += dpCount[i];
            }
        }

        return countOfMaxLength;
    }

    public static void main(String[] args) {

        FindNumberOfLIS solution = new FindNumberOfLIS();

        int[] nums1 = {1,3,5,4,7};
        System.out.println("Result 1 -> " + solution.findNumberOfLIS(nums1) + "\n");    // 2

        int[] nums2 = {2,2,2,2,2};
        System.out.println("Result 2 -> " + solution.findNumberOfLIS(nums2) + "\n");    // 5
        
        int[] nums3 = {100,90,80,70,60,50,60,70,80,90,100};
        System.out.println("Result 3 -> " + solution.findNumberOfLIS(nums3) + "\n");    // 1

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

    ^ Let's change the approach or thinking

        1. Problems
            - tithe maxlength valyacha count hava hota but 
                [1, 3, 4, 7] and [1, 3, 5, 7]
                yat tr apan 4 la replace kel 5 sobt and then 7 add karat hoto tevha apan mhnlo ki jo replace karat hote tyat pn takaychy mala 7
                and mala whole count de ata kay zala to
        
        2. maybe dp madhe yaveles longest Subsequnce till currIndex cha count nahi add karaychay
            - add karaychay tr kiti subsequnce create alyat currIndex parynt

        3. I did some gpting and got a idea
            - mala saglya index chya tithe kiti count bantay he lihin imp ahe..
            - karan mala fact last maxLength cha count nahi tr saglya nums chya subsequence cha count lihil ahe
            - me jo sadhya dp use kartey fact to fact store kartoy longest subsequence 
            - to he nahi mhnt ahe ki currIndex parynt kiti longest subSequence alyat mhnun

        4. Think Fully fresh again

            - will initialize two dp's
                dpLength : jo sangel konta longest LIS ahe till currIndex
                dpCount  : jo sangel aplyakde kiti ways ahet longest LIS bhetnyache (dpLength[i])

            - Initially donhi dp's madhe 1 fill karu
                why ?
                    karan starting la each num is a subsequnce of itself
                dpLength = [1, 1, 1, 1, 1]
                dpCount  = [1, 1, 1, 1, 1]

            - Now check the trace Example
                lengthIfTakenCurrIndex jr equal asel adhi add kelellya dp[currIndex] pekshya 
                    tr apan dp[curr] = dp[curr] + dp[prev] kartoy
                    else apan dp[curr] = dp[prev] kartoy

                
                
        5. Trace Example :

            nums = [1,3,5,4,7]

            - let's initialize Both Dp's
                
                index       :   0   1   2   3   4     
                dpLength    :   [1,  1,  1,  1,  1]
                dpCount     :   [1,  1,  1,  1,  1]

            - currIndex = 1
                prevIndex = 0   -> nums[0] < nums[1] = (1 < 3)   -> 1 + dpLength[0] = 2
                dpCount[1] = dpCount[0] = 1
                
                dpLength = [1, 2, 1, 1, 1]
                dpCount = [1, 1, 1, 1, 1]
                
            - currIndex = 2
                prevIndex = 0   -> nums[0] < nums[2] = (1 < 5)   -> 1 + dpLength[0] = 2
                dpCount[2] = dpCount[0] = 1

                prevIndex = 1   -> nums[1] < nums[2] = (3 < 5)   -> 1 + dpLength[1] = 3
                dpCount[2] = dpCount[1] = 1
                
                dpLength = [1, 2, 3, 1, 1]
                dpCount = [1, 1, 1, 1, 1]    

            - currIndex = 3
                prevIndex = 0   -> nums[0] < nums[3] = (1 < 4)   -> 1 + dpLength[0] = 2
                dpCount[3] = dpCount[0] = 1

                prevIndex = 1   -> nums[1] < nums[3] = (3 < 4)   -> 1 + dpLength[1] = 3
                dpCount[3] = dpCount[1] = 1

                prevIndex = 2   -> nums[2] > nums[3] = (5 > 4)   -> Skipp this one bcoz prevIndex num is greater then currIndex num
                
                dpLength = [1, 2, 3, 3, 1]
                dpCount = [1, 1, 1, 1, 1]      
                
            - currIndex = 4
                prevIndex = 0   -> nums[0] < nums[4] = (1 < 7)   -> 1 + dpLength[0] = 2
                dpCount[4] = dpCount[0] = 1

                prevIndex = 1   -> nums[1] < nums[4] = (3 < 7)   -> 1 + dpLength[1] = 3
                dpCount[4] = dpCount[1] = 1

                prevIndex = 2   -> nums[2] < nums[4] = (5 < 7)   -> 1 + dpLength[2] = 4
                dpCount[4] = dpCount[2] = 1

                prevIndex = 3   -> nums[3] < nums[4] = (4 < 7)   -> 1 + dpLength[3] = 4

                length is same as prevIndex 2 so
                dpCount[4] = dpCount[4] + dpCount[3] = 1 + 1 = 2
                
                dpLength = [1, 2, 3, 3, 4]
                dpCount = [1, 1, 1, 1, 2]       


            


 */