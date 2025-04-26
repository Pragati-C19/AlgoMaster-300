import java.util.*;

public class AssignTasks {
    
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
 *      - create a new updatedServer array which has index in it
 *      - sort that updatedServers
 *      - minHeap to store updatedServers 
 * 2. for loop (task : tasks)
 *      - if(minHeap.isEmpty && task[index] <= currTime) -> minHeap.add(updatedServers[0], updatedServers[1]) adding server and original index
 *      - if(!minHeap.isEmpty) -> 
 *              minHeap.poll()
 *              result.add(updatedServers[1])
 *              currTime += task
 *      - stuck at how to add s2 again in heap.. 
 *      
 * 
 * Pseudo code :
 * 
 * 
 * 
 */
