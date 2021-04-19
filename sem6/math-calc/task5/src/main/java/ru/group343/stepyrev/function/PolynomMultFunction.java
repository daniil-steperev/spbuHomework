package ru.group343.stepyrev.function;

import static java.lang.Math.pow;

/** Класс, который реализует функцию u(x, t) = x^3 * t^3. */
public class PolynomMultFunction implements Function {

  /** Метод, который возвращает значение функции u(x, t) в точке (x, t). */
  public double getValue(double x, double t) {
    return pow(x, 3) * pow(t, 3);
  }

  @Override
  public double getDerivativeXValue(double x, double t) {
    return 3 * pow(x, 2) * pow(t, 3);
  }

  @Override
  public double getSecondDerivativeXValue(double x, double t) {
    return 6 * x * pow(t, 3);
  }

  @Override
  public double getDerivativeTValue(double x, double t) {
    return 3 * pow(x, 3) * pow(t, 2);
  }
}
