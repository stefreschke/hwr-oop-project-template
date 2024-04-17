package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

  @Test
  void testConstructor() {
    Position position = new Position(0, 0);
    Queen whiteQueen = new Queen(Color.WHITE, position);
    Queen blackQueen = new Queen(Color.BLACK, position);
    assertThat(whiteQueen.getColor()).isEqualTo(Color.WHITE);
    assertThat(whiteQueen.getPosition()).isEqualTo(position);
    assertThat(whiteQueen.getSymbol()).isEqualTo('Q');

    assertThat(blackQueen.getColor()).isEqualTo(Color.BLACK);
    assertThat(blackQueen.getPosition()).isEqualTo(position);
    assertThat(blackQueen.getSymbol()).isEqualTo('q');
  }

  @Test
  void testGetPosition() {
    Position position = new Position(3, 4);
    Queen queen = new Queen(Color.WHITE, position);
    assertThat(queen.getPosition()).isEqualTo(position);
  }

  @Test
  void testSetColor() {
    Position position = new Position(5, 6);
    Queen queen = new Queen(Color.WHITE, position);
    assertThat(queen.getColor()).isEqualTo(Color.WHITE);

    queen.setColor(Color.BLACK);
    assertThat(queen.getColor()).isEqualTo(Color.BLACK);
  }

  @Test
  void testSetPosition() {
    Position position = new Position(7, 8);
    Queen queen = new Queen(Color.WHITE, position);
    assertThat(queen.getPosition()).isEqualTo(position);

    Position newPosition = new Position(1, 1);
    queen.setPosition(newPosition);
    assertThat(queen.getPosition()).isEqualTo(newPosition);
  }

  @Test
  void testQueenToString() {
    Position position = new Position(3, 3);
    Queen queen = new Queen(Color.WHITE, position);
    assertThat(queen.toString()).hasToString("Piece{color=WHITE, position=Position[row=3, column=3], symbol=Q}");
  }
}

