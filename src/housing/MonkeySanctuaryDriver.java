package housing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import animal.AnimalSex;
import animal.Monkey;
import animal.MonkeyType;
import food.MonkeyFoodType;

public class MonkeySanctuaryDriver {

	static MonkeySanctuary monkeySanctuary;
	static FileWriter writer;

	private static void fileSetUp() throws IOException {
		File myObjFile = new File("./res/runningResult.txt");
		writer = new FileWriter(myObjFile);
	}

	private static void setUp() throws IOException {
		try {
			fileSetUp();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.write("Initializing the Sanctuary:\n");
		monkeySanctuary = new MonkeySanctuary(10, 20);
		writer.write("Initializing the Monkey Dollie:\n");
		Monkey dollie = new Monkey(MonkeyType.GUEREZA, "Dollie", 24, AnimalSex.FEMALE, 10, MonkeyFoodType.INSECTS);
		writer.write(dollie.toString());
		writer.write("Move the Dollie to the isolation:\n");
		monkeySanctuary.receive(dollie);
		writer.write("Now the Dollie is in the sanctuary.\n");
		writer.write("Now look up for Dollie: \n");
		writer.write(monkeySanctuary.lookUp(dollie).toString().concat("\n"));
		writer.write("Now give Dollie the medical assessment: \n");
		dollie.receiveMedicine();
		writer.write("Now Dollie's condition is : ".concat(String.valueOf(dollie.isHasReceivedMed())));
		writer.write("\nAnd we move it to the Enclosure\n");
		int i = monkeySanctuary.moveHealthyMonkey(dollie);
		writer.write(monkeySanctuary.getEnclosureString(i));
	}

	public static void main(String[] args) {
		try {
			setUp();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
