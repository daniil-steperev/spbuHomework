package ru.group343.stepyrev.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import ru.group343.stepyrev.function.Function;

/** Класс, который реализует метод сеток с весами. */
public class GridWeightSolver implements GridMethodSolver {

  private double shiftH;
  private double shiftT;

  private int N;
  private int M;
  private Function function;

  private final double sigma = 1d;
  private Map<Integer, List<Double>> mapU;

  /** Метод, который реализует метод сеток. */
  public double getValue(int N, int M, double T, Function function) {
    this.function = function;
    this.N = N;
    this.M = M;
    mapU = new HashMap<>();
    shiftH = (1d) / N;
    shiftT = T / M;

    findZeroU();
    for (int k = 1; k <= M; k++) {
      findIU(k);
    }

    return mapU.get(M).get(N);
  }

  @Override
  public List<List<Double>> getSolutionTable() {
    List<List<Double>> resultTable = new ArrayList<>();
    for (Entry<Integer, List<Double>> entry : mapU.entrySet()) {
      resultTable.add(entry.getValue());
    }

    return resultTable;
  }

  /** Метод, который заполняет ui^0. */
  private void findZeroU() {
    List<Double> zeroU = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      double x = i * shiftH;
      double value = function.getValue(x, 0d);
      zeroU.add(value);
    }

    mapU.put(0, zeroU);
  }

  /** Метод, который заполняет ui^k. */
  private void findIU(int k) {
    DifferenceMethodSolver methodSolver = new DifferenceMethodSolver();
    List<Double> listIK =
        methodSolver.performProcess(mapU, shiftH, shiftT, sigma, function, N, k);
    mapU.put(k, listIK);
  }
}
