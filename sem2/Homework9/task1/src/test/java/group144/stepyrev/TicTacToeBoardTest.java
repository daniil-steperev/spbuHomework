package group144.stepyrev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeBoardTest {
    @Test
    public void makeMoveTest() {
        TicTacToeBoard board = new TicTacToeBoard();
        String previousPlayer = "";
        String currentPlayer = "";

        previousPlayer = board.getCurrentPlayer();
        assertTrue(board.makeMove(0));
        currentPlayer = board.getCurrentPlayer();
        assertNotEquals(previousPlayer, currentPlayer);


        previousPlayer = board.getCurrentPlayer();
        assertTrue(board.makeMove(8));
        currentPlayer = board.getCurrentPlayer();
        assertNotEquals(previousPlayer, currentPlayer);


        previousPlayer = board.getCurrentPlayer();
        assertFalse(board.makeMove(0));
        currentPlayer = board.getCurrentPlayer();
        assertEquals(previousPlayer, currentPlayer);

        previousPlayer = board.getCurrentPlayer();
        assertFalse(board.makeMove(8));
        currentPlayer = board.getCurrentPlayer();
        assertEquals(previousPlayer, currentPlayer);

        previousPlayer = board.getCurrentPlayer();
        assertFalse(board.makeMove(-1));
        currentPlayer = board.getCurrentPlayer();
        assertEquals(previousPlayer, currentPlayer);

        previousPlayer = board.getCurrentPlayer();
        assertFalse(board.makeMove(10));
        currentPlayer = board.getCurrentPlayer();
        assertEquals(previousPlayer, currentPlayer);
    }

    @Test
    public void getDrawResultTest() {
        /*
          X O X
          X O O
          O X X
        */
        TicTacToeBoard board = new TicTacToeBoard();

        assertTrue(board.makeMove(0));
        assertTrue(board.makeMove(1));
        assertTrue(board.makeMove(2));
        assertTrue(board.makeMove(4));
        assertTrue(board.makeMove(3));
        assertTrue(board.makeMove(5));
        assertTrue(board.makeMove(7));
        assertTrue(board.makeMove(6));
        assertTrue(board.makeMove(8));

        assertEquals(TicTacToeBoard.GameStatus.DRAW, board.getGameStatus());
    }

    @Test
    public void getPlayingResultTest() {
        TicTacToeBoard board = new TicTacToeBoard();
        assertEquals(TicTacToeBoard.GameStatus.PLAYING, board.getGameStatus());

        assertTrue(board.makeMove(0));
        assertTrue(board.makeMove(1));
        assertTrue(board.makeMove(2));
        assertTrue(board.makeMove(4));

        assertTrue(board.makeMove(3));
        assertEquals(TicTacToeBoard.GameStatus.PLAYING, board.getGameStatus());

        assertTrue(board.makeMove(5));
        assertTrue(board.makeMove(7));
        assertTrue(board.makeMove(6));
        assertEquals(TicTacToeBoard.GameStatus.PLAYING, board.getGameStatus());
    }

    @Test
    public void getWinnerResultTest() {
        TicTacToeBoard board = new TicTacToeBoard();

        assertTrue(board.makeMove(0));
        assertTrue(board.makeMove(1));
        assertTrue(board.makeMove(4));
        assertTrue(board.makeMove(6));
        assertTrue(board.makeMove(8));

        assertEquals(TicTacToeBoard.GameStatus.WIN, board.getGameStatus());
    }

}