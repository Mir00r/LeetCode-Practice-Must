package blind75;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindTheDifferenceOfTwoArrays {

  public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
    // Use HashSets to efficiently store distinct elements
    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new HashSet<>();

    // Add all elements from nums1 to set1
    for (int num : nums1) {
      set1.add(num);
    }

    // Add all elements from nums2 to set2
    for (int num : nums2) {
      set2.add(num);
    }

    // Lists to store the results
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    // Find distinct elements in nums1 not present in nums2
    for (int num : nums1) {
      if (!set2.contains(num) && !list1.contains(num)) {
        list1.add(num);
      }
    }

    // Find distinct elements in nums2 not present in nums1
    for (int num : nums2) {
      if (!set1.contains(num) && !list2.contains(num)) {
        list2.add(num);
      }
    }

    // Create the result list
    List<List<Integer>> result = new ArrayList<>();
    result.add(list1);
    result.add(list2);

    return result;
  }
}
