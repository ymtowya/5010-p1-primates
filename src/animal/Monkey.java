package animal;

import food.MonkeyFood;
import food.MonkeyFoodType;

public class Monkey implements Animal {

	private final String name;
	private double weight;
	private int age;
	private final AnimalSex monkeySex;
	private MonkeySize monkeySize;
	private MonkeyFoodType favouriteFoodType;
	private final int id;
	private boolean hasReceivedMed;
	private final MonkeyType monkeyType;
	private static int uniqueIdSeries = 1;

	private static int getUniqueId() {
		return Monkey.uniqueIdSeries++;
	}

	public Monkey(MonkeyType inputType, String inputName,
			double inputWeight, AnimalSex inputSex,
			int inputAge, MonkeyFoodType inputFoodType) {
		this.id = getUniqueId();
		this.name = inputName;
		this.monkeyType = inputType;
		this.weight = inputWeight;
		this.hasReceivedMed = false;
		this.monkeySex = inputSex;
		this.favouriteFoodType = inputFoodType;
		this.monkeySize = MonkeySize.getSizeByWeight(inputWeight);
	}

	@Override
	public boolean isSameSpecies(Animal animal) {
		if (animal == null || !(animal instanceof Monkey)) {
			return false;
		}
		Monkey thatMonkey = (Monkey) animal;
		return this.getMonkeyType() == thatMonkey.getMonkeyType();
	}

	public boolean compareByName(Monkey monkey) {
		if (monkey == null) {
			return false;
		}
		return monkey.getName().equals(getName());
	}

	public boolean compareByAllAttrExceptId(Monkey monkey) {
		if (monkey == null) {
			return false;
		}
		return monkey.getName().equals(getName())
				&& monkey.getMonkeyType() == getMonkeyType()
				&& monkey.getWeight() == getWeight()
				&& monkey.getAge() == getAge()
				&& monkey.getMonkeySize() == getMonkeySize()
				&& monkey.getMonkeySex() == getMonkeySex()
				&& monkey.getFavouriteFoodType() == getFavouriteFoodType();
	}

	@Override
	public int hashCode() {
		return 17 + 13 * this.getId() + 13 * 13 * this.getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Monkey - No.").append(this.getId())
			.append("\nName: ").append(this.getName())
			.append("\nSpecies: ").append(getMonkeyType().name())
			.append("\nSex: ").append(getMonkeySex().name())
			.append("\nSize: ").append(getMonkeySize().name())
			.append("\nFavourite Food: ").append(getFavouriteFoodType().name())
			.append("\n");
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

	/**
	 * @return the monkeySex
	 */
	public AnimalSex getMonkeySex() {
		return monkeySex;
	}

	/**
	 * @return the monkeySize
	 */
	public MonkeySize getMonkeySize() {
		return monkeySize;
	}

	/**
	 * @return the favouriteFoodType
	 */
	public MonkeyFoodType getFavouriteFoodType() {
		return favouriteFoodType;
	}
	
	public MonkeyFood getFavouriteFood() {
		MonkeyFood food = new MonkeyFood(getFavouriteFoodType(),
				MonkeySize.getFoodAmountBySize(getMonkeySize()));
		return food;
	}
	
	public void receiveMedicine() {
		this.hasReceivedMed = true;
	}
	
	public Monkey getCopy() {
		return new Monkey(getMonkeyType(), getName(),
				getWeight(), getMonkeySex(),
				getAge(), getFavouriteFoodType());
	}

}
