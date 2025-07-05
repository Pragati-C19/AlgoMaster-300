import java.util.*;

public class NumArray {
    
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
 
 
 * Pseudo Code :
 
  


 */