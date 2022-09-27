package food;

public class MonkeyFood implements Food {
	
	private int amount;
	private MonkeyFoodType monkeyFoodType;
	
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
