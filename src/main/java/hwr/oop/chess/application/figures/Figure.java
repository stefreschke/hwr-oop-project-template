package hwr.oop.chess.application.figures;

import hwr.oop.chess.application.Position;

public abstract interface Figure {
  Position startPosition = null;
  Position currentPosition = null;
  FigureType type = null;
  FigureColor color = null;

//  Figure(FigureType type, FigureColor color, Position startPosition) {
//    this.type = type;
//    this.color = color;
//    this.startPosition = startPosition;
//    this.currentPosition = startPosition;
//  }
//
//  public abstract boolean canMoveTo(Position newPosition);
//
//  public void moveTo(Position newPosition) {
//    if (this.canMoveTo(newPosition)) {
//      this.currentPosition = newPosition;
//    }
//  }
//
//  public boolean isOnField(Position field) {
//    return (field.x() == currentPosition.x()) && (field.y() == currentPosition.y());
//  }
//
//  public void capture() {
//    this.currentPosition = null;
//  }
//
//  public boolean isCaptured() {
//    return currentPosition == null;
//  }
//
//  public void setPosition(Position position) {
//    this.currentPosition = position;
//  }
//
//  public Position getPosition() {
//    return this.currentPosition;
//  }
//
//  public FigureColor getColor() {
//    return this.color;
//  }
//
//  public FigureType getType() {
//    return this.type;
//  }
}
