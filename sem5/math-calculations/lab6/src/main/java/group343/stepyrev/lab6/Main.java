package group343.stepyrev.lab6;

import group343.stepyrev.util.FunctionPoint;
import group343.stepyrev.util.PrettyPrinter;
import java.util.List;

public class Main {

  private static double x0 = 0;
  private static double y0 = 0;
  private static double h = 0.1;
  private static int N = 10;
  private static CauchyFinder finder;

  private final static PrettyPrinter printer = new PrettyPrinter(System.out);

  public static void main(String[] args) {
    finder = new CauchyFinder(x0, y0, h, N);

    System.out.println("Таблица значений точного решения: ");
    List<FunctionPoint> accuratePoints = finder.findAccurateSolve();
    printTableValues(finder.findAccurateSolve());

    System.out.println("Таблица значений решения, найденного с помощью разложения в ряд Тейлора:");
    printTableValuesWithError(finder.findTaylorSolve(), accuratePoints);

    System.out.println("Таблица значений решения, найденного с помощью метода Адамса 4-го порядка:");
    printAdamSolve(finder.findAdamsSolve(), accuratePoints);

    System.out.println("Таблица значений решения, найденного с помощью метода Рунге-Кутта:");
    printValuesStartedFromFirst(finder.findRungeKuttaSolve(), accuratePoints);

    System.out.println("Таблица значений решения, найденного с помощью метода Эйлера:");
    printValuesStartedFromFirst(finder.findEulerSolve(), accuratePoints);

    System.out.println("Таблица значений решения, найденного с помощью метода Эйлера I:");
    printValuesStartedFromFirst(finder.findModifiedEulerSolve(), accuratePoints);

    System.out.println("Таблица значений решения, найденного с помощью метода Эйлера II:");
    printValuesStartedFromFirst(finder.findTwiceModifiedEulerSolve(), accuratePoints);
  }

  private static void printTableValues(List<FunctionPoint> points) {
    String[][] table = new String[N + 4][3];

    table[0][0] = "k";
    table[0][1] = "xK";
    table[0][2] = "yK";

    for (int i = -2; i <= N; i++) {
      FunctionPoint point = points.get(i + 2);
      table[i + 3][0] = String.valueOf(i);
      table[i + 3][1] = String.valueOf(point.getValue());
      table[i + 3][2] = String.valueOf(point.getFunctionValue());
    }

    printer.print(table);
    System.out.println("");
  }

  private static void printTableValuesWithError(List<FunctionPoint> points,
      List<FunctionPoint> accuratePoints) {
    String[][] table = new String[N + 4][4];

    table[0][0] = "k";
    table[0][1] = "xK";
    table[0][2] = "yK";
    table[0][3] = "Абсолютная погрешность";

    for (int i = -2; i <= N; i++) {
      FunctionPoint point = points.get(i + 2);
      FunctionPoint accuratePoint = accuratePoints.get(i + 2);
      double x = point.getValue();
      double y = point.getFunctionValue();
      double accurateY = accuratePoint.getFunctionValue();

      table[i + 3][0] = String.valueOf(i);
      table[i + 3][1] = String.valueOf(x);
      table[i + 3][2] = String.valueOf(y);
      table[i + 3][3] = String.valueOf(Math.abs(y - accurateY));
    }

    printer.print(table);
    double currentY = points.get(N + 2).getFunctionValue();
    double accureteY = accuratePoints.get(N + 2).getFunctionValue();
    System.out.println(String
        .format("Абсолютная погрешность последнего значения (y(xN)): %f",
            Math.abs(currentY - accureteY)));
    System.out.println("");

  }

  private static void printValuesStartedFromFirst(List<FunctionPoint> points,
      List<FunctionPoint> accuratePoints) {
    String[][] table = new String[N + 1][4];

    table[0][0] = "k";
    table[0][1] = "xK";
    table[0][2] = "yK";
    table[0][3] = "Абсолютная погрешность";

    for (int i = 1; i <= N; i++) {
      FunctionPoint point = points.get(i - 1);
      FunctionPoint accuratePoint = accuratePoints.get(i + 2);
      double x = point.getValue();
      double y = point.getFunctionValue();
      double accurateY = accuratePoint.getFunctionValue();

      table[i][0] = String.valueOf(i);
      table[i][1] = String.valueOf(x);
      table[i][2] = String.valueOf(y);
      table[i][3] = String.valueOf(Math.abs(y - accurateY));
    }

    printer.print(table);
    double currentY = points.get(N - 1).getFunctionValue();
    double accureteY = accuratePoints.get(N + 2).getFunctionValue();
    System.out.println(String
        .format("Абсолютная погрешность последнего значения (y(xN)): %f",
            Math.abs(currentY - accureteY)));
    System.out.println("");

  }

  private static void printAdamSolve(List<FunctionPoint> points,
      List<FunctionPoint> accuratePoints) {
    String[][] table = new String[N - 2][4];

    table[0][0] = "k";
    table[0][1] = "xK";
    table[0][2] = "yK";
    table[0][3] = "Абсолютная погрешность";

    for (int i = 3; i <= N; i++) {
      FunctionPoint point = points.get(i - 3);
      FunctionPoint accuratePoint = accuratePoints.get(i + 2);
      double x = point.getValue();
      double y = point.getFunctionValue();
      double accurateY = accuratePoint.getFunctionValue();

      table[i - 3][0] = String.valueOf(i);
      table[i - 3][1] = String.valueOf(x);
      table[i - 3][2] = String.valueOf(y);
      table[i - 3][3] = String.valueOf(Math.abs(y - accurateY));
    }

    printer.print(table);
    double currentY = points.get(points.size() - 1).getFunctionValue();
    double accureteY = accuratePoints.get(N + 2).getFunctionValue();
    System.out.println(String
        .format("Абсолютная погрешность последнего значения (y(xN)): %f",
            Math.abs(currentY - accureteY)));
    System.out.println("");

  }
}
