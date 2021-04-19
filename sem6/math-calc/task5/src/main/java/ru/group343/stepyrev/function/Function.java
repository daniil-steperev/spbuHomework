package ru.group343.stepyrev.function;

/** Интерфейс, реализующий функцию u(x, t). */
public interface Function {

  /** Метод, возвращающий значение функции u(x, t) в точке (x, t). */
  double getValue(double x, double t);

  /** Метод, возвращающий значение производной функции u(x, t) по x в точке (x, t). */
  double getDerivativeXValue(double x, double t);

  /** Метод, возвращающий значение второй производной функции u(x, t) по x в точке (x, t). */
  double getSecondDerivativeXValue(double x, double t);

  /** Метод, возвращающий значение второй производной функции u(x, t) по t в точке (x, t). */
  double getDerivativeTValue(double x, double t);

}
