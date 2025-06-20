import java.util.*;

public class SumOfDistancesInTree {
    
    // Globally Declare Variables


    // Driver Function
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        
        return new int[]{0};
    }

    // Helper Function : dfs function to get distance
    private int dfs(int currNode, int parentNode, int distSoFar) {

        return 0;
    }


    public static void main(String[] args) {

        SumOfDistancesInTree solution = new SumOfDistancesInTree();

        int[][] edges1 = {
            {0,1},
            {0,2},
            {2,3},
            {2,4},
            {2,5}
        };
        System.out.println("Result1 -> " + Arrays.toString(solution.sumOfDistancesInTree(6, edges1)) + "\n");  // [8,12,6,10,10,10]

        int[][] edges2 = {
            {1,0}
        };
        System.out.println("Result2 -> " + Arrays.toString(solution.sumOfDistancesInTree(2, edges2)) + "\n");  // [1,1]

        int[][] edges3 = {
            {}
        };
        System.out.println("Result3 -> " + Arrays.toString(solution.sumOfDistancesInTree(1, edges3)) + "\n");  // [0]

    }

}

/*
 * Intuitions :
 
    1. there is an undirected connected tree with n nodes labeled from 0 to n-1 
        and there are n-1 edges too
    2. we are given a integer n and array edges 
        where edges[i] = [a, b]
        which indicates that there is a edge between this a and b
    3. we need to return array ans of length n
        where ans[i] = sum of the distance between i'th node in the tree and all other nodes
 
 
 * Pattern :
 
    1. This is a graph problem 
        so we need adjList
    2. Will use dfs function (currNode, parentNode)
        - Base Case : if currNode is null then return 0
        - assign currDist = 1
            yacha meaning currNode itkya distance vr ahe
        - Check all neighbors
            for(neighbor : adjList)

                if(neighbor != parentNode) 
                    ans[i] += dfs(neighbor, currNode)


    ^ Dry Run :
        
        Example :
            n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]

        - Create adj List
            adjList = {
                0:  [1,2]
                1:  [0] 
                2:  [0,3,4,5] 
                3:  [2] 
                4:  [2] 
                5:  [2] 
            }
    
        - As per explaination on que 
            ans[0] = dist[0,1] + dist[0,2] + dist[0,3] + dist[0,4] + dist[0,5]

            so I think it is more on 
            ans[i] = dist[i,neighbor]

 
 * Pseudo Code:
 





 */