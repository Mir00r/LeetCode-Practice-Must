package blind75;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueNumberOfOccurrences {

  public boolean uniqueOccurrences(int[] arr) {
    // Map to store the frequency of each element
    Map<Integer, Integer> frequencyMap = new HashMap<>();

    // Count the occurrences of each element
    for (int num : arr) {
      frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    }

    // Set to store the unique frequencies
    Set<Integer> frequencySet = new HashSet<>();

    // Check if the frequencies are unique
    for (int frequency : frequencyMap.values()) {
      if (frequencySet.contains(frequency)) {
        return false; // Found a duplicate frequency
      }
      frequencySet.add(frequency);
    }

    return true; // All frequencies are unique
  }
}
