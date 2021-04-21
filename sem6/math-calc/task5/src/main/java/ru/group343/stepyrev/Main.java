package ru.group343.stepyrev;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static ru.group343.stepyrev.util.ParamsUtil.T;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import ru.group343.stepyrev.function.Function;
import ru.group343.stepyrev.function.FunctionAccurate;
import ru.group343.stepyrev.function.HyperbMultFunction;
import ru.group343.stepyrev.function.HyperbPlusFunc;
import ru.group343.stepyrev.function.PolynomMultFunction;
import ru.group343.stepyrev.function.PolynomPlusFunction;
import ru.group343.stepyrev.solver.AccurateSolver;
import ru.group343.stepyrev.solver.GridMethodSolver;
import ru.group343.stepyrev.solver.GridSolver;
import ru.group343.stepyrev.solver.GridWeightSolver;
import ru.group343.stepyrev.util.PrettyPrinter;

/** Класс, который выводит на экран результат работы программы. */
public class Main {

  private static PrettyPrinter printer = new PrettyPrinter(System.out);
  private static final Integer N = 5;
  private static final Integer M = 5;

  private static List<Integer> listM = new ArrayList<>(Arrays.asList(10, 20, 40));
  private static List<Integer> listN = new ArrayList<>(Arrays.asList(10, 20, 40));

  /** Метод, который выводит на экран результат работы программы. */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String answer = "-d";
    boolean isStopped = false;

    printIntroduction();

    System.out.println(
        "Enter a number to choose a function that does not accurately approximate: ");
    System.out.println("1 -- u(x, t) = x^3 + t^3");
    System.out.println("2 -- u(x, t) = x^3 * t^3");
    System.out.println("3 -- u(x, t) = cos(2x) + sin(2t + 1)");
    System.out.println("4 -- u(x, t) = cos(2x) * sin(2t + 1)");
    System.out.println("5 -- u(x, t) = 2x - t");
    System.out.println("0 -- to exit");
    System.out.println("");

    while (!isStopped) {
      answer = scanner.nextLine();
      Function function = null;

      switch (answer) {
        case "1":
          function = new PolynomPlusFunction();
          printPolynomPlusResult();
          break;
        case "2":
          function = new PolynomMultFunction();
          printPolynomMultResult();
          break;
        case "3":
          function = new HyperbPlusFunc();
          printHyperbPlusResult();
          break;
        case "4":
          function = new HyperbMultFunction();
          printHyperbMultResult();
          break;
        case "5":
          function = new FunctionAccurate();
          printAccurateApproximationResult();
          break;
        case "0":
          isStopped = true;
          break;
      }

      if (isStopped) {
        System.out.println("Thank you for using program!");
        return;
      }

      if (function == null) {
        System.out.println("Enter a number from 0 to 5 to start a program.");
      }

      System.out.println("To start a program again choose a number from 0 to 5.");
    }
  }

  private static void printAccurateApproximationResult() {
    Function function = new FunctionAccurate();
    System.out.println("Printing explicit result for u(x, t) = 2x - t");
    printResult(function, new GridSolver());
    System.out.println("");

    System.out.println("Printing implicit result for u(x, t) = 2x - t. ");
    printResult(function, new GridWeightSolver());
    System.out.println("");

    System.out.println("Printing result table for explicit table: ");
    printTableResult(function, new GridSolver());
    System.out.println("");

    System.out.println("Printing result table for implicit table: ");
    printTableResult(function, new GridWeightSolver());
    System.out.println("");
  }

  /** Метод, который выводит на экран результат работы программы для функции u(x, t) = x^3 + t^3. */
  private static void printPolynomPlusResult() {
    Function function = new PolynomPlusFunction();
    System.out.println("Printing explicit result for u(x, t) = x^3 + t^3. ");
    printResult(function, new GridSolver());
    System.out.println("");

    System.out.println("Printing implicit result for u(x, t) = x^3 + t^3. ");
    printResult(function, new GridWeightSolver());
    System.out.println("");

    System.out.println("Printing result table for explicit table: ");
    printTableResult(function, new GridSolver());
    System.out.println("");

    System.out.println("Printing result table for implicit table: ");
    printTableResult(function, new GridWeightSolver());
    System.out.println("");
  }

  /** Метод, который выводит на экран результат работы программы для функции u(x, t) = x^3 + t^3. */
  private static void printPolynomMultResult() {
    Function function = new PolynomMultFunction();
    System.out.println("Printing explicit result for u(x, t) = x^3 * t^3. ");
    printResult(function, new GridSolver());
    System.out.println("");

    System.out.println("Printing implicit result for u(x, t) = x^3 * t^3. ");
    printResult(function, new GridWeightSolver());
    System.out.println("");

    System.out.println("Printing result table for explicit table: ");
    printTableResult(function, new GridSolver());
    System.out.println("");

    System.out.println("Printing result table for implicit table: ");
    printTableResult(function, new GridWeightSolver());
    System.out.println("");
  }

  /**
   * Метод, который выводит на экран результат работы программы для функции u(x, t) = sin(2t + 1) *
   * cos(2x).
   */
  private static void printHyperbPlusResult() {
    Function function = new HyperbPlusFunc();
    System.out.println("Printing explicit result for u(x, t) = sin(2t + 1) + cos(2x). ");
    printResult(function, new GridSolver());
    System.out.println("");

    System.out.println("Printing implicit result for u(x, t) = sin(2t + 1) + cos(2x). ");
    printResult(function, new GridWeightSolver());
    System.out.println("");

    System.out.println("Printing result table for explicit table: ");
    printTableResult(function, new GridSolver());
    System.out.println("");

    System.out.println("Printing result table for implicit table: ");
    printTableResult(function, new GridWeightSolver());
    System.out.println("");
  }

  /**
   * Метод, который выводит на экран результат работы программы для функции u(x, t) = sin(2t + 1) *
   * cos(2x).
   */
  private static void printHyperbMultResult() {
    Function function = new HyperbMultFunction();
    System.out.println("Printing explicit result for u(x, t) = sin(2t + 1) * cos(2x). ");
    printResult(function, new GridSolver());
    System.out.println("");

    System.out.println("Printing implicit result for u(x, t) = sin(2t + 1) * cos(2x). ");
    printResult(function, new GridWeightSolver());
    System.out.println("");

    System.out.println("Printing result table for explicit table: ");
    printTableResult(function, new GridSolver());
    System.out.println("");

    System.out.println("Printing result table for implicit table: ");
    printTableResult(function, new GridWeightSolver());
    System.out.println("");
  }

  /** Метод, который печатает таблицу результата работы метода сеток. */
  private static void printResult(Function function, GridMethodSolver solver) {
    String[][] table = new String[N + 2][M + 2];
    table[0][0] = "t | x";

    solver.getValue(N, M, T, function);
    List<List<Double>> solutionTable = solver.getSolutionTable();

    for (int j = 0; j < M + 1; j++) {
      table[0][j + 1] = String.valueOf(j * 1d / N);
    }

    for (int i = 0; i < N + 1; i++) {
      double xI = i * T / M;
      table[i + 1][0] = String.valueOf(xI);

      for (int j = 0; j < M + 1; j++) {
        table[i + 1][j + 1] = String.valueOf(solutionTable.get(i).get(j));
      }
    }

    printer.print(table);
  }

  /** Метод, который печатает таблицу результатов работы метода сеток на функции. */
  private static void printTableResult(Function function, GridMethodSolver gridSolver) {
    String[][] table = new String[listN.size() + 1][4];

    table[0][0] = "h";
    table[0][1] = "tau";
    table[0][2] = "|| J_ex - u^(h, tau) ||";
    table[0][3] = "|| u^(h, tau) - u^(2h, tau1)||";

    for (int j = 0; j < listN.size(); j++) {
      int n = listN.get(j);
      table[j + 1][0] = String.valueOf(T / M);

      int m = listM.get(j);
      table[j + 1][1] = String.valueOf(T / m);

      gridSolver.getValue(n, m, T, function);
      List<List<Double>> fstGridTable = gridSolver.getSolutionTable();
      List<List<Double>> accurateTable = findAccurateSolutions(function, n, m);
      double fstNorm = findNorm(fstGridTable, accurateTable);
      table[j + 1][2] = String.valueOf(fstNorm);

      gridSolver.getValue(n / 2, m / 2, T, function);
      List<List<Double>> sndGridTable = gridSolver.getSolutionTable();
      double sndNorm = findNorm(fstGridTable, sndGridTable);
      table[j + 1][3] = String.valueOf(sndNorm);
    }

    printer.print(table);
  }

  private static List<List<Double>> findAccurateSolutions(Function function, int n, int m) {
    List<List<Double>> resultTable = new ArrayList<>();
    AccurateSolver solver = new AccurateSolver();

    for (int i = 0; i <= m; i++) {
      List<Double> solutions = new ArrayList<>();
      for (int j = 0; j <= n; j++) {
        double value = solver.getValue(j * 1d / n, i * T / m, function);
        solutions.add(value);
      }
      resultTable.add(solutions);
    }

    return resultTable;
  }

  private static double findNorm(List<List<Double>> fstTable, List<List<Double>> sndTable) {
    double maxValue = -1d;
    int size1 = 5;
    int size2 = 5;

    for (int i = 0; i < size1; i++) {
      for (int j = 0; j < size2; j++) {
        double fstValue =  abs(fstTable.get(i).get(j));
        double sndValue = abs(sndTable.get(i).get(j));
        maxValue = max(maxValue, abs(fstValue - sndValue));
      }
    }

    return maxValue;
  }

  /**
   * Метод, который печатает вводные данные программы.
   */
  public static void printIntroduction() {
    System.out.println("Task 5 for Computational practice lessons.");
    System.out.println("Program was written by Stepyrev Daniil, student of 343 group.");
    System.out.println("The second variant was used in all methods.");
    System.out.println("");
    System.out.println("Start params:");
    System.out.println("du/dt = cosx * d2u/d2x + x * du/dx + f(x, t),");
    System.out.println("u(x, 0) = fi(x), 0 <= x <= 1, ");
    System.out.println("du/dx(0, t) = a(t), u(1, t) + du/dx(1, t) = b(t), 0 <= t <= 0.1.");
    System.out.println("");
  }
}
