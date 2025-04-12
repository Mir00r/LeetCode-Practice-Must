package blind75;

public class BestTimeToBuyAndSellStockWithCooldown {

  public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0)
      return 0;

    int n = prices.length;
    int hold = -prices[0];  // If you buy on day 0
    int sold = 0;           // Profit after selling today
    int rest = 0;           // Profit if you do nothing

    for (int i = 1; i < n; i++) {
      int prevHold = hold;
      int prevSold = sold;
      int prevRest = rest;

      // Either keep holding or buy today if rested yesterday
      hold = Math.max(prevHold, prevRest - prices[i]);
      System.out.println("Hold -> "+hold);

      // Sell stock today
      sold = prevHold + prices[i];
      System.out.println("Sold -> "+sold);

      // Stay at rest or just entered cooldown from a sale
      rest = Math.max(prevRest, prevSold);
      System.out.println("Rest -> "+rest);
      System.out.println("-----------------------------------------------------------------");
    }

    // Can't be holding a stock at the end for max profit
    return Math.max(sold, rest);
  }

  public static void main(String[] args) {
    BestTimeToBuyAndSellStockWithCooldown solver = new BestTimeToBuyAndSellStockWithCooldown();

    int[] prices1 = {1, 2, 3, 0, 2};
    System.out.println("Max Profit: " + solver.maxProfit(prices1));  // Expected: 3

    int[] prices2 = {1};
    System.out.println("Max Profit: " + solver.maxProfit(prices2));  // Expected: 0
  }
}
