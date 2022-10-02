package food;

/**
 * A Food interface represents the name and amount of a certain type of food.
 * Different implementation may have different ways of keeping track of the
 * food, and how to get the desired value.
 *
 */
public interface Food {
  /**
   * This function calculates how much food is needed.
   *
   * @return the amount of food
   */
  int getFoodAmount();

  /**
   * This function fetches the name of the food that is represented.
   *
   * @return the name of food
   */
  String getFoodName();

  /**
   * This decides if they are same type of food.
   *
   * @param food to be compared food.
   * @return true if the Food are of same type.
   */
  boolean isSameType(Food food);
}
