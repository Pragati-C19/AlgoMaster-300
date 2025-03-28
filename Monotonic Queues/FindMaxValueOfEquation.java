import java.util.*;

public class FindMaxValueOfEquation {
    public int findMaxValueOfEquation(int[][] points, int k) {

        Deque<Integer> deque = new ArrayDeque<>();
        int maxResult = Integer.MIN_VALUE;

        for (int j = 0; j < points.length; j++) {

            int xj = points[j][0];
            int yj = points[j][1];
            System.out.println("Processing point: (" + xj + ", " + yj + ")");

            // Remove points out of range (xj - xi > k)
            while (!deque.isEmpty() && xj - points[deque.peekFirst()][0] > k){
                System.out.println("Removing point (out of range): (" + points[deque.peekFirst()][0] + ", " + points[deque.peekFirst()][1] + ")");
                deque.pollFirst();   // Remove from front
            }

            // If valid points exist, calculate max result
            if (!deque.isEmpty()) {
                int i = deque.peekFirst();
                int xi = points[i][0];
                int yi = points[i][1];

                int result = yj + xj + (yi - xi);
                System.out.println("Calculating result with (" + xi + ", " + yi + ") -> " + result);
                
                maxResult = Math.max(maxResult, result);
            }

            // Maintain deque in decreasing order of (yi - xi)
            while (!deque.isEmpty() &&(points[deque.peekLast()][1] - points[deque.peekLast()][0]) <= (yj - xj)) {
                System.out.println("Removing less useful point: (" + points[deque.peekLast()][0] + ", " + points[deque.peekLast()][1] + ")");
                deque.pollLast();
            }

            // Add current point to deque
            System.out.println("Adding point to deque: (" + xj + ", " + yj + ")");
            deque.addLast(j);

            System.out.println("Current Deque: " + deque + "\n");
        }

        System.out.println("Final Maximum Result: " + maxResult);
        return maxResult;
    }

    public static void main(String[] args) {
        FindMaxValueOfEquation solution = new FindMaxValueOfEquation();

        int[][] point1 = { { 1, 3 }, { 2, 0 }, { 5, 10 }, { 6, -10 } };
        int k1 = 1;
        System.out.println("Output 1: " + solution.findMaxValueOfEquation(point1, k1) + "\n");

        int[][] point2 = { { 0, 0 }, { 3, 0 }, { 9, 2 } };
        int k2 = 3;
        System.out.println("Output 2: " + solution.findMaxValueOfEquation(point2, k2) + "\n");

    }
}

/**
 * 
 * What we need in problem :
 * 
 * 1. A sorted array of 2D points points[i] = [xi, yi], where xi is always
 * increasing
 * 2. An integer k that restricts how far apart two points can be
 * 3. The maximum value of the equation yi + yj + |xi - xj|, after rearranging
 * the equation (yj + xj) + (yi - xi)
 * 4. Goal: Find the maximum value of this equation for valid pairs.
 * 
 * 
 * Intuition :
 * 
 * 1. Iterate through each point j
 * For each point j = [xj, yj], we want to pair it with a previous point i to
 * maximize the equation.
 * 
 * 2️. Remove outdated points
 * If xj - xi > k (i.e., the point is too far away), remove it from the queue.
 * 
 * 3️. Calculate the current max equation value
 * - Use the top of the queue (max (yi - xi)) to compute the current result.
 * - Track the maximum result across all pairs.
 * 
 * 4️. Insert the current point's (yj - xj) into the queue, since it may help
 * future points.
 * 
 * 
 * 
 * Pseudo Code :
 * 
 * function maxValueOfEquation(points, k):
 * max_result = -inf
 * max_queue = empty deque (or priority queue)
 * 
 * for each point [xj, yj] in points:
 * # Step 1: Remove outdated points (xj - xi > k)
 * while max_queue is not empty and (xj - max_queue.front[1] > k):
 * pop from max_queue
 * 
 * # Step 2: Calculate max value with the current point
 * if max_queue is not empty:
 * max_result = max(max_result, yj + xj + max_queue.front[0])
 * 
 * # Step 3: Push current point's (yj - xj) to the queue
 * while max_queue is not empty and max_queue.back[0] < (yj - xj):
 * pop from back (maintain decreasing order)
 * 
 * push (yj - xj, xj) to max_queue
 * 
 * return max_result
 * 
 * 
 * 
 * Walkthrough Example :
 * 
 * 
 * Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * 
 * 1. (1,3) — Push (3-1 = 2, 1) to the queue.
 * 
 * 2. (2,0) —
 * - 2 - 1 = 1 is valid (<= k), calculate:
 * 0 + 2 + (2) = 4
 * - Push (0-2 = -2, 2) to the queue.
 * 
 * 3. (5,10) —
 * - 5 - 2 = 3 (too far!), pop both points from the queue.
 * - Push (10-5 = 5, 5) to the queue.
 * 
 * 4.(6,-10) —
 * - 6 - 5 = 1 is valid (<= k), calculate:
 * -10 + 6 + 5 = 1
 * - Push (-10-6 = -16, 6) to the queue.
 * 
 * Final Answer: 4
 * 
 * 
 */