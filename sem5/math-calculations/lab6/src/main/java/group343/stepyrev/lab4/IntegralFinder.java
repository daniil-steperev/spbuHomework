package group343.stepyrev.lab4;

import group343.stepyrev.util.FunctionPoint;
import java.util.LinkedList;
import java.util.List;

public class IntegralFinder {

  private double A;
  private double B;
  private Integer M;
  private Double H;
  private Double WEIGHT_FUNCTION = 1D;

  /**
   * 1: f(x) = 5; 2: f(x) = 3x; 3: f(x) = 3x^3 -2x^2 + 5x - 1 4: f(x) = 7 * e ^ (3 * x)
   */
  private int FUNCTION_NUMBER = 1;
  private List<FunctionPoint> points;
  private List<FunctionPoint> simpsonPoints;

  public IntegralFinder(double a, double b, Integer m, Double h, int FUNCTION_NUMBER) {
    A = a;
    B = b;
    M = m;
    H = h;
    this.FUNCTION_NUMBER = FUNCTION_NUMBER;
    points = initializePoints(A, M, H);
    simpsonPoints = initializePoints(A, 2 * M, H / 2);
  }

  public List<FunctionPoint> initializePoints(Double a, Integer m, Double h) {
    List<FunctionPoint> pointList = new LinkedList<>();

    for (int i = 0; i < m + 1; i++) {
      double x = a + i * h;
      double fX = getFunctionValue(x);
      pointList.add(new FunctionPoint(x, fX));
    }

    return pointList;
  }

  /**
   * Вычисление значения интеграла с использованием матпакета.
   */
  public double getAccurateIntegralValue() {
    switch (FUNCTION_NUMBER) {
      case 1:
        return 5d * WEIGHT_FUNCTION * (B - A);
      case 2:
        return 3d * (B * B - A * A) * WEIGHT_FUNCTION / 2;
      case 3:
        return 3d * WEIGHT_FUNCTION * (Math.pow(B, 4) - Math.pow(A, 4)) / 4
            - 2d * WEIGHT_FUNCTION * (Math.pow(B, 3) - Math.pow(A, 3)) / 3
            + 5d * WEIGHT_FUNCTION * (B * B - A * A) / 2 - WEIGHT_FUNCTION * (B - A);
      case 4:
        return 7d * WEIGHT_FUNCTION * (Math.exp(3 * B) - Math.exp(3 * A)) / 3;
    }

    return 0;
  }

  /**
   * Вычисление значения интеграла методом левых прямоугольников.
   */
  public double getIntegralByLeftRectangleMethod() {
    return getIntegralByRectangleMethod(A);
  }

  /**
   * Вычисление значения интеграла методом правых прямоугольников.
   */
  public double getIntegralByRightRectangleMethod() {
    return getIntegralByRectangleMethod(A + H);
  }

  /**
   * Вычисление значения интеграла методом средних прямоугольников.
   */
  public double getIntegralByMiddleRectangleMethod() {
    return getIntegralByRectangleMethod(A + H / 2);
  }

  /**
   * Вычисление значения интеграла методом трапеций.
   */
  public double getIntegralByTrapezeMethod() {
    double integralSumValue =
        points.get(0).getFunctionValue() + points.get(M).getFunctionValue();

    for (int i = 1; i < M; i++) {
      integralSumValue += 2 * points.get(i).getFunctionValue();
    }

    return (B - A) * integralSumValue / (2 * M);
  }

  /**
   * Вычисление значения интеграла методом Симпсона.
   */
  public double getIntegralBySimpsonMethod() {
    double integralSumValue =
        simpsonPoints.get(0).getFunctionValue() + simpsonPoints.get(2 * M).getFunctionValue();

    for (int i = 1; i <= 2 * M - 1; i += 2) {
      integralSumValue += 4 * simpsonPoints.get(i).getFunctionValue();
    }

    for (int i = 2; i <= 2 * M - 2; i += 2) {
      integralSumValue += 2 * simpsonPoints.get(i).getFunctionValue();
    }

    return (B - A) * integralSumValue / (6 * M);
  }

  public double getAbsoluteErrorValue(double absoluteValue, double methodValue) {
    return Math.abs(absoluteValue - methodValue);
  }

  public double getTheoreticalErrorValue(MethodName methodName) {
    double coefficient = 1;
    switch (methodName) {
      case LEFT_RECTANGLE:
        coefficient = 1d / 2;
        break;
      case RIGHT_RECTANGLE:
        coefficient = 1d / 2;
        break;
      case MIDDLE_RECTANGLE:
        coefficient = 1d / 24;
        break;
      case TRAPEZE:
        coefficient = 1d / 12;
        break;
      case SIMPSON:
        coefficient = 1d / 2880;
        break;
    }

    return coefficient * getMdCoefficient(methodName) * (B - A);
  }

  private double getMdCoefficient(MethodName methodName) {
    int degree = 0;
    switch (methodName) {
      case LEFT_RECTANGLE:
        degree = 0;
        break;
      case RIGHT_RECTANGLE:
        degree = 0;
        break;
      case MIDDLE_RECTANGLE:
        degree = 1;
        break;
      case TRAPEZE:
        degree = 1;
        break;
      case SIMPSON:
        degree = 3;
        break;
    }

    degree += 1;
    return getMaximumOfDerivative(degree) * Math.pow(H, degree);
  }

  private double getMaximumOfDerivative(int degree) {
    switch (FUNCTION_NUMBER) {
      case 1:
        return 0;
      case 2:
        if (degree == 1) {
          return 3;
        }

        return 0;
      case 3:
        if (degree == 1) {
          return Math.max(getThirdFunctionFstDer(A), getThirdFunctionFstDer(B));
        }

        if (degree == 2) {
          return 18 * B - 4;
        }

        return 0;
      case 4:
        if (degree == 1) {
          return 3 * getFunctionValue(B);
        }

        if (degree == 2) {
          return 3 * 3 * getFunctionValue(B);
        }

        if (degree == 4) {
          return 3 * 3 * 3 * 3 * getFunctionValue(B);
        }
    }

    return 0;
  }

  private double getThirdFunctionFstDer(double value) {
    return 9 * Math.pow(value, 2) - 4 * value + 5;
  }

  /**
   * Вычисление значения интеграла методом прямоугольников.
   */
  private double getIntegralByRectangleMethod(Double alpha) {
    double integralSumValue = 0;
    for (int k = 1; k <= M; k++) {
      integralSumValue += getFunctionValue(alpha + (k - 1) * H);
    }

    return H * integralSumValue;
  }

  private double getFunctionValue(Double x) {
    switch (FUNCTION_NUMBER) {
      case 1:
        return 5d;
      case 2:
        return 3 * x;
      case 3:
        return 3 * Math.pow(x, 3) - 2 * Math.pow(x, 2) + 5 * x - 1;
      case 4:
        return 7 * Math.exp(3 * x);
    }

    return 0d;
  }
}
