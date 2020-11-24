package group343.stepyrev.lab3_2;

import group343.stepyrev.lab2_1.FunctionPoint;
import group343.stepyrev.util.PrettyPrinter;
import java.util.List;
import java.util.Scanner;

public class Main {

  private static double A;
  private static Integer M;
  private static Double H;

  private static Scanner scanner;
  private static DerivativeFinder finder;

  private final static PrettyPrinter printer = new PrettyPrinter(System.out);

  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    printIntroduction();

    String answer = "yes";
    while (answer.equals("yes")) {
      doApproximation();

      System.out
          .println("Если хотите продолжить вычисления с новыми значениями M, A, H, введите yes.");
      answer = scanner.nextLine();
    }
  }

  private static void doApproximation() {
    readInitialParams();
    printInitialParams();
    finder = new DerivativeFinder(A, M, H);

    List<FunctionPoint> funcTable = finder.getPointsList();
    System.out.println("Таблица значений: ");
    printFuncTable(funcTable);

    List<DerivativePackage> packages = finder.getDerivativePackage();
    printPackageTable(packages);
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

    System.out.println("Введите значение сдвига h > 0: ");
    H = readDouble();
    while (H <= 0) {
      System.out.println("Введите h > 0");
      H = readDouble();
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
      convertedTable[i + 1][0] = String.format("%.4f", key);
      convertedTable[i + 1][1] = String.valueOf(value);

      //System.out.println(String.format("x%d: %.2f, f(x%d): %.2f", i, key, i, value));
    }

    printer.print(convertedTable);
    System.out.println(" ");
  }

  private static void printPackageTable(List<DerivativePackage> packages) {
    String[][] outTable = new String[packages.size() + 1][6];
    outTable[0][0] = "xI";
    outTable[0][1] = "f(xI)";
    outTable[0][2] = "f'(xI)чд";
    outTable[0][3] = " |f'(xI)т - f'(xI)чд| ";
    outTable[0][4] = "f''(xI)чд";
    outTable[0][5] = " |f''(xI)т - f''(xI)чд| ";

    for (int i = 0; i < packages.size(); i++) {
      DerivativePackage derivativePackage = packages.get(i);

      double x = derivativePackage.getX();
      double fX = derivativePackage.getfX();
      double fstDer = derivativePackage.getFstDerivative();
      double sndDer = derivativePackage.getSndDerivative();
      double absFstDer = derivativePackage.getAbsFstDer();
      double absSndDer = derivativePackage.getAbsSndDer();

      outTable[i + 1][0] = String.format("%f", x);
      outTable[i + 1][1] = String.valueOf(fX);
      outTable[i + 1][2] = String.valueOf(fstDer);
      outTable[i + 1][3] = String.valueOf(absFstDer);

      if (i == 0 || i == (packages.size() - 1)) {
        outTable[i + 1][4] = "";
        outTable[i + 1][5] = "";
        continue;
      }

      outTable[i + 1][4] = String.valueOf(sndDer);
      outTable[i + 1][5] = String.valueOf(absSndDer);
    }

    printer.print(outTable);
    System.out.println(" ");
  }

  /**
   * Метод, который печатает исходные параметры задачи.
   */
  private static void printInitialParams() {
    System.out.println("");
    System.out.println(String.format("Число значение в таблице: %d", M + 1));
    System.out.println(String.format("Начальная точка A: %.2f", A));
    System.out.println(String.format("Сдвиг h: %f", H));
  }

  /**
   * Метод, который печатает описание лабораторной работы.
   */
  private static void printIntroduction() {
    System.out.println("Лабораторная работа номер 3.1");
    System.out.println("На тему: \"Нахождение производных таблично-заданной функции\n"
        + "по формулам численного дифференцирования\".");
    System.out.println("Работа выполнена студентом 343 группы СПбГУ Степыревом Даниилом");
    System.out.println("Вариант 12.");
    System.out.println("Функция, приближенное значение обратной которой будет вычисляться: "
        + "exp(4.5 * x)");
    System.out.println("\n");
  }
}
