package praktikum.group343.stepyrev.labs.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Класс, который реализует схему Гаусса с выбором главного элемента. */
public class GaussSolver {

  private int MATRIX_SIZE;

  /**
   * Метод, который находит решение методом Гаусса с выбором главного элемента. Выбор главного
   * элемента производится по столбцу.
   *
   * @param matrix -- матрица системы, заданная в виде массива Double
   * @return -- решение системы
   */
  public List<Double> findSolutionByMainElement(List<List<Double>> matrix) {
    MATRIX_SIZE = matrix.size();
    List<List<Double>> triangleSystem = performRightProgress(matrix);
    return performReverseProgress(triangleSystem);
  }

  /** Метод, который сортирует строки по главному элементу в столбце. */
  private List<List<Double>> sortMatrixByColumnElement(
      List<List<Double>> matrix, int columnNumber) {
    int maxStringNumber = 0;
    Double max = matrix.get(0).get(columnNumber);
    for (int i = 1; i < matrix.size(); i++) {
      Double curValue = matrix.get(i).get(columnNumber);
      if (curValue > max) {
        System.out.println(
            String.format(
                "Element of %d column %d string is less than element of %d string.",
                columnNumber + 1, maxStringNumber + 1, i + 1));

        List<Double> curString = matrix.get(i);
        List<Double> previousString = matrix.remove(maxStringNumber);
        matrix.add(maxStringNumber, curString);
        matrix.remove(i);
        matrix.add(i - 1, previousString);
        max = curValue;
        maxStringNumber = i;
      }
    }

    return matrix;
  }

  /**
   * Метод, который осуществляет прямой ход алгоритма Гаусса.
   *
   * @param matrix -- матрица системы, заданная в виде массива Double
   * @return -- верхнетреугольную матрицу
   */
  private List<List<Double>> performRightProgress(List<List<Double>> matrix) {
    subtractPrevious(matrix);
    return matrix;
  }

  /**
   * Метод, который осуществляет обратный ход алгоритма Гаусса.
   *
   * @param triangleMatrix -- матрица А, заданная в виде массива Double
   * @return -- решения системы
   */
  private List<Double> performReverseProgress(List<List<Double>> triangleMatrix) {
    List<Double> solutions = new ArrayList<>();
    Double solution;
    for (int i = MATRIX_SIZE - 1; i >= 0; i--) {
      solution = 0D;
      for (int j = i + 1; j < MATRIX_SIZE; j++) {
        solution -= triangleMatrix.get(i).get(j) * solutions.get(MATRIX_SIZE - j - 1);
      }

      solution += triangleMatrix.get(i).get(MATRIX_SIZE);
      solutions.add(solution);
    }

    Collections.reverse(solutions); // меняем местами x1 и x2
    return solutions;
  }

  /**
   * Метод, в котором каждый элемент матрицы выше диагонали делится на диагональный элемент. Этот
   * процесс используется в прямом ходе алгоритма Гаусса.
   *
   * @param matrix -- матрица системы
   */
  private void divideByDiagonal(List<List<Double>> matrix, int stringNumber) {
    Double tmp = matrix.get(stringNumber).get(stringNumber);
    for (int i = 0; i < MATRIX_SIZE + 1; i++) {
      Double curElem = matrix.get(stringNumber).remove(i); // a[stringNumber][j]
      curElem = curElem / tmp;
      matrix.get(stringNumber).add(i, curElem);
    }
  }

  /**
   * Метод, в котором из текущей строки вычитается предыдущая. Этот процесс используется в прямом
   * ходе алгоритма Гаусса.
   *
   * @param matrix -- матрица системы
   */
  private void subtractPrevious(List<List<Double>> matrix) {
    Double tmp = 0D;
    for (int k = 0; k < MATRIX_SIZE; k++) {
      sortMatrixByColumnElement(matrix, k);
      divideByDiagonal(matrix, k);
      for (int i = k + 1; i < MATRIX_SIZE; i++) {
        tmp = matrix.get(i).get(k); // a[i][k]
        for (int j = k; j < MATRIX_SIZE + 1; j++) {
          Double curElem = matrix.get(i).remove(j); // a[i][j]
          Double prevElem = matrix.get(k).get(j); // a[k][j]
          curElem = curElem - prevElem * tmp;
          matrix.get(i).add(j, curElem);
        }
      }
    }
  }
}
