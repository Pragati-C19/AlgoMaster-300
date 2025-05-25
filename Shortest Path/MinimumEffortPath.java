import java.util.*;

public class MinimumEffortPath {
    
}

/*
 * Intuitions :
 
    1. we are a hiker and preparing for upcomming hike
    2. we have given heights (row, col)
    3. we are standing at [0, 0] and our target is to go to [row - 1, col - 1]
    4. we can move BRUL
    5. we need to find route that requires minimum effort
    6. A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
        what is this ?
    7. return minEffortRequired
 
 
 * Pattern :
 
    1. We need a minHeap to store {row, col, effortsSoFar}
        - effortsSoFar : maximum height difference so far along the path to reach this cell
        - minHeap will help us to explore list Effort path
        - I'm a bit confused that shoul I use minHeap or maxHeap..
        - I think minHeap will be better here as we want minimum efforts
    2. we need a directionMatrix array
    3. will initially add {0, 0, heights[0][0]} in minHeap
    4. like maxProbability que here also we need a array which tells bestEfforts
        - which tracks the minimum effort needed to reach each cell 
        - initially add 1 at [0, 0]
    5. start while loop till minHeap get's empty
        pop the cell with the least effortSoFar from minHeap
        currRow = pop[0]
        currCol = pop[1]
        currEffort = pop[2]

        - if row and col hit to an end then 
            return the currEffort

        - check BRUL directions
            - get abs diff between currCell and neighbor
                diff = currCellHeight - neighborCellHeight
                
            - get maximum effort one
                neighborEffort = max(currEffort, diff)

            add neighbor row and col with neighborEffort in minHeap

    6. return 0
 
 

 */