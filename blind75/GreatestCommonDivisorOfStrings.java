package blind75;

public class GreatestCommonDivisorOfStrings {

  public String gcdOfStrings(String str1, String str2) {
    // Check if concatenated strings are equal in both orders
    if (!(str1 + str2).equals(str2 + str1)) {
      return "";
    }

    // Compute the GCD of the lengths of the two strings
    int gcdLength = gcd(str1.length(), str2.length());

    // The largest common divisor string is the substring up to the GCD length
    return str1.substring(0, gcdLength);
  }

  // Helper method to compute the greatest common divisor using Euclidean algorithm
  private int gcd(int a, int b) {
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }
}
