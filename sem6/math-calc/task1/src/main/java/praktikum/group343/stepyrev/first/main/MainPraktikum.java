package praktikum.group343.stepyrev.first.main;

import java.util.Scanner;

/** Класс, который запускает главный метод программы. */
public class MainPraktikum {

  public static void main(String[] args) {
    printIntroduction();
    runProgram(args);
  }

  /** Метод, который печатает на экран результат работы программы. */
  public static void runProgram(String[] args) {
    System.out.println("If you want to run certain method enter: ");
    System.out.println("\t Single division Gaussian scheme - 1");
    System.out.println("\t Gaussian scheme with pivot element selection - 2");
    System.out.println("\t Jordan's method for finding the inverse matrix - 3");
    System.out.println("\t LU-decomposition method for finding the determinant of a matrix - 4");
    System.out.println("\t Exit program - e");
    System.out.println("\t Run all method - any other character");
    Scanner scanner = new Scanner(System.in);

    String nextLine = scanner.next();
    while (!nextLine.equals("e")) {

      try {
        int number = Integer.parseInt(nextLine);
        switch (number) {
          case 1:
            MainGaussSolver.main(args);
            break;
          case 2:
            MainGaussPivotSolver.main(args);
            break;
          case 3:
            MainJordanSolver.main(args);
            break;
          case 4:
            MainLUSolver.main(args);
            break;
          default:
            MainGaussSolver.main(args);
            MainGaussPivotSolver.main(args);
            MainJordanSolver.main(args);
            MainLUSolver.main(args);
            break;
        }

      } catch (Exception e) {
        MainGaussSolver.main(args);
        MainLUSolver.main(args);
        MainJordanSolver.main(args);
      }

      System.out.println("");
      System.out.println(
          "If you want to run again press appropriate char. If you want to exit press e.");
      nextLine = scanner.next();
    }

    System.out.println("Thank you for using my program!");
  }

  /** Метод, который печатает вводные данные программы. */
  public static void printIntroduction() {
    System.out.println("Task 1 for Computational practice lessons.");
    System.out.println("Program was written by Stepyrev Daniil, student of 343 group.");
    System.out.println("The first variant was used in all methods.");
    System.out.println("");
  }
}
