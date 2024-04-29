package hwr.oop.chessTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.predicate;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import hwr.oop.chess.Board;
import hwr.oop.chess.Piece;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardTest {
  @Test
  void isValidMoveTest() {
    Board board = new Board();
    board.initBoard();
    Piece bauer1 = board.getPieceAt(1, 1);
    Piece bauer2 = board.getPieceAt(3, 6);
    Piece turm = board.getPieceAt(7, 0);
    Piece koenig = board.getPieceAt(4, 0);

    assertSoftly(
        softly -> {
          softly.assertThat(board.isValidMove(bauer1, 1, 2)).isTrue();
          softly.assertThat(board.isValidMove(bauer1, 1, 3)).isTrue();
          softly.assertThat(board.isValidMove(bauer1, 0, 3)).isFalse();
          softly.assertThat(board.isValidMove(bauer1, 0, 4)).isFalse();
          softly.assertThat(board.isValidMove(bauer1, 1, 1)).isFalse();
          softly.assertThat(board.isValidMove(bauer1, 1, 4)).isFalse();
          softly.assertThat(board.isValidMove(bauer2, 3, 5)).isTrue();
          softly.assertThat(board.isValidMove(bauer2, 3, 4)).isTrue();
          softly.assertThat(board.isValidMove(bauer2, 0, 3)).isFalse();
          softly.assertThat(board.isValidMove(bauer2, 0, 4)).isFalse();
          softly.assertThat(board.isValidMove(bauer2, 1, 1)).isFalse();
          softly.assertThat(board.isValidMove(bauer2, 1, 4)).isFalse();
          softly.assertThat(board.isValidMove(turm, 7, 4)).isTrue();
          softly.assertThat(board.isValidMove(turm, 0, 3)).isFalse();
          softly.assertThat(board.isValidMove(turm, 0, 4)).isFalse();
          softly.assertThat(board.isValidMove(turm, 1, 1)).isFalse();
          softly.assertThat(board.isValidMove(turm, 1, 3)).isFalse();
          softly.assertThat(board.isValidMove(turm, 1, 4)).isFalse();
          softly.assertThat(board.isValidMove(koenig, 3, 0)).isTrue();
          softly.assertThat(board.isValidMove(koenig, 1, 5)).isFalse();
          softly.assertThat(board.isValidMove(koenig, 2, 4)).isFalse();
          softly.assertThat(board.isValidMove(koenig, 3, 3)).isFalse();
          softly.assertThat(board.isValidMove(koenig, 4, 2)).isFalse();
        });
  }

  @Test
  void isBlockedTest() {
    Board board = new Board();
    board.initBoard();

    assertSoftly(
        softly -> {
          softly.assertThat(board.isBlocked(board.getPieceAt(0, 0), 0, 6)).isTrue();
          softly.assertThat(board.isBlocked(board.getPieceAt(4, 1), 4, 2)).isFalse();
        });
  }

  @Test
  void setBoardToFenTest() {
    Board board = new Board();

    board.setBoardToFen("r7/8/8/2kb4/4K3/8/8/7r");
    assertSoftly(
        softly -> {
          softly
              .assertThat(board.getPieceAt(3, 4).getAbbr())
              .isEqualTo(Piece.PieceType.LAEUFER.getAbbr());
          softly.assertThat(board.getPieceAt(3, 4).getColor()).isEqualTo(Piece.Color.BLACK);
          softly.assertThat(board.getPieceAt(0, 0)).isNull();
        });

    board.setBoardToFen("1r6/8/8/8/4K3/8/8/8");
    assertSoftly(
        softly -> {
          softly.assertThat(board.getPieceAt(0, 7)).isNull();
          softly.assertThat(board.getPieceAt(7, 0)).isNull();
        });
  }

  @Test
  void getFenOfBoardTest() {
    Board board = new Board();
    board.setBoardToFen("1rr5/8/8/8/4K3/8/8/6rr");

    Assertions.assertThat(board.getFenOfBoard()).isEqualTo("1rr5/8/8/8/4K3/8/8/6rr");
  }

  @Test
  void getPieceAtTest() {
    Board board = new Board();
    board.initBoard();

    assertSoftly(
        softly -> {
          softly.assertThat(board.getPieceAt(7, 0).getAbbr()).isEqualTo('t');
          softly.assertThat(board.getPieceAt(7, 0).getActPosition().getFirst()).isEqualTo(7);
          softly.assertThat(board.getPieceAt(7, 0).getColor()).isEqualTo(Piece.Color.WHITE);
        });
  }

  @Test
  void printBoardTest() {
    Board board = new Board();
    board.initBoard();
    board.printBoard();
    assertThat(board.getPieceAt(4, 4)).isNull();
  }

  @Test
  void testBoard() {
    Board board = new Board();

    assertThat(board.getPlayBoard()).hasSize(8);
    // TODO assertions nur am Ende
    for (List<Piece> row : board.getPlayBoard()) {
      assertThat(row).hasSize(8);
      for (Object piece : row) {
        assertThat(piece).isNull();
      }
    }
  }

  @Test
  void initBoardTest() {
    // TODO Assertions nur am Ende
    Board board = new Board();
    board.initBoard();

    int row = 0;
    int column;
    for (List<Piece> pieces : board.getPlayBoard()) {
      column = 0;
      for (Piece piece : pieces) {
        try {
          assertThat(piece.getActPosition()).isEqualTo(List.of(column, row));
        } catch (NullPointerException e) {
          assertThat(piece).isNull();
        }
        column++;
      }
      row++;
    }
  }

  @Test
  void changePosTest() {
    Board board = new Board();
    board.initBoard();
    Piece piece = board.getPieceAt(0, 1);
    board.changePos(1, 0, 0, 2);

    assertSoftly(
        softly -> {
          softly.assertThat(board.getPieceAt(0, 1)).isNull();
          softly.assertThat(piece).isEqualTo(board.getPieceAt(2, 0));
        });
  }
}
