package praktikum.group343.stepyrev.first.main;

import java.util.ArrayList;
import java.util.List;
import praktikum.group343.stepyrev.first.JordanSolver;
import praktikum.group343.stepyrev.first.util.MatrixUtil;

/** Класс, который печатает результат работы метода Жордана для нахождения обратной матрицы. */
public class MainJordanSolver {

  private static JordanSolver jordanSolver;
  private static final List<List<Double>> matrix = MatrixUtil.jordanMatrix;

  /** Метод, который печатает результат работы метода Жордана для нахождения обратной матрицы.  */
  public static void main(String[] args) {
    printIntroduction();
    List<List<Double>> inverseMatrix = findInverseMatrix(matrix);
    System.out.println("Inverse matrix: ");
    MatrixUtil.printMatrix(inverseMatrix);
  }

  /** Метод, который находит обратную матрицу для заданной. */
  public static List<List<Double>> findInverseMatrix(List<List<Double>> matrix) {
    jordanSolver = new JordanSolver();

    int matrixSize = matrix.size();
    List<Double> fstUnitVector = MatrixUtil.getUnitVector(matrixSize, 0);
    // Дополненная матрица
    List<List<Double>> augmentedMatrix = MatrixUtil.uniteMatrices(matrix, fstUnitVector);
    List<Double> fstSolutions = jordanSolver.performJordanScheme(augmentedMatrix);
    List<Double> tmpSolutions;
    List<Double> tmpUnitVector;
    List<List<Double>> inverseMatrix = new ArrayList<>();
    for (int i = 1; i < matrixSize; i++) {
      tmpUnitVector = MatrixUtil.getUnitVector(matrixSize, i);
      augmentedMatrix = MatrixUtil.uniteMatrices(matrix, tmpUnitVector);
      tmpSolutions = jordanSolver.performJordanScheme(augmentedMatrix);

      if (i == 1) {
        inverseMatrix = MatrixUtil.uniteVectors(fstSolutions, tmpSolutions);
      } else {
        inverseMatrix = MatrixUtil.uniteMatrices(inverseMatrix, tmpSolutions);
      }
    }

    return inverseMatrix;
  }

  /** Метод, который печатает вводные данные программы. */
  private static void printIntroduction() {
    System.out.println("Jordan's method for finding the inverse matrix.");
    System.out.println("Test matrix A: ");
    MatrixUtil.printMatrix(matrix);
  }
}
