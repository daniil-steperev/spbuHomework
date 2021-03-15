package mathcalc.praktikum.steperev.main;

import java.util.List;
import java.util.Map;
import mathcalc.praktikum.steperev.solver.JacobiSolver;
import mathcalc.praktikum.steperev.solver.ScalarCompositionSolver;
import mathcalc.praktikum.steperev.solver.SedateSolver;
import mathcalc.praktikum.steperev.solver.SpectrumSolver;
import mathcalc.praktikum.steperev.util.MatrixUtil;
import mathcalc.praktikum.steperev.util.PrettyPrinter;
import mathcalc.praktikum.steperev.util.TaskContainer;
import org.la4j.Matrix;

/** Класс, который запускает главный метод программы. */
public class Main {

  private static final Matrix matrix = MatrixUtil.matrix;
  private static final double epsilonJacobi = Math.pow(10, -6);
  private static final double epsilonSedate = Math.pow(10, -3);
  private static final double epsilonScalar = Math.pow(10, -6);
  private static final double epsilonSpectrum = Math.pow(10, -3);
  private static PrettyPrinter printer = new PrettyPrinter(System.out);

  private static double absoluteLambda;

  private static Matrix eigenvectors;

  /** Метод, который запускает главный метод программы. */
  public static void main(String[] args) {
    printIntroduction();
    performJacobyMethod();
    performSedateMethod();
    performScalarCompositionMethod();
    performSpectrumMethod();
  }

  /**
   * Метод, который печатает на экран результат работы метода нахождения противоположной границы
   * спектра.
   */
  private static void performSpectrumMethod() {
    SpectrumSolver solver = new SpectrumSolver();
    solver.performProcess(matrix, eigenvectors, epsilonSpectrum, absoluteLambda);

    System.out.println("Finding λ`1 by opposite spectrum method:");
    System.out.println(String.format("ε = %f", epsilonSpectrum));

    double eigenvalue = solver.getOppositeEigenvalue();
    System.out.println(String.format("Found λ1 = %f", eigenvalue));
  }

  /** Метод, который печатает результат работы степенного метода. */
  private static void performSedateMethod() {
    SedateSolver solver = new SedateSolver();
    Map<Integer, TaskContainer> result =
        solver.performProcess(matrix, eigenvectors, epsilonSedate, absoluteLambda);
    System.out.println("Finding λ1 by sedate method:");
    System.out.println(String.format("ε = %f", epsilonSedate));
    double eigenvalue = solver.getMaxEigenvalue();
    System.out.println(String.format("Found λ1 = %f", eigenvalue));
    printResult(result);

    Matrix eigenvector = solver.getMaxEigenvalueVector();
    printEigenvector(eigenvector);
  }

  /** Метод, который печатает результат работы метода скалярных произведений. */
  private static void performScalarCompositionMethod() {
    ScalarCompositionSolver solver = new ScalarCompositionSolver();
    Map<Integer, TaskContainer> result =
        solver.performProcess(matrix, eigenvectors, epsilonScalar, absoluteLambda);
    System.out.println("Finding λ1 by scalar composition method:");
    System.out.println(String.format("ε = %f", epsilonScalar));
    double eigenvalue = solver.getMaxEigenvalue();
    System.out.println(String.format("Found λ1 = %f", eigenvalue));
    printResult(result);

    Matrix eigenvector = solver.getMaxEigenvalueVector();
    printEigenvector(eigenvector);
  }

  /** Метод, который печатает результат работы метода Якоби. */
  private static void performJacobyMethod() {
    JacobiSolver solver = new JacobiSolver();
    solver.performProcess(matrix, epsilonJacobi);
    List<Double> foundValues = solver.getEigenvaluesMatrix();
    Matrix foundVectors = solver.getEigenvectorsMatrix();
    eigenvectors = foundVectors;
    printEigenValuesAndVectors(foundValues, foundVectors);

    absoluteLambda = findMaxLambda(foundValues);
    System.out.println(
        String.format("Number of iterations in Jacobi method: %d", solver.getCounter()));
    System.out.println("");
  }

  /** Метод, который находит максимальное собственное число в списке. */
  private static double findMaxLambda(List<Double> lambdas) {
    int index = 0;
    double max = -1d;
    for (int i = 0; i < lambdas.size(); i++) {
      double lambda = Math.abs(lambdas.get(i));
      if (lambda > max) {
        max = lambda;
        index = i;
      }
    }

    return lambdas.get(index);
  }

  /** Метод, который печатает результат lambda1, максимальный вектор и число итераций. */
  private static void printResult(Map<Integer, TaskContainer> result) {
    String[][] table = new String[result.size() + 1][6];
    table[0][0] = "k";
    table[0][1] = "λ1^k";
    table[0][2] = "|λ1^k - λ1^(k-1)|";
    table[0][3] = "|λ1^k - λ1^(*)|";
    table[0][4] = "||A * x1^k - λ1^k * x1^k||";
    table[0][5] = "error estimate apost.";

    for (Map.Entry<Integer, TaskContainer> entry : result.entrySet()) {
      int k = entry.getKey();
      TaskContainer container = entry.getValue();
      table[k - 1][0] = String.valueOf(k);
      table[k - 1][1] = String.valueOf(container.getLambdaK());
      table[k - 1][2] = String.valueOf(container.getDifPrevLambdas());
      table[k - 1][3] = String.valueOf(container.getDifAbsoluteLambdas());
      table[k - 1][4] = String.valueOf(container.getDifNorm());
      table[k - 1][5] = String.valueOf(container.getErrorApost());
    }

    printer.print(table);
  }

  /** Метод, который печатает собственный вектор. */
  private static void printEigenvector(Matrix eigenvector) {
    System.out.println("Found eigen vector for λ1: ");
    String[][] table = new String[3][2];
    for (int i = 0; i < eigenvector.rows(); i++) {
      table[i][0] = "Coordinate " + i;
      table[i][1] = String.valueOf(eigenvector.get(i, 0));
    }

    printer.print(table);
    System.out.println("");
  }

  /** Метод, который печатает найденные собственные числа и вектора. */
  private static void printEigenValuesAndVectors(List<Double> foundValues, Matrix foundVectors) {
    System.out.println("Finding eigen values and vectors by Jacobi's method:");
    System.out.println(String.format("ε = %f", epsilonJacobi));
    printEigenvalues(foundValues);
    printEigenvectors(foundVectors);
  }

  /** Метод, который печатает найденные собственные вектора. */
  private static void printEigenvectors(Matrix foundVectors) {
    System.out.println("Found eigen vectors: ");
    int size = foundVectors.rows();
    String[][] table = new String[size + 1][size + 1];
    table[0][0] = "";

    for (int i = 0; i < size; i++) {
      table[i + 1][0] = "Coordinate " + (i + 1);
      table[0][i + 1] = "Vector " + (i + 1);
      for (int j = 0; j < size; j++) {
        table[i + 1][j + 1] = String.valueOf(foundVectors.get(i, j));
      }
    }

    printer.print(table);
    System.out.println("");
  }

  /** Метод, который печатает найденные собственные числа. */
  private static void printEigenvalues(List<Double> foundValues) {
    System.out.println("Found eigen values: ");
    String[][] table = new String[foundValues.size()][2];
    table[0][0] = "λ";
    table[0][1] = "Value";

    for (int i = 0; i < foundValues.size(); i++) {
      table[i][0] = "λ" + (i + 1);
      table[i][1] = String.valueOf(foundValues.get(i));
    }

    printer.print(table);
    System.out.println("");
  }

  /** Метод, который печатает параметры задания. */
  private static void printIntroduction() {
    System.out.println("Task 3 for Computational practice lessons.");
    System.out.println("Program was written by Stepyrev Daniil, student of 343 group.");
    System.out.println("The first variant was used in all methods.");
    System.out.println("");
    System.out.println("Matrix: ");
    MatrixUtil.printMatrix(MatrixUtil.matrix);
  }
}
