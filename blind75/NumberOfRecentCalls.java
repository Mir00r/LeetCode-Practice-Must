package blind75;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfRecentCalls {

  private Queue<Integer> requests;

  public NumberOfRecentCalls() {
    requests = new LinkedList<>();  // Stores only timestamps in valid 3000ms window
  }

  public int ping(int t) {
    requests.add(t);  // Add the new request timestamp

    // Remove outdated requests
    while (!requests.isEmpty() && requests.peek() < t - 3000) {
      requests.poll();
    }

    return requests.size();  // Return the count of valid requests in the window
  }

  public static void main(String[] args) {
    NumberOfRecentCalls recentCounter = new NumberOfRecentCalls();

    System.out.println(recentCounter.ping(1));     // Output: 1
    System.out.println(recentCounter.ping(100));   // Output: 2
    System.out.println(recentCounter.ping(3001));  // Output: 3
    System.out.println(recentCounter.ping(3002));  // Output: 3
  }
}
