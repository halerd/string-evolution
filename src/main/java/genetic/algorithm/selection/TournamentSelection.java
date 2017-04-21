package genetic.algorithm.selection;

import java.util.AbstractMap;
import java.util.Map.Entry;

import genetic.algorithm.fitness.FitnessMethod;
import genetic.algorithm.util.RandomUtil;

public class TournamentSelection<T> implements CrossoverSelectionMethod<T> {

  final FitnessMethod<T> fitnessMethod;

  public TournamentSelection(final FitnessMethod<T> fitnessMethod) {
    this.fitnessMethod = fitnessMethod;
  }

  public Entry<T, T> selectParents(final T[] population) {
    Entry<T, Integer> p1Data = tournamentSelect(population);
    Entry<T, Integer> p2Data = tournamentSelect(population, p1Data.getValue());
    Entry<T, T> parentPair = new AbstractMap.SimpleEntry<T, T>(p1Data.getKey(), p2Data.getKey());
    return parentPair;
  }

  protected Entry<T, Integer> tournamentSelect(final T[] population) {
    return tournamentSelect(population, -1);
  }

  protected Entry<T, Integer> tournamentSelect(final T[] population, final int excludeIndex) {
    int indexP1 = -1;
    int indexP2 = -1;
    while (true) {
      indexP1 = RandomUtil.randomInt(0, population.length - 1);
      indexP2 = RandomUtil.randomInt(0, population.length - 1);
      if (indexP1 == indexP2) {
        continue;
      }
      if (excludeIndex != -1) {
        if (indexP1 == excludeIndex || indexP2 == excludeIndex) {
          continue;
        }
      }
      T p1 = population[indexP1];
      T p2 = population[indexP2];
      int p1Fitness = fitnessMethod.fitness(p1);
      int p2Fitness = fitnessMethod.fitness(p2);
      if (p1Fitness < p2Fitness) {
        return new AbstractMap.SimpleEntry<T, Integer>(p1, indexP1);
      } else {
        return new AbstractMap.SimpleEntry<T, Integer>(p2, indexP2);
      }
    }
  }

}
