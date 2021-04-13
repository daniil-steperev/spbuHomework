package mathcalc.group343.stepyrev.util;

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
      double elem = rightPart.get(i, 0);
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

  /** Метод, который возвращает единичную матрицу заданного размера. */
  public static Matrix getIdentityMatrix(int size) {
    Matrix matrix = new Basic2DMatrix(size, size);
    for (int i = 0; i < size; i++) {
      matrix.set(i, i, 1);
    }

    return matrix;
  }

  public static Double countNorm(List<Double> fstVector, List<Double> sndVector) {
    Matrix fstMatrix = convertToMatrix(fstVector);
    Matrix sndMatrix = convertToMatrix(sndVector);
    Matrix difMatrix = fstMatrix.subtract(sndMatrix);
    return Math.abs(difMatrix.norm());
  }

  private static Matrix convertToMatrix(List<Double> vector) {
    Matrix matrix = new Basic2DMatrix(vector.size(), 1);
    for (int i = 0; i < vector.size(); i++) {
      matrix.set(i, 0, vector.get(i));
    }

    return matrix;
  }
}
