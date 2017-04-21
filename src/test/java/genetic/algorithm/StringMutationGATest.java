package genetic.algorithm;

import org.junit.Test;

import genetic.algorithm.fitness.FitnessMethod;
import genetic.algorithm.fitness.StringFitnessMethod;
import genetic.algorithm.selection.CrossoverSelectionMethod;
import genetic.algorithm.selection.TournamentSelection;

public class StringMutationGATest {

  @Test
  public void testCompute() {
    String target = "Hello, World!";
    FitnessMethod<char[]> fitnessMethod = new StringFitnessMethod(target.toCharArray());
    CrossoverSelectionMethod<char[]> selectionMethod =
        new TournamentSelection<char[]>(fitnessMethod);
    StringMutationGA ga =
        new StringMutationGA(target.toCharArray(), 50, 0.05, true, fitnessMethod, selectionMethod);
    ga.initialise();
    ga.compute(false);
  }

}
