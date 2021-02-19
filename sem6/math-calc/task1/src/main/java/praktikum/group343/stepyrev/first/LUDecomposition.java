package praktikum.group343.stepyrev.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Класс, который реализует LU-разложение заданной матрицы. */
public class LUDecomposition {
  private List<List<Double>> matrixL;
  private List<List<Double>> matrixU;
  private List<List<Double>> matrixA;

  private final int MATRIX_SIZE = 3;

  /** Метод, который производит LU-разложение заданной матрицы. */
  public void performDecomposition(List<List<Double>> matrix) {
    matrixA = matrix;
    matrixL = initMatrix();
    matrixU = initMatrix();

    for (int i = 0; i < MATRIX_SIZE; i++) {
      for (int j = i; j < MATRIX_SIZE; j++) {
        Double newL = matrixA.get(j).get(i) - countSum(i, j);
        matrixL.get(j).remove(i);
        matrixL.get(j).add(i, newL);

        Double newU = (matrixA.get(i).get(j) - countSum(i, j)) / matrixL.get(i).get(i);
        matrixU.get(i).remove(j);
        matrixU.get(i).add(j, newU);
      }
    }

    int a = 5 + 5;
  }

  /** Метод, который высчитывает определитель матрицы. */
  public Double countDeterminant() {
    Double determinant = 1D;
    for (int i = 0; i < MATRIX_SIZE; i++) {
      determinant *= matrixL.get(i).get(i);
    }

    return determinant;
  }

  /** Метод, который высчитывает определитель сумму, используемую в LU-разложении. */
  private Double countSum(int i, int j) {
    Double sum = 0D;

    for (int k = 0; k < i - 1; k++) {
      sum += matrixL.get(j).get(k) * matrixU.get(k).get(i);
    }

    return sum;
  }

  /** Метод, который инициализирует матрицы. */
  private List<List<Double>> initMatrix() {
    List<List<Double>> matrix = new ArrayList<>();
    for (int i = 0; i < MATRIX_SIZE; i++) {
      matrix.add(new ArrayList<>(Arrays.asList(0D, 0D, 0D)));
    }

    return matrix;
  }

  /** Метод, который возвращает матрицу L. */
  public List<List<Double>> getMatrixL() {
    return matrixL;
  }

  /** Метод, который возвращает матрицу U. */
  public List<List<Double>> getMatrixU() {
    return matrixU;
  }
}
