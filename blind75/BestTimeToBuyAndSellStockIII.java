package blind75;

public class BestTimeToBuyAndSellStockIII {
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0)
      return 0;

    int buy1 = Integer.MIN_VALUE;   // Max profit if we buy the 1st stock
    int sell1 = 0;                  // Max profit after selling the 1st stock
    int buy2 = Integer.MIN_VALUE;   // Max profit if we buy the 2nd stock
    int sell2 = 0;                  // Max profit after selling the 2nd stock

    for (int price : prices) {
      buy1 = Math.max(buy1, -price);          // Buy first stock at lowest possible price
      sell1 = Math.max(sell1, buy1 + price);  // Sell first stock for max profit
      buy2 = Math.max(buy2, sell1 - price);   // Buy second stock with remaining profit
      sell2 = Math.max(sell2, buy2 + price);  // Sell second stock for max total profit
    }

    return sell2;
  }

  public static void main(String[] args) {
    BestTimeToBuyAndSellStockIII solver = new BestTimeToBuyAndSellStockIII();

    int[] prices1 = {3, 3, 5, 0, 0, 3, 1, 4};
    System.out.println("Max Profit: " + solver.maxProfit(prices1));  // Expected: 6

    int[] prices2 = {1, 2, 3, 4, 5};
    System.out.println("Max Profit: " + solver.maxProfit(prices2));  // Expected: 4

    int[] prices3 = {7, 6, 4, 3, 1};
    System.out.println("Max Profit: " + solver.maxProfit(prices3));  // Expected: 0
  }
}
