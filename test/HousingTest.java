import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import animal.AnimalSex;
import animal.Monkey;
import animal.MonkeyType;
import food.MonkeyFoodType;
import housing.AbstractMonkeyHousing;
import housing.MonkeyEnclosure;
import housing.MonkeyIsolation;
import housing.MonkeySanctuary;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Housing.
 *
 */
public class HousingTest {

  Monkey m1;
  Monkey m2;
  MonkeyEnclosure enc1;
  MonkeyIsolation iso1;
  MonkeySanctuary san1;

  /**
   * Set up.
   *
   * @throws IllegalArgumentException when illegal
   */
  @Before
  public void setUp() throws IllegalArgumentException {
    m1 = new Monkey(MonkeyType.MANGABEY, "Iwtye", 8.2, AnimalSex.FEMALE, 4,
        MonkeyFoodType.TREE_SAP);
    m2 = new Monkey(MonkeyType.SAKI, "Quicy", 24.7, AnimalSex.MALE, 9, MonkeyFoodType.LEAVES);
    enc1 = new MonkeyEnclosure(2);
    iso1 = new MonkeyIsolation();
    int[] encArea = new int[] { 74, 40 };
    san1 = new MonkeySanctuary(3, 2, encArea);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCapacity() {
    int[] encArea = new int[] { 74, 40 };
    // ISO
    MonkeySanctuary s2 = new MonkeySanctuary(0, 2, encArea);
    s2.toString();
    // ENC
    MonkeySanctuary s3 = new MonkeySanctuary(3, 0, new int[] {});
    s3.toString();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeCapacity() {
    int[] encArea = new int[] { 74, 40 };
    // ISO
    MonkeySanctuary s2 = new MonkeySanctuary(-1, 2, encArea);
    s2.toString();
    // ENC
    MonkeySanctuary s3 = new MonkeySanctuary(3, -1, new int[] {});
    s3.toString();
  }

  @Test
  public void testAddToIso() {
    assertEquals(iso1.getAmount(), 0);
    iso1.receive(m1);
    assertEquals(iso1.getAmount(), 1);
  }

  @Test
  public void testAddToFullIso() {
    assertEquals(iso1.getAmount(), 0);
    iso1.receive(m1);
    assertTrue(iso1.receive(m2) < 0);
  }

  @Test
  public void testAddToEnc() {
    assertEquals(enc1.getAmount(), 0);
    enc1.receive(m1);
    assertEquals(enc1.getAmount(), 1);
  }

  @Test
  public void testAddToWrongEnc() {
    // wrong species
    enc1.receive(m1);
    assertEquals(enc1.getAmount(), 1);
    assertTrue(enc1.receive(m2) < 0);
  }

  @Test
  public void testAddToFullEnc() {
    enc1.receive(m1);
    enc1.receive(m1.getCopy());
    assertEquals(enc1.getAmount(), enc1.getCapacity());
    assertTrue(enc1.receive(m1.getCopy()) < 0);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testMoveToEnc() {
    assertFalse(m1.isHasReceivedMed());
    san1.receive(m1);
    san1.receive(m2);
    m1.receiveMedicine();
    san1.moveHealthyMonkey(m1);
    Object listObject = san1.lookUp(m1).get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_DETAILS);
    List<Map<String, Object>> list = (List<Map<String, Object>>) listObject;
    System.out.println(list);
    Map<String, Object> map = (Map<String, Object>) list.get(0);
    assertEquals(map.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_HTYPE).toString(), "ENC");
  }

  @Test
  public void testEncExpand() {
    assertEquals(san1.getEnclosureCapacity(), 2);
    san1.expandEnclosure(5, new int[] { 45, 17, 97 });
    assertEquals(san1.getEnclosureCapacity(), 5);
  }

  @Test
  public void testSpeciesReport() {
    san1.receive(m1);
    san1.reportAllSpecies();
  }

  @Test
  public void testFindSpecies() {
    san1.receive(m1);
    san1.receive(m2);
    Map<String, Object> map = san1.hasSpeciesGeneral(m1, MonkeySanctuary.HousingType.ISO);
    assertTrue((boolean) map.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND));
    m1.receiveMedicine();
    san1.moveHealthyMonkey(m1);
    map = san1.hasSpeciesGeneral(m1, MonkeySanctuary.HousingType.ENC);
    assertTrue((boolean) map.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND));
  }

  @Test
  public void testFindNoExistSpecies() {
    san1.receive(m1);
    Map<String, Object> map = san1.hasSpeciesGeneral(m2, MonkeySanctuary.HousingType.ISO);
    assertFalse((boolean) map.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND));
    m1.receiveMedicine();
    san1.moveHealthyMonkey(m1);
    map = san1.hasSpeciesGeneral(m1, MonkeySanctuary.HousingType.ISO);
    assertFalse((boolean) map.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testencSign() {
    san1.receive(m1);
    san1.receive(m2);
    m1.receiveMedicine();
    m2.receiveMedicine();
    san1.moveHealthyMonkey(m1);
    san1.moveHealthyMonkey(m2);
    Object listObject = san1.lookUp(m1).get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_DETAILS);
    List<Map<String, Object>> list = (List<Map<String, Object>>) listObject;
    Map<String, Object> map1 = (Map<String, Object>) list.get(0);
    int id = (int) map1.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_HOUSEID);
    System.out.println(san1.getEnclosureString(id));
    /* Next Monkey */
    listObject = san1.lookUp(m2).get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_DETAILS);
    list = (List<Map<String, Object>>) listObject;
    map1 = (Map<String, Object>) list.get(0);
    id = (int) map1.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_HOUSEID);
    System.out.println(san1.getEnclosureString(id));
  }

  @Test
  public void testMonkeyList() {
    san1.receive(m1);
    san1.receive(m2);
    m1.receiveMedicine();
    m2.receiveMedicine();
    san1.moveHealthyMonkey(m1);
    san1.moveHealthyMonkey(m2);
    san1.receive(
        new Monkey(MonkeyType.TAMARIN, "Zetiuya", 1, AnimalSex.FEMALE, 1, MonkeyFoodType.NUTS));
    System.out.println(san1.getDetail().toString());
  }

  @Test
  public void testFoodList() {
    san1.receive(m1);
    san1.receive(m2);
    m1.receiveMedicine();
    m2.receiveMedicine();
    san1.moveHealthyMonkey(m1);
    san1.moveHealthyMonkey(m2);
    san1.receive(
        new Monkey(MonkeyType.TAMARIN, "Zetiuya", 17, AnimalSex.FEMALE, 5, MonkeyFoodType.NUTS));
    System.out.println(san1.getFoodList());
  }

}
