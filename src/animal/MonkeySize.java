package animal;

public enum MonkeySize {
	SMALL,
	MEDIUM,
	LARGE,
	UNKNOWN;
	
	public static MonkeySize getSizeByWeight(double weight) {
		MonkeySize size = UNKNOWN;
		if (weight < 10) {
			size = MonkeySize.SMALL;
		} else if (weight <= 20) {
			size = MonkeySize.MEDIUM;
		} else {
			size = MonkeySize.LARGE;
		}
		return size;
	}
	
	public static double getAreaBySize(MonkeySize s) {
		double area = 10;
		switch (s) {
			case SMALL:
				area = 2;
				break;
			case MEDIUM:
				area = 5; 
				break;
			case LARGE:
				area = 10;
				break;
			case UNKNOWN:
			default:
				break;
		}
		return area;
	}
	
	public static int getFoodAmountBySize(MonkeySize s) {
		int amount = 0;
		switch (s) {
			case SMALL:
				amount = 100;
				break;
			case MEDIUM:
				amount = 300; 
				break;
			case LARGE:
				amount = 500;
				break;
			case UNKNOWN:
			default:
				break;
		}
		return amount;
	}
}
