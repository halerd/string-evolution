package genetic.algorithm.fitness;

public class StringFitnessMethod implements FitnessMethod<char[]> {

  private final char[] target;

  public StringFitnessMethod(final char[] target) {
    this.target = target;
  }

  public int fitness(final char[] actual) {
    int fitness = 0;
    if (target.length != actual.length) {
      throw new RuntimeException(String.format("Member \"%s\" did not match expected length of %d",
          new String(actual), target.length));
    }
    for (int i = 0; i < target.length; i++) {
      fitness += difference(target[i], actual[i]);
    }
    return fitness;
  }

  protected int difference(final char a, final char b) {
    int difference = (int) a - (int) b;
    if (difference < 0) {
      return -(difference);
    }
    return difference;
  }

}
