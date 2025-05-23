import java.util.*;

public class MinCostConnectPoints {
    
    public int minCostConnectPoints(int[][] points) {
        
        // Declare variables
        int n = points.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        boolean[] visitedPointIndex = new boolean[n];
        int totalCost = 0;

        // add initial point {pointIndex, dist} in minHeap
        minHeap.add(new int[]{0, 0});

        while (!minHeap.isEmpty()) {
            
            int[] popElement = minHeap.poll();

            int pointIndex = popElement[0];
            int cost = popElement[1];
            System.out.println("    Currently checking {" + pointIndex + ", " + cost + "} ");

            if (!visitedPointIndex[pointIndex]) {
                
                // mark as visited
                visitedPointIndex[pointIndex] = true;

                // add cost in totalCost
                totalCost += cost;
                
                // check that pointIndex's neighboring indexes
                // it's coordinates so we can connect to any point randomly so taking 0 to n
                for (int neighborOfPoint = 0; neighborOfPoint < n; neighborOfPoint++) {
                    
                    // check manhattan distance
                    int distWithNeighborPoint = Math.abs(points[pointIndex][0] - points[neighborOfPoint][0]) 
                            + Math.abs(points[pointIndex][1] - points[neighborOfPoint][1]);

                    System.out.println("    distance of " + pointIndex + " with " + neighborOfPoint + " : " + distWithNeighborPoint);

                    // add this in minHeap
                    minHeap.add(new int[]{neighborOfPoint, distWithNeighborPoint});
                }

                System.out.println("MinHeap : " + Arrays.deepToString(minHeap.toArray()));
            }

        }

        return totalCost;
    }

    public static void main(String[] args) {

        MinCostConnectPoints solution = new MinCostConnectPoints();

        int[][] points1 = {
            {0, 0},
            {2, 2},
            {3, 10},
            {5, 2},
            {7, 0}
        };
        System.out.println("Result 1 -> " + solution.minCostConnectPoints(points1) + "\n");         // 20

        int[][] points2 = {
            {3, 12},
            {-2, 5},
            {-4, 1}
        };
        System.out.println("Result 2 -> " + solution.minCostConnectPoints(points2) + "\n");         // 18

    }
}

/*
 * Intuitions :
 
    1. We have given an array points representing coordinates of points 
    2. the cost of connecting two point with a edge is manhattan distance
        manhattan distance is | x1 - x2 | + | y1 - y2 | 
    3. return the minimum cost to make all points connected
 
 * Pattern :
 
    1. what DSA we need to solve this problem?
        - we need minHeap 
        - whenever we starting from a point to next point we need to take a distance 
        - we need to store it somewhere and as per the que we need minimum distance 
        - so which dsa can do it easily?.. it's minHeap
    
    2. What are we going to store in it? 
        - will store 3 things in it
        - distance : min distance at top
        - starting point : point at which we have started 
        - ending point : we need to reach at this point 
    
                     20
                p1 ------ p2
                |         |
              5 |         | 5
                |         |
                p3 ------ p4
                     5
        
        - if we want to go to p2 we have 3 path's p1 -> p2 and p3 -> p2
        - so to take better path will store below thing in minHeap
            [20, p1, p2]
            [5, p3, p2]
        - and minHeap shuffles it and gives a min distance path which is [5, p3, p2]

    3. mark that ending point as visited 
        - karan apan minHeap madhun min distance vala path gheu tyachyakde janara.. 
        - aplyala then bakiche pathchi garaj nasel na
        - so to skip them me visited mark karun dete right?
        - like me [5, p3, p2] path select kela sum += 5 kel good
        - ata minHeap mhnla mazyakde tr [20, p1, p2] he pn ahe so me tyala pn pop karnar ata kay? sum 20 tr nahi karu shakat
        - tyamul apan p2 la mark as visited karu.. mhnje parat dusra path ala ki to mhne tu motha ahe me nahi count karat tula

    4. We are using Prim's Algorithm
        - Start from any node.
        - Always pick the shortest edge that connects a new node (not visited yet).
        - Keep adding such nodes until all are connected.
        - it need {pointsIndex, distance}

    5. we can use Kruskal's Algorithm (DSU) 
        - it uses DSU too
        - it need {start, end, distance}


 * Pseudo Code :
 
    1. Declare variables 
        - minHeap
        - visited array
        - totalCost

    2. add initial cost (distance) and pointsIndex (points like p1, p2) in minHeap
    3. while (!minHeap.isEmpty)
        - pop out top from minHeap
        - if pointsIndex is already visited then continue
        - if not then mark that pointsIndex as visited
        - add it's that popped out distance in totalCost
        - check for neighbors (as in points on which we can go from popOut point)
            for(i = 0 to n)
        - if that neighbor is not visited 
            - get it's manhattan distance with node 
            - add it in minHeap 
        - example :
            suppose maza starting point p1 
            me p1 la pop kel 
            and mark visited kel
            totalCost += cost(p1) which is 0 bcoz it's a self point kel
            ata me p1 vr ahe and tithun mazyakde 2 path (neighbors) ahet p2, p3
            so me p1 and p3 madhal distance kadhal te minHeap madhe takal
            then me p1 and p2 ch distance kadhal te minHeap madhe takal
            then again while loop start zala me top pop kela and same process repeats


    2. 

    public int minCostConnectPoints(int[][] points) {

        int n = points.length;
        List<int[]> edges = new ArrayList<>();

        -> Step 1: Create all possible edges with their distance
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int cost = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                    edges.add(new int[]{i, j, cost});  // {pointIndex1, pointIndex2, distance}
                }
            }

        -> Step 2: Sort edges by distance
            edges.sort((a, b) -> Integer.compare(a[2], b[2]));

        -> Step 3: Initialize DSU
            DSU dsu = new DSU(n);
            int totalCost = 0;
            int edgesUsed = 0;

        -> Step 4: Process edges (Kruskal's main loop)
            for (int[] edge : edges) {
                int pointIndex1 = edge[0];
                int pointIndex2 = edge[1];
                int cost = edge[2];

                -> Only add edge if it connects two different components
                    if (dsu.findParent(pointIndex1) != dsu.findParent(pointIndex2)) {
                        dsu.unionBySize(pointIndex1, pointIndex2);  // use your DSU function as requested
                        totalCost += cost;
                        edgesUsed++;

                        -> Optimization: Stop once we have n-1 edges in MST
                            if (edgesUsed == n - 1) break;
                    }
            }

        return totalCost;
    }



 */