import java.util.*;

public class FindRedundantConnection {
    
    // Globally Declare variable
    int[] removableEdge;
    Map<Integer, List<Integer>> graphMap;

    // Driver Function
    public int[] findRedundantConnection(int[][] edges) {
        
        // Declare variables
        int n = edges.length;
        int[] visitingState = new int[n + 1];   // node numbers go up to n, not n-1, so this causes ArrayIndexOutOfBoundsException
        removableEdge = new int[2];
        graphMap = new HashMap<>();

        // add dependencies in map
        for (int[] edge : edges) {
            
            // add in the edge[0]'s list
            if (!graphMap.containsKey(edge[0])) {
                graphMap.put(edge[0], new ArrayList<>());
            }

            graphMap.get(edge[0]).add(edge[1]);

            // add in the edge[1]'s list
            if (!graphMap.containsKey(edge[1])) {
                graphMap.put(edge[1], new ArrayList<>());
            }

            graphMap.get(edge[1]).add(edge[0]);
        }
        System.out.println("Graph Map : " + graphMap);


        // call dfs for all nodes
        for (int node = 1; node <= n; node++) {
            
            if (visitingState[node] == 0) {
                dfs(node, node, visitingState);
            }
            
        }

        return removableEdge;
    }

    // Recursion Function : to check cycle detection
    private void dfs (int currNode, int parentNode, int[] visitingState) {

        if (visitingState[currNode] == 1) {
            
            removableEdge[0] = currNode;
            removableEdge[1] = parentNode;
            System.out.println("    Cycle Detetcted so adding nodes in removable edge " + Arrays.toString(removableEdge));
            
            return;
        }

        if (visitingState[currNode] == 2) {
            
            System.out.println("    Node (" + currNode + ") is already visited..");
            return;
        }

        // Mark as visiting
        visitingState[currNode] = 1;

        // Check neighbors
        List<Integer> neighborOfCurrNode = graphMap.get(currNode);

        for (int neighbor : neighborOfCurrNode) {
            
            if (neighbor == parentNode) {
                continue;
            }
            dfs(neighbor, currNode, visitingState);
        }

        // Mark as visited
        visitingState[currNode] = 2;

        return;
    }


    public static void main (String[] args) {

        FindRedundantConnection solution =  new FindRedundantConnection();

        int[][] edges1 = {
            {1, 2},
            {1, 3},
            {2, 3}
        };
        System.out.println("Result 1 : " + Arrays.toString(solution.findRedundantConnection(edges1)) + "\n");

        int[][] edges2 = {
            {1, 2},
            {2, 3},
            {3, 4},
            {1, 4},
            {1, 5}
        };
        System.out.println("Result 2 : " + Arrays.toString(solution.findRedundantConnection(edges2)) + "\n");
    }
}

/*
 * Intuitions :
 
    1. we have given a graph with size n labeled from 1 to n
    2. we have given a array edge which tells use there a edge between those two nodes
    3. return an edge that can be removed so that the resulting graph is a tree of n nodes
    4. if there are multiple ans return the ans that occurs last in the input
 
 
 * Pattern :
 
    ^ Trace Example :

        1. edges = [[1,2],[1,3],[2,3]]

            1 ------ 2          
             \      /               - If we remove [2, 3] edge we can get a tree
              \    /                - tree is not fully cinnected right?.. there are a leaf node 
               \  /                 - if we see there is any cyclic connection we'll try to break it?
                \/               
                3

        2. edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]

            2 ------ 1 ------ 5     - there is a cycle at [1, 2, 3, 4]
            |        |              - let's remove any on that edge it will become a tree
            |        |              
            3 ------ 4
            
            - me adhi depemndencies add kelya graphMap madhe
                1 -> 2, 4, 5
                2 -> 1, 3
                3 -> 2, 4
                4 -> 1, 3
                5 -> 1

            - currNode 1 parentNode 1
                mark as visited and check it's neighbor
                neighbor 2 parentNode 1
                    mark visited and check it's neighbor
                    neighbor 1 == parentNode -> skip
                    neighbor 3 parentNode 2
                        mark as visited and check it's neighbor
                        neighbor 2 == parentNode -> skip
                        neighbor 4 parentNode 3
                            mark as visited and check it's neighbor
                            neighbor 1 parentNode 4
                                cycle detected 1 is in visiting state
                                add [1, 4] in currRemovedEdge

            - currNode 2 -> already visited
            - currNode 3 -> already visited
            - currNode 4 -> already visited
            - currNode 5 parentNode 5
                mark as visited and check it's neighbor
                neighbor 1 parentNode 5
                    already visited

    1. let's write psuedo code as I have traced the example
    2. why we are using parentNode ?
        I don't want to detect wrong cycle 
        I want cycle other than parent so that I can break it
 
    ^ Changes the approach : DFS but with more optimal solution

        1. we'll add edge one by one in graphMap
        2. but before adding it will check if the edg[0] and edge[1] exist in graphmap already?
        3. and also check if it has any path ?
            -> if yes then adding (u, v) would create a cycle 
            -> if no add the edge in graphMap
        4. DFS function :
            - Is there already a path from curr to target?
                if(currNode == targetNode) 
                    if yes return true

                let's visiualize it
                    suppose we already have visited [1, 2, 3]
                    now we are about to add: [1, 3]
                    asking Can I already go from 1 to 3 using existing edges?
                    DFS explores:
                        From 1 → goes to 2
                        From 2 → goes to 3
                        Found 3!
                        So during DFS, when we're at curr = 3 and target = 3
                        which means cycle detected
            
            - check neighbors
                if(!visited && dfs(neighbor, targetNode, visited) = true)
                    return true
                    

 * Pseudo Code :
 
    -> Declare global variable
        int[] removableEdge
        Map<Integer, List<Integer>> graphMap 

    function findRedundantConnection (int[][] edges) {
    
        -> declare variables
            n = edges.length            -> Constraints
            graphMap
            removableEdge

        -> add edges in map
            for(int[] edge : edges)

                if(!map.contains(edge[0]))
                    map.put(edge[0], new array)

                if(!map.contains(edge[1]))
                    map.put(edge[1], new array)

                map.get(edge[0]).add(edge[1])
                map.get(edge[1]).add(edge[0])

        -> call dfs
            for(node = 1 to n)      - bcoz que says node start from 1 to n

                dfs(node, node, visitingState)

        -> return removableEdge
    
    }
 
    dfs (currNode, parentNode, visitingState) {

        if(visitingState[currNode] == 1) 
            Cycle Detected
            removableEdge[0] = currNode
            removableEdge[1] = parentNode

        if(visitingState[currNode] == 2)
            already visited

        -> mark as visiting
            visitingState[currNode] = 1

        -> check neighbors
            for(neighbor : map.get(currNode))
                dfs(neighbor, currNode, visitingState)
            
        -> mark as visited
            visitingState[currNode] = 2

    
    }
 
 */