package food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MonkeyFood implements Food {
	
	private int amount;
	private MonkeyFoodType monkeyFoodType;
	
	public static List<MonkeyFood> getMergedFoodList(List<MonkeyFood> foodList) {
		Map<MonkeyFoodType, Integer> foodMap = new HashMap<MonkeyFoodType, Integer>();
		Iterator<MonkeyFood> foodIterator = foodList.iterator();
		while (foodIterator.hasNext()) {
			MonkeyFood monkeyFood = foodIterator.next();
			MonkeyFoodType foodType = monkeyFood.getMonkeyFoodType();
			Integer preAmount = foodMap.get(foodType);
			if (preAmount == null) {
				preAmount = 0;
			}
			Integer curAmount = monkeyFood.getFoodAmount();
			foodMap.put(foodType, preAmount + curAmount);
		}
		List<MonkeyFood> resList = new ArrayList<MonkeyFood>();
		for (MonkeyFoodType type : foodMap.keySet()) {
			int amount = foodMap.get(type).intValue();
			MonkeyFood monkeyFood = new MonkeyFood(type, amount);
			resList.add(monkeyFood);
		}
		return resList;
	}
	
	public MonkeyFood(MonkeyFoodType thisFoodType, int thisAmount) {
		this.monkeyFoodType = thisFoodType;
		this.amount = thisAmount;
	}
	
	private static String getNameByType(MonkeyFoodType mFoodType) {
		return mFoodType.name();
	}

	@Override
	public int getFoodAmount() {
		return this.amount;
	}

	@Override
	public String getFoodName() {
		return getNameByType(this.monkeyFoodType);
	}
	
	public MonkeyFoodType getMonkeyFoodType() {
		return this.monkeyFoodType;
	}
	
	@Override
	public boolean isSameType(Food food) {
		if (food == null || !(food instanceof MonkeyFood)) {
			return false;
		}
		MonkeyFood thatFood = (MonkeyFood) food;
		boolean result = thatFood.getMonkeyFoodType() == this.getMonkeyFoodType();
		return result;
	}
	
	public void addAmount(Food food) {
		if (isSameType(food)) {
			this.amount += food.getFoodAmount();
		} else {
			throw new IllegalArgumentException("Food Type unmatched\n");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(getFoodName());
		sBuilder.append(" : ");
		sBuilder.append(getFoodAmount());
		sBuilder.append("\n");
		return sBuilder.toString();
	}
	

}
