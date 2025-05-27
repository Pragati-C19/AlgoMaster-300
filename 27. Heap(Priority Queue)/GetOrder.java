import java.util.*;

public class GetOrder {

    public int[] getOrder(int[][] tasks) {
        
        int n = tasks.length;
        System.out.println("Length of Tasks Array : " + n );

        // Step 1: Create the updatedTask array with enqueueTime, processingTime, and original index
        int[][] updatedTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            updatedTasks[i][0] = tasks[i][0];  // enqueueTime
            updatedTasks[i][1] = tasks[i][1];  // processingTime
            updatedTasks[i][2] = i;            // original index
        }
        
        // Step 2: Sort the tasks by enqueueTime (updatedTasks[i][0])
        Arrays.sort(updatedTasks, (a, b) -> Integer.compare(a[0], b[0]));
        System.out.println("    Sorted Updated Task Array : " + Arrays.deepToString(updatedTasks));

        // Step 3: Declare result array and other variables
        List<Integer> result = new ArrayList<>();
        int currTime = 0;
        int taskIndex = 0;

        // Step 4: Declare the minHeap for processing tasks (sorting by processing time)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]); // processing time
            return Integer.compare(a[2], b[2]); // original index
        });

        // Step 5: While there are tasks in the heap or there are tasks yet to be added
        while (!minHeap.isEmpty() || taskIndex < n) {
            
            // Step 6: Add tasks to the heap that are available by current time
            while (taskIndex < n && updatedTasks[taskIndex][0] <= currTime) {
                
                minHeap.add(updatedTasks[taskIndex]);
                taskIndex++;
            }

            // Step 7: If the heap has tasks, process the task with the smallest processing time
            if (!minHeap.isEmpty()) {

                int[] currTask = minHeap.poll();
                System.out.println("        Current Task which is running : " + Arrays.toString(currTask));
                
                result.add(currTask[2]);  // Add the task's original index to the result
                
                currTime += currTask[1];   // Increase current time by the processing time of the task
                System.out.println("Processed task " + currTask[1] + " at time " + currTime); 
            
            } else {
                
                // Step 8: If no tasks are in the heap, jump to the next available task's enqueueTime
                currTime = updatedTasks[taskIndex][0];
                System.out.println("No task to process, jumping to time " + currTime); 
            }
        }

        // Step 9: Convert result list to array and return

        int[] resArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resArray[i] = result.get(i);
        }
        return resArray;
    }

    public static void main(String[] args) {
        
        GetOrder solution = new GetOrder();

        int[][] tasks1 = {{1, 2}, {2, 4}, {3, 2}, {4, 1}};
        System.out.println("Result 1: " + Arrays.toString(solution.getOrder(tasks1)) + " \n");

        int[][] tasks2 = {{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}};
        System.out.println("Result 2: " + Arrays.toString(solution.getOrder(tasks2)) + " \n");

    }

}


/*
 * 
 * //? Check out this video for more clarity
 * https://www.youtube.com/watch?v=9zRXNLbl0FI&ab_channel=codestorywithMIK
 * 
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
 * 1. need to sort tasks array
 * 2. sort kel tr original index change hotil array chya so array madhe apan original index pn store karu
 *      - [enqueTime, processingTime, originalIndex]
 *      - sort by enqueTime 
 * 2. Will declare a minHeap to store available tasks till that first task is getting completed
 *      - will add pair of processingTime, originalIndex
 * 2. result = []
 * 3. currTime = 0
 * 4. taskIndex = 0 -> to track index of task as using for loop is so lengthy and confusing
 * 5. while (heap is not empty)
 *      a. WHILE (taskIndex < tasks.length AND tasks[taskIndex].enqueueTime <= currentTime):
 *           → Add (processingTime, originalIndex) to minHeap
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
 * function getOrder (int[][] tasks){
 * 
 *      int n = task.length
 *      int[][] updatedTask = new int[n][3] 
 * 
 *      for(i = 0 to n)
 *          - updatedTask[i][0] = tasks[i][0]   // enqueueTime
 *          - updatedTask[i][1] = tasks[i][1]   // processingTime
 *          - updatedTask[i][2] = i             // add index
 * 
 *      Sort array -> Array.sort(updatedIndex)
 * 
 *      declare a result array to store result
 *      currTime = 0
 * 
 *      taskIndex = 0
 * 
 *      Declare a minHeap
 *          PriorityQueue<Integer, Integer> minHeap = new PriorityQueue
 * 
 *      while(!minHeap.isEmpty)
 *          
 *          -> while(updatedTask[taskIndex][0] <= currTime)
 *                  minHeap.add(updatedTask[taskIndex][1], updatedTask[taskIndex][2])
 *                  taskIndex++
 * 
 *          -> if(!minHeap.isEmpty)
 *                  minHeap.poll()
 *                  result.add(updatedTask[taskIndex][2]
 *                  currTime += updatedTask[taskIndex][1]
 * 
 *          -> else 
 *                  currTime = updatedTask[taskIndex][0]  
 * 
 *      return result
 * }
 * 
 */