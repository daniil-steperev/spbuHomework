package cannon;

import com.javarush.engine.cell.*;

/** A class that represents an object of moon lander game. */
public class GameObject {
    /** A 'x' coordinate. */
    public double x;
    /** A 'y' coordinate. */
    public double y;

    /** An outlook of the object;*/
    public int[][] matrix;
    /** A width of the object. */
    public int width;
    /** A height of the object. */
    public int height;

    /** A constructor of the class. */
    public GameObject(double x, double y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
        this.width = matrix[0].length;
        this.height = matrix.length;
    }

    /** A method that draws the object. */
    public void draw(GameApplication game) {
        if(matrix == null) {
            return;
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int colorIndex = matrix[j][i];
                game.setCellColor((int) x + i, (int) y + j, Color.values()[colorIndex]);
            }
        }
    }
}
