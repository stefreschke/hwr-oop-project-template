package hwr.oop.pieces;
import hwr.oop.Color;
import hwr.oop.Position;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

  @Test
  void testConstructor() {
    Position position = new Position(0, 0);
    Rook whiteRook = new Rook(Color.WHITE, position);
    Rook blackRook = new Rook(Color.BLACK, position);
    assertThat(whiteRook.getColor()).isEqualTo(Color.WHITE);
    assertThat(whiteRook.getPosition()).isEqualTo(position);
    assertThat(whiteRook.getSymbol()).isEqualTo('R');


    assertThat(blackRook.getColor()).isEqualTo(Color.BLACK);
    assertThat(blackRook.getPosition()).isEqualTo(position);
    assertThat(blackRook.getSymbol()).isEqualTo('r');
  }

  @Test
  void testGetPosition() {
    Position position = new Position(3, 4);
    Rook rook = new Rook(Color.WHITE, position);
    assertThat(rook.getPosition()).isEqualTo(position);
  }

  @Test
  void testSetColor() {
    Position position = new Position(5, 6);
    Rook rook = new Rook(Color.WHITE, position);
    assertThat(rook.getColor()).isEqualTo(Color.WHITE);

    rook.setColor(Color.BLACK);
    assertThat(rook.getColor()).isEqualTo(Color.BLACK);
  }

  @Test
  void testSetPosition() {
    Position position = new Position(7, 8);
    Rook rook = new Rook(Color.WHITE, position);
    assertThat(rook.getPosition()).isEqualTo(position);

    Position newPosition = new Position(1, 1);
    rook.setPosition(newPosition);
    assertThat(rook.getPosition()).isEqualTo(newPosition);
  }

  @Test
  void testRookToString() {
    Position position = new Position(3, 3);
    Rook rook = new Rook(Color.WHITE, position);
    assertThat(rook.toString()).hasToString("Piece{color=WHITE, position=Position[row=3, column=3], symbol=R}");
  }
}
