package mathcalc.lection.test1.stepyrev;

/** Класс, используемый для хранения переменных задачи. Используется при выводе таблицы */
public class TaskContainer {
  private double condA;
  private double condB;
  private double divMatrixNorm;
  private int iterationNumber;

  public TaskContainer(double condA, double condB, double divMatrixNorm) {
    this.condA = condA;
    this.condB = condB;
    this.divMatrixNorm = divMatrixNorm;
  }

  public TaskContainer(double condA, double condB, double divMatrixNorm, int iterationNumber) {
    this.condA = condA;
    this.condB = condB;
    this.divMatrixNorm = divMatrixNorm;
    this.iterationNumber = iterationNumber;
  }

  public TaskContainer() {
  }

  public double getCondA() {
    return condA;
  }

  public void setCondA(double condA) {
    this.condA = condA;
  }

  public double getCondB() {
    return condB;
  }

  public void setCondB(double condB) {
    this.condB = condB;
  }

  public double getDivMatrixNorm() {
    return divMatrixNorm;
  }

  public void setDivMatrixNorm(double divMatrixNorm) {
    this.divMatrixNorm = divMatrixNorm;
  }

  public int getIterationNumber() {
    return iterationNumber;
  }

  public void setIterationNumber(int iterationNumber) {
    this.iterationNumber = iterationNumber;
  }
}
