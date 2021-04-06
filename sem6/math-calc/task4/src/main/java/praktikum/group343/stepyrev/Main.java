package praktikum.group343.stepyrev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import praktikum.group343.stepyrev.solver.MechanicalQuadraturesSolver;
import praktikum.group343.stepyrev.util.PrettyPrinter;

/**
 * Класс, который запускает печатает результат работы метода механических квадратур.
 */
public class Main {

  private static final double A = 0;
  private static final double B = 1;
  private static final double EPSILON = Math.pow(10, -2);
  private static boolean isCalculationsPrinted = false;
  private static final int N = 2;
  private static MechanicalQuadraturesSolver solver;
  private static List<Double> uA;
  private static List<Double> uAB;
  private static List<Double> uB;
  private static PrettyPrinter printer = new PrettyPrinter(System.out);

  public static void main(String[] args) {
    isCalculationsPrinted = isCalculationsPrinted();
    solver = new MechanicalQuadraturesSolver(isCalculationsPrinted);
    uA = new ArrayList<>();
    uAB = new ArrayList<>();
    uB = new ArrayList<>();
    printIntroduction();
    performTask();
    printTable();
  }

  private static boolean isCalculationsPrinted() {
    Scanner scanner = new Scanner(System.in);
    String answer = "";
    while (!(answer.equals("y") || answer.equals("n"))) {
      System.out.println("Do you want to print calculate results? (y/n)");
      answer = scanner.nextLine();
    }

    return answer.equals("y");
  }

  /**
   * Метод, который печатает печатает результат работы метода механических квадратур.
   */
  private static void performTask() {
    boolean isAccurate = false;
    int n = N;
    double uNA = solver.getSolution(A, B, A, n);
    double uNAB = solver.getSolution(A, B, (A + B) / 2, n);
    double uNB = solver.getSolution(A, B, B, n);
    uA.add(uNA);
    uB.add(uNB);
    uAB.add(uNAB);

    while (!isAccurate) {
      n *= 2;
      double newUnA = solver.getSolution(A, B, A, n);
      double newUnAB = solver.getSolution(A, B, (A + B) / 2, n);
      double newUnB = solver.getSolution(A, B, B, n);

      if (isCalculationsPrinted) {
        System.out.println(
            String
                .format("u^(%d)(x): x = a: %f, x = (a + b) / 2: %f, x = b: %f", n, uNA, uNAB, uNB));

      }

      isAccurate = checkAccuracy(Arrays.asList(uNA, uNAB, uNB),
          Arrays.asList(newUnA, newUnAB, newUnB));
      uNA = newUnA;
      uNAB = newUnAB;
      uNB = newUnB;
      uA.add(uNA);
      uB.add(uNB);
      uAB.add(uNAB);
    }
  }

  /**
   * Метод, который печатает таблицу решений.
   */
  private static void printTable() {
    int size = uA.size();
    String[][] table = new String[size + 2][4];
    table[0][0] = "x";
    table[0][1] = "a";
    table[0][2] = "(a + b) / 2";
    table[0][3] = "b";

    int n = N;
    for (int i = 0; i < size; i++) {
      table[i][0] = String.format("u^(%d) (x)", n);
      table[i][1] = String.valueOf(uA.get(i));
      table[i][2] = String.valueOf(uAB.get(i));
      table[i][3] = String.valueOf(uB.get(i));
      n *= 2;
    }

    table[size][0] = String.format("u^(%d) (x) - u^(%d) (x)", n / 2, n / 4);
    table[size][1] = String.valueOf(Math.abs(uA.get(size - 1) - uA.get(size - 2)));
    table[size][2] = String.valueOf(Math.abs(uAB.get(size - 1) - uAB.get(size - 2)));
    table[size][3] = String.valueOf(Math.abs(uB.get(size - 1) - uB.get(size - 2)));

    table[size + 1][0] = "Решение, полученное в 1-ом методе";
    table[size + 1][1] = String.valueOf(uA.get(size - 1));
    table[size + 1][2] = String.valueOf(uAB.get(size - 1));
    table[size + 1][3] = String.valueOf(uB.get(size - 1));

    printer.print(table);

    table = new String[1][2];
    table[0][0] = "Оценка, полученная в 1-ом методе";
    List<Double> oldValues = Arrays.asList(uA.get(size - 2), uAB.get(size - 2), uB.get(size - 2));
    List<Double> newValues = Arrays.asList(uA.get(size - 1), uAB.get(size - 1), uB.get(size - 1));
    table[0][1] = String.valueOf(findMaxDif(oldValues, newValues));

    printer.print(table);
  }

  /**
   * Метод, который находит максимальную разность среди последних решений.
   */
  private static double findMaxDif(List<Double> oldValues, List<Double> newValues) {
    double max = -1d;
    for (int i = 0; i < oldValues.size(); i++) {
      double oldVal = oldValues.get(i);
      double newVal = newValues.get(i);
      double dif = Math.abs(oldVal - newVal);
      if (dif > max) {
        max = dif;
      }
    }

    return max;
  }

  /**
   * Метод, который проверяет точность последнего решения.
   */
  private static boolean checkAccuracy(List<Double> oldValues, List<Double> newValues) {
    return findMaxDif(oldValues, newValues) < EPSILON;
  }

  /**
   * Метод, который печатает вводные данные программы.
   */
  public static void printIntroduction() {
    System.out.println("Task 4 for Computational practice lessons.");
    System.out.println("Program was written by Stepyrev Daniil, student of 343 group.");
    System.out.println("The fourth variant was used in all methods.");
    System.out.println("");
    System.out.println("Start params:");
    System.out.println(String.format("A = %f, B = %f, N = %d, Epsilon = %f", A, B, N, EPSILON));
    System.out.println("u(x) + 0.5 * integral(0, 1, sh(x * y) * u(y)dy = x + 0.5");
    System.out.println("");
  }

}
