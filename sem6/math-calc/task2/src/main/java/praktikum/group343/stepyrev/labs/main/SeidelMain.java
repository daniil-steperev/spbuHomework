package praktikum.group343.stepyrev.labs.main;

import java.util.List;
import praktikum.group343.stepyrev.labs.solver.MatrixTransformSolver;
import praktikum.group343.stepyrev.labs.solver.SeidelSolver;
import praktikum.group343.stepyrev.labs.util.ErrorCounter;
import praktikum.group343.stepyrev.labs.util.MatrixUtil;
import praktikum.group343.stepyrev.labs.util.NormCounter;

/** Класс, который печатает решение, найденное методом Зейделя. */
public class SeidelMain {
  private static final List<List<Double>> matrix = MatrixUtil.matrix;
  private static final List<Double> rightPart = MatrixUtil.rightPart;
  private static final int solNumber = 7;

  private static MatrixTransformSolver transformSolver;
  private static List<List<Double>> matrixH;
  private static List<Double> vectorG;
  private static ErrorCounter errorCounter;

  private static SeidelSolver solver;
  private static List<Double> solution;

  private static Double actualError;

  /** Метод, который печатает решение, найденное методом Зейделя. */
  public static void main(String[] args) {
    System.out.println("Zeidel's method.");
    performMatrixTransformation();
    errorCounter = new ErrorCounter(matrixH, vectorG);

    solver = new SeidelSolver();
    solution = solver.foundSolution(matrixH, vectorG, solNumber);
    System.out.println(String.format("Found x%d: ", solNumber));
    MatrixUtil.printVector(solution);
    double pH = MatrixUtil.getMaxEigenvalue(matrixH);
    System.out.println(String.format("p(H) = %f", pH));
    System.out.println("");
  }

  /** Метод, который печатает фактическую погрешность. */
  public static void printActualError(List<Double> gaussSolution) {
    List<Double> difVector = MatrixUtil.divideVectors(solution, gaussSolution);
    actualError = NormCounter.countVectorNorm(difVector);
    System.out.println(String.format("Actual error for Seidel's method: %.14f", actualError));
  }

  /** Метод, в котором осуществляется преобразование Ax = b к виду x = Hx + g. */
  private static void performMatrixTransformation() {
    transformSolver = new MatrixTransformSolver();
    transformSolver.makeTransformation(matrix, rightPart);
    matrixH = transformSolver.getMatrixH();
    vectorG = transformSolver.getVectorG();
  }

  public static SeidelSolver getSolver() {
    return solver;
  }

  public static List<Double> getSolution() {
    return solution;
  }

  public static Double getActualError() {
    return actualError;
  }
}
