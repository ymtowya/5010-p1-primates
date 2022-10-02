package animal;

import food.MonkeyFood;
import food.MonkeyFoodType;

/**
 * represents the Monkey.
 *
 */
public class Monkey implements Animal {

  private static int uniqueIdSeries = 1;
  private final String name;
  private final AnimalSex monkeySex;
  private final MonkeyType monkeyType;
  private final int id;
  private double weight;
  private int age;
  private MonkeySize monkeySize;
  private MonkeyFoodType favouriteFoodType;
  private boolean hasReceivedMed;
  
  /**
   * Constructor.
   *
   * @param inputType type of monkey
   * @param inputName name of monkey
   * @param inputWeight weight of monkey
   * @param inputSex sex of monkey
   * @param inputAge age of monkey
   * @param inputFoodType food for the monkey
   */
  public Monkey(MonkeyType inputType, String inputName, double inputWeight, AnimalSex inputSex,
      int inputAge, MonkeyFoodType inputFoodType) {
    if (inputWeight <= 0 || inputAge < 0) {
      throw new IllegalArgumentException("Negative Weight");
    }
    this.id = getUniqueId();
    this.name = inputName;
    this.monkeyType = inputType;
    this.weight = inputWeight;
    this.age = inputAge;
    this.hasReceivedMed = false;
    this.monkeySex = inputSex;
    this.favouriteFoodType = inputFoodType;
    this.monkeySize = MonkeySize.getSizeByWeight(inputWeight);
  }

  private static int getUniqueId() {
    return Monkey.uniqueIdSeries++;
  }

  @Override
  public boolean isSameSpecies(Animal animal) {
    if (animal == null || !(animal instanceof Monkey)) {
      return false;
    }
    Monkey thatMonkey = (Monkey) animal;
    return this.getMonkeyType() == thatMonkey.getMonkeyType();
  }

  /**
   * compare with other monkeys by name.
   *
   * @param monkey to be compared
   * @return true if same
   */
  public boolean compareByName(Monkey monkey) {
    if (monkey == null) {
      return false;
    }
    return monkey.getName().equals(getName());
  }

  /**
   * compare with all attributes other than id.
   *
   * @param monkey to be compared
   * @return true if same
   */
  public boolean compareByAllAttrExceptId(Monkey monkey) {
    if (monkey == null) {
      return false;
    }
    return monkey.getName().equals(getName()) && monkey.getMonkeyType() == getMonkeyType()
        && monkey.getWeight() == getWeight() && monkey.getAge() == getAge()
        && monkey.getMonkeySize() == getMonkeySize() && monkey.getMonkeySex() == getMonkeySex()
        && monkey.getFavouriteFoodType() == getFavouriteFoodType();
  }

  @Override
  public int hashCode() {
    return 17 + 13 * this.getId() + 13 * 13 * this.getName().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof Monkey)) {
      return false;
    }
    return compareByAllAttrExceptId((Monkey) obj);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Monkey - No.").append(this.getId()).append("\nName: ")
        .append(this.getName()).append("\nSpecies: ").append(getMonkeyType().name())
        .append("\nSex: ").append(getMonkeySex().name()).append("\nSize: ")
        .append(getMonkeySize().name()).append("\nFavourite Food: ")
        .append(getFavouriteFoodType().name()).append("\n");
    return stringBuilder.toString();
  }

  public String getName() {
    return name;
  }

  public double getWeight() {
    return weight;
  }

  public int getAge() {
    return age;
  }

  public int getId() {
    return id;
  }

  public MonkeyType getMonkeyType() {
    return monkeyType;
  }

  public boolean isHasReceivedMed() {
    return hasReceivedMed;
  }

  public AnimalSex getMonkeySex() {
    return monkeySex;
  }

  public MonkeySize getMonkeySize() {
    return monkeySize;
  }

  public MonkeyFoodType getFavouriteFoodType() {
    return favouriteFoodType;
  }

  /**
   * This function gets the favorite food.
   *
   * @return Monkey's favorite food.
   */
  public MonkeyFood getFavouriteFood() {
    MonkeyFood food = new MonkeyFood(getFavouriteFoodType(),
        MonkeySize.getFoodAmountBySize(getMonkeySize()));
    return food;
  }

  public void receiveMedicine() {
    this.hasReceivedMed = true;
  }

  public Monkey getCopy() {
    return new Monkey(getMonkeyType(), getName(), getWeight(), getMonkeySex(), getAge(),
        getFavouriteFoodType());
  }

}
