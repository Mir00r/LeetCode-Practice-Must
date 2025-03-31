package blind75;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Tweet Counts Per Frequency
public class TweetCounts {
  private Map<String, List<Integer>> tweetMap;

  public TweetCounts() {
    tweetMap = new HashMap<>();
  }

  // O(1) amortized, since insertions are at the end
  public void recordTweet(String tweetName, int time) {
    tweetMap.putIfAbsent(tweetName, new ArrayList<>());
    tweetMap.get(tweetName).add(time);
  }

  // O(n) for iterating through timestamps, O(k) for chunk division where k is the number of chunks
  public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime,
    int endTime) {
    int chunkSize = getChunkSize(freq);
    List<Integer> tweetTimes = tweetMap.getOrDefault(tweetName, new ArrayList<>());
    Collections.sort(tweetTimes);  // Ensure timestamps are sorted

    int chunks = (endTime - startTime) / chunkSize + 1;
    int[] counts = new int[chunks];

    // Iterate through timestamps and count in the right bucket
    for (int time : tweetTimes) {
      if (time >= startTime && time <= endTime) {
        int bucket = (time - startTime) / chunkSize;
        counts[bucket]++;
      }
    }

    List<Integer> result = new ArrayList<>();
    for (int count : counts) {
      result.add(count);
    }
    return result;
  }

  private int getChunkSize(String freq) {
    return switch (freq) {
      case "minute" -> 60;
      case "hour" -> 3600;
      case "day" -> 86400;
      default -> throw new IllegalArgumentException("Invalid frequency");
    };
  }
}
