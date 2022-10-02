package housing;

import animal.AnimalSex;
import animal.Monkey;
import animal.MonkeyType;
import food.MonkeyFoodType;

public class MonkeySanctuaryDriver {

	MonkeySanctuary monkeySanctuary;
	
	public void setUp() {
		monkeySanctuary = new MonkeySanctuary(10, 20);
		Monkey dollie = new Monkey(MonkeyType.GUEREZA, "Dollie", 
				24, AnimalSex.FEMALE, 
				10, MonkeyFoodType.INSECTS));
	}
	
	public static void main(String[] args) {
		
	}

}
