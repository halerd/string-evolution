package genetic.algorithm.selection;

import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import genetic.algorithm.StringMutationGA;
import genetic.algorithm.fitness.FitnessMethod;
import genetic.algorithm.fitness.StringFitnessMethod;

public class TournamentSelectionTest {

  @Test
  public void testSelectParents() {
    String target = "Hello, World!";
    FitnessMethod<char[]> fitnessMethod = new StringFitnessMethod(target.toCharArray());
    TournamentSelection<char[]> selectionMethod = new TournamentSelection<char[]>(fitnessMethod);
    StringMutationGA ga =
        new StringMutationGA(target.toCharArray(), 50, 0.05, true, fitnessMethod, selectionMethod);
    ga.initialise();
    for (int i = 0; i < 1000000; i++) {
      Entry<char[], char[]> parents = selectionMethod.selectParents(ga.getPopulation());
      Assert.assertNotEquals("Parents should not match", new String(parents.getKey()),
          new String(parents.getValue()));
    }
  }

}
