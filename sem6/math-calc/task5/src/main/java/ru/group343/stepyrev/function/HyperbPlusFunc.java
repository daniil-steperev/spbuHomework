package ru.group343.stepyrev.function;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/** Класс, который реализует функцию u(x, t) = sin(2t + 1) + cos(2x). */
public class HyperbPlusFunc implements Function {

  /** Метод, который возвращает значение функции u(x, t) в точке (x, t). */
  public double getValue(double x, double t) {
    return sin(2 * t + 1) + cos(2 * x);
  }

  @Override
  public double getDerivativeXValue(double x, double t) {
    return -2 * sin(2 * x);
  }

  @Override
  public double getSecondDerivativeXValue(double x, double t) {
    return -4 * cos(2 * x);
  }

  @Override
  public double getDerivativeTValue(double x, double t) {
    return 2 * cos(2 * t + 1);
  }
}
