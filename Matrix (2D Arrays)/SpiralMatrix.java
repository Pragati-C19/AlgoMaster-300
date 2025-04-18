import java.util.*;

public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse right
            for (int i = left; i <= right; i++) result.add(matrix[top][i]);
            top++;

            // Traverse down
            for (int i = top; i <= bottom; i++) result.add(matrix[i][right]);
            right--;

            // Traverse left (if rows remain)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) result.add(matrix[bottom][i]);
                bottom--;
            }

            // Traverse up (if columns remain)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) result.add(matrix[i][left]);
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        System.out.println("Example 1 Output: " + spiralOrder(matrix1));
        System.out.println("Example 2 Output: " + spiralOrder(matrix2));
    }
}


/**
 * 
 * def spiral_order(matrix):
    result = []
    top, bottom, left, right = 0, len(matrix) - 1, 0, len(matrix[0]) - 1

    while top <= bottom and left <= right:
        # Traverse right
        result.extend(matrix[top][left:right+1])
        top += 1

        # Traverse down
        for i in range(top, bottom+1):
            result.append(matrix[i][right])
        right -= 1

        # Traverse left (if rows remain)
        if top <= bottom:
            result.extend(matrix[bottom][right:left-1:-1])
            bottom -= 1

        # Traverse up (if columns remain)
        if left <= right:
            for i in range(bottom, top-1, -1):
                result.append(matrix[i][left])
            left += 1

    return result

 */