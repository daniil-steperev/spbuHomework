package praktikum.group343.stepyrev.first.util;

import java.util.ArrayList;
import java.util.List;

/** Класс, предназначенный для подсчета нормы матрицы. */
public class NormCounter {

  /**
   * Метод, который высчитывает норму матрицы. Норма вычисляется как sqrt(SUM(aij * aij)).
   *
   * @param matrix -- заданная матрица
   * @return -- норму заданной матрицы
   */
  public static Double countNormMatrix(List<List<Double>> matrix) {
    List<Double> elements = new ArrayList<>();
    for (List<Double> matrixString : matrix) {
      elements.addAll(matrixString);
    }

    Double squareElementsSum = elements.stream().map(el -> el * el).reduce(0D, Double::sum);
    return Math.sqrt(squareElementsSum);
  }

  /**
   * Метод, который высчитывает норму вектора. Норма вычисляется как sqrt(SUM(aij * aij)).
   *
   * @param vector -- заданный вектор
   * @return -- норму заданного вектора
   */
  public static Double countNormVector(List<Double> vector) {
    Double squareElementsSum = vector.stream().map(el -> el * el).reduce(0D, Double::sum);
    return Math.sqrt(squareElementsSum);
  }

  /**
   * Метод, который высчитывает норму разности векторов. Норма вычисляется как sqrt(SUM(aij * aij)).
   *
   * @param fstVector -- заданный первый вектор
   * @param sndVector -- заданный второй вектор
   * @return -- норма разности заданных векторов
   */
  public static Double countNormVector(List<Double> fstVector, List<Double> sndVector) {
    List<Double> vectorDifference = new ArrayList<>();

    for (int i = 0; i < fstVector.size(); i++) {
      Double fstElem = fstVector.get(i);
      Double sndElem = sndVector.get(i);
      vectorDifference.add(fstElem - sndElem);
    }

    return countNormVector(vectorDifference);
  }
}
