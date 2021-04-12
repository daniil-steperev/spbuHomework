package ru.mathcalc.test5.stepyrev.solver;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import org.apache.commons.math3.complex.Complex;
import ru.mathcalc.test5.stepyrev.util.Function;
import ru.mathcalc.test5.stepyrev.util.FunctionG;

/** Класс, который реализует численный метод Виддера. */
public class NumericalSolver {

  /** Метод, который реализует численный метод Виддера. */
  public double getValue(double t, int n, int m, double r) {
    Complex result = Complex.valueOf(0d, 0d);

    for (int j = 1; j <= m; j++) {
      Complex expValue = FunctionG.getExpValue(j, m);
      Complex rExpValue = expValue.multiply(r);
      Complex fstFactor = rExpValue.pow(-n); // pow(r * expValue, -n);
      // 1 / (1 - r * exp)
      Complex idComplex = Complex.valueOf(1d);
      Complex sndFactor = idComplex.divide(idComplex.subtract(rExpValue));

      Complex p = (idComplex.subtract(rExpValue)).divide(t).multiply(n);
      Complex fiValue = Function.getValue(p).multiply(p);

      Complex fstSndFactor = fstFactor.multiply(sndFactor);
      Complex fstSndFiFactor = fstSndFactor.multiply(fiValue);
      result = result.add(fstSndFiFactor);
    }

    double resultReal =  result.getReal();
    double resultImag =  result.getImaginary();
    return sqrt(resultReal * resultReal + resultImag * resultImag);
  }

  /**
   * Метод, который реализует численный метод Виддера. / public double getValue(double t, int n, int
   * m, double r) { double result = 0d;
   *
   * <p>for (int j = 1; j <= m; j++) { double expValue = FunctionG.getExpValue(j, m); double
   * rExpValue = r * expValue; double fstFactor = pow(rExpValue, -n); // pow(r * expValue, -n); // 1
   * / (1 - r * exp) double sndFactor = 1d / (1d - rExpValue);
   *
   * <p>double p = n * (1 - r * expValue) / t; double fiValue = p * Function.getValue(p); result +=
   * fstFactor * sndFactor * fiValue; }
   *
   * <p>return result / m; }
   */
}
