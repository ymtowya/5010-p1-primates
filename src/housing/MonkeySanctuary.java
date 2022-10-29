package housing;

import animal.Animal;
import animal.Monkey;
import food.MonkeyFood;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a sanctuary for the monkeys.
 *
 *
 */
public class MonkeySanctuary implements Housing {

  /**
   * Enumerate for housing type.
   *
   *
   */
  public static enum HousingType {
    ISO, ENC;
  }

  public static final String SPECIESORDER_STRING = "SPECIESORDER";
  public static final String SPECIESDETAIL_STRING = "SPECIESDETAILS";

  private static int uniqueIdSeries = 1;
  
  private final int id;

  private List<AbstractMonkeyHousing> monkeyIsolations;
  private List<AbstractMonkeyHousing> monkeyEnclosures;

  private int isolationCapacity;
  private int enclosureCapacity;

  /**
   * Initialize with the capacity.
   *
   * @param isoCapacity isolation capacity
   * @param encCapacity enclosure capacity
   * @param encAreas array of enclosure areas
   * @throws IllegalArgumentException with length not match or negative capacity
   */
  public MonkeySanctuary(int isoCapacity, int encCapacity, int[] encAreas) {
    if (isoCapacity <= 0 || encCapacity <= 0) {
      throw new IllegalArgumentException("negative capacity");
    }
    if (encAreas.length != encCapacity) {
      throw new IllegalArgumentException("encAreas length does not match encCapacity");
    }
    this.id = getUniqueId();
    monkeyIsolations = new ArrayList<AbstractMonkeyHousing>(isoCapacity);
    for (int i = 0; i < isoCapacity; ++i) {
      monkeyIsolations.add(new MonkeyIsolation());
    }
    monkeyEnclosures = new ArrayList<AbstractMonkeyHousing>(encCapacity);
    for (int i = 0; i < encCapacity; ++i) {
      monkeyEnclosures.add(new MonkeyEnclosure(encAreas[i]));
    }
    this.isolationCapacity = isoCapacity;
    this.enclosureCapacity = encCapacity;
  }
  
  private static int getUniqueId() {
    return MonkeySanctuary.uniqueIdSeries++;
  }

  private MonkeyEnclosure getEnclosure(int id) {
    for (AbstractMonkeyHousing housing : monkeyEnclosures) {
      if (housing.getId() == id) {
        return (MonkeyEnclosure) housing;
      }
    }
    return null;
  }

  /**
   * Print the monkey information in the certain enclosure.
   *
   * @param id the id of the enclosure.
   * @return the String information.
   */
  public String getEnclosureString(int id) {
    MonkeyEnclosure monkeyEnclosure = getEnclosure(id);
    if (monkeyEnclosure == null) {
      return "";
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (Monkey monkey : monkeyEnclosure.getAllMonkeys()) {
      stringBuilder.append(monkey.toString());
    }
    return stringBuilder.toString();
  }

  public int getId() {
    return id;
  }

  public int getIsolationCapacity() {
    return isolationCapacity;
  }

  public int getEnclosureCapacity() {
    return enclosureCapacity;
  }

  private void setIsolationCapacity(int isoCapacity) {
    this.isolationCapacity = isoCapacity;
  }

  private void setEnclosureCapacity(int encCapacity) {
    this.enclosureCapacity = encCapacity;
  }

  @Override
  public boolean isEmpty() {
    return isIsolationEmpty() && isEnclosureEmpty();
  }

  @Override
  public boolean isFull() {
    return isIsolationFull() && isEnclosureFull();
  }

  private List<AbstractMonkeyHousing> getHousingList(HousingType housingType) {
    if (housingType == HousingType.ISO) {
      return this.monkeyIsolations;
    }
    return this.monkeyEnclosures;
  }

  /**
   * Report the certain Species.
   * @param animal of species to be found
   * @param housingType of where to find
   * @return map of detailed info
   */
  public Map<String, Object> hasSpeciesGeneral(Animal animal, HousingType housingType) {
    Map<String, Object> resMap = new HashMap<>();
    boolean foundFlag = false;
    List<Integer> idList = new ArrayList<Integer>();
    List<Integer> amountList = new ArrayList<Integer>();
    List<HousingType> housingList = new ArrayList<HousingType>();
    List<AbstractMonkeyHousing> housings = getHousingList(housingType);

    for (AbstractMonkeyHousing housingSingle : housings) {
      if (housingSingle.hasSpecies(animal)) {
        foundFlag = true;
        idList.add(housingSingle.getId());
        amountList.add(housingSingle.getAmount());
        housingList.add(housingType);
      }
    }

    resMap.put(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND, foundFlag);
    resMap.put(AbstractMonkeyHousing.LOOKUP_MAP_KEY_IDS, idList);
    resMap.put(AbstractMonkeyHousing.LOOKUP_MAP_KEY_AMOUNTS, amountList);
    resMap.put(AbstractMonkeyHousing.LOOKUP_MAP_KEY_HOUSES, housingList);
    return resMap;
  }

  @Override
  public boolean hasSpecies(Animal animal) {
    Map<String, Object> resMap1 = hasSpeciesGeneral(animal, HousingType.ISO);
    Map<String, Object> resMap2 = hasSpeciesGeneral(animal, HousingType.ENC);
    return (boolean) resMap1.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND)
        && (boolean) resMap2.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND);
  }

  private Map<String, Object> hasRoomGeneral(Animal animal, HousingType housingType) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    List<AbstractMonkeyHousing> housings = getHousingList(housingType);
    boolean hasFlag = false;
    int id = AbstractMonkeyHousing.LOOKUP_MAP_VAL_NOT_FOUND;
    for (AbstractMonkeyHousing h : housings) {
      if (h.hasRoom(animal)) {
        hasFlag = true;
        id = h.getId();
        break;
      }
    }
    resMap.put(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND, hasFlag);
    resMap.put(AbstractMonkeyHousing.LOOKUP_MAP_KEY_UNITID, id);
    return resMap;
  }

  @Override
  public boolean hasRoom(Animal animal) {
    Map<String, Object> resMap1 = hasRoomGeneral(animal, HousingType.ISO);
    Map<String, Object> resMap2 = hasRoomGeneral(animal, HousingType.ENC);
    return (boolean) resMap1.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND)
        && (boolean) resMap2.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND);
  }

  private int sendOutGeneral(Animal animal, HousingType housingType) {
    int res = AbstractMonkeyHousing.LOOKUP_MAP_VAL_NOT_FOUND;
    List<AbstractMonkeyHousing> housings = getHousingList(housingType);
    for (AbstractMonkeyHousing h : housings) {
      int tmpRes = h.sendOut(animal);
      if (tmpRes >= 0) {
        res = h.getId();
        break;
      }
    }
    return res;
  }

  @Override
  public int sendOut(Animal animal) {
    int tmpRes = AbstractMonkeyHousing.LOOKUP_MAP_VAL_NOT_FOUND;
    tmpRes = sendOutGeneral(animal, HousingType.ENC);
    if (tmpRes < 0) {
      tmpRes = sendOutGeneral(animal, HousingType.ISO);
    }
    return tmpRes;
  }

  private int receiveGeneral(Animal animal, HousingType housingType) {
    int res = AbstractMonkeyHousing.LOOKUP_MAP_VAL_NOT_FOUND;
    List<AbstractMonkeyHousing> housings = getHousingList(housingType);
    for (AbstractMonkeyHousing h : housings) {
      int tmpRes = h.receive(animal);
      if (tmpRes != AbstractMonkeyHousing.LOOKUP_MAP_VAL_NOT_FOUND) {
        res = h.getId();
        break;
      }
    }
    return res;
  }

  @Override
  public int receive(Animal animal) {
    if (animal == null || !(animal instanceof Monkey)) {
      return AbstractMonkeyHousing.LOOKUP_MAP_VAL_NOT_FOUND;
    }
    int tmpRes = AbstractMonkeyHousing.LOOKUP_MAP_VAL_NOT_FOUND;
    Monkey thatMonkey = (Monkey) animal;
    if (thatMonkey.isHasReceivedMed()) {
      tmpRes = receiveGeneral(animal, HousingType.ENC);
      if (tmpRes < 0) {
        tmpRes = receiveGeneral(animal, HousingType.ISO);
      }
    } else {
      tmpRes = receiveGeneral(animal, HousingType.ISO);
    }
    return tmpRes;
  }

  /**
   * Move the healthy monkey from isolation to enclosure.
   *
   * @param animal to be removed animal
   * @return non-negative if success
   */
  public int moveHealthyMonkey(Animal animal) {
    int res = AbstractMonkeyHousing.LOOKUP_MAP_VAL_NOT_FOUND;
    if (animal == null || !(animal instanceof Monkey)) {
      return res;
    }
    Monkey thatMonkey = (Monkey) animal;
    if (thatMonkey.isHasReceivedMed()) {
      Map<String, Object> resMap1 = lookUpGeneral(thatMonkey, HousingType.ISO);
      Map<String, Object> resMap2 = hasRoomGeneral(thatMonkey, HousingType.ENC);
      if ((boolean) resMap1.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND)
          && (boolean) resMap2.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND)) {
        sendOutGeneral(thatMonkey, HousingType.ISO);
        res = receiveGeneral(thatMonkey, HousingType.ENC);
      }
    }
    return res;
  }

  private List<String> getDetailHelper() {
    List<String> resList = new ArrayList<>();
    for (AbstractMonkeyHousing housing : monkeyIsolations) {
      if (!housing.isEmpty()) {
        resList.addAll(housing.getDetail());
      }
    }
    for (AbstractMonkeyHousing housing : monkeyEnclosures) {
      if (!housing.isEmpty()) {
        resList.addAll(housing.getDetail());
      }
    }
    Collections.sort(resList);
    return resList;
  }

  @Override
  public List<String> getDetail() {
    return getDetailHelper();
  }

  private Map<String, Object> lookUpGeneral(Animal animal, HousingType housingType) {
    List<AbstractMonkeyHousing> housing = getHousingList(housingType);
    List<Map<String, Object>> lookUpResList = new ArrayList<>();
    boolean found = false;
    for (AbstractMonkeyHousing h : housing) {
      Map<String, Object> map = h.lookUp(animal);
      if ((boolean) map.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND)) {
        found = true;
        map.put(AbstractMonkeyHousing.LOOKUP_MAP_KEY_HTYPE, housingType);
        lookUpResList.add(map);
      }
    }
    Map<String, Object> resMap = new HashMap<>();
    resMap.put(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND, found);
    resMap.put(AbstractMonkeyHousing.LOOKUP_MAP_KEY_DETAILS, lookUpResList);
    return resMap;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Map<String, Object> lookUp(Animal animal) {
    Map<String, Object> resMap = new HashMap<>();
    Map<String, Object> resMap1 = lookUpGeneral(animal, HousingType.ISO);
    Map<String, Object> resMap2 = lookUpGeneral(animal, HousingType.ENC);
    resMap.put(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND,
        (boolean) resMap1.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND)
            && (boolean) resMap2.get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_FOUND));
    List<Map<String, Object>> tmpList1 = (List<Map<String, Object>>) resMap1
        .get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_DETAILS);
    List<Map<String, Object>> tmpList2 = (List<Map<String, Object>>) resMap2
        .get(AbstractMonkeyHousing.LOOKUP_MAP_KEY_DETAILS);
    tmpList1.addAll(tmpList2);
    resMap.put(AbstractMonkeyHousing.LOOKUP_MAP_KEY_DETAILS, tmpList1);
    return resMap;
  }

  private List<MonkeyFood> getFavouriteFoodGeneral(HousingType housingType) {
    List<AbstractMonkeyHousing> housings = getHousingList(housingType);
    List<MonkeyFood> foodList = new ArrayList<>();
    for (AbstractMonkeyHousing h : housings) {
      foodList.addAll(h.getFoodList());
    }
    List<MonkeyFood> resList = MonkeyFood.getMergedFoodList(foodList);
    return resList;
  }

  /**
   * get the food list of all animals.
   *
   * @return list of food
   */
  public List<MonkeyFood> getFoodList() {
    List<MonkeyFood> foodList1 = getFavouriteFoodGeneral(HousingType.ISO);
    foodList1.addAll(getFavouriteFoodGeneral(HousingType.ENC));
    List<MonkeyFood> resList = MonkeyFood.getMergedFoodList(foodList1);
    Collections.sort(resList, new Comparator<MonkeyFood>() {
      @Override
      public int compare(MonkeyFood o1, MonkeyFood o2) {
        return o1.getFoodName().compareTo(o2.getFoodName());
      }
    });
    return resList;
  }

  public boolean isIsolationEmpty() {
    return getIsolationAmount() == 0;
  }

  public boolean isIsolationFull() {
    return getIsolationAmount() == getIsolationCapacity();
  }

  /**
   * get the amount of isolation for monkey.
   *
   * @return integer value for isolation amount
   */
  public int getIsolationAmount() {
    if (monkeyIsolations == null) {
      return 0;
    }
    int amount = 0;
    for (AbstractMonkeyHousing housing : monkeyIsolations) {
      if (!housing.isEmpty()) {
        ++amount;
      }
    }
    return amount;
  }

  public boolean isEnclosureEmpty() {
    return getEnclosureAmount() == 0;
  }

  public boolean isEnclosureFull() {
    return getEnclosureAmount() == getEnclosureCapacity();
  }
  
  /**
   * get the amount of enclosure for monkey.
   *
   * @return integer value for enclosure amount
   */
  public int getEnclosureAmount() {
    if (monkeyEnclosures == null) {
      return 0;
    }
    int amount = 0;
    for (AbstractMonkeyHousing housing : monkeyEnclosures) {
      if (!housing.isEmpty()) {
        ++amount;
      }
    }
    return amount;
  }

  /**
   * expand the isolation to new capacity.
   *
   * @param newCapacity new capacity
   * @return true if success
   */
  public boolean expandIsolation(int newCapacity) {
    if (newCapacity > this.getIsolationCapacity()) {
      for (int i = this.getIsolationCapacity(); i < newCapacity; ++i) {
        monkeyIsolations.add(new MonkeyIsolation());
      }
      setIsolationCapacity(newCapacity);
      return true;
    }
    return false;
  }

  /**
   * expand the capacity of enclosure.
   *
   * @param newCapacity new capacity
   * @param encAreas areas for new enclosure
   * @return true if success
   * @throws IllegalArgument if length not match
   */
  public boolean expandEnclosure(int newCapacity, int[] encAreas) {
    if (newCapacity > this.getEnclosureCapacity()) {
      if (encAreas.length != newCapacity - this.getEnclosureCapacity()) {
        throw new IllegalArgumentException("Unmatched length");
      }
      final int preCapacity = this.getEnclosureCapacity();
      for (int i = preCapacity; i < newCapacity; ++i) {
        monkeyEnclosures.add(new MonkeyEnclosure(encAreas[i - preCapacity]));
      }
      setEnclosureCapacity(newCapacity);
      return true;
    }
    return false;
  }

  private Map<String, String> reportAllSpeciesGeneral(HousingType housingType) {
    List<AbstractMonkeyHousing> housings = getHousingList(housingType);
    Map<String, String> resMap = new HashMap<>();
    for (AbstractMonkeyHousing housing : housings) {
      Map<String, Integer> tmpCount = housing.reportAllSpecies();
      if (tmpCount == null) {
        continue;
      }
      for (String keyString : tmpCount.keySet()) {
        if (resMap.containsKey(keyString)) {
          String tmpString = resMap.get(keyString) + " " + tmpCount.get(keyString).toString();
          resMap.put(keyString, tmpString);
        } else {
          resMap.put(keyString, tmpCount.get(keyString).toString());
        }
      }
    }
    return resMap;
  }

  /**
   * Find the report for all species.
   *
   * @return the map containing each species.
   */
  public Map<String, Object> reportAllSpecies() {
    Map<String, String> resMap1 = reportAllSpeciesGeneral(HousingType.ISO);
    Map<String, String> resMap2 = reportAllSpeciesGeneral(HousingType.ENC);

    Set<String> typeStringSet = resMap1.keySet();
    typeStringSet.addAll(resMap2.keySet());
    List<String> typeStringList = new ArrayList<String>();
    typeStringList.addAll(typeStringSet);

    Map<String, String> sumMap = new HashMap<String, String>();
    for (String typeString : typeStringSet) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(typeString).append(" : \n");
      if (resMap1.containsKey(typeString)) {
        stringBuilder.append("Isolation : ").append(resMap1.get(typeString)).append("\n");
      }
      if (resMap2.containsKey(typeString)) {
        stringBuilder.append("Enclosure : ").append(resMap2.get(typeString)).append("\n");
      }
      sumMap.put(typeString, stringBuilder.toString());
    }
    Map<String, Object> resMap = new HashMap<>();
    resMap.put(SPECIESORDER_STRING, typeStringList);
    resMap.put(SPECIESDETAIL_STRING, sumMap);
    return null;
  }

}
