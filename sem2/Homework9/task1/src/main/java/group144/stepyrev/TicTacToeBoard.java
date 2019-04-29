package group144.stepyrev;

public class TicTacToeBoard {
    private char[] board = new char[9];
    private char currentPlayer = 'X';
    private int emptyCells = 9;
    private int size = 9;

    /** A method that changes current player to another one. */
    public void changeCurrentPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
            return;
        }

        currentPlayer = 'X';
    }

    public String getCurrentPlayer() {
        return String.valueOf(currentPlayer);
    }


    public boolean makeMove(int cellNumber) {
        if (board[cellNumber] == 'X' || board[cellNumber] == 'O') {
            return false;
        }

        board[cellNumber] = currentPlayer;
        emptyCells--;
        return true;
    }

    public enum GameStatus {
        PLAYING,
        DRAW,
        WIN
    }

    public GameStatus getGameStatus() {
        if (isAllFilled()) {
            return GameStatus.DRAW;
        }

        if (checkWinner()) {
            return GameStatus.WIN;
        }

        return GameStatus.PLAYING;
    }

    /** A method that checks if all cells are filled. */
    public boolean isAllFilled() {
        return emptyCells == 0;
    }

    /** A method that checks if the player has won the game. */
    public boolean checkWinner() {
        return checkHorizontals() || checkVerticals() || checkDiagonals();
    }

    /** A method that checks if there is a winning combination horizontally. */
    private boolean checkHorizontals() {
        return (board[0] == board[1] && board[0] ==  board[2]) && (board[0] == 'X' || board[0] == 'O') ||
                (board[3] == board[4] && board[3] == board[5]) && (board[3] == 'X' || board[3] == 'O') ||
                (board[6] == board[7] && board[6] == board[8]) && (board[6] == 'X' || board[6] == 'O');
    }

    /** A method that checks if there is a winning combination vertically. */
    private boolean checkVerticals() {
        return (board[0] == board[3] && board[0] ==  board[6]) && (board[0] == 'X' || board[0] == 'O') ||
                (board[1] == board[4] && board[1] == board[7]) && (board[1] == 'X' || board[1] == 'O') ||
                (board[2] == board[5] && board[2] == board[8]) && (board[2] == 'X' || board[2] == 'O');
    }

    /** A method that checks if there is a winning combination diagonally. */
    private boolean checkDiagonals() {
        return (board[0] == board[4] && board[0] ==  board[8]) && (board[0] == 'X' || board[0] == 'O') ||
                (board[2] == board[4] && board[2] == board[6]) && (board[2] == 'X' || board[2] == 'O');
    }

    /**
     * A method that returns a current player name.
     * @return name of current player
     */
    public char returnCurrentPlayer() {
        return currentPlayer;
    }


    public int getSize() {
        return size;
    }
}
