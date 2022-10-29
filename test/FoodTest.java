import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import food.Food;
import food.MonkeyFood;
import food.MonkeyFoodType;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Food.
 *
 */
public class FoodTest {

  private Food monkeyFood1;
  private Food monkeyFood2;
  private Food monkeyFood3;
  private Food monkeyFood4;
  
  /**
   * Set up.
   *
   * @throws IllegalArgumentException when illegal
   */
  @Before
  public void setUp() throws IllegalArgumentException {
    monkeyFood1 = new MonkeyFood(MonkeyFoodType.FRUITS, 400);
    monkeyFood2 = new MonkeyFood(MonkeyFoodType.SEEDS, 780);
    monkeyFood3 = new MonkeyFood(MonkeyFoodType.SEEDS, 107);
    monkeyFood4 = new MonkeyFood(MonkeyFoodType.TREE_SAP, 400);
  }

  @Test
  public void testGetAttr() {
    assertEquals(monkeyFood1.getFoodAmount(), 400);
    assertEquals(monkeyFood2.getFoodName(), "SEEDS");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalInput() {
    MonkeyFood food = new MonkeyFood(MonkeyFoodType.EGGS, -1);
    food.toString();
  }
  
  @Test
  public void testEqualType() {
    assertTrue(monkeyFood2.isSameType(monkeyFood3));
    assertFalse(monkeyFood1.isSameType(monkeyFood4));
  }
  
}
