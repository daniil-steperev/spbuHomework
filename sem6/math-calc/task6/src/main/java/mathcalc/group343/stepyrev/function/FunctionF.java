package mathcalc.group343.stepyrev.function;

/** Метод, который реализует функцию f(x) = 1 - x. */
public class FunctionF implements Function {

  @Override
  public double getValue(double x) {
    return 1 - x;
  }

  @Override
  public double getFstDer(double x) {
    return -1.0;
  }
}
