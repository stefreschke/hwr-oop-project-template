package classTests;

import classes.Board;
import classes.Piece;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class BoardTest {
    @Test
    void testIsValidMove() {
        Board board = new Board();
        board.initBoard();
        Piece piece = board.getPieceAt(0, 1); // weisser bauer
        Assertions.assertThat(board.isValidMove(piece, 0, 2)).isTrue(); // bauer bewegt sich eins nach vorn
        Assertions.assertThat(board.isValidMove(piece, 3, 0)).isFalse(); // bauer kann nicht seitlich gehen
    }

    @Test
    void get_isBlocked(){
        Board board = new Board();
        board.initBoard();
        Assertions.assertThat(board.isBlocked(board.getBoard()[0][0], 0,6,0,0)).isTrue();
        Assertions.assertThat(board.isBlocked(board.getBoard()[2][0], 4,2,0,2)).isTrue();
    }
}