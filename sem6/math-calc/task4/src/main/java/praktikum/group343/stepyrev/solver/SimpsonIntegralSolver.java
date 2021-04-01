package praktikum.group343.stepyrev.solver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import praktikum.group343.stepyrev.util.FunctionPoint;
import praktikum.group343.stepyrev.util.FunctionSh;

/**
 * Класс, который реализует метод Симпсона вычисления интеграла.
 */
public class SimpsonIntegralSolver {

  private double A;
  private double B;
  private Double X;
  private Integer N;
  private Double H;
  private List<FunctionPoint> simpsonPoints;
  private boolean isCalculatedPrinted;
  private FunctionSh functionSh;

  public SimpsonIntegralSolver(boolean isCalculatedPrinted) {
    this.isCalculatedPrinted = isCalculatedPrinted;
    functionSh = new FunctionSh();
  }

  /** Метод, который возвращает значение интеграла. */
  public List<Double> getIntegralValue(Double a, Double b, Double x, Integer n) {
    this.A = a;
    this.B = b;
    this.X = x;
    this.N = n;
    this.H = Math.abs(b - a) / N;
    simpsonPoints = initializePoints(2 * N);
    return getIntegralCoefsBySimpsonMethod();
  }

  /** Метод, который инициализирует узлы. */
  private List<FunctionPoint> initializePoints(int N) {
    List<FunctionPoint> pointList = new LinkedList<>();

    if (isCalculatedPrinted) {
      System.out.println("Quadrature formula elements: ");
    }

    for (int i = 0; i < N + 1; i++) {
      double xI = A + i * H;
      double fX = functionSh.getValue(X, xI);
      pointList.add(new FunctionPoint(X, fX));

      if (isCalculatedPrinted) {
        System.out.println(String.format("xK = %f, f(xK) = %f", xI, fX));
      }
    }

    return pointList;
  }

  /**
   * Метод, который возвращает значения коэффициентов Ak, вычисленных методом Симпсона.
   */
  public List<Double> getIntegralCoefsBySimpsonMethod() {
    List<Double> coefs = new ArrayList<>();
    double div = (B - A) / (6 * N);;
    coefs.add(div * simpsonPoints.get(0).getFunctionValue());

    for (int i = 1; i <= 2 * N - 1; i++) {
      double innerDiv = i % 2 == 0 ? 2 : 4;
      coefs.add(innerDiv * div * simpsonPoints.get(i).getFunctionValue());
    }

    coefs.add(div * simpsonPoints.get(2 * N).getFunctionValue());
    return coefs;
  }
}
