package genetic.algorithm.fitness;

public interface FitnessMethod<T> {

  public int fitness(final T actual);

}
