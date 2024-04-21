package classTests;

import classes.Board;
import classes.Piece;
import java.util.ArrayList;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardTest {
  @Test
  void isValidMoveTest() {
    Board board = new Board();
    board.initBoard();
    Piece piece = board.getPieceAt(1, 0); // weisser bauer
    Assertions.assertThat(board.isValidMove(piece, 0, 2))
        .isTrue(); // bauer bewegt sich eins nach vorn
    Assertions.assertThat(board.isValidMove(piece, 0, 3))
        .isFalse(); // bauer kann nicht seitlich gehen
  }

  @Test
  void isBlockedTest() {
    Board board = new Board();
    board.initBoard();
    Assertions.assertThat(board.isBlocked(board.getPieceAt(0, 0), 0, 6)).isTrue();
    Assertions.assertThat(board.isBlocked(board.getPieceAt(0, 2), 4, 2)).isTrue();
  }

  @Test
  void setBoardToFenTest() {
    Board board = new Board();

    board.setBoardToFen("8/8/8/2kb4/4K3/8/8/1r6");

    Assertions.assertThat(board.getPieceAt(4, 3).getAbbr())
        .isEqualTo(Piece.PieceType.LAEUFER.getAbbr());
    Assertions.assertThat(board.getPieceAt(0, 0)).isNull();

    board.setBoardToFen("8/8/8/8/4K3/8/8/1r6");
    Assertions.assertThat(board.getPieceAt(2, 4)).isNull();
  }

  @Test
  void getPieceAtTest(){
    Board board = new Board();
    board.initBoard();

    Assertions.assertThat(board.getPieceAt(0, 7).getAbbr()).isEqualTo('t');
    Assertions.assertThat(board.getPieceAt(0, 7).getActPosition().get(1)).isEqualTo(7);
    Assertions.assertThat(board.getPieceAt(0, 7).getColor()).isEqualTo(Piece.Color.WHITE);

  }

  @Test
  void initBoardTest() {
    Board board = new Board();
    board.initBoard();
    int row = 0;
    int column = 0;
    for (ArrayList<Piece> pieces : board.getBoard()) {
      column = 0;

      for (Piece piece : pieces) {

        try {
          Assertions.assertThat(piece.getActPosition()).isEqualTo(Arrays.asList(row, column));
        } catch (NullPointerException e) {
          Assertions.assertThat(piece).isNull();
        }
        column++;
      }
      row++;
    }
  }
}
