package praktikum.group343.stepyrev.labs.solver;

import java.util.ArrayList;
import java.util.List;
import praktikum.group343.stepyrev.labs.util.MatrixUtil;

/** Класс, который находит решение методом верхней релаксации. */
public class UpperRelaxationSolver {
  List<List<Double>> solutions;
  private int matrixSize = 3;

  private void init() {
    solutions = new ArrayList<>();
    solutions.add(new ArrayList<>());
    for (int i = 0; i < matrixSize; i++) {
      solutions.get(0).add(0D);
    }
  }

  /** Метод, который находит решение методом верхней релаксации.*/
  public List<Double> findSolution(List<List<Double>> matrixH, List<Double> vectorG, int k) {
    init();
    double qOptional = calculateQOptional(matrixH);
    for (int solNum = 1; solNum <= k; solNum++) {
      solutions.add(new ArrayList<>());
      for (int i = 0; i < matrixSize; i++) {
        // если i == 0, то первая сумма = 0, иначе посчитать
        double fstSum = i > 0 ? countSum(matrixH, i, solNum + 1, 0, i) : 0;
        double sndSum = countSum(matrixH, i, solNum, i, matrixSize);
        double multipliedSum = (fstSum + sndSum - solutions.get(solNum - 1).get(i) + vectorG.get(i)) * qOptional;
        double xI = solutions.get(solNum - 1).get(i) + multipliedSum;
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

  /** Метод, который подсчитывает q оптимальное. */
  private Double calculateQOptional(List<List<Double>> matrixH) {
    double pH = MatrixUtil.getMaxEigenvalue(matrixH);
    return 2 / (1 + Math.sqrt(1 - pH * pH));
  }
}
