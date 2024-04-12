package hwr.oop.chess.application.figures;

import hwr.oop.chess.application.Position;

public abstract class Figure {

  // private final Board board;

  private Position startPosition;
  private Position currentPosition;

  private final FigureType type;

  private final FigureColor color;

  Figure(FigureType type, FigureColor color, Position startPosition) {
    this.type = type;
    this.color = color;
    this.startPosition = startPosition;
    this.currentPosition = startPosition;
  }

  public abstract boolean canMoveTo(Position newPosition);

  public void moveTo(Position newPosition) {
    if (canMoveTo(newPosition)) {
      this.currentPosition = newPosition;
    }
  }

  public void capture() {
    this.currentPosition = null;
  }

  public boolean isCaptured() {
    return currentPosition == null;
  }

  public void setPosition(Position position) {
    this.currentPosition = position;
  }

  public Position getPosition() {
    return this.currentPosition;
  }

  public FigureColor getColor() {
    return color;
  }

  public FigureType getType() {
    return type;
  }
}
