package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

  @Test
  void testConstructor() {
    Position position = new Position(0, 0);
    Bishop whiteBishop = new Bishop(Color.WHITE, position);
    Bishop blackBishop = new Bishop(Color.BLACK, position);
    assertThat(whiteBishop.getColor()).isEqualTo(Color.WHITE);
    assertThat(whiteBishop.getPosition()).isEqualTo(position);
    assertThat(whiteBishop.getSymbol()).isEqualTo('B');

    assertThat(blackBishop.getColor()).isEqualTo(Color.BLACK);
    assertThat(blackBishop.getPosition()).isEqualTo(position);
    assertThat(blackBishop.getSymbol()).isEqualTo('b');
  }

  @Test
  void testGetPosition() {
    Position position = new Position(3, 4);
    Bishop bishop = new Bishop(Color.WHITE, position);
    assertThat(bishop.getPosition()).isEqualTo(position);
  }

  @Test
  void testSetColor() {
    Position position = new Position(5, 6);
    Bishop bishop = new Bishop(Color.WHITE, position);
    assertThat(bishop.getColor()).isEqualTo(Color.WHITE);

    bishop.setColor(Color.BLACK);
    assertThat(bishop.getColor()).isEqualTo(Color.BLACK);
  }

  @Test
  void testSetPosition() {
    Position position = new Position(7, 8);
    Bishop bishop = new Bishop(Color.WHITE, position);
    assertThat(bishop.getPosition()).isEqualTo(position);

    Position newPosition = new Position(1, 1);
    bishop.setPosition(newPosition);
    assertThat(bishop.getPosition()).isEqualTo(newPosition);
  }

  @Test
  void testBishopToString() {
    Position position = new Position(3, 3);
    Bishop bishop = new Bishop(Color.WHITE, position);
    assertThat(bishop.toString())
        .hasToString("Piece{color=WHITE, position=Position[row=3, column=3], symbol=B}");
  }
}
