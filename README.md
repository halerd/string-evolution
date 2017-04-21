# string-evolution

## Synopsis

Genetic algorithm which attempts to evolve a population of strings to a target string.

Uses crossover, mutation, tournament selection and (optionally) elitism to find a solution.

## Code Example

Runs the genetic algorithm and prints results for the following population configuration:
* 50 members
* 5% mutation rate
* Elitism enabled

```
String target = "Hello, World!";
FitnessMethod<char[]> fitnessMethod = new StringFitnessMethod(target.toCharArray());
CrossoverSelectionMethod<char[]> selectionMethod = new TournamentSelection<char[]>(fitnessMethod);
StringMutationGA algorithm = new StringMutationGA(target.toCharArray(), 50, 0.05, true, fitnessMethod, selectionMethod);
algorithm.initialise();
algorithm.compute(true);
```