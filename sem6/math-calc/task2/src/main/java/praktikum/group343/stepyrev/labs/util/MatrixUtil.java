package praktikum.group343.stepyrev.labs.util;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Класс, который содержит тестовый матрицы и методы работы с матрицами. */
public class MatrixUtil {

  // Матрица А
  public static final List<List<Double>> matrix =
      new ArrayList<>(
          Arrays.asList(
              new ArrayList<>(Arrays.asList(7.35272, 0.88255, -2.270052)),
              new ArrayList<>(Arrays.asList(0.88255, 5.58351, 0.528167)),
              new ArrayList<>(Arrays.asList(-2.27005, 0.528167, 4.430329))));

  // Матрица, используемая в качестве правой части
  public static final List<Double> rightPart = new ArrayList<>(Arrays.asList(1.0, 0.0, 0.0));

  private static final PrettyPrinter printer = new PrettyPrinter(System.out);

  /**
   * Метод, которые объединяет объединяет матрицу A и b.
   *
   * @param matrix -- матрица А
   * @param rightPart -- матрица b
   * @return -- объединенная матрица
   */
  public static List<List<Double>> uniteMatrices(
      List<List<Double>> matrix, List<Double> rightPart) {
    List<List<Double>> copyMatrix = copyMatrix(matrix);

    for (int i = 0; i < rightPart.size(); i++) {
      Double elem = rightPart.get(i);
      copyMatrix.get(i).add(elem);
    }

    return copyMatrix;
  }

  /** Метод, который объединяет два вектора в матрицу. */
  public static List<List<Double>> uniteVectors(List<Double> fstVector, List<Double> sndVector) {
    List<List<Double>> unitedMatrix = new ArrayList<>();
    unitedMatrix.add(new ArrayList<>());
    unitedMatrix.add(new ArrayList<>());
    unitedMatrix.add(new ArrayList<>());

    for (int i = 0; i < fstVector.size(); i++) {
      Double elem = fstVector.get(i);
      unitedMatrix.get(i).add(elem);
    }

    for (int j = 0; j < sndVector.size(); j++) {
      Double elem = sndVector.get(j);
      unitedMatrix.get(j).add(elem);
    }

    return unitedMatrix;
  }

  /** Метод, который считает разность векторов. */
  public static List<Double> divideVectors(List<Double> fstVector, List<Double> sndVector) {
    List<Double> difVector = new ArrayList<>();
    for (int i = 0; i < fstVector.size(); i++) {
      difVector.add(fstVector.get(i) - sndVector.get(i));
    }

    return difVector;
  }

  /** Метод, который копирует матрицу. */
  public static List<List<Double>> copyMatrix(List<List<Double>> matrix) {
    List<List<Double>> copyMatrix = new ArrayList<>();
    for (List<Double> elemList : matrix) {
      List<Double> copyElem = new ArrayList<>(elemList);
      copyMatrix.add(copyElem);
    }

    return copyMatrix;
  }

  /** Метод, который копирует вектор. */
  public static List<Double> copyVector(List<Double> vector) {
    List<Double> copyVector = new ArrayList<>();
    for (Double elem : vector) {
      copyVector.add(elem);
    }

    return copyVector;
  }

  /** Метод, который печатает матрицу. */
  public static void printMatrix(List<List<Double>> matrix) {
    int size = matrix.size();
    String[][] table = new String[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        table[i][j] = String.valueOf(matrix.get(i).get(j));
      }
    }

    printer.print(table);
    System.out.println("");
  }

  /** Метод, который печатает вектор. */
  public static void printVector(List<Double> vector) {
    int size = vector.size();
    String[][] table = new String[size][1];
    for (int i = 0; i < size; i++) {
      table[i][0] = String.valueOf(vector.get(i));
    }

    printer.print(table);
    System.out.println("");
  }

  /** Метод, который находит максимальное по модулю собственное число. */
  public static Double getMaxEigenvalue(List<List<Double>> matrix) {
    Matrix jamaMatrix = new Matrix(matrix.size(), matrix.size());
    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.size(); j++) {
        jamaMatrix.set(i, j, matrix.get(i).get(j));
      }
    }

    EigenvalueDecomposition decomposition = jamaMatrix.eig();
    double[] eigenvalues = decomposition.getRealEigenvalues();
    return Arrays.stream(eigenvalues).map(Math::abs).max().getAsDouble();
  }

  public static List<Double> sumVectors(List<Double> fstVector, List<Double> sndVector) {
    List<Double> result = new ArrayList<>();
    for (int i = 0; i < fstVector.size(); i++) {
      result.add(fstVector.get(i) + sndVector.get(i));
    }

    return result;
  }

  /** Метод, который умножает вектор на число. */
  public static List<Double> multiplyVector(List<Double> vector, double value) {
    return vector.stream().map(o -> o * value).collect(Collectors.toList());
  }
}
