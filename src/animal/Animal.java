package animal;

/**
 * The Animal interface represents the animal of certain species and can compare based on their attributes.
 * Different implementation of Animal may vary upon what attributes are kept, and their behaviors.
 *
 */
public interface Animal {
	/**
	 * This function compares the input animal with the instance, and tell if they are of same species.
	 * @param animal input animal to be compared with
	 * @return True of False for same species
	 */
	boolean isSameSpecies(Animal animal);
}
