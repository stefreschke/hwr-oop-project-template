package hwr.oop.chessTests;

import hwr.oop.chess.Piece;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class PieceTest {
  @Test
  void isMoveRepeatableTest() {
    Piece turm = new Piece(Piece.PieceType.TURM, Arrays.asList(0, 0), Piece.Color.BLACK);
    Piece laeufer = new Piece(Piece.PieceType.LAEUFER, Arrays.asList(0, 0), Piece.Color.BLACK);
    Piece dame = new Piece(Piece.PieceType.DAME, Arrays.asList(0, 0), Piece.Color.BLACK);
    Piece springer = new Piece(Piece.PieceType.SPRINGER, Arrays.asList(0, 0), Piece.Color.BLACK);

    assertSoftly(
        softly -> {
          softly.assertThat(turm.isMoveRepeatable()).isTrue();
          softly.assertThat(laeufer.isMoveRepeatable()).isTrue();
          softly.assertThat(dame.isMoveRepeatable()).isTrue();
          softly.assertThat(springer.isMoveRepeatable()).isFalse();
        });
  }

  @Test
  void getActPositionTest() {
    Piece turm = new Piece(Piece.PieceType.TURM, Arrays.asList(1, 0), Piece.Color.BLACK);
    Piece laeufer = new Piece(Piece.PieceType.LAEUFER, Arrays.asList(0, 1), Piece.Color.BLACK);
    Piece dame = new Piece(Piece.PieceType.DAME, Arrays.asList(4, 2), Piece.Color.BLACK);
    Piece springer = new Piece(Piece.PieceType.SPRINGER, Arrays.asList(6, 1), Piece.Color.BLACK);

    assertSoftly(
        softly -> {
          softly.assertThat(turm.getActPosition()).isEqualTo(Arrays.asList(1, 0));
          softly.assertThat(laeufer.getActPosition()).isEqualTo(Arrays.asList(0, 1));
          softly.assertThat(dame.getActPosition()).isEqualTo(Arrays.asList(4, 2));
          softly.assertThat(springer.getActPosition()).isEqualTo(Arrays.asList(6, 1));
        });
  }

  @Test
  void setActPositionTest() {
    Piece turm = new Piece(Piece.PieceType.TURM, Arrays.asList(1, 0), Piece.Color.BLACK);
    Piece laeufer = new Piece(Piece.PieceType.LAEUFER, Arrays.asList(0, 1), Piece.Color.BLACK);

    turm.setActPosition(Arrays.asList(4, 3));
    laeufer.setActPosition(Arrays.asList(2, 6));

    assertSoftly(
        softly -> {
          softly.assertThat(turm.getActPosition()).isEqualTo(Arrays.asList(4, 3));
          softly.assertThat(laeufer.getActPosition()).isEqualTo(Arrays.asList(2, 6));
        });
  }
}
