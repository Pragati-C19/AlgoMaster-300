import java.util.*;

public class NumArray {
   
    // Globally Declare variable
    private int[] numsArray;

    public NumArray(int[] nums) {

        // Assign value here
        numsArray = nums;
    }

    public void update(int index, int val) {

        // update value of index 
        numsArray[index] = val;
    }

    public int sumRange(int left, int right) {

        // initialize sum as 0
        int sum = 0;

        // check all nums in range [left, right]
        for (int i = left; i <= right; i++) {

            // add that num in the sum
            sum += numsArray[i];
        }
        
        return sum;
    }


    public static void main(String[] args) {
        
        // Initialize the array
        NumArray solution = new NumArray(new int[]{1, 3, 5});
        
        // First query
        System.out.println(solution.sumRange(0, 2)); // Output: 9
        
        // Second query
        solution.update(1, 2);  // Now the array becomes [1, 2, 5]
        
        // Third query
        System.out.println(solution.sumRange(0, 2)); // Output: 8
    }

}

/*
 * Intuitions :
 
    1. we have given a array nums and we need to handle few queries like
        - update the value of an element in nums
        - calculate the sum of elements of nums between [left, right]
    2. Functions we want to right
        - NumArray(int[] nums) : Initializes the object with the integer array nums.
        - void update(int index, int val) Updates the value of nums[index] to be val
        - int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 
 
 * Pattern :
 
    1. doing initalize and update is easy 
        - to initialize    
            we need to just declare nums array globally and it's length 
        - to update
            we just need to do nums[ndex] = val
        - I think for sumRange 
            we can use for loop (i = left to right)
            and then add nums[i] in sum

    2. But what I'm thinking is just brute force it will give me TLE.. and I need to change my whole approach
        it will not that easy right?

        let's try to use directly on leetcode
 
    3. I tried to understnad the optimized way of segment tree but I don't undertsnad the logic behind it
        it all feels confusing will check later again
 
 * Pseudo Code :
 
    1. Brute Force :

        - all 15/16 test cases are passed
        - just last 16th one had TLE

    class NumArray {

        private int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public void update(int index, int val) {
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            int sum = 0;
            for (int i = left; i <= right; i++) {
                sum += nums[i];
            }
            return sum;
        }

    }

 */