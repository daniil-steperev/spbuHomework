package mathcalc.praktikum.steperev.util;

import java.util.List;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

/** Класс, который содержит тестовый матрицы и методы работы с матрицами. */
public class MatrixUtil {

  // Матрица А, используемая в задании
  public static Matrix matrix =
      new Basic2DMatrix(
          new double[][] {
            {-0.81417, -0.01937, 0.41372},
            {-0.01937, 0.54414, 0.00590},
            {0.41372, 0.00590, -0.81445}
          });

  private static final PrettyPrinter printer = new PrettyPrinter(System.out);

  /** Метод, который печатает матрицу. */
  public static void printMatrix(Matrix matrix) {
    int size = matrix.rows();
    String[][] table = new String[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        table[i][j] = String.valueOf(matrix.get(i, j));
      }
    }

    printer.print(table);
    System.out.println("");
  }

  /** Метод, который находит апостерирную оценку погрнешности собственного числа. */
  public static double findAposteriorError(Matrix matrixA, Matrix vectorY, double lambda) {
    // A * Y
    Matrix matrixAy = matrixA.multiply(vectorY);
    // lambda * Y
    Matrix matrixLambdaY = vectorY.multiply(lambda);
    // A * Y - lambda * Y
    Matrix difMatrix = matrixAy.subtract(matrixLambdaY);
    double difMatrixNorm = NormCounter.countNormMatrix(difMatrix);
    double vectorYNorm = NormCounter.countNormMatrix(vectorY);
    return difMatrixNorm / vectorYNorm;
  }

  /** Метод, который генерирует вектор из линейной комбинации собственных векторов. */
  public static Matrix generateY0(Matrix eigenvectors, List<Double> coefs) {
    Matrix generatedMatrix = new Basic2DMatrix(eigenvectors.rows(), 1);
    for (int i = 0; i < eigenvectors.rows(); i++) {
      double value = 0d;
      for (int j = 0; j < eigenvectors.columns(); j++) {
        double coef = coefs.get(j);
        double elem = eigenvectors.get(i, j);
        value += coef * elem;
      }

      generatedMatrix.set(i, 0, value);
    }

    return generatedMatrix;
  }

  /** Метод, который выполняет скалярной произведение векторов. */
  public static double makeScalarComposition(Matrix fstVector, Matrix sndVector) {
    double scalarComposition = 0d;
    int size = fstVector.rows();
    for (int i = 0; i < size; i++) {
      scalarComposition += fstVector.get(i, 0) * sndVector.get(i, 0);
    }

    return scalarComposition;
  }

  /** Метод, который нормирует вектор. */
  public static void normalizeVector(Matrix vector) {
    double vectorLength = 0d;
    int size = vector.rows();
    for (int i = 0; i < size; i++) {
      vectorLength += Math.pow(vector.get(i, 0), 2);
    }
    vectorLength = Math.sqrt(vectorLength);

    for (int i = 0; i < size; i++) {
      double elem = vector.get(i, 0);
      vector.set(i, 0, elem / vectorLength);
    }
  }

  /** Метод, который создает матрицу E 3x3.*/
  public static Matrix getUnitMatrix() {
    return new Basic2DMatrix(new double[][] {{1d, 0d, 0d}, {0d, 1d, 0d}, {0d, 0d, 1d}});
  }
}
