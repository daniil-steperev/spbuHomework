package group343.stepyrev.lab3_2;

import group343.stepyrev.lab2_1.FunctionPoint;
import java.util.LinkedList;
import java.util.List;

public class DerivativeFinder {

  private Double A;
  private Integer M;
  private Double H;

  private List<FunctionPoint> points;

  public DerivativeFinder(Double A, Integer M, Double H) {
    this.A = A;
    this.M = M;
    this.H = H;

    points = initializePoints(A, M, H);
  }

  public List<FunctionPoint> getPointsList() {
    return points;
  }

  private List<FunctionPoint> initializePoints(Double a, Integer m, Double h) {
    List<FunctionPoint> pointList = new LinkedList<>();

    for (int i = 0; i < m + 1; i++) {
      double x = a + i * h;
      double fX = getFunctionValue(x);
      pointList.add(new FunctionPoint(x, fX));
    }

    return pointList;
  }

  public List<DerivativePackage> getDerivativePackage() {
    List<DerivativePackage> packages = new LinkedList<>();

    for (int i = 0; i < points.size(); i++) {
      double x = points.get(i).getValue();
      double fX = points.get(i).getFunctionValue();
      double fstDer = countFstDerivative(i);
      double sndDer = countSndDerivative(i);

      double absoluteFstValue = getFstDer(x);
      double absoluteSndValue = getSndDer(x);

      double absFstDer = Math.abs(fstDer - absoluteFstValue);
      double absSndDer = Math.abs(sndDer - absoluteSndValue);

      DerivativePackage pack = new DerivativePackage(x, fX, fstDer, sndDer, absFstDer, absSndDer);
      packages.add(pack);
    }

    return packages;
  }

  private double countFstDerivative(int n) {
    if (n == 0) {
      return
          (-3 * points.get(n).getFunctionValue() + 4 * points.get(n + 1).getFunctionValue() - points
              .get(n + 2).getFunctionValue())
              / (2 * H);
    }

    if (n == points.size() - 1) {
      return
          (3 * points.get(n).getFunctionValue() - 4 * points.get(n - 1).getFunctionValue() + points
              .get(n - 2).getFunctionValue())
              / (2 * H);
    }

    return (points.get(n + 1).getFunctionValue() - points.get(n - 1).getFunctionValue()) / (2 * H);
  }

  private double countSndDerivative(int n) {
    if (n == 0 || n == points.size() - 1) {
      return 0;
    }

    return (points.get(n + 1).getFunctionValue() - 2 * points.get(n).getFunctionValue() + points
        .get(n - 1).getFunctionValue()) / (H * H);
  }


  private double getFunctionValue(double x) {
    return Math.exp(4.5 * x);
  }

  private double getFstDer(double x) {
    return 4.5 * getFunctionValue(x);
  }

  private double getSndDer(double x) {
    return 4.5 * 4.5 * getFunctionValue(x);
  }

}
