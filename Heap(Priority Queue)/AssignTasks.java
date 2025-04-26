import java.util.*;

public class AssignTasks {
    
    public int[] assignTasks(int[] servers, int[] tasks) {
        
        int m = tasks.length;
        int n = servers.length;

        int[] result = new int[m];
        int currentTime = 0;
        int serverIndex = 0;

        // Let's update server array and add originalIndex and available Time parametter
        int[][] updatedServers = new int[n][3];
        
        for (int i = 0; i < n; i++) {
            updatedServers[i][0] = servers[i];      // serverWeight
            updatedServers[i][1] = i;               // original index
            updatedServers[i][2] = 0;               // availableTime
        }
        System.out.println("    Updated Server Array : " + Arrays.deepToString(updatedServers));

        // Let's Sort the updatedServers array
        Arrays.sort(updatedServers, (a, b) -> Integer.compare(a[0], b[0]));
        System.out.println("    Sorted Updated Server Array : " + Arrays.deepToString(updatedServers));

        // Declare a minHeap -> refere from getOrder que
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]);   // available time
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);   // server weight
            return Integer.compare(a[1], b[1]);                     // original index
        });

        for (int i = 0; i < m; i++) {
            
            int task = tasks[i];
            currentTime = Math.max(currentTime, i);

            while (serverIndex < n && updatedServers[serverIndex][2] <= currentTime) {
                
                System.out.println("        - [WHILE] Adding server to minHeap " + serverIndex + " : " + Arrays.toString(updatedServers[serverIndex]) + " with current time = " + currentTime);
                
                minHeap.add(updatedServers[serverIndex]);
                serverIndex++;
            }

            if (minHeap.isEmpty()) {

                // let's updated the task first to that specific server
                currentTime = updatedServers[serverIndex][2];
                
                System.out.println("    updatedServer of index " + serverIndex + " : " + Arrays.toString(updatedServers[serverIndex]) + " with current time = " + currentTime);
                
                // adding that serverIndex task to minHeap
                minHeap.add(updatedServers[serverIndex]);
                serverIndex++;
            }
            

                int[] server = minHeap.poll();
                result[i] = server[1];

                System.out.println("    Current Result Array : " + Arrays.toString(result));

                server[2] = currentTime + task;

                System.out.println("    serever adding to the heap is " + Arrays.toString(server));
                minHeap.add(server);
         
            
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
 * Pseudo code :
 * 
 * 
 * 
 */
