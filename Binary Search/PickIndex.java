import java.util.*;

public class PickIndex {

    int[] prefix;

    // Constructor: Build the prefix sum array
    public PickIndex(int[] w) {
        prefix = new int[w.length];
        prefix[0] = w[0];

        // Create cumulative prefix sum
        for (int i = 1; i < w.length; i++) {
            prefix[i] = prefix[i - 1] + w[i];
            System.out.println("Prefix[ " + i + " ] : " + prefix[i]);
        }

        System.out.println("Prefix Sum Array: " + Arrays.toString(prefix) + "\n");
    }

    public int pickIndex() {

        int last = prefix.length - 1;
        int totalSum = prefix[last];
        int randomIndex = (int) (Math.random() * totalSum) + 1;

        System.out.println("Last: " + last);
        System.out.println("Total Prefix Sum: " + totalSum);
        System.out.println("Random Index: " + randomIndex);

        // Binary search for the index within prefix sums
        int left = 0;
        int right = last;

        while (left < right) {

            int mid = left + (right - left) / 2;
            System.out.println("Left: " + left + ", Right: " + right + ", Mid: " + mid);

            if (prefix[mid] < randomIndex) {
                left = mid + 1;
                System.out.println("Updated | Left Value: " + left);
            } else {
                right = mid;
                System.out.println("Updated | Right Value: " + right);
            }
        }

        System.out.println("Ending Values | left: " + left + " , Right: " + right);
        return left;
    }

    public static void main(String[] args) {
        int[] weights = { 1, 3 };
        PickIndex solution = new PickIndex(weights);

        // Simulate multiple calls to pickIndex()
        System.out.println(solution.pickIndex() + "\n");
        System.out.println(solution.pickIndex() + "\n");
        System.out.println(solution.pickIndex() + "\n");
        System.out.println(solution.pickIndex() + "\n");
        System.out.println(solution.pickIndex());

    }

    // List<Integer> list = new ArrayList<>();
    // // Expand weights into a list of indices
    // public PickIndex(int[] w) {
    // for(int i = 0; i < w.length; i++){
    // for(int j = 0; j < w[i]; j++){
    // // Add index 'i' multiple times based on its weight
    // list.add(i);
    // }
    // }
    // System.out.println("List Made from w array : " + list);
    // }

    // public int pickIndex() {

    // int randomIndex = (int) (Math.random() * list.size());
    // System.out.println("randomIndex: " + randomIndex);

    // return list.get(randomIndex);
    // }

}

/*
 * 
 * 
 * Understanding the problem :
 * 
 * 1. Given an array w where each number represents a "weight" for that index.
 * 2. The higher the weight, the more likely that index should be picked
 * randomly.
 * 3. Example:
 * Take w = [1, 3].
 * - Index 0 has weight 1 (lighter).
 * - Index 1 has weight 3 (heavier).
 * The total weight is 1 + 3 = 4.
 * 
 * The probability of picking each index is:
 * - index 0: 1/4 = 0.25 (25% chance)
 * - index 1: 3/4 = 0.75 (75% chance)
 * So, if you call pickIndex() many times, index 1 should appear more often —
 * roughly 3 times as frequently as index 0.
 * 
 * 
 * Intuitions :
 * 
 * 1. this que is like below
 * 2. if u have 1 ticket of red color and 3 ticket of blue color
 * and now u are blind fold the probability of u picking blue ticket is more
 * right?
 * 3. Took GPT's help
 * 
 * Pattern :
 * 
 * 1. Identify what controls the probability — here, it's the weights array.
 * 2. Convert weights to a form where equal random picking works — in brute
 * force, we expand weights into a long list.
 * 3. Randomly select from the new data — any basic random picker (like
 * Math.random() or Random in Java) will now behave correctly.
 * 
 * Pseudo Code :
 * 
 * 1. Brute Force:
 * 
 * Declare new list
 * 
 * // Constructor: Expands weights into a list of indices
 * function Solution (int[] w){
 * for(int i = 0; i<w.length; i++){
 * for(int j = 0; j < w[i]; j++){
 * list.add(i); // Add index 'i' multiple times based on weight
 * }
 * }
 * }
 * 
 * // Pick Random index
 * function pickIndex(){
 * Declare Random
 * 
 * int randomIndex = random.nextInt(list.size())
 * or int randomIndex = (Math.random() * list.size());
 * 
 * return list.get(randomIndex)
 * }
 * 
 * 
 * Binary Search :
 * 
 * 1. Convert weights into prefix sums (cumulative sums)
 * - Example: w = [1, 3] → prefix = [1, 4]
 * 
 * 2. Generate a random number between 1 and total sum.
 * - total sum = 4, so random number ∈ [1, 4]
 * 
 * 3. Use binary search to find the smallest index where the prefix sum ≥ random
 * number.
 * 
 * 
 * // Constructor: Expands weights into a list of indices
 * function Solution (int[] w){
 * prefix[0] = w[0]
 * 
 * for(int i = 1; i<w.length; i++){
 * prefix[i] = prefix[i - 1] + w[i]
 * }
 * }
 * 
 * // Pick Random index
 * function pickIndex(){
 * int target = random(1, prefix[last])
 * int left = 0
 * int right = prefix.length -1
 * 
 * while left < right {
 * int mid = (left + right) / 2
 * if(prefix[mid] < target){
 * left = mid + 1
 * }
 * else {
 * right = mid
 * }
 * }
 * return left
 * }
 * 
 */
