package mathcalc.group343.stepyrev;

import static java.lang.Math.abs;

import Jama.Matrix;
import mathcalc.group343.stepyrev.solver.CollocationSolver;
import mathcalc.group343.stepyrev.solver.RitzSolver;
import mathcalc.group343.stepyrev.util.MatrixUtil;
import mathcalc.group343.stepyrev.util.PrettyPrinter;

/** Класс, который выводит на экран результат работы программы. */
public class Main {

  private static final double x1 = -0.5;
  private static final double x2 = 0.0;
  private static final double x3 = 0.5;
  private static final int startN = 3;
  private static final int endN = 7;
  private static PrettyPrinter printer;

  private static String[][] taskTable;
  private static RitzSolver ritzSolver;
  private static CollocationSolver collocationSolver;

  /** Метод, который выводит на экран результат работы программы. */
  public static void main(String[] args) {
    init();
    printIntroduction();
    for (int i = startN; i <= endN; i++) {
      makeIteration(i);
    }

    printer.print(taskTable);
  }

  /** Метод, который печатает расширенную матрицу системы. */
  private static void printMatrix(Matrix matrix) {
    System.out.println(String.format("Extended system matrix for n = %d", matrix.getRowDimension()));
    MatrixUtil.printMatrix(matrix);
    System.out.println("");
  }

  /** Метод, который заполняет значения одной строки таблицы. */
  private static void makeIteration(int i) {
    double ritzX1Val = ritzSolver.getValue(x1, i);
    double ritzX2Val = ritzSolver.getValue(x2, i);
    double ritzX3Val = ritzSolver.getValue(x3, i);
    double condA = ritzSolver.getCondA();
    printMatrix(ritzSolver.getMatrix());

    double colX1Val = collocationSolver.getValue(x1, i);
    double colX2Val = collocationSolver.getValue(x2, i);
    double colX3Val = collocationSolver.getValue(x3, i);

    taskTable[i + 1 - 3][0] = String.valueOf(i);
    taskTable[i + 1 - 3][1] = String.valueOf(condA);
    taskTable[i + 1 - 3][2] = String.valueOf(ritzX1Val);
    taskTable[i + 1 - 3][3] = String.valueOf(ritzX2Val);
    taskTable[i + 1 - 3][4] = String.valueOf(ritzX3Val);
    taskTable[i + 1 - 3][5] = String.valueOf(abs(colX1Val - ritzX1Val));
    taskTable[i + 1 - 3][6] = String.valueOf(abs(colX2Val - ritzX2Val));
    taskTable[i + 1 - 3][7] = String.valueOf(abs(colX3Val - ritzX3Val));
  }

  /** Метод, который инициализирует начальные параметры задачи. */
  private static void init() {
    ritzSolver = new RitzSolver();
    collocationSolver = new CollocationSolver();
    taskTable = new String[6][8];
    printer = new PrettyPrinter(System.out);
    taskTable[0][0] = "n";
    taskTable[0][1] = "cond(A)";
    taskTable[0][2] = "y^n(-0.5)";
    taskTable[0][3] = "y^n(0)";
    taskTable[0][4] = "y^n(0.5)";
    taskTable[0][5] = "y^*(-0.5) - y^n(-0.5)";
    taskTable[0][6] = "y^*(0) - y^n(0)";
    taskTable[0][7] = "y^*(0.5) - y^n(0.5)";
  }

  /** Метод, который печатает вводные данные программы. */
  public static void printIntroduction() {
    System.out.println("Task 6 for Computational practice lessons.");
    System.out.println("Program was written by Stepyrev Daniil, student of 343 group.");
    System.out.println("The second variant was used in all methods.");
    System.out.println("");
    System.out.println("Start params:");
    System.out.println("-( (2 + x) / (3 + x * u')' + (1 + sin(x))u = 1 - x,");
    System.out.println("u'(-1) = u'(1)+ u(1) = 0");
    System.out.println("Coordination system: 1, x, (1 - x^2) * P(i, 1, 1)(x), i = 0, 1, ...");
    System.out.println("");
  }
}
