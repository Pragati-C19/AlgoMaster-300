import java.util.*;

public class MincostToHireWorkers {
    
}

/*
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
 *      quality = [10,20,5], wage = [70,50,30], k = 2
 * 
 *      - Workers 0 and 2 were selected
 *      - They should be paid at the same rate (70 or 30). 
 *      - Worker 0 has twice the quality (10) compared to worker 2 (5), so he should be paid double the price of worker 2.
 *      - If we choose 30 as the base rate, then we pay 30 to worker 2, and 30 * 2 = 60 to worker 0, which is lower than his minimum pay, and We cant pay any worker below his minumun wage.
 *      - So we have to choose 70 as the base rate. Then, we have 70 for worker 0, and 70/2 = 35 for worker 2.
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 */
