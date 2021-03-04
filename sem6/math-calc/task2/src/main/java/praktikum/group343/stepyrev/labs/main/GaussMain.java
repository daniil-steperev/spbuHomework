package praktikum.group343.stepyrev.labs.main;

import java.util.List;
import praktikum.group343.stepyrev.labs.solver.GaussSolver;
import praktikum.group343.stepyrev.labs.util.MatrixUtil;

/**
 * Клаcc, который печатает на экран результат работы схемы Гаусса с выбором главного элемента по
 * столбцу.
 */
public class GaussMain {
  private static final List<List<Double>> matrix = MatrixUtil.matrix;
  private static final List<Double> rightPart = MatrixUtil.rightPart;
  private static List<Double> solution;

  private static GaussSolver gaussSolver;

  /**
   * Метод, который печатает на экран результат работы схемы Гаусса с выбором главного элемента по
   * столбцу.
   */
  public static void main(String[] args) {
    gaussSolver = new GaussSolver();
    System.out.println("Gaussian scheme with pivot element selection.");
    List<List<Double>> augmentedMatrix = MatrixUtil.uniteMatrices(matrix, rightPart);
    solution = gaussSolver.findSolutionByMainElement(augmentedMatrix);
    System.out.println("Found solution: ");
    MatrixUtil.printVector(solution);
  }

  public GaussSolver getSolver() {
    return gaussSolver;
  }

  public List<Double> getSolution() {
    return solution;
  }
}
