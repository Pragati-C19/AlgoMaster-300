public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        // Two-pointer approach
        while (left < right) {
            // Calculate current area
            int width = right - left;
            int currentArea = width * Math.min(height[left], height[right]);

            // Track the maximum area
            maxWater = Math.max(maxWater, currentArea);

            // Move the shorter line inward to find a potentially larger area
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }

  
    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();

        int[] heights1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(solution.maxArea(heights1)); // Output: 49

        int[] heights2 = {1, 1};
        System.out.println(solution.maxArea(heights2)); // Output: 1

        int[] heights3 = {4, 3, 2, 1, 4};
        System.out.println(solution.maxArea(heights3)); // Output: 16

        int[] heights4 = {1, 2, 1};
        System.out.println(solution.maxArea(heights4)); // Output: 2
    }
}

/*
 * 
 * function maxArea(height):
    left = 0
    right = height.length - 1
    max_water = 0

    while left < right:
        # Calculate the area (width * min height)
        width = right - left
        area = width * min(height[left], height[right])
        max_water = max(max_water, area)

        # Move the shorter line inward to try maximizing the area
        if height[left] < height[right]:
            left += 1
        else:
            right -= 1

    return max_water

 */