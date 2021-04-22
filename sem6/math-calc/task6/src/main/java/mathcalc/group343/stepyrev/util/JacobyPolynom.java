package mathcalc.group343.stepyrev.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Класс, который реализует многочлены Якоби. */
public class JacobyPolynom {

  private List<Double> polynoms;

  /** Метод, который считает полиномы Якоби. */
  public List<Double> initPolynoms(int n, int k, double x) {
    polynoms = new ArrayList<>();

    polynoms.addAll(getStartPolynoms(k, x));
    for (int i = 2; i < n; i++) {
      double prevPolynom = polynoms.get(i - 1);
      double prevPrevPolynom = polynoms.get(i - 2);
      double curPolynom =
          ((n + k + 2) * (2 * n + 2 * k + 3) * x * prevPolynom
                  - (n + k + 2) * (n + k + 1) * prevPrevPolynom)
              / ((n + 2 * k + 2) * (n + 2));
      polynoms.add(curPolynom);
    }

    return polynoms;
  }

  private List<Double> getStartPolynoms(int k, double x) {
    double pol0 = 1;
    double pol1 = (k + 1) * x;

    return new ArrayList<>(Arrays.asList(pol0, pol1));
  }
}
