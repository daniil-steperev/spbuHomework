package group343.stepyrev.lab6;

import group343.stepyrev.util.FunctionPoint;
import java.util.ArrayList;
import java.util.List;

public class CauchyFinder {

  private double x0;
  private double y0;
  private double h;
  private double N;

  private List<FunctionPoint> accurateValue;

  public CauchyFinder(double x0, double y0, double h, double n) {
    this.x0 = x0;
    this.y0 = y0;
    this.h = h;
    N = n;
  }

  /**
   * Метод, который возвращает точное решение для y'(x) = -y^2(x) + 1.
   */
  public List<FunctionPoint> findAccurateSolve() {
    accurateValue = new ArrayList<>();

    double xK = 0;
    double yK = 0;
    for (int k = -2; k <= N; k++) {
      xK = x0 + k * h;
      yK = getFunctionValue(xK);

      accurateValue.add(new FunctionPoint(xK, yK));
    }

    return accurateValue;
  }

  /**
   * Метод, который возвращает решение методом Тейлора для y'(x) = -y^2(x) + 1.
   */
  public List<FunctionPoint> findTaylorSolve() {
    List<FunctionPoint> points = new ArrayList<>();

    double xK = 0;
    double yK = 0;
    for (int k = -2; k <= N; k++) {
      xK = x0 + k * h;
      yK = getTaylorFunctionValue(xK);

      points.add(new FunctionPoint(xK, yK));
    }

    return points;
  }

  /**
   * Метод, который возвращает решение методом Адамса для y'(x) = -y^2(x) + 1.
   */
  public List<FunctionPoint> findAdamsSolve() {
    List<FunctionPoint> points = findTaylorSolve().subList(0, 5);

    double xK = 0;
    double currentY = 0;
    double previousY = points.get(4).getFunctionValue();
    List<Double> q = getQ(points);
    List<Double> dq = getDifference(q);
    List<Double> d2q = getDifference(dq);
    List<Double> d3q = getDifference(d2q);
    List<Double> d4q = getDifference(d3q);

    double qK;
    for (int k = 5; k <= N + 2; k++) {
      xK = x0 + (k - 2) * h;
      currentY =
          previousY +
          q.get(k - 1) +
          1 / 2 * dq.get(k - 2) +
          5 / 12 * d2q.get(k - 3) +
          3 / 8 * d3q.get(k - 4) +
          251 / 720 * d4q.get(k - 5);

      points.add(new FunctionPoint(xK, currentY));

      previousY = currentY;
      qK = h * getFirstDerivativeValue(currentY);
      q.add(qK);

      dq  = updateQk(q, dq);
      d2q = updateQk(dq, d2q);
      d3q = updateQk(d2q, d3q);
      d4q = updateQk(d3q, d4q);
    }

    return points.subList(5, points.size());
  }

  /**
   * Метод, который возвращает решение методом Рунге-Кутта для y'(x) = -y^2(x) + 1.
   */
  public List<FunctionPoint> findRungeKuttaSolve() {
    List<FunctionPoint> points = new ArrayList<>();
    double xK;
    double yK;
    double k1;
    double k2;
    double k3;
    double k4;
    double previousY = 0;
    double currentY = 0;
    for (int i = 1; i <= N; i++) {
      xK = x0 + i * h;
      k1 = h * getFirstDerivativeValue(previousY);
      k2 = h * getFirstDerivativeValue(previousY + k1 / 2);
      k3 = h * getFirstDerivativeValue(previousY + k2 / 2);
      k4 = h * getFirstDerivativeValue(previousY + k3);

      currentY = previousY + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
      points.add(new FunctionPoint(xK, currentY));
      previousY = currentY;
    }

    return points;
  }

  /**
   * Метод, который возвращает решение методом Эйлера для y'(x) = -y^2(x) + 1.
   */
  public List<FunctionPoint> findEulerSolve() {
    List<FunctionPoint> points = new ArrayList<>();

    double xK;
    double currentY;
    double previousY = y0;
    for (int i = 1; i <= N; i++) {
      xK = x0 + i * h;
      currentY = previousY + h * getFirstDerivativeValue(previousY);

      points.add(new FunctionPoint(xK, currentY));
      previousY = currentY;
    }

    return points;
  }

  /**
   * Метод, который возвращает решение методом Эйлера I для y'(x) = -y^2(x) + 1.
   */
  public List<FunctionPoint> findModifiedEulerSolve() {
    List<FunctionPoint> points = new ArrayList<>();

    double xK;
    double argumentValue;
    double currentY;
    double previousY = y0;
    for (int i = 1; i <= N; i++) {
      xK = x0 + i * h;
      argumentValue = previousY + h / 2 * getFirstDerivativeValue(previousY);
      currentY = previousY + h * getFirstDerivativeValue(argumentValue);

      points.add(new FunctionPoint(xK, currentY));
      previousY = currentY;
    }

    return points;
  }

  /**
   * Метод, который возвращает решение методом Эйлера II для y'(x) = -y^2(x) + 1.
   */
  public List<FunctionPoint> findTwiceModifiedEulerSolve() {
    List<FunctionPoint> points = new ArrayList<>();

    double xK;
    double tildaY;
    double currentY;
    double previousY = y0;
    for (int i = 1; i <= N; i++) {
      xK = x0 + i * h;
      tildaY = previousY + h * getFirstDerivativeValue(previousY);
      currentY = previousY + h / 2 * (getFirstDerivativeValue(previousY) + getFirstDerivativeValue(
          tildaY));

      points.add(new FunctionPoint(xK, currentY));
      previousY = currentY;
    }

    return points;
  }

  private List<Double> updateQk(List<Double> prevQ, List<Double> curQ) {
    List<Double> newQ = new ArrayList<>(curQ);

    int size = prevQ.size();
    double qK = prevQ.get(size - 1) - prevQ.get(size - 2);
    newQ.add(qK);

    return newQ;
  }

  private List<Double> getQ(List<FunctionPoint> firstPoints) {
    List<Double> points = new ArrayList<>();

    for (int i = 0; i < firstPoints.size(); i++) {
      double yK = firstPoints.get(i).getFunctionValue();
      points.add(h * getFirstDerivativeValue(yK));
    }

    return points;
  }

  private List<Double> getDifference(List<Double> previous) {
    List<Double> difference = new ArrayList<>();

    double curElement = 0;
    for (int i = 1; i < previous.size(); i++) {
      curElement = previous.get(i) - previous.get(i - 1);
      difference.add(curElement);
    }

    return difference;
  }


  /**
   * Метод, который возвращает значение функции y(x) = (-1 - e^(2x)) / (1 - e^(2x)).
   */
  private double getFunctionValue(double x) {
    return (Math.exp(2 * x) - 1) / (1 + Math.exp(2 * x));
  }

  /**
   * Метод, который возвращает значение функции Тейлора для первых пяти ненулевых коэффициентов.
   */
  private double getTaylorFunctionValue(double x) {
    return
        1 * (x - x0) +
            -2 * Math.pow(x - x0, 3) / 6 +
            16 * Math.pow(x - x0, 5) / 120 +
            -32 * Math.pow(x - x0, 6) / 720 +
            36 * Math.pow(x - x0, 7) / 5040;
  }

  /**
   * Метод, который возвращает значений функции f(x, y) = -y^2(x) + 1.
   */
  private double getFirstDerivativeValue(double y) {
    return -Math.pow(y, 2) + 1;
  }
}
