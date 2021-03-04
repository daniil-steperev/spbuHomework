package praktikum.group343.stepyrev.labs.util;

import java.util.List;

/** Класс, предназначенный для подсчета нормы матрицы. */
public class NormCounter {

  /**
   * Метод, который высчитывает норму матрицы. Норма вычисляется как max(SUM(|aij|)).
   *
   * @param matrix -- заданная матрица
   * @return -- норму заданной матрицы
   */
  public static Double countNorm(List<List<Double>> matrix) {
    double maxSum = 0d;
    for (List<Double> matrixString : matrix) {
      // считаем сумму элементов строки
      double sum = matrixString.stream().map(Math::abs).reduce(0d, Double::sum);
      if (sum > maxSum) {
        maxSum = sum;
      }
    }

    return maxSum;
  }

  /** Метод, который считает норму вектора. */
  public static Double countVectorNorm(List<Double> vector) {
    return vector.stream().map(Math::abs).max(Double::compare).get();
  }
}

