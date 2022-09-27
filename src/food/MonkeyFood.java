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
