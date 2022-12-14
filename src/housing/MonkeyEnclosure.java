package housing;

import animal.Animal;
import animal.Monkey;
import animal.MonkeySize;
import animal.SortMonkeyByName;
import food.MonkeyFood;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the enclosure of a certain species monkey.
 *
 */
public class MonkeyEnclosure extends AbstractMonkeyHousing {

  private static int uniqueIdSeries = 1;

  private final int id;

  private List<Monkey> residentMonkeys;
  private double area;

  /**
   * This is the constructor taking anonymously.
   */
  public MonkeyEnclosure() {
    this.area = 100;
    this.id = getUniqueId();
    residentMonkeys = new ArrayList<Monkey>();
    setCapacity(1);
  }

  /**
   * This is the constructor, instantiating the class with areas.
   *
   * @param initArea the area
   */
  public MonkeyEnclosure(double initArea) {
    this.area = initArea;
    this.id = getUniqueId();
    residentMonkeys = new ArrayList<Monkey>();
    setCapacity(1);
  }
  
  private static int getUniqueId() {
    return MonkeyEnclosure.uniqueIdSeries++;
  }

  @Override
  public boolean hasSpecies(Animal animal) {
    if (animal == null || !(animal instanceof Monkey) || isEmpty()) {
      return false;
    }
    Monkey firstMonkey = residentMonkeys.get(0);
    return firstMonkey.isSameSpecies(animal);
  }

  @Override
  void updateCapacity(Animal animal) {
    Monkey monkey = null;
    if (isEmpty()) {
      if (animal != null) {
        monkey = (Monkey) animal;
      } else {
        return;
      }
    } else if (animal == null || hasSpecies(animal)) {
      monkey = residentMonkeys.get(0);
    } else {
      return;
    }
    double unitArea = MonkeySize.getAreaBySize(monkey.getMonkeySize());
    final int newCapacity = (int) Math.floor(area / unitArea);
    setCapacity(newCapacity);
  }

  @Override
  public boolean hasRoom(Animal animal) {
    if (isFull() || animal == null || !(animal instanceof Monkey)) {
      return false;
    } else if (isEmpty()) {
      return true;
    }
    return residentMonkeys.get(0).isSameSpecies(animal);
  }

  @Override
  public int sendOut(Animal animal) {
    int resultId = LOOKUP_MAP_VAL_NOT_FOUND;
    Map<String, Object> resMap = lookUp(animal);
    if ((boolean) resMap.get(LOOKUP_MAP_KEY_FOUND)) {
      int unitId = (int) resMap.get(LOOKUP_MAP_KEY_UNITID);
      residentMonkeys.remove(unitId);
      resultId = getId();
      updateCapacity(animal);
    }
    return resultId;
  }

  @Override
  public int receive(Animal animal) {
    int resultId = LOOKUP_MAP_VAL_NOT_FOUND;
    if (hasRoom(animal)) {
      Monkey thatMonkey = (Monkey) animal;
      residentMonkeys.add(thatMonkey);
      resultId = getId();
      updateCapacity(animal);
    }
    return resultId;
  }

  @Override
  public List<String> getDetail() {
    List<String> resList = new ArrayList<>();
    for (Monkey monkey : residentMonkeys) {
      resList.add(monkey.getName().concat(" - residing in Enclosure - ")
          .concat(String.valueOf(getId())).concat("\n"));
    }
    return resList;
  }

  @Override
  public Map<String, Object> lookUp(Animal animal) {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    boolean foundFlag = false;
    if (!isEmpty() && hasSpecies(animal)) {
      int i = 0;
      Monkey thatMonkey = (Monkey) animal;
      for (; i < residentMonkeys.size(); ++i) {
        if (residentMonkeys.get(i).compareByAllAttrExceptId(thatMonkey)) {
          foundFlag = true;
          resultMap.put(LOOKUP_MAP_KEY_HOUSEID, getId());
          resultMap.put(LOOKUP_MAP_KEY_UNITID, i);
          resultMap.put(LOOKUP_MAP_KEY_MONKEYID, residentMonkeys.get(i).getId());
          break;
        }
      }
    }
    resultMap.put(LOOKUP_MAP_KEY_FOUND, foundFlag);
    return resultMap;
  }

  @Override
  public List<MonkeyFood> getFoodList() {
    List<MonkeyFood> foodList = new ArrayList<MonkeyFood>();
    for (Monkey monkey : residentMonkeys) {
      foodList.add(monkey.getFavouriteFood());
    }
    List<MonkeyFood> resList = MonkeyFood.getMergedFoodList(foodList);
    return resList;
  }

  @Override
  public int getAmount() {
    if (residentMonkeys == null) {
      return 0;
    }
    return residentMonkeys.size();
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public Map<String, Integer> reportAllSpecies() {
    if (isEmpty()) {
      return null;
    }
    Map<String, Integer> resMap = new HashMap<>();
    resMap.put(residentMonkeys.get(0).getMonkeyType().name(), getId());
    return resMap;
  }

  @Override
  public List<Monkey> getAllMonkeys() {
    List<Monkey> resList = new ArrayList<>();
    for (Monkey monkey : residentMonkeys) {
      resList.add(monkey.getCopy());
    }
    Collections.sort(resList, new SortMonkeyByName());
    return resList;
  }

}
