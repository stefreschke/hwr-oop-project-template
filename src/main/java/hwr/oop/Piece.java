package hwr.oop;

public class Piece {
  Color color;
  char name;
  Position position;
  boolean captured;

  public Piece(Color color, Position position, char name){
    this.color = color;
    this.position = position;
    this.captured = false;
    this.name = name;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public char getName() {
    return name;
  }

  public void setName(char name) {
    this.name = name;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public boolean isCaptured() {
    return captured;
  }

  public void setCaptured(boolean captured) {
    this.captured = captured;
  }
}
