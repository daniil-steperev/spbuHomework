package cannon;

import static java.lang.Math.pow;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

/** A class that represents a shell. */
public class Shell extends GameObject {
    private final double START_SPEED = 0.7;
    private final double GRAVITY_ACCELERATION = 0.41;

    /** A shell's X coordinate speed. */
    private double speedX;
    /** A shell's Y coordinate speed. */
    private double speedY;

    private boolean isShooting;

    /**
     * A constructor of the shell class.
     * @param x - a start x coordinate
     * @param y - a start y coordinate
     */
    public Shell(double x, double y) {
        super(x, y, ShapeMatrix.SHELL);
        isShooting = false;
    }

    /** A method that starts a move of the shell. */
    public void shoot(double angle) {
        isShooting = true;

        speedX = START_SPEED * cos(angle);
        speedY = START_SPEED * sin(angle);
    }

    public void changeCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** A method that realizes a parabola flight. */
    private void fly() {
        x += speedX;
        y -= (speedY - pow(GRAVITY_ACCELERATION, 2) / 2); // orientation changed
    }

    @Override
    public void draw(GameApplication game) {
        if (isShooting) {
            fly();
        }

        if (x < 0 || x > GameApplication.WIDTH - 1 || y < 0 || y > GameApplication.HEIGHT - 1) {
            isShooting = false;
            return;
        }

        super.draw(game);
    }
}
