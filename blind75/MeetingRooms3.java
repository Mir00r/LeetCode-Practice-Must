package blind75;

import java.util.*;

public class MeetingRooms3 {
  public int mostBooked(int n, int[][] meetings) {
    // Sort meetings by start time
    Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

    // Min-heap of available room numbers (lowest room comes first)
    PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
    for (int i = 0; i < n; i++)
      availableRooms.offer(i);

    // Min-heap of ongoing meetings: [endTime, roomNumber]
    PriorityQueue<int[]> ongoingMeetings = new PriorityQueue<>(
      (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1])
    );

    // Count meetings per room
    int[] roomUsage = new int[n];

    for (int[] meeting : meetings) {
      int start = meeting[0], end = meeting[1];
      int duration = end - start;

      // Free up rooms where meetings have ended before the current meeting starts
      while (!ongoingMeetings.isEmpty() && ongoingMeetings.peek()[0] <= start) {
        availableRooms.offer(ongoingMeetings.poll()[1]);
      }

      if (!availableRooms.isEmpty()) {
        // Use the smallest numbered available room
        int room = availableRooms.poll();
        ongoingMeetings.offer(new int[] {end, room});
        roomUsage[room]++;
      } else {
        // Delay the meeting to when the earliest one ends
        int[] earliest = ongoingMeetings.poll();
        int delayedStart = earliest[0];
        int delayedEnd = delayedStart + duration;
        ongoingMeetings.offer(new int[] {delayedEnd, earliest[1]});
        roomUsage[earliest[1]]++;
      }
    }

    // Find the room with max usage
    int maxUsage = 0;
    int resultRoom = 0;
    for (int i = 0; i < n; i++) {
      if (roomUsage[i] > maxUsage) {
        maxUsage = roomUsage[i];
        resultRoom = i;
      }
    }

    return resultRoom;
  }

  public static void main(String[] args) {
    MeetingRooms3 scheduler = new MeetingRooms3();

    int[][] meetings1 = {{0, 10}, {1, 5}, {2, 7}, {3, 4}};
    System.out.println(scheduler.mostBooked(2, meetings1)); // Output: 0

    int[][] meetings2 = {{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}};
    System.out.println(scheduler.mostBooked(3, meetings2)); // Output: 1
  }
}
