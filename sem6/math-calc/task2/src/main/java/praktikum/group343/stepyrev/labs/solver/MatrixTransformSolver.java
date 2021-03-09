package praktikum.group343.stepyrev.labs.solver;

import java.util.ArrayList;
import java.util.List;

/** Класс, который выполняет преобразование Ax = b к виду x = Hx + g. */
public class MatrixTransformSolver {
  private List<List<Double>> matrixH;
  private List<Double> vectorG;

  public MatrixTransformSolver() {
    matrixH = new ArrayList<>();
    vectorG = new ArrayList<>();
  }

  /** Метод, которые преобразование Ax = b к виду x = Hx + g. */
  public void makeTransformation(List<List<Double>> matrix, List<Double> rightPart) {
    int stringNumber = matrix.size();
    for (int i = 0; i < stringNumber; i++) {
      matrixH.add(new ArrayList<>());
      int columnNumber = matrix.get(i).size();
      double aII = matrix.get(i).get(i);
      for (int j = 0; j < columnNumber; j++) {
        if (i == j) {
          matrixH.get(i).add(j, 0d);
        } else {
          double aIJ = matrix.get(i).get(j);
          matrixH.get(i).add(j, -aIJ / aII);
        }
      }
      double bI = rightPart.get(i);
      vectorG.add(bI / aII);
    }
  }

  /** Метод, которые возвращает матрицу H. */
  public List<List<Double>> getMatrixH() {
    return matrixH;
  }

  /** Метод, которые возвращает вектор G. */
  public List<Double> getVectorG() {
    return vectorG;
  }
}
