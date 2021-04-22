package mathcalc.group343.stepyrev.util;

import java.util.List;

/** Класс, в котором содержатся константы, используемые в задаче. */
public class ParamsUtil {

  public static final double alpha1 = 0d;
  public static final double alpha2 = -1d;
  public static final double beta1 = 1d;
  public static final double beta2 = 1d;

  /** Метод, который возвращает значение i-го элемента координатной системы в точке x. */
  public static double getCoordinationSystemValue(double x, int i, List<Double> jacobiPolynoms) {
    if (i == 0) {
      return 1;
    }

    if (i == 1) {
      return x;
    }

    int n = i - 2;
    return (1 - x * x) * jacobiPolynoms.get(n);
  }

  /**
   * Метод, который возвращает значение первой производной i-го элемента координатной системы в
   * точке x.
   */
  public static double getCoordinateFuncDerivative(double x, int i, List<Double> jacobiPolynoms) {
    if (i == 0) {
      return 0.0;
    }

    if (i == 1) {
      return 1.0;
    }

    int n = i - 2;
    double polynomNval = jacobiPolynoms.get(n);

    if (i == 2) {
      return -2 * x * polynomNval;
    }

    double k = 1d;
    double polynomN_1val = ((n + 2 * k + 1) / 2) * jacobiPolynoms.get(n - 1);
    return (1 - x * x) * polynomN_1val - 2 * x * polynomNval;
  }

  /**
   * Метод, который возвращает значение второй производной i-го элемента координатной системы в
   * точке x.
   */
  public static double getCoordinateFuncSndDerivative(
      double x, int i, List<Double> fstPolynomDerivatives) {
    if (i == 0) {
      return 0.0;
    }

    if (i == 1) {
      return 0.0;
    }

    double k = 1;
    int n = i - 2;
    double polynomNval = ((n + 2 * k + 1) / 2) * fstPolynomDerivatives.get(n);

    if (i == 2) {
      return 2 * polynomNval;
    }

    double polynomN_1val = ((n + 2 * k + 1) / 2) * fstPolynomDerivatives.get(n - 1);
    return (1 - x * x) * polynomN_1val - 2 * polynomNval;
  }
}
