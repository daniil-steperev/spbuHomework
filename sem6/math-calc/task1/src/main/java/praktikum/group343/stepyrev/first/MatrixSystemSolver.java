package praktikum.group343.stepyrev.first;

import java.util.List;
import praktikum.group343.stepyrev.first.util.MatrixUtil;

/** Класс, предназначенный для решения систем с матрицами вида Ax = b. */
public class MatrixSystemSolver {
  private GaussSolver gaussSolver;

  /** Конструктор класса. */
  public MatrixSystemSolver() {
    this.gaussSolver = new GaussSolver();
  }

  /**
   * Метод, который находит решение системы вида Ax = b.
   *
   * @param matrix -- матрица А, заданная в виде массива Double
   * @param rightPart -- матрица b, определяющая правую часть; задана в виде масива Double
   * @return -- решение заданной системы
   */
  public List<Double> findSolution(List<List<Double>> matrix, List<Double> rightPart) {
    List<List<Double>> augmentedMatrix = MatrixUtil.uniteMatrices(matrix, rightPart); // дополненная матрица
    return gaussSolver.findSolution(augmentedMatrix);
  }
}
