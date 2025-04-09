package blind75;

import java.util.Stack;

public class StockSpanner {

  // Pair class to store both price and its span
  private static class PriceSpan {
    int price;
    int span;

    PriceSpan(int price, int span) {
      this.price = price;
      this.span = span;
    }
  }

  private Stack<PriceSpan> stack;

  // Constructor to initialize the stack
  public StockSpanner() {
    stack = new Stack<>();
  }

  // For each new price, return its stock span
  public int next(int price) {
    int span = 1;

    // Pop all prices less than or equal to current price
    while (!stack.isEmpty() && stack.peek().price <= price) {
      span += stack.pop().span;
    }

    // Push current price and its calculated span
    stack.push(new PriceSpan(price, span));

    return span;
  }

  // Main method to test the class
  public static void main(String[] args) {
    StockSpanner spanner = new StockSpanner();
    System.out.println(spanner.next(100)); // 1
    System.out.println(spanner.next(80));  // 1
    System.out.println(spanner.next(60));  // 1
    System.out.println(spanner.next(70));  // 2
    System.out.println(spanner.next(60));  // 1
    System.out.println(spanner.next(75));  // 4
    System.out.println(spanner.next(85));  // 6
  }
}
