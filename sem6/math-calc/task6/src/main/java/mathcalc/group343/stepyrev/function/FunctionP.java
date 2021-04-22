package mathcalc.group343.stepyrev.function;

import static java.lang.Math.pow;

/** Класс, который реализует функцию p(x) = (2 + x) / (3 + x). */
public class FunctionP implements Function {

  @Override
  public double getValue(double x) {
    return (2 + x) / (3 + x);
  }

  @Override
  public double getFstDer(double x) {
    return 1.0 / (3 + x) - (2 + x) / pow(3 + x, 2);
  }
}
