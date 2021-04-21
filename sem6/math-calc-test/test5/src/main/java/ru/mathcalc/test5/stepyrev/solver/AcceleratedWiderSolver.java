package ru.mathcalc.test5.stepyrev.solver;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Класс, который реализует ускоренный метод Виддера. */
public class AcceleratedWiderSolver {

  private List<Double> dK;
  private Random random;

  public AcceleratedWiderSolver() {
    Long seed = 21L;
    random = new Random(seed);
  }

  /** Метод, который реализует ускоренный метод Виддера. */
  public double getValue(double t, int k, int n) {
    WidderSolver solver = new WidderSolver();
    List<Double> cK = countCk(k, n);

    double result = 0d;
    for (int j = 0; j < n; j++) {
      double cKJ = cK.get(j);
      double dJ = dK.get(j);
      int kDj = (int) (k * dJ);
      double widderValue = solver.getValue(t, kDj);
      result += cKJ * widderValue;
    }

    return result;
  }

  /** Метод, который считает коэффициенты cK. */
  private List<Double> countCk(int k, int n) {
    dK = countDk(k);
    List<Double> coefs = new ArrayList<>();
    for (int j = 0; j < k; j++) {
      double dJ = dK.get(j);
      double cKJ = 1d;
      for (int i = 0; i < n; i++) {
        if (i == j) {
          continue;
        }

        double dI = dK.get(i);
        cKJ *= dJ / (dJ - dI);
      }
      coefs.add(cKJ);
    }

    return coefs;
  }

  /** Метод, который считает коэффициенты dK. */
  private List<Double> countDk(int k) {
    List<Double> coefs = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      double dK = abs(((double) random.nextInt()) % 10 / k);
      while (coefs.contains(dK)) {
        dK = abs(((double) random.nextInt()) % 10 / k);
      }

      coefs.add(dK);
    }
    return coefs;
  }
}
