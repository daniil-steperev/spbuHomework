package ru.group343.stepyrev.solver;

import java.util.List;
import ru.group343.stepyrev.function.Function;

/** Интерфейс, который реализует метод сеток. */
public interface GridMethodSolver {

  /** Метод, который реализует метод сеток. */
  double getValue(int N, int M, double T, Function function);

  /** Метод, который возвращает таблицу решений. */
  List<List<Double>> getSolutionTable();
}
