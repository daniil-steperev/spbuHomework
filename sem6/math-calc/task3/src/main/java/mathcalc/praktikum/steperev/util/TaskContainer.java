package mathcalc.praktikum.steperev.util;

/** Класс, который используется для хранения результатов задачи. */
public class TaskContainer {
  private double lambdaK;
  private double difPrevLambdas;
  private double difAbsoluteLambdas;
  private double difNorm;
  private double errorApost;

  public double getLambdaK() {
    return lambdaK;
  }

  public void setLambdaK(double lambdaK) {
    this.lambdaK = lambdaK;
  }

  public double getDifPrevLambdas() {
    return difPrevLambdas;
  }

  public void setDifPrevLambdas(double difPrevLambdas) {
    this.difPrevLambdas = difPrevLambdas;
  }

  public double getDifAbsoluteLambdas() {
    return difAbsoluteLambdas;
  }

  public void setDifAbsoluteLambdas(double difAbsoluteLambdas) {
    this.difAbsoluteLambdas = difAbsoluteLambdas;
  }

  public double getDifNorm() {
    return difNorm;
  }

  public void setDifNorm(double difNorm) {
    this.difNorm = difNorm;
  }

  public double getErrorApost() {
    return errorApost;
  }

  public void setErrorApost(double errorApost) {
    this.errorApost = errorApost;
  }
}
