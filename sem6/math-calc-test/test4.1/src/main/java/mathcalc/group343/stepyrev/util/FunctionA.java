package mathcalc.group343.stepyrev.util;

/** Метод, который реализует оператор A. */
public class FunctionA {

  /** Метод, который возвращает значение оператора A в точке х. */
  public static double getValue(double x) {
    return Math.log(10 - x) - Math.log(9 - x);
  }

}
