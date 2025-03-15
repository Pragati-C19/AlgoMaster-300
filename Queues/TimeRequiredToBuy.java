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

        // Add each person (by their index) into the queue initially
        for (int i = 0; i < tickets.length; i++) {
            queue.add(i);
        }

        // Process the queue
        while (!queue.isEmpty()) {
            int current = queue.poll();     // Get the front person
            tickets[current]--;             // They buy one ticket
            time++;                         // Time increases by 1 second

            // If they still need tickets, go to the end of the queue
            if (tickets[current] > 0) {
                queue.add(current);
            }

            // If "k" has finished buying tickets
            if (k == current && tickets[current] == 0) {
                break;
            }
        }

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
 */
