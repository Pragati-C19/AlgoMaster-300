class NumArray {
    private int[] prefix;

    public NumArray(int[] nums) {
        prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}


public class NumArrayMain {
    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2)); // Output: 1
        System.out.println(numArray.sumRange(2, 5)); // Output: -1
        System.out.println(numArray.sumRange(0, 5)); // Output: -3
    }
}


/*
 * 
 * class NumArray:
    def __init__(self, nums):
        self.prefix = [0]  
        for n in nums:
            self.prefix.append(self.prefix[-1] + n)

    def sumRange(self, left, right):
        return self.prefix[right + 1] - self.prefix[left]

 */