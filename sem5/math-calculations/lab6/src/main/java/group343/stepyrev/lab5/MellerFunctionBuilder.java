package group343.stepyrev.lab5;

import group343.stepyrev.util.PrettyPrinter;

public class MellerFunctionBuilder {

  private double A;
  private double B;
  private Integer N;

  private final static PrettyPrinter printer = new PrettyPrinter(System.out);

  public MellerFunctionBuilder(double a, double b, int n) {
    A = a;
    B = b;
    N = n;
  }

  public void buildFunction() {
    double value = 0;
    System.out.println("Узлы и коэффициенты: ");
    String[][] table = new String[N + 1][3];
    table[0][0] = "xK";
    table[0][1] = "f(xK)";
    table[0][2] = "Ak";

    System.out.println(" ");
    for (int k = 1; k <= N; k++) {
      double x = (2 * k - 1) * Math.PI / (2 * N);
      double fX = getFunctionValue(x);
      table[k][0] = String.valueOf(Math.cos(x));
      table[k][1] = String.valueOf(fX);
      table[k][2] = String.valueOf(Math.PI / N);

      value += getFunctionValue((2 * k - 1) * Math.PI / (2 * N));
    }
    printer.print(table);

    value *= Math.PI / N;
    System.out.println(String.format(
        "Вычисленный приближенно интеграл от ρ(x) * f(x) при помощи КФ Мелера с N (N = %d) узлами: %f",
        N,
        value));

    //checkResult(value);
  }

  private void checkResult(double value) {
    MellerAccurateBuilder builder = new MellerAccurateBuilder(A, B, 10000);
    double foundIntegral = builder.build();

    System.out.println(String.format("|I - J| = %f", Math.abs(value - foundIntegral)));
  }

  private double getWeightFunctionValue(double x) {
    return 1d / (Math.sqrt(1 - x * x));
  }

  private double getFunctionValue(double x) {
    return Math.cos(Math.cos(x));
  }

  private class MellerAccurateBuilder extends GaussAccurateBuilder {

    public MellerAccurateBuilder(double a, double b, Integer m) {
      super(a, b, m);
    }

    @Override
    public double getFunctionValue(double x) {
      return Math.cos(x) / (Math.sqrt(1 - x * x));
    }
  }
}
