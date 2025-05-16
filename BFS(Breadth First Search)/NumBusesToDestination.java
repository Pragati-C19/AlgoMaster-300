import java.util.*;

public class NumBusesToDestination {
    
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
 
 


 
 */