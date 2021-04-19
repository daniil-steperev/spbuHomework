package ru.group343.stepyrev.function;

/** Класс, который реализует точную функцию u(x, t) = 2 * x - t. */
public class FunctionAccurate implements Function {

  @Override
  public double getValue(double x, double t) {
    return 2 * x - t;
  }

  @Override
  public double getDerivativeXValue(double x, double t) {
    return 2d;
  }

  @Override
  public double getSecondDerivativeXValue(double x, double t) {
    return 0d;
  }

  @Override
  public double getDerivativeTValue(double x, double t) {
    return -1d;
  }
}
