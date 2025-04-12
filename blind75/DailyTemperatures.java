package blind75;

import java.util.Stack;

public class DailyTemperatures {

  public int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    int[] result = new int[n];
    Stack<Integer> stack = new Stack<>(); // Stack stores indices

    // Traverse from left to right
    for (int i = 0; i < n; i++) {
      // If current temperature is warmer than the temperature at top index of the stack
      while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
        System.out.println("temperatures I -> "+temperatures[i]);
        System.out.println("temperatures Stack -> "+temperatures[stack.peek()]);

        int prevIndex = stack.pop(); // Index with unresolved warmer temperature

        System.out.println("Index I -> "+i);
        System.out.println("prevIndex -> "+prevIndex);
        System.out.println("Days waited -> "+(i-prevIndex));
        result[prevIndex] = i - prevIndex; // Days waited
      }

      System.out.println("--------------Before push-------------------------");
      stack.push(i); // Push current index onto the stack
    }

    // Remaining indices have 0 by default (no warmer day found)
    return result;
  }

  public static void main(String[] args) {
    DailyTemperatures solver = new DailyTemperatures();

    int[] test1 = {73, 74, 75, 71, 69, 72, 76, 73};
    int[] res1 = solver.dailyTemperatures(test1);
    System.out.println(java.util.Arrays.toString(res1)); // [1, 1, 4, 2, 1, 1, 0, 0]

//    int[] test2 = {30, 40, 50, 60};
//    int[] res2 = solver.dailyTemperatures(test2);
//    System.out.println(java.util.Arrays.toString(res2)); // [1, 1, 1, 0]
//
//    int[] test3 = {30, 60, 90};
//    int[] res3 = solver.dailyTemperatures(test3);
//    System.out.println(java.util.Arrays.toString(res3)); // [1, 1, 0]
  }
}
