package ru.group343.stepyrev.solver;

import static java.lang.Math.pow;
import static ru.group343.stepyrev.util.ParamsUtil.alpha1;
import static ru.group343.stepyrev.util.ParamsUtil.alpha2;
import static ru.group343.stepyrev.util.ParamsUtil.beta1;
import static ru.group343.stepyrev.util.ParamsUtil.beta2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import ru.group343.stepyrev.function.Function;
import ru.group343.stepyrev.function.FunctionA;
import ru.group343.stepyrev.function.FunctionB;

/** Класс, который реализует метод сеток. */
public class GridSolver implements GridMethodSolver {

  private double shiftH;
  private double shiftT;

  private int N;
  private int M;
  private Function function;
  private Function functionA;
  private Function functionB;

  private Map<Integer, List<Double>> mapU;

  /** Метод, который реализует метод сеток. */
  public double getValue(int N, int M, double T, Function function) {
    this.function = function;
    this.functionA = new FunctionA();
    this.functionB = new FunctionB();
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
    List<Double> listIK = new ArrayList<>();
    for (int i = 1; i < N; i++) {
      // ui^(k - 1)
      double xI = i * shiftH;
      double tK1 = (k - 1) * shiftT;

      double prevUMinus = mapU.get(k - 1).get(i - 1);
      double prevU = mapU.get(k - 1).get(i);
      double prevUPlus = mapU.get(k - 1).get(i + 1);
      double functionFValue = AccurateSolver.getFValue(xI, tK1, function);

      double lH =
          functionA.getValue(xI, tK1) * (prevUPlus - 2 * prevU + prevUMinus) / pow(shiftH, 2)
              + functionB.getValue(xI, tK1) * (prevUPlus - prevUMinus) / (2 * shiftH);

      double nextU = prevU + shiftT * (lH * prevU + functionFValue);
      listIK.add(nextU);
    }

    double u0k = getU0K(k, listIK);
    listIK.add(0, u0k);
    double uNk = getNK(k, listIK);
    listIK.add(uNk);
    mapU.put(k, listIK);
  }

  /** Метод, который возвращает u0^k. */
  private double getU0K(int k, List<Double> listIK) {
    double u1k = listIK.get(0);
    double u2k = listIK.get(1);
    double tK = k * shiftT;

    double alpha = alpha1 * function.getValue(0, tK) - alpha2 * function.getDerivativeXValue(0, tK);

    return (2 * shiftH * alpha + 4 * alpha2 * u1k - alpha2 * u2k)
        / (2 * shiftH * alpha1 + 3 * alpha2);
  }

  /** Метод, который возвращает uN^k. */
  private double getNK(int k, List<Double> listIK) {
    double uN1k = listIK.get(N - 2); // XXX
    double uN2k = listIK.get(N - 3); // XXX
    double tK = k * shiftT;

    double beta = beta1 * function.getValue(1, tK) + beta2 * function.getDerivativeXValue(1, tK);

    return (2 * shiftH * beta + 4 * beta2 * uN1k - beta2 * uN2k) / (2 * shiftH * beta1 + 3 * beta2);
  }
}
