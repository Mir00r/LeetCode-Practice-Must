package blind75;

import java.util.ArrayList;
import java.util.List;

public class BrowserHistory {

  private List<String> history;  // Stores visited URLs
  private int currentIndex;      // Points to the current page
  private int historySize;       // Stores the last valid index in history

  // Constructor to initialize the browser with a homepage
  public BrowserHistory(String homepage) {
    history = new ArrayList<>();
    history.add(homepage);
    currentIndex = 0;
    historySize = 0;
  }

  // Visit a new URL, clearing forward history
  public void visit(String url) {
    currentIndex++; // Move forward to a new page
    if (currentIndex < history.size()) {
      history.set(currentIndex, url);  // Overwrite existing history
    } else {
      history.add(url);  // Add new entry
    }
    historySize = currentIndex;  // Clear forward history
  }

  // Move 'steps' back in history
  public String back(int steps) {
    currentIndex = Math.max(0, currentIndex - steps); // Ensure index doesn't go negative
    return history.get(currentIndex);
  }

  // Move 'steps' forward in history
  public String forward(int steps) {
    currentIndex =
      Math.min(historySize, currentIndex + steps); // Ensure index doesn't exceed history size
    return history.get(currentIndex);
  }

  public static void main(String[] args) {
    BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
    browserHistory.visit("google.com");      // Visit google.com
    browserHistory.visit("facebook.com");    // Visit facebook.com
    browserHistory.visit("youtube.com");     // Visit youtube.com

    System.out.println(
      browserHistory.back(1));  // Move back to facebook.com → Output: "facebook.com"
    System.out.println(browserHistory.back(1));  // Move back to google.com → Output: "google.com"
    System.out.println(
      browserHistory.forward(1)); // Move forward to facebook.com → Output: "facebook.com"

    browserHistory.visit("linkedin.com");    // Visit linkedin.com
    System.out.println(browserHistory.forward(2)); // Cannot move forward → Output: "linkedin.com"
    System.out.println(browserHistory.back(2));  // Move back to google.com → Output: "google.com"
    System.out.println(browserHistory.back(7));  // Move back to homepage → Output: "leetcode.com"
  }
}
