package praktikum.group343.stepyrev.util;

import java.util.List;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

public class MatrixUtil {

  private static PrettyPrinter printer = new PrettyPrinter(System.out);

  /**
   * Метод, которые объединяет объединяет матрицу A и b.
   *
   * @param matrix    -- матрица А
   * @param rightPart -- матрица b
   * @return -- объединенная матрица
   */
  public static Matrix uniteMatrices(
      Matrix matrix, Matrix rightPart) {
    Matrix copyMatrix = new Basic2DMatrix(matrix.rows(), matrix.columns() + 1);
    for (int i = 0; i < matrix.rows(); i++) {
      for (int j = 0; j < matrix.columns(); j++) {
        double value = matrix.get(i, j);
        copyMatrix.set(i, j, value);
      }
    }

    for (int i = 0; i < matrix.rows(); i++) {
      double elem = rightPart.get(0, i);
      copyMatrix.set(i, matrix.rows(), elem);
    }

    return copyMatrix;
  }

  /**
   * Метод, который печатает матрицу.
   */
  public static void printMatrix(Matrix matrix) {
    int size = matrix.rows();
    String[][] table = new String[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        table[i][j] = String.valueOf(matrix.get(i, j));
      }
    }

    printer.print(table);
    System.out.println("");
  }

  /**
   * Метод, который печатает вектор.
   */
  public static void printVector(Matrix vector) {
    int size = vector.columns();
    String[][] table = new String[size][1];
    for (int i = 0; i < size; i++) {
      table[i][0] = String.valueOf(vector.get(0, i));
    }

    printer.print(table);
    System.out.println("");
  }

}
