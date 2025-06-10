import java.util.*;

public class JobScheduling {
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
     
        // Declare variable
        int n = startTime.length;
        int[][] jobsArray = new int[n][3];
        int[] dp = new int[n];

        // add values in jobsArray
        for (int i = 0; i < n; i++) {
            
            jobsArray[i][0] = startTime[i];
            jobsArray[i][1] = endTime[i];
            jobsArray[i][2] = profit[i];
        }

        // Sort the array by endTime
        Arrays.sort(jobsArray, (a,b) -> {
            if (a[1] == b[1]) return Integer.compare(a[0], b[0]);   // Start Time
            return Integer.compare(a[1], b[1]);                     // End Time
        });
        System.out.println("Sorted Jobs Array : " + Arrays.deepToString(jobsArray));


        // Add initial value to dp with profit for first day 
        dp[0] = jobsArray[0][2];

        
        // Check other jobs now
        for (int currJob = 1; currJob < n; currJob++) {

            // to debug : stated variable name for starttime and endtime
            int currStartTime = jobsArray[currJob][0];
            int currEndTime = jobsArray[currJob][1];

            // Initialize with max of previous profit or current job's profit
            dp[currJob] = Math.max(dp[currJob - 1], jobsArray[currJob][2]);

            // Find the last non-overlapping job using binary search
            int prevJob = binarySearch(jobsArray, currJob);

            // If a non-overlapping job was found, update dp[curr]
            if (prevJob != -1) {
                
                int currProfit = dp[prevJob] + jobsArray[currJob][2];

                // we are checking  multiple prev so we might need to get max
                dp[currJob] = Math.max(dp[currJob], currProfit);
            }
            else {
                System.out.println("    No non-overlapping job found (prev : -1)");
            }

            System.out.println("  Updated DP Array : " + Arrays.toString(dp));
        }

        return dp[n-1];
    }

    // Helper Function : To get non-overlapping jobs
    private int binarySearch(int[][] jobsArray, int currJob) {

        int left = 0;
        int right = currJob - 1;
        int prevJob = -1;

        // Will perform binary search to find the highest index prev < curr
        while (left <= right) {
            
            int mid = left + (right - left) / 2;

            // If job at mid ends before or at curr's start time, it's non-overlapping
            if (jobsArray[mid][1] <= jobsArray[currJob][0]) {
                
                // Update prev to current mid as a potential candidate
                prevJob = mid;

                // Search right half for a later non-overlapping job (higher index)
                left = mid + 1;
            }
            else {

                // Job at mid overlaps (end time > curr's start time)
                // Search left half for non-overlapping jobs
                right = mid - 1;
            }
        }

        System.out.println("      - [Binary search] prevJob : " + prevJob);
        return prevJob;
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

 ^ Improvement :
    1. let's use Binary search let's not check every prev num will just check non overlaping one between previous


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


    Approach 1 code :

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
     
        -> Declare variable
        int n = startTime.length;
        int[][] jobsArray = new int[n][3];
        int[] dp = new int[n];

        -> add values in jobsArray
        for (int i = 0; i < n; i++) {
            
            jobsArray[i][0] = startTime[i];
            jobsArray[i][1] = endTime[i];
            jobsArray[i][2] = profit[i];
        }

        -> Sort the array by endTime
        Arrays.sort(jobsArray, (a,b) -> {
            if (a[1] == b[1]) return Integer.compare(a[0], b[0]);   // Start Time
            return Integer.compare(a[1], b[1]);                     // End Time
        });
        System.out.println("Sorted Jobs Array : " + Arrays.deepToString(jobsArray));


        -> Add initial value to dp with profit for first day 
        dp[0] = jobsArray[0][2];

        
        -> Check other jobs now
        for (int currJob = 1; currJob < n; currJob++) {

            -> to debug : stated variable name for starttime and endtime
            int currStartTime = jobsArray[currJob][0];
            int currEndTime = jobsArray[currJob][1];

            -> Initialize with max of previous profit or current job's profit
            dp[currJob] = Math.max(dp[currJob - 1], jobsArray[currJob][2]);

            for (int prevJob = 0; prevJob < currJob; prevJob++) {
                
                -> to debug : stated variable name for starttime and endtime
                int prevStartTime = jobsArray[prevJob][0];
                int prevEndTime = jobsArray[prevJob][1];

                -> If jobs are overlapping skip it
                if (prevEndTime > currStartTime) {
                    
                    System.out.println("    Jobs are overlapping | dp Array : " + Arrays.toString(dp));
                    continue;
                }

                -> else will store profit in dp
                int currProfit = dp[prevJob] + jobsArray[currJob][2];

                -> we are checking  multiple prev so we might need to get max
                dp[currJob] = Math.max(dp[currJob], currProfit);

                System.out.println("    Updated DP Array : " + Arrays.toString(dp));
            }
        }

        return dp[n-1];
    }

 */