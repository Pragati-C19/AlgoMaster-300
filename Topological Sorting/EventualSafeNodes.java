import java.util.*;

public class EventualSafeNodes {
    
    // Globally Declare variables


    // Driver Function 
    public List<Integer> eventualSafeNodes(int[][] graph) {
        
        List<Integer> result = new ArrayList<>();


        return result;
    }

    // Recursion Function : to check if any cycle detected
    private boolean dfs(int currNode, int[] visitingState) {

        return true;
    }

    public static void main(String[] args) {

        EventualSafeNodes solution = new EventualSafeNodes();

        int[][] graph1 = {
            {1, 2},
            {2, 3},
            {5},
            {0},
            {5},
            {},
            {}
        };
        System.out.println("Result 1 : " + solution.eventualSafeNodes(graph1) + "\n");

        int[][] graph2 = {
            {1, 2, 3, 4},
            {1, 2},
            {3, 4},
            {0, 4},
            {}
        };
        System.out.println("Result 2 : " + solution.eventualSafeNodes(graph2) + "\n");

    }

}

/*
 * Intuitions :
 
    1. we have given a directed graph of n nodes (0-indexed)
    2. graph[i] is an neighbors of i
    3. Terminal Node -> a node if there are no outgoing edges 
    4. Safe Node -> if every possible path starting from that node leads to a terminal node
        or another safe node
    5. return array contains all safe nodes
    6. array should be in ascending order
 
 * Pattern :
 
    1. when I tried to trace eamaples I think when we get cycle detected we are skipping that node and checking the next node
    2. like start from second node and check if it goes to end
    3. will use basic DFS pattern with boolean
        - Base Case : 
            if(visited == 1) cycle detected -> return false
            if(visited == 2) already visited -> return true
        - mark currCourse as visiting
        - check it's neighbors and call dfs for them
        - now mark currCourse as visited
        - add values in stack here
            stack.push(currCourse) 
        - return true
    4. driverFunction :
        - we need to focus on avoiding num with detected cycle
        - will add dependencies in graphmap
        - then start for loop till n
            call dfs 
            if(dfs == true)
                result.add(i)
            else check next index
        - return result in ascending order
        - I think we don't need stack here
 
 * Pseudo Code :
 
    Gloablly Declare variables
        graphMap

    eventualSafeNode (graph) {
    
        -> Declare variables
            n = prerequisites.length
            visitingState = new int[numCourses]
            graphMap = new Hashmap
            result

        -> add dependencies in graphMap
            for(i = 0 to n)
                if(!map.contains(i))
                    map.put(i, arrayList)

                -> atta aplya grpah[i] chya length different ahet so used for loop again 
                    for(j = 0 to graph[i].length)
                        map.get(i).add(graph[i][j])
        
        -> call dfs
            for(node = 0 to n)
                
                -> if we get true and not any cycle detected will add that i in result
                if(dfs(node, visitingState))    
                    result.add(i)
        
        -> return result

    }

    dfs (currNode, visitingState) {
    
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