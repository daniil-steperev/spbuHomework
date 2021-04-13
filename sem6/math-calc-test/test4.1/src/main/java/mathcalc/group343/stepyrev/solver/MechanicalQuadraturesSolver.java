package mathcalc.group343.stepyrev.solver;

import java.util.List;
import mathcalc.group343.stepyrev.util.Function;
import mathcalc.group343.stepyrev.util.MatrixUtil;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

/**
 * Класс, который реализует метод механических квадратур.
 */
public class MechanicalQuadraturesSolver {

  private IntegralRectangleSolver integralSolver;
  private double A;
  private double B;
  private Double X;
  private Integer N;
  private Double H;
  private Double alpha;

  private Matrix matrixC;
  private Matrix matrixU;

  /**
   * Метод, который реализует метод механических квадратур.
   */
  public List<Double> getSolution(Double a, Double b, Double x, Integer n, Double alpha) {
    this.A = a;
    this.B = b;
    this.X = x;
    this.N = n;
    this.H = Math.abs(b - a) / N;
    this.alpha = alpha;

    integralSolver = new IntegralRectangleSolver(A, H, N, X);
    List<Double> coefs = integralSolver.getIntegralCoefs();
    matrixC = initMatrixC(coefs);

    Matrix leftPart = countLeftPart();
    Matrix rightPart = countRightPart();

    GaussSystemSolver solver = new GaussSystemSolver();
    Matrix augmentedMatrix = MatrixUtil.uniteMatrices(leftPart, rightPart);

    return solver.findSolution(augmentedMatrix);
  }

  /**
   * Метод, который инициализирует матрицу C, U.
   */
  private Matrix initMatrixC(List<Double> coefs) {
    Matrix matrix = new Basic2DMatrix(N, N);
    matrixU = new Basic2DMatrix(N, 1);

    for (int j = 0; j < N; j++) {
      double xJ = X + j * H;
      double sum = 0d;
      for (int k = 0; k < N; k++) {
        double dJK = j == k ? 1 : 0;
        double aK = coefs.get(k);
        double xK = X + k * H;
        double hJK = Function.getValue(xJ, xK);
        dJK -= aK * hJK;
        matrix.set(j, k, dJK);
        sum += dJK;
      }

      matrixU.set(j, 0, sum);
    }

    return matrix;
  }

  /** Метод, который вычисляет левую часть в уравнении (C*C + aE)Z = C*U. */
  private Matrix countLeftPart() {
    Matrix matrixCTranspose = matrixC.transpose();
    Matrix result = matrixCTranspose.multiply(matrixC);
    Matrix identityMatrix = MatrixUtil.getIdentityMatrix(matrixC.rows());
    result = result.add(identityMatrix.multiply(alpha));
    return result;
  }

  /** Метод, который вычисляет правую часть в уравнении (C*C + aE)Z = C*U. */
  private Matrix countRightPart() {
    Matrix matrixCTranspose = matrixC.transpose();
    return matrixCTranspose.multiply(matrixU);
  }
}
