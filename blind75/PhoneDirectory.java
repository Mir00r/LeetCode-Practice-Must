package blind75;

import java.util.*;

public class PhoneDirectory {
  private Set<Integer> assignedNumbers;   // Tracks numbers currently assigned
  private Queue<Integer> availableNumbers; // Queue to store available numbers

  // Constructor: initialize directory with maxNumbers available numbers
  public PhoneDirectory(int maxNumbers) {
    assignedNumbers = new HashSet<>();
    availableNumbers = new LinkedList<>();

    // Initially, all numbers are available
    for (int i = 0; i < maxNumbers; i++) {
      availableNumbers.offer(i);
    }
  }

  // Get an available number or return -1 if none available
  public int get() {
    if (availableNumbers.isEmpty()) {
      return -1;
    }
    int number = availableNumbers.poll();
    assignedNumbers.add(number); // Mark as assigned
    return number;
  }

  // Check if a specific number is available
  public boolean check(int number) {
    return !assignedNumbers.contains(number);
  }

  // Release a number and make it available again
  public void release(int number) {
    // Only add it back if it was assigned
    if (assignedNumbers.remove(number)) {
      availableNumbers.offer(number);
    }
  }
}
