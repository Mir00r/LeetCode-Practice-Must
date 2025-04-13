package blind75;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DetermineIfTwoStringsAreClose {

  public boolean closeStrings(String word1, String word2) {
    // If the lengths of the words are different, they cannot be close.
    if (word1.length() != word2.length()) {
      return false;
    }

    // Count the frequency of each character in both words.
    Map<Character, Integer> freq1 = new HashMap<>();
    Map<Character, Integer> freq2 = new HashMap<>();
    for (char c : word1.toCharArray()) {
      freq1.put(c, freq1.getOrDefault(c, 0) + 1);
    }
    for (char c : word2.toCharArray()) {
      freq2.put(c, freq2.getOrDefault(c, 0) + 1);
    }

    // Check if the words have the same set of characters.
    if (!freq1.keySet().equals(freq2.keySet())) {
      return false;
    }

    // Check if the frequency counts are the same after sorting.
    int[] counts1 = freq1.values().stream().mapToInt(Integer::intValue).toArray();
    int[] counts2 = freq2.values().stream().mapToInt(Integer::intValue).toArray();
    Arrays.sort(counts1);
    Arrays.sort(counts2);

    return Arrays.equals(counts1, counts2);
  }
}
