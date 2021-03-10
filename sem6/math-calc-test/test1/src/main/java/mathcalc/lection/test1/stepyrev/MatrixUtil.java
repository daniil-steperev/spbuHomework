package mathcalc.lection.test1.stepyrev;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import java.util.ArrayList;
import java.util.List;
import org.la4j.inversion.GaussJordanInverter;
import org.la4j.inversion.MatrixInverter;
import org.la4j.matrix.dense.Basic2DMatrix;

/** Класс, который реализует дополнительные методы работы с матрицами. */
public class MatrixUtil {

  /**
   * Метод, который возвращает собственные числа матрицы.
   * @param matrix -- матрица, собственные числа которой возвращаются
   * @return -- собственные числа
   */
  public static double[] getEigenvalues(Matrix matrix) {
    EigenvalueDecomposition decomposition = matrix.eig();
    return decomposition.getRealEigenvalues();
  }

  public static Matrix findInverseMatrix(Matrix jamaMatrix) {
    double[][] arrayMatrix = jamaMatrix.getArray();
    org.la4j.Matrix matrix = new Basic2DMatrix(arrayMatrix);
    MatrixInverter inverter = new GaussJordanInverter(matrix);
    matrix = inverter.inverse();
    double[][] inverseArrayMatrix = matrix.toDenseMatrix().toArray();
    return new Matrix(inverseArrayMatrix);
  }

  public static Matrix getSqrtFromDiagonal(Matrix matrix) {
    int size = matrix.getColumnDimension();
    for (int i = 0; i < size; i++) {
      double elem = matrix.get(i, i);
      matrix.set(i, i, Math.sqrt(elem));
    }

    return matrix;
  }

  private static Matrix convertListToJama(List<List<Double>> inverseMatrix) {
    int size = inverseMatrix.get(0).size();
    Matrix jamaMatrix = new Matrix(size, size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        double elem = inverseMatrix.get(i).get(j);
        jamaMatrix.set(i, j, elem);
      }
    }

    return jamaMatrix;
  }

  private static List<List<Double>> convertJamaToList(Matrix jamaMatrix) {
    List<List<Double>> matrixList = new ArrayList<>();
    int size = jamaMatrix.getColumnDimension();
    for (int i = 0; i < size; i++) {
      matrixList.add(new ArrayList<>());
      for (int j = 0; j < size; j++) {
        double elem = jamaMatrix.get(i, j);
        matrixList.get(i).add(j, elem);
      }
    }

    return matrixList;
  }

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
    for (int i = 0; i < fstVector.size(); i++) {
      unitedMatrix.add(new ArrayList<>());
    }

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
