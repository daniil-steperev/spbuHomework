package group343.stepyrev.lab2_2;

import group343.stepyrev.util.FunctionPoint;
import group343.stepyrev.util.PrettyPrinter;
import java.util.List;
import java.util.Scanner;

public class Main {

  private static double A;
  private static double B;
  private static Integer M;
  private static Integer N;
  private static Double X;

  private static Scanner scanner;
  private static NewtonPolynomial polynomial;

  private final static PrettyPrinter printer = new PrettyPrinter(System.out);

  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    printIntroduction();

    readInitialParams();
    printInitialParams();
    polynomial = new NewtonPolynomial(M, A, B);

    List<FunctionPoint> funcTable = polynomial.getFunctionPoints();
    System.out.println("Таблица значений: ");
    printFuncTable(funcTable);

    String answer = "yes";
    while (answer.equals("yes")) {
      doPolynomialCalculations();

      System.out.println("Если хотите продолжить вычисления с новыми значениями x и n, введите yes.");
      answer = scanner.nextLine();
    }
  }

  private static void doPolynomialCalculations() {
    System.out.println("Введите точку интерполирования x: ");
    X = readDouble();
    readInterpolationPolDegree();

    System.out.println(String
        .format("Первые %d значений таблицы после сортировки относительно удаления от x (%.5f): ", N + 1, X));
    List<FunctionPoint> funcTable = polynomial.sortFunctionPoints(X, N);
    printFuncTable(funcTable);

    Double polValue = polynomial.getPolynomialValue();
    System.out.println(
        String.format("Значение интерполяционного многочлена в точке %.5f: %.14f", X, polValue));

    Double absoluteValue = polynomial.getFunctionValue(X);
    Double difference = Math.abs(absoluteValue - polValue);
    System.out.println(String.format("Значение абсолютной фактической погрешности: %.14f", difference));
  }

  /**
   * Метод, в котором считываются исходные параметры задачи.
   */
  private static void readInitialParams() {
    System.out.println("Введите число значений в таблице: ");
    M = readInt() - 1;
    while (M <= 0) {
      System.out.println("Введите M >= 2.");
      M = readInt() - 1;
    }

    System.out.println("Введите левый конец отрезка: ");
    A = readDouble();

    System.out.println("Введите правый конец отрезка: ");
    B = readDouble();
  }

  /**
   * Метод, который считывает степень интерполяционного многочлена.
   */
  private static void readInterpolationPolDegree() {
    System.out
        .println(String.format("Введите степень интерполяционного многочлена N (N  <= %d): ", M));
    N = readInt();
    while (N > M || N <= 0) {
      System.out.println(String.format("Введите целое значение N: N >= 1, N <= %d: ", M));
      N = readInt();
    }
  }

  private static Double readDouble() {
    String readValue = scanner.nextLine();
    while (!isDoubleValue(readValue)) {
      System.out.println("Пожалуйста, введите корректное значение (дробное или целое число).");
      readValue = scanner.nextLine();
    }

    return Double.parseDouble(readValue);
  }

  /**
   * Метод, который считывает из консоли  число.
   *
   * @return - возвращает целое число
   */
  private static Integer readInt() {
    String readValue = scanner.nextLine();
    while (!isIntValue(readValue)) {
      System.out.println("Пожалуйста, введите целое значение.");
      readValue = scanner.nextLine();
    }

    return Integer.parseInt(readValue);
  }

  /**
   * Метод, которые проверяет, является ли целым числом строка.
   *
   * @param value - исходная строка
   * @return - true, если является целым числом, иначе false
   */
  private static boolean isIntValue(String value) {
    try {
      Integer.parseInt(value);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private static boolean isDoubleValue(String value) {
    try {
      Double.parseDouble(value);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Метод, который печатает таблицу значений.
   */
  private static void printFuncTable(List<FunctionPoint> table) {
    String[][] convertedTable = new String[table.size() + 1][2];
    convertedTable[0][0] = "xK";
    convertedTable[0][1] = "f(xK)";

    for (int i = 0; i < table.size(); i++) {
      Double key = table.get(i).getValue();
      Double value = table.get(i).getFunctionValue();
      convertedTable[i + 1][0] = String.format("%.2f", key);
      convertedTable[i + 1][1] = String.valueOf(value);

      //System.out.println(String.format("x%d: %.2f, f(x%d): %.2f", i, key, i, value));
    }

    printer.print(convertedTable);
    System.out.println(" ");
  }

  /**
   * Метод, который печатает исходные параметры задачи.
   */
  private static void printInitialParams() {
    System.out.println("");
    System.out.println(String.format("Число значение в таблице: %d", M + 1));
    System.out.println(String.format("Исходный промежуток: [%.2f; %.2f]", A, B));
  }

  /**
   * Метод, который печатает описание лабораторной работы.
   */
  private static void printIntroduction() {
    System.out.println("Лабораторная работа номер 2");
    System.out.println("На тему: \"Численные методы решения нелинейных уравнений\".");
    System.out.println("Работа выполнена студентом 343 группы СПбГУ Степыревом Даниилом");
    System.out.println("Вариант 12.");
    System.out.println("Функция, приближенное значение которой будет вычисляться: "
        + "exp(-x) - x^2 / 2.");
    System.out.println("\n");
  }
}
