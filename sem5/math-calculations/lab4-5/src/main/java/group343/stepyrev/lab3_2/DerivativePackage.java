package group343.stepyrev.lab3_2;

/**
 * A class that contains x, f(x), f'(x), f''(x)
 */
public class DerivativePackage {

  private double x;
  private double fX;
  private double fstDerivative;
  private double sndDerivative;

  private double absFstDer;
  private double absSndDer;

  public DerivativePackage(double x, double fX, double fstDer, double sndDer, double absFstDer,
      double absSndDer) {
    this.x = x;
    this.fX = fX;
    this.fstDerivative = fstDer;
    this.sndDerivative = sndDer;
    this.absFstDer = absFstDer;
    this.absSndDer = absSndDer;
  }

  public double getX() {
    return x;
  }

  public double getfX() {
    return fX;
  }

  public void setfX(double fX) {
    this.fX = fX;
  }

  public double getFstDerivative() {
    return fstDerivative;
  }

  public void setFstDerivative(double fstDerivative) {
    this.fstDerivative = fstDerivative;
  }

  public double getSndDerivative() {
    return sndDerivative;
  }

  public void setSndDerivative(double sndDerivative) {
    this.sndDerivative = sndDerivative;
  }

  public double getAbsFstDer() {
    return absFstDer;
  }

  public double getAbsSndDer() {
    return absSndDer;
  }
}
