import java.util.*;

public class MergeSortedArrays {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointers to track nums1, nums2, and merged array positions
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        // Merge from the back to avoid overwriting nums1 early
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p--] = nums1[p1--];
            } else {
                nums1[p--] = nums2[p2--];
            }
        }

        // If any elements remain in nums2, copy them (nums1 leftovers are already in place)
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }

 
    public static void main(String[] args) {
        MergeSortedArrays merger = new MergeSortedArrays();

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merger.merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1)); // Output: [1, 2, 2, 3, 5, 6]

        int[] nums3 = {1};
        int[] nums4 = {};
        merger.merge(nums3, 1, nums4, 0);
        System.out.println(Arrays.toString(nums3)); // Output: [1]

        int[] nums5 = {0};
        int[] nums6 = {1};
        merger.merge(nums5, 0, nums6, 1);
        System.out.println(Arrays.toString(nums5)); // Output: [1]
    }
}


/*
 * 
 * function merge(nums1, m, nums2, n):
    # Pointers for nums1, nums2, and merged array (starting from the end)
    p1 = m - 1
    p2 = n - 1
    p = m + n - 1

    # Traverse from the end to the start
    while p1 >= 0 and p2 >= 0:
        if nums1[p1] > nums2[p2]:
            nums1[p] = nums1[p1]
            p1 -= 1
        else:
            nums1[p] = nums2[p2]
            p2 -= 1
        p -= 1

    # If nums2 has remaining elements, copy them over
    while p2 >= 0:
        nums1[p] = nums2[p2]
        p2 -= 1
        p -= 1

 */