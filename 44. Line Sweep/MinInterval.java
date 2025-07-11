import java.util.*;
import java.util.stream.Collectors;

public class MinInterval {
    
    public int[] minInterval(int[][] intervals, int[] queries) {
        
        // Declare variables
        int m = intervals.length;
        int n = queries.length;
        int[] result = new int[n];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        int intervalIndex = 0;
        int[][] updatedQueries = new int[n][2];


        // Phase 1 : Store original index and actual value in updated Queries
        for (int i = 0; i < n; i++) {
            
            updatedQueries[i][0] = queries[i];  // Actual query 
            updatedQueries[i][1] = i;           // Original Index
        }
        // System.out.println(" Updated Queries : " + Arrays.deepToString(updatedQueries));
       

        // Phase 2 : Sort the intervals based on starting point
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        // Phase 3 : Sort queries based on value (not original index)
        Arrays.sort(updatedQueries, (a,b) -> a[0] - b[0]);

        System.out.println(" Sorted Intervals       : " + Arrays.deepToString(intervals));
        System.out.println(" Sorted UpdatedQueries  : " + Arrays.deepToString(updatedQueries));


        // Phase 4 : Process each query in sorted order
        for (int[] queryPair : updatedQueries) {
            
            int query = queryPair[0];
            int queryIndex = queryPair[1];

            System.out.println(" For query : " + Arrays.toString(queryPair));

            // Add all Intervals in minHeap whose start <= query
            while (intervalIndex < m && intervals[intervalIndex][0] <= query) {
                
                // get values of start and end with size
                int start = intervals[intervalIndex][0];
                int end = intervals[intervalIndex][1];
                int size = end - start + 1;

                System.out.println("    - Valid Interval : " + Arrays.toString(intervals[intervalIndex]));
                System.out.println("    - Size : " + size);

                // Push valid interval into heap
                minHeap.add(new int[]{size, end});

                intervalIndex++;
            }

            System.out.println("    minHeap After Adding Valid Interval : ");
            System.out.println(
                "         " + 
                minHeap.stream()
                    .map(Arrays::toString)
                    .collect(Collectors.joining(", ", "[", "]"))
            );


            // Remove all intervals from heap whose end < query
            while (!minHeap.isEmpty() && minHeap.peek()[1] < query) {
                
                minHeap.poll();
            }

            System.out.println("    minHeap After Removing unvalid Interval : ");
            System.out.println(
                "         " + 
                minHeap.stream()
                    .map(Arrays::toString)
                    .collect(Collectors.joining(", ", "[", "]"))
            );


            // The top of heap is the smallest interval that includes the query
            // add it in result[queryIndex]
            result[queryIndex] = minHeap.peek()[0]; 
            
            
        }

        return new int[0];
    }

    public static void main(String[] args) {

        MinInterval solution = new MinInterval();

        // Testcase 1
        int[][] intervals1 = {
            {1, 4},
            {2, 4},
            {3, 6},
            {4, 4}
        };
        int[] queries1 = {2, 3, 4, 5};
        System.out.println(" Result1 -> " + Arrays.toString(solution.minInterval(intervals1, queries1)) + "\n");   // [3, 3, 1, 4]

        // Testcase 2
        int[][] intervals2 = {
            {2, 3},
            {2, 5},
            {1, 8},
            {20, 25}
        };
        int[] queries2 = {2, 19, 5, 22};
        System.out.println(" Result2 -> " + Arrays.toString(solution.minInterval(intervals2, queries2)) + "\n");   // [2, -1, 4, 6]
    }

}

/*
 * Intuitions :
 
    1. We have given a 2D array intervals
        where intervals[i] = [left, right]
    2. interval is starting at left and ending at right
    3. Size of interval = right - left + 1
    4. We have also given a integer array queries
        ans to the jth query is the size of the smallest interval i
    5. If no such interval exists return -1
    6. return an array contains ans to the quiries

 
 * Pattern :
 
    1. So que madhe queries cha array one by one trace karaychay
    2. queries[i] jya [left, right] range chya madhe asel tr
        - Sagele intervals baghayche and tyanchi size kadhaychi
        - By using this formula given right - left + 1
        - Tya size madhun ji minSize asel add it in result

    3. Line Sweep Method :
        - Will sort intervals by left 
        - Will Sort queries 
            but we in ans we need original order of queries 
            so will create a new array which stores queries and original index
            then will sort it
        - Will use min-heap to store valid intervals [size, end]
            size = right - left + 1
            end = right, which is used to remove invalid intervals
        - will check each query one by one
            add all intervals in heap whose left <= query 
            remove all intervals from heap whose end < query
            then add top of heap in result[index]


 
 * Pseudo Code :
 
    function minInterval(intervals, queries) {
    
        -> declare variables
            m               - intervals length
            n               - queries length
            result          - return the result
            minHeap         - It will store valid intervals [size, end]
            updatedQueries  - it will store actual value and original index in it

        -> Phase 1 : Store original index and actual value in updated Queries
            for(i = 0 to n)
                [i][0] = queries[i]
                [i][1] = i

        -> Phase 2 : Sort intervals and updatedQueries 
        
        -> Phase 3 : Process each query in sorted order

            - declare a index as pointer for intervals array outside of for loop

            - Start for loop 
                for(queryPair : updatedQueries)

                    - Add all intervals whose start <= query in minHeap
                        while (intervalIndex < m && intervals[i][0] <= query)
                            add size and end in minHeap
                            i++

                    - Remove all intervals from heap whose end < query 
                        why? bcoz end < query asel mhnjech start pn less asel and that means query tya interval madhe nahiye
                        
                        while (!minHeap.isEmpty && minHeap.peek()[1] < query)
                            minHeap.poll

                    - Add top size of heap which is the smallest interval in result[queryIndex]
                        result[queryIndex] = minHeap.peek()[0]


        -> At the end return result
    
    }


 */