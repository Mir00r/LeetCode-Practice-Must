package blind75;

import java.util.HashMap;
import java.util.Map;

public class MaxNumberofKSumPairs {

  public int maxOperations(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();  // Count the occurrences
    int operations = 0;

    for (int num : nums) {
      int complement = k - num;

      // If complement exists, form a pair
      if (map.getOrDefault(complement, 0) > 0) {
        operations++;  // One valid pair found
        map.put(complement, map.get(complement) - 1);  // Decrement complement
      } else {
        map.put(num, map.getOrDefault(num, 0) + 1);  // Store num for future use
      }
    }

    return operations;
  }

  public static void main(String[] args) {
    MaxNumberofKSumPairs solver = new MaxNumberofKSumPairs();

    int[] nums1 = {1, 2, 3, 4};
    System.out.println("Max Operations: " + solver.maxOperations(nums1, 5));  // Expected: 2

    int[] nums2 = {3, 1, 3, 4, 3};
    System.out.println("Max Operations: " + solver.maxOperations(nums2, 6));  // Expected: 1
  }
}
