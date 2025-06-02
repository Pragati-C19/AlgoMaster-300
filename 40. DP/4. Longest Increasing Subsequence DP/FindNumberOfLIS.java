import java.util.*;

public class FindNumberOfLIS {
    
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