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

/** Класс, который реализует степенной метод. */
public class SedateSolver {
  private double absoluteLambda;
  private double epsilon;
  private Matrix matrixA;
  private Matrix vectorYk;
  private double lambda;
  private double prevLambda;
  private int counter;
  private final List<Double> coefs = new ArrayList<>(Arrays.asList(10d, 2d, 1d));

  private Map<Integer, TaskContainer> result;
  private TaskContainer curContainer;

  /** Метод, который реализует степенной метод. */
  public Map<Integer, TaskContainer> performProcess(
      Matrix matrixA, Matrix eigenvectors, double epsilon, double absoluteLambda) {
    this.absoluteLambda = absoluteLambda;
    this.matrixA = matrixA;
    this.epsilon = epsilon;
    vectorYk = MatrixUtil.generateY0(eigenvectors, coefs);
    result = new TreeMap<>();
    lambda = 0d;

    counter = 0;
    boolean isAccurate = false;
    while (!isAccurate) {
      curContainer = new TaskContainer();
      prevLambda = lambda;
      lambda = findMaxInVectorY(vectorYk);
      vectorYk = vectorYk.divide(lambda);

      curContainer.setLambdaK(lambda);
      curContainer.setDifPrevLambdas(Math.abs(lambda - prevLambda));
      curContainer.setDifAbsoluteLambdas(Math.abs(lambda - absoluteLambda));
      curContainer.setDifNorm(getDifNorm());
      curContainer.setErrorApost(MatrixUtil.findAposteriorError(matrixA, vectorYk, lambda));

      isAccurate = checkAccurate();
      counter += 1;
      if (counter > 50) {
        System.out.println("Число итераций превысило 50!");
        return null;
      }

      vectorYk = matrixA.multiply(vectorYk);
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
    int indexI = 0;
    int indexJ = 0;
    int rowsSize = vector.rows();
    int columnsSize = vector.columns();
    for (int i = 0; i < rowsSize; i++) {
      for (int j = 0; j < columnsSize; j++) {
        double elem = Math.abs(vector.get(i, j));
        if (elem > maxElem) {
          maxElem = elem;
          indexI = i;
          indexJ = j;
        }
      }
    }

    return vector.get(indexI, indexJ);
  }

  /** Метод, который проверяет, что |y(p) - y(p + 1)| < epsilon. */
  private boolean checkAccurate() {
    double aposteriorError = MatrixUtil.findAposteriorError(matrixA, vectorYk, lambda);
    return aposteriorError < epsilon;
  }
}
