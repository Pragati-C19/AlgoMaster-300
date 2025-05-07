import java.util.*;

public class MincostToHireWorkers {
    
}

/*
 * 
 * 
 * //! todo: Solve this que later.. keep this in pending for now
 * 
 * Intuitions :
 * 
 * 1. We need to form paid worker group of k workers
 * 2. To hire people we need to follow below conditions :
 *      - Every worker must be paid at least there minimun wage or more
 *      - If we form a group of worker then each worker's pay must be directly proportional to their quality.
 *      - This means if a worker’s quality is double that of another worker in the group, then they must be paid twice as much as the other worker. 
 * 3. return the least amount of money needed to form a paid group
 * 
 * 
 * Pattern :
 * 
 * 1. Trace Example : Doing it with help of discussion prompt under the que
 *      
 *     1.  quality = [10,20,5], wage = [70,50,30], k = 2
 * 
 *      - Workers 0 and 2 were selected
 *      - They should be paid at the same rate (70 or 30). 
 *      - Worker 0 has twice the quality (10) compared to worker 2 (5), so he should be paid double the price of worker 2.
 *      - If we choose 30 as the base rate, then we pay 30 to worker 2, and 30 * 2 = 60 to worker 0, which is lower than his minimum pay, and We cant pay any worker below his minumun wage.
 *      - So we have to choose 70 as the base rate. Then, we have 70 for worker 0, and 70/2 = 35 for worker 2.
 * 
 * 
 *    2. quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 * 
 *      - Let's take wage[i]/quality[j] ratio of all workers
 *              1.33, 8, 0.2, 0.2, 7
 *      - Create a group of 3 peoples like
 *          tya group madhe ek maxRatio havay aplyala..
 *          eg. [3, 1, 10] yat max ratio ahe 8 
 * 
 * 2. After so many GPT asking I come up with solution that
 *      Total cost = maxRatio * totalQualityOfKWorkers
 * 
 *      Let’s say 
 *  
 *          - Worker A: quality = 3, wage = 4 → ratio = 1.33
 *          - Worker B: quality = 10, wage = 2 → ratio = 0.2
 *          - Worker C: quality = 10, wage = 2 → ratio = 0.2
 *  
 *  Step 1: Compute max ratio among these = 1.33 (from Worker A)
 *  Step 2: Total quality = 3 + 10 + 10 = 23
 *  Step 3: Total cost = 1.33 × 23 = 30.666
 *  Why? Because:
 *  
 *  You’re paying everyone 1.33 per unit of quality
 *  
 *  So:
 *  
 *  A gets 3 × 1.33 = 4 ✅ (meets their wage)
 *  
 *  B gets 10 × 1.33 = 13.3 ✅ (more than 2)
 *  
 *  C gets 10 × 1.33 = 13.3 ✅ (more than 2)
 *  
 *  So everyone is happy, and total is valid.
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 */
