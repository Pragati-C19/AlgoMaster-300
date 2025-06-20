import java.util.*;

public class SumOfDistancesInTree {
    
    // Globally Declare Variables
    Map<Integer, List<Integer>> adjList;
    int[] ans;
    int[] count;

    // Driver Function
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        
        // Base Case :
        if (n == 1) {
            
            return new int[]{0};
        }

        // Decalre variables
        adjList = new HashMap<>();
        ans = new int[n];
        count = new int[n];

        // Add values in map
        // Create a adjList
        for (int[] edge : edges) {
            
            int startPoint = edge[0];
            int endPoint = edge[1];

            if (!adjList.containsKey(startPoint)) {
                
                adjList.put(startPoint, new ArrayList<>());
            }
            adjList.get(startPoint).add(endPoint);


            if (!adjList.containsKey(endPoint)) {
                
                adjList.put(endPoint, new ArrayList<>());
            }
            adjList.get(endPoint).add(startPoint);

        }

        // Debugger Souts
        System.out.println(" AdjList Map    : " + adjList);
        System.out.println(" Ans DP Array   : " + Arrays.toString(ans));
        System.out.println(" Count DP Array : " + Arrays.toString(count));

        // Call both DFS now
        postOrderDFS(0, -1);
        preOrderDFS(0, -1);
        
        return ans;
    }

    // Helper Function : dfs function to get distance
    private int postOrderDFS(int currNode, int parentNode) {

        // declare currDistance 
        int currDist = 1;

        // Check neighbors
        for (int neighbor : adjList.get(currNode)) {
            
            if (neighbor != parentNode) {
                
                currDist += postOrderDFS(neighbor, currNode);
            }
        }

        return currDist;
    }

    // Helper Function : dfs function to get distance
    private int preOrderDFS(int currNode, int parentNode) {

        // declare currDistance 
        int currDist = 1;

        // Check neighbors
        for (int neighbor : adjList.get(currNode)) {
            
            if (neighbor != parentNode) {
                
                currDist += preOrderDFS(neighbor, currNode);
            }
        }

        return currDist;
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


    ^ Improvements :
    
        - We are getting Time Limit Exceeded for brute Force approach 
        - let's change approach 
            we need to use 2 DP's
                ans     -> To store total distance from node 0 to all others
                count   -> how many nodes are in the subtree of node i (including i)
            we need 2 DFS function 
                for postOrder
                for preOrder
            That means :
                dfs1(0, -1);  - Post-order DFS to fill `res[0]` and `count[]`
                dfs2(0, -1);  - Pre-order DFS to fill rest of `res[i]`


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

        - Visiual tree
                  0
                 / \
                1   2
                   /|\
                  3 4 5

        - Post Order (left - right - root)
            
            start at node 0 
                dfs(1)  -> count[1] = count[1] + count[child]            = 1 + 0     = 1
                             res[1] = res[1] + res[child] + count[child] = 0 + 0 + 0 = 0
                
                dfs(3)  -> count[3] = count[3] + count[child]            = 1 + 0     = 1
                             res[3] = res[3] + res[child] + count[child] = 0 + 0 + 0 = 0
                
                dfs(4)  -> count[4] = count[4] + count[child]            = 1 + 0     = 1
                             res[4] = res[4] + res[child] + count[child] = 0 + 0 + 0 = 0
                
                dfs(5)  -> count[5] = count[5] + count[child]            = 1 + 0     = 1
                             res[5] = res[5] + res[child] + count[child] = 0 + 0 + 0 = 0
                
                - all above has no childs 
                - for dfs(2) and dfs(0) we need to check all childs at count[child] and res[child]

 
 * Pseudo Code:
 
    -> Globally Declare Variables
        Map<Integer, List<Integer>> adjList;

    -> Driver Function
        public int[] sumOfDistancesInTree(int n, int[][] edges) {
            
        -> Base Case :
            if (n == 1) {
                
                return new int[]{0};
            }

        -> Decalre variables
            adjList = new HashMap<>();
            int[] ans = new int[n];

        -> Add values in map
        -> Create a adjList
            for (int[] edge : edges) {
                
                int startPoint = edge[0];
                int endPoint = edge[1];

                if (!adjList.containsKey(startPoint)) {
                    
                    adjList.put(startPoint, new ArrayList<>());
                }
                adjList.get(startPoint).add(endPoint);


                if (!adjList.containsKey(endPoint)) {
                    
                    adjList.put(endPoint, new ArrayList<>());
                }
                adjList.get(endPoint).add(startPoint);

            }

        -> Debugger Souts
            System.out.println(" AdjList Map : " + adjList);

            for (int i = 0; i < n; i++) {
                
                boolean[] visited = new boolean[n];

                ans[i] = dfs(i, -1, 0, visited);
            }
            
            return ans;
        }

    -> Helper Function : dfs function to get distance
        private int dfs(int currNode, int parentNode, int distSoFar, boolean[] visited) {

        -> Base Case :
            if (visited[currNode]) {
                
                System.out.println(" - currNode is already visited..");
                return 0;
            }

        -> declare currDistance 
            int currDist = distSoFar;

        -> Check neighbors
            for (int neighbor : adjList.get(currNode)) {
                
                if (neighbor != parentNode) {
                    
                    currDist += dfs(neighbor, currNode, distSoFar + 1, visited);
                }
            }

            return currDist;
        }


 */