package blind75;

public class StringCompressor {
  public int compress(char[] chars) {
    int write = 0; // Pointer to write the compressed chars
    int read = 0;  // Pointer to read through the array
    int chLen = chars.length;

    while (read < chLen) {
      char currentChar = chars[read];
      int groupStart = read;

      // Move read pointer to the end of the group
      while (read < chLen && chars[read] == currentChar) {
        read++;
      }

      int count = read - groupStart;

      // Write the character
      chars[write++] = currentChar;

      // If count > 1, write the count digits
      if (count > 1) {
        for (char digit : String.valueOf(count).toCharArray()) {
          chars[write++] = digit;
        }
      }
    }

    return write; // New length of the compressed array
  }

  public static void main(String[] args) {
    StringCompressor sc = new StringCompressor();

    char[] chars1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
    int len1 = sc.compress(chars1);
    System.out.println(len1); // 6
    System.out.println(
      java.util.Arrays.toString(java.util.Arrays.copyOf(chars1, len1))); // [a, 2, b, 2, c, 3]

    char[] chars2 = {'a'};
    int len2 = sc.compress(chars2);
    System.out.println(len2); // 1
    System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(chars2, len2))); // [a]

    char[] chars3 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
    int len3 = sc.compress(chars3);
    System.out.println(len3); // 4
    System.out.println(
      java.util.Arrays.toString(java.util.Arrays.copyOf(chars3, len3))); // [a, b, 1, 2]
  }
}
