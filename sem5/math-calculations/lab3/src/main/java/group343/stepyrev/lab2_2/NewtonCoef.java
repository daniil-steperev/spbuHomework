package group343.stepyrev.lab2_2;

public class NewtonCoef {
  private final Integer number;
  private final Double value;

  public NewtonCoef(Integer number, Double value) {
    this.number = number;
    this.value = value;
  }

  public Double getValue() {
    return value;
  }

  public Integer getNumber() {
    return number;
  }
}
