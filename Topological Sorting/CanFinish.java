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

 ^ Improvements :

    1. what is basic of DFS :
        - Base Case :
        - mark cuurNode as visited
        - check for neighbors 
    2. will use visited Array not set 
        - bcoz will check for 3 stages
        - not visited : 0
        - visiting : 1 -> means in recursion current node is that it's for detecting cycle
        - visited : 2  -> means the currNode is already visited
        - why this ? ask gpt u did that to make sure ur logic was right
    3. if cycle detected return false
    4. if it's already visited return true
    5. otherwise
        - mark currNode as visiting
        - check it's neighbors and use recursion 
        - mark currNode as visited
        - why this u remeber we were adding nums in stack after checking it's neighbors
            it's the same process just instead of stack we are return true or false directly
            we don't want too much time complexity
    6. why we are using visitedArray = new Array(numCourses)
        - numCourses -> Total number of distinct courses (nodes)
        - prerequisites -> List of edges b -> a (course dependencies)
        - new Array(numCourses) -> Ensures we track all courses (even those with no prerequisites)
    7. why only map.put(prerequisites[i][2], prerequisites[i][1])
        - as I already know there are only 0 and 1 in prerequisites[i]
        - using map is my decision not gpt's now I took help there
    8. why saying for(i = 0 to numCourses) or visitedArray = new Array(numCourses) ... why numCourse?
        - The prerequisites array only lists relationships, not all courses.
        - Let’s take this input:
                numCourses = 4;
                prerequisites = [[1, 0]];

            This means:
                - There are 4 courses total: 0, 1, 2, 3
                - But the prerequisites array only mentions that:
                - To take course 1, you must take course 0
                - So: 0 → 1
                - But what about course 2 and course 3?
                - They’re not in the prerequisites list — why?
                    Because:
                        They have no prerequisites
                        And no one depends on them
                        They’re basically independent courses
            → They are still part of the graph, just disconnected nodes




 * Pseudo Code :
 
 ^ Improved appraoch :
    
    function canFinish (numCourses, prerequisites) {
    
        -> declare variables
            n = prerequisites.length
            visitingState = new Array(numCourses).fill(0)       -> array fill with 0 at start as we haven't visited any node yet
            map = hashmap                                       -> to store all course dependancy
            bool result

        -> Add key and value in map
            for(i = 0 to n)
                if(!map.contains(prerequisites[i][1]))
                    map.put(prerequisites[i][1], arrayList)

                map.put(prerequisites[i][1]).add(prerequisites[i][0])

        -> let's check array now
            for(i = 0 to numCourses)
                result = dfs(i, visitingState, map)

                -> if result is false return false
                    if(!result) return false 
    

        return true
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
    
    

  Last Appraoch  :

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