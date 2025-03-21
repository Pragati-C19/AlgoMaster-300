import java.util.*;

public class SortColors {
    public void sortColors(int[] nums) {

        int n = nums.length;
        int prev = 0; // Red Color
        int curr = 0; // White Color
        int next = n - 1; // Blue Color

        System.out.println("Starting Length of Array: " + n);

        while (curr <= next) {

            System.out.println("Current Num : " + nums[curr] + " | Current Index : " + curr);

            if (nums[curr] == 0) {
                // Swap (curr, prev)
                int temp = nums[prev];
                nums[prev] = nums[curr];
                nums[curr] = temp;
                prev++;
                curr++;

                System.out.println("[IF] Current Num : " + nums[curr] + " | Current Index : " + curr);
                System.out.println("[IF] Prev Num : " + nums[prev] + " | Prev Index : " + prev);

            } else if (nums[curr] == 1) {
                System.out.println("[ELSEIF] Current Num : " + nums[curr] + " | Current Index : " + curr);
                curr++;
            } else {
                // Swap (curr, next)
                int temp = nums[next];
                nums[next] = nums[curr];
                nums[curr] = temp;
                next--;
                curr++;

                System.out.println("[ELSE] Current Num : " + nums[curr] + " | Current Index : " + curr);
                System.out.println("[ELSE] Next Num : " + nums[next] + " | Next Index : " + next);
            }

            System.out.println("Current Length of Array: " + n);
            System.out.println("Current Array: " + Arrays.toString(nums) + "\n");
        }

    }

    public static void main(String[] args) {
        SortColors solution = new SortColors();

        int[] nums1 = { 2, 0, 2, 1, 1, 0 };
        int[] nums2 = { 2, 0, 1 };

        // int[] result1 = solution.sortColors(nums1);
        // int[] result2 = solution.sortColors(nums2);

        solution.sortColors(nums1);
        solution.sortColors(nums2);

        System.out.println("Result1 : " + Arrays.toString(nums1) + "\n");
        System.out.println("Result2 : " + Arrays.toString(nums2) + "\n");
    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. red = 0 , white = 1, blue = 2
 * 2. u can't use sort function so use swapping
 * 
 * 
 * Why without return statement still code works?
 * 1. Arrays in Java are passed by reference, so the original array is modified
 * directly.
 * 2. If you only need to modify the array in place, void is enough — no return
 * needed.
 * 3. If you need to return something (like a new array or result), then you
 * must change void to the correct return type.
 * 
 * 
 * 
 * Pattern :
 * 
 * 1. see u have 3 numbers so check middle number = 1
 * 2. if it's 0 swap(red and white)
 * 3. if it's 2 swap(blue and white)
 * 4. I'm not using linkelist just giving name like that for my preference
 * 5. and I'm using white color or mid as curr..
 * 
 * 
 * Pseudo Code :
 * 
 * fn sortColor(nums) :
 * 
 * int n = nums.length;
 * int prev = 0;
 * int curr = 0;
 * int next = n - 1;
 * 
 * for(int i = 0; i < n; i++ ){
 * 
 * if(nums[curr] == 0){
 * swap(curr, prev);
 * temp = nums[prev];
 * nums[prev] = nums[curr];
 * nums[curr] = temp;
 * prev++;
 * curr++;
 * }
 * else if(nums[curr] == 1){
 * curr++;
 * }
 * else {
 * // nums[curr] == 2
 * swap(curr, high)
 * temp = nums[high];
 * nums[high] = nums[curr];
 * nums[curr] = temp;
 * high++;
 * curr++;
 * }
 * }
 * 
 * return nums;
 */
