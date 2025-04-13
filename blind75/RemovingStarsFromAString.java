package blind75;

public class RemovingStarsFromAString {

  public String removeStars(String s) {
    // Use StringBuilder to efficiently build the result string
    StringBuilder result = new StringBuilder();

    // Iterate through the input string
    for (char c : s.toCharArray()) {
      // If the current character is a star, remove the last character from the result
      if (c == '*') {
        if (!result.isEmpty()) {
          result.deleteCharAt(result.length() - 1);
        }
      } else {
        // If the current character is not a star, append it to the result
        result.append(c);
      }
    }

    // Return the resulting string
    return result.toString();
  }

}
