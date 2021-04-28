package mathcalc.group343.stepyrev.solver;

import static java.lang.Math.abs;

import Jama.Matrix;
import java.util.List;
import mathcalc.group343.stepyrev.function.Function;
import mathcalc.group343.stepyrev.function.FunctionF;
import mathcalc.group343.stepyrev.function.FunctionP;
import mathcalc.group343.stepyrev.function.FunctionQl;
import mathcalc.group343.stepyrev.function.FunctionQr;
import mathcalc.group343.stepyrev.function.FunctionR;
import mathcalc.group343.stepyrev.util.JacobyPolynom;
import mathcalc.group343.stepyrev.util.MatrixUtil;
import mathcalc.group343.stepyrev.util.ParamsUtil;
import mathcalc.group343.stepyrev.util.PrettyPrinter;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;

/** Класс, который реализует метод Ритца. */
public class RitzSolver {
  private Matrix aIJ;
  private Matrix fI;
  private List<Double> cJ;

  private double X;
  private int N;

  private TrapezoidIntegrator trapezoid;

  private List<Double> jacobiPolynoms;
  private Function functionP;
  private Function functionR;
  private Function functionQl;
  private FunctionQr functionQr;
  private Function functionF;

  private static PrettyPrinter printer = new PrettyPrinter(System.out);

  /** Метод, который реализует метод Ритца. */
  public double getValue(double x, int n) {
    initFunctions();
    this.X = x;
    this.N = n;

    JacobyPolynom jacobyPolynom = new JacobyPolynom();
    jacobiPolynoms = jacobyPolynom.initPolynoms(N, 1, X);

    trapezoid = new TrapezoidIntegrator();
    aIJ = countAij();
    fI = countFi();
    cJ = countCj();

    return countSolution();
  }

  public double getCondA() {
    double normA = aIJ.normInf();
    Matrix aInversed = aIJ.inverse();
    double normAInversed = aInversed.normInf();
    return normAInversed * normA;
  }

  public Matrix getMatrix() {
    return aIJ;
  }

  /** Метод, который инициализирует функции. */
  private void initFunctions() {
    functionP = new FunctionP();
    functionR = new FunctionR();
    functionQl = new FunctionQl();
    functionQr = new FunctionQr();
    functionF = new FunctionF();
  }

  /** Метод, который вычисляет коэффициенты матрицы A. */
  private Matrix countAij() {
    Matrix matrix = new Matrix(N, N);

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        final int iConst = i;
        final int jConst = j;

        UnivariateFunction function =
            v -> {
              double y = ParamsUtil.getCoordinationSystemValue(v, jConst, jacobiPolynoms);
              double z = ParamsUtil.getCoordinationSystemValue(v, iConst, jacobiPolynoms);
              double yDif = ParamsUtil.getCoordinateFuncDerivative(v, jConst, jacobiPolynoms);
              double zDif = ParamsUtil.getCoordinateFuncDerivative(v, iConst, jacobiPolynoms);
              return functionP.getValue(v) * yDif * zDif + functionR.getValue(v) * y * z;
            };

        double integralValue = trapezoid.integrate(500000, function, -1, 1);

        double y1 = ParamsUtil.getCoordinationSystemValue(1, jConst, jacobiPolynoms);
        double z1 = ParamsUtil.getCoordinationSystemValue(1, iConst, jacobiPolynoms);
        double qL = functionQl.getValue(X);
        double qR = functionQr.getValue(y1, z1);
        matrix.set(i, j, integralValue + qL + qR);
      }
    }

    return matrix;
  }

  /** Метод, который вычисляет коэффициенты fI. */
  private Matrix countFi() {
    Matrix fIList = new Matrix(1, N);

    for (int i = 0; i < N; i++) {
      final int iConst = i;
      UnivariateFunction function =
          v -> {
            double wI = ParamsUtil.getCoordinationSystemValue(v, iConst, jacobiPolynoms);
            double fValue = functionF.getValue(v);
            return fValue * wI;
          };

      double integralValue = trapezoid.integrate(500000, function, -1, 1);
      fIList.set(0, i, integralValue);
    }

    return fIList;
  }

  /** Метод, который вычисляет коэффициенты cJ. */
  private List<Double> countCj() {
    GaussSolver solver = new GaussSolver();
    Matrix augmentedMatrix = MatrixUtil.uniteMatrices(aIJ, fI);
    return solver.findSolution(augmentedMatrix);
  }

  /** Метод, который вычисляет решение. */
  private double countSolution() {
    double sum = 0d;
    for (int i = 0; i < N; i++) {
      double cI = cJ.get(i);
      double wI = ParamsUtil.getCoordinationSystemValue(X, i, jacobiPolynoms);
      sum += cI * wI;
    }

    return sum;
  }
}
