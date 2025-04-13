package blind75;

public class FindPivotIndex {

  /**
   * Finds the leftmost pivot index where the sum of the numbers to the left equals the sum of the
   * numbers to the right.
   *
   * @param nums input array
   * @return pivot index or -1 if none exists
   */
  public int pivotIndex(int[] nums) {
    int totalSum = 0;
    for (int num : nums) {
      totalSum += num; // Step 1: compute total sum
    }

    int leftSum = 0;

    for (int i = 0; i < nums.length; i++) {
      // Step 2: check pivot condition
      if (leftSum == totalSum - leftSum - nums[i]) {
        return i; // Found pivot
      }
      // Step 3: update leftSum
      leftSum += nums[i];
    }

    return -1; // No pivot found
  }

  public static void main(String[] args) {
    FindPivotIndex finder = new FindPivotIndex();

    int[] nums1 = {1, 7, 3, 6, 5, 6};
    System.out.println("Pivot Index: " + finder.pivotIndex(nums1));  // Expected: 3

    int[] nums2 = {1, 2, 3};
    System.out.println("Pivot Index: " + finder.pivotIndex(nums2));  // Expected: -1

    int[] nums3 = {2, 1, -1};
    System.out.println("Pivot Index: " + finder.pivotIndex(nums3));  // Expected: 0
  }
}
