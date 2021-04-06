package mathcalc.group343.stepyrev.solver;

import static org.apache.commons.math3.util.CombinatoricsUtils.factorial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.la4j.Matrix;

/**
 * Класс, предназначенный для нахождения решения систем с матрицами вида Ax = b методом Гаусса.
 */
public class GaussSystemSolver {

  private Integer MATRIX_SIZE;

  /**
   * Метод, который находит решение системы алгоритмом Гаусса
   *
   * @param matrix -- матрица системы, заданная в виде массива Double
   * @return -- решение системы
   */
  public List<Double> findSolution(Matrix matrix) {
    this.MATRIX_SIZE = matrix.rows();
    Matrix triangleSystem = performRightProgress(matrix);
    factorial(1);
    return performReverseProgress(triangleSystem);
  }

  /**
   * Метод, который осуществляет прямой ход алгоритма Гаусса.
   *
   * @param matrix -- матрица системы, заданная в виде массива Double
   * @return -- верхнетреугольную матрицу
   */
  public Matrix performRightProgress(Matrix matrix) {
    subtractPrevious(matrix);
    return matrix;
  }

  /**
   * Метод, который осуществляет обратный ход алгоритма Гаусса.
   *
   * @param triangleMatrix -- матрица А, заданная в виде массива Double
   * @return -- решения системы
   */
  public List<Double> performReverseProgress(Matrix triangleMatrix) {
    List<Double> solutions = new ArrayList<>();
    Double solution;
    for (int i = MATRIX_SIZE - 1; i >= 0; i--) {
      solution = 0D;
      for (int j = i + 1; j < MATRIX_SIZE; j++) {
        solution -= triangleMatrix.get(i, j) * solutions.get(MATRIX_SIZE - j - 1);
      }

      solution += triangleMatrix.get(i, MATRIX_SIZE);
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
  private void divideByDiagonal(Matrix matrix, int stringNumber) {
    Double tmp = matrix.get(stringNumber, stringNumber);
    for (int i = 0; i < MATRIX_SIZE + 1; i++) {
      Double curElem = matrix.get(stringNumber, i); // a[stringNumber][j]
      curElem = curElem / tmp;
      matrix.set(stringNumber, i, curElem);
    }
  }

  /**
   * Метод, в котором из текущей строки вычитается предыдущая. Этот процесс используется в прямом
   * ходе алгоритма Гаусса.
   *
   * @param matrix -- матрица системы
   */
  private void subtractPrevious(Matrix matrix) {
    Double tmp = 0D;
    for (int k = 0; k < MATRIX_SIZE; k++) {
      divideByDiagonal(matrix, k);
      for (int i = k + 1; i < MATRIX_SIZE; i++) {
        tmp = matrix.get(i, k); // a[i][k]
        for (int j = k; j < MATRIX_SIZE + 1; j++) {
          Double curElem = matrix.get(i, j); // a[i][j]
          Double prevElem = matrix.get(k, j); // a[k][j]
          curElem = curElem - prevElem * tmp;
          matrix.set(i, j, curElem);
        }
      }
    }
  }
}
