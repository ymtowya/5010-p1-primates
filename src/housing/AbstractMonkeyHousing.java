package housing;

import java.util.List;
import java.util.Map;

import animal.Animal;
import animal.Monkey;
import food.MonkeyFood;

/**
 * This class.
 *
 */
public abstract class AbstractMonkeyHousing implements Housing {
	
	public static final String LOOKUP_MAP_KEY_FOUND = "found";
	public static final String LOOKUP_MAP_KEY_HOUSEID = "housingId";
	public static final String LOOKUP_MAP_KEY_UNITID = "unitId";
	public static final String LOOKUP_MAP_KEY_MONKEYID = "monkeyId";
	
	public static final String LOOKUP_MAP_KEY_IDS = "housingIds";
	public static final String LOOKUP_MAP_KEY_AMOUNTS = "monkeyAmounts";
	public static final String LOOKUP_MAP_KEY_HOUSES = "monkeyHouses";
	public static final String LOOKUP_MAP_KEY_DETAILS = "details";
	public static final String LOOKUP_MAP_KEY_HTYPE = "htypes";
	
	public static final int LOOKUP_MAP_VAL_NOT_FOUND = -1;
	
	private int monkeyCapacity;
	
	abstract int getId();

	@Override
	public boolean isEmpty() {
		return this.getAmount() == 0;
	}
	
	@Override
	public boolean isFull() {
		return this.getAmount() == this.getCapacity();
	}
	
	@Override
	public abstract boolean hasSpecies(Animal animal);

	public int getCapacity() {
		return this.monkeyCapacity;
	}
	
	public abstract int getAmount();
	
	void setCapacity(int newCapacity) {
		this.monkeyCapacity = newCapacity;
	}
	
	abstract void updateCapacity(Animal animal);
	
	@Override
	public abstract boolean hasRoom(Animal animal);
	
	@Override
	public abstract int sendOut(Animal animal);
	
	@Override
	public abstract int receive(Animal animal);
	
	@Override
	public abstract List<String> getDetail();
	
	@Override
	public abstract Map<String, Object> lookUp(Animal animal);
	
	public abstract Map<String, Integer> reportAllSpecies();

	public abstract List<MonkeyFood> getFoodList();
	
	public abstract List<Monkey> getAllMonkeys();

}
