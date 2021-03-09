package praktikum.group343.stepyrev.labs.main;

import java.util.List;
import praktikum.group343.stepyrev.labs.solver.MatrixTransformSolver;
import praktikum.group343.stepyrev.labs.solver.UpperRelaxationSolver;
import praktikum.group343.stepyrev.labs.util.ErrorCounter;
import praktikum.group343.stepyrev.labs.util.MatrixUtil;
import praktikum.group343.stepyrev.labs.util.NormCounter;

/** Класс, который печатает решение, найденное методом верхней релаксации. */
public class UpperRelaxationMain {
  private static final List<List<Double>> matrix = MatrixUtil.matrix;
  private static final List<Double> rightPart = MatrixUtil.rightPart;
  private final static int solutionNumber = 7;

  private static List<List<Double>> matrixH;
  private static List<Double> vectorG;
  private static List<Double> solution;

  private static UpperRelaxationSolver solver;
  private static MatrixTransformSolver transformSolver;
  private static ErrorCounter errorCounter;

  private static double actualError;

  /** Метод, который печатает решение, найденное методом верхней релаксации. */
  public static void main(String[] args) {
    System.out.println("Upper relaxation method.");
    performMatrixTransformation();
    errorCounter = new ErrorCounter(matrixH, vectorG);

    solver = new UpperRelaxationSolver();
    solution = solver.findSolution(matrixH, vectorG, solutionNumber);
    System.out.println(String.format("Found x%d solution: ", solutionNumber));
    MatrixUtil.printVector(solution);
  }

  /** Метод, который печатает фактическую погрешность. */
  public static void printActualError(List<Double> gaussSolution) {
    List<Double> difVector = MatrixUtil.divideVectors(solution, gaussSolution);
    actualError = NormCounter.countVectorNorm(difVector);
    System.out.println(String.format("Actual error for upper relaxation method: %.14f", actualError));
  }

  public static List<Double> getSolution() {
    return solution;
  }

  public static UpperRelaxationSolver getSolver() {
    return solver;
  }

  public static double getActualError() {
    return actualError;
  }

  /** Метод, в котором осуществляется преобразование Ax = b к виду x = Hx + g. */
  private static void performMatrixTransformation() {
    transformSolver = new MatrixTransformSolver();
    transformSolver.makeTransformation(matrix, rightPart);
    matrixH = transformSolver.getMatrixH();
    vectorG = transformSolver.getVectorG();
  }
}
