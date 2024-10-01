package blind75.ArrayAndHashing.ValidAnagram;

import java.util.LinkedHashMap;
import java.util.Map;

public class ValidAnagram {
  public static boolean solution2(String s, String t) {
    if (s.length() != t.length()) return false;

    Map<Character, Integer> sFrequencyMap = processFrequencyMap(s);
    Map<Character, Integer> tFrequencyMap = processFrequencyMap(t);

    return sFrequencyMap.equals(tFrequencyMap);
  }

  public static Map<Character, Integer> processFrequencyMap(String str) {
    Map<Character, Integer> frequenceyMap = new LinkedHashMap<>();

    for (char c : str.toCharArray()) {
      frequenceyMap.put(c, frequenceyMap.getOrDefault(c, 0) + 1);
    }
    return frequenceyMap;
  }

  public static boolean solution(String s, String t) {
    if (s.length() != t.length()) return false;

    // array to store the latter frequency
    int[] letterCount = new int[26];

    // Increment for characters in s, decrement for characters in t
    for (int i = 0; i < s.length(); i++) {
      letterCount[s.charAt(i) - 'a']++;
      letterCount[t.charAt(i) - 'a']--;
    }

    // check if all count is zero or not
    for (int lc : letterCount) {
      if (lc != 0) return false;
    }
    return true;
  }


  public static void main(String[] args) {
    System.out.println(solution("rat", "cat"));
    System.out.println(solution("anagram", "nagaram"));
  }
}
