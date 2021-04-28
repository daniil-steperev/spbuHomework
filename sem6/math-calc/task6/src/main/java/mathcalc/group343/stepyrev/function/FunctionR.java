package mathcalc.group343.stepyrev.function;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/** Класс, который реализует функцию r(x) = 1 + sin(x). */
public class FunctionR implements Function {

  @Override
  public double getValue(double x) {
    return 1 + sin(x);
  }

  @Override
  public double getFstDer(double x) {
    return cos(x);
  }
}
