/**
 * Calculates the following recursive equation:
   F_0 = 1
   F_1 = 2
   F_i = F_(i-1) * F_(i-2) for i >= 2
 * @param i the subscript to calculate up to
 * @return The value for F_i
 */
public class DPMultiplicative {


    public static long multiplicative( int i ){
        if( i == 0 || i == 1 ){
            return i+1;
        }
        long[] dp = new long[i + 1];
        dp[0] = 1;
        dp[1] = 2;
        for (int j = 2; j <= i; j++) {
            dp[j] = dp[j - 1] * dp[j - 2];
        }
        return dp[i];

        //return multiplicative( i - 1 ) * multiplicative( i - 2 );
    }
    public static void main(String[] args) {
        int[] testCases = {0, 1, 2, 3, 4, 5, 10, 15, 20}; // Different test cases

        for (int i : testCases) {
            long startTime = System.nanoTime(); // Capture start time
            long result = multiplicative(i);
            long endTime = System.nanoTime(); // Capture end time

            long duration = endTime - startTime; // Calculate execution time in nanoseconds
            System.out.println("multiplicative(" + i + ") = " + result + " | Time: " + duration + " ns");
        }
    }
}