package praktikum.group343.stepyrev.solver;

import java.util.List;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import praktikum.group343.stepyrev.util.FunctionF;
import praktikum.group343.stepyrev.util.FunctionSh;
import praktikum.group343.stepyrev.util.MatrixUtil;

/**
 * Класс, который реализует метод механических квадратур.
 */
public class MechanicalQuadraturesSolver {

  private SimpsonIntegralSolver integralSolver;
  private double A;
  private double B;
  private Double X;
  private Integer N;
  private Double H;
  private boolean isCalculationsPrinted;

  private Matrix matrixG;
  private Matrix matrixD;
  private Matrix matrixZ;

  private FunctionSh function;

  public MechanicalQuadraturesSolver(boolean isCalculationsPrinted) {
    this.isCalculationsPrinted = isCalculationsPrinted;
    function = new FunctionSh();
  }

  /**
   * Метод, который реализует метод механических квадратур.
   */
  public Double getSolution(Double a, Double b, Double x, Integer n) {
    this.A = a;
    this.B = b;
    this.X = x;
    this.N = n;
    this.H = Math.abs(b - a) / N;

    if (isCalculationsPrinted) {
      System.out.println(String.format("Number of quadrature formula elements: %d.", N));
    }

    integralSolver = new SimpsonIntegralSolver(isCalculationsPrinted);
    List<Double> coefs = integralSolver.getIntegralValue(A, B, X, N);
    matrixD = initMatrixD(coefs);
    matrixG = initMatrixG();

    GaussSystemSolver solver = new GaussSystemSolver();
    Matrix augmentedMatrix = MatrixUtil.uniteMatrices(matrixD, matrixG);
    List<Double> systemSolve = solver.findSolution(augmentedMatrix);

    if (isCalculationsPrinted) {
      System.out.println("Coefficients of quadrature formula: ");
      for (int i = 0; i < systemSolve.size(); i++) {
        System.out.println(String.format("A%d = %f", i + 1, systemSolve.get(i)));
      }

      if (N < 10) {
        System.out.println("Matrix D:");
        MatrixUtil.printMatrix(matrixD);
        System.out.println("Vector G:");
        MatrixUtil.printVector(matrixG);
      }
    }

    return getSolution(coefs, systemSolve);
  }

  /**
   * Метод, который возвращает решение u^n(x).
   */
  private Double getSolution(List<Double> coefs, List<Double> systemSolve) {
    double fX = FunctionF.getValue(X);
    double solve = 0;

    for (int k = 0; k < N; k++) {
      double cK = systemSolve.get(k);
      double aK = coefs.get(k);
      double xK = X + k * H;
      double hK = function.getValue(X, xK);
      solve += aK * hK * cK;
    }

    return solve * (-0.5) + fX;
  }

  /**
   * Метод, который инициализирует матрицу G.
   */
  private Matrix initMatrixG() {
    Matrix matrix = new Basic2DMatrix(1, N);

    for (int i = 0; i < N; i++) {
      double xI = A + i * H;
      double fXi = FunctionF.getValue(xI);
      matrix.set(0, i, fXi);
    }

    return matrix;
  }

  /**
   * Метод, который инициализирует матрицу D.
   */
  private Matrix initMatrixD(List<Double> coefs) {
    Matrix matrix = new Basic2DMatrix(N, N);

    for (int j = 0; j < N; j++) {
      double xJ = X + j * H;
      for (int k = 0; k < N; k++) {
        double dJK = j == k ? 1 : 0;
        double aK = coefs.get(k);
        double xK = X + k * H;
        double hJK = function.getValue(xJ, xK);
        dJK -= aK * hJK;
        matrix.set(j, k, dJK);
      }
    }

    return matrix;
  }
}
