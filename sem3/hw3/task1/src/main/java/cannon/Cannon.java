package cannon;

import static cannon.GameApplication.*;
import static cannon.ShapeMatrix.LANDSCAPE;
import static java.lang.Math.round;

/** A class that represents a cannon. */
public class Cannon extends GameObject {
    /** A shift value. */
    private final double SHIFT = 0.125;

    /** A length of the cannon. */
    private final int CANNON_LENGTH = 6;
    /** A length of the cannon's barrel*/
    private final int BARREL_LENGTH = 8;
    /** A height of the canon*/
    private final int CANNON_HEIGHT = 2;

    /** A cannon's barrel. */
    private Barrel barrel;

    /** A constructor of cannon class. */
    public Cannon(double x, double y) {
        super(x, y, ShapeMatrix.CANNON);
        barrel = new Barrel(x + CANNON_HEIGHT, y - CANNON_LENGTH);
    }

    /** A method that moves the cannon. */
    public void move(CannonController controller) {
        if (controller.getLeftPressed() && LANDSCAPE[(int) y][(int) x - 1] == 0) { // x - 1 - previous cell before cannon
            x -= SHIFT;
            barrel.move(-SHIFT, 0);
            checkUpAndDown();
        }

        if (controller.getRightPressed() && LANDSCAPE[(int) y - CANNON_HEIGHT][(int) x + BARREL_LENGTH] == 0) {
            x += SHIFT;
            barrel.move(SHIFT, 0);
            checkUpAndDown();
        }

        if (controller.getUpPressed() || controller.getDownPressed()) {
            moveBarrel(controller);
        }

        checkBorders();
    }

    public void shoot() {
        barrel.shoot();
    }

    /** A method that moves the cannon's barrel. */
    private void moveBarrel(CannonController controller) {
        if (controller.getUpPressed()) {
            barrel.move(0, SHIFT);
        }

        if (controller.getDownPressed()) {
            barrel.move(0, -SHIFT);
        }
    }

    /** A method that checks if the cannon should get up or down. */
    private void checkUpAndDown() {
        if (isMountain()) {
            y -= SHIFT;
            barrel.makeBarrelHorizontal(y - CANNON_LENGTH);
            return;
        }

        if (isEmptyCellsBelow()) {
            y += SHIFT;
            barrel.makeBarrelHorizontal(y - CANNON_LENGTH);
        }
    }

    /**
     * A method that checks if all cells below the cannon is empty.
     * @return - true if is not, false otherwise
     */
    private boolean isEmptyCellsBelow() {
        for (int i = 0; i < CANNON_LENGTH; i++) {
            if (LANDSCAPE[(int) y + CANNON_HEIGHT][(int) round(x) + i] != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * A method that checks if the mountain.
     * @return - true if it is, false otherwise
     */
    private boolean isMountain() {
        if (LANDSCAPE[(int) y + 1][(int) round(x)] != 0 && LANDSCAPE[(int) y][(int) round(x)] == 0) {
            return true;
        }

        if (LANDSCAPE[(int) y + 1][(int) round(x) + CANNON_LENGTH] != 0 && LANDSCAPE[(int) y][(int) round(x) + CANNON_LENGTH] == 0) {
            return true;
        }

        for (int i = 0; i < CANNON_LENGTH; i++) {
            if (LANDSCAPE[(int) y + 1][(int) x + i] != 0 && LANDSCAPE[(int) y][(int) x + i] == 0) {
                return true;
            }
        }

        return false;
    }

    /** A method that checks if the rocket crossed borders (up, left, right) and returns it if it crossed. */
    private void checkBorders() {
        if (x < 0) {
            x = START_X;
            barrel.x = x + CANNON_HEIGHT;
        } else if (x + width > GameApplication.WIDTH) {
            x = GameApplication.WIDTH - width;
            barrel.x = x + CANNON_HEIGHT;
        }
        if (y - BARREL_LENGTH <= 0) {
            y = START_Y;
            barrel.makeBarrelHorizontal(y - CANNON_LENGTH);
        }
    }

    /** {@inheritDoc}*/
    @Override
    public void draw(GameApplication game) {
        super.draw(game);
        barrel.draw(game);
    }
}
