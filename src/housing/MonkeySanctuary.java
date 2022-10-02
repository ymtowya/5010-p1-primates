package housing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import animal.Animal;
import food.Food;

public class MonkeySanctuary implements Housing{
	
	private static int uniqueIdSeries = 1;

	private static int getUniqueId() {
		return MonkeySanctuary.uniqueIdSeries++;
	}
	private final int id;
	
	List<MonkeyIsolation> monkeyIsolations;
	List<MonkeyEnclosure> monkeyEnclosures;
	
	private int isolationCapacity, enclosureCapacity;
	
	public MonkeySanctuary(int isoCapacity, int encCapacity) {
		// TODO Auto-generated constructor stub
		this.id = getUniqueId();
		monkeyIsolations = new ArrayList<MonkeyIsolation>(isoCapacity);
		monkeyEnclosures = new ArrayList<MonkeyEnclosure>(encCapacity);
	}

	/**
	 * @param isolationCapacity the isolationCapacity to set
	 */
	private void setIsolationCapacity(int isoCapacity) {
		this.isolationCapacity = isoCapacity;
	}

	/**
	 * @param enclosureCapacity the enclosureCapacity to set
	 */
	private void setEnclosureCapacity(int encCapacity) {
		this.enclosureCapacity = encCapacity;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasSpecies(Animal animal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRoom(Animal animal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int sendOut(Animal animal) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int receive(Animal animal) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> getDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> lookUp(Animal animal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Food> getFoodList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isIsolationEmpty() {
		return getIsolationCount() == 0;
	}
	
	public int getIsolationCount() {
		if (monkeyIsolations == null) {
			return 0;
		}
		return monkeyIsolations.size();
	}
	
	public boolean isEnclosureEmpty() {
		return getEnclosureCount() == 0;
	}
	
	public int getEnclosureCount() {
		if (monkeyEnclosures == null) {
			return 0;
		}
		return monkeyEnclosures.size();
	}
	

}
