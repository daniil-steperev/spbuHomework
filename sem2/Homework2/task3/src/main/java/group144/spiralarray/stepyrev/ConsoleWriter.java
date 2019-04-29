package group144.spiralarray.stepyrev;

/** A class that represents spiral writer of Matrix to the console */
public class ConsoleWriter implements SpiralWriter {
    /* {@inheritDoc} */
    @Override
    public void write(String expression) {
        System.out.print(expression);
    }
}
