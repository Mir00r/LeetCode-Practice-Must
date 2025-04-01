package blind75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostPopularCreator {
  public static List<List<String>> mostPopularCreator(String[] creators, String[] ids,
    int[] views) {
    Map<String, Integer> popularityMap = new HashMap<>(); // Creator -> Total Views
    Map<String, Pair<Integer, String>> maxViewMap =
      new HashMap<>(); // Creator -> (Max Views, Lexicographically Smallest ID)
    int maxPopularity = 0;

    // Step 1: Compute popularity and track most viewed video per creator
    for (int i = 0; i < creators.length; i++) {
      String creator = creators[i];
      String videoId = ids[i];
      int viewCount = views[i];

      // Update total popularity
      popularityMap.put(creator, popularityMap.getOrDefault(creator, 0) + viewCount);
      maxPopularity = Math.max(maxPopularity, popularityMap.get(creator));

      // Update most viewed video for the creator
      if (!maxViewMap.containsKey(creator) || viewCount > maxViewMap.get(creator).views ||
        (viewCount == maxViewMap.get(creator).views
          && videoId.compareTo(maxViewMap.get(creator).id) < 0)) {
        maxViewMap.put(creator, new Pair<>(viewCount, videoId));
      }
    }

    // Step 2: Find all creators with max popularity
    List<List<String>> result = new ArrayList<>();
    for (String creator : popularityMap.keySet()) {
      if (popularityMap.get(creator) == maxPopularity) {
        result.add(Arrays.asList(creator, maxViewMap.get(creator).id));
      }
    }
    return result;
  }

  // Helper class to store the most viewed video for each creator
  static class Pair<T, U> {
    T views;
    U id;

    Pair(T views, U id) {
      this.views = views;
      this.id = id;
    }
  }

  // Test the implementation
  public static void main(String[] args) {
    String[] creators1 = {"alice", "bob", "alice", "chris"};
    String[] ids1 = {"one", "two", "three", "four"};
    int[] views1 = {5, 10, 5, 4};
    System.out.println(
      mostPopularCreator(creators1, ids1, views1)); // Expected: [["alice","one"],["bob","two"]]

    String[] creators2 = {"alice", "alice", "alice"};
    String[] ids2 = {"a", "b", "c"};
    int[] views2 = {1, 2, 2};
    System.out.println(mostPopularCreator(creators2, ids2, views2)); // Expected: [["alice","b"]]
  }
}
