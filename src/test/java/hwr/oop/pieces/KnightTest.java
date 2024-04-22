/*package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

  @Test
  void testConstructor() {
    Position position = new Position(0, 0);
    Knight whiteKnight = new Knight(Color.WHITE, position);
    Knight blackKnight = new Knight(Color.BLACK, position);
    assertThat(whiteKnight.getColor()).isEqualTo(Color.WHITE);
    assertThat(whiteKnight.getPosition()).isEqualTo(position);
    assertThat(whiteKnight.getSymbol()).isEqualTo('N');

    assertThat(blackKnight.getColor()).isEqualTo(Color.BLACK);
    assertThat(blackKnight.getPosition()).isEqualTo(position);
    assertThat(blackKnight.getSymbol()).isEqualTo('n');
  }

  @Test
  void testGetPosition() {
    Position position = new Position(3, 4);
    Knight knight = new Knight(Color.WHITE, position);
    assertThat(knight.getPosition()).isEqualTo(position);
  }

  @Test
  void testSetColor() {
    Position position = new Position(5, 6);
    Knight knight = new Knight(Color.WHITE, position);
    assertThat(knight.getColor()).isEqualTo(Color.WHITE);

    knight.setColor(Color.BLACK);
    assertThat(knight.getColor()).isEqualTo(Color.BLACK);
  }

  @Test
  void testSetPosition() {
    Position position = new Position(7, 8);
    Knight knight = new Knight(Color.WHITE, position);
    assertThat(knight.getPosition()).isEqualTo(position);

    Position newPosition = new Position(1, 1);
    knight.setPosition(newPosition);
    assertThat(knight.getPosition()).isEqualTo(newPosition);
  }

  @Test
  void testKnightToString() {
    Position position = new Position(3, 3);
    Knight knight = new Knight(Color.WHITE, position);
    assertThat(knight.toString())
        .hasToString("Piece{color=WHITE, position=Position[row=3, column=3], symbol=N}");
  }
}
*/