package blind75.ArrayAndHashing.ContainsDuplicate;

import java.util.TreeSet;

public class ContainsDuplicate {
  public static boolean solution(int[] nums) {
    TreeSet<Integer> set = new TreeSet<>();

    for (int num : nums) {
      // Insert the current number, and check if the same number already exists.
      if (!set.add(num)) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.out.println(solution(new int[] {1, 2, 3, 1}));
    System.out.println(solution(new int[] {1, 2, 3, 4}));
    System.out.println(solution(new int[] {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
    System.out.println(solution(new int[] {2, 14, 18, 22, 22}));
    System.out.println(solution(new int[] {0, 4, 5, 0, 3, 6}));
    System.out.println(solution(new int[] {1, 1, 1, 1}));
    System.out.println(solution(new int[] {83, 83, 83}));
    System.out.println(solution(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
  }
}
