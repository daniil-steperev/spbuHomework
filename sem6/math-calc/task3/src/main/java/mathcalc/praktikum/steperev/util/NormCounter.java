package mathcalc.praktikum.steperev.util;

import org.la4j.Matrix;

/** Класс, предназначенный для подсчета нормы матрицы. */
public class NormCounter {

  /**
   * Метод, который высчитывает норму матрицы. Норма вычисляется как sqrt(SUM(aij * aij)).
   *
   * @param matrix -- заданная матрица
   * @return -- норму заданной матрицы
   */
  public static Double countNormMatrix(Matrix matrix) {
    double norm = 0d;
    int rowsSize = matrix.rows();
    int columnsSize = matrix.columns();
    for (int i = 0; i < rowsSize; i++) {
      for (int j = 0; j < columnsSize; j++) {
        norm += Math.pow(matrix.get(i, j), 2);
      }
    }

    return Math.sqrt(norm);
  }
}
