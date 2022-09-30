package animal;

public class Monkey implements Animal{

	private final String name;
	private double weight;
	private int age;
	private final int id;
	private boolean hasReceivedMed;
  private static int uniqueIdSeries = 1;
  
  private static int getUniqueId() {
    return Monkey.uniqueIdSeries++;
  }
  
  public Monkey(String monkeyName) {
    this.name = monkeyName;
    this.hasReceivedMed = false;
    this.id = getUniqueId();
  }
	
	@Override
	public boolean isSameSpecies(Animal animal) {
	  return true;
	}
	
	public boolean compareByName(Monkey monkey) {
		return monkey.getName().equals(getName());
	}
	
	public boolean compareByNameWeightAge(Monkey animal) {
		return animal.getName().equals(getName())
				&& animal.getWeight() == getWeight()
				&& animal.getAge() == getAge();
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

  public boolean isHasReceivedMed() {
    return hasReceivedMed;
  }
	
	

}
