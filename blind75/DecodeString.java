package blind75;

import java.util.Stack;

public class DecodeString {

  public String decodeString(String s) {
    // Stack to store counts (repetition factors)
    Stack<Integer> countStack = new Stack<>();
    // Stack to store partially decoded strings
    Stack<String> resultStack = new Stack<>();
    // Current result string
    String result = "";
    // Current repetition count
    int index = 0;

    while (index < s.length()) {
      char currentChar = s.charAt(index);

      // If the current character is a digit, parse the count
      if (Character.isDigit(currentChar)) {
        int count = 0;
        while (Character.isDigit(s.charAt(index))) {
          count = count * 10 + (s.charAt(index) - '0');
          index++;
        }
        countStack.push(count); // Push the count onto the stack
      } else if (currentChar == '[') {
        // If the current character is '[', push the current result onto the result stack
        resultStack.push(result);
        result = ""; // Reset the result for the inner string
        index++;
      } else if (currentChar == ']') {
        // If the current character is ']', decode the inner string
        StringBuilder temp = new StringBuilder(resultStack.pop()); // Get the previous result
        int repeatTimes = countStack.pop(); // Get the repetition count
        for (int i = 0; i < repeatTimes; i++) {
          temp.append(result); // Append the inner string 'repeatTimes' times
        }
        result = temp.toString(); // Update the result
        index++;
      } else {
        // If the current character is a letter, append it to the result
        result += currentChar;
        index++;
      }
    }

    return result; // Return the final decoded string
  }
}
