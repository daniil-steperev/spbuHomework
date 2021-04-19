package ru.group343.stepyrev.solver;

import static java.lang.Math.cos;

import ru.group343.stepyrev.function.Function;

/** Класс, который вовзращает точное решение уравнения. */
public class AccurateSolver {

  /** Метод, который вовзращает точное решение уравнения. */
  public double getValue(double x, double t, Function function) {
    return function.getValue(x, t);
  }

  /** Метод, который возвращает значение функции f в точке (x, t).*/
  public static double getFValue(double x, double t, Function function) {
     return function.getDerivativeTValue(x, t)
     - cos(x) * function.getSecondDerivativeXValue(x, t)
     + x * function.getDerivativeXValue(x, t);
  }
}
