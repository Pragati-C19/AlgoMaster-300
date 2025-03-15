public class TimeRequiredToBuy {

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
 * 1. Initialize time = 0
 * 2. Loop through the queue repeatedly
 * - For each person i:
 * - If tickets[i] > 0, they buy one ticket:
 * - tickets[i]--
 * - time++
 * - If person `k` has tickets[k] == 0 after buying, return the total time
 * 3. Repeat until person `k` finishes
 * 4. Return time
 * 
 * 
 */
