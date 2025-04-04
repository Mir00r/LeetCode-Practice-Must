package blind75;

import java.util.*;

class FoodRatings {
  // Map food name to its cuisine
  private Map<String, String> foodToCuisine;
  // Map food name to its rating
  private Map<String, Integer> foodToRating;
  // Map cuisine to a sorted set of foods based on rating and lexicographic order
  private Map<String, TreeSet<Food>> cuisineToFoods;


  // Custom class for ordering foods based on rating (highest first) and name (lexicographically)
  private static class Food implements Comparable<Food> {
    String name;
    int rating;

    Food(String name, int rating) {
      this.name = name;
      this.rating = rating;
    }

    @Override
    public int compareTo(Food other) {
      if (this.rating != other.rating) {
        return Integer.compare(other.rating, this.rating); // Higher rating first
      }
      return this.name.compareTo(other.name); // Lexicographically smaller first
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (!(obj instanceof Food))
        return false;
      Food other = (Food) obj;
      return this.name.equals(other.name) && this.rating == other.rating;
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, rating);
    }
  }

  public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
    foodToCuisine = new HashMap<>();
    foodToRating = new HashMap<>();
    cuisineToFoods = new HashMap<>();

    for (int i = 0; i < foods.length; i++) {
      String food = foods[i];
      String cuisine = cuisines[i];
      int rating = ratings[i];

      foodToCuisine.put(food, cuisine);
      foodToRating.put(food, rating);
      cuisineToFoods.putIfAbsent(cuisine, new TreeSet<>());
      cuisineToFoods.get(cuisine).add(new Food(food, rating));
    }
  }

  public void changeRating(String food, int newRating) {
    String cuisine = foodToCuisine.get(food);
    int oldRating = foodToRating.get(food);
    Food oldFood = new Food(food, oldRating);

    // Remove the old rating entry
    cuisineToFoods.get(cuisine).remove(oldFood);

    // Update the rating
    foodToRating.put(food, newRating);

    // Add the updated food with new rating
    cuisineToFoods.get(cuisine).add(new Food(food, newRating));
  }

  public String highestRated(String cuisine) {
    return cuisineToFoods.get(cuisine).first().name; // Get highest-rated food
  }

  public static void main(String[] args) {
    String[] foods = {"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"};
    String[] cuisines = {"korean", "japanese", "japanese", "greek", "japanese", "korean"};
    int[] ratings = {9, 12, 8, 15, 14, 7};

    FoodRatings foodRatings = new FoodRatings(foods, cuisines, ratings);

    System.out.println(foodRatings.highestRated("korean"));  // Output: "kimchi"
    System.out.println(foodRatings.highestRated("japanese")); // Output: "ramen"

    foodRatings.changeRating("sushi", 16);
    System.out.println(foodRatings.highestRated("japanese")); // Output: "sushi"

    foodRatings.changeRating("ramen", 16);
    System.out.println(foodRatings.highestRated("japanese")); // Output: "ramen"
  }
}
