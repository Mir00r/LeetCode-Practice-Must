package blind75;

import java.util.Map;
import java.util.TreeMap;

public class BetterStringCompressor {
  public static String reformatString(String s) {
    // Use TreeMap to store characters and combined counts, automatically sorted by character.
    Map<Character, Integer> charCountMap = new TreeMap<>();
    StringBuilder result = new StringBuilder();
    int i = 0;

    while (i < s.length()) {
      char c = s.charAt(i);
      i++; // Move to the next character (which should be a digit).

      int count = 0;
      // Extract the number, which could be multiple digits
      while (i < s.length() && Character.isDigit(s.charAt(i))) {
        count = count * 10 + (s.charAt(i) - '0');
        i++;
      }
      //If the character is already in the map, add to the existing count.
      charCountMap.put(c, charCountMap.getOrDefault(c, 0) + count);
    }

    // Build the result string from the sorted map.
    for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
      result.append(entry.getKey()).append(entry.getValue());
    }

    return result.toString();
  }

  public static void main(String[] args) {
    String s = "a3c9b2c1";
    String reformattedString = reformatString(s);
    System.out.println("Reformatted string: " + reformattedString); // Output: a3b2c10

    String s2 = "a1b2c3d4e5";
    String reformattedString2 = reformatString(s2);
    System.out.println("Reformatted string 2: " + reformattedString2); // Output: a1b2c3d4e5

    String s3 = "a10b20c30";
    String reformattedString3 = reformatString(s3);
    System.out.println("Reformatted string 3: " + reformattedString3); // Output: a10b20c30

    String s4 = "a1b2c3a4b5c6";
    String reformattedString4 = reformatString(s4);
    System.out.println("Reformatted string 4: " + reformattedString4); // Output: a5b7c9
  }
}
