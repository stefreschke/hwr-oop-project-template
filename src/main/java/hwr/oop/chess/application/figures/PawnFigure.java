package hwr.oop.chess.application.figures;

import hwr.oop.chess.application.Board;
import hwr.oop.chess.application.Position;

public class PawnFigure implements Figure {
  private Position startPosition = null;
  private Position currentPosition = null;
  private final FigureType type = FigureType.PAWN;
  private FigureColor color = null;

  public PawnFigure(FigureColor color, Position position) {
    this.color = color;
    this.startPosition = position;
    this.currentPosition = position;
  }

  public boolean canMoveTo(Position newPosition) {
    Position oldPosition = this.currentPosition;
    boolean isFieldBlocked = Board.isFigureOnField(newPosition);

    if (this.color == FigureColor.BLACK) {
      // Move one field straight down if the way is free
      if (!isFieldBlocked
          && (oldPosition.x()      == newPosition.x())
          && (oldPosition.y() - 1) == newPosition.y()) {
        return true;
      }

      // Moving two fields from the start position
      if (!isFieldBlocked
          && startPosition == oldPosition
          && (oldPosition.x()      == newPosition.x())
          && (oldPosition.y() - 2) == newPosition.y()) {
        return true;
      }

      // Move one field diagonally if the opponent is there
      Figure opponent = Board.getFigureOnField(newPosition);
      if (isFieldBlocked // check if a different figure is on the new field
          && opponent != null
          && (opponent.getColor() == FigureColor.WHITE)
          && (Math.abs(oldPosition.x() - newPosition.x()) == 1)
          && (oldPosition.y() - 1) == newPosition.y()) {
        opponent.setPosition(null); // catch opponent figure
        return true;
      }

    }


    if (this.color == FigureColor.WHITE) {
      // Move one field straight up if the way is free
      if (!isFieldBlocked
          && (oldPosition.x() == newPosition.x())
          && (oldPosition.y() + 1) == (newPosition.y())) {
        return true;
      }

      // Move one field diagonally if the opponent is there
      Figure opponent = Board.getFigureOnField(newPosition);
      if (isFieldBlocked // check if a different figure is on the new field
              && opponent != null
              && (opponent.getColor() == FigureColor.BLACK)
              && (Math.abs(oldPosition.x() - newPosition.x()) == 1)
              && (oldPosition.y() + 1) == newPosition.y()) {
        opponent.setPosition(null); // catch opponent figure
        return true;
      }

      // Moving two fields from the start position
      return !isFieldBlocked
              && startPosition == oldPosition
              && (oldPosition.y() + 2) == newPosition.y()
              && (oldPosition.x() == newPosition.x());
    }

    // this move is not allowed as it does not obey the rules.
    return false;
  }

  public boolean isOnField(Position field) {
    return this.currentPosition == field;
  }

  public boolean isCaptured() {
    return this.currentPosition == null;
  }

  public Position getPosition() {
    return this.currentPosition;
  }

  public void setPosition(Position position) {
    this.currentPosition = position;
  }

  public FigureColor getColor() {
    return this.color;
  }
}
