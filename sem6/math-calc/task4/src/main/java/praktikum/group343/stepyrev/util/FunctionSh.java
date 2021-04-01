package praktikum.group343.stepyrev.util;

/** Класс, который реализует функцию sh(x * y). */
public class FunctionSh {

  /** Метод, который возвращает значение функции sh(x * y) в точке x, y. */
  public double getValue(double x, double y) {
    return Math.sinh(x * y);
  }

}
