package mathcalc.praktikum.steperev.solver;

import java.util.ArrayList;
import java.util.List;
import mathcalc.praktikum.steperev.util.MatrixUtil;
import mathcalc.praktikum.steperev.util.Pair;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

/** Класс, который находит собственные числа матрицы методом Якоби. */
public class JacobiSolver {

  private Matrix matrixAk;
  private Matrix matrixX;
  private int counter;

  /** Метод, который выполняет метод Якоби с заданной точностью. */
  public void performProcess(Matrix matrix, Double epsilon) {
    this.matrixAk = matrix.copy();
    this.matrixX = MatrixUtil.getUnitMatrix();
    boolean isAccurate = false;
    counter = 0;
    while (!isAccurate) {
      this.matrixX = matrixX.multiply(findMatrixV());
      Matrix nextMatrixA = findNextMatrix(matrixAk);
      this.matrixAk = nextMatrixA;

      isAccurate = checkAccurate(epsilon);
      counter += 1;

      if (counter > 50) {
        System.out.println("Число итераций превысило 50!");
        break;
      }
    }
  }

  /** Метод, который возвращает матрицу собственных векторов */
  public Matrix getEigenvectorsMatrix() {
    return matrixX;
  }

  /** Метод, который возвращает список собственных чисел. */
  public List<Double> getEigenvaluesMatrix() {
    List<Double> eigenvalues = new ArrayList<>();
    int size = matrixAk.rows();
    for (int i = 0; i < size; i++) {
      double lambda = matrixAk.get(i, i) + getSum(i);
      eigenvalues.add(lambda);
    }
    return eigenvalues;
  }

  public int getCounter() {
    return counter;
  }

  /** Метод, который считает сумму, используемую при вычислении собственных чисел. */
  private double getSum(int i) {
    double sum = 0d;
    for (int j = 0; j < matrixAk.rows(); j++) {
      if (i == j) {
        continue;
      }
      double aIJ = matrixAk.get(i, j);
      double aII = matrixAk.get(i, i);
      double aJJ = matrixAk.get(j, j);
      sum += aIJ / (aII - aJJ);
    }

    return sum;
  }

  public Matrix findNextMatrix(Matrix matrix) {
    int size = matrix.rows();
    Matrix nextMatrix = new Basic2DMatrix(size, size);

    Pair<Integer, Integer> maxElemIndex = findMaxElementUpperDiagonal();
    int iK = maxElemIndex.getFst();
    int jK = maxElemIndex.getSnd();
    double cConstant = findCConstant(iK, jK);
    double sConstant = findSConstant(iK, jK);

    double aIk = matrix.get(iK, iK);
    double aJk = matrix.get(jK, jK);
    double aIkJk = matrix.get(iK, jK);

    double elem = 0d;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (i == iK && j == jK || i == jK && j == iK) {
          elem = 0d;
        } else if (i == jK && j == jK) {
          elem =
              sConstant * sConstant * aIk
                  - 2 * cConstant * sConstant * aIkJk
                  + cConstant * cConstant * aJk;
        } else if (i == iK && j == iK) {
          elem =
              cConstant * cConstant * aIk
                  + 2 * cConstant * sConstant * aIkJk
                  + sConstant * sConstant * aJk;
        } else if (j == jK) {
          double aIIk = matrix.get(i, iK);
          double aIJk = matrix.get(i, jK);
          elem = -sConstant * aIIk + cConstant * aIJk;
        } else if (j == iK) {
          double aIIk = matrix.get(i, iK);
          double aIJk = matrix.get(i, jK);
          elem = cConstant * aIIk + sConstant * aIJk;
        } else {
          elem = matrix.get(i, j);
        }

        nextMatrix.set(i, j, elem);
      }
    }

    return nextMatrix;
  }

  /** Метод, который проверяет, что |aIkJk| < epsilon. */
  private boolean checkAccurate(Double epsilon) {
    Pair<Integer, Integer> maxPair = findMaxElementUpperDiagonal();
    double maxElem = Math.abs(matrixAk.get(maxPair.getFst(), maxPair.getSnd()));
    return maxElem < epsilon;
  }

  /** Метод, который ищет коэффициент c = cos(фи(k)). */
  private double findCConstant(int iK, int jK) {
    double aIk = matrixAk.get(iK, iK);
    double aJk = matrixAk.get(jK, jK);
    double aIkJk = matrixAk.get(iK, jK);

    // sqrt( (aIk - aJk)^2  + 4 * aIkJk ^ 2 )
    double dConst = Math.sqrt( Math.pow(aIk - aJk, 2) + 4 * Math.pow(aIkJk, 2) );
    // sqrt( 1/2 * (|aIk - aJk| / d) )
    return Math.sqrt(0.5 * (1 + Math.abs(aIk - aJk) / dConst));
  }

  /** Метод, который ищет коэффициент s = sin(фи(k)). */
  private double findSConstant(int iK, int jK) {
    double aIk = matrixAk.get(iK, iK);
    double aJk = matrixAk.get(jK, jK);
    double aIkJk = matrixAk.get(iK, jK);

    // sign( aIkJk * (aIk - aJk) )
    double sign = Math.signum(aIkJk * (aIk - aJk));
    // sqrt( (aIk - aJk)^2  + 4 * aIkJk ^ 2 )
    double dConst = Math.sqrt( Math.pow(aIk - aJk, 2) + 4 * Math.pow(aIkJk, 2) );

    // sign * ( 1/2 * (1 - |aIk - aJk| / d) )
    return sign * Math.sqrt(0.5 * (1 - Math.abs(aIk - aJk) / dConst));
  }

  /** Метод, который ищет максимальные над диагональный элемент заданной матрицы. */
  private Pair<Integer, Integer> findMaxElementUpperDiagonal() {
    double maxElem = -1;
    int size = matrixAk.rows();
    Pair<Integer, Integer> maxPair = new Pair<>(0, 0);
    for (int i = 0; i < size; i++) {
      for (int j = i + 1; j < size; j++) {
        double elem = Math.abs(matrixAk.get(i, j));
        if (elem > maxElem) {
          maxElem = elem;
          maxPair = new Pair<>(i, j);
        }
      }
    }

    return maxPair;
  }

  /** Метод, который формирует матрицу Vk.*/
  private Matrix findMatrixV() {
    Matrix matrixV = new Basic2DMatrix(matrixAk.rows(), matrixAk.columns());
    setDiagonalElements(matrixV);
    setExtraElements(matrixV);
    return matrixV;
  }

  /** Метод, который устанавливает элементы ik, jk матрицы Vk.*/
  private void setExtraElements(Matrix matrixV) {
    Pair<Integer, Integer> maxElemIndex = findMaxElementUpperDiagonal();
    int iK = maxElemIndex.getFst();
    int jK = maxElemIndex.getSnd();
    double cConstant = findCConstant(iK, jK);
    double sConstant = findSConstant(iK, jK);

    matrixV.set(iK, iK, cConstant);
    matrixV.set(jK, jK, cConstant);
    matrixV.set(iK, jK, -sConstant);
    matrixV.set(jK, iK, sConstant);
  }

  /** Метод, который устанавливает диагональные элементы матрицы. */
  private void setDiagonalElements(Matrix matrixV) {
    int size = matrixV.rows();
    for (int i = 0; i < size; i++) {
      matrixV.set(i, i, 1d);
    }
  }
}
