package praktikum.group343.stepyrev.first.main;

import java.util.List;
import praktikum.group343.stepyrev.first.GaussPivotSolver;
import praktikum.group343.stepyrev.first.util.MatrixUtil;

/**
 * Клаcc, который печатает на экран результат работы схемы Гаусса с выбором главного элемента по
 * столбцу.
 */
public class MainGaussPivotSolver {
  private static final List<List<Double>> matrix = MatrixUtil.jordanMatrix;
  private static final List<Double> rightPart = MatrixUtil.rightPartLU;

  private static GaussPivotSolver gaussSolver;

  /**
   * Метод, который печатает на экран результат работы схемы Гаусса с выбором главного элемента по
   * столбцу.
   */
  public static void main(String[] args) {
    gaussSolver = new GaussPivotSolver();
    printIntroduction();
    List<List<Double>> augmentedMatrix = MatrixUtil.uniteMatrices(matrix, rightPart);
    List<Double> solutions = gaussSolver.findSolutionByMainElement(augmentedMatrix);
    System.out.println("Found solutions: ");
    printSolutions(solutions);
  }

  /** Метод, который печатает вводные данные программы. */
  private static void printIntroduction() {
    System.out.println("Gaussian scheme with pivot element selection.");
    System.out.println("Test matrix A: ");
    MatrixUtil.printMatrix(matrix);
    System.out.println("Test right part: ");
    MatrixUtil.printVector(rightPart);
  }

  /** Метод, который печатает найденные решения. */
  private static void printSolutions(List<Double> solutions) {
    for (int i = 0; i < solutions.size(); i++) {
      System.out.println(String.format("x%d = %f", i + 1, solutions.get(i)));
    }
  }
}
