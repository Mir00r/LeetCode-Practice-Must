package blind75;

public class ReverseWordsInAString {

  public String reverseWords(String s) {
    // Trim leading and trailing spaces and split the string into words
    String[] words = s.trim().split("\\s+");

    // Use StringBuilder for efficient string concatenation
    StringBuilder reversed = new StringBuilder();

    // Iterate through words in reverse order
    for (int i = words.length - 1; i >= 0; i--) {
      reversed.append(words[i]);
      if (i > 0) {
        reversed.append(" "); // Add a single space between words
      }
    }

    return reversed.toString(); // Return the reversed string
  }
}
