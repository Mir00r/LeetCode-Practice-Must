package blind75;

public class BestTimeToBuyAndSellStockWithTransactionFee {

  public int maxProfit(int[] prices, int fee) {
    if (prices == null || prices.length == 0)
      return 0;

    int n = prices.length;
    int hold = -prices[0]; // If you buy at day 0
    int cash = 0;          // If you do nothing at day 0

    for (int i = 1; i < n; i++) {
      System.out.println("Per day Price -> "+prices[i]);
      // When holding a stock: max of
      // - keep holding
      // - buy today (after selling previously)
      hold = Math.max(hold, cash - prices[i]);
      System.out.println("HOLD -> "+hold);

      // When not holding a stock: max of
      // - stay with cash
      // - sell stock today (if holding), paying fee
      cash = Math.max(cash, hold + prices[i] - fee);
      System.out.println("CASH -> "+cash);
      System.out.println("-----------------------------------------------------------------");
    }

    // Best profit must be in cash state (cannot leave it in 'hold')
    return cash;
  }

  public static void main(String[] args) {
    BestTimeToBuyAndSellStockWithTransactionFee solver =
      new BestTimeToBuyAndSellStockWithTransactionFee();

//    int[] prices1 = {1, 3, 2, 8, 4, 9};
//    int fee1 = 2;
//    System.out.println("Max Profit: " + solver.maxProfit(prices1, fee1));  // Expected: 8

    int[] prices2 = {1, 3, 7, 5, 10, 3};
    int fee2 = 3;
    System.out.println("Max Profit: " + solver.maxProfit(prices2, fee2));  // Expected: 6
  }
}
