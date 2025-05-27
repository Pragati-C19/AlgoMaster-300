import java.util.*;

public class TimeRequiredToBuy {
    // Declared a queue
    private Queue<Integer> queue;

    // Constructor initializes the queue as a LinkedList
    public TimeRequiredToBuy() {
        queue = new LinkedList<>();
    }

    public int timeRequiredToBuy(int[] tickets, int k) {

        int time = 0;

        // 🔹 Loop through each person in the line
        for (int i = 0; i < tickets.length; i++) {

            // 🔸 Case 1: People before or at `k` get all their tickets or `tickets[k]` tickets max
            if (i <= k) {
                time += Math.min(tickets[i], tickets[k]);
            } 
            // 🔸 Case 2: People after `k` get tickets only until `k` finishes (max `tickets[k] - 1`)
            else {
                time += Math.min(tickets[i], tickets[k] - 1);
            }

            // 🔹 Debugging output to see how time accumulates per person
            System.out.println("Person " + i + " - Tickets left: " + tickets[i] + ", Time: " + time);
        }

        // // Add each person (by their index) into the queue initially
        // for (int i = 0; i < tickets.length; i++) {
        //     queue.add(i);
        // }

        // System.out.println("Initial queue: " + queue);
        // System.out.println("Tickets: " + Arrays.toString(tickets));

        // // Process the queue
        // while (!queue.isEmpty()) {
        //     int current = queue.poll(); // Get the front person
        //     tickets[current]--; // They buy one ticket
        //     time++; // Time increases by 1 second

        //     System.out.println("Person " + current + " buys a ticket. Remaining tickets: " + Arrays.toString(tickets));
        //     System.out.println("Time elapsed: " + time + "s");

        //     // If they still need tickets, go to the end of the queue
        //     if (tickets[current] > 0) {
        //         queue.add(current);
        //         System.out.println("Person " + current + " goes to the end of the line.");
        //     }

        //     // If "k" has finished buying tickets
        //     if (k == current && tickets[current] == 0) {
        //         break;
        //     }

        //     System.out.println("Current queue: " + queue + "\n");
        // }

        return time;
    }

    public static void main(String[] args) {
        TimeRequiredToBuy solution = new TimeRequiredToBuy();

        int[] tickets1 = { 2, 3, 5 };
        int k1 = 2;
        System.out.println("Time for k1: " + solution.timeRequiredToBuy(tickets1, k1));

        int[] tickets2 = { 5, 1, 1, 1 };
        int k2 = 0;
        System.out.println("Time for k2: " + solution.timeRequiredToBuy(tickets2, k2));

    }
}

/*
 * 
 * Intuitions:
 * 
 * 1. Each person buys one tickets per sec and moves to the end of the line
 * 2. Person leaves the line once they buys all tickets they wanted
 * 3. We count the total time until person k finishes buying all their tickets.
 * 4. Each person can only buy up to tickets[k] tickets — once k finishes, we
 * stop.
 * 
 * Pattern :
 * 
 * 1. If the person still needs tickets (tickets[i] > 0):
 * ➜ Decrease their ticket count (tickets[i]--).
 * ➜ Increase the time counter (time++).
 * 2. If it's person k's turn and they get their last ticket, we stop and return
 * the total time.
 * 
 * 1. People before or at position k ( i <= k ):
 * They get their turn every time, so they buy either all their tickets or stop
 * when k finishes.
 * Each person i buys min(tickets[i], tickets[k]) tickets.
 * 
 * 2️. People after position k ( i > k ):
 * They only buy tickets until person k is done.
 * They get at most tickets[k] - 1 turns (because k gets the final turn to
 * finish).
 * Each such person buys min(tickets[i], tickets[k] - 1) tickets.
 * 
 * Psuedo Code :
 * 
 * Input: tickets = [2,3,2], k = 2
 * Output: 6
 * 
 * 1. Initialize the queue
 * 2. Track time = 0
 * 3. Loop through the queue repeatedly
 * - For each person i:
 * - If tickets[i] > 0, they buy one ticket:
 * - Decrement tickets -> tickets[i]--
 * - Increment time -> time++
 * - If person `k` has tickets[k] == 0 after buying, return the total time
 * 3. Repeat until person `k` finishes
 * 4. Return time
 * 
 * 
 * Input: tickets[], k
 * Output: Total time taken for person `k` to finish buying tickets.
 * 
 * 1. Initialize `time = 0`
 * 
 * 2. Loop through each person `i` in the queue:
 * a) If `i <= k` (before or at `k`):
 * ➜ Add `min(tickets[i], tickets[k])` to `time`
 * b) If `i > k` (after `k`):
 * ➜ Add `min(tickets[i], tickets[k] - 1)` to `time`
 * 
 * 3. Return `time`
 * 
 * 
 */
