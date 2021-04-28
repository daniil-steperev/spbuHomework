package mathcalc.group343.stepyrev.function;

import mathcalc.group343.stepyrev.util.ParamsUtil;

/** Класс, который реализует функцию Qr. */
public class FunctionQr {

  public double getValue(double y1, double z1) {
    Function functionP = new FunctionP();
    return ParamsUtil.beta1 / ParamsUtil.beta2 * functionP.getValue(1.0) * y1 * z1;
  }
}
