import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import animal.AnimalSex;
import animal.Monkey;
import animal.MonkeyType;
import food.MonkeyFoodType;
import org.junit.Before;
import org.junit.Test;


/**
 * Test the Monkey Class.
 *
 *
 */
public class MonkeyTest {
  
  Monkey monkey;

  /**
   * Set up.
   *
   * @throws IllegalArgumentException when illegal
   */
  @Before
  public void setUp() throws IllegalArgumentException {
    monkey = new Monkey(MonkeyType.GUEREZA, "Ryuti",
        17.8, AnimalSex.MALE,
        2, MonkeyFoodType.LEAVES);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMonkey() {
    Monkey m2 = new Monkey(MonkeyType.SAKI, "Erwt",
        14, AnimalSex.MALE, -1, MonkeyFoodType.EGGS);
    m2.toString();
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSize() {
    Monkey m2 = new Monkey(MonkeyType.SAKI, "Erwt",
        -7, AnimalSex.MALE, 11, MonkeyFoodType.EGGS);
    m2.toString();
  }
  
  @Test
  public void testReceiveMed() {
    Monkey m2 = new Monkey(MonkeyType.SAKI, "Erwt",
        14, AnimalSex.MALE, 12, MonkeyFoodType.EGGS);
    assertEquals(m2.getAge(), 12);
    assertFalse(m2.isHasReceivedMed());
    m2.receiveMedicine();
    assertTrue(m2.isHasReceivedMed());
  }

}
