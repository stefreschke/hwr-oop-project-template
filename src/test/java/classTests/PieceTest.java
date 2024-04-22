package classTests;

import classes.Piece;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PieceTest {
  @Test
  void isMoveRepeatableTest() {
    Piece turm = new Piece(Piece.PieceType.TURM, Arrays.asList(0, 0), Piece.Color.BLACK);
    Piece laeufer = new Piece(Piece.PieceType.LAEUFER, Arrays.asList(0, 0), Piece.Color.BLACK);
    Piece dame = new Piece(Piece.PieceType.DAME, Arrays.asList(0, 0), Piece.Color.BLACK);
    Piece springer = new Piece(Piece.PieceType.SPRINGER, Arrays.asList(0, 0), Piece.Color.BLACK);

    Assertions.assertThat(turm.isMoveRepeatable()).isTrue();
    Assertions.assertThat(laeufer.isMoveRepeatable()).isTrue();
    Assertions.assertThat(dame.isMoveRepeatable()).isTrue();
    Assertions.assertThat(springer.isMoveRepeatable()).isFalse();
  }

  @Test
  void getActPositionTest() {
    Piece turm = new Piece(Piece.PieceType.TURM, Arrays.asList(1, 0), Piece.Color.BLACK);
    Piece laeufer = new Piece(Piece.PieceType.LAEUFER, Arrays.asList(0, 1), Piece.Color.BLACK);
    Piece dame = new Piece(Piece.PieceType.DAME, Arrays.asList(4, 2), Piece.Color.BLACK);
    Piece springer = new Piece(Piece.PieceType.SPRINGER, Arrays.asList(6, 1), Piece.Color.BLACK);

    Assertions.assertThat(turm.getActPosition()).isEqualTo(Arrays.asList(1, 0));
    Assertions.assertThat(laeufer.getActPosition()).isEqualTo(Arrays.asList(0, 1));
    Assertions.assertThat(dame.getActPosition()).isEqualTo(Arrays.asList(4, 2));
    Assertions.assertThat(springer.getActPosition()).isEqualTo(Arrays.asList(6, 1));
  }

  @Test
  void setActPositionTest() {
    Piece turm = new Piece(Piece.PieceType.TURM, Arrays.asList(1, 0), Piece.Color.BLACK);
    Piece laeufer = new Piece(Piece.PieceType.LAEUFER, Arrays.asList(0, 1), Piece.Color.BLACK);

    Assertions.assertThat(turm.getActPosition()).isEqualTo(Arrays.asList(1, 0));
    Assertions.assertThat(laeufer.getActPosition()).isEqualTo(Arrays.asList(0, 1));

    turm.setActPosition(Arrays.asList(4, 3));
    laeufer.setActPosition(Arrays.asList(2, 6));

    Assertions.assertThat(turm.getActPosition()).isEqualTo(Arrays.asList(4, 3));
    Assertions.assertThat(laeufer.getActPosition()).isEqualTo(Arrays.asList(2, 6));
  }
}
