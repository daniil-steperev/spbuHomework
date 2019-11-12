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
        isVisible = false;
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

    /**
     * A method that returns if the shell is flying.
     * @return - true if is flying, false otherwise
     */
    public boolean isFlying() {
        return isFlying;
    }

    /** A method that realizes a parabola flight. */
    private void fly() {
        x += speedX;
        speedY -= pow(GRAVITY_ACCELERATION, 2) / 2; // "-" because orientation is changed
        y -= speedY; // orientation changed
    }

    /** {@inheritDoc}*/
    @Override
    public void draw(GameApplication game) {
        if (x < 0 || x > GameApplication.WIDTH - 1 || y > GameApplication.HEIGHT - 1) { // if the shell is below (lefter, righter) the window
            isFlying = false;
            isVisible = false;
            return;
        }

        if (isVisible && y < 0) { // if the shell is above the window
            isVisible = false;
            return;
        }

        if (!isVisible && y >= 0 && isFlying) { // if the shell above the game window, but it still flying
            isVisible = true;
        }

        if (isVisible && ShapeMatrix.LANDSCAPE[(int) y][(int) x] != 0) { // has exploded about the mountain
            isVisible = false;
            isFlying = false;
            return;
        }

        if (isFlying) { // is still flying
            fly();
        }

        if (!isVisible) { // above the window
            return;
        }

        super.draw(game);
    }
}
