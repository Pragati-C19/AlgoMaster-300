import java.util.*;

public class FindOrder {
 
    // Globally Declare variables
    Map<Integer, List<Integer>> graphMap;
    Stack<Integer> stack;

    // Driver Function 
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        // declare variable
        int n = prerequisites.length;
        int[] visitingState = new int[numCourses];
        graphMap = new HashMap<>();
        stack = new Stack<>();

        
        // add dependencies in map
        for (int i = 0; i < n; i++) {
            
            if (!graphMap.containsKey(prerequisites[i][1])) {
                graphMap.put(prerequisites[i][1], new ArrayList<>());
            }

            graphMap.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        System.out.println("Graph Map : " + graphMap);


        // call DFS for all courses now 
        for (int course = 0; course < numCourses; course++) {
            
            if (!dfs(course, visitingState)) {
                    
                return new int[0];
            }
        }
        System.out.println("Stack : " + stack);
        

        // Result array to add all values from stack here
        int[] result = new int[numCourses];
        int index = 0;

        while (!stack.empty()) {
            
            result[index] = stack.pop();
            index++;
        }

        return result;
    }

    // Recursion Function : To check if node and it's neighbors are visited or not
    private boolean dfs(int currCourse, int[] visitingState) {

        // Base Case :
        if (visitingState[currCourse] == 1) {
            
            System.out.println("    Cycle Detected...");
            return false;
        }

        if (visitingState[currCourse] == 2) {
            
            System.out.println("    Course (" + currCourse + ") already visited...");
            return true;
        }

        // if node is unvisited mark it as visiting
        visitingState[currCourse] = 1;

        // check it's neighbors
        List<Integer> neighborsOfCurrNodes = graphMap.get(currCourse);
        
        if (neighborsOfCurrNodes != null) {
            
            for (int neighbor : neighborsOfCurrNodes) {
            
                if (!dfs(neighbor, visitingState)) {
                    
                    return false;
                }
                
            } 
        }

        // mark currNode as visited now as we have checked all it's neighbors
        visitingState[currCourse] = 2;

        // as we have checked all neighbors of currNode add it in stack
        stack.push(currCourse);

        return true;
    }

    public static void main(String[] args) {

        FindOrder solution = new FindOrder();

        int[][] prerequisites1 = {
            {1, 0}
        };
        System.out.println("Result 1 : " + Arrays.toString(solution.findOrder(2, prerequisites1)) + "\n");

        int[][] prerequisites2 = {
            {1, 0},
            {2, 0},
            {3, 1},
            {3, 2}
        };
        System.out.println("Result 2 : " + Arrays.toString(solution.findOrder(4, prerequisites2)) + "\n");

        int[][] prerequisites3 = {
            {0, 1},
            {1, 0}
        };
        System.out.println("Result 3 : " + Arrays.toString(solution.findOrder(2, prerequisites3)) + "\n");      // []

    }
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