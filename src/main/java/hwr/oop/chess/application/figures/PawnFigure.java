package hwr.oop.chess.application.figures;

import hwr.oop.chess.application.Board;
import hwr.oop.chess.application.Position;

public class PawnFigure implements Figure {
  Position startPosition = null;
  Position currentPosition = null;
  FigureType type = null;
  FigureColor color = null;


  public PawnFigure(FigureColor color, Position position) {
    this.type = FigureType.PAWN;
    this.color = color;
    this.startPosition = position;
    this.currentPosition = position;
  }


  public boolean canMoveTo(Position newPosition) {
    Position oldPosition = this.currentPosition;
    boolean isFieldBlocked = Board.isFigureOnField(newPosition);
    if (this.color == FigureColor.BLACK) {
      // Move one field straight down
      if(!isFieldBlocked
          && (oldPosition.x() == newPosition.x())
          && (oldPosition.y() - 1) == (newPosition.y())){
          return true;
      }

      // Move one field diagonally
      if (isFieldBlocked // check if a different figure is on the new field
             && Board.getFigureOnField(newPosition).color == FigureColor.WHITE
              && (Math.abs(oldPosition.x() - newPosition.x()) == 1)
            && (oldPosition.y() - 1) == newPosition.y()) {
          return true;
      }

      // Moving two fields from the start position
        return isFieldBlocked
                && (oldPosition.y() == 7)
                && (oldPosition.y() - 2) == newPosition.y()
                && (oldPosition.x() == newPosition.x());

    }
    if (this.color == FigureColor.WHITE) {
      // Move one field straight down
      if(!isFieldBlocked
              && (oldPosition.x() == newPosition.x())
              && (oldPosition.y() +1) == (newPosition.y())){
        return true;
        }
      }
      // Move one field straight up
      return !isFieldBlocked
              && (oldPosition.x() == newPosition.x())
              && (oldPosition.y() + 1) == newPosition.y();
    }

}

