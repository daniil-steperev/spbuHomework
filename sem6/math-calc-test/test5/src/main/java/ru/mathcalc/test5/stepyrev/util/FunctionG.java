package ru.mathcalc.test5.stepyrev.util;

import static java.lang.Math.PI;
import static org.apache.commons.math3.complex.Complex.I;

import org.apache.commons.math3.complex.Complex;

/** Класс, который реализует функцию G. */
public class FunctionG {

  /** Метод, который реализует функцию G. */
  public static double getValue(double z, double t) {
    return 1 / t * Function.getValue((1 - z) / t);
  }

  /** Метод, который реализует функцию em(x). */
  public static Complex getExpValue(double x, int m) {
    return I.multiply(2 * PI * x / m).exp();
    //double real = val.getReal();
    //double imag = val.getImaginary();
    //return  sqrt(real * real + imag * imag);
  }
}
