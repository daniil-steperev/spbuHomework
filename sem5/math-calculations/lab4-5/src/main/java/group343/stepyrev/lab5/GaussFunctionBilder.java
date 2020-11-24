package group343.stepyrev.lab5;

import group343.stepyrev.util.PrettyPrinter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GaussFunctionBilder {

  private double A;
  private double B;
  private Integer M;

  private final static PrettyPrinter printer = new PrettyPrinter(System.out);

  public GaussFunctionBilder(double a, double b, Integer m) {
    A = a;
    B = b;
    M = m;
  }

  public void buildFunction(double accurateValue) {
    List<Double> moments = calculateMoments();
    printMoments(moments);

    List<Double> ortoCoefficients = calculateCoefByKramer(moments);
    printOrthogonalPolynom(ortoCoefficients);

    List<Double> xs = calculateXs(ortoCoefficients);
    List<Double> coefs = calculateCoefs(xs, moments);

    printPolynomKnots(xs);
    printCoefs(coefs);
    checkCoefs(coefs, moments);
    checkAccuracy(xs, coefs);

    double integralValue = countIntegral(xs, coefs);
    printIntegralResult(integralValue);
    checkResult(integralValue, accurateValue);
  }

  private void checkAccuracy(List<Double> xs, List<Double> coefs) {
    double x1 = xs.get(0);
    double x2 = xs.get(1);

    double A1 = coefs.get(0);
    double A2 = coefs.get(1);

    TrapezeIntegralFinder finder = new TrapezeIntegralFinder(A, B);

    //double integralFst = finder.countIntegral(initializePoints(m, h, 0)); // f(x) = 1
    //double integralSnd = finder.countIntegral(initializePoints(m, h, 1)); // f(x) = x
    //double integralThd = finder.countIntegral(initializePoints(m, h, 2)); // f(x) = x^2
    //double integralFour = finder.countIntegral(initializePoints(m, h, 3)); // f(x) = x^3

    double integralFst = (finder.countIntegral(0));
    double integralSnd = (finder.countIntegral(1));
    double integralThd = (finder.countIntegral(2));
    double integralFour = (finder.countIntegral(3));

    double countedFstIntegral = A1 * 1 + A2 * 1;
    double countedSndIntegral = A1 * x1 + A2 * x2;
    double countedThdIntegral = A1 * x1 * x1 + A2 * x2 * x2;
    double countedFourIntegral = A1 * x1 * x1 * x1 + A2 * x2 * x2 * x2;

    System.out.println("Проверка точности КФ: ");
    String[][] table = new String[5][4];

    table[0][0] = "f(x)";
    table[0][1] = "I (Приближенное значение интеграла)";
    table[0][2] = "J (Значение интеграла с помощью КФ)";
    table[0][3] = "|I - J|";

    table[1][0] = "1";
    table[1][1] = String.valueOf(integralFst);
    table[1][2] = String.valueOf(countedFstIntegral);
    table[1][3] = String.valueOf(Math.abs( integralFst - countedFstIntegral));

    table[2][0] = "x";
    table[2][1] = String.valueOf(integralSnd);
    table[2][2] = String.valueOf(countedSndIntegral);
    table[2][3] = String.valueOf(Math.abs(integralSnd - countedSndIntegral));

    table[3][0] = "x^2";
    table[3][1] = String.valueOf(integralThd);
    table[3][2] = String.valueOf(countedThdIntegral);
    table[3][3] = String.valueOf(Math.abs(integralThd - countedThdIntegral));

    table[4][0] = "x^3";
    table[4][1] = String.valueOf(integralFour);
    table[4][2] = String.valueOf(countedFourIntegral);
    table[4][3] = String.valueOf(Math.abs(integralFour - countedFourIntegral));

    printer.print(table);
    System.out.println(" ");
  }

  private void checkResult(double integralValue, double accurateValue) {
    System.out
        .println(String.format("Проверка точности КФ: |J - I| = %f, где J - значение интеграла, вычисленное "
                + "при помощи составной КФ Гаусса, \nI - значение интеграла, вычисленное при помощи КФ типа Гаусса.",
            Math.abs(integralValue - accurateValue)));
  }

  private void checkCoefs(List<Double> coefs, List<Double> moments) {
    double A1 = coefs.get(0);
    double A2 = coefs.get(1);
    double zerMom = moments.get(0);

    System.out.println("Проверка коэффициентов КФ: ");
    System.out.println(String.format("A1 + A2 = %f + %f = %f", A1, A2, A1 + A2));
    System.out.println(String.format("μ0 = %f", zerMom));
    System.out.println(String.format("|(A1 + A2) - μ0| = %f", Math.abs(A1 + A2 - zerMom)));
    System.out.println(" ");
  }

  private void printIntegralResult(double integralValue) {
    System.out.println(String.format(
        "Вычисленный приближенно интеграл от ρ(x) * f(x) \n"
            + "при помощи КФ типа Гаусса с 2-мя узлами: %f",
        integralValue));
  }

  private double countIntegral(List<Double> xs, List<Double> coefs) {
    double x1 = xs.get(0);
    double x2 = xs.get(1);
    double fX1 = getFunctionValue(x1);
    double fX2 = getFunctionValue(x2);

    double A1 = coefs.get(0);
    double A2 = coefs.get(1);

    return (A1 * fX1 + A2 * fX2);
  }

  private void printCoefs(List<Double> coefs) {
    System.out.println("Коэффициенты КФ: ");
    String[][] table = new String[3][2];
    table[0][0] = "Ak";
    table[0][1] = " ";

    table[1][0] = "A1";
    table[1][1] = String.valueOf(coefs.get(0));

    table[2][0] = "A2";
    table[2][1] = String.valueOf(coefs.get(1));

    printer.print(table);
    System.out.println(" ");
  }

  private void printPolynomKnots(List<Double> xs) {
    double x1 = xs.get(0);
    double x2 = xs.get(1);

    System.out.println("\nУзлы КФ: ");
    String[][] table = new String[3][2];
    table[0][0] = "xK";
    table[0][1] = "f(xK)";

    double fX1 = getFunctionValue(x1) * getWeightFunctionValue(x1);
    double fX2 = getFunctionValue(x2) * getWeightFunctionValue(x2);

    table[1][0] = String.valueOf(x1);
    table[1][1] = String.valueOf(fX1);
    table[2][0] = String.valueOf(x2);
    table[2][1] = String.valueOf(fX2);

    printer.print(table);
    System.out.println(" ");
  }

  private List<Double> calculateCoefs(List<Double> xs, List<Double> moments) {
    List<Double> coefs = new ArrayList<>();
    double x1 = xs.get(0);
    double x2 = xs.get(1);

    double zerMom = moments.get(0);
    double fstMom = moments.get(1);
    double sndMom = moments.get(2);
    double thdMom = moments.get(3);

    double a1 = (fstMom - x2 * zerMom) / (x1 - x2);
    double a2 = (fstMom - x1 * zerMom) / (x2 - x1);
    coefs.add(a1);
    coefs.add(a2);

    return coefs;
  }

  private List<Double> calculateXs(List<Double> ortoCoefficients) {
    List<Double> coefs = new ArrayList<>();
    double a1 = ortoCoefficients.get(0);
    double a2 = ortoCoefficients.get(1);
    double discriminant = a1 * a1 - 4 * a2;

    double x1 = (-a1 - Math.sqrt(discriminant)) / 2;
    double x2 = (-a1 + Math.sqrt(discriminant)) / 2;
    coefs.add(x1);
    coefs.add(x2);

    return coefs;
  }

  private void printOrthogonalPolynom(List<Double> coefficients) {
    double a1 = coefficients.get(0);
    double a2 = coefficients.get(1);
    System.out.print("Ортогональный многочлен: x^2 ");
    if (a1 >= 0) {
      System.out.print(String.format("+ %f * x ", a1));
    } else {
      System.out.print(String.format("- %f * x ", -a1));
    }

    if (a2 >= 0) {
      System.out.print(String.format("+ %f.", a2));
    } else {
      System.out.print(String.format("- %f.", -a2));
    }
  }

  private List<Double> calculateCoefByKramer(List<Double> moments) {
    double zerMom = moments.get(0);
    double fstMom = moments.get(1);
    double sndMom = moments.get(2);
    double thdMom = moments.get(3);

    List<Double> coef = new ArrayList<>();
    double fstCoef = (zerMom * thdMom - sndMom * fstMom) / (fstMom * fstMom - sndMom * zerMom);
    coef.add(fstCoef);

    double sndCoef = (sndMom * sndMom - thdMom * fstMom) / (fstMom * fstMom - sndMom * zerMom);
    coef.add(sndCoef);

    return coef;
  }

  private List<Double> calculateMoments() {
    List<Double> moments = new LinkedList<>();
    TrapezeIntegralFinder finder = new TrapezeIntegralFinder(A, B);

    //moments.add(finder.countIntegral(initializePoints(m, h, 0)));
    //moments.add(finder.countIntegral(initializePoints(m, h, 1)));
    //moments.add(finder.countIntegral(initializePoints(m, h, 2)));
    //moments.add(finder.countIntegral(initializePoints(m, h, 3)));
    moments.add(finder.countIntegral(0));
    moments.add(finder.countIntegral(1));
    moments.add(finder.countIntegral(2));
    moments.add(finder.countIntegral(3));

    return moments;
  }

  private List<Double> initializePoints(int m, double h, int deg) {
    List<Double> pointList = new LinkedList<>();

    for (int i = 0; i < m + 1; i++) {
      double x = A + i * h;

      double fX = getWeightFunctionValue(x);
      switch (deg) {
        case 0:
          break;
        case 1:
          fX *= x;
          break;
        case 2:
          fX *= x * x;
          break;
        case 3:
          fX *= x * x * x;
          break;
        default:
          fX *= Math.pow(x, deg);
          break;
      }
      pointList.add(fX);
    }

    return pointList;
  }

  private double getWeightFunctionValue(double x) {
    return 1d / Math.sqrt(1 - x * x);
  }

  private double getFunctionValue(double x) {
    return Math.cos(x);
  }

  private void printMoments(List<Double> moments) {
    System.out.println("Моменты весовой функции ρ(x) = x^(1/2):");
    String[][] table = new String[5][2];
    table[0][0] = "μK";
    table[0][1] = " ";

    table[1][0] = "μ0";
    table[1][1] = String.valueOf(moments.get(0));

    table[2][0] = "μ1";
    table[2][1] = String.valueOf(moments.get(1));

    table[3][0] = "μ2";
    table[3][1] = String.valueOf(moments.get(2));

    table[4][0] = "μ3";
    table[4][1] = String.valueOf(moments.get(3));

    printer.print(table);
    System.out.println(" ");
  }
}
