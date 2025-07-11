import java.util.*;

public class MinInterval {
    
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