package group343.stepyrev.lab2_2;

import group343.stepyrev.lab2_1.FunctionPoint;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Класс, который реализует интерполяионный многочлен Ньютона.
 */
public class NewtonPolynomial {

  private final Integer M;
  private final Double A;
  private final Double B;

  private Double X;
  private Integer N;

  private List<FunctionPoint> functionPoints;
  private List<FunctionPoint> sortedPoints;
  private Map<Integer, List<NewtonCoef>> newtonCoefficients;

  public NewtonPolynomial(Integer m, Double a, Double b) {
    M = m;
    A = a;
    B = b;

    functionPoints = initializeFunctionPoints();
    newtonCoefficients = new HashMap<>();
  }

  public NewtonPolynomial(Integer m, List<FunctionPoint> functionPoints) {
    M = m;
    A = 1D;
    B = 2D;
    this.functionPoints = functionPoints;
    newtonCoefficients = new HashMap<>();
  }

  public List<FunctionPoint> getFunctionPoints() {
    return functionPoints;
  }

  public List<FunctionPoint> getSortedPoints() {
    return sortedPoints;
  }

  /**
   * Метод, который заполняет таблицу значений.
   *
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
    findNewtonCoefficients();

    return sortedPoints;
  }

  private void findNewtonCoefficients() {
    List<NewtonCoef> coefficients = new LinkedList<>();
    newtonCoefficients
        .put(0,
            Collections.singletonList(new NewtonCoef(0, sortedPoints.get(0).getFunctionValue())));

    for (int degree = 1; degree < N + 1; degree++) {
      for (int i = 0; i + degree < N + 1; i++) {
        double elemValue = countNewtonCoefficient(i, i + degree);
        coefficients.add(new NewtonCoef(i,
            elemValue)); // первая координата точки - номер первого элемента, вторая - значения коэффициента
      }
      newtonCoefficients.put(degree, coefficients);

      coefficients = new LinkedList<>();
    }
  }

  private double countNewtonCoefficient(int fstNumber, int lastNumber) {
    int degree = lastNumber - fstNumber;
    double fstElem;
    double sndElem;
    double denominator;

    if (degree == 1) {
      fstElem = sortedPoints.get(lastNumber).getFunctionValue();
      sndElem = sortedPoints.get(fstNumber).getFunctionValue();
      denominator =
          sortedPoints.get(lastNumber).getValue() - sortedPoints.get(fstNumber).getValue();
    } else {
      List<NewtonCoef> thisDegreeCoefs = newtonCoefficients.get(degree - 1);

      fstElem = getFunctionPointValue(thisDegreeCoefs, fstNumber + 1);
      sndElem = getFunctionPointValue(thisDegreeCoefs, fstNumber);
      denominator = sortedPoints.get(lastNumber).getValue() - sortedPoints.get(fstNumber).getValue();
    }

    return (fstElem - sndElem) / denominator;
  }

  private double getFunctionPointValue(List<NewtonCoef> points, int index) {
    for (NewtonCoef point : points) {
      if (point.getNumber() == index) { // FIXME
        return point.getValue();
      }
    }

    return 0;
  }

  public Double getPolynomialValue() {
    double result = 0D;

    double aK;
    double xComposition;
    for (int k = 0; k < sortedPoints.size(); k++) {
      aK = getNewtonCoefficient(k);
      xComposition = getXComposition(k);
      result += aK * xComposition;
    }

    return result;
  }

  public Double getPolynomialValue(double x) {
    sortFunctionPoints(x, N);
    return getPolynomialValue();
  }

  /**
   * Метод, который вычисляет произведение иксов: (x - x0)(x - x1) ... (x - x(K-1))
   *
   * @param k - номер последнего x
   * @return - произведение иксов
   */
  private double getXComposition(int k) {
    double result = 1;
    double xK;
    for (int i = 0; i < k; i++) {
      xK = sortedPoints.get(i).getValue();
      result *= (X - xK);
    }

    return result;
  }

  private double getNewtonCoefficient(int k) {
    return newtonCoefficients.get(k).get(0).getValue();
  }

  /**
   * Метод, который возвращает значение исходной функции в точке.
   *
   * @param point - точка, в которой требуется найти значени
   * @return - значение функции в точке
   */
  public double getFunctionValue(double point) {
    return Math.exp(-point) - Math.pow(point, 2) / 2; // exp(-x) - x^2 / 2
  }

}
