package ru.mathcalc.test5.stepyrev.util;

/** Класс, который реализует факториал. */
public class Factorial {

  public static int getValue(int n) {
    if (n <= 1) {
      return 1;
    }

    return n * getValue(n - 1);
  }

}
