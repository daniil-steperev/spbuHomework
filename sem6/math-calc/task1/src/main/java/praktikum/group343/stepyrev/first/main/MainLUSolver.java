package praktikum.group343.stepyrev.first.main;

import java.util.List;
import praktikum.group343.stepyrev.first.LUDecomposition;
import praktikum.group343.stepyrev.first.util.MatrixUtil;

/** Класс, печатающий результат работы метода LU-разложения для нахождения определителя матрицы. */
public class MainLUSolver {

  private static final List<List<Double>> matrix = MatrixUtil.jordanMatrix;

  private static LUDecomposition luDecomposition;

  /**
   * Метод, печатающий результат работы метода LU-разложения для нахождения определителя матрицы.
   */
  public static void main(String[] args) {
    luDecomposition = new LUDecomposition();
    printIntroduction();
    luDecomposition.performDecomposition(matrix);
    System.out.println("Matrix L: ");
    MatrixUtil.printMatrix(luDecomposition.getMatrixL());
    System.out.println("Matrix U: ");
    MatrixUtil.printMatrix(luDecomposition.getMatrixU());

    Double determinant = luDecomposition.countDeterminant();
    System.out.println(String.format("Determinant of matrix A: %f", determinant));
  }

  /** Метод, который печатает вводные данные программы. */
  private static void printIntroduction() {
    System.out.println("LU-decomposition for matrix. ");
    System.out.println("Test matrix A: ");
    MatrixUtil.printMatrix(matrix);
  }
}
