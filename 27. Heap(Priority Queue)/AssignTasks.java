import java.util.*;

public class AssignTasks {
    
    public int[] assignTasks(int[] servers, int[] tasks) {
        
        int m = tasks.length;
        int n = servers.length;

        int[] result = new int[m];
        int currentTime = 0;

        // It will store all available servers
        PriorityQueue<int[]> availableHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);   // server weight
            return Integer.compare(a[1], b[1]);                     // original index
        });
        
        // It will store all busy servers
        PriorityQueue<int[]> busyHeap = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]);   // available time
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);   // server weight
            return Integer.compare(a[1], b[1]);                     // original index
        });

        // Adding all server in available heap (bcoz at start all servers will be availble)
        for (int i = 0; i < n; i++) {
            availableHeap.add(new int[] {servers[i], i});
        }

        // checking all task now 
        for (int i = 0; i < m; i++) {
            
            int task = tasks[i];
            currentTime = Math.max(currentTime, i);

            while (!busyHeap.isEmpty() && busyHeap.peek()[2] <= currentTime) {
               int[] finished = busyHeap.poll();
               availableHeap.add(new int[]{finished[0], finished[1]});
            }

            if (availableHeap.isEmpty()) {

                // let's updated the task first to that specific server
                currentTime = busyHeap.peek()[2];
        
                while (!busyHeap.isEmpty() && busyHeap.peek()[2] <= currentTime) {
                    int[] finished = busyHeap.poll();
                    availableHeap.add(new int[]{finished[0], finished[1]});
                }
                
            }
            
            int[] server = availableHeap.poll();
            result[i] = server[1];
            System.out.println("    Current Result Array : " + Arrays.toString(result));
            busyHeap.add(new int[]{server[0], server[1], currentTime + task});
         
        }

        return result;
        
    }

    public static void main(String[] args){

        AssignTasks solution = new AssignTasks();

        int[] servers1 = {3, 3, 2};
        int[] tasks1 = {1, 2, 3, 2, 1, 2};
        System.out.println("-> Result 1: " + Arrays.toString(solution.assignTasks(servers1, tasks1)) + "\n");

        int[] servers2 = {5,1,4,3,2};
        int[] tasks2 = {2,1,2,4,5,2,1};
        System.out.println("-> Result 2: " + Arrays.toString(solution.assignTasks(servers2, tasks2)) + "\n");        

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
 * After refined solutions is :
 * 
 * 1. I need to Declare below things
 *      - m and n length of arrays
 *      - int [] result
 *      - currTime = 0
 *      - serverIndex = 0
 *      - create a new updatedServer array which has original Index, and availableTime in it
 *          [serverWeight, OriginalIndex, availableTime]
 *      - sort that updatedServers
 * 2. Create 2 minHeaps
 *      - First to heap to check if server is available or not 
 *          it will store serverWeight and original Index
 *      - Second heap to check if server is busy or not
 *          it will store ServerWeight, availableTime, OriginalIndex
 * 3. use for loop and add all servers in available heap first 
 *      bcoz at start every server is available na
 * 4. for(i = 0 to tasks.length)
 *      - taskTime = task[i]
 *      - Move all servers that are done with tasks back to available
 *          if(!busyHeap.isEmpty && busyHeap.peek()[0] <= currTime) 
 *              -> poll that server and add it to available heap
 *      - If no server available, fast-forward time to the next server's available time
 *          currTime = busy.peek()[0]
 *          again check while loop as above to move all servers that are done with tasks back to available
 *      - if above conditions are not true then do below 
 *          poll server from available
 *          add it in result
 *          add it in busyHeap too
 * 
 * 
 * 
 */
