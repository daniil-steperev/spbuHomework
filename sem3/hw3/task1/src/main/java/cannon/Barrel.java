package cannon;

import static java.lang.Math.*;

/** A class that represents a barrel of the cannon. */
public class Barrel extends GameObject {
    private double lastX;
    private double lastY;
    private final double RADIUS = 6;
    private final int BARREL_SIZE = 6;

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
        lastX = x + 5;
        lastY = y;
    }

    /** A method that moves the barrel.
     *
     *  Moving produced using the Bresenham Algorithm.
     * */
    public void move(double shift, double angle) {
        x += shift;

        if (y == lastY && angle <= 0) { // horizontal orientation
            return;
        }

        if (x == lastX && angle >= 0) { // vertical orientation
            return;
        }

        if (angle != 0) {
            lastY += angle;
            lastX = getXOnCircle();
        }
    }

    /** A method that draws the cannon. */
    @Override
    public void draw(GameApplication game) {
        super.draw(game);
        drawBresenhamLine(x, y, lastX, lastY);
    }

    /**
     * A method that counts x for y.
     *
     * A counting occurred due to expression x^2 + y^2 = 6^2.
     * @return - a value of x
     */
    private double getXOnCircle() {
        double yLength = pow(RADIUS, 2) - pow(lastY, 2);
        return x - sqrt(abs(yLength));
    }

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

        double angle = atan(abs((yStart - yEnd) / (xStart - xEnd)));
        if (angle >= 45.0 && angle <= 90.0) {
            drawLineAbove(xStart, yStart, xEnd, yEnd);
        } else if (angle < 45.0 && angle >= 0){
            drawLineBelow(xStart, yStart, xEnd, yEnd);
        }
    }

    private void drawLineBelow(double xStart, double yStart, double xEnd, double yEnd)
    {
        double currentY = yStart;
        int xCoordinate = 0;
        int yCoordinate = 0;

        matrix[BARREL_SIZE - yCoordinate - 1][xCoordinate] = 2; // initialize first pixel

        for (int i = 1; i < BARREL_SIZE; i++)
        {
            double yShift = getShift(yStart, yEnd);

            currentY += yShift;

            xCoordinate += 1;
            yCoordinate = abs((int) Math.round(yStart - currentY) % BARREL_SIZE);
            matrix[BARREL_SIZE - yCoordinate - 1][xCoordinate] = 2;
        }
    }

    private void drawLineAbove(double xStart, double yStart, double xEnd, double yEnd)
    {
        double currentX = xStart;
        int xCoordinate = 0;
        int yCoordinate = 0;

        matrix[BARREL_SIZE - yCoordinate - 1][xCoordinate] = 2; // initialize first pixel
        for (int i = 1; i < BARREL_SIZE; i++)
        {
            double xShift = getShift(xStart, xEnd);

            currentX += xShift;

            yCoordinate += 1;
            xCoordinate = abs((int) Math.round(xStart - currentX) % BARREL_SIZE);
            matrix[yCoordinate][xCoordinate] = 2;
        }
    }

    private boolean isAboveDiagonal() {
        for (int i = 0; i < BARREL_SIZE; i++) {
            for (int j = 0; j < BARREL_SIZE; j++) {
                if (barrel[i][j] != 0 && i >= j) {
                    return false;
                }
            }
        }

        return true;
    }

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
    }
}
