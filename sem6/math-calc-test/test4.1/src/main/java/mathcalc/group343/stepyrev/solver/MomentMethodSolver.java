package mathcalc.group343.stepyrev.solver;

import java.util.List;
import mathcalc.group343.stepyrev.util.FunctionA;
import mathcalc.group343.stepyrev.util.MatrixUtil;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

public class MomentMethodSolver {

  private IntegralRectangleSolver integralSolver;
  private double A;
  private double B;
  private Double X;
  private Integer N;
  private Double H;
  private Double alpha;

  private Matrix matrixB;
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
    matrixB = initMatrixB();

    Matrix leftPart = countLeftPart();
    Matrix rightPart = countRightPart();

    GaussSystemSolver solver = new GaussSystemSolver();
    Matrix augmentedMatrix = MatrixUtil.uniteMatrices(leftPart, rightPart);

    return solver.findSolution(augmentedMatrix);
  }

  /**
   * Метод, который инициализирует матрицу B, U.
   */
  private Matrix initMatrixB() {
    Matrix matrix = new Basic2DMatrix(N, N);
    matrixU = new Basic2DMatrix(N, 1);

    for (int j = 0; j < N; j++) {
      double xJ = X + j * H;
      List<Double> coefs = integralSolver.getIntegralInMomentCoefs(j);
      double sum = 0d;
      for (int k = 0; k < N; k++) {
        double dJK = j == k ? 1 : 0;
        double aK = coefs.get(k);
        double xK = X + k * H;
        double hJK = FunctionA.getValue(xK);
        dJK -= aK * hJK;
        matrix.set(j, k, dJK);
        sum += dJK;
      }

      matrixU.set(j, 0, sum);
    }

    return matrix;
  }

  /** Метод, который вычисляет левую часть в уравнении (B*B + aE)C = B*U. */
  private Matrix countLeftPart() {
    Matrix matrixCTranspose = matrixB.transpose();
    Matrix result = matrixCTranspose.multiply(matrixB);
    Matrix identityMatrix = MatrixUtil.getIdentityMatrix(matrixB.rows());
    result = result.add(identityMatrix.multiply(alpha));
    return result;
  }

  /** Метод, который вычисляет правую часть в уравнении (B*B + aE)C = B*U. */
  private Matrix countRightPart() {
    Matrix matrixBTranspose = matrixB.transpose();
    return matrixBTranspose.multiply(matrixU);
  }

}
