package genetic.algorithm.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

  private final static int PRINTABLE_ASCII_START = 32;
  private final static int PRINTABLE_ASCII_END = 126;

  public static char randomPrintableChar() {
    return (char) ThreadLocalRandom.current().nextInt(PRINTABLE_ASCII_START,
        PRINTABLE_ASCII_END + 1);
  }

  public static int randomInt(final int min, final int max) {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

}
