package blind75;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {

  public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals.length == 0) return 0;

    // Sort intervals based on end time
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

    int count = 0;
    int end = intervals[0][1];

    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] >= end) {
        // No overlap, update end to current interval's end
        end = intervals[i][1];
      } else {
        // Overlap, increment removal count
        count++;
      }
    }

    return count;
  }
}
