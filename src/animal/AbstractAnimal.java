package animal;

public abstract class AbstractAnimal implements Animal{

	private String name;
	private double weight;
	private int age;
	
	@Override
	public abstract boolean isSameSpecies(Animal animal);
	
	public boolean compareByName(AbstractAnimal animal) {
		return animal.getName().equals(getName());
	}
	
	public boolean compareByNameWeightAge(AbstractAnimal animal) {
		return animal.getName().equals(getName())
				&& animal.getWeight() == getWeight()
				&& animal.getAge() == getAge();
	}

	// Following Getter - Setter are set to package mode so they
	// can be accessed by child classes of same package.
	
	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	double getWeight() {
		return weight;
	}

	void setWeight(double weight) {
		this.weight = weight;
	}

	int getAge() {
		return age;
	}

	void setAge(int age) {
		this.age = age;
	}

}
