package group343.stepyrev.lab;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

  private static double A;
  private static double B;
  private static Integer M;
  private static Integer N;
  private static Double X;

  private static Scanner scanner;
  private static InterpolationPolynomial polynomial;

  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    printIntroduction();

    readInitialParams();
    printInitialParams();
    polynomial = new InterpolationPolynomial(M, A, B);

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
        .format("Первые %d значений таблицы после сортировки относительно удаления от x: ", N + 1));
    List<FunctionPoint> funcTable = polynomial.sortFunctionPoints(X, N);
    printFuncTable(funcTable);

    Double polValue = polynomial.getPolynomialValue();
    System.out.println(
        String.format("Значение интерполяционного многочлена в точке %.2f: %f", X, polValue));

    Double absoluteValue = polynomial.getFunctionValue(X);
    Double difference = Math.abs(absoluteValue - polValue);
    System.out.println(String.format("Значение абсолютной фактической погрешности: %.13f", difference));
  }

  /**
   * Метод, в котором считываются исходные параметры задачи.
   */
  private static void readInitialParams() {
    System.out.println("Введите число значений в таблице: ");
    M = readInt() - 1;

    System.out.println("Введите левый конец отрезка: ");
    A = readInt();

    System.out.println("Введите правый конец отрезка: ");
    B = readInt();
  }

  /**
   * Метод, который считывает степень интерполяционного многочлена.
   */
  private static void readInterpolationPolDegree() {
    System.out
        .println(String.format("Введите степень интерполяционного многочлена n (n <= %d): ", M));
    N = readInt();
    while (N > M) {
      System.out.println(String.format("Введите целое значение <= %d: ", M));
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
    for (int i = 0; i < table.size(); i++) {
      Double key = table.get(i).getValue();
      Double value = table.get(i).getFunctionValue();
      System.out.println(String.format("x%d: %.2f, f(x%d): %.2f", i, key, i, value));
    }
  }

  /**
   * Метод, который печатает исходные параметры задачи.
   */
  private static void printInitialParams() {
    System.out.println("");
    System.out.println(String.format("Число значение в таблице: %d", M + 1));
    System.out.println(String.format("Исходный промежуток: [%.0f; %.0f]", A, B));
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
