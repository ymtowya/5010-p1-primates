package housing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import animal.Animal;
import animal.Monkey;
import food.MonkeyFood;

public class MonkeyIsolation extends AbstractMonkeyHousing {
	
	private static int uniqueIdSeries = 1;

	private static int getUniqueId() {
		return MonkeyIsolation.uniqueIdSeries++;
	}
	
	private Monkey residentMonkey;
	private final int id;
	
	public MonkeyIsolation() {
		this.id = getUniqueId();
		this.updateCapacity(null);
		residentMonkey = null;
	}

	@Override
	public boolean hasSpecies(Animal animal) {
		if (animal == null || this.isEmpty() || !(animal instanceof Monkey)) {
			return false;
		}
		Monkey thatMonkey = (Monkey) animal;
		return thatMonkey.getMonkeyType().equals(this.residentMonkey.getMonkeyType());
	}

	@Override
	public boolean hasRoom(Animal animal) {
		if (animal == null || !(animal instanceof Monkey)) {
			return false;
		}
		return !isEmpty();
	}

	@Override
	public int sendOut(Animal animal) {
		int resultId = LOOKUP_MAP_VAL_NOT_FOUND;
		Map<String, Object> resMap = lookUp(animal);
		if ((boolean) resMap.get(LOOKUP_MAP_KEY_FOUND)) {
			this.residentMonkey = null;
			resultId = getId();
		}
		return resultId;
	}

	@Override
	public int receive(Animal animal) {
		int resultId = LOOKUP_MAP_VAL_NOT_FOUND;
		if (hasRoom(animal)) {
			this.residentMonkey = (Monkey) animal;
			resultId = getId();
		}
		return resultId;
	}

	@Override
	public List<String> getDetail() {
		List<String> resList = new ArrayList<>();
		resList.add(residentMonkey.getName().concat(" - residing in Isolation - ")
					.concat(String.valueOf(getId()))
					.concat("\n"));
		return resList;
	}

	@Override
	public Map<String, Object> lookUp(Animal animal) {
		Map<String, Object> resultMap = new HashMap<>();
		boolean foundFlag = false;
		if (hasSpecies(animal) 
				&& residentMonkey.compareByAllAttrExceptId((Monkey) animal)) {
			foundFlag = true;
			resultMap.put(LOOKUP_MAP_KEY_HOUSEID, this.getId());
			resultMap.put(LOOKUP_MAP_KEY_UNITID, 1);
			resultMap.put(LOOKUP_MAP_KEY_MONKEYID, residentMonkey.getId());
		}
		resultMap.put(LOOKUP_MAP_KEY_FOUND, foundFlag);
		return resultMap;
	}

	@Override
	public List<MonkeyFood> getFoodList() {
		List<MonkeyFood> foodList = new ArrayList<MonkeyFood>();
		if (!isEmpty()) {
			foodList.add(residentMonkey.getFavouriteFood());
		}
		return foodList;
	}

	/**
	 * @return the id
	 */
	@Override
	public int getId() {
		return id;
	}

	@Override
	void updateCapacity(Animal animal) {
		/**
		 *  For isolation, the capacity is always 1 
		 *  regardless of what animal is in.
		 */
		setCapacity(1);
	}

	@Override
	public int getAmount() {
		return (residentMonkey == null) ? 0 : 1;
	}

	@Override
	public Map<String, Integer> reportAllSpecies() {
		if (isEmpty()) {
			return null;
		}
		Map<String, Integer> resMap = new HashMap<>();
		resMap.put(residentMonkey.getMonkeyType().name(), getId());
		return resMap;
	}

	@Override
	public List<Monkey> getAllMonkeys() {
		List<Monkey> resList = new ArrayList<>();
		resList.add(residentMonkey.getCopy());
		return resList;
	}

}
