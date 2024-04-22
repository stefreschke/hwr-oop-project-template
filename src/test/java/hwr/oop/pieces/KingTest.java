package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

  @Test
  void testConstructor() {
    Position position = new Position(0, 0);
    King whiteKing = new King(Color.WHITE, position);
    King blackKing = new King(Color.BLACK, position);
    assertThat(whiteKing.getColor()).isEqualTo(Color.WHITE);
    assertThat(whiteKing.getPosition()).isEqualTo(position);
    assertThat(whiteKing.getSymbol()).isEqualTo('K');

    assertThat(blackKing.getColor()).isEqualTo(Color.BLACK);
    assertThat(blackKing.getPosition()).isEqualTo(position);
    assertThat(blackKing.getSymbol()).isEqualTo('k');
  }

  @Test
  void testGetPosition() {
    Position position = new Position(3, 4);
    King king = new King(Color.WHITE, position);
    assertThat(king.getPosition()).isEqualTo(position);
  }

  @Test
  void testSetColor() {
    Position position = new Position(5, 6);
    King king = new King(Color.WHITE, position);
    assertThat(king.getColor()).isEqualTo(Color.WHITE);

    king.setColor(Color.BLACK);
    assertThat(king.getColor()).isEqualTo(Color.BLACK);
  }

  @Test
  void testSetPosition() {
    Position position = new Position(7, 8);
    King king = new King(Color.WHITE, position);
    assertThat(king.getPosition()).isEqualTo(position);

    Position newPosition = new Position(1, 1);
    king.setPosition(newPosition);
    assertThat(king.getPosition()).isEqualTo(newPosition);
  }

  @Test
  void testKingToString() {
    Position position = new Position(3, 3);
    King king = new King(Color.WHITE, position);
    assertThat(king.toString())
        .hasToString("Piece{color=WHITE, position=Position[row=3, column=3], symbol=K}");
  }
}
