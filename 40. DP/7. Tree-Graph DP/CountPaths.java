import java.util.*;
import java.util.stream.Collectors;

public class CountPaths {
    
    public int countPaths(int n, int[][] roads) {
        
        // Declare variables
        int[] waysDP = new int[n];
        int[] minTimeDP = new int[n];
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        
        // Create a adjList
        for (int[] road : roads) {
            
            int startPoint = road[0];
            int endPoint = road[1];
            int time = road[2];

            if (!adjList.containsKey(startPoint)) {
                
                adjList.put(startPoint, new ArrayList<>());
            }
            adjList.get(startPoint).add(new int[]{endPoint, time});


            if (!adjList.containsKey(endPoint)) {
                
                adjList.put(endPoint, new ArrayList<>());
            }
            adjList.get(endPoint).add(new int[]{startPoint, time});

        }

        // Create a MinHeap
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        
        // Assign initial int[] in minHeap
        minHeap.add(new int[]{0,0});

        // Assign initial values in both dp
        Arrays.fill(minTimeDP, Integer.MAX_VALUE);
        minTimeDP[0] = 0;
        waysDP[0] = 1;


        // Debugger Souts
        System.out.println(" AdjList Map : ");
        adjList.forEach((k, v) -> System.out.println("     " + k + " -> " + v.stream().map(Arrays::toString).collect(Collectors.toList())));
        System.out.println(" MinTimeDP : " + Arrays.toString(minTimeDP));
        System.out.println(" WaysDP : " + Arrays.toString(waysDP));
        System.out.println(" minHeap : ");
        minHeap.forEach(arr -> System.out.println("     " + Arrays.toString(arr)));
       

        // Will start while loop
        while (!minHeap.isEmpty()) {

            int[] top = minHeap.poll();
            int currNode = top[0];
            int currTime = top[1];

            // will skip this currNode if we see currTime is greater than already found minTime
            if (currTime > minTimeDP[currNode]) {
                
                System.out.println("    - currTime(" + currTime + ") > minTime(" + minTimeDP[currNode] + ") so will skip this currNode(" + currNode + ")");
                continue;
            }

            // else check all neighbors of currNode
            for (int[] neighbor : adjList.getOrDefault(currNode, new ArrayList<>())) {
                
                int neighborNode = neighbor[0];
                int timeToNeighbor = neighbor[1];

                int totalTimeFromZero = currTime + timeToNeighbor;

                // if we get totalTime < minTime
                if (totalTimeFromZero < minTimeDP[neighborNode]) {
                    
                    minTimeDP[neighborNode] = totalTimeFromZero;
                    waysDP[neighborNode] = waysDP[currNode];

                    minHeap.add(new int[]{neighborNode, totalTimeFromZero});
                }

                // if we get totalTime = minTime
                else if (totalTimeFromZero == minTimeDP[neighborNode]) {
                    
                    waysDP[neighborNode] += waysDP[currNode];
                }
            }

            System.out.println("  - Updated MinTimeDP : " + Arrays.toString(minTimeDP));
            System.out.println("  - Updated WaysDP : " + Arrays.toString(waysDP));
            System.out.println("  - Updated minHeap : ");
            minHeap.forEach(arr -> System.out.println("     " + Arrays.toString(arr)));
       
        }


        return waysDP[n-1];
    }

    public static void main(String[] args) {

        CountPaths solution = new CountPaths();

        int[][] roads1 = {
            {0,6,7},
            {0,1,2},
            {1,2,3},
            {1,3,3},
            {6,3,3},
            {3,5,1},
            {6,5,1},
            {2,5,1},
            {0,4,5},
            {4,6,2}
        };
        System.out.println("Result1 -> " + solution.countPaths(7, roads1) + "\n");

        int[][] roads2 = {
            {1,0,10}
        };
        System.out.println("Result2 -> " + solution.countPaths(2, roads2) + "\n");

    }

}

/*
 * Intuitions :
 
    1. We are in city which consistes of n intersections numbered from 0 to n-1
        with bi-directional roads
    2. We have given integer and 2D array roads
        where roads[i] = [u, v, time]
        u = startingPoint
        v = endPoint
        time = time it took to reach till v from u
    3. we want to know, how many ways we can travel from 0 to n-1
        in shortest amount of time
    4. Return number of ways you can arrive at destination in the shortest amount of time    

 
 * Pattern :

    1. Will wrote dp where will store all ways to reach any point 
        how much time it took to reach that point 
    2. I think this is a 
        - graph problem
            so we need adj list - where will store all dependencies of index 
        - we need shortest path
        - We need to dp's
            minTime[]   - min Time to reach i
            ways[]      - number of ways to reach node i in minTime[i]
        
    3. Will store initial values in both dp's
        - minTime = Max_Value at start  - bcoz we need min values and if we add 0 at start will get wrong ans
            minTime[0] = 0  - bcoz there is 0 time to reach 0 we are starting from 0'th index so
        - ways = 0 initially will keep it as 0 
            ways[0] = 1     - bcoz there is only 1 way to reach 0

    4. Adj List means
        - adj List means storing neighbors of index in it 

    5. will use minHeap to get minTime 
        - yat apan [node, time] store karu


    ^ Dry Run :

        n     = 7
        roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
        
        index   =   0  1  2  3  4  5  6 
        minTime = [ ∞, ∞, ∞, ∞, ∞, ∞, ∞ ]
        ways    = [ 0, 0, 0, 0, 0, 0, 0 ]

        - Create adj List
            adjList = {
                0:  [6,7], [1,2], [4,5] 
                1:  [0,2], [2,3], [3,3] 
                2:  [1,3], [5,1] 
                3:  [1,3], [5,1], [6,3] 
                4:  [0,5], [6,2] 
                5:  [3,1], [6,1], [2,1] 
                6:  [0,7], [3,3], [5,1], [4,2] 
            }

        - When will check every nums one by one will see how much time it will take to reach currIndex
            and how much ways are there

            point 0 - there is 0 ways and time to reach at point 0
                minTime[0] = 0
                ways[0] = 1 
                minHeap = [(0,0)]

        - let's start while loop till out minHeap get's empty

            -> pop (0,0)

                From node 0:
                    - To 6: time = popTime + adjList[0][0][2] = 0 + 7 = 7
                            ways[6] = ways[0] + ways[6] = 1 + 0 = 1
                    - To 1: time = popTime + adjList[0][1][2] = 0 + 2 = 2
                            ways[1] = ways[0] + ways[1] = 1 + 0 = 1
                    - To 4: time = popTime + adjList[0][2][2] = 0 + 5 = 5
                            ways[4] = ways[0] + ways[4] = 1 + 0 = 1

                minTime = [0, 2, ∞, ∞, 5, ∞, 7]
                ways    = [1, 1, 0, 0, 1, 0, 1]
                minHeap = [(1,2), (4,5), (6,7)]


            -> pop (1,2)

                From node 1:
                    - To 0: already reached faster as time was 0
                    - To 2: time = popTime + adjList[1][1][2] = 2 + 3 = 5
                            ways[2] = ways[1] + ways[2] = 1 + 0 = 1
                    - To 3: time = popTime + adjList[1][2][2] = 2 + 3 = 5
                            ways[3] = ways[1] + ways[3] = 1 + 0 = 1

                minTime = [0, 2, 5, 5, 5, ∞, 7]
                ways    = [1, 1, 1, 1, 1, 0, 1]
                minHeap = [(2,5), (3,5), (4,5), (6,7)]
 
            
            -> pop (2,5)

                From node 2:
                    - To 1: already reached faster as time was 2
                    - To 5: time = popTime + adjList[2][1][2] = 5 + 1 = 6
                            ways[5] = ways[2] + ways[5] = 1 + 0 = 1

                minTime = [0, 2, 5, 5, 5, 6, 7]
                ways    = [1, 1, 1, 1, 1, 1, 1]
                minHeap = [(3,5), (4,5), (5,6), (6,7)]

            
            -> pop (3,5)

                From node 3:
                    - To 1: already reached faster as time was 2
                    - To 5: time = popTime + adjList[3][1][2] = 5 + 1 = 6
                            ways[5] = ways[3] + ways[5] = 1 + 1 = 2
                    - To 6: time = popTime + adjList[3][2][2] = 5 + 3 = 8   -> already reached faster as time was 7
                            ways[6] = ways[3] + ways[6] = 1 + 1 = 2

                minTime = [0, 2, 5, 5, 5, 6, 7]
                ways    = [1, 1, 1, 1, 1, 2, 1]
                minHeap = [(4,5), (5,6), (6,7)]


            -> pop (4,5)

                From node 4:
                    - To 0: already reached faster as time was 0
                    - To 6: time = popTime + adjList[4][1][2] = 5 + 2 = 7    
                            ways[6] = ways[4] + ways[6] = 1 + 1 = 2

                minTime = [0, 2, 5, 5, 5, 6, 7]
                ways    = [1, 1, 1, 1, 1, 2, 2]
                minHeap = [(5,6), (6,7)]

            
            -> pop (5,6)

                From node 5:
                    - To 3: time = popTime + adjList[5][1][2] = 6 + 1 = 7   -> already reached faster as time was 5
                            ways[3] = ways[5] + ways[3] = 2 + 1 = 3
                    - To 6: time = popTime + adjList[5][1][2] = 6 + 1 = 7
                            ways[6] = ways[5] + ways[6] = 2 + 2 = 4
                    - To 2: time = popTime + adjList[5][2][2] = 6 + 1 = 7   -> already reached faster as time was 5
                            ways[2] = ways[5] + ways[2] = 2 + 1 = 3

                minTime = [0, 2, 5, 5, 5, 6, 7]
                ways    = [1, 1, 1, 1, 1, 2, 4]
                minHeap = [(6,7)]

            
            -> pop (6,7)

                From node 6:
                    - To 0: already reached faster as time was 0
                    - To 3: already reached faster as time was 5
                    - To 5: already reached faster as time was 6
                    - To 4: already reached faster as time was 5

                minTime = [0, 2, 5, 5, 5, 6, 7]
                ways    = [1, 1, 1, 1, 1, 2, 4]
                minHeap = []

        - Lastly return ways[n-1] = 4


 * Pseudo Code :
 
    function countPaths (n, roads) {
    
        -> Declare variables
            waysDP      - to store all ways to reach i
            minTimeDP   - to store all minTime we need to reach i\
            minHeap     - it will store [node, timeSoFar] 
            Map<Integer, List<int[]>> adjList     - it will store index : dependanciesNode, time]

        -> Create a adj List
            for(int[] road : roads)

                startPoint = road[0]
                endPoint = road[1]
                time = road[2]

                if(map.contains(startPoint))
                    map.put(startPoint, new ArraysList<>())
                
                map.get(startPoint).add(endPoint)
                map.get(startPoint).add(time)


                if(map.contains(endPoint))
                    map.put(endPoint, new ArraysList<>())
                
                map.get(endPoint).add(startPoint)
                map.get(endPoint).add(time)

                
        -> Add Initial values in dp
            - for minTime Array fill it with Max_Value
            - ways[0] = 1
            - minTime[0] = 0

        -> add (0, 0) in pq at start
            minHeap.add(new int[]{0,0})

        -> Start while loop till minHeap is empty
            
            top = minHeap.pop
            currNode = top[0]
            currTime = top[1]

            - will skip this node is we see currTime > minTime[currNode]

            - else will check all neighbors
                for(int[] neighbor : adjList)

                    neighborNode = neighbor[0]
                    timeToNeighbor = neighbor[1]

                    totalTimeFromZero = currTime + neighbor[1]

                    - if totalTimeFromZero < minTime[neighborNode]
                        update minTime and ways 
                        also add this node and totalTime in minHeap

                    - if totalTime == minTime
                        update only ways


        -> At the end return ways[n-1]
    
    }


 */