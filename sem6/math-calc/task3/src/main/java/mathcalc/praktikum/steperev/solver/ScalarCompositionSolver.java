package mathcalc.praktikum.steperev.solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import mathcalc.praktikum.steperev.util.MatrixUtil;
import mathcalc.praktikum.steperev.util.NormCounter;
import mathcalc.praktikum.steperev.util.TaskContainer;
import org.la4j.Matrix;

/** Класс, который реализует метод скалярных произведений. */
public class ScalarCompositionSolver {
  private double epsilon;
  private Matrix matrixA;
  private Matrix vectorYk;
  private double lambda;
  private int counter;
  private final List<Double> coefs = new ArrayList<>(Arrays.asList(1d, 2d, 10d));

  private double absoluteLambda;
  private Map<Integer, TaskContainer> result;
  private TaskContainer curContainer;

  /** Метод, который реализует метод скалярных произведений. */
  public Map<Integer, TaskContainer> performProcess(Matrix matrixA, Matrix eigenvectors, double epsilon, double absoluteLambda) {
    this.absoluteLambda = absoluteLambda;
    result = new TreeMap<>();
    this.matrixA = matrixA;
    this.epsilon = epsilon;
    vectorYk = MatrixUtil.generateY0(eigenvectors, coefs);
    vectorYk = normalizeMatrixY(vectorYk);
    lambda = 0d;

    counter = 0;
    boolean isAccurate = false;
    while (!isAccurate) {
      curContainer = new TaskContainer();
      double prevLambda = lambda;
      lambda = countLambda();

      curContainer.setLambdaK(lambda);
      curContainer.setDifPrevLambdas(Math.abs(lambda - prevLambda));
      curContainer.setDifAbsoluteLambdas(Math.abs(lambda - absoluteLambda));
      curContainer.setDifNorm(getDifNorm());
      curContainer.setErrorApost(MatrixUtil.findAposteriorError(matrixA, vectorYk, lambda));

      isAccurate = checkAccurate(vectorYk);
      counter += 1;
      if (counter > 50) {
        System.out.println("Число итераций превысило 50!");
        return null;
      }

      vectorYk = matrixA.multiply(vectorYk);
      vectorYk = normalizeMatrixY(vectorYk);
      if (counter >= 2) {
        result.put(counter, curContainer);
      }
    }

    return result;
  }

  /** Метод, который вычисляет || A*xK - lambdaK * xK ||*/
  private double getDifNorm() {
    Matrix aX = matrixA.multiply(vectorYk);
    Matrix lambdaX = vectorYk.multiply(lambda);
    Matrix difMatrix = aX.subtract(lambdaX);
    return NormCounter.countNormMatrix(difMatrix);
  }

  /** Метод, который подсчитывает lambdaK. */
  private double countLambda() {
    Matrix matrixAyNorm = matrixA.multiply(vectorYk);
    double upperValue = MatrixUtil.makeScalarComposition(matrixAyNorm, vectorYk);
    double lowerValue = MatrixUtil.makeScalarComposition(vectorYk, vectorYk);
    return upperValue / lowerValue;
  }

  /**
   * Метод, который возврващает собстсвенный вектор, соответвующий максимальному собственному числу.
   */
  public Matrix getMaxEigenvalueVector() {
    MatrixUtil.normalizeVector(vectorYk);
    return vectorYk;
  }

  /** Метод, который возврващает максимальное собственное число. */
  public double getMaxEigenvalue() {
    return lambda;
  }

  /** Метод, который возвращает counter. */
  public int getCounter() {
    return counter;
  }

  /** Метод, который находит наибольший по модулю элемент в векторе. */
  private double findMaxInVectorY(Matrix vector) {
    double maxElem = -1d;
    int rowsSize = vector.rows();
    int columnsSize = vector.columns();
    for (int i = 0; i < rowsSize; i++) {
      for (int j = 0; j < columnsSize; j++) {
        double elem = Math.abs(vector.get(i, j));
        if (elem > maxElem) {
          maxElem = elem;
        }
      }
    }

    return maxElem;
  }

  /** Метод, который проверяет, что |y(p) - y(p + 1)| < epsilon. */
  private boolean checkAccurate(Matrix nextYk) {
    double aposteriorError = MatrixUtil.findAposteriorError(matrixA, nextYk, lambda);
    return aposteriorError < epsilon;
  }

  /** Метод, который нормирует переданную матрицу Y. */
  public Matrix normalizeMatrixY(Matrix matrixY) {
    Matrix matrix = matrixY.copy();
    double maxElem = findMaxInVectorY(matrixY);
    int size = matrixA.columns();
    for (int i = 0; i < size; i++) {
      double elem = matrix.get(i, 0);
      matrix.set(i, 0, elem / maxElem);
    }

    return matrix;
  }
}
