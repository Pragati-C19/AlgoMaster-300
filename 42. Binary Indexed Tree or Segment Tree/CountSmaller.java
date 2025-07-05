import java.util.*;

public class CountSmaller {
    
    // Driver Function 
    public List<Integer> countSmaller(int[] nums) {
        
        // Declare variables
        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        // Loop through each element in the array
        for (int i = 0; i < n; i++) {
            int count = 0;

            // Check all elements to the right of i
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }

            // Add count to result
            result.add(count);
        }

        return result;
    }


    public static void main(String[] args) {
    
        CountSmaller solution = new CountSmaller();

        int[] nums1 = {5,2,6,1};
        System.out.println(" Result 1 -> " + solution.countSmaller(nums1) + "\n");  // [2,1,1,0]

        int[] nums2 = {-1};
        System.out.println(" Result 2 -> " + solution.countSmaller(nums2) + "\n");  // [0]

        int[] nums3 = {-1, -1};
        System.out.println(" Result 3 -> " + solution.countSmaller(nums3) + "\n");  // [0,0]

    }

}

/*
 * Intuitions :
 
    1. we have given an array nums
    2. we need to return an integer array counts
        where count[i] is the number of smaller elements to the right of nums[i]

 
 * Pattern :
 
    1. Brute force :
        - use for loop (i = 0 to n) and (j = i to n)
        - check if nums[i] > nums[j]
            then increase count 
        - after end of j loop when we are going to start next i 
            will add that count in count[i]

    2. Problem with above approach is I need to check all number each time
        - mala asa kahitri havay jyani mala fact ekdach check karava lagel num
        - and will wrote there konapekshya chota ahe te

    3. I did try to submit brute force approach
        but 62/66 test cases are passed..
        63rd gives me TLE
 
 
 * Pseudo Code :
 
    1. Brute Force :

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        -> Loop through each element in the array
            for (int i = 0; i < n; i++) {
                int count = 0;

            -> Check all elements to the right of i
                for (int j = i + 1; j < n; j++) {
                    if (nums[j] < nums[i]) {
                        count++;
                }
            }

            -> Add count to result
                result.add(count);
        }   

        return result
    
    }



 */