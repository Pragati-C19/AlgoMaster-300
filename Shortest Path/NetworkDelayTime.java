package Shortest Path;

public class NetworkDelayTime {
    
}

/*
 * Intuitions :
 
    1. we ahve given n nodes numbered 1 to n
    2. we have given time array where it says
        - u : source node, starting point from signal travels
        - v : target node, end point where signal goes
        - w : time taken by nodes to travel from u to v 
    3. will send signal from node k 
    4. minimum time it takes for all the n nodes to receive the signal
 
 * Pattern :
 
    1. This feels like an MST problem
    2. I'm thinking of using minHeap to store shortest time from any point 
        - we are storing the node we are about to visit next 
        - and the time is the shortest known time to reach that node


    ^ Trace Example :

      1. times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2

        - initially will store values in minHeap
            minHeap.add(new int {k, 0})
            means we are adding the starting node from k

                minHeap = { [2, 0] }

        - We need an map maybe to store dependencies like source and destination 
            map which stores source -> destination and time array

                2 -> [1, 1] , [3, 1]
                3 -> [4, 1]

        - I need a boolean[] to say if that source is visited or not

        - Store Time to reach for each node
            int[] minTime = new int[n + 1];
            minTime[k] = 0

        - start a while (!minHeap.isEmpty)
            popOutElement  
            currNode = pop[0]
            currNodetime = pop[1]

            mark currnode as visited

            check it's neighbors
                for(neighbor : map.get(popNode))

                    timeToGetHere = currNodeTime + neighborNodeTime

                    if(!visited(neighbor) && timeToGetHere < minTime[neighbor])     // we need minimum time to reach to this neighbor node
                        
                        - change minTime to timeToGetHere
                        - add this node and timeToGetHere in minHeap
                        
            
        -> Get max time to reach any node same as u know there was a que in which we need to tell info to subordinates and need to count time it's like that
            int maxTime = 0;
            for (int i = 1; i <= n; i++) {
                if (minTime[i] == -1) return -1; // unreachable node
                maxTime = Math.max(maxTime, minTime[i]);
            }

        - return maxTime    
 
 * Pseudo Code :
 



 */