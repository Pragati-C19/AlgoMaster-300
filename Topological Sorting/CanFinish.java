import java.util.*;

public class CanFinish {
    
}

/*
 * Intuitions :
    
    1. We have given an array prerequistes which indecates that to take course[0] we need to first take course[1]
            - example : [[1,0]] means we need to take course 1 before we should take course 0 (
            - 1 -> 0
    2. prerequisites[i].length == 2
            - means pratek index of prerequisites jo array asel to sangtoy maza [node, neighbor] kay ahe te
            - node -> neighbor
    3. we alos have given an numCourses int which says we can take that much courses


 * Pattern :
 
    1. To solve any topological sorting we are doing normal BFS or DFS just adding stack after checking neighbors of that node
    2. will add all neighbors of that node then add node in the stack
    3. at the end will pop out the stack
    4. that will be a topological order
    5. in this que if we get topological order that means true otherwise false
    6. we don't want cyclic graph we need to find it 
    7. I think basic code will look like this : 
        if(visitedSet.contains(prerequisites[i][1]))
            - neighbor is only one here so not using for loops
                visitedSet.add(prerequisites[i][2])
        
        stack.add(prerequisites[i][1])
    

 * Pseudo Code :
 
    function canFinish (numCourses, prerequisites) {
    
        -> declare variables
            n = prerequisites.length
            visitedSet
            stack
        

        -> let's check array now
            for(i = 0 to n)
                dfs(prerequisites[i], visitedSet, numCourses)
    
        -> get result from stack
            if(stack.isEmpty)   -> return false 
            else -> return true
    }
        
    dfs(u, visitedSet, numCourses) {
    
        -> [0, 1], to take course 0 you have to first take course 1.
            if(visitedSet.contains(u[2]))
                visitedSet.add(u[1])
                stack.add(u[1])
                numCourses--
    }
    
 */