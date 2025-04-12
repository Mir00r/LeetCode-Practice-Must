package blind75;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class MaximumEarningsFromTaxi {

  public long maxTaxiEarnings(int n, int[][] rides) {
    // Sort rides by their end position for chronological DP
    Arrays.sort(rides, Comparator.comparingInt(a -> a[1]));

    // TreeMap: key = end point, value = max profit up to that point
    TreeMap<Integer, Long> dp = new TreeMap<>();
    dp.put(0, 0L); // Base case: before point 1, earnings are 0

    for (int[] ride : rides) {
      int start = ride[0];
      int end = ride[1];
      int tip = ride[2];

      // Find the best profit before the start of the current ride
      Long bestBeforeStart = dp.floorEntry(start).getValue();
      System.out.println("-----------------------------------------------------------------");
      System.out.println("BestBeforeStart -> "+bestBeforeStart);

      // Profit if taking this ride
      long newProfit = bestBeforeStart + (end - start + tip);
      System.out.println("NewProfit -> "+newProfit);

      // If this profit improves the best known for 'end', update it
      long currentBestAtEnd = dp.getOrDefault(end, 0L);
      System.out.println("CurrentBestAtEnd -> "+currentBestAtEnd);
      System.out.println("-----------------------------------------------------------------");

      if (newProfit > currentBestAtEnd) {
        dp.put(end, newProfit);
      }
    }

    // Max profit is the last value in the TreeMap
    return dp.lastEntry().getValue();
  }

  // Testing with the given examples
  public static void main(String[] args) {
    MaximumEarningsFromTaxi solver = new MaximumEarningsFromTaxi();

    int[][] rides1 = {{2, 5, 4}, {1, 5, 1}};
    System.out.println(solver.maxTaxiEarnings(5, rides1)); // Expected: 7

    int[][] rides2 = {{1, 6, 1}, {3, 10, 2}, {10, 12, 3}, {11, 12, 2}, {12, 15, 2}, {13, 18, 1}};
    System.out.println(solver.maxTaxiEarnings(20, rides2)); // Expected: 20
  }
}
