import java.util.*;

public class FindMinHeightTrees {
    
    // Gloablly declare variables
    Map<Integer, List<Integer>> graphMap;

    // Driver Function 
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
        // Declare variables
        int m = edges.length;
        graphMap = new HashMap<>();
        int minHeight = Integer.MAX_VALUE;
        // Map<Integer, List<Integer>> heightNodeMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // Declare queue as we are using BFS now to store leaf Nodes
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visitedNode = new boolean[n];


        // Base Case :
        if (n == 1) {
            result.add(0);
            return result;
        }
        
        // add dependancies in graphMap
        for (int i = 0; i < m; i++) {
            
            // check if 0'th index of edges[i] is in map or not
            if (!graphMap.containsKey(edges[i][0])) {
                graphMap.put(edges[i][0], new ArrayList<>());
            }

            // check if 1'st index of edges[i] is in map or not
            if (!graphMap.containsKey(edges[i][1])) {
                graphMap.put(edges[i][1], new ArrayList<>());
            }

            // add dependency on each other
            graphMap.get(edges[i][0]).add(edges[i][1]);
            graphMap.get(edges[i][1]).add(edges[i][0]);

        }
        System.out.println("Graph Map : " + graphMap);


        // Initially add all leaf node in queue
        for (Map.Entry<Integer, List<Integer>> entry : graphMap.entrySet()) {
            
            int node = entry.getKey();
            List<Integer> listOfNodes = entry.getValue();
            System.out.println("        listOfNodes : " + listOfNodes);

            if (listOfNodes.size() == 1) {
                
                queue.add(node);
                visitedNode[node] = true;
                System.out.println("  -> Leaf Node " + node + " is added in : " + queue);
            }
        } 


        // start while loop for level order
        while (n > 2) {
            
            // get level size
            int queueSize = queue.size();

            // if there are multiple nodes we need to go further to the center so add it's neighbors in queue
            for (int i = 0; i < queueSize; i++) {
                
                int leafNodePop = queue.poll();
                // as leafNode if it has any neighbors?
                List<Integer> neighborsOfCurrLeafNodes = graphMap.get(leafNodePop);
                if (neighborsOfCurrLeafNodes != null) {
                    
                    for (int neighbor : neighborsOfCurrLeafNodes) {
                        
                        if (!visitedNode[neighbor]) {
                            
                            queue.add(neighbor);
                            visitedNode[neighbor] = true;
                            System.out.println("  -> Neighbor of Leaf Node " + leafNodePop + " is added in : " + queue);
                        } 
                    }
                }
            }

            // decrease value of n bcoz we are kinda removing leafNodes
            n -= queueSize;
           
        }   


        // add remaining values in queue at the end in result
        while (!queue.isEmpty()) {

            result.add(queue.poll());
        }


        // call dfs
        // for (int rootNode = 0; rootNode < n; rootNode++) {
            
               // we need to reset visitingState as 0 for every new rootNode
        //     int[] visitingState = new int[n];
            
               // get currHeight of node
        //     int currHeight = dfs(rootNode, visitingState, rootNode, 1);
        //     System.out.println("CurrHeight for node " + rootNode + " : " + currHeight);

               // add that height and node in heigthNodeMap 
        //     if (!heightNodeMap.containsKey(currHeight)) {
        //         heightNodeMap.put(currHeight, new ArrayList<>());
        //     }
        //     heightNodeMap.get(currHeight).add(rootNode);
        //     System.out.println("HeigthNode Map : " + heightNodeMap);

               // update minHeight value
        //     minHeight = Math.min(minHeight, currHeight);

        // }
    
        // return minHeight nodes now
        // I was going to use new variable and for loop but suddenly thought both are List<Integer> let's just return .get
        // result = heightNodeMap.get(0);

        return result;
    }



    //~ NOT USING Recursion Function : to get height of curr level
    private int dfs (int currNode, int[] visitingState, int parentNode, int currHeight) {
        
        System.out.println("    - Visiting : currNode " + currNode + " <- " + parentNode + " with currHeight : " + currHeight);
        
        // mark as visiting
        visitingState[currNode] = 1;

        int longestHeightForCurrNode = currHeight;

        // check neighbors
        List<Integer> neighborsOfCurrNodes = graphMap.get(currNode);

        if (neighborsOfCurrNodes != null) {
            
            for (int neighbor : neighborsOfCurrNodes) {
                
                // [3, 0] if we say our root node is 0 then we need to check it's neighbor 3 and for 3 to mark as visited we need to check 0 
                // so to avoid this type of cycle detection will avoid to check neighbor or currnode which equals to parentNode
                if (neighbor == parentNode) {

                    System.out.println("        Neighbor (" + neighbor + ") equals to parentNode (" + parentNode + ")");
                    continue;
                }


                int childHeight = dfs(neighbor, visitingState, currNode, currHeight + 1);

                longestHeightForCurrNode = Math.max(longestHeightForCurrNode, childHeight);
                
            }
        }

        // Mark currNode as visited
        visitingState[currNode] = 2;

        // return curren height of the node
        return longestHeightForCurrNode;
    }

    
    public static void main(String[] args) {

        FindMinHeightTrees solution = new FindMinHeightTrees();

        int[][] edges1 = {
            {1, 0},
            {1, 2},
            {1, 3}
        };
        System.out.println("Result 1 : " + solution.findMinHeightTrees(4, edges1) + "\n");

        int[][] edges2 = {
            {3, 0},
            {3, 1},
            {3, 2},
            {3, 4},
            {5, 4}
        };
        System.out.println("Result 2 : " + solution.findMinHeightTrees(6, edges2) + "\n");

    }
}

/*

 * Improvements : 
        - This Type of Problems are solved by Topological Trim
        - Took help for optimal solution - Got TLE so 
            https://www.youtube.com/watch?v=ZXANlaEuYvQ&ab_channel=codestorywithMIK
        - Brute force is same as mine..
        - apan leaf node la root mhnun gheu? nahi bcoz it's an end of tree.. jithun height check keli tr maxHeight ch yeil 
        - common sense jr mala minHeight haviye tr me centre vala ekhada root baghte jo aplost equidistance asel saglyanpasun
        - leaf node la gheun minHeight tree bannarch nahi
        - after we trace examples like shown in video - we saw that there are only 1 or 2 roots with minHeights
            minHeight root 1 -> if n is odd
            minHeight root 2 -> if n is even
            like in example :
                0 -> 1 -> 2 -> 3 -> 4                   n is odd
                    skip leaf nodes [0, 4]
                    so height of root 2 is minimum than other

                0 -> 1 -> 2 -> 3 -> 4 -> 5              n is even
                    skip leaf nodes [0, 4]
                    so height of root [2, 3] is minimum than other
        - so now we know 2 major things 
            we need center nodes as root
            ans can be of at most 2 roots.. roots count will not exceed 2 
        
        - let's visualize example :  n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
            
            n is even   so our minHeight nodes will be 2
            let's draw a tree

                            0
                            |
                    1 ----- 3 ----- 2
                            |
                            4
                            |
                            5
            
            - so first will remove all leaf nodes we are going level by level
            - so consider like initially we have added all leaf node in queue 
            - now pop out top of queue
            - ans that popNode who is ur neighbor ?
                like if we as 0, 1, 2 who is there neighbor they wee say 3
                add that 3 in queue
                then ask 5 who is their naighbor it will say 4
                add that 4 in queue
            - if queueSize = 1 or 2 we'll return nums in queue [3, 4]
        - ithe queueSize 1 or 2 nahi haviye aplyala aplyala last remaining nodes 1 or 3 rahile pahije asa havay
            means n = 6 ahe tr tyachi value 1 or 2 zali pahije
            so like initially if I added leafNode in queue mark it as visited and do n--


 * Intuitions :
 
    1. we have given a tree which is an undirected graph mean two vertices are connected by exactly one path
    2. Any connected grpah without simple cycle is tree
    3. we have given a tree of n nodes (0-indexed)
        where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree
        we can use any node of tree as root
    4. when we choose x node of the tree as root, the result tree has height h
    5. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees 
    6. we want to return list of all minimum height trees's rootNode

 * Pattern :
 
    1. after tracing the problem I think we need to select one node as root and then check it's neighbors
    2. store it's height then check next node as root and get it's height
    3. updated minHeight = min(currHeight, minHeight)
    3. store that [node, height] in a array temporary
    4. then get node whose heights == minHeights

    ^ Trace example :

        n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]

        - we only knows the edge it's not compolusary that [3, 0] mean 3 -> 0 it can be 0 -> depend on what is our root node
        - so let's see edges between nodes
            0 - 3
            1 - 3
            2 - 3
            3 - 0, 1, 2, 4
            4 - 3, 5
            5 - 4

        - we have 6 nodes and will have to select one node as root one by one
        - 0 as rootNode : height = 4

                            0
                            |
                            3
                     _______|_______
                    |       |       |
                    1       2       4
                                    |
                                    5

        - 1 as rootNode : height = 4

                            1
                            |
                            3
                     _______|_______
                    |       |       |
                    0       2       4
                                    |
                                    5

        - 2 as rootNode : height = 4

                            2
                            |
                            3
                     _______|_______
                    |       |       |
                    0       1       4
                                    |
                                    5
        
        - 3 as rootNode : height = 3
                    
                            3
                 ___________|___________
                |       |       |       |
                0       1       2       4
                                        |
                                        5
                                    
        - 4 as rootNode : height = 3
                    
                                4
                         _______|_______
                        |               |
                        3               5
                 _______|_______
                |       |       |       
                0       1       2       
        
        - so minHeight so far is 3 
        - and there are two root node whose height is 3 
            which are [3, 4]
                                    
    Thinking process :
        - I understand that I need to slect one node and trace it till end it doen't have cycle detected thing
        - bcoz we know edges 
        - my problem is I can't use map to store key and value if I did that it will goes in cycle detected loop
        - I think when I say this is root I need to add values in map according to that node?
        - I'm only stuck at here

^   Solution to my cycle detection problem
    - see maza problem hota jr me grpah map madhe store kelya value tr tya distil like below
            0 - 3
            1 - 3
            2 - 3
            3 - 0, 1, 2, 4
            4 - 3, 5
            5 - 4
    - jr me root as 0 mhnle and jevha te 3 la check karayla jail tevha mhnelch ki ohh u need to visit 0 first cycle detected
    - tyamul to avoid this.. apan rk kam karu currNode, visitingState sobt apan parentNode pn deu
    - will check if neighbor == parentNode then continue
    
    
    - by doing dfs(neighbor, visitingState, currNode, currHeight + 1)
        you're only returning the height at the moment of calling, not the deepest height from all child branches. 

 
 * Pseudo Code :

    findMinHeightTrees (n, edge) {
    
        -> Declare variables
            graphMap = new Hashmap
            visitingState = new int[n]      -> there are n nodes
            minHeight = Max_Value
            heightNodeMap                   -> to store height as key and list of nodes as value
            result

        -> add dependencies in grpahmap
            it's a little bit different bcoz [1, 0] means 1 -> 0 and 0 -> 1
            length of edge[i] is 2 bcoz it as (a, b)
            
            for(i = 0 to edge.length)

                -> check for 0'th index 
                    if(!map.contains(edge[i][0]))
                        map.put(edge[i][0], ArrayList)

                -> check for 1'st index
                    if(!map.contains(edge[i][1]))
                        map.put(edge[i][1], ArrayList)

                -> now add values in array
                    map.get(edge[i][0]).add(edge[i][1])
                    map.get(edge[i][1]).add(edge[i][0])

        
        -> now call dfs 
            for(rootNode = 0 to n)

                - get curr height
                currHeight = dfs(rootNode, visitingState, rootNode, 0)

                - add that height and node in map
                    if(!heightNodeMap.contains(currHeight))
                        heightNodeMap.put(currHeight, new ArrayList)

                    heightNodeMap.get(currHeight).add(rootNode)
                        
                - update minHeight value
                minHeight = min(currHeight, minHeight)

        -> add nodes with minHeight in result
            if(heightNodeMap.contains(minHeight))
                
                listOfNode = map.get(minHeight)
                
                for(i = 0 to listOfNode.lenght)
                    result.add(listOfNode.get(i))

        -> return result

    }


    dfs (currNode, visitingState, parentNode, currHeight) {
    
        -> Base Case :
            if(visitingState[currNode] == 2)    return currHeight

        -> mark currNode as visiting
            visitingState[currNode] = 1
        

        -> check for neighbors 
            for(neighbor : map.get(currNode))

                -> to manage cycle detection for this problem 
                    if(neighbor == parentNode) continue

                dfs(neighbor, visitingState, currNode, currHeight + 1)

        -> mark currNode as visited now as we check it's neighbors so
            visitingState[currNode] = 2
        
        -> if everything works we can return as currHeight
    
    }

 */