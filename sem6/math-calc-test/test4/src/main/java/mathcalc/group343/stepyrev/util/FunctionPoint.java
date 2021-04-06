package mathcalc.group343.stepyrev.util;

/** Класс, который реализует пару из точки и значения функции, вычисленное в ней. */
public class FunctionPoint {
  // точка x
  private final Double value;
  // значение f(x)
  private final Double functionValue;

  public FunctionPoint(Double value, Double functionValue) {
    this.value = value;
    this.functionValue = functionValue;
  }

  public Double getValue() {
    return value;
  }

  public Double getFunctionValue() {
    return functionValue;
  }
}

