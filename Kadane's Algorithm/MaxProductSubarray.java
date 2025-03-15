public class MaxProductSubarray {
    public static int maxProduct(int[] nums) {
        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];

        // Loop from the second element onward
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];

            // Save current max to avoid overwriting it before minProd uses it
            int tempMax = maxProd;

            // Update max and min products
            maxProd = Math.max(n, Math.max(n * maxProd, n * minProd));
            minProd = Math.min(n, Math.min(n * tempMax, n * minProd));

            // Track the global maximum product
            result = Math.max(result, maxProd);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, -2, 4};
        int[] nums2 = {-2, 0, -1};
        int[] nums3 = {1, -2, -3, 4};

        System.out.println("Example 1 Output: " + maxProduct(nums1)); // 6
        System.out.println("Example 2 Output: " + maxProduct(nums2)); // 0
        System.out.println("Example 3 Output: " + maxProduct(nums3)); // 12
    }
}


/**
 * 
 * def max_product_subarray(nums):
    max_prod = min_prod = result = nums[0]

    for n in nums[1:]:
        temp = max_prod  # Save current max to avoid overwriting
        max_prod = max(n, n * max_prod, n * min_prod)
        min_prod = min(n, n * temp, n * min_prod)
        result = max(result, max_prod)

    return result

 */