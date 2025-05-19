import java.util.*;

public class FindOrder {
    
}

/*
 * Intuitions :
    
    1. There are a total of numCourses courses you have to take from 0 to numCourses - 1
    2. We have given an array prerequistes which indecates that to take course[0] we need to first take course[1]
            - example : [[1,0]] means to take course 1, we have to take course 0 first
            - 0 -> 1
    3. prerequisites[i].length == 2
            - means pratek index of prerequisites jo array asel to sangtoy maza [node, neighbor] kay ahe te
            - node -> neighbor
    4. we need to return ordering of courses you should take to finish all courses
    5. If it is impossible to finish all courses, return an empty array.

 * Patttern :
 
    1. will use map to store dependancies of courses given in prerequisites
    2. we need an array which can mark our courses as 
        - 0 : unvisited
        - 1 : currently visiting -> why this? see any node is not fully visited till it's neighbors are not visited
        - 2 : visited
    2. will call dfs for courses 
        for(i = 0 to numCourses)
    3. DFS :
        - Base Case : 
            if(visited == 1) cycle detected -> return 
            if(visited == 2) already visited -> return
        - mark currCourse as visiting
        - check it's neighbors and call dfs for them
        - now mark currCourse as visited
        ~ this is the new part now will add values in stack here
            stack.push(currCourse)  

 
 * Pseudo Code :
 
    Gloabally declare variables
        map = hashmap              -> to store all course dependancy
        stack                      -> to store courses in order

    function findOrder (numCourses, prerequisites) {
    
        -> declare variables
            n = prerequisites.length
            visitingState = new int[numCourses]       -> array fill with 0 at start as we haven't visited any node yet


        -> Add key and value in map
            for(i = 0 to n)
                if(!map.contains(prerequisites[i][1]))
                    map.put(prerequisites[i][1], arrayList)

                map.put(prerequisites[i][1]).add(prerequisites[i][0])

        -> let's check courses now
            for(i = 0 to numCourses)
                dfs(i, visitingState)

        -> take out values from stack in result array
            int[] result = stack.length
            int index = 0

            while(!stack.isEmpty)
                result[index] = stack.pop()
                index++

        return result
    }
        
    dfs(currNode, visitingState, map) {
    
        -> Base Case :
            if(visitingState[currNode] == 1)    return false
            if(visitingState[currNode] == 2)    return true

        -> mark currNode as visiting
            visitingState[currNode] = 1

        -> check for neighbors 
            for(neighbor : map.get(currNode))
                
                if (!dfs(neighbor, visitingState, map))
                    reutrn false

        -> mark currNode as visited now as we check it's neighbors so
            visitingState[currNode] = 2
        
        -> if everything works we can return as true

    }
 
 */