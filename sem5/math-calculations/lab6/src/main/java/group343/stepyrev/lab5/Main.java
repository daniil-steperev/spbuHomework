package group343.stepyrev.lab5;

import group343.stepyrev.util.PrettyPrinter;
import java.util.Scanner;

public class Main {

  private static double GAUSS_A = -1;
  private static double GAUSS_B = 1;
  private static Integer GAUSS_M = 100;

  private static double MELLER_A = -1;
  private static double MELLER_B = 1;
  private static int MELLER_N = 2;

  private static Scanner scanner;

  private final static PrettyPrinter printer = new PrettyPrinter(System.out);

  public static void main(String[] args) {
    printIntroduction();
    scanner = new Scanner(System.in);
    System.out.println("Использовать тестовые исходные параметры задачи? (д/н)");
    String answer = scanner.next();
    while (!answer.equals("д") && !answer.equals("н")) {
      System.out.println("Пожалуйста, введите д или н.");
      answer = scanner.next();
    }

    if (answer.equals("н")) {
      getMainParams();
    }

    printIntegrals();
  }

  private static void getMainParams() {
    System.out.println("Введите значение A (для пп. 3, 4): ");
    GAUSS_A = readDouble();
    System.out.println("Введите значение B (для пп. 3, 4): ");
    GAUSS_B = readDouble();
    System.out.println("Введите m (для пп. 3, 4): ");
    GAUSS_M = readInt();

    System.out.println("Введите значение A (для п. 5): ");
    MELLER_A = readDouble();
    System.out.println("Введите значение B (для п. 5): ");
    MELLER_B = readDouble();
    System.out.println("Введите N (для п. 5): ");
    MELLER_N = readInt();
  }

  private static void printIntegrals() {
    printGaussMainParams();
    System.out.println(" ");

    GaussAccurateBuilder builder = new GaussAccurateBuilder(GAUSS_A, GAUSS_B, GAUSS_M);
    double integralValue = builder.build();
    System.out.println(String.format(
        "Вычисленный приближенно интеграл от ρ(x) * f(x) при помощи составной \nКФ Гаусса с 2-мя "
            + "узлами с числом промежутков деления [a; b] m (m = %d): %f\n", GAUSS_M,
        integralValue));

    GaussFunctionBilder gaussBilder = new GaussFunctionBilder(GAUSS_A, GAUSS_B, GAUSS_M);
    gaussBilder.buildFunction(integralValue);
    System.out.println(" ");

    printMellerMainParams();
    System.out.println(" ");
    MellerFunctionBuilder mellerBilder = new MellerFunctionBuilder(MELLER_A, MELLER_B, MELLER_N);
    mellerBilder.buildFunction();
  }

  private static void printMellerMainParams() {
    System.out.println(String.format(
        "Параметры задачи при вычислении интеграла с помощью КФ Мелера с N (N = %d) узлами.",
        MELLER_N));
    System.out
        .println(String.format("Пределы интегрирования [a; b] = [%f, %f]", MELLER_A, MELLER_B));
    System.out.println("Функция ρ(x) = 1 / sqrt(1 - x^2)");
    System.out.println("Функция f(x) = sin(x)");
    System.out.println(String.format("Число промежутков деления [a; b] N = %d", MELLER_N));
  }

  private static void printGaussMainParams() {
    System.out.println(
        "Параметры задачи при вычислении интеграла с помощью составной КФ Гаусса с 2-мя узлами и КФ типа Гаусса с 2-мя узлами.");
    System.out.println(String.format("Пределы интегрирования [a; b] = [%f, %f]", GAUSS_A, GAUSS_B));
    System.out.println("Функция ρ(x) = x^(1/2)");
    System.out.println("Функция f(x) = sin(x)");
    System.out.println(String.format("Число промежутков деления [a; b] m = %d", GAUSS_M));
  }

  /**
   * Метод, который печатает описание лабораторной работы.
   */
  private static void printIntroduction() {
    System.out.println("Лабораторная работа номер 5");
    System.out.println(
        "На тему: \"Приближённое вычисление интегралов при помощи квадратурных формул Наивысшей Алгебраической Степени Точности (КФ НАСТ)\"");
    System.out.println("Вариант 1");
    System.out.println("Работа выполнена студентом 343 группы СПбГУ Степыревом Даниилом");
    System.out.println("\n");
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
}
