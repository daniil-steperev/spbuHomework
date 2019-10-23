package cannon;

/** A class that represents a game controller. */
public class CannonController {
    /** A flag that represents if the up button was pressed. */
    private boolean isUpPressed;
    /** A flag that represents if the down button was presed. */
    private boolean isDownPressed;
    /** A flag that represents if the left button was pressed. */
    private boolean isLeftPressed;
    /** A flag that represents if the right button was pressed. */
    private boolean isRightPressed;

    /** A constructor of cannon controller class. */
    public CannonController() {
        isUpPressed = false;
        isDownPressed = false;
        isLeftPressed = false;
        isRightPressed = false;
    }

    /**
     * A method that sets a new value to the upPressed flag.
     * @param state - a value that upPressed should be.
     */
    public void setUpPressed(boolean state) {
        isUpPressed = state;
    }

    /**
     * A method that sets a new value to the downPressed flag.
     * @param state - a value that downPressed should be.
     */
    public void setDownPressed(boolean state) {
        isDownPressed = state;
    }

    /**
     * A method that sets a new value to the leftPressed flag.
     * @param state - a value that leftPressed should be.
     */
    public void setLeftPressed(boolean state) {
        isLeftPressed = state;
    }

    /**
     * A method that sets a new value to the rightPressed flag.
     * @param state - a value that rightPressed should be.
     */
    public void setRightPressed(boolean state) {
        isRightPressed = state;
    }

    /**
     * A method that returns the value of isUpPressed.
     * @return - a value of isUpPressed
     */
    public boolean getUpPressed() {
        return isUpPressed;
    }

    /**
     * A method that returns the value of isDownPressed.
     * @return - a value of isDownPressed
     */
    public boolean getDownPressed() {
        return isDownPressed;
    }

    /**
     * A method that returns the value of isLeftPressed.
     * @return - a value of isLeftPressed
     */
    public boolean getLeftPressed() {
        return isLeftPressed;
    }

    /**
     * A method that returns the value of isRightPressed.
     * @return - a value of isRightPressed
     */
    public boolean getRightPressed() {
        return isRightPressed;
    }
}
