import java.util.*;

public class NumBusesToDestination {
    
    public int numBusesToDestination(int[][] routes, int source, int target) {
        
        return -1;
    }

    public static void main(String[] args) {

        NumBusesToDestination solution = new NumBusesToDestination();

        int[][] grid1 = {
            {1, 2, 7},
            {3, 6, 7}
        };
        System.out.println("Result 1 : " + solution.numBusesToDestination(grid1, 1, 6) + "\n");         // 2

        int[][] grid2 = {
            {7, 12},
            {4, 5, 15},
            {6},
            {15, 19},
            {9, 12, 13}
        };
        System.out.println("Result 2 : " + solution.numBusesToDestination(grid2, 15, 12) + "\n");       // -1

    }

}

/*
 * Intuitions :
 
    1. we have given an int[][] array representing bus routes
        where route[i] means i'th bus repeatedly follows that route
        ex. routes[0] = [1, 2, 7] this means that the 0th bus travels in the sequence 1 -> 2 -> 7 -> 1 -> 2 -> 7 -> 1 -> ... forever
    2. you'll start at the bus stop source and you want to go to bus stop target
    3. initially u are not on any bus 
    4. Return the least number of buses you must take to travel from source to target. 
    5. Return -1 if it is not possible.
 
 * Pattern :
 
    1. source jo asel adhi mala to check karaychay to ahe ka kontya routes[i] madhe?
    2. asel tr me tya route[i] madhle konte elements ashe ahet je mala target parynt netil
    3. jr asel tr good will add that path in queue
    4. nasel tr will skip that path
    5. will use hashmap to store busStop -> busIndex

 ^ Trace example :

    routes = [[1,2,7],[3,6,7]]

        - busIndex : 0, 1
        - busRoute : for 0th bus -> [1,2,7] | for 1st bus -> [3,6,7]
        - busStop  : 1, 2, 3, 6, 7
        - so how hashmap will look like
            1 -> [0]
            2 -> [0]
            7 -> [0, 1]
            3 -> [1]
            6 -> [1]

        - that means our path are like

            Stop 1 — route[0] —> Stop 2  
                    |  
                    v  
                Stop 7 — route[1] —> Stop 3  
                                        |
                                        v
                                     Stop 6
            
            From stop 1 (source), you ride route[0] → you get access to [1,2,7]
            At stop 7, you can change to route[1] because it also visits 7
            Then from route[1], you can go to stop 6 (target)

        - BFS Level 0 (0 buses taken):
            Start from source = 1
                - It's in route[0]
                - Visit all stops in route[0]: 1, 2, 7
                - Queue becomes: 2, 7 (don’t re-add 1 bcoz we have already visited it),
                - Mark route[0] as visited
                - Visited stops: {1, 2, 7}

        - BFS Level 1 (1 bus taken):
            Pop 2:
                - it's in route[0], already visited
                - nothing to do

            Pop 7:
                - it's in route[1]
                - not visited → visit route[1]
                - from route[1], access stops: 3, 6, 7
                - 6 is the target! 

        - So, we reached in 2 buses:
            route[0] (1 → 7)
            route[1] (7 → 6)



 
 * Pseudo Code :
 
 
    function numBusesToDestination (routes, source, target) {
    
        -> Declare variables
            n = routes.length
            queue               -> to store busStops
            visitedBusStop      -> it will check if that bus stop is visited or not
            visitedBusIndex     -> it will check if any bus is visited?.. means whole route of that bus ia already visited?
            map                 -> it will store busStop -> busIndex
            busCount = 0        -> it will check how many buses we have changed

        -> add key and value in map
            for(i = 0 to n)
                for(j = 0 to routes[i].length)
                    if(!map.contains(routes[i][j]))
                        map.put(routes[i][j], new ArrayList)
                    
                    map.get(routes[i][j]).add(i)

        -> Initially add bus stop in queue 
            queue.add(source)
            visitedBusStop.add(source)
            
        -> check for others routes now
            while(!queue.isEmpty)
                queueSize = queue.size

                for(i = 0 to queueSize)
                    
                    popBusStop = queue.poll

                    if(popBusStop == target)
                        return busCount

                    -> checking what buses are available from this stop.
                        busIndexes = map.get(popBusStop)

                    -> Looping over bus routes we can take from this stop
                        for(index : busIndexes)
                        
                        if(!visitedBusIndex(index)) {
                        
                            -> check all stops of that BusIndex
                                for(j = 0 to routes[index].length)

                                    -> if that bus stop from bus Index is not visited then add it in queue 
                                    if(!visitedBusStop(routes[index][j]))
                                        queue.add(routes[index][j])
                                        visitedBusStop.add(routes[index][j])
                            
                            -> mark this busIndex as visited
                                visitedBusIndex.add(index)

                        } 
                       
                -> increase busCount++

        return -1;
    
    }

 */