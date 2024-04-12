package hwr.oop;

import hwr.oop.enums.Color;

public class Piece {
  private final Color color;
  private final Square position;
  private boolean captured = false;

  public Piece(Color color, Square position) {
    this.color = color;
    this.position = position;
  }

  public Color getColor() {
    return color;
  }
  public Square getPosition() {
    return position;
  }
  public boolean isCaptured() {
    return captured;
  }
  public void setCaptured(){
    captured = true;
  }
}