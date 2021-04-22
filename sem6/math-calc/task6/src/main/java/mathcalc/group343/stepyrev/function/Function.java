package mathcalc.group343.stepyrev.function;

/** Интерфейс, который реализует функцию. */
public interface Function {

  /** Метод, который возвращает значение функции в точке x. */
  double getValue(double x);

  /** Метод, который возвращает значение производной функции в точке x. */
  double getFstDer(double x);
}
