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
	
	private int monkeyTotalAmount;

	@Override
	public boolean isEmpty() {
		return this.monkeyTotalAmount == 0;
	}
	
	/**
	 * This function checks if the housing contains animal of the same species as the input animal.
	 * @param animal to be checked if same species are contained
	 * @return True of False for weather same species animal are living in the housing
	 */
	public abstract boolean hasSpecies(Animal animal);

	public abstract int getCapacity();
	
	public int getAmount() {
		return this.monkeyTotalAmount;
	}
	
	public abstract boolean hasRoom(Animal animal);
	
	public abstract boolean sendOut(Animal animal);
	
	public abstract boolean receive(Animal animal);
	
	public abstract Map<String, Object> getDetail();
	
	public abstract Map<String, Object> lookUp(Animal animal);
	
	public abstract List<Food> getFoodList();
}
