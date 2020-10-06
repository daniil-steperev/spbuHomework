package group343.stepyrev.lab2_1;

public class FunctionPoint {
  private final Double value;
  private final Double functionValue;
  private Double distanceToPoint;

  public FunctionPoint(Double value, Double functionValue) {
    this.value = value;
    this.functionValue = functionValue;
  }

  public void setDistanceToPoint(Double point) {
    distanceToPoint = Math.abs(point - value);
  }

  public Double getDistanceToPoint() {
    return distanceToPoint;
  }

  public Double getValue() {
    return value;
  }

  public Double getFunctionValue() {
    return functionValue;
  }
}
