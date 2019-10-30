package cannon;

import static java.lang.Math.pow;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

/** A class that represents a shell. */
public class Shell extends GameObject {
    private final double START_SPEED = 0.6;
    private final double GRAVITY_ACCELERATION = 0.1;

    /** A shell's X coordinate speed. */
    private double speedX;
    /** A shell's Y coordinate speed. */
    private double speedY;

    /** A flag that represents if the shell is flying. */
    private boolean isFlying;
    /** A flag that represents if the shell is visible. */
    private boolean isVisible;

    /**
     * A constructor of the shell class.
     * @param x - a start x coordinate
     * @param y - a start y coordinate
     */
    public Shell(double x, double y) {
        super(x, y, ShapeMatrix.SHELL);
        isFlying = false;
    }

    /** A method that starts a move of the shell. */
    public void shoot(double angle) {
        isFlying = true;
        isVisible = true;

        speedX = START_SPEED * cos(angle);
        speedY = START_SPEED * sin(angle);
    }

    /**
     * A method that changes coordinates of the shell.
     * @param x - new x
     * @param y - new y
     */
    public void changeCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** A method that realizes a parabola flight. */
    private void fly() {
        x += speedX;
        speedY -= pow(GRAVITY_ACCELERATION, 2) / 2;
        y -= speedY; // orientation changed
    }

    /** {@inheritDoc}*/
    @Override
    public void draw(GameApplication game) {
        if (!isVisible) {
            return;
        }

        if (isFlying) {
            fly();
        }

        if (x < 0 || x > GameApplication.WIDTH - 1 || y < 0 || y > GameApplication.HEIGHT - 1) {
            isFlying = false;
            isVisible = false;
            return;
        }

        super.draw(game);
    }
}
