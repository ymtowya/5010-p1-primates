package housing;

import animal.Animal;

/**
 * This class.
 *
 */
public abstract class AbstractAnimalHousing implements Housing {
	
	private int animalTotalAmount;

	@Override
	public boolean isEmpty() {
		return this.animalTotalAmount == 0;
	}
	
	/**
	 * This function checks if the housing contains animals of the same species as the input animal
	 * @param animal to be checked if same species are contained
	 * @return True of False for weather same species animal are living in the housing
	 */
	public abstract boolean hasSpecies(Animal animal);

}
