package ru.group343.stepyrev.function;

/** Класс, который реализует функцию a(x, t) = cos(x). */
public class FunctionB implements Function {

  @Override
  public double getValue(double x, double t) {
    return x;
  }

  @Override
  public double getDerivativeXValue(double x, double t) {
    return 1d;
  }

  @Override
  public double getSecondDerivativeXValue(double x, double t) {
    return 0d;
  }

  @Override
  public double getDerivativeTValue(double x, double t) {
    return 0d;
  }
}
