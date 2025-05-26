import java.util.*;

public class FindItinerary {
    
}

/*
 * Intuitions :
 
    1. we have given a list of tickets 
        - where ticket[i] represents departure and the arrival
    2. all the tickets belong to man who departs from "JFK" 
        - means journey must begin from JFK always
    3. we need to return whole journey
 
 
 * Pattern :
 
    1. let's create a grpahMap to write dependncies
    2. call dfs (currDestination, visitingState, currResult)
        Base Case :
            if(visiting) return currResult
            if(visited) return empty string
        mark as visiting
        add currDestination in currResult 
        check neighbors
            for(neighbor : map.get(currDestination))
                if not visited 
                    currDestination = dfs (neighbor)
        mark as visited
        return currResult
    3. FinalResult = above dfs call
 
 
 * Pseudo Code :
 
    

 */