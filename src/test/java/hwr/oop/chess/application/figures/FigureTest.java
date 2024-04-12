package hwr.oop.chess.application.figures;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import hwr.oop.chess.application.Position;

class FigureTest {
  @Test
  void createPawn() {
    Position position = new Position('b', 2);
    PawnFigure pawn = new PawnFigure(FigureColor.BLACK, position);
    Assertions.assertThat(pawn.getColor()).isEqualTo(FigureColor.BLACK);
    Assertions.assertThat(pawn.getType()).isEqualTo(FigureType.PAWN);
    Assertions.assertThat(pawn.getPosition()).isEqualTo(position);
  }

  @Test
  void movePawnOneField() {
    Position position = new Position('b', 2);
    PawnFigure pawn = new PawnFigure(FigureColor.BLACK, position);
    Assertions.assertThat(pawn.getPosition()).isEqualTo(position);

    Position newPosition = new Position('b', 3);
    Assertions.assertThat(pawn.canMoveTo(newPosition)).isTrue();
    Assertions.assertThat(pawn.getPosition()).isEqualTo(position);
    pawn.moveTo(newPosition);
    Assertions.assertThat(pawn.getPosition()).isEqualTo(newPosition);
  }

  @Test
  void movePawnThreeFields() {
    Position position = new Position('b', 2);
    PawnFigure pawn = new PawnFigure(FigureColor.BLACK, position);
    Assertions.assertThat(pawn.getPosition()).isEqualTo(position);

    Position newPosition = new Position('b', 5);
    Assertions.assertThat(pawn.canMoveTo(newPosition)).isFalse();
  }
}
