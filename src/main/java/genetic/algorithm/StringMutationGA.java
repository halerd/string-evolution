package genetic.algorithm;

import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import genetic.algorithm.fitness.FitnessMethod;
import genetic.algorithm.selection.CrossoverSelectionMethod;
import genetic.algorithm.util.RandomUtil;

public class StringMutationGA {

  private final char[] target;
  private final int members;
  private final double mutationRate;
  private final boolean elitism;
  private final FitnessMethod<char[]> fitnessMethod;
  private final CrossoverSelectionMethod<char[]> selectionMethod;
  private char[][] population;
  private int generation = 0;

  public StringMutationGA(final char[] target, final int members, final double mutationRate,
      final boolean elitism, final FitnessMethod<char[]> fitnessMethod,
      final CrossoverSelectionMethod<char[]> selectionMethod) {
    this.target = target;
    this.members = members;
    this.mutationRate = mutationRate;
    this.elitism = elitism;
    this.population = new char[this.members][target.length];
    this.fitnessMethod = fitnessMethod;
    this.selectionMethod = selectionMethod;
  }

  public void initialise() {
    for (int i = 0; i < members; i++) {
      for (int j = 0; j < target.length; j++) {
        population[i][j] = RandomUtil.randomPrintableChar();
      }
    }
  }

  public void compute(final boolean print) {
    char[] fittest = findFittest();
    int fitness = fitnessMethod.fitness(fittest);
    if (print) {
      System.out
          .println(String.format("Gen: init | %s | Fitness: %04d", new String(fittest), fitness));
    }
    while (0 < fitness) {
      population = evolvePopulation();
      fittest = findFittest();
      fitness = fitnessMethod.fitness(fittest);
      if (print) {
        System.out.println(String.format("Gen: %04d | %s | Fitness: %04d", generation,
            new String(fittest), fitness));
      }
      generation++;
    }
  }

  protected char[] findFittest() {
    char[] fittest = population[0];
    int fitness = fitnessMethod.fitness(population[0]);
    for (int i = 1; i < population.length; i++) {
      int currentFitness = fitnessMethod.fitness(population[i]);
      if (currentFitness <= fitness) {
        fitness = currentFitness;
        fittest = population[i];
      }
    }
    return fittest;
  }

  protected char[][] evolvePopulation() {
    char[][] evolved = new char[members][target.length];
    Entry<char[], char[]> parents = selectionMethod.selectParents(population);
    char[] p1 = parents.getKey();
    char[] p2 = parents.getValue();
    if (elitism) {
      char[] best = findFittest();
      evolved[0] = best;
      evolve(1, evolved, p1, p2);
    } else {
      evolve(0, evolved, p1, p2);
    }
    return evolved;
  }

  // TODO: GA Design - Breeding could produce 2 children
  // TODO: GA Design - Crossover/mutation could occur on entire String, as opposed to each character
  protected void evolve(final int startIndex, final char[][] evolved, final char[] p1,
      final char[] p2) {
    for (int i = startIndex; i < members; i++) {
      char[] child = new char[target.length];
      for (int j = 0; j < target.length; j++) {
        breed(j, p1, p2, child);
      }
      evolved[i] = child;
    }
  }

  protected void breed(final int index, final char[] p1, final char[] p2, char[] child) {
    // Mutate
    if (ThreadLocalRandom.current().nextDouble(0, 1) <= mutationRate) {
      child[index] = RandomUtil.randomPrintableChar();
    }
    // Crossover
    else {
      crossover(index, p1, p2, child);
    }
  }

  protected void crossover(final int index, final char[] p1, final char[] p2, char[] child) {
    if (ThreadLocalRandom.current().nextInt(0, 2) == 0) {
      child[index] = p1[index];
    } else {
      child[index] = p2[index];
    }
  }

  public char[][] getPopulation() {
    return population;
  }

  public int getGeneration() {
    return generation;
  }

}
