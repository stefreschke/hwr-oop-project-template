package hwr.oop.chess.application.figures;

import hwr.oop.chess.application.Position;

public class PawnFigure extends Figure {
  public PawnFigure(FigureColor color, Position position) {
    super(FigureType.PAWN, color, position);
  }

  @Override
  public boolean canMoveTo(Position newPosition) {
    Position oldPosition = getPosition();
    if (getColor() == FigureColor.BLACK) {
      // Move one field straight down
      return (oldPosition.x() == newPosition.x()) && oldPosition.y() == (newPosition.y() - 1);

    } else {
      // Move one field straight up
      return (oldPosition.x() == newPosition.x()) && oldPosition.y() == (newPosition.y() + 1);
    }
  }
}
