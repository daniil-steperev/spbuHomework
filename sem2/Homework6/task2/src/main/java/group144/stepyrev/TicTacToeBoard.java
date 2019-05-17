package group144.stepyrev;

/** A class that represents a tic-tac-toe board. */
public class TicTacToeBoard {
    private char[] board = new char[9];
    private char currentPlayer = 'X';
    private int emptyCells = 9;

    /** A method that changes current player to another one. */
    public void changeCurrentPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
            return;
        }

        currentPlayer = 'X';
    }

    /** A method that carries a new move.
     *
     * If the cell was already used, nothing happens.
     */
    public void makeMove(int cellNumber) {
        board[cellNumber] = currentPlayer;
        emptyCells--;
    }

    /** A method that checks if all cells are filled. */
    public boolean isAllFilled() {
        return emptyCells == 0;
    }

    /** A method that checks if the game has ended. */
    public boolean checkForWinningCombination() {
        return checkOneCombination(0,board.length / 3, board.length / 3, 1) || // verticals
                checkOneCombination(0,board.length, 1, board.length / 3) || // horizontals
                checkOneCombination(0,1, 4, 1) || // main diagonal
                checkOneCombination(2, 3, 2, 2); // incidental diagonal
    }

    private boolean checkOneCombination(int start, int repeatNumber, int addedNumber, int increment) {
        boolean result = false;
        char cellNumber = board[0];

        for (int i = start; i < repeatNumber; i += increment) {
            cellNumber = board[i];
            result = (board[i] == board[i + addedNumber] && board[i] == board[i + addedNumber * 2]);

            if (result && (cellNumber == 'X' || cellNumber == 'O')) {
                return true;
            }
        }

        return false;
    }

    /**
     * A method that returns a current player name.
     * @return name of current player
     */
    public char returnCurrentPlayer() {
        return currentPlayer;
    }
}
