package blind75;

public class FindTheHighestAltitude {

  /**
   * This method calculates the highest altitude reached during the biker's road trip using prefix
   * sum logic.
   */
  public int largestAltitude(int[] gain) {
    int currentAltitude = 0;   // Start at sea level
    int maxAltitude = 0;       // Initial max is 0

    for (int g : gain) {
      currentAltitude += g;             // Update current altitude
      System.out.println("Current Altitude -> "+currentAltitude);
      maxAltitude = Math.max(maxAltitude, currentAltitude);  // Track highest point
      System.out.println("Max Altitude -> "+maxAltitude);
      System.out.println("---------------------------------------------");
    }

    return maxAltitude;
  }

  public static void main(String[] args) {
    FindTheHighestAltitude solver = new FindTheHighestAltitude();

//    int[] gain1 = {-5, 1, 5, 0, -7};
//    System.out.println("Highest Altitude: " + solver.largestAltitude(gain1));  // Expected: 1

    int[] gain2 = {-4, -3, -2, -1, 4, 3, 2};
    System.out.println("Highest Altitude: " + solver.largestAltitude(gain2));  // Expected: 0
  }
}
