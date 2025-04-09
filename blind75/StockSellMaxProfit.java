package blind75;

public class StockSellMaxProfit {

  public int maxProfit(int[] prices) {
    // Initialize minPrice to the maximum possible value
    int minPrice = Integer.MAX_VALUE;
    // Initialize maxProfit to 0 (no profit if no transaction is done)
    int maxProfit = 0;

    for (int price : prices) {
      // Update minPrice if current price is lower than what we've seen before
      if (price < minPrice) {
        minPrice = price;
        System.out.println("MinPrice -> "+minPrice);
      }
      // Calculate potential profit if we sell at current price
      else if (price - minPrice > maxProfit) {
        // Update maxProfit if this potential profit is better
        maxProfit = price - minPrice;
        System.out.println("MaxPrice -> "+maxProfit);
      }
    }

    return maxProfit;
  }
}
