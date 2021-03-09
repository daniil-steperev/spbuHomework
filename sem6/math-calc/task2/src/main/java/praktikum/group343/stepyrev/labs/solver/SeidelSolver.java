package praktikum.group343.stepyrev.labs.solver;

import java.util.ArrayList;
import java.util.List;

/** Класс, который находит решение методом Зейделя. */
public class SeidelSolver {
  List<List<Double>> solutions;
  private int matrixSize = 3;

  private void init() {
    solutions = new ArrayList<>();
    solutions.add(new ArrayList<>());
    for (int i = 0; i < matrixSize; i++) {
      solutions.get(0).add(0D);
    }
  }

  /** Метод, которые находит k-ое решение системы. */
  public List<Double> foundSolution(
      List<List<Double>> matrixH, List<Double> vectorG, int k) {
    init();
    for (int solNum = 1; solNum <= k; solNum++) {
      solutions.add(new ArrayList<>());
      for (int i = 0; i < matrixSize; i++) {
        // если i == 0, то первая сумма = 0, иначе посчитать
        double fstSum = i > 0 ? countSum(matrixH, i, solNum + 1, 0, i) : 0;
        double sndSum = countSum(matrixH, i, solNum, i, matrixSize);
        double xI = fstSum + sndSum + vectorG.get(i);
        solutions.get(solNum).add(xI);
      }
    }

    return solutions.get(k);
  }

  /** Метод, которые считает sum(hIJ * xJ(k)). */
  private Double countSum(List<List<Double>> matrixH, int i, int k, int start, int end) {
    double sum = 0D;
    for (int j = start; j < end; j++) {
      double hIJ = matrixH.get(i).get(j);
      double xJ = solutions.get(k - 1).get(j);
      sum += hIJ * xJ;
    }

    return sum;
  }
}
