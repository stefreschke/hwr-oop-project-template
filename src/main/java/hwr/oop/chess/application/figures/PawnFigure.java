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

  @Override
  public boolean canMoveTo(Position newPosition) {
    Position oldPosition = this.currentPosition;
    boolean isFieldBlocked = Board.isFigureOnField(newPosition);

    int moveDirection = 1;
    if(this.color == FigureColor.BLACK) {
      moveDirection = -1;
    }

    // Move one field straight up/down if the way is free
    if (!isFieldBlocked
        && (oldPosition.x() == newPosition.x())
        && (oldPosition.y() + moveDirection) == newPosition.y()) {
      return true;
    }

    // Moving two fields from the start position if both fields are free
//    boolean isFieldInFrontOfPawnBlocked =
    if (!isFieldBlocked // field to move to is free
        && !Board.isFigureOnField(new Position(oldPosition.x(), oldPosition.y() + moveDirection)) // field between current and final field

        && (startPosition == oldPosition)
        && (oldPosition.x()      == newPosition.x())
        && (oldPosition.y() + 2 * moveDirection) == newPosition.y()) {
      return true;
    }

    // Move one field diagonally if the opponent is there
    Figure opponent = Board.getFigureOnField(newPosition);
    if (isFieldBlocked // check if a different figure is on the new field
        && opponent != null
        && (opponent.getColor() == FigureColor.WHITE)
        && (Math.abs(oldPosition.x() - newPosition.x()) == 1)
        && (oldPosition.y() + moveDirection) == newPosition.y()) {
      opponent.setPosition(null); // catch opponent figure
      return true;
    }

    // this move is not allowed as it does not obey the rules.
    return false;
  }

  @Override
  public void moveTo(Position newPosition) {
   if(canMoveTo(newPosition)) {
     this.currentPosition = newPosition;
   }
  }

  @Override
  public boolean isOnField(Position field) {
    return this.currentPosition == field;
  }

  @Override
  public boolean isCaptured() {
    return this.currentPosition == null;
  }

  @Override
  public Position getPosition() {
    return this.currentPosition;
  }

  @Override
  public void setPosition(Position position) {
    this.currentPosition = position;
  }

  @Override
  public FigureColor getColor() {
    return this.color;
  }

  @Override
  public FigureType getType() {
    return this.type;
  }
}
