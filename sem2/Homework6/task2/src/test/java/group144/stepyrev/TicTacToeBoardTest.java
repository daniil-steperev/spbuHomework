package group144.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeBoardTest {
    @Test
    public void horizontalWinTest() {
        /*
           X O O
           X X X
           O
         */

        TicTacToeBoard board = new TicTacToeBoard();
        board.makeMove(0);
        board.changeCurrentPlayer();

        board.makeMove(1);
        board.changeCurrentPlayer();

        board.makeMove(3);
        board.changeCurrentPlayer();

        board.makeMove(2);
        board.changeCurrentPlayer();

        board.makeMove(4);
        board.changeCurrentPlayer();

        board.makeMove(6);
        board.changeCurrentPlayer();

        board.makeMove(5);

        assertTrue(board.checkWinner());
        assertEquals('X', board.returnCurrentPlayer());
    }

    @Test
    public void verticalWinTest() {
        /*
            X O O
            X
            X
         */

        TicTacToeBoard board = new TicTacToeBoard();
        board.makeMove(0);
        board.changeCurrentPlayer();

        board.makeMove(1);
        board.changeCurrentPlayer();

        board.makeMove(3);
        board.changeCurrentPlayer();

        board.makeMove(2);
        board.changeCurrentPlayer();

        board.makeMove(6);

        assertTrue(board.checkWinner());
        assertEquals('X', board.returnCurrentPlayer());
    }

    @Test
    public void diagonalWinTest() {
        /*
            X O
            O X
                X
         */

        TicTacToeBoard board = new TicTacToeBoard();
        board.makeMove(0);
        board.changeCurrentPlayer();

        board.makeMove(1);
        board.changeCurrentPlayer();

        board.makeMove(4);
        board.changeCurrentPlayer();

        board.makeMove(3);
        board.changeCurrentPlayer();

        board.makeMove(8);

        assertTrue(board.checkWinner());
        assertEquals('X', board.returnCurrentPlayer());
    }

    @Test
    public void drawTest() {
        /*
            X O X
            O O X
            X X O
         */

        TicTacToeBoard board = new TicTacToeBoard();
        board.makeMove(0);
        board.changeCurrentPlayer();

        board.makeMove(1);
        board.changeCurrentPlayer();

        board.makeMove(2);
        board.changeCurrentPlayer();

        board.makeMove(3);
        board.changeCurrentPlayer();

        board.makeMove(5);
        board.changeCurrentPlayer();

        board.makeMove(4);
        board.changeCurrentPlayer();

        board.makeMove(6);
        board.changeCurrentPlayer();

        board.makeMove(8);
        board.changeCurrentPlayer();

        board.makeMove(7);

        assertFalse(board.checkWinner());
        assertTrue(board.isAllFilled());
    }
}