package blind75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> anagramMap = new HashMap<>();
    List<List<String>> result = new ArrayList<>();

    for (String str : strs) {
      char[] ca = str.toCharArray();
      Arrays.sort(ca);
      String key = String.valueOf(ca);

      if (!anagramMap.containsKey(key)) {
        anagramMap.put(key, new ArrayList<>());
      }
      anagramMap.get(key).add(str);
    }
    return new ArrayList<>(anagramMap.values());
  }
}
