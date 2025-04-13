package blind75;

import java.util.PriorityQueue;

public class KthLargestElementInAnArray {

  public int findKthLargest(int[] nums, int k) {
    // Use a min-heap (PriorityQueue) to store the k largest elements.
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // Iterate through the nums array.
    for (int num : nums) {
      // Add the current number to the min-heap.
      minHeap.offer(num);

      // If the size of the min-heap exceeds k, remove the smallest element.
      // This ensures that the min-heap always contains the k largest elements.
      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }

    // After iterating through all elements, the smallest element in the min-heap
    // is the kth largest element.
    return minHeap.peek();
  }
}
