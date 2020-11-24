package group343.stepyrev.lab5;

import java.util.List;

public class TrapezeIntegralFinder {

  private double A;
  private double B;
  private Integer M;
  private double H;

  public TrapezeIntegralFinder(double a, double b) {
    A = a;
    B = b;
    M = 10000;
    H = (B - A) / M;
  }

  public double countIntegral(List<Double> points) {
    double integralSumValue =
        points.get(0) + points.get(M);

    for (int i = 1; i < M; i++) {
      integralSumValue += 2 * points.get(i);
    }

    return (B - A) * integralSumValue / (2 * M);
  }

  public double countIntegral(int deg) {
    return getIntegralByMiddleRectangleMethod(deg);
  }

  /**
   * Вычисление значения интеграла методом средних прямоугольников.
   */
  public double getIntegralByMiddleRectangleMethod(int deg) {
    return getIntegralByRectangleMethod(A + H / 2, deg);
  }

  /**
   * Вычисление значения интеграла методом прямоугольников.
   */
  private double getIntegralByRectangleMethod(Double alpha, int deg) {
    double integralSumValue = 0;
    for (int k = 1; k <= M; k++) {
      integralSumValue += getFunctionValue(alpha + (k - 1) * H, deg);
    }

    return H * integralSumValue;
  }

  public double getFunctionValue(double x, int deg) {
      double fX = getWeightFunctionValue(x);
      switch (deg) {
        case 0:
          break;
        case 1:
          fX *= x;
          break;
        case 2:
          fX *= x * x;
          break;
        case 3:
          fX *= x * x * x;
          break;
        default:
          fX *= Math.pow(x, deg);
          break;
      }

    return fX;
  }

  private double getWeightFunctionValue(double x) {
    return 1 / Math.sqrt(1 - x * x);
  }
}
