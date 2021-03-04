package praktikum.group343.stepyrev.labs.main;

import java.util.List;
import praktikum.group343.stepyrev.labs.solver.LusternikSolver;
import praktikum.group343.stepyrev.labs.solver.MatrixTransformSolver;
import praktikum.group343.stepyrev.labs.solver.SimpleIterationSolver;
import praktikum.group343.stepyrev.labs.util.MatrixUtil;
import praktikum.group343.stepyrev.labs.util.NormCounter;

/** Класс, который печатает результат работым метода Люстерника. */
public class LusternikMain {

  private static final List<List<Double>> matrix = MatrixUtil.matrix;
  private static final List<Double> rightPart = MatrixUtil.rightPart;

  private static List<List<Double>> matrixH;
  private static List<Double> vectorG;
  private static SimpleIterationSolver iterationSolver;
  private static MatrixTransformSolver transformSolver;

  private static double actualError;

  private static int solutionNumber = 7;
  private static List<Double> solution;
  private static LusternikSolver solver;

  /** Метод, который печатает результат работым метода Люстерника. */
  public static void main(String[] args) {
    System.out.println("Lusternik's method.");
    performMatrixTransformation();
    iterationSolver = new SimpleIterationSolver();
    solver = new LusternikSolver();
    solution = solver.findSolution(matrixH, vectorG, iterationSolver, solutionNumber);
    System.out.println("Found Lusternik solution: ");
    MatrixUtil.printVector(solution);
  }

  /** Метод, который печатает фактическую погрешность. */
  public static void printActualError(List<Double> gaussSolution) {
    List<Double> difVector = MatrixUtil.divideVectors(solution, gaussSolution);
    actualError = NormCounter.countVectorNorm(difVector);
    System.out.println(String.format("Actual error for Lusternik's method: %.14f", actualError));
  }

  /** Метод, в котором осуществляется преобразование Ax = b к виду x = Hx + g. */
  private static void performMatrixTransformation() {
    transformSolver = new MatrixTransformSolver();
    transformSolver.makeTransformation(matrix, rightPart);
    matrixH = transformSolver.getMatrixH();
    vectorG = transformSolver.getVectorG();
  }

  public LusternikSolver getSolver() {
    return solver;
  }

  public static List<Double> getSolution() {
    return solution;
  }

  public static double getActualError() {
    return actualError;
  }
}
