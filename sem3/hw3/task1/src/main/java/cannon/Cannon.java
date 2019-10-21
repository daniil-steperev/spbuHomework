package cannon;

import static cannon.GameApplication.START_X;
import static cannon.GameApplication.START_Y;
import static cannon.ShapeMatrix.LANDSCAPE;

/** A class that represents a cannon. */
public class Cannon extends GameObject {
    private final double SHIFT = 0.07;
    private final int LENGTH = 5;

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

    private void checkUpAndDown() {
        if (isCellBelow()) {
            if (isMountain()) {
               y -= SHIFT;
            }
        } else {
            y += SHIFT;
        }

        barrel.makeBarrelHorizontal(y - 6); // FIXME
    }

    /**
     * A method that checks if all cells below the cannon is not empty.
     * @return - true if is not, false otherwise
     */
    private boolean isCellBelow() { // FIXME
        for (int i = 0; i < LENGTH; i++) {
            if (LANDSCAPE[(int) y + 1][(int) x + i] == 0) {
                System.out.println((int) (y + 1) + " " + (int) (x + i));
                return false;
            }
        }

        return true;
    }

    private boolean isMountain() {
        if (LANDSCAPE[(int) y + 1][(int) x] != 0) {
            System.out.println("HERE!!");
            return true;
        }

        if (LANDSCAPE[(int) y + 1][(int) x + LENGTH] != 0) {
            return true;
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
