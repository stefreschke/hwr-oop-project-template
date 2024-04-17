package classTests;

import classes.Board;
import classes.Piece;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @Test
    public void testIsValidMoveSuccess() {
        Board board = new Board();
        board.initBoard();

        Piece piece = board.getPieceAt(0, 1); // weisser bauer
        assertThat(board.isValidMove(piece, 0, 2)).isTrue(); // bauer bewegt sich eins nach vorn
    }

    @Test
    public void testIsValidMoveFail() {

        Board board = new Board();
        board.initBoard();

        Piece piece = board.getPieceAt(0, 1); // weisser bauer
        assertThat(board.isValidMove(piece, 3, 0)).isFalse(); // bauer kann nicht seitlich gehen
    }
}
