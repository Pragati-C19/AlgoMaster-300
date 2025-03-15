public class BestSightseeingPair {
    public static int maxScoreSightseeingPair(int[] values) {
        int maxScore = 0;
        int bestPrev = values[0]; // Track best values[i] + i so far

        // Loop from second element onward
        for (int j = 1; j < values.length; j++) {
            // Calculate max score with the current j
            maxScore = Math.max(maxScore, bestPrev + values[j] - j);

            // Update bestPrev for the next j
            bestPrev = Math.max(bestPrev, values[j] + j);
        }

        return maxScore;
    }

    public static void main(String[] args) {
        int[] values1 = {8, 1, 5, 2, 6};
        int[] values2 = {1, 2};

        System.out.println("Example 1 Output: " + maxScoreSightseeingPair(values1)); // 11
        System.out.println("Example 2 Output: " + maxScoreSightseeingPair(values2)); // 2
    }
}


/**
 * 
 * def max_sightseeing_pair(values):
    max_score = 0
    best_prev = values[0]  # Track best (values[i] + i) seen so far

    for j in range(1, len(values)):
        # Calculate score with current j and the best i found so far
        max_score = max(max_score, best_prev + values[j] - j)

        # Update best_prev for next iteration
        best_prev = max(best_prev, values[j] + j)

    return max_score

 */