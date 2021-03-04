package praktikum.group343.stepyrev.labs.util;

import java.util.List;

/** Класс, который подсчитывает погрешности. */
public class ErrorCounter {
  private List<List<Double>> matrixH;
  private List<Double> vectorG;

  private double matrixHNorm;
  private double vectorGNorm;

  private PrettyPrinter printer = new PrettyPrinter(System.out);

  public ErrorCounter(List<List<Double>> matrixH, List<Double> vectorG) {
    this.matrixH = matrixH;
    this.vectorG = vectorG;
    matrixHNorm = NormCounter.countNorm(matrixH);
    vectorGNorm = NormCounter.countVectorNorm(vectorG);
  }

  /** Метод, который высчитывает априорную оценку. */
  public Double countAprioryError(int solutionNumber) {
    return Math.pow(matrixHNorm, solutionNumber) * vectorGNorm / (1 - matrixHNorm);
  }

  /** Метод, который высчитывает апостериорную оценку. */
  public Double countAposterioryError(List<Double> curSol, List<Double> prevSol) {
    List<Double> diffSol = MatrixUtil.divideVectors(curSol, prevSol);
    double diffVectorNorm = NormCounter.countVectorNorm(diffSol);
    return matrixHNorm * diffVectorNorm / (1 - matrixHNorm);
  }

  /**
   * Метод, который сравнивает фактическую погрешность решения, найденного метода простых итераций,
   * и решения, найденного методом Зейделя.
   */
  public void compareActualErrors(
      double iterationError,
      double seidelError,
      double lusternikError,
      double upperRelaxationError) {
    String[][] table = new String[5][2];
    table[0][0] = "Method";
    table[0][1] = "Actual error";
    table[1][0] = "Simple iteration";
    table[2][0] = "Lusternik";
    table[3][0] = "Seidel";
    table[4][0] = "Upper relaxation";
    table[1][1] = String.valueOf(iterationError);
    table[2][1] = String.valueOf(lusternikError);
    table[3][1] = String.valueOf(seidelError);
    table[4][1] = String.valueOf(upperRelaxationError);
    printer.print(table);
    System.out.println("");
  }

  public void setMatrixH(List<List<Double>> matrixH) {
    this.matrixH = matrixH;
  }

  public void setVectorG(List<Double> vectorG) {
    this.vectorG = vectorG;
  }
}
