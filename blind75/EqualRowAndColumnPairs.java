package blind75;

public class EqualRowAndColumnPairs {

  public int equalPairs(int[][] grid) {
    int n = grid.length;
    int count = 0;

    // Iterate through each row
    for (int i = 0; i < n; i++) {
      // Iterate through each column
      for (int j = 0; j < n; j++) {
        // Check if the current row and column are equal
        if (areEqual(grid, i, j)) {
          count++;
        }
      }
    }

    return count;
  }

  // Helper function to check if a row and column are equal
  private boolean areEqual(int[][] grid, int rowIndex, int colIndex) {
    int n = grid.length;
    for (int k = 0; k < n; k++) {
      if (grid[rowIndex][k] != grid[k][colIndex]) {
        return false; // If any element is different, they are not equal
      }
    }
    return true; // All elements are equal
  }
}
