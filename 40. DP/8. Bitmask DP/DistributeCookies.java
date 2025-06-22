import java.util.*;

public class DistributeCookies {
    
    // Globally Declare variables


    // Driver Function 
    public int distributeCookies(int[] cookies, int k) {
        
        return 0;
    }

    // Recursion Function : to assign cookie bags to each child
    private void dfs(int currBagIndex, int[] dp, int[] cookies, int n, int k) {

        return;
    }

    public static void main(String[] args){
      
        DistributeCookies solution = new DistributeCookies();

        // First Example
        int[] cookies1 = {8,15,10,20,8};
        System.out.println("Result1 -> " + solution.distributeCookies(cookies1, 2) + "\n");   // 31

        // Second Example
        int[] cookies2 = {6,1,3,2,2,4,1,2};
        System.out.println("Result2 -> " + solution.distributeCookies(cookies2, 3) + "\n");   // 7
        
        // Third Example
        // int[] cookies3 = {1,2,3,4,5};
        // System.out.println("Result3 -> " + solution.distributeCookies(cookies3, 15) + "\n");   // 1
        
    }

}

/*
 * Intuitions :
 
    1. We have given an array cookies
        - where cookies[i] means number of cookies in the i'th bag
    2. we also have given k 
        - means number of childrens to distribute all the bags
    3. conditions is
        - we can't split cookies in bag
        - all cookies in bag must go to same child
    4. return the minimun unfairness of all distributions
        - what is it? will check it with example
    
 
 * Pattern :
 
    1. What is unfairness means ? 
        - Unfairness = the maximum total cookies any one child gets.
        - So we need to distribute cookies however we want.
        - For each distribution, calculate how many cookies each child has now.
        - Find the maximum among those totals.
        - That number is the unfairness for that distribution.
        - our job : try all possible distributions, and return the minimum unfairness that can happen.
            mhnje distribution asa karaych ki to maxCookies count jo ahe to over all ways pekshya min asla pahije
          
    2. Brute Force :
        - let's try every possible ways to assign each cookie bag to one of the 2 child
        - after checking all possible ways will track the unfairness for each 
        - then will pick the smallest unfairness


    ^ Dry Run :

        cookies = [8,15,10,20,8], k = 2
 
        - First Distribution : [0, 0, 0, 0, 0]  or  [1, 1, 1, 1, 1]
            all bags to child 0 or all bags to child 1
                   child 0 : 8 + 15 + 10 + 20 + 8 = 61
                   child 1 : 0
                maxCookies : 61
                unfairness : 61

        - Second Distribution : [0, 0, 0, 0, 1]  or  [1, 1, 1, 1, 0]
            last bag goes to child 1 or last bag goes to child 0
                   child 0 : 8 + 15 + 10 + 20 = 53
                   child 1 : 8
                maxCookies : 53
                unfairness : min(unfairness, maxCookies) = min(61, 53) = 53

        - Third Distribution : [0, 0, 0, 1, 1]  or  [1, 1, 1, 0, 0]
            last two bags to child 1 or last two bags to child 0
                   child 0 : 8 + 15 + 10 = 33
                   child 1 : 20 + 8 = 28
                maxCookies : 33
                unfairness : min(unfairness, maxCookies) = min(53, 33) = 33

        - Fourth Distribution : [0, 0, 1, 1, 1]  or  [1, 1, 0, 0, 0]
            last 3 bags to child 1 or last 3 bags to child 0
                   child 0 : 8 + 15 = 23
                   child 1 : 10 + 20 + 8 = 38
                maxCookies : 38
                unfairness : min(unfairness, maxCookies) = min(33, 38) = 33

        - Apan last bags aivaji first bags pn 1 la assign kelya astya but ans kahi vegal nahi yenar
            just child 0 ch child 1 la asel ans so me sadhya sathi skip kel te 
            but code madhe it will check that too

        - Fifth Distribution : [0, 1, 0, 1, 0]  or  [1, 0, 1, 0, 1]
            let's check alternate ones
                   child 0 : 8 + 10 + 8 = 26
                   child 1 : 15 + 20 = 35
                maxCookies : 35
                unfairness : min(unfairness, maxCookies) = min(33, 35) = 33

        - asa karat gelo tr almost 32 combinations ahet tyache itke nahi lihit basat me
            directly ans val lihite.. bcoz all other are greater then currUnfairness which is 33

        - Sixth Distribution : [0, 0, 1, 1, 0]  or  [1, 1, 0, 0, 1]
                   child 0 : 8 + 15 + 8 = 31
                   child 1 : 10 + 20 = 30
                maxCookies : 31
                unfairness : min(unfairness, maxCookies) = min(33, 31) = 31
 
 
 * Pseudo Code :
 

    1. Brute Force approach :

        function distributionCookies (cookies, k) {

            -> Declare variables 
                n = cookies.length
                minUnfairness = Max_Value   - bcoz we want to take min between all unfairness
                dp = int[k]                 - it stores total cookies assign to child i

            -> Call dfs start with 0'th cookie bag
                dfs(0, dp, n, k)

            -> at the end return minUnfairness
        }

        function dfs(currBagIndex, dp, n, k) {
        
            -> Base Case :
                if currBagIndex == n
                    means all cookies bag are assigned check currUnfairness(maxCookies) and get minUnfairness
                    
                    maxCookies = max(dp)
                    minUnfairness = min(unfairness, maxCookies)

            -> If not then try assigning currBag to each child 
                for(childIndex = 0 to k)

                    - assign currBag to this child 
                        dp[childIndex] += cookies[currBagIndex] 

                    - call dfs to assign next bag to another any other child
                        dfs(currBagIndex + 1, dp, n, k)

                    - Backtrack : once we get unfairness for one way of distributions we need to backtrack everything
                        so that will check another distribution from start
                        dp[childIndex] -= cookies[currBagIndex] 
        
        }

 */