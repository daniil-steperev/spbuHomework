package praktikum.group343.stepyrev.first.main;

import java.util.ArrayList;
import java.util.List;
import praktikum.group343.stepyrev.first.MatrixSystemSolver;
import praktikum.group343.stepyrev.first.util.MatrixUtil;
import praktikum.group343.stepyrev.first.util.NormCounter;

/** Метод, который печатает на экран результат работы схемы Гаусса единичного деления. */
public class MainGaussSolver {

  // Матрица А
  private static final List<List<Double>> matrix = MatrixUtil.gaussSolverMatrix;

  // Матрица A^(-1)
  private static List<List<Double>> inverseMatrix;

  // Точная матрица
  private static final List<Double> accurateMatrix = MatrixUtil.accurateGaussMatrix;

  // Измененная матрица
  private static final List<Double> modifiedMatrix = MatrixUtil.modifiedGaussMatrix;

  private static List<Double> accurateSolution;
  private static List<Double> modifiedSolution;
  // Число обусловленности матрицы А
  private static Double conditionNumber;
  private static Double matrixNorm;
  private static Double inverseMatrixNorm;

  private static MatrixSystemSolver matrixSystemSolver;

  /** Метод, который печатает результат работы схемы Гаусса единичного деления. */
  public static void main(String[] args) {
    matrixSystemSolver = new MatrixSystemSolver();
    printSolutionForAccurateMatrix();
    printSolutionForModifiedMatrix();
    printConditionNumber();
    printActualError();
    printActualErrorEvaluation();
  }

  /** Метод, который печает решения системы для "точной" правой части. */
  private static void printSolutionForAccurateMatrix() {
    System.out.println("Solution of a system of equations with \"exact\" right-hand side:");
    System.out.println("Matrix A: ");
    MatrixUtil.printMatrix(matrix);
    System.out.println("Accurate matrix b: ");
    MatrixUtil.printVector(accurateMatrix);

    accurateSolution = matrixSystemSolver.findSolution(matrix, accurateMatrix);
    System.out.println("Found solutions: ");
    printSolutions(accurateSolution);
  }

  /** Метод, который печатает решения системы для измененной правой части. */
  private static void printSolutionForModifiedMatrix() {
    System.out.println("Solving the system of equations with a modified right side:");
    System.out.println("Matrix A: ");
    MatrixUtil.printMatrix(matrix);
    System.out.println("Modified matrix b': ");
    MatrixUtil.printVector(modifiedMatrix);

    modifiedSolution = matrixSystemSolver.findSolution(matrix, modifiedMatrix);
    System.out.println("Found solutions: ");
    printSolutions(modifiedSolution);
  }

  /** Метод, который печатает решения системы. */
  private static void printSolutions(List<Double> solutions) {
    for (int i = 0; i < solutions.size(); i++) {
      System.out.println(String.format("x%d = %f", i + 1, solutions.get(i)));
    }
  }

  /** Метод, который печатает число обусловленности матрицы А. */
  private static void printConditionNumber() {
    matrixNorm = NormCounter.countNormMatrix(matrix);
    inverseMatrix = MainJordanSolver.findInverseMatrix(matrix);
    inverseMatrixNorm = NormCounter.countNormMatrix(inverseMatrix);
    conditionNumber = matrixNorm * inverseMatrixNorm;
    System.out.println(String.format("Condition number for matrix A: %f", conditionNumber));
  }

  /** Метод, который печатает фактическую погрешность. */
  private static void printActualError() {
    List<Double> divisionVector = new ArrayList<>(); // вектор разности x и x'
    for (int i = 0; i < accurateSolution.size(); i++) {
      divisionVector.add(accurateSolution.get(i) - modifiedSolution.get(i));
    }

    Double divisionVectorNorm = NormCounter.countNormVector(divisionVector);
    Double accurateSolutionNorm = NormCounter.countNormVector(accurateSolution);
    System.out.println(
        String.format("Actual relative error: %f", divisionVectorNorm / accurateSolutionNorm));
  }

  /** Метод, который печатает оценку для фактической погрешности. */
  private static void printActualErrorEvaluation() {
    Double accuratePartNorm = NormCounter.countNormVector(accurateMatrix); // это b
    Double deltaPartNorm =
        NormCounter.countNormVector(modifiedMatrix, accurateMatrix); // это delta b = b - b'
    Double errorEvaluation = conditionNumber * (deltaPartNorm / accuratePartNorm);
    System.out.println(String.format("Actual error estimate: %f", errorEvaluation));
  }
}
