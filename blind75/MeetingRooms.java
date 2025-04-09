package blind75;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms {

  /**
   * Determines if a person can attend all meetings given an array of meeting time intervals.
   *
   * @param intervals An array of meeting time intervals where intervals[i] = [starti, endi].
   * @return true if a person can attend all meetings, false otherwise.
   * @throws IllegalArgumentException if the input array is null or contains invalid intervals.
   */
  public boolean canAttendMeetings(int[][] intervals) {
    // Input validation: Check for null array and invalid intervals.
    if (intervals == null) {
      throw new IllegalArgumentException("The array of intervals cannot be null.");
    }
    for (int[] interval : intervals) {
      if (interval == null || interval.length != 2 || interval[0] < 0
        || interval[1] <= interval[0]) {
        throw new IllegalArgumentException(
          "Invalid meeting time interval: " + Arrays.toString(interval));
      }
    }

    // Sort the intervals by start time.  This is crucial for efficiently detecting overlaps.
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    // Iterate through the sorted intervals and check for overlaps.
    for (int i = 0; i < intervals.length - 1; i++) {
      //If the end time of the current meeting is after the start time of the next meeting, there is an overlap
      if (intervals[i][1] > intervals[i + 1][0]) {
        return false; // Overlapping meetings found, cannot attend all.
      }
    }
    return true; // No overlapping meetings found, can attend all.
  }

  public static void main(String[] args) {
    MeetingRooms scheduler = new MeetingRooms();

    // Test cases
    int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
    System.out.println("Can attend meetings for intervals1: " + scheduler.canAttendMeetings(
      intervals1)); // Output: false

    int[][] intervals2 = {{7, 10}, {2, 4}};
    System.out.println("Can attend meetings for intervals2: " + scheduler.canAttendMeetings(
      intervals2)); // Output: true

    int[][] intervals3 = {{1, 5}, {6, 10}, {11, 15}};
    System.out.println("Can attend meetings for intervals3: " + scheduler.canAttendMeetings(
      intervals3)); // Output: true

    int[][] intervals4 = {{1, 10}, {2, 5}, {5, 8}};
    System.out.println("Can attend meetings for intervals4: " + scheduler.canAttendMeetings(
      intervals4)); // Output: false

    int[][] intervals5 = {{1, 3}, {3, 8}, {8, 10}};
    System.out.println("Can attend meetings for intervals5: " + scheduler.canAttendMeetings(
      intervals5)); //Output: true

    //Example with an empty array
    int[][] intervals6 = {};
    System.out.println("Can attend meetings for intervals6: " + scheduler.canAttendMeetings(
      intervals6)); //Output: true

    //Example with one meeting.
    int[][] intervals7 = {{10, 20}};
    System.out.println("Can attend meetings for intervals7: " + scheduler.canAttendMeetings(
      intervals7)); //Output: true

    //Example with  negative start time
    int[][] intervals8 = {{-10, 20}};
    try {
      System.out.println(
        "Can attend meetings for intervals8: " + scheduler.canAttendMeetings(intervals8));
    } catch (IllegalArgumentException e) {
      System.out.println("Exception for intervals8: " + e.getMessage());
    }

    //Example with  end time <= start time
    int[][] intervals9 = {{10, 10}};
    try {
      System.out.println(
        "Can attend meetings for intervals9: " + scheduler.canAttendMeetings(intervals9));
    } catch (IllegalArgumentException e) {
      System.out.println("Exception for intervals9: " + e.getMessage());
    }
  }
}
