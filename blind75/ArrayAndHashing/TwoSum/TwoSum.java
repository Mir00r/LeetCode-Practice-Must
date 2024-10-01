package blind75.ArrayAndHashing.TwoSum;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TwoSum {
  public static int[] solution2(int[] nums, int target) {
    // Create a hash map to store the numbers we have seen so far and their indices.
    Map<Integer, Integer> map = new HashMap<>();

    // Traverse the array once
    for (int i = 0; i < nums.length; i++) {
      // Calculate the complement (what we need to reach the target)
      int complement = target - nums[i];

      // Check if the complement exists in the map
      if (map.containsKey(complement)) {
        // If it exists, return the indices
        return new int[] { map.get(complement), i };
      }

      // If not, store the current number and its index in the map
      map.put(nums[i], i);
    }

    // Return an empty array if no solution found
    return new int[] {};
  }

  public static int[] solution(int[] nums, int target) {
    int[] result = new int[2];
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
      if (nums[start] + nums[end] == target) {
        result[0] = start;
        result[1] = end;
        break;
      }
      end--;
      if(start == end) {
        start++;
        end = nums.length - 1;
      }
    }
    return result;
  }


  public static void main(String[] args) {
//    System.out.println(solution2(new int[] {2,7,11,15}, 19));
    System.out.println(solution2(new int[] {3,2,4}, 6));
//    System.out.println(solution(new int[] {3,3}, 6));
  }
}
