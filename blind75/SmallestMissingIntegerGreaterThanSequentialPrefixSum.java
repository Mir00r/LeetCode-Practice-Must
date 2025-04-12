package blind75;

import java.util.HashSet;
import java.util.Set;

public class SmallestMissingIntegerGreaterThanSequentialPrefixSum {

  /**
   * Given a 0-indexed array of integers nums, returns the smallest integer x missing from nums such
   * that x is greater than or equal to the sum of the longest sequential prefix.
   *
   * @param nums 0-indexed array of integers
   * @return the smallest integer x missing from nums such that x is greater than or equal to the
   * sum of the longest sequential prefix
   */
  public int smallestMissing(int[] nums) {
    // 1. Find the longest sequential prefix and its sum
    int longestPrefixSum = 0;
    int longestPrefixLength = 0;
    if (nums.length > 0) { //handle edge case
      longestPrefixSum = nums[0];
      longestPrefixLength = 1;
    }

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1] + 1) {
        longestPrefixSum += nums[i];
        longestPrefixLength++;
      } else {
        break; // Stop when the sequence is broken
      }
    }
    //If the input array is sequential, we need to calculate the sum.
    if (longestPrefixLength == nums.length) {
      longestPrefixSum = 0;
      for (int num : nums) {
        longestPrefixSum += num;
      }
    }

    // 2. Find the smallest missing integer >= longestPrefixSum
    Set<Integer> numSet = new HashSet<>();
    for (int num : nums) {
      numSet.add(num);
    }

    int missingInteger = longestPrefixSum;
    while (numSet.contains(missingInteger)) {
      missingInteger++;
    }

    return missingInteger;
  }
}
