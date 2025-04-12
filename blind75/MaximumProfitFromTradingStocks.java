package blind75;

public class MaximumProfitFromTradingStocks {

  // Dynamic Programming solution: 0/1 Knapsack pattern
  public int maxProfit(int[] present, int[] future, int budget) {
    int n = present.length;
    int[] dp = new int[budget + 1];  // dp[b] = max profit with budget b

    for (int i = 0; i < n; i++) {
      int cost = present[i];
      int profit = future[i] - present[i];

      // Skip if profit is not positive or can't afford
      if (profit <= 0 || cost > budget)
        continue;

      // Traverse backwards to avoid overwriting early values
      for (int b = budget; b >= cost; b--) {
        dp[b] = Math.max(dp[b], dp[b - cost] + profit);
      }
    }

    return dp[budget];
  }

  public static void main(String[] args) {
    MaximumProfitFromTradingStocks solver = new MaximumProfitFromTradingStocks();

    int[] present1 = {5, 4, 6, 2, 3}, future1 = {8, 5, 4, 3, 5};
    System.out.println("Max Profit: " + solver.maxProfit(present1, future1, 10));  // Expected: 6

    int[] present2 = {2, 2, 5}, future2 = {3, 4, 10};
    System.out.println("Max Profit: " + solver.maxProfit(present2, future2, 6));  // Expected: 5

    int[] present3 = {3, 3, 12}, future3 = {0, 3, 15};
    System.out.println("Max Profit: " + solver.maxProfit(present3, future3, 10));  // Expected: 0
  }
}
