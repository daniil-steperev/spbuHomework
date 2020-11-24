package group343.stepyrev.lab4;

import static group343.stepyrev.util.SmartReader.readDouble;
import static group343.stepyrev.util.SmartReader.readInt;

import group343.stepyrev.util.PrettyPrinter;
import java.util.Scanner;

public class Main {

  private static double A;
  private static double B;
  private static Integer M;
  private static Double H;
  private static Double WEIGHT_FUNCTION = 1D;
  private static IntegralFinder finder;

  /**
   * 1: f(x) = 5;
   * 2: f(x) = 3x;
   * 3: f(x) = 3x^3 -2x^2 + 5x - 1
   * 4: f(x) = 7 * e ^ (3 * x)
   */
  private static final int FUNCTION_NUMBER = 4;

  private static Scanner scanner;

  private final static PrettyPrinter printer = new PrettyPrinter(System.out);

  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    printIntroduction();

    readInitialParams();
    H = (B - A) / M;
    printInitialParams();
    finder = new IntegralFinder(A, B, M, H, FUNCTION_NUMBER);

    printIntegrals();
  }

  private static void printIntegrals() {
    double accurateValue = finder.getAccurateIntegralValue();
    System.out.println(String.format("Точное значение интеграла: J = %f", accurateValue));
    System.out.println("");

    printAbsoluteError(accurateValue, MethodName.LEFT_RECTANGLE);
    printAbsoluteError(accurateValue, MethodName.RIGHT_RECTANGLE);
    printAbsoluteError(accurateValue, MethodName.MIDDLE_RECTANGLE);
    printAbsoluteError(accurateValue, MethodName.TRAPEZE);
    printAbsoluteError(accurateValue, MethodName.SIMPSON);
  }

  private static void printAbsoluteError(double accurateValue, MethodName methodName) {
    double value = 0d;
    String msg = "Значение интеграла, вычисленное при помощи составных формул ";

    switch (methodName) {
      case LEFT_RECTANGLE:
        value = finder.getIntegralByLeftRectangleMethod();
        msg = msg + "левых прямоугольников: J(H) = %f";
        break;
      case RIGHT_RECTANGLE:
        value = finder.getIntegralByRightRectangleMethod();
        msg = msg + "правых прямоугольников: J(H) = %f";
        break;
      case MIDDLE_RECTANGLE:
        value = finder.getIntegralByMiddleRectangleMethod();
        msg = msg + "средних прямоугольников: J(H) = %f";
        break;
      case TRAPEZE:
        value = finder.getIntegralByTrapezeMethod();
        msg = msg + "трапеций: %f";
        break;
      case SIMPSON:
        value = finder.getIntegralBySimpsonMethod();
        msg = msg + String.format("Симпсона с параметром m (m = %d): ", M) + "J(H) = %f";
        break;
    }

    double absoluteError = finder.getAbsoluteErrorValue(accurateValue, value);
    System.out.println(String.format(msg, value));
    System.out.println(String.format("Абсолютная фактическая погрешность: %f", absoluteError));
    printTheoreticalError(methodName);
    System.out.println("");

  }

  private static void printTheoreticalError(MethodName methodName) {
    double theoreticalError = finder.getTheoreticalErrorValue(methodName);
    System.out.println(String.format("Теоретическая погрешность %f: ", theoreticalError));
  }

  /**
   * Метод, в котором считываются исходные параметры задачи.
   */
  private static void readInitialParams() {
    System.out.println("Введите левый конец предела интегрирования (A): ");
    A = readDouble(scanner);
    System.out.println("Введите правый конец предела интегрирования (B): ");
    B = readDouble(scanner);
    System.out.println("Введите число промежутков деления [A; B] (m): ");
    M = readInt(scanner);
  }

  /**
   * Метод, который печатает исходные параметры задачи.
   */
  private static void printInitialParams() {
    System.out.println("");
    System.out.println(String.format("A = %f, B = %f, m = %d, H = %f", A, B, M, H));

    switch (FUNCTION_NUMBER) {
      case 1:
        System.out.println("f(x) = 5");
        break;
      case 2:
        System.out.println("f(x) = 3 * x");
        break;
      case 3:
        System.out.println("f(x) = 3 * x^3 -2 * x^2 + 5 * x - 1");
        break;
      case 4:
        System.out.println("f(x) = 7 * e ^ (3 * x)");
        break;
    }
  }

  /**
   * Метод, который печатает описание лабораторной работы.
   */
  private static void printIntroduction() {
    System.out.println("Лабораторная работа номер 4");
    System.out.println("На тему: \"Приближённое вычисление интеграла по составным квадратурным формулам\"");
    System.out.println("Работа выполнена студентом 343 группы СПбГУ Степыревом Даниилом");
    System.out.println("\n");
  }
}
