package animal;

import food.MonkeyFoodType;

public class Monkey implements Animal, Comparable<Monkey> {

	private final String name;
	private double weight;
	private int age;
	private AnimalSex monkeySex;
	private MonkeySize monkeySize;
	private MonkeyFoodType favouriteFoodType;
	private final int id;
	private boolean hasReceivedMed;
	private final MonkeyType monkeyType;
	private static int uniqueIdSeries = 1;

	private static int getUniqueId() {
		return Monkey.uniqueIdSeries++;
	}

	public Monkey(MonkeyType monkeyType, String monkeyName) {
		this.name = monkeyName;
		this.monkeyType = monkeyType;
		this.hasReceivedMed = false;
		this.id = getUniqueId();
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
		return monkey.getName().equals(getName());
	}

	public boolean compareByNameTypeWeightAge(Monkey monkey) {
		return monkey.getName().equals(getName())
				&& monkey.getMonkeyType() == getMonkeyType()
				&& monkey.getWeight() == getWeight()
				&& monkey.getAge() == getAge();
	}
	
	@Override
	public int compareTo(Monkey monkey) {
		return this.getName().compareTo(monkey.getName());
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
			.append("\nMedical Reception: ").append(isHasReceivedMed() ? "Y" : "N")
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
	
	

}
