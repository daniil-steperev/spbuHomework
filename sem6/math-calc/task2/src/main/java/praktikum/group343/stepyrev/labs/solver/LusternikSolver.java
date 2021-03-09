package praktikum.group343.stepyrev.labs.solver;

import java.util.List;
import praktikum.group343.stepyrev.labs.util.MatrixUtil;

/** Класс, который реализует метод Люстерника. */
public class LusternikSolver {
  /** Метод, который реализует метод Люстерника. */
  public List<Double> findSolution(
      List<List<Double>> matrixH, List<Double> vectorG, SimpleIterationSolver solver, int k) {
    double pH = MatrixUtil.getMaxEigenvalue(matrixH);
    List<Double> curSol = solver.foundSolution(matrixH, vectorG, k);
    List<Double> prevSol = solver.foundSolution(matrixH, vectorG, k - 1);
    // x(k) - x(k-1)
    List<Double> sol = MatrixUtil.divideVectors(curSol, prevSol);
    // (1 / (1 - p(H)) * (x(k) - x(k-1))
    sol = MatrixUtil.multiplyVector(sol, 1 / (1 - pH));
    return MatrixUtil.sumVectors(prevSol, sol);
  }
}
