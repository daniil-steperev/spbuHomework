package mathcalc.group343.stepyrev.solver;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

import Jama.Matrix;
import java.util.ArrayList;
import java.util.List;
import mathcalc.group343.stepyrev.function.Function;
import mathcalc.group343.stepyrev.function.FunctionF;
import mathcalc.group343.stepyrev.function.FunctionP;
import mathcalc.group343.stepyrev.function.FunctionR;
import mathcalc.group343.stepyrev.util.JacobyPolynom;
import mathcalc.group343.stepyrev.util.MatrixUtil;
import mathcalc.group343.stepyrev.util.ParamsUtil;

/** Класс, который реализует метод коллокации. */
public class CollocationSolver {

  private double X;
  private int N;

  private GaussSolver gaussSolver;
  private JacobyPolynom jacobyPolynom;

  private List<Double> listT;
  private List<Double> listC;
  private List<Double> jacobiPolynoms;
  private Matrix matrixA;
  private Matrix rightPart;

  private Function functionP;
  private Function functionR;
  private Function functionF;

  public CollocationSolver() {
    jacobyPolynom = new JacobyPolynom();
    gaussSolver = new GaussSolver();
    listT = new ArrayList<>();
    functionP = new FunctionP();
    functionR = new FunctionR();
    functionF = new FunctionF();
  }

  /** Метод, который реализует метод коллокации. */
  public double getValue(double x, int n) {
    this.X = x;
    this.N = n;
    this.listT = getListT(n);
    this.matrixA = getMatrixA();
    this.rightPart = getRightPart();
    this.listC = getListC();

    return getSolution();
  }

  /** Метод, который вычисляет решение. */
  private double getSolution() {
    double sum = 0d;
    jacobiPolynoms = jacobyPolynom.initPolynoms(N, 1, X);
    for (int i = 0; i < N; i++) {
      double cI = listC.get(i);
      double wI = ParamsUtil.getCoordinationSystemValue(X, i, jacobiPolynoms);
      sum += cI * wI;
    }

    return sum;
  }

  /** Метод, который вычисляет коэффициееты cJ. */
  private List<Double> getListC() {
    Matrix augmentedMatrix = MatrixUtil.uniteMatrices(matrixA, rightPart);
    return gaussSolver.findSolution(augmentedMatrix);
  }

  /** Метод, который вычисляет узлы многочлена Чебышева первого рода tK. */
  private List<Double> getListT(int n) {
    List<Double> list = new ArrayList<>();

    for (int k = 1; k <= n; k++) {
      double tK = cos((2 * k - 1) * PI / (2 * n));
      list.add(tK);
    }

    return list;
  }

  /** Метод, который вычисляет коэффициенты левой части уравнения. */
  private Matrix getMatrixA() {
    Matrix matrix = new Matrix(N, N);

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        double tI = listT.get(i);
        double value = getLuValue(j, tI);
        matrix.set(i, j, value);
      }
    }

    return matrix;
  }

  /** Метод, который вычисляет правую часть уравнения. */
  private Matrix getRightPart() {
    Matrix matrix = new Matrix(1, N);

    for (int i = 0; i < N; i++) {
      double tI = listT.get(i);
      double value = functionF.getValue(tI);
      matrix.set(0, i, value);
    }

    return matrix;
  }

  /** Метод, который вычисляет левую часть уравнения. */
  private double getLuValue(int j, double tI) {
    jacobiPolynoms = jacobyPolynom.initPolynoms(N, 1, tI);

    double pValue = functionP.getValue(tI);
    double pFstDer = functionP.getFstDer(tI);

    double wIValue = ParamsUtil.getCoordinationSystemValue(tI, j, jacobiPolynoms);
    double wIfstDer = ParamsUtil.getCoordinateFuncDerivative(tI, j, jacobiPolynoms);
    double wISndDer = ParamsUtil.getCoordinateFuncSndDerivative(tI, j, jacobiPolynoms);

    double rValue = functionR.getValue(tI);
    double rFstDer = functionR.getFstDer(tI);

    return -(pFstDer * wIfstDer + pValue * wISndDer) + rFstDer * wIValue + rValue * wIfstDer;
  }
}
