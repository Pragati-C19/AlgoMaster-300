
public class MyPow {

    public double myPow(double x, int n) {
        
    }

    public static void main(String[] args){
        MyPow solution = new MyPow();

        double x1 = 2.00000;
        int n1 = 10;
        System.out.println("Output1: " + solution.myPow(x1, n1) + "\n");    // 1024.00000

        double x2 = 2.10000;
        int n2 = 3;
        System.out.println("Output2: " + solution.myPow(x2, n2) + "\n");    // 9.26100

        double x3 = 2.00000;
        int n3 = -2;
        System.out.println("Output3: " + solution.myPow(x3, n3) + "\n");    // 0.25000

    }

}

/**
 * 
 * 
 * Intuition :
 * 
 * 1. need to calculate x raised to the power n (i.e. x^n).
 * 2. If we multiply x by itself n times, that’s O(n) — too slow for large n
 * values.
 * 3. We can speed it up using Exponentiation by Squaring — a Divide & Conquer
 * approach.
 * - x^10 = ((x^5)^2)
 * - x^5 = x * (x^2)^2
 * 
 * 
 * Pattern :
 * 
 * 1. Pattern hear is
 * - If n is even: x^n = (x^(n/2))^2
 * - If n is odd: x^n = x * (x^(n-1))
 * 2. Positive power: Just apply the squaring approach.
 * 3. Negative power: x^-n = 1 / (x^n)
 * 4. x = 0, n > 0 → 0
 * 5. x = anything, n = 0 → 1
 * 
 * Pseudo Code :
 * 
 * function myPow(x, n):
 * if n == 0:
 * return 1
 * 
 * # Handle negative powers
 * if n < 0:
 * x = 1 / x
 * n = -n
 * 
 * result = 1
 * 
 * while n > 0:
 * if n is odd:
 * result *= x
 * x = x * x # square the base
 * n = n // 2 # halve the power
 * 
 * return result
 * 
 * 
 */
