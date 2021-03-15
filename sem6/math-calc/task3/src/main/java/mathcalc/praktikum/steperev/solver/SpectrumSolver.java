package mathcalc.praktikum.steperev.solver;

import mathcalc.praktikum.steperev.util.MatrixUtil;
import org.la4j.Matrix;

/** Класс, который реализует нахождение противоположной границы спектра. */
public class SpectrumSolver {
  private double lambda;
  private Matrix matrixA;

  private double oppositeEigenvalue;
  private Matrix oppositeEigenvector;

  /** Метод, который реализует нахождение противоположной границы спектра. */
  public void performProcess(Matrix matrixA, Matrix eigenvectors, double epsilon, double lambda) {
    this.lambda = lambda;
    this.matrixA = matrixA;
    SedateSolver solver = new SedateSolver();
    Matrix matrixB = getMatrixB();
    solver.performProcess(matrixB, eigenvectors, epsilon, lambda);
    oppositeEigenvalue = solver.getMaxEigenvalue() + lambda;
    oppositeEigenvector = solver.getMaxEigenvalueVector();
  }

  public double getOppositeEigenvalue() {
    return oppositeEigenvalue;
  }

  public Matrix getOppositeEigenvector() {
    return oppositeEigenvector;
  }

  /** Метод, который строит матрицу B. */
  private Matrix getMatrixB() {
    Matrix matrix = matrixA.copy();
    Matrix matrixE = MatrixUtil.getUnitMatrix();
    return matrix.subtract(matrixE.multiply(lambda));
  }

}
