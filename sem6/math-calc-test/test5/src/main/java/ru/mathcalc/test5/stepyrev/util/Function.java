package ru.mathcalc.test5.stepyrev.util;

import static java.lang.Math.pow;

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.complex.Complex;

/** Класс, который реализует функцию F. */
public class Function {
  /** Метод, который возвращает значение функции F в точке p. */
  public static double getValue(double p) {
    return (pow(p, 1d / 2) - pow(p, 1d / 3)) / (p - 3);
  }

  /** Метод, который возвращает значение функции F в точке p. */
  public static Complex getValue(Complex p) {
    Complex sqrtP = p.pow(1d / 2);
    Complex sqrt3p = p.pow(1d / 3);
    Complex minis3p = p.subtract(3);
    return (sqrtP.subtract(sqrt3p)).divide(minis3p);
  }

  /** Метод, который возвращает значение производной F(n) в точке p. */
  public static double getDerivative(double p, int n) {
    DerivativeStructure x = new DerivativeStructure(1, n, 0, p);
    DerivativeStructure sqrtX = x.pow(1d / 2);
    DerivativeStructure sqrt3x = x.pow(1d / 3);
    DerivativeStructure numerator = sqrtX.subtract(sqrt3x);
    DerivativeStructure denumerator = x.subtract(3);
    DerivativeStructure function = numerator.divide(denumerator);
    return function.getPartialDerivative(n);
  }
}
