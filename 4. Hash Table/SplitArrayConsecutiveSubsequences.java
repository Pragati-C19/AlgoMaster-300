import java.util.*;

public class SplitArrayConsecutiveSubsequences {
    public boolean isPossible(int[] nums) {
        // Step 1: Count frequencies and sequences ending at a number
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> endSeq = new HashMap<>();

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // Step 2: Process each number
        for (int num : nums) {
            if (count.get(num) == 0) continue;

            // Step 3: Extend existing sequence if possible
            if (endSeq.getOrDefault(num - 1, 0) > 0) {
                endSeq.put(num - 1, endSeq.get(num - 1) - 1);
                endSeq.put(num, endSeq.getOrDefault(num, 0) + 1);
            }
            // Step 4: Start a new sequence of length at least 3
            else if (count.getOrDefault(num + 1, 0) > 0 && count.getOrDefault(num + 2, 0) > 0) {
                count.put(num + 1, count.get(num + 1) - 1);
                count.put(num + 2, count.get(num + 2) - 1);
                endSeq.put(num + 2, endSeq.getOrDefault(num + 2, 0) + 1);
            } 
            // Step 5: If neither, fail
            else {
                return false;
            }

            count.put(num, count.get(num) - 1);
        }

        return true;
    }

    // Test cases
    public static void main(String[] args) {
        SplitArrayConsecutiveSubsequences obj = new SplitArrayConsecutiveSubsequences();

        int[] nums1 = {1, 2, 3, 3, 4, 5};
        System.out.println("Input: " + Arrays.toString(nums1) + " -> Output: " + obj.isPossible(nums1));

        int[] nums2 = {1, 2, 3, 3, 4, 4, 5, 5};
        System.out.println("Input: " + Arrays.toString(nums2) + " -> Output: " + obj.isPossible(nums2));

        int[] nums3 = {1, 2, 3, 4, 4, 5};
        System.out.println("Input: " + Arrays.toString(nums3) + " -> Output: " + obj.isPossible(nums3));
    }
}

/*8
 * 
 * def is_possible(nums):
    # Step 1: Track frequencies of numbers and sequences ending at specific numbers
    count = Counter(nums)
    end_seq = Counter()

    # Step 2: Process each number in order
    for num in nums:
        if count[num] == 0:
            continue

        # Step 3: If number extends an existing sequence
        if end_seq[num - 1] > 0:
            end_seq[num - 1] -= 1
            end_seq[num] += 1
        # Step 4: If number starts a new sequence of at least length 3
        elif count[num + 1] > 0 and count[num + 2] > 0:
            count[num + 1] -= 1
            count[num + 2] -= 1
            end_seq[num + 2] += 1
        else:
            return False

        count[num] -= 1

    return True

 */