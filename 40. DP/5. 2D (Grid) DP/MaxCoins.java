import java.util.*;

public class MaxCoins {
    
    public int maxCoins(int[] nums) {
        
        // Declare variables 
        int n = nums.length;
        int[][] dp = new int[n][n];


        // In que we have given there is 1 before nums[0] and after nums[n] so let's add it in array 
        // in java we can't do push front push back so let's create new List<Integer>
        List<Integer> extendedNums = new ArrayList<>();

        // Add 1 at the begininng
        extendedNums.addFirst(1);

        for (int num : nums) {
            extendedNums.add(num);
        }

        // Add 1 at the end
        extendedNums.addLast(1);

        System.out.println("Extended List : " + extendedNums);


        // 
        
        return 0;
    }

    public static void main (String[] args) {

        MaxCoins solution = new MaxCoins();

        int[] nums1 = {3,1,5,8};
        System.out.println("Result 1 -> " + solution.maxCoins(nums1) + "\n");  // 167
    
        int[] nums2 = {1,5};
        System.out.println("Result 2 -> " + solution.maxCoins(nums2) + "\n");  // 10
    
    }

}

/*
 * Intuitions :
 
    1. we have given n ballons (0 to n - 1)
    2. each balloon has a number on it which tells in nums array
    3. we need to burst all balloons but there is a catch
        if we bust the ith balloon 
            will get nums[i-1] * nums[i] * nums[i+1] coins
        if i-1 and i+1 goes out of bound 
            treat it as if number on those balloons are 1

    4. need to return maximum coins we can collect by bursting balloons
 
 
 * Pattern :
 
    1. Start with entire Array or Block
	2. will mark them i and j 
		where i = start
		          j = end 
	3. Try all partitions 
		- Run A loop to try those partitions
		- take example : [ A, B, C, D]
		partitions are in format of ( i ) ( j )
			(A) (B,C,D)
			(A,B) (C,D)
			(A,B,C) (D)
	4. Return the best possible two partitions
		- Two partitions means ( i ) ( j )


    -> It seems same as binary search 
        like find start and end 
        then check mid and do operations on both sides of mid
        while loop till i <= j
	
	-> Just ithe dp create karun tyat value takaychiye

    ^ Check out below link :
        https://youtu.be/Yz4LlDSlkns?si=FlpGioHQz7mhsI_1

 
 
 * Pseudo Code :
 


 */