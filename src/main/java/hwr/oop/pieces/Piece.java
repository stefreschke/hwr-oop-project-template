package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;

public abstract class Piece {
  private Color color;
  private Position position;
  private final char symbol;

  protected Piece(Color color, Position position, char symbol) {
    this.color = color;
    this.position = position;
    this.symbol = symbol;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }



  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public char getSymbol() {
    return symbol;
  }

  @Override
  public String toString() {
    return "Piece{" +
            "color=" + color +
            ", position=" + position +
            ", symbol=" + symbol +
            '}';
  }
}
