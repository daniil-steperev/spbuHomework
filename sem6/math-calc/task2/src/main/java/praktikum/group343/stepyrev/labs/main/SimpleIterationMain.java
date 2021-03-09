package praktikum.group343.stepyrev.labs.main;

import java.util.List;
import praktikum.group343.stepyrev.labs.solver.MatrixTransformSolver;
import praktikum.group343.stepyrev.labs.solver.SimpleIterationSolver;
import praktikum.group343.stepyrev.labs.util.ErrorCounter;
import praktikum.group343.stepyrev.labs.util.MatrixUtil;
import praktikum.group343.stepyrev.labs.util.NormCounter;

/** Класс, который печатает результат решения, найденного методом простой итерации. */
public class SimpleIterationMain {
  private static final List<List<Double>> matrix = MatrixUtil.matrix;
  private static final List<Double> rightPart = MatrixUtil.rightPart;

  private static List<List<Double>> matrixH;
  private static List<Double> vectorG;
  private static List<Double> solution;

  private static SimpleIterationSolver iterationSolver;
  private static MatrixTransformSolver transformSolver;
  private static ErrorCounter errorCounter;

  private static double actualError;

  private static int solutionNumber = 7;

  /** Метод, который печатает результат решения, найденного методом простой итерации. */
  public static void main(String[] args) {
    System.out.println("Simple iteration method finder.");
    performMatrixTransformation();
    errorCounter = new ErrorCounter(matrixH, vectorG);

    iterationSolver = new SimpleIterationSolver();
    solution = iterationSolver.foundSolution(matrixH, vectorG, solutionNumber);
    System.out.println(String.format("Found x%d: ", solutionNumber));
    MatrixUtil.printVector(solution);
  }

  /** Метод, который печатает погрешности. */
  public static void printErrors(List<Double> gaussSolution) {
    printActualError(gaussSolution);
    printAprioryError();
    printAposterioriError();
  }

  /** Метод, который печатает фактическую погрешность. */
  private static void printActualError(List<Double> gaussSolution) {
    List<Double> difVector = MatrixUtil.divideVectors(solution, gaussSolution);
    actualError = NormCounter.countVectorNorm(difVector);
    System.out.println(String.format("Actual error for simple iteration method: %.14f", actualError));
  }

  /** Метод, который печатает априорную оценку. */
  private static void printAprioryError() {
    double aprioryError = errorCounter.countAprioryError(solutionNumber);
    System.out.println(
        String.format(
            "A priory error for x%d solution for simple iteration method: %f",
            solutionNumber, aprioryError));
  }

  /** Метод, который печатает апостериорную оценку. */
  private static void printAposterioriError() {
    List<Double> curSol = iterationSolver.foundSolution(matrixH, vectorG, solutionNumber);
    List<Double> prevSol = iterationSolver.foundSolution(matrixH, vectorG, solutionNumber - 1);
    double aposterioryError = errorCounter.countAposterioryError(curSol, prevSol);
    System.out.println(
        String.format(
            "A posteriori error for x%d solution for simple iteration method: %f",
            solutionNumber, aposterioryError));
  }

  /** Метод, в котором осуществляется преобразование Ax = b к виду x = Hx + g. */
  private static void performMatrixTransformation() {
    transformSolver = new MatrixTransformSolver();
    transformSolver.makeTransformation(matrix, rightPart);
    matrixH = transformSolver.getMatrixH();
    vectorG = transformSolver.getVectorG();
  }

  public SimpleIterationSolver getSolver() {
    return iterationSolver;
  }

  public List<Double> getSolution() {
    return solution;
  }

  public static double getActualError() {
    return actualError;
  }
}
