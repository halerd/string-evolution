package genetic.algorithm.selection;

import java.util.Map.Entry;

public interface CrossoverSelectionMethod<T> {

  public Entry<T, T> selectParents(final T[] population);

}
