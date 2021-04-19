package ru.group343.stepyrev.function;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/** Класс, который реализует функцию a(x, t) = cos(x). */
public class FunctionA implements Function {

  @Override
  public double getValue(double x, double t) {
    return cos(x);
  }

  @Override
  public double getDerivativeXValue(double x, double t) {
    return -sin(x);
  }

  @Override
  public double getSecondDerivativeXValue(double x, double t) {
    return -cos(x);
  }

  @Override
  public double getDerivativeTValue(double x, double t) {
    return 0d;
  }
}
