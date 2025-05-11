import java.util.*;

public class IsBipartite {
    
    // Driver Function 
    public boolean isBipartite(int[][] graph) {
        
        return true;
    }

    // Recursion Function : Set colors to neighbors and node
    private boolean dfs(int currNode, int currNodeColor, int[] colorsOfAllNodes, int[][] graph, int n){

        return true;
    }

    public static void main(String[] arg){

        IsBipartite solution = new IsBipartite();

        int[][] graph1 = {
            {1,2,3},
            {0,2},
            {0,1,3},
            {0,2}
        };
        System.out.println("Result 1 -> " + solution.isBipartite(graph1) + "\n");

        int[][] graph2 = {
            {1,3},
            {0,2},
            {1,3},
            {0,2}
        };
        System.out.println("Result 2 -> " + solution.isBipartite(graph2) + "\n");

    }

}

/*

    ? Understand abt bipartite more
        https://www.youtube.com/watch?v=NeU-C1PTWB8&ab_channel=codestorywithMIK

 *  Intuitions :

    1. We have given an graph array (0-indexed)
    2. graph[i] are neighbors of index i 
    3. bipartite graph 
        - means it's nodes can be partitioned in two sets A and B, 
        - such that every edge in the graph connects a node in set A and a node in set B
    4. equivalent coloring defination of Bipartite Graph
        - Can you color the graph using two colors such that no two adjacent nodes have the same color?
        - If yes then it's bipartite
    5. length defination
        - If we found odd cycle length means it's NOT bipartite
        - otherwise it is

 *  Pattern :

 ^ Trace Example :

    Graph (adjacency list):
                0 -- 1
                |    |
                3 -- 2

    Adjacency List:
            [[1,3], [0,2], [1,3], [0,2]]


    Approach 1: Partition into Sets A and B
        
    -> Try putting nodes in sets such that:

        - If node 0 is in set A
        - Its neighbors 1 and 3 must go to set B
        - Then, 1's neighbor 2 must go to set A
        - 3's neighbor 2 is already in A — still okay

    -> Set's will look like 
        
        - Set A = [0, 2]
        - Set B = [1, 3]

    All edges are across sets. So, graph is bipartite.


    Approach 2: Color with Two Colors

    -> Let’s say:
        
        - Color 0 → Red (Color 1)
        - So its neighbors 1 and 3 → Blue (Color -1)
        - Then 1's neighbor 2 → Red (Color 1)
        - 3's neighbor 2 is already Red → ✅ same as above

    -> Now colors look like
        0 → Red
        1 → Blue
        2 → Red
        3 → Blue
    
    No edge connects two same-colored nodes.

 ^ Approach to Solve this problems
    
    Approach 1 : Color with Two Colors
        
        1. Let's create an array colorsOfAllNodes -> to maintains which color that node have 
            - for start that array have -1 in all index's   -> We can say that it's not visited yet then
            - when we hit any node will change -1 to 0 (Red) or 1 (Blue) according to condition

        2. Call a Recursion : dfs( currNode, currNodeColor, colorsOfAllNodes, graph)

            - Check if we have set any color to this currNode? If not then go set it
                if(colorsOfAllNodes[currNode] == -1)

                - Set color 0 for first node and then will change color to neighboring nodes 
                - changing -1 to 0 or 1 means we have set a color there
                    colorsOfAllNodes[currNode] = currNodeColor

            - Now will check neighbors
                for(neighbor : graph[currNode])

                - If neighbors color is not set already we need to set it 
                    if(colorsOfAllNodes[neighbor] == -1 )

                    - How to get currNodeColor? 
                        if we have currColor 0 then it should be 1-0 = 1 
                        if we have currColor 1 then it should be 1-1 = 0
                            neighborColor = 1 - currNodeColor

                    - So call recursion function
                        dfs(neighbor, neighborColor, colorsOfAllNodes, graph)

                - there is one more if condtion here.. what if currcolor and neighborColor looks same?
                    if(colorsOfAllNodes[neighbor] == colorsOfAllNodes[currNode])
                        return false

            - return true


 */