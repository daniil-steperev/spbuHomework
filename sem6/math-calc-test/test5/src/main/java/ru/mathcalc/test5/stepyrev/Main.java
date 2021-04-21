package ru.mathcalc.test5.stepyrev;

import ru.mathcalc.test5.stepyrev.solver.AcceleratedWiderSolver;
import ru.mathcalc.test5.stepyrev.solver.NumericalSolver;
import ru.mathcalc.test5.stepyrev.solver.WidderSolver;
import ru.mathcalc.test5.stepyrev.util.PrettyPrinter;

public class Main {

  private static PrettyPrinter printer = new PrettyPrinter(System.out);
  private static final int N = 10;
  private static final int M = 30;
  private static final double R = 0.95;
  private static final double T = 0.5;


  public static void main(String[] args) {
    WidderSolver widderSolver = new WidderSolver();
    AcceleratedWiderSolver acceleratedWiderSolver = new AcceleratedWiderSolver();
    NumericalSolver numericalSolver = new NumericalSolver();
    String[][] table = new String[N + 1][4];
    table[0][0] = "n";
    table[0][1] = "Wn(f, 0.5)";
    table[0][2] = "Wn(n, f, 0.5)";
    table[0][3] = "Wnm(n, f, 0.5)";

    for (int i = 1; i <= N; i++) {
      table[i][0] = String.valueOf(i);
      double widderValue = widderSolver.getValue(T, i);
      table[i][1] = String.valueOf(widderValue);
      double acceleratedWiderValue = acceleratedWiderSolver.getValue(T, i, i);
      table[i][2] = String.valueOf(acceleratedWiderValue);
      double numericalValue = numericalSolver.getValue(T, N, M, R);
      table[i][3] = String.valueOf(numericalValue);
    }

    printer.print(table);
  }
}
