package mathcalc.group343.stepyrev.util;

/** Класс, который реализует многочлен Лежандра. */
public class LegandrePolynom {

  /** Метод, который возвращает значения n-го многочлена Лежандра в точке x. */
  public static double getValue(double x, int n) {
    double value = 0d;

    for (int k = 0; k <= n; k++) {
      double combination = factorial(n) / (factorial(k) * factorial(n - k));
      combination *= combination;
      double xComb = Math.pow(x - 1, n - k) * Math.pow(x + 1, k);
      value += combination * xComb;
    }

    return value / Math.pow(2, n);
  }

  private static double factorial(int k) {
    if (k <= 1) {
      return 1;
    }

    return k * factorial(k - 1);
  }
}
