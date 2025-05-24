import java.util.*;

public class FindCheapestPrice {
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        
        // Declare variables
        Map<Integer, List<int[]>> graphMap = new HashMap<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        Map<Integer, Integer> visitedCity = new HashMap<>();

        // Add dependencies in map
        for (int[] flight : flights) {
            
            int fromCity = flight[0];
            int toCity = flight[1];
            int cost = flight[2];

            if (!graphMap.containsKey(fromCity)) {
                
                graphMap.put(fromCity, new ArrayList<>());
            }

            graphMap.get(fromCity).add(new int[]{toCity, cost});
        }
        System.out.println("GraphMap : ");
        graphMap.forEach((u, v) -> System.out.println("     " + u + " -> " + v.stream().map(Arrays::toString).toList()));


        // add initial values in MinHeap
        minHeap.add(new int[]{0, src, 0});

        // start while loop
        while (!minHeap.isEmpty()) {
         
            int[] popMinHeap = minHeap.poll();

            int costSoFar = popMinHeap[0];
            int currCity = popMinHeap[1];
            int stopsUsed = popMinHeap[2];

            System.out.println(" Pop Out Elements : " + Arrays.toString(popMinHeap));

            // if destination city reached return the cost
            if (currCity == dst) {
                
                System.out.println("Reach the Destination in : " + costSoFar);
                return costSoFar;
            }

            // if we used more than k stops we need to skip that city now
            if (stopsUsed > k) {

                System.out.println("    ~ Stops visited are greated than k : " + stopsUsed);
                continue;
            }

            // if city is not visited OR this path used fewer stops
            if (!visitedCity.containsKey(currCity) || visitedCity.get(currCity) > stopsUsed) {

                // mark as visited
                visitedCity.put(currCity, stopsUsed);

                // check neighbors
                for (int[] neighbor : graphMap.get(currCity)) {
                    
                    int nextCity = neighbor[0];
                    int costToNextCity = neighbor[1];

                    int newCost = costSoFar + costToNextCity;
                    int newStops = stopsUsed + 1;

                    minHeap.add(new int[]{newCost, nextCity, newStops});
                }

            }

            System.out.println("    - VisitedCity Map after " + currCity + " : " + visitedCity);
            System.out.println("    - Updated minHeap : " + Arrays.deepToString(minHeap.toArray()));

        }
        
        return -1;
    }

    public static void main(String[] args) {

        FindCheapestPrice solution = new FindCheapestPrice();

        int[][] flights1 = {
            {0, 1, 100},
            {1, 2, 100},
            {2, 0, 100},
            {1, 3, 600},
            {2, 3, 200}
        };
        int n1 = 4, src1 = 0, dst1 = 3, k1 = 1;
        System.out.println("Result 1 -> " + solution.findCheapestPrice(n1, flights1, src1, dst1, k1) + "\n");   // 700

        int[][] flights2 = {
            {0, 1, 100},
            {1, 2, 100},
            {0, 2, 500}
        };
        int n2 = 3, src2 = 0, dst2 = 2, k2 = 1;
        System.out.println("Result 2 -> " + solution.findCheapestPrice(n2, flights2, src2, dst2, k2) + "\n");   // 200

        int[][] flights3 = {
            {0, 1, 100},
            {1, 2, 100},
            {0, 2, 500}
        };
        int n3 = 3, src3 = 0, dst3 = 2, k3 = 0;
        System.out.println("Result 3 -> " + solution.findCheapestPrice(n3, flights3, src3, dst3, k3) + "\n");   // 500

    }

}

/*
 * Intuitions :
 
    1. There are n cities connected by flights
    2. fights array tells us abt
        - from : there is a flight from this city
        - to   : to this destination city
        - cost : with cost this much
    3. we have given src, dst, k 
        where src : source city from which I need a flight
              dst : destination city where I need to go
              k   : we want only k stops, 
                    means if we don't find direct src to dst flight then apan src pasun eka thikani janar then tithun dst
                    asa stop mala fact k havet 
    4. we need to return cheapest Price
    5. if there is no route will return -1
 
 
 * Pattern :
 
    1. we need cheapest price means we want MinHeap
        store {costSoFar, cityAbtToVisitNext}
    2. we need to store src and dst with cost it takes in an graph
    3. we need a visitedCity array to not check that city again and again
    4. let's think abt taking destination node first 
    5. Or think abt how we can only visit k stops?
        - I'm thinking of use K as level
        - in that level if we found dst check cheapest price 

    6. Improvement Points :
        - will use visited map to store { city, stopsVisitedCount }
        - in minHeap will store { costSoFar, currCity, stopsVisitedCount }
    
    bcoz If we take this example
        n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1

        Graph (nodes = cities, arrows = flights):

                       100
             _______________________
            |                       |
            |    100         100    v
            1 <-------- 0 <-------- 2
            |                       |
            |                       | 
             ---------> 3 <---------             
                600          200

        GraphMap:
            0 -> [1, 100]
            1 -> [2, 100], [3, 600]
            2 -> [0, 100], [3, 200]
        
        So jr ata mal 0 pasun 3 parynt jaych asel tr  
            0 -> 1 -> 3 = 700 
            0 -> 1 -> 2 -> 3 = 400
        
        even if path2 < path1
            we have given we need only k = 1 stop in between src and dst
            so we have to choose path1    

 
 * Pseudo Code :
 
    function findCheapestPrice (n, flights, src, dst, k) {
    
        -> Declare variables
            graphMap        - add dependencies with cost in it
            minHeap         - it stores {costSoFar, currCity, stopsVisitedCount}
            visitedCity     - this map stores {currCity, stopsVisitedCount}

        -> add dependencies
            for(flight : flights)
                
                sourceCity = flight[0]
                destinationCity = flight[1]
                cost = flight[2]

                if(!map.contains(sourceCity))
                    map.put(sourceCity, new array)

                map.get(sourceCity).add(new int[] {destinationCity, cost})

        -> add inital values in minHeap
            at start we havent's visited any city so cost is 0 and we haven't check path's yet so 0 stops
                minHeap.add(0, src, 0)


        -> start while loop
            while(!minHeap.isEmpty)

                -> pop out top
                
                costSoFar 
                currCity
                stopsUsed

                -> if destination reached will return costSoFar

                -> if we have used more than k stops then skip

                -> if We never visited this city before Or we found a way to reach it with fewer stops
                    will check neighbors

                    - mark currCity as visited now

                    - check neighbors now
                        for(neighbor : map.get(currCity))

                            int nextCity = neighbor[0];
                            int costToNext = neighbor[1];

                            int newCost = costSoFar + costToNext;
                            int newStops = stopsUsed + 1;

                            -> add it in minHeap

        -> return -1 if destination is unreachable
        
    }

 */