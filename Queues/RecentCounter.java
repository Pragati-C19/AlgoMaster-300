import java.util.*;

public class RecentCounter {
    // Declared a queue to store timestamps
    private Queue<Integer> queue;

    // Constructor initializes the queue as a LinkedList
    public RecentCounter() {
        queue = new LinkedList<>();
    }

    public int ping(int t) {

        // Add the current timestamps to the queue
        queue.add(t);

        // Remove timestamps that is older then (t-3000)
        while (queue.peek() < t - 3000) {
            queue.poll(); // Remove outdated request
        }

        return queue.size();
    }

    public static void main(String[] args) {
        RecentCounter solution = new RecentCounter();

        System.out.println(solution.ping(1));
        System.out.println(solution.ping(100));
        System.out.println(solution.ping(3001));
        System.out.println(solution.ping(3002));
    }
}

/*
 *
 * Intution : Queue (FIFO structure)
 * 
 * 1. We need to track timestamps and counting requests of 3000 milliseconds
 * 2. Each call to ping(t), increasing time value
 * 3. We only care abt timestamps in range [t-3000, t]
 * 4. We are doing below things
 * - adding new requests
 * - removing old requests
 * - counting current valid request
 * 5. add() - works as append which add the current element in queue
 * 6. poll() - work as pop which removes the front element of the queue
 * 7. peek() - this is like checking who’s first in line but leaving them there.
 * 
 * Pattern :
 * 
 * 1. Add the current timestamp t to the queue.
 * 2. Remove all timestamps from the front that are less than (t-3000).
 * 3. Count the remaining timestamps
 * 
 * 
 * Psuedo code :
 * 
 * Input: ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * Output: [null, 1, 2, 3, 3]
 * 
 * function RecentCounter():
 * initialize an empty queue
 * 
 * function ping(t):
 * add t to the queue
 * 
 * while queue.front < t - 3000:
 * remove the front element (it's too old)
 * 
 * return the size of the queue (valid requests count)
 * 
 * 
 * 
 * Step-by-Step Explanation :
 * 
 * 1. RecentCounter is initialized - queue = []
 * 
 * 2. ping(1) - queue = [1]... timestamp range is [1-3000, 1] -> [-2999, 1]
 * The only request that is within this range is 1 itself.
 * So, the output for this ping is 1 because there is only one request in the
 * range [-2999, 1].
 * 
 * 3. ping(100) - queue = [1, 100]... timestamp range is [100 - 3000, 100] ->
 * [-2900, 100]
 * The requests in this range are 1 and 100. Both are within the range [-2900,
 * 100].
 * So, the output for this ping is 2 because there are two requests in the range
 * [-2900, 100].
 * 
 * 4. ping(3001) - queue = [1, 100, 3001]... timestamp range is [3001 - 3000,
 * 3001] -> [1, 3001]
 * The requests in this range are 1, 100, and 3001. All are within the range [1,
 * 3001]
 * So, the output for this ping is 3 because there are three requests in the
 * range [1, 3001]
 * 
 * 5. ping(3002) - queue = [1, 100, 3001, 3002]... timestamp range is [3002 -
 * 3000, 3002] -> [2, 3002]
 * The requests in this range are 100, 3001, and 3002, request 1 is no longer in
 * the time frame
 * So, the output for this ping is 3 because there are three requests in the
 * range [2, 3002]
 * 
 * 
 */
