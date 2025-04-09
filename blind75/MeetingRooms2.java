package blind75;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms2 {

  /**
   * Determines if a person can attend all meetings given an array of meeting time intervals.
   *
   * @param intervals An array of meeting time intervals where intervals[i] = [starti, endi].
   * @return true if a person can attend all meetings, false otherwise.
   * @throws IllegalArgumentException if the input array is null or contains invalid intervals.
   */
  public int minMeetingRooms(int[][] intervals) {
    // Input validation
    if (intervals == null) {
      throw new IllegalArgumentException("The array of intervals cannot be null.");
    }
    for (int[] interval : intervals) {
      if (interval == null || interval.length != 2 || interval[0] < 0 || interval[1] <= interval[0]) {
        throw new IllegalArgumentException("Invalid meeting time interval: " + Arrays.toString(interval));
      }
    }

    // If there are no meetings, no rooms are needed.
    if (intervals.length == 0) {
      return 0;
    }

    // Sort the intervals by start time.  This is crucial for efficiently detecting overlaps.
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    // Use a min-heap (PriorityQueue) to track the end times of meetings.
    // The head of the queue will always be the meeting that ends the soonest.
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    minHeap.offer(intervals[0][1]); // Add the end time of the first meeting.

    // Iterate through the remaining meetings.
    for (int i = 1; i < intervals.length; i++) {
      // If the current meeting starts after the meeting that ends the soonest
      if (intervals[i][0] >= minHeap.peek()) {
        // No overlap, so we can reuse the same room.
        minHeap.poll(); // Remove the meeting that ends the soonest.
      }
      // Allocate a new room (add the end time to the heap).
      minHeap.offer(intervals[i][1]);
    }

    // The size of the min-heap is the number of rooms needed.
    return minHeap.size();
  }

  public static void main(String[] args) {
    MeetingRooms2 scheduler = new MeetingRooms2();

    // Test cases
    int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
    System.out.println("Number of meeting room need for intervals1: " + scheduler.minMeetingRooms(
      intervals1)); // Output: false

    int[][] intervals2 = {{7, 10}, {2, 4}};
    System.out.println("Number of meeting room need for intervals2: " + scheduler.minMeetingRooms(
      intervals2)); // Output: true

    int[][] intervals3 = {{1, 5}, {6, 10}, {11, 15}};
    System.out.println("Number of meeting room need for intervals3: " + scheduler.minMeetingRooms(
      intervals3)); // Output: true

    int[][] intervals4 = {{1, 10}, {2, 5}, {5, 8}};
    System.out.println("Number of meeting room need for intervals4: " + scheduler.minMeetingRooms(
      intervals4)); // Output: false

    int[][] intervals5 = {{1, 3}, {3, 8}, {8, 10}};
    System.out.println("Number of meeting room need for intervals5: " + scheduler.minMeetingRooms(
      intervals5)); //Output: true

    //Example with an empty array
    int[][] intervals6 = {};
    System.out.println("Number of meeting room need for intervals6: " + scheduler.minMeetingRooms(
      intervals6)); //Output: true

    //Example with one meeting.
    int[][] intervals7 = {{10, 20}};
    System.out.println("Number of meeting room need for intervals7: " + scheduler.minMeetingRooms(
      intervals7)); //Output: true

    //Example with  negative start time
    int[][] intervals8 = {{-10, 20}};
    try {
      System.out.println(
        "Number of meeting room need for intervals8: " + scheduler.minMeetingRooms(intervals8));
    } catch (IllegalArgumentException e) {
      System.out.println("Exception for intervals8: " + e.getMessage());
    }

    //Example with  end time <= start time
    int[][] intervals9 = {{10, 10}};
    try {
      System.out.println(
        "Number of meeting room need for intervals9: " + scheduler.minMeetingRooms(intervals9));
    } catch (IllegalArgumentException e) {
      System.out.println("Exception for intervals9: " + e.getMessage());
    }
  }
}
