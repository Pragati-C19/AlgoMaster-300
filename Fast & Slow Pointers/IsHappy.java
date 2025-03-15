class IsHappy {
    public boolean isHappy(int n) {
        int slow = n, fast = getNext(n);

        // Detect cycle using slow and fast pointers
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }

        // If fast reaches 1, it's a happy number
        return fast == 1;
    }

    // Helper function to compute the sum of squares of digits
    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }


    public static void main(String[] args) {
        IsHappy solution = new IsHappy();

        System.out.println(solution.isHappy(19));  // ✅ Output: true
        System.out.println(solution.isHappy(2));   // ✅ Output: false
    }
}

/*8
 * 
 * Calculate the sum of squares of digits repeatedly.
If the number reaches 1, it’s a happy number.
If the number repeats (forms a cycle), it’s unhappy — use a slow and fast pointer to detect cycles.
 */
