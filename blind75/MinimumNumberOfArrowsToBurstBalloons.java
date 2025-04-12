package blind75;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumNumberOfArrowsToBurstBalloons {

  public int findMinArrowShots(int[][] points) {
    if (points.length == 0)
      return 0;

    // Sort balloons by their ending x coordinate (greedy target point)
    Arrays.sort(points, Comparator.comparingInt(a -> a[1]));

    int arrows = 1;  // At least one arrow for the first balloon
    int lastShot = points[0][1];  // Aim at the end of the first balloon

    for (int i = 1; i < points.length; i++) {
      int xStart = points[i][0];

      // If current balloon starts after the last arrow position, new arrow needed
      if (xStart > lastShot) {
        arrows++;
        lastShot = points[i][1];  // Update to end of current balloon
      }
      // Else: balloon is burst by the previous arrow, do nothing
    }

    return arrows;
  }

  public static void main(String[] args) {
    MinimumNumberOfArrowsToBurstBalloons solver = new MinimumNumberOfArrowsToBurstBalloons();

    int[][] points1 = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
    System.out.println("Minimum Arrows: " + solver.findMinArrowShots(points1));  // Expected: 2

    int[][] points2 = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
    System.out.println("Minimum Arrows: " + solver.findMinArrowShots(points2));  // Expected: 4

    int[][] points3 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
    System.out.println("Minimum Arrows: " + solver.findMinArrowShots(points3));  // Expected: 2
  }
}
