package group343.stepyrev.lab;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/** Класс, который реализует интерполяционный многочлен. */
public class InterpolationPolynomial {
  private final Integer M;
  private final Double A;
  private final Double B;

  private Double X;
  private Integer N;

  private List<FunctionPoint> functionPoints;
  private List<FunctionPoint> sortedPoints;

  public InterpolationPolynomial(Integer m, Double a, Double b) {
    M = m;
    A = a;
    B = b;

    functionPoints = initializeFunctionPoints();
  }

  public List<FunctionPoint> getFunctionPoints() {
    return functionPoints;
  }

  /**
   * Метод, который заполняет таблицу значений.
   * @return - таблицу значений
   */
  private List<FunctionPoint> initializeFunctionPoints() {
    List<FunctionPoint> result = new LinkedList<>();

    for (int j = 0; j <= M; j++) {
      Double point = A + j * (B - A) / M;
      Double funcValue = getFunctionValue(point);
      result.add(new FunctionPoint(point, funcValue));
    }

    return result;
  }

  public List<FunctionPoint> sortFunctionPoints(Double x, Integer n) {
    X = x;
    N = n;
    sortedPoints = functionPoints;
    for (FunctionPoint point : sortedPoints) {
      point.setDistanceToPoint(x);
    }

    Comparator<FunctionPoint> comparator = Comparator.comparing(FunctionPoint::getDistanceToPoint);
    sortedPoints.sort(comparator);
    sortedPoints = sortedPoints.subList(0, N + 1);

    return sortedPoints;
  }

  public Double getPolynomialValue() {
    Double result = 0D;

    double numerator;
    double denominator;
    double curPoint;
    double curPointValue;
    for (int k = 0; k < sortedPoints.size(); k++) {
      curPoint = sortedPoints.get(k).getValue();
      curPointValue = sortedPoints.get(k).getFunctionValue();

      numerator = countPointComposition(k, X);
      denominator = countPointComposition(k, curPoint);
      result += curPointValue * numerator / denominator;
    }

    return result;
  }

  private Double countPointComposition(int k, double curPoint) {
    double result = 1d;
    double xI;

    for (int i = 0; i < functionPoints.size(); i++) {
      if (i == k) {
        continue;
      }

      xI = functionPoints.get(i).getValue();
      result *= (curPoint - xI);
    }

    return result;
  }


  /**
   * Метод, который возвращает значение исходной функции в точке.
   * @param point - точка, в которой требуется найти значени
   * @return - значение функции в точке
   */
  public double getFunctionValue(double point) {
    return Math.exp(-point) - Math.pow(point, 2) / 2; // exp(-x) - x^2 / 2
  }
}
