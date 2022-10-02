package housing;

import java.util.List;
import java.util.Map;

import animal.Animal;
import food.Food;

/**
 * This class.
 *
 */
public abstract class AbstractMonkeyHousing implements Housing {
	
	public static final String LOOKUP_MAP_KEY_FOUND = "found";
	public static final String LOOKUP_MAP_KEY_HOUSEID = "housingId";
	public static final String LOOKUP_MAP_KEY_UNITID = "unitId";
	public static final String LOOKUP_MAP_KEY_MONKEYID = "monkeyId";
	public static final int LOOKUP_MAP_VAL_NOT_FOUND = -1;
	
	private int monkeyCapacity;

	@Override
	public boolean isEmpty() {
		return this.getAmount() == 0;
	}
	
	@Override
	public boolean isFull() {
		return this.getAmount() == this.getCapacity();
	}
	
	/**
	 * This function checks if the housing contains animal of the same species as the input animal.
	 * @param animal to be checked if same species are contained
	 * @return True of False for weather same species animal are living in the housing
	 */
	public abstract boolean hasSpecies(Animal animal);

	public int getCapacity() {
		return this.monkeyCapacity;
	}
	
	public abstract int getAmount();
	
	void setCapacity(int newCapacity) {
		this.monkeyCapacity = newCapacity;
	}
	
	abstract void updateCapacity(Animal animal);
	
	public abstract boolean hasRoom(Animal animal);
	
	public abstract int sendOut(Animal animal);
	
	public abstract int receive(Animal animal);
	
	public abstract Map<String, Object> getDetail();
	
	public abstract Map<String, Object> lookUp(Animal animal);
	
	public abstract List<Food> getFoodList();
}
