package ru.group343.stepyrev.solver;

import static java.lang.Math.pow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ru.group343.stepyrev.function.Function;
import ru.group343.stepyrev.function.FunctionA;
import ru.group343.stepyrev.function.FunctionB;
import ru.group343.stepyrev.util.ParamsUtil;

/** Класс, который реализует метод прогонки. */
public class DifferenceMethodSolver {

  private Integer N;
  private Double shiftH;
  private Double shiftT;
  private Integer K;
  private Double sigma;

  private Function function;
  private Function functionA;
  private Function functionB;

  private final Double alpha1 = ParamsUtil.alpha1;
  private final Double alpha2 = ParamsUtil.alpha2;
  private final Double beta1 = ParamsUtil.beta1;
  private final Double beta2 = ParamsUtil.beta2;

  private List<Double> listA;
  private List<Double> listB;
  private List<Double> listC;
  private List<Double> listG;

  private List<Double> listS;
  private List<Double> listT;
  private List<Double> listY;

  private List<Double> listU;
  private Map<Integer, List<Double>> mapU;

  /** Метод, который реализует метод прогонки. */
  public List<Double> performProcess(
      Map<Integer, List<Double>> mapU,
      Double shiftH,
      Double shiftT,
      Double sigma,
      Function function,
      Integer n,
      Integer k) {
    this.N = n;
    this.shiftH = shiftH;
    this.shiftT = shiftT;
    this.K = k;
    this.mapU = mapU;
    this.listU = mapU.get(k);
    this.function = function;
    this.functionA = new FunctionA();
    this.functionB = new FunctionB();
    this.sigma = sigma;
    initLists();

    addStartCoefs();
    for (int i = 1; i <= N - 1; i++) {
      performIteration(i);
    }
    addEndCoefs();
    countSTCoefs();
    countYCoefs();

    return listY;
  }

  /** Метод, который инициализирует списки коэффициентов. */
  private void initLists() {
    this.listA = new ArrayList<>();
    this.listB = new ArrayList<>();
    this.listC = new ArrayList<>();
    this.listG = new ArrayList<>();

    listS = new ArrayList<>();
    listT = new ArrayList<>();
    listY = new ArrayList<>();
  }

  /** Метод, который выполняет одну итерацию метода прогонки. */
  private void performIteration(int i) {
    double tK = K * shiftT;
    double xI = i * shiftH;

    double funcAValue = functionA.getValue(xI, tK);
    double funcBValue = functionB.getValue(xI, tK);

    double sigmaAh = sigma * funcAValue / pow(shiftH, 2);
    double sigmaB2h = funcBValue / (2 * shiftH);

    double Ai = sigmaAh - sigmaB2h;
    double Bi = 2 * sigmaAh + 1 / shiftT;
    double Ci = sigmaAh + sigmaB2h;
    double Gi = getGi(i, K);
    listA.add(Ai);
    listB.add(Bi);
    listC.add(Ci);
    listG.add(Gi);
  }

  /** Метод, который возвращает Gi^k. */
  private double getGi(int i, int k) {
    double xI = i * shiftH;
    double tK = k * shiftT;

    double prevUMinus = mapU.get(k - 1).get(i - 1);
    double prevU = mapU.get(k - 1).get(i);
    double prevUPlus = mapU.get(k - 1).get(i + 1);
    double functionFValue = AccurateSolver.getFValue(xI, tK, function);

    double lH =
        functionA.getValue(xI, tK) * (prevUPlus - 2 * prevU + prevUMinus) / pow(shiftH, 2)
            + functionB.getValue(xI, tK) * (prevUPlus - prevUMinus) / (2 * shiftH); // TODO

    return -(1 / shiftT) * prevU - (1 - sigma) * lH * prevU - functionFValue;
  }

  /** Метод, который заполняет начальные (i = 0) коэффициенты Ai, Bi, Ci, Gi. */
  private void addStartCoefs() {
    double A0 = 0d;
    double B0 = -alpha1 * shiftH - alpha2;
    double C0 = -alpha2;

    double tK = shiftT * K;

    double alpha = function.getDerivativeXValue(0d, tK);
    double G0 = alpha * shiftH;
    listA.add(A0);
    listB.add(B0);
    listC.add(C0);
    listG.add(G0);
  }

  /** Метод, который заполняет конечные (i = n) коэффициенты Ai, Bi, Ci, Gi. */
  private void addEndCoefs() {
    double An = shiftH * beta1 + beta2;
    double Bn = -beta2;
    double Cn = 0d;

    double tK = K * shiftT;
    double beta = function.getValue(1, tK) + function.getDerivativeXValue(1, tK);
    double Gn = shiftH * beta;
    listA.add(An);
    listB.add(Bn);
    listC.add(Cn);
    listG.add(Gn);
  }

  /** Метод, который вычисляет sK, tK, используя реккурентные формулы. */
  private void countSTCoefs() {
    double sPrev;
    double tPrev;
    double aI;
    double bI = listB.get(0);
    double cI = listC.get(0);
    double gI = listG.get(0);
    double sI = cI / bI;
    double tI = -gI / bI;
    listS.add(sI);
    listT.add(tI);

    for (int i = 1; i <= N; i++) {
      aI = listA.get(i);
      bI = listB.get(i);
      cI = listC.get(i);
      gI = listG.get(i);
      sPrev = listS.get(i - 1);
      tPrev = listT.get(i - 1);

      sI = cI / (bI - aI * sPrev);
      tI = (aI * tPrev - gI) / (bI - aI * sPrev);
      listS.add(sI);
      listT.add(tI);
    }
  }

  /** Метод, который вычисляет yK, используя реккурентные формулы. */
  private void countYCoefs() {
    double yNext = listT.get(N);
    double yI;
    listY.add(yNext);
    double sI;
    double tI;

    for (int i = N - 1; i >= 0; i--) {
      yNext = listT.get(0);
      sI = listS.get(i);
      tI = listT.get(i);
      yI = sI * yNext + tI;
      listY.add(0, yI);
    }
  }
}
