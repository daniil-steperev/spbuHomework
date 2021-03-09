package praktikum.group343.stepyrev.labs.main;

import java.util.List;
import praktikum.group343.stepyrev.labs.solver.MatrixTransformSolver;
import praktikum.group343.stepyrev.labs.util.MatrixUtil;
import praktikum.group343.stepyrev.labs.util.NormCounter;

/** Клаcc, который печатает на экран результат преобразования Ax = b к виду x = Hx + g. */
public class MatrixTransformMain {
  private static final List<List<Double>> matrix = MatrixUtil.matrix;
  private static final List<Double> rightPart = MatrixUtil.rightPart;

  private static MatrixTransformSolver solver;

  /** Метод, который печатает на экран результат преобразования Ax = b к виду x = Hx + g. */
  public static void main(String[] args) {
    System.out.println("Matrix transformer from Ax = b to x = Hx + g.");
    solver = new MatrixTransformSolver();
    solver.makeTransformation(matrix, rightPart);
    List<List<Double>> matrixH = solver.getMatrixH();
    System.out.println("Matrix H: ");
    MatrixUtil.printMatrix(matrixH);

    List<Double> vectorG = solver.getVectorG();
    System.out.println("Vector G: ");
    MatrixUtil.printVector(vectorG);

    double matrixHNorm = NormCounter.countNorm(matrixH);
    System.out.println(String.format("Matrix H norm: %f", matrixHNorm));
  }

  public MatrixTransformSolver getSolver() {
    return solver;
  }
}
