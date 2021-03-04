package praktikum.group343.stepyrev.labs.main;

import java.util.List;
import praktikum.group343.stepyrev.labs.solver.GaussSolver;
import praktikum.group343.stepyrev.labs.solver.LusternikSolver;
import praktikum.group343.stepyrev.labs.solver.MatrixTransformSolver;
import praktikum.group343.stepyrev.labs.solver.SeidelSolver;
import praktikum.group343.stepyrev.labs.solver.SimpleIterationSolver;
import praktikum.group343.stepyrev.labs.solver.UpperRelaxationSolver;
import praktikum.group343.stepyrev.labs.util.ErrorCounter;
import praktikum.group343.stepyrev.labs.util.MatrixUtil;

/** Класс, в котором печатается результат работы программы. */
public class Main {
  private static List<Double> gaussSolution;
  private static GaussMain gaussMain;
  private static GaussSolver gaussSolver;

  private static SimpleIterationMain iterationMain;
  private static SimpleIterationSolver iterationSolver;
  private static List<Double> iterationSolution;

  private static MatrixTransformMain matrixMain;
  private static MatrixTransformSolver matrixSolver;
  private static List<List<Double>> matrixH;
  private static List<Double> vectorG;

  private static LusternikMain lusternikMain;
  private static LusternikSolver lusternikSolver;
  private static List<Double> lusternikSolution;

  private static SeidelMain seidelMain;
  private static SeidelSolver seidelSolver;
  private static List<Double> seidelSolution;

  private static UpperRelaxationMain upperRelaxationMain;
  private static UpperRelaxationSolver upperRelaxationSolver;
  private static List<Double> upperRelaxationSolution;

  private static ErrorCounter errorCounter;

  /** Метод, который печатает результат работы программы. */
  public static void main(String[] args) {
    printIntroduction();
    performGaussMethod(args);
    performMatrixTransformation(args);
    performSimpleIteration(args);
    performLusternikMethod(args);
    performSeidelMethod(args);
    performUpperRelaxationMethod(args);
    compareActualErrors();
  }

  /** Метод, который печатает вводные данные программы. */
  private static void printIntroduction() {
    System.out.println("Task 2 for Computational practice lessons.");
    System.out.println("Program was written by Stepyrev Daniil, student of 343 group.");
    System.out.println("The first variant of task was used.");
    System.out.println("The second variant of matrix was used.");
    System.out.println("");
    printMatrix();
  }

  /** Метод, который печатает тестовые матрицы. */
  private static void printMatrix() {
    System.out.println("Matrix A: ");
    MatrixUtil.printMatrix(MatrixUtil.matrix);
    System.out.println("Vector b: ");
    MatrixUtil.printVector(MatrixUtil.rightPart);
  }

  /** Метод, который печатает результат работы метода Гаусса. */
  private static void performGaussMethod(String[] args) {
    gaussMain = new GaussMain();
    GaussMain.main(args);
    gaussSolver = gaussMain.getSolver();
    gaussSolution = gaussMain.getSolution();
    System.out.println("");
  }

  /** Метод, который печатает результат матричного преобразования. */
  private static void performMatrixTransformation(String[] args) {
    matrixMain = new MatrixTransformMain();
    MatrixTransformMain.main(args);
    matrixSolver = matrixMain.getSolver();
    matrixH = matrixSolver.getMatrixH();
    vectorG = matrixSolver.getVectorG();
    System.out.println("");
  }

  /** Метод, который печатает результат работы метода простых итераций. */
  private static void performSimpleIteration(String[] args) {
    iterationMain = new SimpleIterationMain();
    SimpleIterationMain.main(args);
    iterationSolver = iterationMain.getSolver();
    iterationSolution = iterationMain.getSolution();
    SimpleIterationMain.printErrors(gaussSolution);
    System.out.println("");
  }

  /** Метод, который печатает результат работы метода Люстерника. */
  private static void performLusternikMethod(String[] args) {
    lusternikMain = new LusternikMain();
    LusternikMain.main(args);
    lusternikSolver = lusternikMain.getSolver();
    lusternikSolution = LusternikMain.getSolution();
    LusternikMain.printActualError(gaussSolution);
    System.out.println("");
  }

  /** Метод, который печатает результат работы метода Зейделя. */
  private static void performSeidelMethod(String[] args) {
    seidelMain = new SeidelMain();
    SeidelMain.main(args);
    seidelSolver = SeidelMain.getSolver();
    seidelSolution = SeidelMain.getSolution();
    SeidelMain.printActualError(gaussSolution);
    System.out.println("");
  }

  /** Метод, который печатает результат работы метода верхней релаксации. */
  private static void performUpperRelaxationMethod(String[] args) {
    upperRelaxationMain = new UpperRelaxationMain();
    UpperRelaxationMain.main(args);
    upperRelaxationSolver = UpperRelaxationMain.getSolver();
    upperRelaxationSolution = UpperRelaxationMain.getSolution();
    UpperRelaxationMain.printActualError(gaussSolution);
    System.out.println("");
  }

  /** Метод, который печатает сравнение фактических погрешностей для всех методов. */
  private static void compareActualErrors() {
    errorCounter = new ErrorCounter(matrixH, vectorG);
    double iterationError = SimpleIterationMain.getActualError();
    double seidelError = SeidelMain.getActualError();
    double lusternikError = LusternikMain.getActualError();
    double upperRelaxationError = UpperRelaxationMain.getActualError();
    System.out.println("Compare actual errors: ");
    errorCounter.compareActualErrors(
        iterationError, seidelError, lusternikError, upperRelaxationError);
  }
}
