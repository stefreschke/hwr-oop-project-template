package hwr.oop.chess.application;

public class Position {
  private int x;
  private int y;

//  public Position(char x, int y) {
//    this(x - 96, y);
//  }

  public Position(int x, int y) {
    if (x < 1 || x > 8 || y < 1 || y > 8) {
      throw new IllegalArgumentException("Invalid Position");
    }

    this.x = x;
    this.y = y;
  }

  public int x() {
    return x;
  }

  public int y() {
    return y;
  }


  public boolean equals(Position pos2) {
    Position pos1 = this;
    return (pos1.x() == pos2.x()) && (pos1.y() == pos2.y());
  }
}
