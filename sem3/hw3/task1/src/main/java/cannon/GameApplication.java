package cannon;

import com.javarush.engine.cell.*;

/** A class that represents a game application. */
public class GameApplication extends Game {
    /** A width of the game field. */
    public static final int WIDTH = 100;
    /** A height of the game field. */
    public static final int HEIGHT = 100;
    /** A start x coordinate of the cannon. */
    public static final int START_X = 2;
    /** A start y coordinate of the cannon. */
    public static final int START_Y = HEIGHT / 2;

    /** A cannon controller. */
    private CannonController controller;

    /** A cannon. */
    private Cannon cannon;
    /** A landscape. */
    private GameObject landscape;


    /** A method that initializes a game field. */
    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
        showGrid(false);
    }

    /** A method that creates a game. */
    private void createGame() {
        createGameObjects();
        drawScene();
        setTurnTimer(10);

        controller = new CannonController();
        controller.setLeftPressed(false);
        controller.setRightPressed(false);
        controller.setUpPressed(false);
        controller.setDownPressed(false);
    }

    /** A method that draws a scene. */
    private void drawScene() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellColor(x, y, Color.ORANGE);
            }
        }

        cannon.draw(this);
        landscape.draw(this);
    }

    /** A method that creates a game objects. */
    private void createGameObjects() {
        cannon = new Cannon(START_X, START_Y);
        landscape = new GameObject(0 ,25, ShapeMatrix.LANDSCAPE);
    }

    /** A method that realizes events that occurs during one turn. */
    public void onTurn(int step) {
        cannon.move(controller);
        drawScene();
    }

    /** A method that reflects all events that occurs when key button was pressed. */
    public void onKeyPress(Key key) {
        switch (key) {
            case UP:
                controller.setUpPressed(true);
                controller.setDownPressed(false);
                break;
            case DOWN:
                controller.setDownPressed(true);
                controller.setUpPressed(false);
                break;
            case RIGHT:
                controller.setRightPressed(true);
                controller.setLeftPressed(false);
                break;
            case LEFT:
                controller.setLeftPressed(true);
                controller.setRightPressed(false);
                break;
            case ENTER:

        }
    }

    /** A method that reflects all events that occurs when key button was unpressed. */
    public void onKeyReleased(Key key) {
        switch (key) {
            case UP:
                controller.setUpPressed(false);
                break;
            case DOWN:
                controller.setDownPressed(false);
                break;
            case RIGHT:
                controller.setRightPressed(false);
                break;
            case LEFT:
                controller.setLeftPressed(false);
                break;
            case ENTER:
                //FIXME
                break;
        }
    }

}
