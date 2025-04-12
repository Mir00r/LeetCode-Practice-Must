package blind75;

public class ContainerWithMostWater {

  public int maxArea(int[] height) {
    int left = 0;                     // Start pointer
    int right = height.length - 1;    // End pointer
    int maxArea = 0;                  // Track the maximum area found

    while (left < right) {
      int width = right - left;
      int minHeight = Math.min(height[left], height[right]);
      int currentArea = width * minHeight;

      maxArea = Math.max(maxArea, currentArea);  // Update if better area found

      // Move the pointer of the shorter line inward (potential to improve height)
      if (height[left] < height[right]) {
        left++;
      } else {
        right--;
      }
    }

    return maxArea;
  }

  public static void main(String[] args) {
    ContainerWithMostWater solver = new ContainerWithMostWater();

    int[] example1 = {1,8,6,2,5,4,8,3,7};
    System.out.println("Max water area: " + solver.maxArea(example1));  // Expected: 49

    int[] example2 = {1,1};
    System.out.println("Max water area: " + solver.maxArea(example2));  // Expected: 1
  }
}
