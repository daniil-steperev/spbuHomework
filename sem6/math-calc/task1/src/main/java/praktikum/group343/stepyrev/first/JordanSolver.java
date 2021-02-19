package praktikum.group343.stepyrev.first;

import java.util.ArrayList;
import java.util.List;

/** Класс, который реализует схему Жордана. */
public class JordanSolver {

  private static int MATRIX_SIZE;

  public List<Double> performJordanScheme(List<List<Double>> matrix) {
    MATRIX_SIZE = matrix.size();
    subtractPrevious(matrix);
    return getSolutions(matrix);
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
    Double tmp;
    for (int k = 0; k < MATRIX_SIZE; k++) {
      divideByDiagonal(matrix, k);
      for (int i = 0; i < MATRIX_SIZE; i++) {
        if (i == k) {
          continue;
        }

        tmp = matrix.get(i).get(k);
        for (int j = k; j < MATRIX_SIZE + 1; j++) {
          Double curElem = matrix.get(i).remove(j); // a[i][j]
          Double prevElem = matrix.get(k).get(j); // a[k][j]
          curElem = curElem - prevElem * tmp;
          matrix.get(i).add(j, curElem);
        }
      }
    }
  }

  private List<Double> getSolutions(List<List<Double>> matrix) {
    List<Double> solutions = new ArrayList<>();
    for (int i = 0; i < MATRIX_SIZE; i++) {
      Double solution = matrix.get(i).get(MATRIX_SIZE);
      solutions.add(solution);
    }

    return solutions;
  }

}
