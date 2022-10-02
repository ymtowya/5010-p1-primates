package housing;

import animal.Animal;
import java.util.List;
import java.util.Map;

/**
 * This interface provides general functions for housing-related classes.
 *
 */
public interface Housing {
  /**
   * This function checks if the housing is empty or not.
   *
   * @return True for empty housing, False for non-empty one.
   */
  boolean isEmpty();

  /**
   * This function checks if the housing is full or not.
   *
   * @return True for empty housing, False for not yet full one.
   */
  boolean isFull();

  /**
   * This function checks if the housing contains animal of the same species as the input animal.
   *
   *
   * @param animal to be checked if same species are contained
   * @return True of False for weather same species animal are living in the
   *         housing
   */
  boolean hasSpecies(Animal animal);

  /**
   * This function checks if there are rooms for the input animal. Prerequisite is
   * the animal must be same species.
   *
   * @param animal input argument of the animal.
   * @return boolean denoting has or not.
   */
  boolean hasRoom(Animal animal);

  /**
   * This function sends out the input argument animal.
   *
   * @param animal that to be sent out.
   * @return negative number if this failed. Non-negative number if succeeds.
   */
  int sendOut(Animal animal);

  int receive(Animal animal);

  List<String> getDetail();

  Map<String, Object> lookUp(Animal animal);

}
