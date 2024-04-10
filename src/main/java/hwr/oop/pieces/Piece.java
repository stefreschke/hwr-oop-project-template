package hwr.oop.pieces;

import hwr.oop.Color;
import hwr.oop.Position;

public abstract class Piece {
  private Color color;
  private Position position;
  private char symbol;

  public Piece(Color color, Position position, char symbol) {
    this.color = color;
    this.position = position;
    this.symbol = symbol;
  }

  public Color getColor() {
    return color;
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
}
