package blind75;

import java.util.*;

class NumberContainers {
  // Map to store the index -> number mapping
  private Map<Integer, Integer> indexToNumber;

  // Map to store number -> sorted set of indices
  private Map<Integer, TreeSet<Integer>> numberToIndices;

  // Constructor: Initializes the system
  public NumberContainers() {
    indexToNumber = new HashMap<>();
    numberToIndices = new HashMap<>();
  }

  // ✅ Change or Insert a number at a given index
  public void change(int index, int number) {
    if (indexToNumber.containsKey(index)) {
      // Remove old number from index mapping
      int oldNumber = indexToNumber.get(index);
      if (numberToIndices.containsKey(oldNumber)) {
        numberToIndices.get(oldNumber).remove(index);
        if (numberToIndices.get(oldNumber).isEmpty()) {
          numberToIndices.remove(oldNumber); // Cleanup empty sets
        }
      }
    }

    // Update the mapping
    indexToNumber.put(index, number);

    // Add index to the new number set
    numberToIndices.putIfAbsent(number, new TreeSet<>());
    numberToIndices.get(number).add(index);
  }

  // ✅ Find the smallest index for a given number
  public int find(int number) {
    if (!numberToIndices.containsKey(number) || numberToIndices.get(number).isEmpty()) {
      return -1; // No index found for this number
    }
    return numberToIndices.get(number).first(); // Smallest index
  }

  public static void main(String[] args) {
    NumberContainers nc = new NumberContainers();
    System.out.println(nc.find(10)); // -1
    nc.change(2, 10);
    nc.change(1, 10);
    nc.change(3, 10);
    nc.change(5, 10);
    System.out.println(nc.find(10)); // 1
    nc.change(1, 20);
    System.out.println(nc.find(10)); // 2
  }
}

