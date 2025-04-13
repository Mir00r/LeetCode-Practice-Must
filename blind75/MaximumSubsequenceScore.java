package blind75;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumSubsequenceScore {

  public long maxScore(int[] nums1, int[] nums2, int k) {
    int n = nums1.length;

    // Step 1: Pair nums1 and nums2 with their indices
    int[][] pair = new int[n][2];
    for (int i = 0; i < n; i++) {
      pair[i][0] = nums2[i];  // sort by nums2
      pair[i][1] = nums1[i];  // value for summation
    }

    // Step 2: Sort by nums2 descending (so each loop treats nums2[i] as min)
    Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();  // Keeps smallest nums1
    long sum = 0;
    long maxScore = 0;

    // Step 3: Iterate over sorted pairs
    for (int[] p : pair) {
      int currentNums1 = p[1];
      int currentNums2 = p[0];

      minHeap.offer(currentNums1);
      sum += currentNums1;

      if (minHeap.size() > k) {
        sum -= minHeap.poll();  // Remove smallest nums1 to maintain k elements
      }

      if (minHeap.size() == k) {
        long score = sum * currentNums2;
        maxScore = Math.max(maxScore, score);
      }
    }

    return maxScore;
  }

  public static void main(String[] args) {
    MaximumSubsequenceScore solver = new MaximumSubsequenceScore();

    int[] nums1a = {1, 3, 3, 2};
    int[] nums2a = {2, 1, 3, 4};
    System.out.println("Max Score: " + solver.maxScore(nums1a, nums2a, 3));  // Expected: 12

    int[] nums1b = {4, 2, 3, 1, 1};
    int[] nums2b = {7, 5, 10, 9, 6};
    System.out.println("Max Score: " + solver.maxScore(nums1b, nums2b, 1));  // Expected: 30
  }
}
