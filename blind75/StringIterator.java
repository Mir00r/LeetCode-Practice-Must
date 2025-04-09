package blind75;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringIterator {
  private String compressedStr;   // The original compressed string
  private int index;              // Current position in the string
  private char currentChar;       // Current character being returned
  private int currentCount;       // Remaining count of currentChar

  // Constructor: Parse the compressed string
  public StringIterator(String compressedString) {
    this.compressedStr = compressedString;
    this.index = 0;
    this.currentChar = ' ';
    this.currentCount = 0;
    parseNext(); // Initialize the first character and count
  }

  // Return the next character, or ' ' if exhausted
  public char next() {
    if (!hasNext())
      return ' ';
    currentCount--;
    char result = currentChar;
    if (currentCount == 0) {
      parseNext(); // Move to next char/count pair
    }
    return result;
  }

  // Check if there are more characters
  public boolean hasNext() {
    return currentCount > 0;
  }

  // Helper method: parse the next character and its count
  private void parseNext() {
    if (index >= compressedStr.length())
      return;

    currentChar = compressedStr.charAt(index++);
    System.out.println("CurrentChar -> "+ currentChar);
    int count = 0;

    // Parse the number (can be multiple digits)
    while (index < compressedStr.length() && Character.isDigit(compressedStr.charAt(index))) {
      int asciiValue = compressedStr.charAt(index++) - '0';
      System.out.println("ASCII Value is -> "+asciiValue);
      count = count * 10 + asciiValue;
      System.out.println("Count is -> "+count);
    }

    currentCount = count;
    System.out.println("CurrentCount -> "+ currentCount);
  }

}
