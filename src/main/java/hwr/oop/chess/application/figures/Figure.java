package hwr.oop.chess.application.figures;

import hwr.oop.chess.application.Position;

public interface Figure {

  boolean canMoveTo(Position newPosition);
  boolean isOnField(Position field);
  boolean isCaptured();
  void setPosition(Position position);
  Position getPosition();
  FigureColor getColor();
  FigureType getType();
  void moveTo(Position newPosition);

  //  default Figure(FigureType type, FigureColor color, Position startPosition) {
//    this.type = type;
//    this.color = color;
//    this.startPosition = startPosition;
//    this.currentPosition = startPosition;
//  }

//  public abstract boolean canMoveTo(Position newPosition);
//
//  public void moveTo(Position newPosition) {
//    if (this.canMoveTo(newPosition)) {
//      this.currentPosition = newPosition;
//    }
//  }
//
//default boolean isOnField(Position field) {
//   return (field.x() == currentPosition.x()) && (field.y() == currentPosition.y());
//}
//
//  default void capture() {
//    this.currentPosition = null;
//  }

//  default boolean isCaptured() {
//    return currentPosition == null;
//  }

//  default void setPosition(Position position) {
//    this.currentPosition = position;
//  }

//  default Position getPosition() {
//    return this.currentPosition;
//  }
//
//  default FigureColor getColor() {
//    return this.color;
//  }
//
//  default FigureType getType() {
//    return this.type;
//  }
}
