package cannon;

import static java.lang.Math.*;

/** A class that represents a barrel of the cannon. */
public class Barrel extends GameObject {
    /** A last x coordinate of the barrel. */
    private double lastX;
    /** A last y coordinate of the barrel. */
    private double lastY;

    /** A barrel rotation radius. */
    private final double RADIUS = 6;

    /** A barrel size. */
    private final int BARREL_SIZE = 6;

    /** A number of the green color in pixel. */
    private final static int BARREL_COLOR = 6;

    /** A current shell. */
    private Shell shell;

    /** A matrix that represents barrel view in pixels. */
    private static int[][] barrel = new int[][] {
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
    };

    /** A constructor of the barrel. */
    public Barrel(double x, double y) {
        super(x, y, barrel);
        lastX = x + BARREL_SIZE - 1;
        lastY = y;

        shell = new Shell(lastX, lastY + BARREL_SIZE - 1); // lastY + BARREL_SIZE - 1 - because we have inverted matrix
    }

    /** A method that moves the barrel.
     *
     *  Moving produced using the Bresenham Algorithm.
     * */
    public void move(double shift, double angle) {
        x += shift;
        lastX += shift;

        if (y == lastY && angle <= 0) { // horizontal orientation
            return;
        }

        if (x == lastX && angle >= 0) { // vertical orientation
            return;
        }

        if (angle != 0) {
            lastY -= angle;
            lastX = getXOnCircle();
        }
    }

    /** A method that realizes the shot. */
    public void shoot() {
        double angle = atan(abs((y - lastY) / (x - lastX)));
        shell.changeCoordinates(lastX, lastY + BARREL_SIZE - 1);
        shell.shoot(angle);
    }

    /** {@inheritDoc}*/
    @Override
    public void draw(GameApplication game) {
        super.draw(game);
        drawBresenhamLine(x, y, lastX, lastY);
        shell.draw(game);
    }

    /**
     * A method that counts x for y.
     *
     * A counting occurred due to expression x^2 + y^2 = 6^2.
     * @return - a value of x
     */
    private double getXOnCircle() {
        double yLength = sqrt(abs(pow(RADIUS, 2) - pow(lastY - y, 2)));
        return x + yLength;
    }

    /** A method that resets the barrel matrix. */
    private void resetMatrix() {
        for (int i = 0; i < BARREL_SIZE; i++) {
            for (int j = 0; j < BARREL_SIZE; j++) {
                barrel[i][j] = 0;
            }
        }
    }

    /**
     * A method that changes matrix of the barrel.
     *
     * This method is used to draw the line.
     * @param xEnd - last x coordinate
     * @param yEnd - last y coordinate
     */
    private void drawBresenhamLine (double xStart, double yStart, double xEnd, double yEnd)
    {
        resetMatrix();

        double angle = toDegrees(atan(abs((yStart - yEnd) / (xStart - xEnd))));
        if (angle >= 45.0 && angle <= 90.0) {
            drawLineAbove(xStart, xEnd);
        } else if (angle < 45.0 && angle >= 0){
            drawLineBelow(yStart, yEnd);
        }
    }

    /**
     * A mehod that draws a line below the 45 degrees line.
     * @param yStart - a start y coordinate
     * @param yEnd - an end y coordinate
     */
    private void drawLineBelow(double yStart, double yEnd)
    {
        double currentY = yStart;
        int xCoordinate = 0;
        int yCoordinate = 0;

        matrix[BARREL_SIZE - yCoordinate - 1][xCoordinate] = BARREL_COLOR; // initialize first pixel

        for (int i = 1; i < BARREL_SIZE; i++)
        {
            double yShift = getShift(yStart, yEnd);

            currentY += yShift;

            xCoordinate += 1;
            yCoordinate = abs((int) Math.round(yStart - currentY) % BARREL_SIZE);
            matrix[BARREL_SIZE - yCoordinate - 1][xCoordinate] = BARREL_COLOR;
        }
    }

    /**
     * A mehod that draws a line above the 45 degrees line.
     * @param xStart - a start x coordinate
     * @param xEnd - an end x coordinate
     */
    private void drawLineAbove(double xStart, double xEnd)
    {
        double currentX = xStart;
        int xCoordinate = 0;
        int yCoordinate = 0;

        matrix[BARREL_SIZE - yCoordinate - 1][xCoordinate] = BARREL_COLOR; // initialize first pixel
        for (int i = 1; i < BARREL_SIZE; i++)
        {
            double xShift = getShift(xStart, xEnd);

            currentX += xShift;

            yCoordinate += 1;
            xCoordinate = abs((int) (round(currentX) - round(xStart)) % BARREL_SIZE);
            matrix[BARREL_SIZE - yCoordinate - 1][xCoordinate] = BARREL_COLOR;
        }
    }

    /**
     * A method that gets the shift.
     * @param firstCoordinate - a first coordinate
     * @param lastCoordinate - a last coordinate
     * @return - the shift between two coordinates
     */
    private double getShift(double firstCoordinate, double lastCoordinate) {
        double result = lastCoordinate - firstCoordinate;
        if (result < 0) {
            return -(abs(result) / BARREL_SIZE);
        }

        return result / BARREL_SIZE;
    }

    /**
     * A method that makes barrel horizontal due to the barrel y.
     * @param cannonY - an y of the barrel
     *
     * This method is useful when we change y of the cannon.
     */
    public void makeBarrelHorizontal(double cannonY) {
        y = cannonY;
        lastY = y;
        lastX = x + BARREL_SIZE - 1;
    }
}
