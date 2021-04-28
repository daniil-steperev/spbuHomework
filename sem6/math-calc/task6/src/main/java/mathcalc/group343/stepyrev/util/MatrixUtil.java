package mathcalc.group343.stepyrev.util;


import Jama.Matrix;

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
    Matrix copyMatrix = new Matrix(matrix.getRowDimension(), matrix.getColumnDimension() + 1);
    for (int i = 0; i < matrix.getRowDimension(); i++) {
      for (int j = 0; j < matrix.getColumnDimension(); j++) {
        double value = matrix.get(i, j);
        copyMatrix.set(i, j, value);
      }
    }

    for (int i = 0; i < matrix.getRowDimension(); i++) {
      double elem = rightPart.get(0, i);
      copyMatrix.set(i, matrix.getColumnDimension(), elem);
    }

    return copyMatrix;
  }

  /**
   * Метод, который печатает матрицу.
   */
  public static void printMatrix(Matrix matrix) {
    int size = matrix.getRowDimension();
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
    int size = vector.getColumnDimension();
    String[][] table = new String[size][1];
    for (int i = 0; i < size; i++) {
      table[i][0] = String.valueOf(vector.get(0, i));
    }

    printer.print(table);
    System.out.println("");
  }

}
