package mathcalc.group343.stepyrev;

import java.util.ArrayList;
import java.util.List;
import mathcalc.group343.stepyrev.solver.MechanicalQuadraturesSolver;
import mathcalc.group343.stepyrev.util.MatrixUtil;
import mathcalc.group343.stepyrev.util.PrettyPrinter;

public class Application {

  private static final Double A = 0d;
  private static final Double B = 1d;
  private static final Double X = (A + B) / 2;

  private static final Integer MAX_N = 11;
  private static final Integer MAX_A = 10;

  private static MechanicalQuadraturesSolver solver;
  private static List<Double> accurateSolution;

  private static PrettyPrinter printer = new PrettyPrinter(System.out);

  public static void main(String[] args) {
    solver = new MechanicalQuadraturesSolver();
    performProcess();
  }

  private static void performProcess() {
    int nDegree = 1;
    int alphaDegree;
    double alpha;
    int n;
    String[][] table = new String[MAX_N][MAX_A];
    table[0][0] = "n, alpha";

    while (nDegree < MAX_N) {
      alphaDegree = 1;
      n = nDegree;
      table[nDegree][0] = String.valueOf(n);

      while (alphaDegree < MAX_A) {
        table[0][alphaDegree] = String.format("10^(-%d)", alphaDegree + 5);
        alpha = Math.pow(10, -alphaDegree - 5);
        accurateSolution = getAccurateSolution(n);
        List<Double> foundSolution = solver.getSolution(A, B, X, n, alpha);
        Double difNorm = MatrixUtil.countNorm(foundSolution, accurateSolution);
        table[nDegree][alphaDegree] = String.valueOf(difNorm);
        alphaDegree += 1;
      }

      nDegree += 1;
    }

    printer.print(table);
  }

  private static List<Double> getAccurateSolution(int n) {
    List<Double> solution = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      solution.add(1d);
    }

    return solution;
  }

}
