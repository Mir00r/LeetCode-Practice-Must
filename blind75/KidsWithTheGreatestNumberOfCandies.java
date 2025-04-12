package blind75;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KidsWithTheGreatestNumberOfCandies {
  public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
    int maxCandies = 0;
    for (int candy : candies) {
      maxCandies = Math.max(maxCandies, candy);
    }

    List<Boolean> result = new ArrayList<>(candies.length);
    for (int candy : candies) {
      result.add(candy + extraCandies >= maxCandies);
    }

    return result;
  }
}
