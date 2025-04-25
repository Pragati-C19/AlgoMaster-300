import java.util.*;

public class GetOrder {
    
}


/*
 * 
 * Intuitions :
 * 
 * 1. We have given a 2D integer array tasks
 * 2. in that two things are given [enqueueTime, processingTime]
 * - enqueueTime -> ith task will be available to process at this time
 * - processingTime -> will take this much time to finish that task
 * 3. U have single threaded cpu that can process at most one task at a time
 * 4. Conditions
 * - if cpu is idle and there are no tasks to process -> cpu remains idle
 * - if cpu is idle and there are task available -> it will choose the shortest processing time
 * - if multiple tasks have the same shortest processing time then it will choose the task with smallest index
 * 5. return the order in which the cpu will process the tasks
 * 
 * 
 * This ques is a bit tricky
 * 
 * tasks = [[1, 2], [2, 4], [3, 2], [4, 1]]
 * 
 * Index   Enqueue  Process
 *   0        1         2
 *   1        2         4
 *   2        3         2
 *   3        4         1
 * 
 * -> enqueueTime -> kevha taknar ahe cpu madhe to time
 * -> processingTime -> kiti vel chalnar ahe to task
 * 
 * 1. like currentTime 0 asel tr tyaveles ek pn task nahiye
 * 2. pn tech currentTime 1 zala tr tithe 1 task ahe [1, 2] -> 0th index varcha
 * 3. then to task complete honyasathi processing time = 2 ahe 
 * 4. tr next currentTime asel 1 + 2 = 3, so tyaveles apan 2 task check karnar [2, 4] [3, 2]
 * //! Now this is most trickest part of this que
 * We are building a list of all available tasks up to currentTime.
 * enqueueTime <= currentTime che sagle task add karayche min heap to store available tasks madhe
 * 
 * 5. min heap madhe tasks sort astil as per ProcessingTime
 * 
 * 
 * Core Idea behind the que is :
 * 
 * When multiple tasks are available (enqueueTime ≤ currentTime),
 * we pick the one with the smallest processing time
 * (if tie → smaller original index)
 * 
 * 
 * Pattern :
 * 
 * 1. Will declare a minHeap to store available tasks in it
 * 2. result = []
 * 3. currTime = 0
 * 4. taskIndex = 0 -> to track index of task as using for loop is so lengthy and confusing
 * 5. while (heap is not empty)
 *      a. WHILE (taskIndex < tasks.length AND tasks[taskIndex].enqueueTime <= currentTime):
 *           → Add (processingTime, index) to minHeap
 *           → taskIndex++
 * 
 *     b. IF (minHeap is not empty):
 *           → Pop task with smallest processingTime (and lowest index if tie)
 *           → Add index to result
 *           → currentTime += processingTime
 * 
 *     c. ELSE:
 *           → No task available right now
 *           → Jump currentTime to next available task’s enqueueTime
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 */