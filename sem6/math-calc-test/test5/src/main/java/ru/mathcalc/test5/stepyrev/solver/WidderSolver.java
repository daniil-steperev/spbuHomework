package ru.mathcalc.test5.stepyrev.solver;

import static java.lang.Math.pow;

import ru.mathcalc.test5.stepyrev.util.Factorial;
import ru.mathcalc.test5.stepyrev.util.Function;

/** Класс, который реализует метод Виддера. */
public class WidderSolver {

  /** Метод, который реализует метод Виддера. */
  public double getValue(double t, int n) {
    double p = n / t;
    double derivativeF = Function.getDerivative(p, n);
    double sign = n % 2 == 0 ? 1d : -1d;
    double factorial = Factorial.getValue(n);
    double pN = pow(p, n + 1);
    return sign * pN * derivativeF / factorial;
  }
}
