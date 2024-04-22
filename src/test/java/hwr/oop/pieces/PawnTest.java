/*package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

  @Test
  void testConstructor() {
    Position position = new Position(0, 0);
    Pawn whitePawn = new Pawn(Color.WHITE, position);
    Pawn blackPawn = new Pawn(Color.BLACK, position);
    assertThat(whitePawn.getColor()).isEqualTo(Color.WHITE);
    assertThat(whitePawn.getPosition()).isEqualTo(position);
    assertThat(whitePawn.getSymbol()).isEqualTo('P');

    assertThat(blackPawn.getColor()).isEqualTo(Color.BLACK);
    assertThat(blackPawn.getPosition()).isEqualTo(position);
    assertThat(blackPawn.getSymbol()).isEqualTo('p');
  }

  @Test
  void testGetPosition() {
    Position position = new Position(3, 4);
    Pawn pawn = new Pawn(Color.WHITE, position);
    assertThat(pawn.getPosition()).isEqualTo(position);
  }

  @Test
  void testSetColor() {
    Position position = new Position(5, 6);
    Pawn pawn = new Pawn(Color.WHITE, position);
    assertThat(pawn.getColor()).isEqualTo(Color.WHITE);

    pawn.setColor(Color.BLACK);
    assertThat(pawn.getColor()).isEqualTo(Color.BLACK);
  }

  @Test
  void testSetPosition() {
    Position position = new Position(7, 8);
    Pawn pawn = new Pawn(Color.WHITE, position);
    assertThat(pawn.getPosition()).isEqualTo(position);

    Position newPosition = new Position(1, 1);
    pawn.setPosition(newPosition);
    assertThat(pawn.getPosition()).isEqualTo(newPosition);
  }

  @Test
  void testPawnToString() {
    Position position = new Position(3, 3);
    Pawn pawn = new Pawn(Color.WHITE, position);
    assertThat(pawn.toString())
        .hasToString("Piece{color=WHITE, position=Position[row=3, column=3], symbol=P}");
  }
}


 */