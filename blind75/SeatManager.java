package blind75;

import java.util.PriorityQueue;

class SeatManager {
  private PriorityQueue<Integer> minHeap; // Min Heap to store available seat numbers

  // Constructor initializes the manager with n seats
  public SeatManager(int n) {
    minHeap =
      new PriorityQueue<>(); // Min Heap ensures the lowest-numbered seat is always available
    for (int i = 1; i <= n; i++) {
      minHeap.add(i); // Populate the heap with all seat numbers
    }
  }

  // Reserves the lowest-numbered available seat
  public int reserve() {
    return minHeap.poll(); // Remove and return the smallest seat number
  }

  // Unreserves a previously reserved seat
  public void unreserve(int seatNumber) {
    minHeap.add(seatNumber); // Add the seat back to the available pool
  }
}
