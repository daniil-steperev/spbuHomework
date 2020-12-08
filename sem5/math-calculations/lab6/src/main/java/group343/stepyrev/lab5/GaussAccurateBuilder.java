package group343.stepyrev.lab5;

public class GaussAccurateBuilder {
  private double A;
  private double B;
  private Integer M;
  private double H;

  public GaussAccurateBuilder(double a, double b, Integer m) {
    A = a;
    B = b;
    M = m;
    H = (B - A) / M;
  }

  public double build() {
    double integralValue = 0;

    for (int k = 0; k < M; k++) {
      double zK = A + k * H;
      double x1 = 1d / Math.sqrt(3) * H / 2 + (zK + H / 2);
      double x2 = -1d / Math.sqrt(3) * H / 2 + (zK + H / 2);

      integralValue += H / 2 * (getFunctionValue(x1) + getFunctionValue(x2));
    }

    return integralValue;
  }

  public double getFunctionValue(double x) {
    return Math.cos(x) / Math.sqrt(1 - x * x);
  }
}
