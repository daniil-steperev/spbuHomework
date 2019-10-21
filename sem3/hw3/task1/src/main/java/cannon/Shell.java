package cannon;

import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

/** A class that represents a shell. */
public class Shell extends GameObject {

    /** A shell's X coordinate speed. */
    private double speedX;
    /** A shell's Y coordinate speed. */
    private double speedY;

    /**
     * A constructor of the shell class.
     * @param x - a start x coordinate
     * @param y - a start y coordinate
     */
    public Shell(double x, double y, double angle) {
        super(x, y, ShapeMatrix.SHELL);
        speedX = 100 * cos(angle);
        speedY = 100 * sin(angle);
    }

    /** A method that starts a move of the shell. */
    public void shoot() {

    }

    private void fly() {
        // Движение по параболе
    }
}
