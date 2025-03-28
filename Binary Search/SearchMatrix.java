import java.util.*;

public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;      // Number of rows
        int n = matrix[0].length;   // Number of col
        System.out.println("Matrix of ( " + m + " * " + n + " )");

        int left = 0;
        int right = (m * n) - 1;    // (m * n) is length of whole matrix
        System.out.println("Initial Values | left: " + left + " , Right: " + right);

        while (left <= right) {
            
            int mid = (left + right) / 2;
            System.out.println("Mid value : " + mid);

            int row = mid / n;  // find row of that mid
            int col = mid % n;  // find col of that mid

            if(matrix[row][col] == target){
                System.out.println("Target found at row: " + row + " col: " + col);
                return true;
            }
            else if (matrix[row][col] > target) {
                right = mid - 1;
                System.out.println("[IF] Right Value: " + right);
            }
            else {
                left = mid + 1;
                System.out.println("[ELSE] Left Value: " + left);
            }

        }
        
        System.out.println("Target not found");
        return false;
    }

    public static void main(String[] args) {
        SearchMatrix solution = new SearchMatrix();

        int[][] matrix = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 60 }
        };

        int target1 = 3;
        int target2 = 13;

        System.out.println("Result1 : " + solution.searchMatrix(matrix, target1) + "\n");
        System.out.println("Result2 : " + solution.searchMatrix(matrix, target2));

    }

}

/**
 * 
 * 
 * Intuitions :
 * 
 * 1. we have given an m x n matrix
 * 2. each row are sorted in increasing order
 * 3. first integer is greater than the last integer
 * 4. As per our pattern if u need to solve matrix problem and it's row or col
 * are solted then use binary search
 * 5. And to use binary search first we need to convert 2D array to 1D
 * 6. they check for target if u found it then return true.
 * 
 * Pattern :
 * 
 * 1. Convert 2D array (m x n) to 1D by below method
 * - index = row * n + col
 * 2. Covert 1D array to 2D array (m x n) by below method
 * - row = index / n
 * - col = index % n
 * 3. Use binary search to find target in 1D array
 * 4. If target found then return true else return false
 * 
 * Pseudo Code :
 * 
 * 
 * function searchMatrix(int[][] matrix, int target){
 * 
 * int m = matrix.length; // Number of rows
 * int n = matrix[0].length; // Number of columns
 * 
 * left = 0
 * right = m * n - 1 // m * n is length of whole matrix
 * 
 * while(left < right){
 * mid = (left + right) / 2
 * 
 * row = mid / n
 * col = mid % n
 * 
 * if(matrix[row][col] == target){
 * return true
 * }
 * else if(matrix[row][col] > target){
 * right = mid - 1; // We are using mid - 1 instead of mid bcoz if u use mid
 * again it will go in infinite loop
 * }
 * else {
 * left = mid + 1;
 * }
 * }
 * 
 * return false
 * }
 * 
 * 
 * 
 */
