import java.util.*;

public class JobScheduling {
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
     
        return 0;
    }

    public static void main (String[] agrs) {

        JobScheduling solution = new JobScheduling();

        int[] startTime1 = {1,2,3,3};
        int[] endTime1 = {3,4,5,6};
        int[] profit1 = {50,10,40,70};
        System.out.println("Result 1 -> " + solution.jobScheduling(startTime1, endTime1, profit1) + "\n");

        int[] startTime2 = {1,2,3,4,6};
        int[] endTime2 = {3,5,10,6,9};
        int[] profit2 = {20,20,100,70,60};
        System.out.println("Result 2 -> " + solution.jobScheduling(startTime2, endTime2, profit2) + "\n");
        
        int[] startTime3 = {1,1,1};
        int[] endTime3 = {2,3,4};
        int[] profit3 = {5,6,4};
        System.out.println("Result 3 -> " + solution.jobScheduling(startTime3, endTime3, profit3) + "\n");
        
    } 

}

/*
 * Intuitions :
 
    1. we have n number of jobs 
        where every job needs to be done from startTime[i] to endTime[i]
        which gives profit[i]
    2. we need to return maximum profit we can take 
    3. condition is
        no two jobs should be overlapping
 
 * Pattern :
 
    1. Core idea
        - we don't want overlapping so if we see any overlapping will ignore it
        - jr overlapping nasel tr will ad it's profit in curr profit
    2. apan one by one all jobs la mhanu consider karu apan ghetoy ha 
        - then check karu that job with others 
        - will check prev one I think it will be easy to get which job can give max profit
        - store max profit till currJob in dp
     
 
 
 * Pseudo Code :
 
    function JobScheduling (startTime, endTime, profit) {
    
        -> Declare variables
            n   -> number of jobs
            dp  -> to store maxProfit till currJob 

        -> will create new array to store [start end profit]
            for(i = 0 to n)

                combinedArray[i][0] = startTime[i]
                combinedArray[i][1] = endTime[i]
                combinedArray[i][2] = profit[i]

        -> Sort combinedArray by end time

        -> Initially add dp[0] = combin[0]
            why ? bcoz initially we take 1st job and then it's pofit will equal to 0'th index job's profit


        -> check other jobs now
            for (curr = 1 to n) 
                for(prev = 0 to curr)

                    -> see if we get any overlapping if yes then continue, we are not taking that prev index 
                        overlapping kevha hote ?
                            - if endTime[prev] > startTime[curr] 
                            - also startTime[prev] should be < startTime[curr] why? bcoz aplyala asa nakoy ki endtime tya prev nighun pn gela and we still saying it's overlap

                            - nahi samjal?
                                see khalchi diagram 
                                1 ---- 3 and 2 ---- 4 time check karu
                                first case  : 3 >= 2
                                second case : 1 < 2  

                                    0    1    2    3    4    5    6 
                                ----|----|----|----|----|----|----|----
                                    |    |_________|_________|    |   
                                    |         |_________|         |
                                    |              |______________|
                                    |

                        if overlaps continue to next
                    
                    -> else store value of curr + dpProfit[prev] in dp
                        jo pn prev profit asel apan tyat curr cha profit add karun store karu
                        
                        currProfit = dp[prev] + profit[curr]
                        
                        we are checking prev so we might have multiple jobs we can take.. but I want max one so 
                        dp[curr] = max(dp[curr], currProfit)

        -> at the end return max between dp 
    
    }


 */