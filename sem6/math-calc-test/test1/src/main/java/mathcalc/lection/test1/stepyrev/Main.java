package mathcalc.lection.test1.stepyrev;

import Jama.Matrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  private static List<TaskContainer> taskLines;
  private static PrettyPrinter printer = new PrettyPrinter(System.out);

  public static void main(String[] args) {
    System.setProperty("console.encoding", "utf-8");
    printIntroduction();
    performFstProcess();
    System.out.println("Результаты вычислений матрицы B с помощью собственных чисел и векторов:");
    printTable();
    performSndProcess();
  }

  private static void performFstProcess() {
    taskLines = new ArrayList<>();
    boolean isBigMistake = false;
    int n = 2;
    while (!isBigMistake) {
      Matrix curMatrix = generateMatrixA(n);
      if (!isAllEigenvaluesPositive(curMatrix)) {
        System.out.println(
            String.format("При n = %d у матрицы А есть неположительное собственное число!", n));
        break;
      }

      Matrix matrixB = countMatrixByFstMethod(curMatrix);

      isBigMistake = checkResult(curMatrix, matrixB);
      n += 1;
    }
  }

  /**
   * Метод, который считает матрицу B первым способом.
   *
   * @param matrix -- матрица А
   * @return -- матрица B
   */
  private static Matrix countMatrixByFstMethod(Matrix matrix) {
    // диагональная матрица с корнями из собственных числел на диагонали
    Matrix eigenValuesMatrix = MatrixUtil.getSqrtFromDiagonal(matrix.eig().getD());
    // диагональная матрица с собственными векторами
    Matrix eigenVectorMatrix = matrix.eig().getV();
    // обратная матрица собственных векторов
    Matrix eigenVectorInverseMatrix = MatrixUtil.findInverseMatrix(eigenVectorMatrix);
    // V * Л1
    Matrix matrixB = eigenVectorMatrix.times(eigenValuesMatrix);
    // (V * Л1) * V^(-1)
    matrixB = matrixB.times(eigenVectorInverseMatrix);
    return matrixB;
  }

  private static void performSndProcess() {
    int n = 2;
    taskLines = new ArrayList<>();
    Matrix matrixA = new Matrix(0, 0);
    boolean isIterationHuge = false;
    while (!isIterationHuge) {
      matrixA = generateMatrixA(n);
      isIterationHuge = performNewtonMethod(matrixA);
      n += 1;
    }

    printSndResult();
  }

  /** Метод, который печатает результат работы второго метода нахождения матрицы B. */
  private static void printSndResult() {
    System.out.println("");
    System.out.println("Результат вычисления матрицы методом Ньютона.");
    int taskSize = taskLines.size();
    String[][] table = new String[taskSize + 1][5];
    table[0][0] = "n";
    table[0][1] = "cond(A)";
    table[0][2] = "cond(B)";
    table[0][3] = "|B^2 - A|";
    table[0][4] = "Iteration number";

    for (int i = 0; i < taskSize; i++) {
      TaskContainer curTask = taskLines.get(i);
      table[i + 1][0] = String.valueOf(i + 2); // так как n начинается с 2
      table[i + 1][1] = String.valueOf(curTask.getCondA());
      table[i + 1][2] = String.valueOf(curTask.getCondB());
      table[i + 1][3] = String.valueOf(curTask.getDivMatrixNorm());
      table[i + 1][4] = String.valueOf(curTask.getIterationNumber());
    }

    printer.print(table);
    System.out.println("");
    System.out.println(
        String.format(
            "При n > %d число итераций превышает 4000. При условии |B^2 - A| < 1", taskSize));
  }

  private static boolean performNewtonMethod(Matrix matrix) {
    double divMatrixNorm = 2;
    int counter = 0;
    Matrix inverseB = new Matrix(0, 0);
    Matrix prevB = matrix;
    Matrix nextB = new Matrix(0, 0);
    Matrix nextB2 = new Matrix(0, 0);
    while (divMatrixNorm > 1) {
      if (counter > 4000) {
        return true;
      }

      inverseB = MatrixUtil.findInverseMatrix(prevB);
      nextB = (prevB.plus(inverseB.times(matrix))).times(0.5);

      nextB2 = nextB.times(nextB);
      divMatrixNorm = (nextB2.minus(matrix)).normInf();
      counter += 1;

      prevB = nextB;
    }

    double condA = matrix.cond();
    double condB = nextB.cond();
    nextB2 = nextB2.times(nextB2);
    TaskContainer container = new TaskContainer(condA, condB, divMatrixNorm, counter);
    taskLines.add(container);
    return false;
  }

  /** Метод, который печатает условие задачи. */
  private static void printIntroduction() {
    System.out.println("Тест 1 по предмету \"Методы вычислений\"");
    System.out.println("Работа выполнена студентом 343 группы Степыревым Даннилом");
    System.out.println("Условия задачи: ak = 4 * k, bk = k");
    System.out.println("");
  }

  /** Метод, который печатает результат работы программы. */
  private static void printTable() {
    int taskSize = taskLines.size();
    String[][] table = new String[taskSize][4];
    table[0][0] = "n";
    table[0][1] = "cond(A)";
    table[0][2] = "cond(B)";
    table[0][3] = "|B^2 - A|";

    for (int i = 0; i < taskSize - 1; i++) {
      TaskContainer curTask = taskLines.get(i);
      table[i + 1][0] = String.valueOf(i + 2); // так как n начинается с 2
      table[i + 1][1] = String.valueOf(curTask.getCondA());
      table[i + 1][2] = String.valueOf(curTask.getCondB());
      table[i + 1][3] = String.valueOf(curTask.getDivMatrixNorm());
    }

    printer.print(table);
    System.out.println("");
    System.out.println(String.format("При n > %d процесс становится неустойчивым.", taskSize + 1));
  }

  /**
   * Метод, который проверяет результат.
   *
   * <p>Если разность нормы B^2 и A больше 1, то цикл останавливается
   *
   * @param matrixA -- матрица А
   * @param matrixB -- матрица B
   * @return -- true -- остановка цикла, иначе продолжить
   */
  private static boolean checkResult(Matrix matrixA, Matrix matrixB) {
    double condA = matrixA.cond();
    double condB = matrixB.cond();
    Matrix matrixB2 = matrixB.times(matrixB);
    double divNorm = (matrixB2.minus(matrixA)).normInf();
    TaskContainer container = new TaskContainer(condA, condB, divNorm);
    taskLines.add(container);

    return divNorm > 1;
  }

  /**
   * Метод, который проверяет, что все собственные числа переданной матрицы положительные
   *
   * @param matrix -- матрица, числа которой проверяются
   * @return -- true -- все положительные, false -- хотя бы одно неположительное
   */
  private static boolean isAllEigenvaluesPositive(Matrix matrix) {
    double[] eigenvalues = MatrixUtil.getEigenvalues(matrix);
    // выбираем в массиве только отрицательные числа
    eigenvalues = Arrays.stream(eigenvalues).filter(o -> o <= 0).toArray();
    // проверяем, равно ли 0 число неположительных собственных чисел
    return eigenvalues.length == 0;
  }

  /**
   * Метод, который генерирует тестовую матрицу A. ak = (4 * k) ^ k
   *
   * @param n -- размерность матрицы A
   * @return -- матрицу А
   */
  private static Matrix generateMatrixA(int n) {
    Matrix matrix = new Matrix(n, n);
    for (int k = 1; k <= n; k++) {
      int aK = 4 * k;
      for (int i = 1; i <= n; i++) {
        matrix.set(k - 1, i - 1, Math.pow(aK, i));
      }
    }

    return matrix;
  }
}
