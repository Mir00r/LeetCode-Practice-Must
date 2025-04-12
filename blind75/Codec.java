package blind75;

import java.util.*;

public class Codec {

  // Encodes a list of strings to a single string.
  public String encode(List<String> strs) {
    StringBuilder encoded = new StringBuilder();

    for (String str : strs) {
      encoded.append(str.length()).append('#').append(str);
    }

    return encoded.toString();
  }

  /**
   * Decodes a string that is encoded with length prefixes. The encoding format is:
   * <length>#<string>, where <length> is the length of the following string.
   *
   * @param s The encoded string to decode.
   * @return A list of decoded strings. Returns an empty list if the input string is empty.
   * @throws IllegalArgumentException if the input string is malformed.
   */
  public List<String> decode(String s) {
    List<String> result = new ArrayList<>(); // Initialize a list to store the decoded strings.
    int i = 0; // Initialize a pointer to traverse the encoded string.

    // Iterate through the encoded string.  The loop continues as long as the pointer
    // 'i' is within the bounds of the string.
    while (i < s.length()) {
      int delimiterIndex = s.indexOf('#',
        i); // Find the index of the '#' delimiter, starting from the current position 'i'.
//      System.out.println("delimiterIndex -> " + delimiterIndex);

      // Check if the delimiter '#' was not found.  If not, the string is malformed.
//      if (delimiterIndex == -1) {
//        throw new IllegalArgumentException("Malformed encoded string: Missing '#' delimiter.");
//      }

      // Extract the length of the encoded string.
      int len;
//      try {
        len = Integer.parseInt(s.substring(i,
          delimiterIndex)); // Parse the substring representing the length (from 'i' to '#') as an integer.
//      } catch (NumberFormatException e) {
//        throw new IllegalArgumentException("Malformed encoded string: Invalid length format.", e);
//      }

//      System.out.println("Len -> " + len);

      i = delimiterIndex + 1; // Move the pointer 'i' to the character immediately after the '#'.
//      System.out.println("Move past # -> " + i);

      // Check if the length is greater than the remaining part of the string.
//      if (i + len > s.length()) {
//        throw new IllegalArgumentException(
//          "Malformed encoded string: Length exceeds remaining string.");
//      }
      // Extract the encoded string using the parsed length.
      String decodedString = s.substring(i, i + len);
      result.add(decodedString); // Add the decoded string to the result list.

      i += len; // Move the pointer 'i' to the beginning of the next encoded part (after the decoded string).
    }

    return result; // Return the list of decoded strings.
  }

  // For testing
  public static void main(String[] args) {
    Codec codec = new Codec();

    List<String> input = Arrays.asList("Hello", "World", "", "123#456", "😄 emoji!");
    String encoded = codec.encode(input);
    List<String> decoded = codec.decode(encoded);

    System.out.println("Encoded: " + encoded);
    System.out.println("Decoded: " + decoded);
    System.out.println("Equal?  : " + input.equals(decoded)); // should be true
  }
}

