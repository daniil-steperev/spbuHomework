package praktikum.group343.stepyrev.first.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Класс, который содержит тестовый матрицы и методы работы с матрицами. */
public class MatrixUtil {

  // Матрица А для схемы Гаусса единственного деления
  public static List<List<Double>> gaussSolverMatrix =
      new ArrayList<>(
          Arrays.asList(
              new ArrayList<>(Arrays.asList(-400.60, 199.80)),
              new ArrayList<>(Arrays.asList(1198.80, -600.40))));

  // Точная матрица для схемы Гаусса единственного деления
  public static final List<Double> accurateGaussMatrix =
      new ArrayList<>(Arrays.asList(200D, -600D));

  // Измененная матрица для схемы Гаусса единственного деления
  public static final List<Double> modifiedGaussMatrix =
      new ArrayList<>(Arrays.asList(199D, -601D));

  // Матрица А используема ядля нахождения обратной матрицы методом Жордана
  public static final List<List<Double>> jordanMatrix =
      new ArrayList<>(
          Arrays.asList(
              new ArrayList<>(Arrays.asList(3.278164, 1.046583, 1.378574)),
              new ArrayList<>(Arrays.asList(1.046583, -2.975937, 0.934251)),
              new ArrayList<>(Arrays.asList(-1.378574, 0.934251, 4.836173))));

  // Матрица, используемая в качестве правой части второго задания
  public static final List<Double> rightPartLU =
      new ArrayList<>(Arrays.asList(-0.527466, 2.526877, 5.165441));

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

  /** Метод, который создает единичный вектор. */
  public static List<Double> getUnitVector(int dimension, int position) {
    List<Double> vector = new ArrayList<>();
    for (int i = 0; i < dimension; i++) {
      if (i == position) {
        vector.add(1d);
      } else vector.add(0d);
    }

    return vector;
  }
}
