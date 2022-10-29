package animal;

import java.util.Comparator;

/**
 * Comparator for monkey, sorting by their name.
 *
 *
 */
public class SortMonkeyByName implements Comparator<Monkey> {

  @Override
  public int compare(Monkey o1, Monkey o2) {
    
    if (o1 == null) {
      return -1;
    } else if (o2 == null) {
      return 1;
    }
    return o1.getName().compareTo(o2.getName());
  }

}
