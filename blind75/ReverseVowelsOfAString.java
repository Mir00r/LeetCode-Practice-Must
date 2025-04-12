package blind75;

public class ReverseVowelsOfAString {

  public String reverseVowels(String s) {
    char[] chars = s.toCharArray();
    int left = 0;
    int right = chars.length - 1;
    String vowels = "aeiouAEIOU";

    while (left < right) {
      while (left < right && vowels.indexOf(chars[left]) == -1) {
        left++;
      }
      while (left < right && vowels.indexOf(chars[right]) == -1) {
        right--;
      }
      if (left < right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
        left++;
        right--;
      }
    }
    return new String(chars);
  }
}
