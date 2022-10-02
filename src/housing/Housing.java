package housing;

import java.util.List;
import java.util.Map;

import animal.Animal;
import food.Food;

/**
 * This interface provides general functions for housing-related classes.
 *
 */
public interface Housing {
	/**
	 * This function checks if the housing is empty or not.
	 * @return True for empty housing, False for non-empty one.
	 */
	boolean isEmpty();
	/**
	 * This function checks if the housing is full or not.
	 * @return True for empty housing, False for not yet full one.
	 */
	boolean isFull();
	/**
	 * This function checks if the housing contains animal of the same species as the input animal.
	 * @param animal to be checked if same species are contained
	 * @return True of False for weather same species animal are living in the housing
	 */
	boolean hasSpecies(Animal animal);
	
	boolean hasRoom(Animal animal);
	
	int sendOut(Animal animal);
	
	int receive(Animal animal);
	
	Map<String, Object> getDetail();
	
	Map<String, Object> lookUp(Animal animal);
	
	List<Food> getFoodList();

}
