package mathcalc.group343.stepyrev.solver;

import java.util.ArrayList;
import java.util.List;
import mathcalc.group343.stepyrev.util.Function;
import mathcalc.group343.stepyrev.util.FunctionA;
import mathcalc.group343.stepyrev.util.LegandrePolynom;

/**
 * Класс, который вычисляет значения интеграла методом прямоугольников.
 */
public class IntegralRectangleSolver {

  private double A;
  private double H;
  private double M;
  private double X;

  public IntegralRectangleSolver(double a, double h, double m, double x) {
    A = a;
    H = h;
    M = m;
    X = x;
  }

  /**
   * Метод, который возвращает коэффициенты разложения интеграла методом прямоугольников.
   */
  public List<Double> getIntegralCoefs() {
    double alpha = A + H / 2;
    List<Double> integralCoefs = new ArrayList<>();
    for (int k = 1; k <= M; k++) {
      integralCoefs.add(Function.getValue(X, alpha + (k - 1) * H));
    }

    return integralCoefs;
  }

  /**
   * Метод, который возвращает коэффициенты разложения интеграла методом прямоугольников.
   */
  public List<Double> getIntegralInMomentCoefs(int j) {
    double alpha = A + H / 2;
    List<Double> integralCoefs = new ArrayList<>();
    for (int k = 1; k <= M; k++) {
      integralCoefs.add(FunctionA.getValue(alpha + (k - 1) * H) * LegandrePolynom
          .getValue(alpha + (k - 1) * H, j));
    }

    return integralCoefs;
  }

}
