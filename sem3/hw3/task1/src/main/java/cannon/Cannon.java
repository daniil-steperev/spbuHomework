package cannon;

import java.awt.*;

import static cannon.GameApplication.*;
import static cannon.ShapeMatrix.LANDSCAPE;
import static java.lang.Math.round;

/** A class that represents a cannon. */
public class Cannon extends GameObject {
    private final double SHIFT = 0.125;
    private final int LENGTH = 6;

    /** A field that represents if the cannon is alive. */
    private boolean isAlive;

    private Barrel barrel;

    /** A constructor of cannon class. */
    public Cannon(double x, double y) {
        super(x, y, ShapeMatrix.CANNON);
        barrel = new Barrel(x + 2, y - 6);
    }

    /** A method that realizes the cannon's shot. */
    public void shoot() {

    }

    /** A method that moves the cannon. */
    public void move(CannonController controller) {
        if (controller.getLeftPressed()) {
            x -= SHIFT;
            barrel.move(-SHIFT, 0);
            checkUpAndDown();
        }

        if (controller.getRightPressed()) {
            x += SHIFT;
            barrel.move(SHIFT, 0);
            checkUpAndDown();
        }

        if (controller.getUpPressed() || controller.getDownPressed()) {
            moveBarrel(controller);
        }

        checkBorders();
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

    private void checkUpAndDown() { // FIXME
        if (isMountain()) {
            y -= SHIFT;
            barrel.makeBarrelHorizontal(y - 6);
            return;
        }

        if (isEmptyCellsBelow()) {
            y += SHIFT;
            barrel.makeBarrelHorizontal(y - 6);
        }
    }

    /**
     * A method that checks if all cells below the cannon is empty.
     * @return - true if is not, false otherwise
     */
    private boolean isEmptyCellsBelow() { // FIXME
        for (int i = 0; i < LENGTH; i++) {
            if (LANDSCAPE[(int) y + 2][(int) round(x) + i] != 0) { // FIXME
                System.out.println(((int) y + 2) + " " + ((int) round(x)));
                return false;
            }
        }

        return true;
    }

    private boolean isMountain() {
        if (LANDSCAPE[(int) y + 1][(int) round(x)] != 0 && LANDSCAPE[(int) y][(int) round(x)] == 0) {
            System.out.println("HERE!!");
            return true;
        }

        if (LANDSCAPE[(int) y + 1][(int) round(x) + LENGTH] != 0 && LANDSCAPE[(int) y][(int) round(x) + LENGTH] == 0) {
            return true;
        }

        for (int i = 0; i < LENGTH; i++) {
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
            barrel.x = x + 2;
        } else if (x + width > GameApplication.WIDTH) {
            x = GameApplication.WIDTH - width;
            barrel.x = x + 2;
        }
        if (y - (LENGTH - 2) <= 0) { // FIXME: MAKE CONSTANT LENGTH -2 = BARREL LENGTH + CANNON HEIGHT
            y = START_Y;
            barrel.makeBarrelHorizontal(y - 6);
        }
    }

    @Override
    public void draw(GameApplication game) {
        super.draw(game);
        barrel.draw(game);
    }
}
