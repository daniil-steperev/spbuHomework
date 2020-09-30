import group343.stepyrev.lab.LagrangePolynomial;
import org.junit.Assert;
import org.junit.Test;

public class LagrangePolynomialTest {
  @Test
  public void lowDegreePolynomialTest() {
    Integer m = 100;
    Double a = 1D;
    Double b = 2D;
    LagrangePolynomial polynomial = new LowDegreePolynomial(m, a, b);

    Integer n = 5;
    double x = a;
    double funcValue;
    double polValue;
    double shift = (b - a) / 10;
    for (int i = 0; i < 10; i++) {
      funcValue = polynomial.getFunctionValue(x);
      polynomial.sortFunctionPoints(x, n);
      polValue = polynomial.getPolynomialValue();
      Assert.assertEquals(polValue, funcValue, Math.pow(1, -20));

      x += shift;
    }
  }

  @Test
  public void pointFromSegment() {
    Integer m = 100;
    Double a = 1D;
    Double b = 5D;
    Double x = a;
    LagrangePolynomial polynomial = new LagrangePolynomial(m, a, b);

    double funcValue;
    double polValue;
    for (int n = 1; n < 50; n++) {
      funcValue = polynomial.getFunctionValue(x);
      polynomial.sortFunctionPoints(x, n);
      polValue = polynomial.getPolynomialValue();
      Assert.assertEquals(polValue, funcValue, Math.pow(1, -20));
    }
  }

  private class LowDegreePolynomial extends LagrangePolynomial {

    public LowDegreePolynomial(Integer m, Double a, Double b) {
      super(m, a, b);
    }

    @Override
    public double getFunctionValue(double point) {
      return Math.pow(point - 1, 2) + point + 1; // (x - 1) ^ 2 + x + 1
    }
  }
}
