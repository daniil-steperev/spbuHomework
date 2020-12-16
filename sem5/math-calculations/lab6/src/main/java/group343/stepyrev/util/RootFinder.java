package group343.stepyrev.util;

import group343.stepyrev.lab2_2.NewtonPolynomial;
import java.util.*;

import static java.lang.Math.abs;

/**
 * Класс, который находит все корни нечетной кратности на заданном отрезке.
 */
public class RootFinder {

  private double A;
  private double B;
  private double EPSILON;
  private Segment taskSegment;

  private NewtonPolynomial polynomial;

  private Double FX;

  public RootFinder(double A, double B, double EPSILON, NewtonPolynomial polynomial, Double fx) {
    this.polynomial = polynomial;
    this.A = A;
    this.B = B;
    this.EPSILON = EPSILON;
    this.FX = fx;
    this.taskSegment = new Segment(A, B);
  }


  /**
   * Метод, который отделяет корни.
   */
  public List<Segment> separateRoots(int n) {
    double SHIFT = (B - A) / n; // это h
    double currentStartPoint = taskSegment.getStartPoint(); // это x1
    double currentEndPoint = currentStartPoint + SHIFT; // это x2
    double startPointFunctionValue = getFunctionValue(currentStartPoint); // это y1

    List<Segment> segments = new LinkedList<Segment>();
    while (currentEndPoint <= taskSegment.getEndPoint()) {
      double endPointFunctionValue = getFunctionValue(currentEndPoint); // это y2

      if (startPointFunctionValue * endPointFunctionValue <= 0) {
        Segment newSegment = new Segment(currentStartPoint, currentEndPoint);
        segments.add(newSegment);
      }

      currentStartPoint = currentEndPoint;
      currentEndPoint += SHIFT;
      startPointFunctionValue = endPointFunctionValue;
    }

    return segments;
  }

  /**
   * Метод, который уточняет корни на заданном отрезке методом бисекции (половинного деления).
   *
   * @param segment - это отрезок, на котором уточняется корень
   */
  public List<Double> findRootByBisectionMethod(Segment segment) {
    double startPoint = segment.getStartPoint();
    double endPoint = segment.getEndPoint();

    List<Double> resultValues = new LinkedList<>();
    double root = (startPoint + endPoint) / 2; // это Х
    System.out.println(String.format("Начальное приближение к корню (x0): %.8f", root));
    while ((endPoint - startPoint) > EPSILON) {
      double middlePoint = (startPoint + endPoint) / 2; // это c

      if (getFunctionValue(startPoint) * getFunctionValue(middlePoint) <= 0) {
        endPoint = middlePoint;
      } else {
        startPoint = middlePoint;
      }
    }

    root = (endPoint + startPoint) / 2;
    resultValues.add(root);

    return resultValues;
  }

  private Double getFunctionValue(double point) {
    return polynomial.getPolynomialValue(point) - FX;
  }
}