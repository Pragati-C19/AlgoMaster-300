import java.util.*;

public class GetSkyline {
    
    public List<List<Integer>> getSkyline(int[][] buildings) {
        
        // Declare variables
        List<int[]> events = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b - a);
        int prevHeight = 0;
        List<List<Integer>> result = new ArrayList<>();

        // Phase 1 : Convert buildings into events
        for (int[] building : buildings) {
            
            // Assign variable names for elements in int[]
            int left = building[0];
            int right = building[1];
            int height = building[2];

            // Add start event : use -height to distinguish from end event
            events.add(new int[]{left, -height});

            // Add end event : use +height to distinguish from start event
            events.add(new int[]{right, height});
        }
        // System.out.println(" Events List:");
        // events.stream().map(e -> "    " + Arrays.toString(e)).forEach(System.out::println);


        // Phase 2 : Sort events Array
        Collections.sort(events, (a,b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);   // Sort by height
            return Integer.compare(a[0], b[0]);                     // Sort by x-coordinates
        });
        System.out.println(" Sorted Events List:");
        events.stream().map(e -> "    " + Arrays.toString(e)).forEach(System.out::println);


        // Phase 3 : Store initial active height 0 (ground level) in heap
        maxHeap.add(0);

        // Phase 4 : Process all events
        for (int[] event : events) {
            
            // Get value of x-coordinate and height from event
            int xCoordinate = event[0];
            int height = event[1];        

            // Check if it's start of building or end of building
            if (height < 0) {
                
                // it will convert -height back to +height before adding in heap
                maxHeap.add(-height);
                System.out.println("    - Start of building so added height in maxHeap : " + maxHeap);
            }
            else {

                // will not use -height bcoz for end we are storing value as +height in events
                maxHeap.remove(height);
                System.out.println("    - End of building so removed height in maxHeap : " + maxHeap);
            }

            
        }

        return result;
    }


    public static void main(String[] args) {
        
        GetSkyline solution = new GetSkyline();

        // Testcase 1
        int[][] buildings1 = {
            {2,9,10},
            {3,7,15},
            {5,12,12},
            {15,20,10},
            {19,24,8}
        };
        System.out.println(" Result1 -> " + solution.getSkyline(buildings1) + "\n");    // [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]

        // Testcase 2
        int[][] buildings2 = {
            {0, 2, 3},
            {2, 5, 3}
        };
        System.out.println(" Result2 -> " + solution.getSkyline(buildings2) + "\n");    // [[0,3],[5,0]]

    }

}

/*
 * Intuitions :
 
    1. There is a skyline in sky 
        means je apan nakshatra mhnto te, or te stars la line ni connect kel ki ji image bante 
    2. we have given left, right and height of building
    3. need to return co-ordinates formed by outlines of those buildings 
 
 
 * Pattern :
 
    1. Convert buildings into events :
        For each building
            - Add event (left, -height)  -> start of building (negative sign to indicate insert)
            - Add event (right, height)  -> End of building (positive to indicate removal)
        
    2. Sort Events :
        - First by x-coordinate 
        - If we have same x: process start (negative heights) before ends(positive heights)
            it will keep taller buildings before shorter onces
        
    3. Sweep line simulation:
        - Use a MaxHeap to track of active heights
        - At each event:
            Add height to heap if it's a start
            Remove height from heap if it's end
        - If max height changes compared to previous, record it as a key point
 
    4. One thing to remember while adding height in maxHeap
        - apan sorting sathi events madhe Start la -heights lihilay
        - and heap madhe apalyala fact height(positive) add karaychiye
        - tyamul jevha pn heap madhe height add karayla jashil tevha 
            maxHeap.add(-height) kr 
        - or jevha event madhe height variable extract karshil tevhach 
            height = -event[1] kr
        - why this?
            it will convert -height back to +height before adding in heap
        - Example :
            event = [2, -4]
            x-cordinate = 2
            height = -4

            maxHeap.add(-(-4))      - minus minus -> plus
            maxHeap.add(4)

    5. Why used currMaxHeight != prevMaxHeight, not just currMaxHeight > prevMaxHeight?
        - bcoz skyline can go up and down both
        - so we need to check both ways 
        - if we check curr > prev, it will only gives us value for going up 
        - if we check curr < prev, it will only gives us value for going down
        - so to avoid problem will check if it's not equal and then update prevHeight



 * Pseudo Code :
 
    function getSkyline(buildings) {
    
        -> Declare variables 
            events              - List of int[] to hold events: building start and end
            maxHeap             - To store active heights and get max height first
            prevHeight = 0   - It will tell prev Max height so far
            result              - Store coordinates as result 

        
        -> Phase 1 : Convert buildings into events
            for(building : buildings)

                Add events in events array
                    - Add start event : use -height to distinguish from end event
                    - Add end event : use +height to distinguish from start event
    
        
        -> Phase 2 : Sort Events Array 

            - use Collections instead of Array as events is a List<int[]>
            - sort by x-coordinates means a[0] - b[0]
            - but is x same then 
                sort by heights means a[1] - b[1]

        
        -> Phase 3 : Store initial active height 0 (ground level) in heap

            - Add height 0 as Ground Level in heap 
                it's already told in que 
                

        -> Phase 4: Process all events
            for(event : events)
            
                - Get value of x-coordinate and height from event
                
                - Check if it's start of building or end of building
                    will check if height < 0 
                        means start of building as we are adding -height for start of building in events so
                        So that time add height in heap

                    else 
                        means it's end of building 
                        So remove height from heap

                - Declare a variable currHeight 
                    which is the peek of maxHeap

                - check if currHeight != prevHeight
                    means height changed we need to add new coordinates of point in result
                        result.add(x, currHeight)

                    updated prevHeight value to currHeight


        -> At end return result

    }




 */