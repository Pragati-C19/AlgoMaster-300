import java.util.*;

public class AssignTasks {
    
    public int[] assignTasks(int[] servers, int[] tasks) {
        
    }

    public static main(String[] args){

        AssignTasks solution = new AssignTasks();

        int[] servers1 = {3, 3, 2};
        int[] tasks1 = {1, 2, 3, 2, 1, 2};
        System.out.println("-> Result 1: " + solution.assignTasks(servers1, tasks1) + "\n");

        int[] servers2 = {5,1,4,3,2};
        int[] tasks2 = {2,1,2,4,5,2,1};
        System.out.println("-> Result 2: " + solution.assignTasks(servers2, tasks2) + "\n");        

    }
}

/*
 * 
 * 
 * Intutions :
 * 
 * 1. severs array -> weight of server
 * 2. tasks array -> process time
 * 3. let's sort servers and add original index too in that updated server list
 * 4. will store servers in minHeap
 *      -> if server has tie then will add server with it's index
 * 5. task[index] is running on the server send by minheap
 *      -> if server is busy then check another server
 *      -> after server completes the task then add another task in that server
 * 
 * 
 * Pattern :
 * 
 * My logic :
 * 
 * 1. I need to Declare below things
 *      - int [] result
 *      - currTime = 0
 *      - serverIndex = 0
 *      - create a new updatedServer array which has original Index, and availableTime in it
 *          [serverWeight, OriginalIndex, availableTime]
 *      - sort that updatedServers
 *      - minHeap to store updatedServers 
 * 2. for loop (task : tasks)
 *      - if(minHeap.isEmpty) -> 
 *              updatedServers[serverIndex][2] = currTime + task 
 *              minHeap.add(updatedServer[serverIndex])
 *              serverIndex++
 *      - if(!minHeap.isEmpty && minheap(updatedServers[2]) <= currTime) -> 
 *              server = minHeap.poll()
 *              result[i] = server[1]
 *              server[2] = currentTime + task
 *              minHeap.add(server)
 *      
 * 
 * Pseudo code :
 * 
 * 
 * 
 */
