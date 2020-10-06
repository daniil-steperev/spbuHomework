package group343.stepyrev.lab3_1;

import group343.stepyrev.util.RootFinder;
import group343.stepyrev.util.Segment;
import group343.stepyrev.lab2_1.FunctionPoint;
import group343.stepyrev.lab2_2.NewtonPolynomial;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс, который решает задачу обратного интерполирования.
 */
public class ReverseInterpolator {

  private final Integer M;
  private final Double A;
  private final Double B;
  private final Double EPSILON;

  private Double fX;
  private Integer N;

  private NewtonPolynomial polynomial;
  private RootFinder rootFinder;

  private List<FunctionPoint> functionPoints;
  private List<FunctionPoint> reversedTable;

  private Integer numberOfSegments = 1000;

  public ReverseInterpolator(Integer m, Double a, Double b) {
    M = m;
    A = a;
    B = b;
    EPSILON = Math.pow(10, -12);

    functionPoints = initializeFunctionPoints();
    reversedTable = new LinkedList<>();
    polynomial = new NewtonPolynomial(M, functionPoints);
  }

  public Double findSolutionByReverseTable(Double fx, int n) {
    this.fX = fx;
    this.N = n;

    reverseTable();
    polynomial = new NewtonPolynomial(M, reversedTable);
    polynomial.sortFunctionPoints(fx, n);

    return polynomial.getPolynomialValue();
  }

  private void reverseTable() {
    reversedTable = new LinkedList<>();

    for (FunctionPoint point : functionPoints) {
      double value = point.getValue();
      double functionValue = point.getFunctionValue();

      reversedTable.add(new FunctionPoint(functionValue, value));
    }
  }

  public List<Double> findSolutionByRoot(Double fx, int n) {
    this.fX = fx;
    this.N = n;

    polynomial = new NewtonPolynomial(M, functionPoints);
    polynomial.sortFunctionPoints(fx, n);
    rootFinder = new RootFinder(A, B, EPSILON, polynomial, fx);

    return getRoots();
  }

  public List<FunctionPoint> getFunctionPoints() {
    return functionPoints;
  }

  public List<FunctionPoint> getSortedPoints() {
    return polynomial.getSortedPoints();
  }

  private List<Double> getRoots() {
    List<Double> roots = new LinkedList<>();
    List<Segment> segments = rootFinder.separateRoots(numberOfSegments);

    List<Double> segmentRoots;
    for (Segment segment : segments) {
      segmentRoots = rootFinder.findRootByBisectionMethod(segment);
      roots.addAll(segmentRoots);
    }

    return roots;
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

  public Double getFunctionValue(Double point) {
    return Math.exp(-point) - Math.pow(point, 2) / 2; // exp(-x) - x^2 / 2
  }

}
